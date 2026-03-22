import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useWalletStore } from '../store/walletStore';
import {
  ArrowLeftIcon,
  WalletIcon,
  UserGroupIcon,
  KeyIcon,
  CheckCircleIcon,
  DocumentDuplicateIcon,
  ExclamationTriangleIcon
} from '@heroicons/react/24/outline';

type WalletType = 'STANDARD' | 'HD' | 'MULTI_SIG' | 'IMPORT';

export default function CreateWallet() {
  const navigate = useNavigate();
  const { createWallet, importWallet } = useWalletStore();
  
  const [step, setStep] = useState(1);
  const [walletType, setWalletType] = useState<WalletType>('STANDARD');
  const [name, setName] = useState('');
  const [network] = useState('ETHEREUM_SEPOLIA');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [mnemonic, setMnemonic] = useState('');
  const [privateKey, setPrivateKey] = useState('');
  const [generatedMnemonic, setGeneratedMnemonic] = useState('');
  const [mnemonicConfirmed, setMnemonicConfirmed] = useState(false);
  
  // Multi-sig options
  const [requiredSignatures, setRequiredSignatures] = useState(2);
  const [signerAddresses, setSignerAddresses] = useState<string[]>(['']);
  
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const [createdWallet, setCreatedWallet] = useState<any>(null);
  const [copied, setCopied] = useState(false);

  const walletTypes = [
    {
      type: 'STANDARD' as WalletType,
      title: 'Standard Wallet',
      description: 'Simple single-key wallet for basic transactions',
      icon: WalletIcon
    },
    {
      type: 'HD' as WalletType,
      title: 'HD Wallet',
      description: 'Hierarchical Deterministic wallet with recovery phrase',
      icon: KeyIcon
    },
    {
      type: 'MULTI_SIG' as WalletType,
      title: 'Multi-Signature',
      description: 'Requires multiple signatures for transactions',
      icon: UserGroupIcon
    },
    {
      type: 'IMPORT' as WalletType,
      title: 'Import Wallet',
      description: 'Import existing wallet using private key or mnemonic',
      icon: DocumentDuplicateIcon
    }
  ];

  const handleAddSigner = () => {
    setSignerAddresses([...signerAddresses, '']);
  };

  const handleRemoveSigner = (index: number) => {
    setSignerAddresses(signerAddresses.filter((_, i) => i !== index));
  };

  const handleSignerChange = (index: number, value: string) => {
    const updated = [...signerAddresses];
    updated[index] = value;
    setSignerAddresses(updated);
  };

  const handleCreate = async () => {
    setIsLoading(true);
    setError('');

    try {
      if (password !== confirmPassword) {
        throw new Error('Passwords do not match');
      }

      if (walletType === 'IMPORT') {
        const wallet = await importWallet(name, network, password, mnemonic, privateKey);
        setCreatedWallet(wallet);
      } else {
        const wallet = await createWallet(name, network, password);
        setCreatedWallet(wallet);
        if (wallet.mnemonic) {
          setGeneratedMnemonic(wallet.mnemonic);
        }
      }
      setStep(3);
    } catch (err: any) {
      setError(err.message || 'Failed to create wallet');
    } finally {
      setIsLoading(false);
    }
  };

  const handleCopyMnemonic = async () => {
    await navigator.clipboard.writeText(generatedMnemonic);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  // Step 1: Select wallet type
  if (step === 1) {
    return (
      <div className="max-w-2xl mx-auto space-y-6">
        <div className="flex items-center space-x-4">
          <button
            onClick={() => navigate(-1)}
            className="p-2 rounded-lg hover:bg-gray-700 transition-colors"
          >
            <ArrowLeftIcon className="h-5 w-5 text-gray-400" />
          </button>
          <div>
            <h1 className="text-2xl font-bold text-white">Create Wallet</h1>
            <p className="text-gray-400 text-sm">Choose your wallet type</p>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {walletTypes.map((wt) => (
            <button
              key={wt.type}
              onClick={() => {
                setWalletType(wt.type);
                setStep(2);
              }}
              className={`p-6 rounded-xl border text-left transition-all hover:scale-105 ${
                walletType === wt.type
                  ? 'border-primary-500 bg-primary-500/20'
                  : 'border-gray-700 bg-gray-800 hover:border-gray-600'
              }`}
            >
              <wt.icon className={`h-8 w-8 mb-4 ${
                walletType === wt.type ? 'text-primary-400' : 'text-gray-400'
              }`} />
              <h3 className="text-lg font-medium text-white">{wt.title}</h3>
              <p className="text-gray-400 text-sm mt-1">{wt.description}</p>
            </button>
          ))}
        </div>
      </div>
    );
  }

  // Step 2: Enter details
  if (step === 2) {
    return (
      <div className="max-w-lg mx-auto space-y-6">
        <div className="flex items-center space-x-4">
          <button
            onClick={() => setStep(1)}
            className="p-2 rounded-lg hover:bg-gray-700 transition-colors"
          >
            <ArrowLeftIcon className="h-5 w-5 text-gray-400" />
          </button>
          <div>
            <h1 className="text-2xl font-bold text-white">
              {walletType === 'IMPORT' ? 'Import Wallet' : 'Create Wallet'}
            </h1>
            <p className="text-gray-400 text-sm">
              {walletTypes.find(w => w.type === walletType)?.title}
            </p>
          </div>
        </div>

        <div className="bg-gray-800 rounded-xl p-6 border border-gray-700 space-y-6">
          {/* Wallet Name */}
          <div>
            <label className="block text-sm font-medium text-gray-300 mb-2">
              Wallet Name
            </label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="My Wallet"
              className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            />
          </div>

          {/* Import Fields */}
          {walletType === 'IMPORT' && (
            <>
              <div>
                <label className="block text-sm font-medium text-gray-300 mb-2">
                  Private Key or Mnemonic
                </label>
                <textarea
                  value={privateKey || mnemonic}
                  onChange={(e) => {
                    const value = e.target.value;
                    if (value.startsWith('0x')) {
                      setPrivateKey(value);
                      setMnemonic('');
                    } else {
                      setMnemonic(value);
                      setPrivateKey('');
                    }
                  }}
                  placeholder="Enter your private key (0x...) or 12/24 word mnemonic"
                  rows={4}
                  className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white font-mono text-sm focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                />
              </div>
            </>
          )}

          {/* Multi-sig Options */}
          {walletType === 'MULTI_SIG' && (
            <>
              <div>
                <label className="block text-sm font-medium text-gray-300 mb-2">
                  Required Signatures
                </label>
                <select
                  value={requiredSignatures}
                  onChange={(e) => setRequiredSignatures(Number(e.target.value))}
                  className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                >
                  {[2, 3, 4, 5].map((n) => (
                    <option key={n} value={n}>{n} of {signerAddresses.length + 1}</option>
                  ))}
                </select>
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-300 mb-2">
                  Co-Signer Addresses
                </label>
                <div className="space-y-2">
                  {signerAddresses.map((address, index) => (
                    <div key={index} className="flex space-x-2">
                      <input
                        type="text"
                        value={address}
                        onChange={(e) => handleSignerChange(index, e.target.value)}
                        placeholder="0x..."
                        className="flex-1 bg-gray-700 border border-gray-600 rounded-lg px-4 py-2 text-white font-mono text-sm focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                      />
                      {signerAddresses.length > 1 && (
                        <button
                          onClick={() => handleRemoveSigner(index)}
                          className="px-3 py-2 bg-red-500/20 text-red-400 rounded-lg hover:bg-red-500/30"
                        >
                          ✕
                        </button>
                      )}
                    </div>
                  ))}
                  <button
                    onClick={handleAddSigner}
                    className="w-full py-2 border border-dashed border-gray-600 text-gray-400 rounded-lg hover:border-gray-500 hover:text-gray-300"
                  >
                    + Add Signer
                  </button>
                </div>
              </div>
            </>
          )}

          {/* Password */}
          <div>
            <label className="block text-sm font-medium text-gray-300 mb-2">
              Password
            </label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter a strong password"
              className="w-full bg-gray-700 border border-gray-600 rounded-lg px-4 py-3 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-300 mb-2">
              Confirm Password
            </label>
            <input
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              placeholder="Confirm your password"
              className={`w-full bg-gray-700 border rounded-lg px-4 py-3 text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent ${
                confirmPassword && password !== confirmPassword
                  ? 'border-red-500'
                  : 'border-gray-600'
              }`}
            />
            {confirmPassword && password !== confirmPassword && (
              <p className="text-red-400 text-sm mt-1">Passwords do not match</p>
            )}
          </div>

          {/* Error */}
          {error && (
            <div className="bg-red-500/20 border border-red-500 rounded-lg p-4 flex items-center">
              <ExclamationTriangleIcon className="h-5 w-5 text-red-400 mr-2" />
              <span className="text-red-400">{error}</span>
            </div>
          )}

          {/* Submit */}
          <button
            onClick={handleCreate}
            disabled={!name || !password || !confirmPassword || isLoading}
            className="w-full flex items-center justify-center space-x-2 px-4 py-3 bg-primary-600 text-white rounded-lg hover:bg-primary-700 disabled:bg-gray-600 disabled:cursor-not-allowed transition-colors"
          >
            {isLoading ? (
              <>
                <div className="animate-spin rounded-full h-5 w-5 border-b-2 border-white"></div>
                <span>Creating...</span>
              </>
            ) : (
              <span>{walletType === 'IMPORT' ? 'Import Wallet' : 'Create Wallet'}</span>
            )}
          </button>
        </div>
      </div>
    );
  }

  // Step 3: Success
  return (
    <div className="max-w-lg mx-auto space-y-6">
      <div className="bg-gray-800 rounded-xl p-8 border border-gray-700 text-center">
        <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mx-auto mb-4">
          <CheckCircleIcon className="h-8 w-8 text-green-400" />
        </div>
        <h2 className="text-xl font-bold text-white mb-2">Wallet Created!</h2>
        <p className="text-gray-400 mb-6">Your wallet is ready to use</p>

        {/* Mnemonic Backup */}
        {generatedMnemonic && !mnemonicConfirmed && (
          <div className="bg-yellow-500/20 border border-yellow-500 rounded-lg p-4 mb-6 text-left">
            <div className="flex items-center text-yellow-400 mb-3">
              <ExclamationTriangleIcon className="h-5 w-5 mr-2" />
              <span className="font-medium">Backup Your Recovery Phrase</span>
            </div>
            <p className="text-yellow-200 text-sm mb-4">
              Write down these 12 words in order and store them safely. This is the only way to recover your wallet.
            </p>
            <div className="bg-gray-900 rounded-lg p-4 mb-4">
              <div className="grid grid-cols-3 gap-2">
                {generatedMnemonic.split(' ').map((word, index) => (
                  <div key={index} className="flex items-center space-x-2 text-sm">
                    <span className="text-gray-500 w-5">{index + 1}.</span>
                    <span className="text-white font-mono">{word}</span>
                  </div>
                ))}
              </div>
            </div>
            <div className="flex space-x-3">
              <button
                onClick={handleCopyMnemonic}
                className="flex-1 px-4 py-2 bg-gray-700 text-white rounded-lg hover:bg-gray-600 transition-colors"
              >
                {copied ? '✓ Copied' : 'Copy'}
              </button>
              <button
                onClick={() => setMnemonicConfirmed(true)}
                className="flex-1 px-4 py-2 bg-yellow-500 text-black rounded-lg hover:bg-yellow-400 transition-colors font-medium"
              >
                I've Saved It
              </button>
            </div>
          </div>
        )}

        {/* Wallet Info */}
        {createdWallet && (
          <div className="bg-gray-700/50 rounded-lg p-4 mb-6 text-left">
            <div className="mb-3">
              <p className="text-gray-400 text-sm">Wallet Name</p>
              <p className="text-white font-medium">{createdWallet.name}</p>
            </div>
            <div>
              <p className="text-gray-400 text-sm">Address</p>
              <code className="text-primary-400 text-sm break-all">{createdWallet.address}</code>
            </div>
          </div>
        )}

        {(mnemonicConfirmed || !generatedMnemonic) && (
          <button
            onClick={() => navigate(`/wallet/${createdWallet?.id}`)}
            className="w-full px-4 py-3 bg-primary-600 text-white rounded-lg hover:bg-primary-700 transition-colors"
          >
            Go to Wallet
          </button>
        )}
      </div>
    </div>
  );
}
