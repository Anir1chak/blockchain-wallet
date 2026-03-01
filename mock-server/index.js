const express = require('express');
const cors = require('cors');
const { v4: uuidv4 } = require('uuid');

const app = express();
app.use(cors());
app.use(express.json());

// In-memory storage
const wallets = [];
const transactions = [];
const users = [
  // Demo user
  {
    id: 'demo-user-123',
    email: 'demo@example.com',
    password: 'password123',
    name: 'Demo User',
    createdAt: Date.now()
  }
];

// Generate random Ethereum address
function generateAddress() {
  return '0x' + Array.from({length: 40}, () => 
    Math.floor(Math.random() * 16).toString(16)
  ).join('');
}

function generateToken(userId) {
  return `mock-jwt-token-${userId}-${Date.now()}`;
}


app.post('/api/auth/signup', (req, res) => {
  const { name, email, password } = req.body;
  
  const existingUser = users.find(u => u.email === email);
  if (existingUser) {
    return res.status(400).json({ message: 'Email already registered' });
  }

  if (!name || !email || !password) {
    return res.status(400).json({ message: 'All fields are required' });
  }

  if (password.length < 8) {
    return res.status(400).json({ message: 'Password must be at least 8 characters' });
  }

  const user = {
    id: uuidv4(),
    email,
    password,
    name,
    createdAt: Date.now()
  };

  users.push(user);
  console.log(`User registered: ${email}`);

  const token = generateToken(user.id);

  res.status(201).json({
    user: {
      id: user.id,
      email: user.email,
      name: user.name,
      createdAt: user.createdAt
    },
    token
  });
});

// Login
app.post('/api/auth/login', (req, res) => {
  const { email, password } = req.body;

  if (!email || !password) {
    return res.status(400).json({ message: 'Email and password are required' });
  }

  const user = users.find(u => u.email === email);
  if (!user) {
    return res.status(401).json({ message: 'Invalid email or password' });
  }

  if (user.password !== password) {
    return res.status(401).json({ message: 'Invalid email or password' });
  }

  console.log(`User logged in: ${email}`);

  const token = generateToken(user.id);

  res.json({
    user: {
      id: user.id,
      email: user.email,
      name: user.name,
      createdAt: user.createdAt
    },
    token
  });
});

app.get('/api/auth/me', (req, res) => {
  const authHeader = req.headers.authorization;
  if (!authHeader || !authHeader.startsWith('Bearer ')) {
    return res.status(401).json({ message: 'Unauthorized' });
  }

  const token = authHeader.split(' ')[1];
  const match = token.match(/^mock-jwt-token-(.+)-\d+$/);
  const userId = match ? match[1] : null;

  const user = users.find(u => u.id === userId);
  if (!user) {
    return res.status(401).json({ message: 'User not found' });
  }

  res.json({
    user: {
      id: user.id,
      email: user.email,
      name: user.name,
      createdAt: user.createdAt
    }
  });
});

app.post('/api/wallets', (req, res) => {
  const { userId, name, network, password } = req.body;
  const wallet = {
    id: uuidv4(),
    walletId: uuidv4(),
    userId,
    name,
    network: network || 'ETHEREUM_MAINNET',
    address: generateAddress(),
    balance: '0',
    type: 'SINGLE_SIG',
    mnemonic: 'word1 word2 word3 word4 word5 word6 word7 word8 word9 word10 word11 word12',
    createdAt: Date.now(),
    updatedAt: Date.now()
  };
  wallets.push(wallet);
  console.log(`Created wallet: ${wallet.address} for user: ${userId}`);
  res.status(201).json(wallet);
});

app.get('/api/wallets/:walletId', (req, res) => {
  const wallet = wallets.find(w => w.id === req.params.walletId || w.walletId === req.params.walletId);
  if (!wallet) {
    return res.status(404).json({ error: 'Wallet not found' });
  }
  res.json(wallet);
});

app.get('/api/wallets/user/:userId', (req, res) => {
  const userWallets = wallets.filter(w => w.userId === req.params.userId);
  console.log(`Getting wallets for user: ${req.params.userId}, found: ${userWallets.length}`);
  res.json(userWallets);
});

app.post('/api/wallets/import', (req, res) => {
  const { userId, name, network, password, privateKey, mnemonic } = req.body;
  const wallet = {
    id: uuidv4(),
    walletId: uuidv4(),
    userId,
    name,
    network: network || 'ETHEREUM_MAINNET',
    address: generateAddress(),
    balance: '0',
    type: 'SINGLE_SIG',
    createdAt: Date.now(),
    updatedAt: Date.now()
  };
  wallets.push(wallet);
  console.log(`Imported wallet: ${wallet.address}`);
  res.status(201).json(wallet);
});

