import axios from 'axios';

const API_BASE_URL = '/api';

const axiosClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Wallet API
export const walletApi = {
  createWallet: (data: {
    userId: string;
    name: string;
    network: string;
    password: string;
  }) => axiosClient.post('/wallets', data),

  getWallet: (walletId: string) => axiosClient.get(`/wallets/${walletId}`),

  getUserWallets: (userId: string) => axiosClient.get(`/wallets/user/${userId}`),

  importWallet: (data: {
    userId: string;
    name: string;
    network: string;
    password: string;
    privateKey?: string;
    mnemonic?: string;
  }) => axiosClient.post('/wallets/import', data),

  createMultiSigWallet: (data: {
    userId: string;
    name: string;
    network: string;
    threshold: number;
    signerAddresses: string[];
  }) => axiosClient.post('/wallets/multisig', data),

  getMultiSigWallet: (walletId: string) => axiosClient.get(`/wallets/multisig/${walletId}`),

  addSigner: (walletId: string, data: { signerAddress: string; signerName: string }) =>
    axiosClient.post(`/wallets/multisig/${walletId}/signers`, data),

  removeSigner: (walletId: string, signerAddress: string) =>
    axiosClient.delete(`/wallets/multisig/${walletId}/signers/${signerAddress}`),
};

// Transaction API
export const transactionApi = {
  sendTransaction: (data: {
    walletId: string;
    toAddress: string;
    amount: string;
    password?: string;
    gasSpeed?: string;
    data?: string;
  }) => axiosClient.post('/transactions', data),

  getTransaction: (txHash: string, network?: string) =>
    axiosClient.get(`/transactions/${txHash}`, { params: { network } }),

  getTransactionHistory: (
    walletId: string,
    page?: number,
    pageSize?: number,
    type?: string
  ) =>
    axiosClient.get(`/transactions/wallet/${walletId}`, {
      params: { page, pageSize, type },
    }),

  estimateGas: (
    fromAddress: string,
    toAddress: string,
    amount: string,
    data?: string,
    network?: string
  ) =>
    axiosClient.post('/transactions/estimate-gas', null, {
      params: { fromAddress, toAddress, amount, data, network },
    }),

  createMultiSigTransaction: (data: {
    walletId: string;
    initiatorAddress: string;
    toAddress: string;
    amount: string;
    description?: string;
  }) => axiosClient.post('/transactions/multisig', data),

  signMultiSigTransaction: (multiSigTxId: string, data: { signerAddress: string; password?: string }) =>
    axiosClient.post(`/transactions/multisig/${multiSigTxId}/sign`, data),

  getPendingMultiSigTransactions: (walletId: string) =>
    axiosClient.get(`/transactions/multisig/pending/${walletId}`),

  executeMultiSigTransaction: (multiSigTxId: string) =>
    axiosClient.post(`/transactions/multisig/${multiSigTxId}/execute`),
};

// Blockchain API
export const blockchainApi = {
  getBalance: (address: string, network?: string) =>
    axiosClient.get(`/blockchain/balance/${address}`, { params: { network } }),

  getBalances: (addresses: string[], network?: string) =>
    axiosClient.post('/blockchain/balances', addresses, { params: { network } }),

  getGasPrices: (network?: string) =>
    axiosClient.get('/blockchain/gas-prices', { params: { network } }),

  getBlockNumber: (network?: string) =>
    axiosClient.get('/blockchain/block-number', { params: { network } }),

  validateAddress: (address: string) =>
    axiosClient.get(`/blockchain/validate-address/${address}`),

  getNetworkStatus: (network?: string) =>
    axiosClient.get('/blockchain/network-status', { params: { network } }),

  getTokenBalance: (walletAddress: string, tokenAddress: string, network?: string) =>
    axiosClient.get('/blockchain/token-balance', {
      params: { walletAddress, tokenAddress, network },
    }),
};

// Combined API object for easier imports
export const api = {
  // Wallet methods
  createWallet: walletApi.createWallet,
  getWallet: walletApi.getWallet,
  getUserWallets: walletApi.getUserWallets,
  importWallet: walletApi.importWallet,
  createMultiSigWallet: walletApi.createMultiSigWallet,
  getMultiSigWallet: walletApi.getMultiSigWallet,
  addSigner: walletApi.addSigner,
  removeSigner: walletApi.removeSigner,
  
  // Transaction methods  
  sendTransaction: transactionApi.sendTransaction,
  getTransaction: transactionApi.getTransaction,
  getTransactionHistory: (address: string, page?: number, limit?: number) =>
    transactionApi.getTransactionHistory(address, page, limit).then(res => ({
      transactions: res.data?.transactions || res.data || []
    })),
  estimateGas: (from: string, to: string, amount: string, network: string, _gasSpeed?: string) =>
    transactionApi.estimateGas(from, to, amount, undefined, network).then(res => res.data),
  createMultiSigTransaction: transactionApi.createMultiSigTransaction,
  signMultiSigTransaction: transactionApi.signMultiSigTransaction,
  getPendingMultiSigTransactions: transactionApi.getPendingMultiSigTransactions,
  executeMultiSigTransaction: transactionApi.executeMultiSigTransaction,
  
  // Blockchain methods
  getBalance: blockchainApi.getBalance,
  getBalances: blockchainApi.getBalances,
  getGasPrices: blockchainApi.getGasPrices,
  getBlockNumber: blockchainApi.getBlockNumber,
  validateAddress: (address: string, _network?: string) =>
    blockchainApi.validateAddress(address).then(res => ({ valid: res.data?.valid || false })),
  getNetworkStatus: blockchainApi.getNetworkStatus,
  getTokenBalance: blockchainApi.getTokenBalance,
};

export default api;
