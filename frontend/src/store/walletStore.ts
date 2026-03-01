import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { Wallet, Balance, MultiSigWallet, Transaction } from '../types';
import { walletApi, blockchainApi, transactionApi } from '../services/api';
import { useAuthStore } from './authStore';

// Helper to get userId from auth store
const getAuthUserId = () => useAuthStore.getState().user?.id || 'default-user';

interface WalletState {
  wallets: Wallet[];
  selectedWallet: Wallet | null;
  balances: Record<string, Balance>;
  transactions: Transaction[];
  isLoading: boolean;
  error: string | null;

  // Actions
  fetchWallets: () => Promise<void>;
  selectWallet: (wallet: Wallet | null) => void;
  fetchBalance: (address: string, network?: string) => Promise<void>;
  refreshBalance: (address: string, network?: string) => Promise<void>;
  fetchRecentTransactions: () => Promise<void>;
  createWallet: (name: string, network: string, password: string) => Promise<Wallet>;
  importWallet: (name: string, network: string, password: string, mnemonic?: string, privateKey?: string) => Promise<Wallet>;
  createMultiSigWallet: (name: string, network: string, threshold: number, signerAddresses: string[]) => Promise<MultiSigWallet>;
  clearError: () => void;
}

export const useWalletStore = create<WalletState>()(
  persist(
    (set, get) => ({
      wallets: [],
      selectedWallet: null,
      balances: {},
      transactions: [],
      isLoading: false,
      error: null,

      fetchWallets: async () => {
        const userId = getAuthUserId();
        set({ isLoading: true, error: null });
        try {
          const response = await walletApi.getUserWallets(userId);
          set({ wallets: response.data, isLoading: false });
          
          // Fetch balances for all wallets
          for (const wallet of response.data) {
            get().fetchBalance(wallet.address, wallet.network);
          }
        } catch (error: any) {
          set({ 
            error: error.response?.data?.message || 'Failed to fetch wallets',
            isLoading: false 
          });
        }
      },

      selectWallet: (wallet: Wallet | null) => set({ selectedWallet: wallet }),

      fetchBalance: async (address: string, network?: string) => {
        try {
          const response = await blockchainApi.getBalance(address, network);
          set((state) => ({
            balances: {
              ...state.balances,
              [address]: response.data,
            },
          }));
        } catch (error) {
          console.error('Failed to fetch balance:', error);
        }
      },

      refreshBalance: async (address: string, network?: string) => {
        // Alias for fetchBalance - forces a refresh
        await get().fetchBalance(address, network);
      },

      fetchRecentTransactions: async () => {
        const { wallets } = get();
        if (wallets.length === 0) {
          set({ transactions: [] });
          return;
        }
        try {
          // Fetch transactions for the first wallet (or could aggregate from all)
          const wallet = wallets[0];
          const response = await transactionApi.getTransactionHistory(wallet.id, 0, 10);
          set({ transactions: response.data?.transactions || [] });
        } catch (error) {
          console.error('Failed to fetch transactions:', error);
          set({ transactions: [] });
        }
      },

      createWallet: async (name: string, network: string, password: string) => {
        const userId = getAuthUserId();
        set({ isLoading: true, error: null });
        try {
          const response = await walletApi.createWallet({
            userId,
            name,
            network,
            password,
          });
          const newWallet = response.data;
          set((state) => ({
            wallets: [...state.wallets, newWallet],
            isLoading: false,
          }));
          return newWallet;
        } catch (error: any) {
          set({
            error: error.response?.data?.message || 'Failed to create wallet',
            isLoading: false,
          });
          throw error;
        }
      },

      importWallet: async (name: string, network: string, password: string, mnemonic?: string, privateKey?: string) => {
        const userId = getAuthUserId();
        set({ isLoading: true, error: null });
        try {
          const response = await walletApi.importWallet({
            userId,
            name,
            network,
            password,
            mnemonic,
            privateKey,
          });
          const newWallet = response.data;
          set((state) => ({
            wallets: [...state.wallets, newWallet],
            isLoading: false,
          }));
          return newWallet;
        } catch (error: any) {
          set({
            error: error.response?.data?.message || 'Failed to import wallet',
            isLoading: false,
          });
          throw error;
        }
      },

      createMultiSigWallet: async (name: string, network: string, threshold: number, signerAddresses: string[]) => {
        const userId = getAuthUserId();
        set({ isLoading: true, error: null });
        try {
          const response = await walletApi.createMultiSigWallet({
            userId,
            name,
            network,
            threshold,
            signerAddresses,
          });
          const newWallet = response.data;
          set((state) => ({
            wallets: [...state.wallets, { ...newWallet, type: 'MULTI_SIG' as const }],
            isLoading: false,
          }));
          return newWallet;
        } catch (error: any) {
          set({
            error: error.response?.data?.message || 'Failed to create multi-sig wallet',
            isLoading: false,
          });
          throw error;
        }
      },

      clearError: () => set({ error: null }),
    }),
    {
      name: 'wallet-storage',
    }
  )
);
