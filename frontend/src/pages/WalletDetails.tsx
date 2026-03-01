import { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { useWalletStore } from '../store/walletStore';
import TransactionList from '../components/TransactionList';
import { api } from '../services/api';
import { Transaction } from '../types';
import {
  ArrowLeftIcon,
  ClipboardDocumentIcon,
  CheckIcon,
  PaperAirplaneIcon,
  QrCodeIcon,
  ArrowPathIcon,
  KeyIcon,
  TrashIcon,
  UserGroupIcon
} from '@heroicons/react/24/outline';

export default function WalletDetails() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { wallets, fetchWallets, refreshBalance } = useWalletStore();
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [copied, setCopied] = useState(false);
  const [showQR, setShowQR] = useState(false);
  const [activeTab, setActiveTab] = useState<'transactions' | 'settings'>('transactions');

  const wallet = wallets.find((w) => w.id === id);

  useEffect(() => {
    if (!wallet) {
      fetchWallets();
    }
  }, [wallet, fetchWallets]);

  useEffect(() => {
    const loadTransactions = async () => {
      if (wallet) {
        setIsLoading(true);
        try {
          const response = await api.getTransactionHistory(wallet.id, 0, 50);
          setTransactions(response.transactions || []);
        } catch (error) {
          console.error('Failed to load transactions:', error);
        } finally {
          setIsLoading(false);
        }
      }
    };

    loadTransactions();
  }, [wallet]);

  const handleCopyAddress = async () => {
    if (wallet) {
      await navigator.clipboard.writeText(wallet.address);
      setCopied(true);
      setTimeout(() => setCopied(false), 2000);
    }
  };

  const handleRefreshBalance = async () => {
    if (wallet) {
      await refreshBalance(wallet.address);
    }
  };

  const formatBalance = (balance: string) => {
    const wei = BigInt(balance);
    const eth = Number(wei) / 1e18;
    return eth.toFixed(6);
  };

  if (!wallet) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary-400"></div>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center space-x-4">
        <button
          onClick={() => navigate(-1)}
          className="p-2 rounded-lg hover:bg-gray-700 transition-colors"
        >
          <ArrowLeftIcon className="h-5 w-5 text-gray-400" />
        </button>
        <div>
          <h1 className="text-2xl font-bold text-white">{wallet.name}</h1>
          <p className="text-gray-400 text-sm">
            {wallet.type === 'MULTI_SIG' ? 'Multi-Signature Wallet' : 'Standard Wallet'}
          </p>
        </div>
      </div>

      {/* Wallet Card */}
      <div className="bg-gradient-to-br from-primary-600 to-primary-800 rounded-2xl p-6 shadow-xl">
        <div className="flex items-start justify-between">
          <div>
            <p className="text-primary-200 text-sm">Balance</p>
            <p className="text-4xl font-bold text-white mt-1">
              {formatBalance(wallet.balance || '0')} ETH
            </p>
            <p className="text-primary-200 text-sm mt-2">
              ≈ ${(parseFloat(formatBalance(wallet.balance || '0')) * 2500).toFixed(2)} USD
            </p>
          </div>
          <button
            onClick={handleRefreshBalance}
            className="p-2 bg-white/10 rounded-lg hover:bg-white/20 transition-colors"
          >
            <ArrowPathIcon className="h-5 w-5 text-white" />
          </button>
        </div>

        <div className="mt-6">
          <p className="text-primary-200 text-sm mb-2">Address</p>
          <div className="flex items-center space-x-2">
            <code className="flex-1 text-white text-sm bg-white/10 px-3 py-2 rounded-lg font-mono overflow-hidden text-ellipsis">
              {wallet.address}
            </code>
            <button
              onClick={handleCopyAddress}
              className="p-2 bg-white/10 rounded-lg hover:bg-white/20 transition-colors"
            >
              {copied ? (
                <CheckIcon className="h-5 w-5 text-green-400" />
              ) : (
                <ClipboardDocumentIcon className="h-5 w-5 text-white" />
              )}
            </button>
            <button
              onClick={() => setShowQR(!showQR)}
              className="p-2 bg-white/10 rounded-lg hover:bg-white/20 transition-colors"
            >
              <QrCodeIcon className="h-5 w-5 text-white" />
            </button>
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex space-x-3 mt-6">
          <Link
            to={`/send?wallet=${wallet.id}`}
            className="flex-1 flex items-center justify-center space-x-2 px-4 py-3 bg-white/10 rounded-lg hover:bg-white/20 transition-colors"
          >
            <PaperAirplaneIcon className="h-5 w-5 text-white" />
            <span className="text-white font-medium">Send</span>
          </Link>
          <button
            onClick={() => setShowQR(true)}
            className="flex-1 flex items-center justify-center space-x-2 px-4 py-3 bg-white/10 rounded-lg hover:bg-white/20 transition-colors"
          >
            <QrCodeIcon className="h-5 w-5 text-white" />
            <span className="text-white font-medium">Receive</span>
          </button>
          {wallet.type === 'MULTI_SIG' && (
            <Link
              to={`/multisig/${wallet.id}`}
              className="flex-1 flex items-center justify-center space-x-2 px-4 py-3 bg-white/10 rounded-lg hover:bg-white/20 transition-colors"
            >
              <UserGroupIcon className="h-5 w-5 text-white" />
              <span className="text-white font-medium">Multi-Sig</span>
            </Link>
          )}
        </div>
      </div>

      {/* Tabs */}
      <div className="border-b border-gray-700">
        <nav className="flex space-x-8">
          <button
            onClick={() => setActiveTab('transactions')}
            className={`py-4 text-sm font-medium border-b-2 transition-colors ${
              activeTab === 'transactions'
                ? 'border-primary-400 text-primary-400'
                : 'border-transparent text-gray-400 hover:text-gray-300'
            }`}
          >
            Transactions
          </button>
          <button
            onClick={() => setActiveTab('settings')}
            className={`py-4 text-sm font-medium border-b-2 transition-colors ${
              activeTab === 'settings'
                ? 'border-primary-400 text-primary-400'
                : 'border-transparent text-gray-400 hover:text-gray-300'
            }`}
          >
            Settings
          </button>
        </nav>
      </div>

      {/* Tab Content */}
      {activeTab === 'transactions' && (
        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
          {isLoading ? (
            <div className="flex items-center justify-center h-32">
              <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary-400"></div>
            </div>
          ) : (
            <TransactionList transactions={transactions} />
          )}
        </div>
      )}

      {activeTab === 'settings' && (
        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700 space-y-6">
          <div>
            <h3 className="text-lg font-medium text-white mb-4">Wallet Settings</h3>
            
            <div className="space-y-4">
              <div className="flex items-center justify-between p-4 bg-gray-700/50 rounded-lg">
                <div className="flex items-center space-x-3">
                  <KeyIcon className="h-5 w-5 text-yellow-400" />
                  <div>
                    <p className="text-white font-medium">Export Private Key</p>
                    <p className="text-gray-400 text-sm">View and copy your private key</p>
                  </div>
                </div>
                <button className="px-4 py-2 bg-yellow-500/20 text-yellow-400 rounded-lg hover:bg-yellow-500/30 transition-colors">
                  Export
                </button>
              </div>

              <div className="flex items-center justify-between p-4 bg-gray-700/50 rounded-lg">
                <div className="flex items-center space-x-3">
                  <TrashIcon className="h-5 w-5 text-red-400" />
                  <div>
                    <p className="text-white font-medium">Delete Wallet</p>
                    <p className="text-gray-400 text-sm">Remove this wallet from your account</p>
                  </div>
                </div>
                <button className="px-4 py-2 bg-red-500/20 text-red-400 rounded-lg hover:bg-red-500/30 transition-colors">
                  Delete
                </button>
              </div>
            </div>
          </div>

          <div>
            <h3 className="text-lg font-medium text-white mb-4">Network Details</h3>
            <div className="grid grid-cols-2 gap-4 text-sm">
              <div className="p-4 bg-gray-700/50 rounded-lg">
                <p className="text-gray-400">Network</p>
                <p className="text-white font-medium mt-1">{wallet.network}</p>
              </div>
              <div className="p-4 bg-gray-700/50 rounded-lg">
                <p className="text-gray-400">Wallet Type</p>
                <p className="text-white font-medium mt-1">{wallet.type}</p>
              </div>
              <div className="p-4 bg-gray-700/50 rounded-lg">
                <p className="text-gray-400">Created</p>
                <p className="text-white font-medium mt-1">
                  {new Date(wallet.createdAt).toLocaleDateString()}
                </p>
              </div>
              <div className="p-4 bg-gray-700/50 rounded-lg">
                <p className="text-gray-400">Last Activity</p>
                <p className="text-white font-medium mt-1">
                  {new Date(wallet.updatedAt).toLocaleDateString()}
                </p>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* QR Code Modal */}
      {showQR && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
          <div className="bg-gray-800 rounded-xl p-6 max-w-sm w-full mx-4">
            <h3 className="text-lg font-medium text-white mb-4 text-center">Receive ETH</h3>
            <div className="bg-white p-4 rounded-lg">
              {/* QR Code placeholder - in production, use a QR code library */}
              <div className="w-48 h-48 mx-auto bg-gray-200 flex items-center justify-center">
                <span className="text-gray-500 text-sm">QR Code</span>
              </div>
            </div>
            <p className="text-center text-gray-400 text-sm mt-4 break-all font-mono">
              {wallet.address}
            </p>
            <button
              onClick={() => setShowQR(false)}
              className="w-full mt-4 px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors"
            >
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
