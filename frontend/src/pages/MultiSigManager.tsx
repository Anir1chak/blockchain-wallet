import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useWalletStore } from '../store/walletStore';
import { api } from '../services/api';
import {
  ArrowLeftIcon,
  UserPlusIcon,
  UserMinusIcon,
  CheckCircleIcon,
  ClockIcon,
  XCircleIcon,
  DocumentDuplicateIcon
} from '@heroicons/react/24/outline';

interface Signer {
  address: string;
  name?: string;
  hasSigned?: boolean;
}

interface PendingTransaction {
  id: string;
  toAddress: string;
  amount: string;
  requiredSignatures: number;
  currentSignatures: number;
  signers: Signer[];
  status: 'PENDING_SIGNATURES' | 'READY' | 'EXECUTED' | 'CANCELLED';
  createdAt: string;
}

export default function MultiSigManager() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { wallets, fetchWallets } = useWalletStore();
  
  const [signers, setSigners] = useState<Signer[]>([]);
  const [pendingTxs, setPendingTxs] = useState<PendingTransaction[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [newSignerAddress, setNewSignerAddress] = useState('');
  const [isAddingSigner, setIsAddingSigner] = useState(false);
  const [showAddSigner, setShowAddSigner] = useState(false);
  const [copied, setCopied] = useState('');

  const wallet = wallets.find((w) => w.id === id);

  useEffect(() => {
    if (!wallet) {
      fetchWallets();
    }
  }, [wallet, fetchWallets]);

  useEffect(() => {
    const loadMultiSigData = async () => {
      if (wallet && wallet.type === 'MULTI_SIG') {
        setIsLoading(true);
        try {
          setSigners([
            { address: wallet.address, name: 'You (Owner)', hasSigned: true },
            { address: '0x742d35Cc6634C0532925a3b844Bc9e7595f28abc', name: 'Co-signer 1' },
            { address: '0x8626f6940E2eb28930eFb4CeF49B2d1F2C9C1199', name: 'Co-signer 2' },
          ]);

          setPendingTxs([
            {
              id: '1',
              toAddress: '0x1234...5678',
              amount: '1000000000000000000',
              requiredSignatures: 2,
              currentSignatures: 1,
              signers: [
                { address: wallet.address, hasSigned: true },
                { address: '0x742d35Cc6634C0532925a3b844Bc9e7595f28abc', hasSigned: false },
              ],
              status: 'PENDING_SIGNATURES',
              createdAt: new Date().toISOString(),
            },
          ]);
        } catch (error) {
          console.error('Failed to load multi-sig data:', error);
        } finally {
          setIsLoading(false);
        }
      }
    };

    loadMultiSigData();
  }, [wallet]);

  const handleAddSigner = async () => {
    if (!newSignerAddress || !wallet) return;
    
    setIsAddingSigner(true);
    try {
      await api.addSigner(wallet.id, { signerAddress: newSignerAddress, signerName: 'New Signer' });
      setSigners([...signers, { address: newSignerAddress }]);
      setNewSignerAddress('');
      setShowAddSigner(false);
    } catch (error) {
      console.error('Failed to add signer:', error);
    } finally {
      setIsAddingSigner(false);
    }
  };

  const handleRemoveSigner = async (signerAddress: string) => {
    if (!wallet) return;
    
    try {
      await api.removeSigner(wallet.id, signerAddress);
      setSigners(signers.filter(s => s.address !== signerAddress));
    } catch (error) {
      console.error('Failed to remove signer:', error);
    }
  };

  const handleSignTransaction = async (txId: string) => {
    console.log('Signing transaction:', txId);
  };

  const handleCopyAddress = async (address: string) => {
    await navigator.clipboard.writeText(address);
    setCopied(address);
    setTimeout(() => setCopied(''), 2000);
  };

  const formatAddress = (address: string) => {
    return `${address.slice(0, 6)}...${address.slice(-4)}`;
  };

  const formatAmount = (amount: string) => {
    const wei = BigInt(amount);
    const eth = Number(wei) / 1e18;
    return eth.toFixed(6);
  };

  if (!wallet || wallet.type !== 'MULTI_SIG') {
    return (
      <div className="text-center py-12">
        <h2 className="text-xl font-bold text-white">Invalid Wallet</h2>
        <p className="text-gray-400 mt-2">This wallet is not a multi-signature wallet or doesn't exist</p>
        <button
          onClick={() => navigate(-1)}
          className="mt-4 px-4 py-2 bg-primary-600 text-white rounded-lg"
        >
          Go Back
        </button>
      </div>
    );
  }

  if (isLoading) {
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
          <h1 className="text-2xl font-bold text-white">Multi-Sig Manager</h1>
          <p className="text-gray-400 text-sm">{wallet.name}</p>
        </div>
      </div>

      {/* Overview Card */}
      <div className="bg-gradient-to-br from-purple-600 to-purple-800 rounded-2xl p-6 shadow-xl">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div>
            <p className="text-purple-200 text-sm">Signers</p>
            <p className="text-3xl font-bold text-white">{signers.length}</p>
          </div>
          <div>
            <p className="text-purple-200 text-sm">Required Signatures</p>
            <p className="text-3xl font-bold text-white">2 of {signers.length}</p>
          </div>
          <div>
            <p className="text-purple-200 text-sm">Pending Transactions</p>
            <p className="text-3xl font-bold text-white">{pendingTxs.length}</p>
          </div>
        </div>
      </div>

      {/* Signers Section */}
      <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-lg font-semibold text-white">Signers</h2>
          <button
            onClick={() => setShowAddSigner(true)}
            className="inline-flex items-center px-3 py-1.5 bg-primary-600 text-white text-sm rounded-lg hover:bg-primary-700 transition-colors"
          >
            <UserPlusIcon className="h-4 w-4 mr-1" />
            Add Signer
          </button>
        </div>

        <div className="space-y-3">
          {signers.map((signer, index) => (
            <div
              key={signer.address}
              className="flex items-center justify-between p-4 bg-gray-700/50 rounded-lg"
            >
              <div className="flex items-center space-x-4">
                <div className="w-10 h-10 bg-primary-500/20 rounded-full flex items-center justify-center">
                  <span className="text-primary-400 font-medium">{index + 1}</span>
                </div>
                <div>
                  <p className="text-white font-medium">
                    {signer.name || `Signer ${index + 1}`}
                  </p>
                  <div className="flex items-center space-x-2">
                    <code className="text-gray-400 text-sm">{formatAddress(signer.address)}</code>
                    <button
                      onClick={() => handleCopyAddress(signer.address)}
                      className="text-gray-500 hover:text-gray-300"
                    >
                      {copied === signer.address ? (
                        <CheckCircleIcon className="h-4 w-4 text-green-400" />
                      ) : (
                        <DocumentDuplicateIcon className="h-4 w-4" />
                      )}
                    </button>
                  </div>
                </div>
              </div>
              
              {index !== 0 && (
                <button
                  onClick={() => handleRemoveSigner(signer.address)}
                  className="p-2 text-red-400 hover:bg-red-500/20 rounded-lg transition-colors"
                >
                  <UserMinusIcon className="h-5 w-5" />
                </button>
              )}
            </div>
          ))}
        </div>

        {/* Add Signer Modal */}
        {showAddSigner && (
          <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
            <div className="bg-gray-800 rounded-xl p-6 max-w-md w-full mx-4 border border-gray-700">
              <h3 className="text-lg font-medium text-white mb-4">Add New Signer</h3>
              <div>
                <label className="block text-sm font-medium text-gray-300 mb-2">
                  Signer Address
                </label>
                <input
                  type="text"
                  value={newSignerAddress}
                  onChange={(e) => setNewSignerAddress(e.target.value)}
                  placeholder="0x..."
                  className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white font-mono text-sm focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                />
              </div>
              <div className="flex space-x-3 mt-6">
                <button
                  onClick={() => setShowAddSigner(false)}
                  className="flex-1 px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors"
                >
                  Cancel
                </button>
                <button
                  onClick={handleAddSigner}
                  disabled={!newSignerAddress || isAddingSigner}
                  className="flex-1 px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 disabled:bg-gray-600 transition-colors"
                >
                  {isAddingSigner ? 'Adding...' : 'Add Signer'}
                </button>
              </div>
            </div>
          </div>
        )}
      </div>

      {/* Pending Transactions Section */}
      <div className="bg-gray-800 rounded-xl p-6 border border-gray-700">
        <h2 className="text-lg font-semibold text-white mb-4">Pending Transactions</h2>

        {pendingTxs.length === 0 ? (
          <div className="text-center py-8">
            <ClockIcon className="h-12 w-12 text-gray-600 mx-auto mb-4" />
            <p className="text-gray-400">No pending transactions</p>
          </div>
        ) : (
          <div className="space-y-4">
            {pendingTxs.map((tx) => (
              <div
                key={tx.id}
                className="border border-gray-700 rounded-lg p-4"
              >
                <div className="flex items-start justify-between mb-4">
                  <div>
                    <div className="flex items-center space-x-2">
                      <span className="text-white font-medium">
                        Send {formatAmount(tx.amount)} ETH
                      </span>
                      {tx.status === 'PENDING_SIGNATURES' && (
                        <span className="px-2 py-1 bg-yellow-500/20 text-yellow-400 text-xs rounded">
                          Awaiting Signatures
                        </span>
                      )}
                      {tx.status === 'READY' && (
                        <span className="px-2 py-1 bg-green-500/20 text-green-400 text-xs rounded">
                          Ready to Execute
                        </span>
                      )}
                    </div>
                    <p className="text-gray-400 text-sm mt-1">
                      To: {formatAddress(tx.toAddress)}
                    </p>
                  </div>
                  <span className="text-gray-500 text-sm">
                    {new Date(tx.createdAt).toLocaleDateString()}
                  </span>
                </div>

                {/* Signature Progress */}
                <div className="mb-4">
                  <div className="flex items-center justify-between text-sm mb-2">
                    <span className="text-gray-400">Signatures</span>
                    <span className="text-white">
                      {tx.currentSignatures} / {tx.requiredSignatures}
                    </span>
                  </div>
                  <div className="w-full bg-gray-700 rounded-full h-2">
                    <div
                      className="bg-primary-500 h-2 rounded-full transition-all"
                      style={{ width: `${(tx.currentSignatures / tx.requiredSignatures) * 100}%` }}
                    ></div>
                  </div>
                </div>

                {/* Signers Status */}
                <div className="flex flex-wrap gap-2 mb-4">
                  {tx.signers.map((signer) => (
                    <div
                      key={signer.address}
                      className={`flex items-center space-x-1 px-2 py-1 rounded text-sm ${
                        signer.hasSigned
                          ? 'bg-green-500/20 text-green-400'
                          : 'bg-gray-700 text-gray-400'
                      }`}
                    >
                      {signer.hasSigned ? (
                        <CheckCircleIcon className="h-4 w-4" />
                      ) : (
                        <ClockIcon className="h-4 w-4" />
                      )}
                      <span>{formatAddress(signer.address)}</span>
                    </div>
                  ))}
                </div>

                {/* Actions */}
                <div className="flex space-x-3">
                  <button
                    onClick={() => handleSignTransaction(tx.id)}
                    className="flex-1 px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
                  >
                    Sign Transaction
                  </button>
                  {tx.status === 'READY' && (
                    <button className="flex-1 px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors">
                      Execute
                    </button>
                  )}
                  <button className="px-4 py-2 bg-red-500/20 text-red-400 rounded-lg hover:bg-red-500/30 transition-colors">
                    <XCircleIcon className="h-5 w-5" />
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