app.post('/api/wallets/multisig', (req, res) => {
  const { userId, name, network, threshold, signerAddresses } = req.body;
  const wallet = {
    id: uuidv4(),
    walletId: uuidv4(),
    userId,
    name,
    network: network || 'ETHEREUM_MAINNET',
    address: generateAddress(),
    balance: '0',
    type: 'MULTI_SIG',
    threshold,
    totalSigners: signerAddresses?.length || 0,
    signers: (signerAddresses || []).map((addr, i) => ({
      address: addr,
      name: `Signer ${i + 1}`,
      isOwner: i === 0
    })),
    createdAt: Date.now(),
    updatedAt: Date.now()
  };
  wallets.push(wallet);
  console.log(`Created multi-sig wallet: ${wallet.address}`);
  res.status(201).json(wallet);
});

app.get('/api/wallets/multisig/:walletId', (req, res) => {
  const wallet = wallets.find(w => 
    (w.id === req.params.walletId || w.walletId === req.params.walletId) && 
    w.type === 'MULTI_SIG'
  );
  if (!wallet) {
    return res.status(404).json({ error: 'Multi-sig wallet not found' });
  }
  res.json(wallet);
});

app.post('/api/wallets/multisig/:walletId/signers', (req, res) => {
  const wallet = wallets.find(w => w.id === req.params.walletId || w.walletId === req.params.walletId);
  if (!wallet || wallet.type !== 'MULTI_SIG') {
    return res.status(404).json({ error: 'Multi-sig wallet not found' });
  }
  const { signerAddress, signerName } = req.body;
  wallet.signers = wallet.signers || [];
  wallet.signers.push({ address: signerAddress, name: signerName, isOwner: false });
  wallet.totalSigners = wallet.signers.length;
  res.json(wallet);
});

app.delete('/api/wallets/multisig/:walletId/signers/:signerAddress', (req, res) => {
  const wallet = wallets.find(w => w.id === req.params.walletId || w.walletId === req.params.walletId);
  if (!wallet || wallet.type !== 'MULTI_SIG') {
    return res.status(404).json({ error: 'Multi-sig wallet not found' });
  }
  wallet.signers = (wallet.signers || []).filter(s => s.address !== req.params.signerAddress);
  wallet.totalSigners = wallet.signers.length;
  res.json(wallet);
});

app.post('/api/transactions', (req, res) => {
  const { walletId, toAddress, amount, password, gasSpeed, data } = req.body;
  const wallet = wallets.find(w => w.id === walletId || w.walletId === walletId);
  
  const tx = {
    txHash: '0x' + uuidv4().replace(/-/g, '') + uuidv4().replace(/-/g, '').substring(0, 32),
    fromAddress: wallet?.address || generateAddress(),
    toAddress,
    amount,
    network: wallet?.network || 'ETHEREUM_MAINNET',
    status: 'CONFIRMED',
    type: 'SEND',
    gasUsed: '21000',
    gasPrice: '20000000000',
    fee: '420000000000000',
    blockNumber: Math.floor(Math.random() * 1000000) + 18000000,
    confirmations: Math.floor(Math.random() * 10) + 1,
    timestamp: Date.now(),
    createdAt: Date.now()
  };
  transactions.push({ ...tx, walletId });
  console.log(`Transaction sent: ${tx.txHash}`);
  res.status(201).json(tx);
});

app.get('/api/transactions/:txHash', (req, res) => {
  const tx = transactions.find(t => t.txHash === req.params.txHash);
  if (!tx) {
    return res.json({
      txHash: req.params.txHash,
      fromAddress: generateAddress(),
      toAddress: generateAddress(),
      amount: '1000000000000000000',
      network: req.query.network || 'ETHEREUM_MAINNET',
      status: 'CONFIRMED',
      type: 'SEND',
      gasUsed: '21000',
      gasPrice: '20000000000',
      fee: '420000000000000',
      blockNumber: 18500000,
      confirmations: 100,
      timestamp: Date.now() - 3600000
    });
  }
  res.json(tx);
});

app.get('/api/transactions/wallet/:walletId', (req, res) => {
  const { page = 0, pageSize = 10, type } = req.query;
  let walletTxs = transactions.filter(t => t.walletId === req.params.walletId);
  if (type) {
    walletTxs = walletTxs.filter(t => t.type === type);
  }
  const start = parseInt(page) * parseInt(pageSize);
  const paginatedTxs = walletTxs.slice(start, start + parseInt(pageSize));
  res.json({
    transactions: paginatedTxs,
    totalCount: walletTxs.length,
    page: parseInt(page),
    pageSize: parseInt(pageSize)
  });
});

app.post('/api/transactions/estimate-gas', (req, res) => {
  res.json({
    gasEstimate: '21000',
    gasPrices: {
      slow: '15000000000',
      standard: '20000000000',
      fast: '30000000000'
    }
  });
});

app.post('/api/transactions/multisig', (req, res) => {
  const { walletId, initiatorAddress, toAddress, amount, description } = req.body;
  const tx = {
    multiSigTxId: uuidv4(),
    walletId,
    initiatorAddress,
    toAddress,
    amount,
    description,
    status: 'PENDING',
    signatures: [],
    threshold: 2,
    currentSignatures: 0,
    createdAt: Date.now()
  };
  transactions.push({ ...tx, type: 'MULTI_SIG' });
  res.status(201).json(tx);
});

