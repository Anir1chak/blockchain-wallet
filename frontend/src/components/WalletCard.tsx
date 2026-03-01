import { Wallet, Balance } from '../types';
import { Link } from 'react-router-dom';
import { 
  WalletIcon, 
  UserGroupIcon,
  ArrowTopRightOnSquareIcon 
} from '@heroicons/react/24/outline';

interface WalletCardProps {
  wallet: Wallet;
  balance?: Balance;
}

export default function WalletCard({ wallet, balance }: WalletCardProps) {
  const isMultiSig = wallet.type === 'MULTI_SIG';
  
  const formatAddress = (address: string) => {
    return `${address.slice(0, 6)}...${address.slice(-4)}`;
  };

  const formatBalance = (balanceEth: string) => {
    const num = parseFloat(balanceEth);
    if (num === 0) return '0';
    if (num < 0.0001) return '< 0.0001';
    return num.toFixed(4);
  };

  const walletLink = isMultiSig ? `/multisig/${wallet.id}` : `/wallet/${wallet.id}`;

  return (
    <Link to={walletLink} className="block">
      <div className="card hover:border-primary-500 transition-colors cursor-pointer">
        <div className="flex items-start justify-between">
          <div className="flex items-center space-x-3">
            <div className={`p-2 rounded-lg ${isMultiSig ? 'bg-purple-500/20' : 'bg-primary-500/20'}`}>
              {isMultiSig ? (
                <UserGroupIcon className={`h-6 w-6 ${isMultiSig ? 'text-purple-400' : 'text-primary-400'}`} />
              ) : (
                <WalletIcon className={`h-6 w-6 ${isMultiSig ? 'text-purple-400' : 'text-primary-400'}`} />
              )}
            </div>
            <div>
              <h3 className="font-semibold text-white">{wallet.name}</h3>
              <p className="text-sm text-gray-400 flex items-center">
                {formatAddress(wallet.address)}
                <ArrowTopRightOnSquareIcon className="h-3 w-3 ml-1" />
              </p>
            </div>
          </div>
          
          {isMultiSig && (
            <span className="px-2 py-1 text-xs bg-purple-500/20 text-purple-400 rounded-full">
              Multi-Sig
            </span>
          )}
        </div>

        <div className="mt-4">
          {balance ? (
            <>
              <div className="text-2xl font-bold text-white">
                Ξ {formatBalance(balance.balanceEth || '0')}
              </div>
              <div className="text-sm text-gray-400">
                ${parseFloat(balance.balanceUsd || '0').toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
              </div>
            </>
          ) : (
            <div className="animate-pulse">
              <div className="h-8 bg-gray-700 rounded w-24 mb-2"></div>
              <div className="h-4 bg-gray-700 rounded w-16"></div>
            </div>
          )}
        </div>

        <div className="mt-4 flex items-center justify-between text-sm">
          <span className="text-gray-500">{wallet.network.replace('_', ' ')}</span>
          <span className={`flex items-center ${wallet.type === 'SINGLE_SIG' ? 'text-green-400' : 'text-purple-400'}`}>
            <span className="w-2 h-2 rounded-full bg-current mr-2"></span>
            {wallet.type === 'SINGLE_SIG' ? 'Active' : 'Multi-Sig'}
          </span>
        </div>
      </div>
    </Link>
  );
}
