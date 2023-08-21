import TicketOverview from '@/common/features/tickets/components/TicketOverview';
import Head from 'next/head';
import { useRouter } from 'next/router';

export default function TicketOverviewPage() {
  const router = useRouter();
  const { id } = router.query;
  return (
    <>
      <Head>Eventio | Ticket overview</Head>
      <TicketOverview id={id as string} />
    </>
  );
}
