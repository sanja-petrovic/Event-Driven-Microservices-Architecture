import Button from '@/common/components/button/Button';
import Concert from '@/model/Concert';
import { Ticket } from '@/model/Ticket';
import Venue from '@/model/Venue';
import { findConcertById } from '@/services/concert.service';
import {
  cancelTicket,
  getTicket,
  purchaseTicket,
} from '@/services/ticket.service';
import { findVenue } from '@/services/venue.service';
import { Divider } from 'antd';
import dayjs from 'dayjs';
import duration from 'dayjs/plugin/duration';
import { useRouter } from 'next/router';
import { useEffect, useRef, useState } from 'react';
import { toast } from 'react-toastify';
import styles from '../styles/tickets.module.scss';
dayjs.extend(duration);

interface TicketOverviewProps {
  id: string;
}

const TicketOverview = ({ id }: TicketOverviewProps) => {
  const [ticket, setTicket] = useState<Ticket>();
  const [concert, setConcert] = useState<Concert>();
  const [venue, setVenue] = useState<Venue>();
  const [timeLeft, setTimeLeft] = useState(60 * 1);
  const intervalRef = useRef();

  useEffect(() => {
    intervalRef.current = setInterval(() => {
      setTimeLeft((t) => t - 1);
    }, 1000);

    return () => clearInterval(intervalRef.current);
  }, []);

  // Add a listener to `timeLeft`
  useEffect(() => {
    if (timeLeft <= 0) {
      toast.error('Expired!');
      clearInterval(intervalRef.current);
      router.push(`/concerts/${ticket?.concertId}`);
    }
  }, [timeLeft]);

  const router = useRouter();
  useEffect(() => {
    getTicket(id)
      .then((response) => {
        setTicket(response.data);
        findConcertById(response.data.concertId)
          .then((response) => {
            setConcert(response.data);
            findVenue(response.data.venueId)
              .then((response) => setVenue(response.data))
              .catch((error) => console.log(error));
          })
          .catch((error) => console.log(error));
      })
      .catch((error) => console.log(error));
  }, [id]);

  const handlePurchase = () => {
    purchaseTicket(id)
      .then((response) => router.push('/'))
      .catch((error) => console.log(error));
  };

  const handleCancel = () => {
    cancelTicket(id)
      .then((response) => router.push(`/concerts/${ticket?.concertId}`))
      .catch((error) => console.log(error));
  };
  return (
    <div className={styles.wrapper}>
      <div className={styles.ticket}>
        <div className={styles.info}>
          <Divider>
            <p
              style={{ marginBottom: '0', fontSize: '12px' }}
              className="subtitle"
            >
              {concert?.performer}
            </p>
            <p className="title">{concert?.name}</p>
          </Divider>
          <p className={styles.text}>
            {venue?.name}, {venue?.location}
          </p>
          <p className={styles.text}>
            {dayjs(concert?.dateTime).format('dddd, MMMM D, YYYY  HH:mm')}
          </p>
          <p className={styles.text}>
            <b>Seat no. {ticket?.seat}</b>
          </p>
        </div>
        <div className={styles.footer}>
          <Button type="transparent" text="Purchase" action={handlePurchase} />
        </div>
      </div>
      <div className={styles.timer}>
        <p>
          Time left to purchase:{' '}
          <b>{dayjs.duration(timeLeft, 'seconds').format('mm:ss')}</b>
        </p>
      </div>
      <Divider style={{ marginTop: '1rem' }}>OR</Divider>
      <Button
        type="transparent"
        style={{ marginTop: '1rem' }}
        text="Cancel"
        action={handleCancel}
      />
    </div>
  );
};

export default TicketOverview;
