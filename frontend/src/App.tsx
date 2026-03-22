import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Toaster } from 'react-hot-toast';
import Layout from './components/Layout';
import ProtectedRoute from './components/ProtectedRoute';
import Dashboard from './pages/Dashboard';
import WalletDetails from './pages/WalletDetails';
import SendTransaction from './pages/SendTransaction';
import TransactionHistory from './pages/TransactionHistory';
import MultiSigManager from './pages/MultiSigManager';
import MultiSigList from './pages/MultiSigList';
import CreateWallet from './pages/CreateWallet';
import Login from './pages/Login';
import Signup from './pages/Signup';

function App() {
  return (
    <Router>
      <Toaster 
        position="top-right"
        toastOptions={{
          style: {
            background: '#1f2937',
            color: '#fff',
          },
        }}
      />
      <Routes>
        {/* Public Routes */}
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        {/* Protected Routes */}
        <Route
          path="/*"
          element={
            <ProtectedRoute>
              <Layout>
                <Routes>
                  <Route path="/" element={<Dashboard />} />
                  <Route path="/wallet/:id" element={<WalletDetails />} />
                  <Route path="/send" element={<SendTransaction />} />
                  <Route path="/transactions" element={<TransactionHistory />} />
                  <Route path="/multisig" element={<MultiSigList />} />
                  <Route path="/multisig/:id" element={<MultiSigManager />} />
                  <Route path="/create" element={<CreateWallet />} />
                </Routes>
              </Layout>
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
