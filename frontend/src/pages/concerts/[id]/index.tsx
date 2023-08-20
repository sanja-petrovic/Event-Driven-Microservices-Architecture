import ConcertOverview from '@/common/features/concerts/components/ConcertOverview';
import { useRouter } from 'next/router';

export default function ConcertPage() {
  const router = useRouter();
  const { id } = router.query;
  return (
    <>
      <ConcertOverview id={id} />
    </>
  );
}
