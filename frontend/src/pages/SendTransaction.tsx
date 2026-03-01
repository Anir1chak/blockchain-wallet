import { useState, useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { useWalletStore } from '../store/walletStore';
import { api } from '../services/api';
import {
  ArrowLeftIcon,
  PaperAirplaneIcon,
  ExclamationTriangleIcon,
  CheckCircleIcon,
  InformationCircleIcon
} from '@heroicons/react/24/outline';

interface GasEstimate {
  gasLimit: string;
  gasPrice: string;
  totalCost: string;
  gasPriceGwei: string;
}

export default function SendTransaction() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const { wallets } = useWalletStore();
  
  const [selectedWalletId, setSelectedWalletId] = useState(searchParams.get('wallet') || '');
  const [toAddress, setToAddress] = useState('');
  const [amount, setAmount] = useState('');
  const [gasSpeed, setGasSpeed] = useState<'SLOW' | 'MEDIUM' | 'FAST'>('MEDIUM');
  const [gasEstimate, setGasEstimate] = useState<GasEstimate | null>(null);
  const [isEstimating, setIsEstimating] = useState(false);
  const [isSending, setIsSending] = useState(false);
  const [txHash, setTxHash] = useState('');
  const [error, setError] = useState('');
  const [validAddress, setValidAddress] = useState<boolean | null>(null);

  const selectedWallet = wallets.find((w) => w.id === selectedWalletId);
  const network = selectedWallet?.network || 'ETHEREUM_SEPOLIA';

  // Validate address on change
  useEffect(() => {
    const validateAddress = async () => {
      if (toAddress.length === 42 && toAddress.startsWith('0x')) {
        try {
          const response = await api.validateAddress(toAddress, network);
          setValidAddress(response.valid);
        } catch {
          setValidAddress(false);
        }
      } else {
        setValidAddress(null);
      }
    };

    const debounce = setTimeout(validateAddress, 500);
    return () => clearTimeout(debounce);
  }, [toAddress, network]);

  // Estimate gas when form is filled
  useEffect(() => {
    const estimateGas = async () => {
      if (selectedWallet && toAddress && amount && validAddress) {
        setIsEstimating(true);
        try {
          const weiAmount = (parseFloat(amount) * 1e18).toString();
          const response = await api.estimateGas(
            selectedWallet.address,
            toAddress,
            weiAmount,
            network,
            gasSpeed
          );
          setGasEstimate(response);
        } catch {
          console.error('Failed to estimate gas');
        } finally {
          setIsEstimating(false);
        }
      }
    };

    const debounce = setTimeout(estimateGas, 1000);
    return () => clearTimeout(debounce);
  }, [selectedWallet, toAddress, amount, validAddress, network, gasSpeed]);

  const handleSend = async () => {
    if (!selectedWallet || !toAddress || !amount) return;

    setIsSending(true);
    setError('');

    try {
      const weiAmount = (parseFloat(amount) * 1e18).toString();
      const response = await api.sendTransaction({
        walletId: selectedWallet.id,
        toAddress,
        amount: weiAmount,
        gasSpeed
      });
      setTxHash(response.data?.txHash || '');
    } catch (err: any) {
      setError(err.response?.data?.message || 'Transaction failed');
    } finally {
      setIsSending(false);
    }
  };

  const formatBalance = (balance: string) => {
    const wei = BigInt(balance);
    const eth = Number(wei) / 1e18;
    return eth.toFixed(6);
  };

  const formatGwei = (wei: string) => {
    return (BigInt(wei) / BigInt(1e9)).toString();
  };

  const formatEth = (wei: string) => {
    return (Number(wei) / 1e18).toFixed(8);
  };

  // Success state
  if (txHash) {
    return (
      <div className="max-w-lg mx-auto">
        <div className="bg-gray-800 rounded-xl p-8 border border-gray-700 text-center">
          <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mx-auto mb-4">
            <CheckCircleIcon className="h-8 w-8 text-green-400" />
          </div>
          <h2 className="text-xl font-bold text-white mb-2">Transaction Sent!</h2>
          <p className="text-gray-400 mb-6">Your transaction has been submitted to the network</p>
          
          <div className="bg-gray-700/50 rounded-lg p-4 mb-6">
            <p className="text-gray-400 text-sm mb-1">Transaction Hash</p>
            <code className="text-primary-400 text-sm break-all">{txHash}</code>
          </div>

          <div className="flex space-x-3">
            <a
              href={`https://sepolia.etherscan.io/tx/${txHash}`}
              target="_blank"
              rel="noopener noreferrer"
              className="flex-1 px-4 py-2 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
            >
              View on Etherscan
            </a>
            <button
              onClick={() => navigate(`/wallet/${selectedWalletId}`)}
              className="flex-1 px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors"
            >
              Back to Wallet
            </button>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-lg mx-auto space-y-6">
      {/* Header */}
      <div className="flex items-center space-x-4">
        <button
          onClick={() => navigate(-1)}
          className="p-2 rounded-lg hover:bg-gray-700 transition-colors"
        >
          <ArrowLeftIcon className="h-5 w-5 text-gray-400" />
        </button>
        <div>
          <h1 className="text-2xl font-bold text-white">Send Transaction</h1>
          <p className="text-gray-400 text-sm">Transfer ETH to another address</p>
        </div>
      </div>

      {/* Form */}
      <div className="bg-gray-800 rounded-xl p-6 border border-gray-700 space-y-6">
        {/* From Wallet */}
        <div>
          <label className="block text-sm font-medium text-gray-300 mb-2">
            From Wallet
          </label>
          <select
            value={selectedWalletId}
            onChange={(e) => setSelectedWalletId(e.target.value)}
            className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
          >
            <option value="">Select a wallet</option>
            {wallets.map((wallet) => (
              <option key={wallet.id} value={wallet.id}>
                {wallet.name} ({formatBalance(wallet.balance || '0')} ETH)
              </option>
            ))}
          </select>
        </div>

        {/* To Address */}
        <div>
          <label className="block text-sm font-medium text-gray-300 mb-2">
            To Address
          </label>
          <div className="relative">
            <input
              type="text"
              value={toAddress}
              onChange={(e) => setToAddress(e.target.value)}
              placeholder="0x..."
              className={`w-full bg-gray-700 border rounded-lg px-4 py-3 text-white font-mono text-sm focus:ring-2 focus:ring-primary-500 focus:border-transparent ${
                validAddress === true
                  ? 'border-green-500'
                  : validAddress === false
                  ? 'border-red-500'
                  : 'border-gray-600'
              }`}
            />
            {validAddress === true && (
              <CheckCircleIcon className="absolute right-3 top-1/2 -translate-y-1/2 h-5 w-5 text-green-400" />
            )}
            {validAddress === false && (
              <ExclamationTriangleIcon className="absolute right-3 top-1/2 -translate-y-1/2 h-5 w-5 text-red-400" />
            )}
          </div>
          {validAddress === false && (
            <p className="text-red-400 text-sm mt-1">Invalid Ethereum address</p>
          )}
        </div>

        {/* Amount */}
        <div>
          <label className="block text-sm font-medium text-gray-300 mb-2">
            Amount (ETH)
          </label>
          <div className="relative">
            <input
              type="number"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              placeholder="0.0"
              step="0.0001"
              min="0"
              className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            />
            {selectedWallet && (
              <button
                onClick={() => setAmount(formatBalance(selectedWallet.balance || '0'))}
                className="absolute right-3 top-1/2 -translate-y-1/2 text-primary-400 text-sm hover:text-primary-300"
              >
                MAX
              </button>
            )}
          </div>
          {selectedWallet && (
            <p className="text-gray-500 text-sm mt-1">
              Available: {formatBalance(selectedWallet.balance || '0')} ETH
            </p>
          )}
        </div>

        {/* Gas Speed */}
        <div>
          <label className="block text-sm font-medium text-gray-300 mb-2">
            Transaction Speed
          </label>
          <div className="grid grid-cols-3 gap-3">
            {(['SLOW', 'MEDIUM', 'FAST'] as const).map((speed) => (
              <button
                key={speed}
                onClick={() => setGasSpeed(speed)}
                className={`p-3 rounded-lg border transition-colors ${
                  gasSpeed === speed
                    ? 'border-primary-500 bg-primary-500/20 text-primary-400'
                    : 'border-gray-600 text-gray-400 hover:border-gray-500'
                }`}
              >
                <span className="block font-medium">
                  {speed === 'SLOW' ? '🐢' : speed === 'MEDIUM' ? '🚗' : '🚀'}
                </span>
                <span className="block text-xs mt-1">{speed}</span>
              </button>
            ))}
          </div>
        </div>

        {/* Gas Estimate */}
        {gasEstimate && (
          <div className="bg-gray-700/50 rounded-lg p-4 space-y-2">
            <div className="flex items-center text-gray-300 mb-2">
              <InformationCircleIcon className="h-5 w-5 mr-2" />
              <span className="font-medium">Transaction Details</span>
            </div>
            <div className="flex justify-between text-sm">
              <span className="text-gray-400">Gas Limit</span>
              <span className="text-white">{gasEstimate.gasLimit}</span>
            </div>
            <div className="flex justify-between text-sm">
              <span className="text-gray-400">Gas Price</span>
              <span className="text-white">{formatGwei(gasEstimate.gasPrice)} Gwei</span>
            </div>
            <div className="flex justify-between text-sm pt-2 border-t border-gray-600">
              <span className="text-gray-400">Estimated Fee</span>
              <span className="text-white">{formatEth(gasEstimate.totalCost)} ETH</span>
            </div>
          </div>
        )}

        {isEstimating && (
          <div className="flex items-center justify-center text-gray-400">
            <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-primary-400 mr-2"></div>
            Estimating gas...
          </div>
        )}

        {/* Error */}
        {error && (
          <div className="bg-red-500/20 border border-red-500 rounded-lg p-4 flex items-center">
            <ExclamationTriangleIcon className="h-5 w-5 text-red-400 mr-2" />
            <span className="text-red-400">{error}</span>
          </div>
        )}

        {/* Submit Button */}
        <button
          onClick={handleSend}
          disabled={!selectedWallet || !toAddress || !amount || !validAddress || isSending}
          className="w-full flex items-center justify-center space-x-2 px-4 py-3 bg-primary-600 text-white rounded-lg hover:bg-primary-700 disabled:bg-gray-600 disabled:cursor-not-allowed transition-colors"
        >
          {isSending ? (
            <>
              <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white"></div>
              <span>Sending...</span>
            </>
          ) : (
            <>
              <PaperAirplaneIcon className="h-5 w-5" />
              <span>Send Transaction</span>
            </>
          )}
        </button>
      </div>
    </div>
  );
}
