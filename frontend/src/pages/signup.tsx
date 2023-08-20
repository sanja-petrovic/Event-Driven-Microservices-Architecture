import SignUp from '@/common/features/auth/components/SignUp';
import Head from 'next/head';

export default function SignUpPage() {
  return (
    <>
      <Head>
        <title>Eventio | Sign up</title>
      </Head>
      <SignUp />
    </>
  );
}
