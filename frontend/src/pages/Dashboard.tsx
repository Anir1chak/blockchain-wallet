import { useState, useEffect } from 'react';
import { useWalletStore } from '../store/walletStore';
import WalletCard from '../components/WalletCard';
import TransactionList from '../components/TransactionList';
import { 
  PlusIcon, 
  ArrowTrendingUpIcon,
  ArrowTrendingDownIcon,
  CurrencyDollarIcon,
  WalletIcon
} from '@heroicons/react/24/outline';
import { Link } from 'react-router-dom';

export default function Dashboard() {
  const { wallets, transactions, fetchWallets, fetchRecentTransactions, isLoading } = useWalletStore();
  const [portfolioValue, setPortfolioValue] = useState(0);
  const [portfolioChange, setPortfolioChange] = useState(0);

  useEffect(() => {
    fetchWallets();
    fetchRecentTransactions();
  }, [fetchWallets, fetchRecentTransactions]);

  useEffect(() => {
    // Calculate total portfolio value
    const total = wallets.reduce((sum, wallet) => {
      const balance = parseFloat(wallet.balance || '0') / 1e18;
      return sum + balance;
    }, 0);
    setPortfolioValue(total);
    // Simulate portfolio change (in real app, this would come from API)
    setPortfolioChange(2.34);
  }, [wallets]);

  const recentTransactions = (transactions || []).slice(0, 5);

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-white">Dashboard</h1>
          <p className="text-gray-400">Welcome to your crypto wallet</p>
        </div>
        <Link
          to="/create"
          className="inline-flex items-center px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
        >
          <PlusIcon className="h-5 w-5 mr-2" />
          Create Wallet
        </Link>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-400 text-sm">Portfolio Value</p>
              <p className="text-2xl font-bold text-white mt-1">
                {portfolioValue.toFixed(4)} ETH
              </p>
            </div>
            <div className="p-3 bg-primary-500/20 rounded-lg">
              <CurrencyDollarIcon className="h-6 w-6 text-primary-400" />
            </div>
          </div>
          <div className={`flex items-center mt-4 text-sm ${
            portfolioChange >= 0 ? 'text-green-400' : 'text-red-400'
          }`}>
            {portfolioChange >= 0 ? (
              <ArrowTrendingUpIcon className="h-4 w-4 mr-1" />
            ) : (
              <ArrowTrendingDownIcon className="h-4 w-4 mr-1" />
            )}
            <span>{Math.abs(portfolioChange).toFixed(2)}% (24h)</span>
          </div>
        </div>

        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-400 text-sm">Total Wallets</p>
              <p className="text-2xl font-bold text-white mt-1">{wallets.length}</p>
            </div>
            <div className="p-3 bg-blue-500/20 rounded-lg">
              <WalletIcon className="h-6 w-6 text-blue-400" />
            </div>
          </div>
          <p className="text-gray-500 text-sm mt-4">
            {wallets.filter(w => w.type === 'MULTI_SIG').length} Multi-Sig
          </p>
        </div>

        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
          <div className="flex items-center justify-between">
            <div>
              <p className="text-gray-400 text-sm">Transactions</p>
              <p className="text-2xl font-bold text-white mt-1">{(transactions || []).length}</p>
            </div>
            <div className="p-3 bg-green-500/20 rounded-lg">
              <ArrowTrendingUpIcon className="h-6 w-6 text-green-400" />
            </div>
          </div>
          <p className="text-gray-500 text-sm mt-4">
            {(transactions || []).filter(t => t.status === 'PENDING').length} Pending
          </p>
        </div>
      </div>

      {/* Wallets Section */}
      <div>
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-lg font-semibold text-white">Your Wallets</h2>
          <Link to="/wallets" className="text-primary-400 hover:text-primary-300 text-sm">
            View All →
          </Link>
        </div>
        
        {isLoading ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {[1, 2, 3].map((i) => (
              <div key={i} className="bg-gray-800 rounded-xl p-6 animate-pulse">
                <div className="h-4 bg-gray-700 rounded w-1/2 mb-4"></div>
                <div className="h-6 bg-gray-700 rounded w-3/4 mb-2"></div>
                <div className="h-4 bg-gray-700 rounded w-full"></div>
              </div>
            ))}
          </div>
        ) : wallets.length > 0 ? (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {wallets.slice(0, 6).map((wallet) => (
              <WalletCard key={wallet.id} wallet={wallet} />
            ))}
          </div>
        ) : (
          <div className="bg-gray-800 rounded-xl p-12 text-center border border-gray-700 border-dashed">
            <WalletIcon className="h-12 w-12 text-gray-600 mx-auto mb-4" />
            <h3 className="text-lg font-medium text-gray-400">No wallets yet</h3>
            <p className="text-gray-500 mt-1 mb-4">Create your first wallet to get started</p>
            <Link
              to="/create"
              className="inline-flex items-center px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
            >
              <PlusIcon className="h-5 w-5 mr-2" />
              Create Wallet
            </Link>
          </div>
        )}
      </div>

      {/* Recent Transactions Section */}
      <div>
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-lg font-semibold text-white">Recent Transactions</h2>
          <Link to="/transactions" className="text-primary-400 hover:text-primary-300 text-sm">
            View All →
          </Link>
        </div>
        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
          <TransactionList transactions={recentTransactions} showWalletAddress />
        </div>
      </div>
    </div>
  );
}
