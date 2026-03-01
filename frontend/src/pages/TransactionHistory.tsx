import { useState, useEffect } from 'react';
import { useWalletStore } from '../store/walletStore';
import TransactionList from '../components/TransactionList';
import { api } from '../services/api';
import { Transaction } from '../types';
import {
  FunnelIcon,
  ArrowPathIcon,
  MagnifyingGlassIcon
} from '@heroicons/react/24/outline';

type FilterType = 'ALL' | 'SEND' | 'RECEIVE' | 'SWAP' | 'CONTRACT_CALL';
type FilterStatus = 'ALL' | 'PENDING' | 'CONFIRMED' | 'FAILED';

export default function TransactionHistory() {
  const { wallets } = useWalletStore();
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [filteredTransactions, setFilteredTransactions] = useState<Transaction[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedWallet, setSelectedWallet] = useState<string>('all');
  const [typeFilter, setTypeFilter] = useState<FilterType>('ALL');
  const [statusFilter, setStatusFilter] = useState<FilterStatus>('ALL');
  const [searchQuery, setSearchQuery] = useState('');
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);

  const LIMIT = 20;

  useEffect(() => {
    loadTransactions();
  }, [selectedWallet, page]);

  useEffect(() => {
    filterTransactions();
  }, [transactions, typeFilter, statusFilter, searchQuery]);

  const loadTransactions = async () => {
    setIsLoading(true);
    try {
      let allTxs: Transaction[] = [];
      
      if (selectedWallet === 'all') {
        // Load transactions for all wallets
        const promises = wallets.map(wallet =>
          api.getTransactionHistory(wallet.address, page, LIMIT)
        );
        const responses = await Promise.all(promises);
        responses.forEach(response => {
          allTxs = [...allTxs, ...response.transactions];
        });
      } else {
        const wallet = wallets.find(w => w.id === selectedWallet);
        if (wallet) {
          const response = await api.getTransactionHistory(wallet.address, page, LIMIT);
          allTxs = response.transactions;
          setHasMore(response.transactions.length === LIMIT);
        }
      }

      // Sort by timestamp descending
      allTxs.sort((a, b) => b.timestamp - a.timestamp);
      
      if (page === 0) {
        setTransactions(allTxs);
      } else {
        setTransactions(prev => [...prev, ...allTxs]);
      }
    } catch (error) {
      console.error('Failed to load transactions:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const filterTransactions = () => {
    let filtered = [...transactions];

    // Type filter
    if (typeFilter !== 'ALL') {
      filtered = filtered.filter(tx => tx.type === typeFilter);
    }

    // Status filter
    if (statusFilter !== 'ALL') {
      filtered = filtered.filter(tx => tx.status === statusFilter);
    }

    // Search filter
    if (searchQuery) {
      const query = searchQuery.toLowerCase();
      filtered = filtered.filter(tx =>
        tx.txHash.toLowerCase().includes(query) ||
        tx.fromAddress.toLowerCase().includes(query) ||
        tx.toAddress.toLowerCase().includes(query)
      );
    }

    setFilteredTransactions(filtered);
  };

  const handleRefresh = () => {
    setPage(0);
    loadTransactions();
  };

  const handleLoadMore = () => {
    setPage(prev => prev + 1);
  };

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-white">Transaction History</h1>
          <p className="text-gray-400">View all your blockchain transactions</p>
        </div>
        <button
          onClick={handleRefresh}
          disabled={isLoading}
          className="inline-flex items-center px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors disabled:opacity-50"
        >
          <ArrowPathIcon className={`h-5 w-5 mr-2 ${isLoading ? 'animate-spin' : ''}`} />
          Refresh
        </button>
      </div>

      {/* Filters */}
      <div className="bg-gray-800 rounded-xl p-4 border border-gray-700">
        <div className="flex flex-wrap items-center gap-4">
          {/* Search */}
          <div className="flex-1 min-w-64">
            <div className="relative">
              <MagnifyingGlassIcon className="absolute left-3 top-1/2 -translate-y-1/2 h-5 w-5 text-gray-400" />
              <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Search by hash or address..."
                className="w-full bg-gray-700 border border-gray-600 rounded-lg pl-10 pr-4 py-2 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
              />
            </div>
          </div>

          {/* Wallet Filter */}
          <div>
            <select
              value={selectedWallet}
              onChange={(e) => {
                setSelectedWallet(e.target.value);
                setPage(0);
              }}
              className="bg-gray-700 border border-gray-600 rounded-lg px-4 py-2 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            >
              <option value="all">All Wallets</option>
              {wallets.map(wallet => (
                <option key={wallet.id} value={wallet.id}>{wallet.name}</option>
              ))}
            </select>
          </div>

          {/* Type Filter */}
          <div className="flex items-center space-x-2">
            <FunnelIcon className="h-5 w-5 text-gray-400" />
            <select
              value={typeFilter}
              onChange={(e) => setTypeFilter(e.target.value as FilterType)}
              className="bg-gray-700 border border-gray-600 rounded-lg px-4 py-2 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            >
              <option value="ALL">All Types</option>
              <option value="SEND">Sent</option>
              <option value="RECEIVE">Received</option>
              <option value="SWAP">Swapped</option>
              <option value="CONTRACT_CALL">Contract Calls</option>
            </select>
          </div>

          {/* Status Filter */}
          <div>
            <select
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value as FilterStatus)}
              className="bg-gray-700 border border-gray-600 rounded-lg px-4 py-2 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            >
              <option value="ALL">All Status</option>
              <option value="PENDING">Pending</option>
              <option value="CONFIRMED">Confirmed</option>
              <option value="FAILED">Failed</option>
            </select>
          </div>
        </div>

        {/* Active Filters */}
        {(typeFilter !== 'ALL' || statusFilter !== 'ALL' || searchQuery) && (
          <div className="flex items-center gap-2 mt-4 pt-4 border-t border-gray-700">
            <span className="text-gray-400 text-sm">Active filters:</span>
            {typeFilter !== 'ALL' && (
              <span className="px-2 py-1 bg-primary-500/20 text-primary-400 rounded text-sm">
                {typeFilter}
              </span>
            )}
            {statusFilter !== 'ALL' && (
              <span className="px-2 py-1 bg-green-500/20 text-green-400 rounded text-sm">
                {statusFilter}
              </span>
            )}
            {searchQuery && (
              <span className="px-2 py-1 bg-blue-500/20 text-blue-400 rounded text-sm">
                "{searchQuery}"
              </span>
            )}
            <button
              onClick={() => {
                setTypeFilter('ALL');
                setStatusFilter('ALL');
                setSearchQuery('');
              }}
              className="text-gray-400 hover:text-white text-sm"
            >
              Clear all
            </button>
          </div>
        )}
      </div>

      {/* Results Count */}
      <div className="text-gray-400 text-sm">
        Showing {filteredTransactions.length} transaction{filteredTransactions.length !== 1 ? 's' : ''}
      </div>

      {/* Transactions List */}
      <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
        {isLoading && page === 0 ? (
          <div className="flex items-center justify-center h-32">
            <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary-400"></div>
          </div>
        ) : (
          <>
            <TransactionList transactions={filteredTransactions} showWalletAddress />
            
            {/* Load More */}
            {hasMore && filteredTransactions.length > 0 && (
              <div className="mt-6 text-center">
                <button
                  onClick={handleLoadMore}
                  disabled={isLoading}
                  className="px-6 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors disabled:opacity-50"
                >
                  {isLoading ? 'Loading...' : 'Load More'}
                </button>
              </div>
            )}
          </>
        )}
      </div>
    </div>
  );
}
