import Button from '@/common/components/button/Button';
import Loading from '@/common/components/loading/Loading';
import Concert from '@/model/Concert';
import { Ticket } from '@/model/Ticket';
import { findConcertById } from '@/services/concert.service';
import {
  checkTicketAvailability,
  findAllTicketsByConcert,
  selectTicket,
} from '@/services/ticket.service';
import { Divider } from 'antd';
import classNames from 'classnames';
import Head from 'next/head';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import styles from '../styles/concerts.module.scss';

interface ConcertOverviewProps {
  id: string;
}

const ConcertOverview = ({ id }: ConcertOverviewProps) => {
  const [concert, setConcert] = useState<Concert>();
  const [tickets, setTickets] = useState<Ticket[]>([]);
  const [selected, setSelected] = useState<string>();
  const router = useRouter();

  const getTickets = () => {
    findAllTicketsByConcert(id)
      .then((response) => setTickets(response.data))
      .catch((error) => console.log(error));
  };
  useEffect(() => {
    if (id) {
      if (!concert) {
        findConcertById(id)
          .then((response) => {
            setConcert(response.data);
            getTickets();
          })
          .catch((error) => console.log(error));
      }
    }
  }, [tickets, concert, id]);

  const handleContinue = async () => {
    if (selected) {
      await checkTicketAvailability(selected).then((response) => {
        if (response.data == true) {
          selectTicket(selected)
            .then(() => router.push(`/tickets/${selected}/purchase`))
            .catch((error) => console.log(error));
        } else {
          toast.error(
            'Unfortunately, the selected ticket is not available anymore. Please try a different one.'
          );
          getTickets();
          setSelected(undefined);
        }
      });
    }
  };

  const handleSelect = async (id: string) => {
    if (selected == id) {
      setSelected(undefined);
    } else {
      await checkTicketAvailability(id)
        .then((response) => {
          if (response.data == true) {
            setSelected(id);
          } else {
            toast.error(
              'Unfortunately, the selected ticket is not available anymore. Please try a different one.'
            );
            getTickets();
            setSelected(undefined);
          }
        })
        .catch((error) => console.log(error));
    }
  };

  return concert ? (
    <div className={styles.wrapper}>
      <Head>
        <title>{`Eventio | ${concert.name}`}</title>
      </Head>
      <div className={styles.info}>
        <h2 className="subtitle">{concert.performer}</h2>
        <h1 className="title-bigger">{concert.name}</h1>
      </div>
      <Divider />
      <div className={styles.stage}>
        <p className="undraggable">stage</p>
      </div>
      <div className={styles.seatingPlan}>
        {tickets.map((ticket) => (
          <button
            key={ticket.id}
            disabled={ticket.status != 'AVAILABLE'}
            onClick={() => handleSelect(ticket.id)}
            className={classNames(
              styles.seat,
              {
                [styles.taken]: ticket.status != 'AVAILABLE',
              },
              {
                [styles.selected]: ticket.id == selected,
              }
            )}
          ></button>
        ))}
      </div>
      <Button
        type="primary"
        text="Purchase selected seat"
        disabled={!selected}
        action={handleContinue}
        style={{
          marginTop: '2rem',
          textTransform: 'uppercase',
          fontWeight: '600',
          letterSpacing: '1.12px',
        }}
      ></Button>
    </div>
  ) : (
    <>
      <Head>
        <title>Eventio</title>
      </Head>
      <Loading />
    </>
  );
};

export default ConcertOverview;
