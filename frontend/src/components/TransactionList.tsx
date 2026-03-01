import { Transaction } from '../types';
import { 
  ArrowUpRightIcon, 
  ArrowDownLeftIcon,
  ArrowsRightLeftIcon,
  DocumentTextIcon,
  ClockIcon,
  CheckCircleIcon,
  XCircleIcon
} from '@heroicons/react/24/outline';

interface TransactionListProps {
  transactions: Transaction[];
  showWalletAddress?: boolean;
}

export default function TransactionList({ transactions, showWalletAddress = false }: TransactionListProps) {
  // Ensure transactions is always an array
  const txList = Array.isArray(transactions) ? transactions : [];
  
  const formatAddress = (address: string) => {
    return `${address.slice(0, 6)}...${address.slice(-4)}`;
  };

  const formatAmount = (amount: string) => {
    const wei = BigInt(amount);
    const eth = Number(wei) / 1e18;
    return eth.toFixed(6);
  };

  const formatTimestamp = (timestamp: number) => {
    const date = new Date(timestamp * 1000);
    const now = new Date();
    const diff = now.getTime() - date.getTime();
    
    if (diff < 60000) return 'Just now';
    if (diff < 3600000) return `${Math.floor(diff / 60000)} min ago`;
    if (diff < 86400000) return `${Math.floor(diff / 3600000)} hours ago`;
    return date.toLocaleDateString();
  };

  const getTypeIcon = (type: Transaction['type']) => {
    switch (type) {
      case 'SEND':
        return <ArrowUpRightIcon className="h-5 w-5 text-red-400" />;
      case 'RECEIVE':
        return <ArrowDownLeftIcon className="h-5 w-5 text-green-400" />;
      case 'SWAP':
        return <ArrowsRightLeftIcon className="h-5 w-5 text-blue-400" />;
      case 'CONTRACT_CALL':
        return <DocumentTextIcon className="h-5 w-5 text-purple-400" />;
      default:
        return <ArrowUpRightIcon className="h-5 w-5 text-gray-400" />;
    }
  };

  const getStatusIcon = (status: Transaction['status']) => {
    switch (status) {
      case 'CONFIRMED':
        return <CheckCircleIcon className="h-5 w-5 text-green-400" />;
      case 'PENDING':
        return <ClockIcon className="h-5 w-5 text-yellow-400 animate-pulse" />;
      case 'FAILED':
      case 'CANCELLED':
        return <XCircleIcon className="h-5 w-5 text-red-400" />;
      default:
        return <ClockIcon className="h-5 w-5 text-gray-400" />;
    }
  };

  if (txList.length === 0) {
    return (
      <div className="text-center py-12">
        <ClockIcon className="h-12 w-12 text-gray-600 mx-auto mb-4" />
        <h3 className="text-lg font-medium text-gray-400">No transactions yet</h3>
        <p className="text-gray-500 mt-1">Your transaction history will appear here</p>
      </div>
    );
  }

  return (
    <div className="space-y-3">
      {txList.map((tx) => (
        <div
          key={tx.txHash}
          className="bg-gray-700/50 rounded-lg p-4 hover:bg-gray-700 transition-colors cursor-pointer"
        >
          <div className="flex items-center justify-between">
            <div className="flex items-center space-x-4">
              <div className={`p-2 rounded-lg ${
                tx.type === 'SEND' ? 'bg-red-500/20' :
                tx.type === 'RECEIVE' ? 'bg-green-500/20' :
                tx.type === 'SWAP' ? 'bg-blue-500/20' :
                'bg-purple-500/20'
              }`}>
                {getTypeIcon(tx.type)}
              </div>
              
              <div>
                <div className="flex items-center space-x-2">
                  <span className="font-medium text-white">
                    {tx.type === 'SEND' ? 'Sent' : 
                     tx.type === 'RECEIVE' ? 'Received' :
                     tx.type === 'SWAP' ? 'Swapped' : 'Contract Call'}
                  </span>
                  {getStatusIcon(tx.status)}
                </div>
                <div className="text-sm text-gray-400">
                  {tx.type === 'SEND' ? 'To: ' : 'From: '}
                  {formatAddress(tx.type === 'SEND' ? tx.toAddress : tx.fromAddress)}
                </div>
              </div>
            </div>

            <div className="text-right">
              <div className={`font-medium ${
                tx.type === 'SEND' ? 'text-red-400' : 
                tx.type === 'RECEIVE' ? 'text-green-400' : 
                'text-white'
              }`}>
                {tx.type === 'SEND' ? '-' : '+'}{formatAmount(tx.amount)} ETH
              </div>
              <div className="text-sm text-gray-500">
                {formatTimestamp(tx.timestamp)}
              </div>
            </div>
          </div>

          {tx.status === 'CONFIRMED' && (
            <div className="mt-3 pt-3 border-t border-gray-600 flex items-center justify-between text-sm">
              <span className="text-gray-500">
                Block #{tx.blockNumber}
              </span>
              <a
                href={`https://sepolia.etherscan.io/tx/${tx.txHash}`}
                target="_blank"
                rel="noopener noreferrer"
                className="text-primary-400 hover:text-primary-300"
                onClick={(e) => e.stopPropagation()}
              >
                View on Etherscan →
              </a>
            </div>
          )}
        </div>
      ))}
    </div>
  );
}
