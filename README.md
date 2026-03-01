# Blockchain Cryptocurrency Wallet System

A multi-node wallet service with gRPC communication, supporting wallet creation, transaction signing, balance queries, and multi-signature wallets.

## Architecture

```
┌─────────────────────┐     ┌─────────────────────┐
│   React Frontend    │────▶│   API Gateway       │
│   (Port 3000)       │     │   (Port 8080)       │
└─────────────────────┘     └──────────┬──────────┘
                                       │ gRPC
                    ┌──────────────────┼──────────────────┐
                    ▼                  ▼                  ▼
          ┌─────────────────┐ ┌─────────────────┐ ┌─────────────────┐
          │ Wallet Service  │ │  TX Service     │ │Blockchain Svc   │
          │  (Port 9091)    │ │  (Port 9092)    │ │  (Port 9093)    │
          └────────┬────────┘ └────────┬────────┘ └────────┬────────┘
                   │                   │                   │
                   ▼                   ▼                   ▼
          ┌─────────────────────────────────────────────────────────┐
          │  PostgreSQL (5432)  │  Redis (6379)  │  Ethereum Node   │
          └─────────────────────────────────────────────────────────┘
```

## Tech Stack

- **Backend**: Spring Boot 3.2, gRPC, Web3j
- **Frontend**: React 18, TypeScript, TailwindCSS
- **Database**: PostgreSQL 15
- **Cache**: Redis 7
- **Containerization**: Docker & Docker Compose

## Quick Start

```bash
# Start all services
docker-compose up -d

# Frontend will be available at http://localhost:3000
# API Gateway at http://localhost:8080
```

## Services

| Service | Port | Description |
|---------|------|-------------|
| Frontend | 3000 | React Web Application |
| API Gateway | 8080 | REST API + WebSocket |
| Wallet Service | 9091 | gRPC - Wallet Management |
| Transaction Service | 9092 | gRPC - TX Signing |
| Blockchain Service | 9093 | gRPC - Chain Interactions |
| PostgreSQL | 5432 | Database |
| Redis | 6379 | Cache |

## Features

- ✅ HD Wallet Creation (BIP39/BIP44)
- ✅ Multiple Network Support (Ethereum, Sepolia)
- ✅ Transaction Signing & Broadcasting
- ✅ Real-time Balance Updates
- ✅ Transaction History
- ✅ Multi-Signature Wallets (2-of-3, 3-of-5, etc.)
- ✅ Gas Estimation
- ✅ Portfolio Analytics