app.post('/api/transactions/multisig/:multiSigTxId/sign', (req, res) => {
  const tx = transactions.find(t => t.multiSigTxId === req.params.multiSigTxId);
  if (!tx) {
    return res.status(404).json({ error: 'Multi-sig transaction not found' });
  }
  const { signerAddress } = req.body;
  tx.signatures = tx.signatures || [];
  tx.signatures.push({ signerAddress, timestamp: Date.now() });
  tx.currentSignatures = tx.signatures.length;
  res.json(tx);
});

app.get('/api/transactions/multisig/pending/:walletId', (req, res) => {
  const pendingTxs = transactions.filter(t => 
    t.walletId === req.params.walletId && 
    t.type === 'MULTI_SIG' && 
    t.status === 'PENDING'
  );
  res.json(pendingTxs);
});

app.post('/api/transactions/multisig/:multiSigTxId/execute', (req, res) => {
  const tx = transactions.find(t => t.multiSigTxId === req.params.multiSigTxId);
  if (!tx) {
    return res.status(404).json({ error: 'Multi-sig transaction not found' });
  }
  tx.status = 'CONFIRMED';
  tx.txHash = '0x' + uuidv4().replace(/-/g, '') + uuidv4().replace(/-/g, '').substring(0, 32);
  tx.executedAt = Date.now();
  res.json(tx);
});

app.get('/api/blockchain/balance/:address', (req, res) => {
  const wallet = wallets.find(w => w.address === req.params.address);
  const balance = wallet?.balance || (Math.random() * 10).toFixed(6) + '000000000000000000';
  res.json({
    address: req.params.address,
    balance,
    network: req.query.network || 'ETHEREUM_MAINNET',
    timestamp: Date.now()
  });
});

app.post('/api/blockchain/balances', (req, res) => {
  const addresses = req.body;
  const balances = addresses.map(address => ({
    address,
    balance: (Math.random() * 10).toFixed(6) + '000000000000000000',
    network: req.query.network || 'ETHEREUM_MAINNET'
  }));
  res.json(balances);
});

app.get('/api/blockchain/gas-prices', (req, res) => {
  res.json({
    slow: {
      price: '15000000000',
      estimatedTime: 300
    },
    standard: {
      price: '20000000000',
      estimatedTime: 180
    },
    fast: {
      price: '30000000000',
      estimatedTime: 60
    },
    network: req.query.network || 'ETHEREUM_MAINNET',
    baseFee: '12000000000',
    timestamp: Date.now()
  });
});

app.get('/api/blockchain/block-number', (req, res) => {
  res.json({
    blockNumber: 18500000 + Math.floor(Math.random() * 1000),
    network: req.query.network || 'ETHEREUM_MAINNET',
    timestamp: Date.now()
  });
});

app.get('/api/blockchain/validate-address/:address', (req, res) => {
  const address = req.params.address;
  const isValid = /^0x[a-fA-F0-9]{40}$/.test(address);
  res.json({
    address,
    isValid,
    format: 'ETH'
  });
});

app.get('/api/blockchain/network-status', (req, res) => {
  res.json({
    network: req.query.network || 'ETHEREUM_MAINNET',
    isConnected: true,
    latestBlock: 18500000 + Math.floor(Math.random() * 1000),
    peerCount: 50 + Math.floor(Math.random() * 50),
    chainId: 1,
    syncStatus: 'SYNCED',
    timestamp: Date.now()
  });
});

app.get('/api/blockchain/token-balance', (req, res) => {
  const { walletAddress, tokenAddress, network } = req.query;
  res.json({
    walletAddress,
    tokenAddress,
    balance: (Math.random() * 1000).toFixed(2) + '000000000000000000',
    symbol: 'USDT',
    decimals: 18,
    network: network || 'ETHEREUM_MAINNET'
  });
});

app.get('/health', (req, res) => {
  res.json({ status: 'ok', timestamp: Date.now() });
});

const PORT = 8080;
app.listen(PORT, () => {
  console.log(`\n🚀 Mock Blockchain Wallet API Server running at http://localhost:${PORT}`);
  console.log(`\n📋 Available endpoints:`);
  console.log(`   POST   /api/wallets                          - Create wallet`);
  console.log(`   GET    /api/wallets/:walletId                - Get wallet`);
  console.log(`   GET    /api/wallets/user/:userId             - Get user wallets`);
  console.log(`   POST   /api/wallets/import                   - Import wallet`);
  console.log(`   POST   /api/wallets/multisig                 - Create multi-sig wallet`);
  console.log(`   POST   /api/transactions                     - Send transaction`);
  console.log(`   GET    /api/transactions/wallet/:walletId    - Get transaction history`);
  console.log(`   GET    /api/blockchain/balance/:address      - Get balance`);
  console.log(`   GET    /api/blockchain/gas-prices            - Get gas prices`);
  console.log(`\n`);
});
