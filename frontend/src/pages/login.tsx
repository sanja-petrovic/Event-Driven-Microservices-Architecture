import Login from '@/common/features/auth/components/Login';
import Head from 'next/head';

export default function LoginPage() {
  return (
    <>
      <Head>
        <title>Eventio | Log in</title>
      </Head>
      <Login />
    </>
  );
}
