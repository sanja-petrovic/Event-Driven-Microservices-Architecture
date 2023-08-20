import NotFound from '@/common/components/404/NotFound';
import Head from 'next/head';

export default function NotFoundPage() {
  return (
    <>
      <Head>
        <title>Eventio | Not Found</title>
      </Head>
      <NotFound />
    </>
  );
}
