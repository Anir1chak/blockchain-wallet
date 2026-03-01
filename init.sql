-- Initialize database schemas for Blockchain Wallet System

-- Wallet Service Tables
CREATE TABLE IF NOT EXISTS wallets (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(42) NOT NULL UNIQUE,
    encrypted_private_key TEXT NOT NULL,
    public_key TEXT NOT NULL,
    wallet_type VARCHAR(50) NOT NULL,
    network VARCHAR(50) NOT NULL,
    balance VARCHAR(78) DEFAULT '0',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_wallets_address ON wallets(address);
CREATE INDEX idx_wallets_network ON wallets(network);

CREATE TABLE IF NOT EXISTS multi_sig_wallets (
    id UUID PRIMARY KEY,
    wallet_id UUID REFERENCES wallets(id) ON DELETE CASCADE,
    required_signatures INT NOT NULL,
    contract_address VARCHAR(42),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS signers (
    id UUID PRIMARY KEY,
    multi_sig_wallet_id UUID REFERENCES multi_sig_wallets(id) ON DELETE CASCADE,
    address VARCHAR(42) NOT NULL,
    name VARCHAR(255),
    added_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_signers_multi_sig ON signers(multi_sig_wallet_id);

-- Transaction Service Tables
CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY,
    tx_hash VARCHAR(66) UNIQUE,
    from_address VARCHAR(42) NOT NULL,
    to_address VARCHAR(42) NOT NULL,
    amount VARCHAR(78) NOT NULL,
    gas_price VARCHAR(78),
    gas_limit VARCHAR(78),
    gas_used VARCHAR(78),
    nonce BIGINT,
    network VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    block_number BIGINT,
    block_hash VARCHAR(66),
    input_data TEXT,
    timestamp BIGINT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_transactions_from ON transactions(from_address);
CREATE INDEX idx_transactions_to ON transactions(to_address);
CREATE INDEX idx_transactions_status ON transactions(status);
CREATE INDEX idx_transactions_network ON transactions(network);
CREATE INDEX idx_transactions_timestamp ON transactions(timestamp DESC);

CREATE TABLE IF NOT EXISTS multi_sig_transactions (
    id UUID PRIMARY KEY,
    tx_hash VARCHAR(66) UNIQUE,
    multi_sig_wallet_id UUID REFERENCES multi_sig_wallets(id),
    to_address VARCHAR(42) NOT NULL,
    amount VARCHAR(78) NOT NULL,
    data TEXT,
    required_signatures INT NOT NULL,
    current_signatures INT DEFAULT 0,
    status VARCHAR(50) NOT NULL,
    nonce BIGINT,
    executed_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_multi_sig_tx_wallet ON multi_sig_transactions(multi_sig_wallet_id);
CREATE INDEX idx_multi_sig_tx_status ON multi_sig_transactions(status);

CREATE TABLE IF NOT EXISTS signatures (
    id UUID PRIMARY KEY,
    multi_sig_tx_id UUID REFERENCES multi_sig_transactions(id) ON DELETE CASCADE,
    signer_address VARCHAR(42) NOT NULL,
    signature TEXT NOT NULL,
    signed_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_signatures_tx ON signatures(multi_sig_tx_id);

-- Function to update timestamp on modification
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Triggers for updated_at
CREATE TRIGGER update_wallets_updated_at
    BEFORE UPDATE ON wallets
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_transactions_updated_at
    BEFORE UPDATE ON transactions
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_multi_sig_transactions_updated_at
    BEFORE UPDATE ON multi_sig_transactions
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
