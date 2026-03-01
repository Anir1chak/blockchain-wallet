import { ReactNode } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import {
  HomeIcon,
  WalletIcon,
  ArrowsRightLeftIcon,
  ClockIcon,
  UserGroupIcon,
  PlusIcon,
  Cog6ToothIcon,
  BellIcon,
  ArrowRightOnRectangleIcon,
  UserCircleIcon,
} from '@heroicons/react/24/outline';
import { useAuthStore } from '../store/authStore';

interface LayoutProps {
  children: ReactNode;
}

const navigation = [
  { name: 'Dashboard', href: '/', icon: HomeIcon },
  { name: 'Send', href: '/send', icon: ArrowsRightLeftIcon },
  { name: 'History', href: '/transactions', icon: ClockIcon },
  { name: 'Multi-Sig', href: '/multisig', icon: UserGroupIcon },
];

export default function Layout({ children }: LayoutProps) {
  const location = useLocation();
  const navigate = useNavigate();
  const { user, logout } = useAuthStore();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <div className="min-h-screen bg-gray-900">
      {/* Header */}
      <header className="bg-gray-800 border-b border-gray-700">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center h-16">
            <div className="flex items-center">
              <Link to="/" className="flex items-center space-x-2">
                <WalletIcon className="h-8 w-8 text-primary-500" />
                <span className="text-xl font-bold text-white">CryptoWallet</span>
              </Link>
            </div>

            <div className="flex items-center space-x-4">
              <button className="p-2 text-gray-400 hover:text-white transition-colors">
                <BellIcon className="h-6 w-6" />
              </button>
              <button className="p-2 text-gray-400 hover:text-white transition-colors">
                <Cog6ToothIcon className="h-6 w-6" />
              </button>
              <div className="flex items-center space-x-2 bg-gray-700 rounded-lg px-3 py-1.5">
                <div className="w-2 h-2 bg-green-500 rounded-full"></div>
                <span className="text-sm text-gray-300">Sepolia</span>
              </div>

              {/* User Menu */}
              <div className="relative group">
                <button className="flex items-center space-x-2 bg-gray-700 hover:bg-gray-600 rounded-lg px-3 py-1.5 transition-colors">
                  <UserCircleIcon className="h-6 w-6 text-gray-300" />
                  <span className="text-sm text-gray-300">{user?.name || 'User'}</span>
                </button>
                
                {/* Dropdown */}
                <div className="absolute right-0 mt-2 w-48 bg-gray-800 rounded-lg shadow-lg border border-gray-700 opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all z-50">
                  <div className="p-3 border-b border-gray-700">
                    <p className="text-sm font-medium text-white">{user?.name}</p>
                    <p className="text-xs text-gray-400 truncate">{user?.email}</p>
                  </div>
                  <button
                    onClick={handleLogout}
                    className="w-full flex items-center space-x-2 px-3 py-2 text-sm text-red-400 hover:bg-gray-700 rounded-b-lg transition-colors"
                  >
                    <ArrowRightOnRectangleIcon className="h-5 w-5" />
                    <span>Sign Out</span>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>

      <div className="flex">
        {/* Sidebar */}
        <nav className="w-64 bg-gray-800 min-h-[calc(100vh-4rem)] border-r border-gray-700 relative flex flex-col">
          <div className="p-4">
            <Link
              to="/create"
              className="flex items-center justify-center space-x-2 w-full btn-primary"
            >
              <PlusIcon className="h-5 w-5" />
              <span>New Wallet</span>
            </Link>
          </div>

          <ul className="space-y-1 px-2">
            {navigation.map((item) => {
              const isActive = location.pathname === item.href;
              return (
                <li key={item.name}>
                  <Link
                    to={item.href}
                    className={`flex items-center space-x-3 px-4 py-3 rounded-lg transition-colors ${
                      isActive
                        ? 'bg-primary-600 text-white'
                        : 'text-gray-400 hover:bg-gray-700 hover:text-white'
                    }`}
                  >
                    <item.icon className="h-5 w-5" />
                    <span>{item.name}</span>
                  </Link>
                </li>
              );
            })}
          </ul>

          {/* Network Status - Fixed to bottom */}
          <div className="mt-auto p-4">
            <div className="bg-gray-900/50 border border-gray-700 rounded-xl p-4">
              <div className="flex items-center justify-between mb-3">
                <span className="text-xs font-medium text-gray-400 uppercase tracking-wide">Network</span>
                <div className="flex items-center space-x-1.5">
                  <span className="h-2 w-2 bg-green-500 rounded-full animate-pulse"></span>
                  <span className="text-xs font-medium text-green-400">Connected</span>
                </div>
              </div>
              <div className="flex items-center space-x-2">
                <div className="h-8 w-8 bg-gradient-to-br from-purple-500 to-blue-500 rounded-lg flex items-center justify-center">
                  <svg className="h-4 w-4 text-white" viewBox="0 0 24 24" fill="currentColor">
                    <path d="M12 1.5l-8 13.5h16l-8-13.5zm0 5l4.5 7.5h-9l4.5-7.5z"/>
                  </svg>
                </div>
                <div>
                  <div className="text-sm font-medium text-white">Ethereum Sepolia</div>
                  <div className="text-xs text-gray-500">Chain ID: 11155111</div>
                </div>
              </div>
            </div>
          </div>
        </nav>

        {/* Main Content */}
        <main className="flex-1 p-8">
          {children}
        </main>
      </div>
    </div>
  );
}
