export interface Wallet {
  id: string;
  walletId: string;
  address: string;
  name: string;
  type: 'SINGLE_SIG' | 'MULTI_SIG';
  network: string;
  balance?: string;
  mnemonic?: string;
  createdAt: number;
  updatedAt: number;
}

export interface MultiSigWallet extends Wallet {
  threshold: number;
  totalSigners: number;
  signers: Signer[];
}

export interface Signer {
  address: string;
  name: string;
  isOwner: boolean;
}

export interface Balance {
  address: string;
  balanceWei: string;
  balanceEth: string;
  balanceUsd: string;
  network: string;
  timestamp: number;
}

export interface Transaction {
  txHash: string;
  fromAddress: string;
  toAddress: string;
  amount: string;
  gasPrice: string;
  gasUsed: string;
  status: 'PENDING' | 'CONFIRMED' | 'FAILED' | 'CANCELLED';
  type: 'SEND' | 'RECEIVE' | 'SWAP' | 'CONTRACT_CALL';
  blockNumber: number;
  timestamp: number;
  network: string;
}

export interface GasEstimate {
  gasLimit: string;
  slowGasPrice: string;
  standardGasPrice: string;
  fastGasPrice: string;
  instantGasPrice: string;
  slowTotalCost: string;
  standardTotalCost: string;
  fastTotalCost: string;
  instantTotalCost: string;
}

export interface MultiSigTransaction {
  multiSigTxId: string;
  walletId: string;
  toAddress: string;
  amount: string;
  description: string;
  signaturesCollected: number;
  signaturesRequired: number;
  signatures: SignatureInfo[];
  status: string;
  createdAt: number;
  expiresAt: number;
}

export interface SignatureInfo {
  signerAddress: string;
  signature: string;
  signedAt: number;
}

export interface NetworkStatus {
  network: string;
  chainId: string;
  latestBlock: number;
  peerCount: string;
  isSyncing: boolean;
  gasPrice: string;
  timestamp: number;
}

export interface GasPrices {
  slow: string;
  standard: string;
  fast: string;
  instant: string;
  baseFee: string;
  network: string;
  timestamp: number;
}
