import { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useWalletStore } from '../store/walletStore';
import {
  UserGroupIcon,
  PlusIcon,
  ArrowTopRightOnSquareIcon,
  ShieldCheckIcon,
} from '@heroicons/react/24/outline';

export default function MultiSigList() {
  const { wallets, fetchWallets, isLoading, balances } = useWalletStore();
  
  useEffect(() => {
    fetchWallets();
  }, [fetchWallets]);

  const multiSigWallets = wallets.filter(w => w.type === 'MULTI_SIG');

  const formatAddress = (address: string) => {
    return `${address.slice(0, 6)}...${address.slice(-4)}`;
  };

  const getBalance = (address: string) => {
    const balance = balances[address];
    if (!balance) return '0.0000';
    const eth = parseFloat(balance.balanceEth || '0');
    return eth.toFixed(4);
  };

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-white">Multi-Signature Wallets</h1>
          <p className="text-gray-400">Manage wallets that require multiple approvals</p>
        </div>
        <Link
          to="/create"
          className="inline-flex items-center px-4 py-2 bg-purple-600 text-white rounded-lg hover:bg-purple-700 transition-colors"
        >
          <PlusIcon className="h-5 w-5 mr-2" />
          Create Multi-Sig
        </Link>
      </div>

      {/* Info Card */}
      <div className="bg-gradient-to-r from-purple-900/50 to-blue-900/50 rounded-xl p-6 border border-purple-500/30">
        <div className="flex items-start space-x-4">
          <div className="p-3 bg-purple-500/20 rounded-lg">
            <ShieldCheckIcon className="h-8 w-8 text-purple-400" />
          </div>
          <div>
            <h3 className="text-lg font-semibold text-white">Enhanced Security</h3>
            <p className="text-gray-300 mt-1">
              Multi-signature wallets require multiple approvals before transactions are executed,
              providing additional security for your digital assets.
            </p>
          </div>
        </div>
      </div>

      {/* Wallets List */}
      {isLoading ? (
        <div className="flex items-center justify-center h-48">
          <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-purple-400"></div>
        </div>
      ) : multiSigWallets.length === 0 ? (
        <div className="text-center py-16 bg-gray-800/50 rounded-xl border border-gray-700">
          <UserGroupIcon className="h-16 w-16 text-gray-600 mx-auto mb-4" />
          <h3 className="text-xl font-semibold text-white mb-2">No Multi-Sig Wallets</h3>
          <p className="text-gray-400 mb-6">Create your first multi-signature wallet to get started</p>
          <Link
            to="/create"
            className="inline-flex items-center px-6 py-3 bg-purple-600 text-white rounded-lg hover:bg-purple-700 transition-colors"
          >
            <PlusIcon className="h-5 w-5 mr-2" />
            Create Multi-Sig Wallet
          </Link>
        </div>
      ) : (
        <div className="grid gap-4">
          {multiSigWallets.map((wallet) => (
            <Link
              key={wallet.id}
              to={`/multisig/${wallet.id}`}
              className="block bg-gray-800 rounded-xl p-6 border border-gray-700 hover:border-purple-500 transition-colors"
            >
              <div className="flex items-start justify-between">
                <div className="flex items-center space-x-4">
                  <div className="p-3 bg-purple-500/20 rounded-lg">
                    <UserGroupIcon className="h-8 w-8 text-purple-400" />
                  </div>
                  <div>
                    <h3 className="text-lg font-semibold text-white">{wallet.name}</h3>
                    <p className="text-sm text-gray-400 flex items-center mt-1">
                      {formatAddress(wallet.address)}
                      <ArrowTopRightOnSquareIcon className="h-3 w-3 ml-1" />
                    </p>
                  </div>
                </div>
                
                <div className="text-right">
                  <div className="text-xl font-bold text-white">
                    Ξ {getBalance(wallet.address)}
                  </div>
                  <span className="px-3 py-1 text-xs bg-purple-500/20 text-purple-400 rounded-full">
                    Multi-Sig
                  </span>
                </div>
              </div>

              <div className="mt-4 pt-4 border-t border-gray-700 flex items-center justify-between">
                <div className="flex items-center space-x-4 text-sm">
                  <span className="text-gray-400">
                    Network: <span className="text-white">{wallet.network.replace('_', ' ')}</span>
                  </span>
                </div>
                <span className="text-sm text-purple-400 flex items-center">
                  <span className="w-2 h-2 rounded-full bg-purple-400 mr-2 animate-pulse"></span>
                  Manage →
                </span>
              </div>
            </Link>
          ))}
        </div>
      )}
    </div>
  );
}
