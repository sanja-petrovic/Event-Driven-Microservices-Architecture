//import  "../styles/globals.scss";
import BaseTemplate from '@/common/components/baseTemplate/BaseTemplate';
import api from '@/common/utils/axiosInstance';
import { ConfigProvider } from 'antd';
import type { AppProps } from 'next/app';
import { Inter } from 'next/font/google';
import { useEffect } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../common/styles/globals.scss';

const inter = Inter({ subsets: ['latin'] });
export default function App({ Component, pageProps }: AppProps) {
  useEffect(() => {
    if (typeof window !== 'undefined' && localStorage.getItem('jwt')) {
      api.defaults.headers.common['Authorization'] =
        'Bearer ' + localStorage.getItem('jwt');
    }
  });
  return (
    <ConfigProvider
      theme={{
        token: {
          fontFamily: `${inter.style.fontFamily}`,
        },
      }}
    >
      <main className={inter.className}>
        <BaseTemplate>
          <ToastContainer />
          <Component {...pageProps} />
        </BaseTemplate>
      </main>
    </ConfigProvider>
  );
}
