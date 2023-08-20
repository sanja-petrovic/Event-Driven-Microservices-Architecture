import Button from '@/common/components/button/Button';
import { SearchOutlined } from '@ant-design/icons';
import { Form, Input, List, Select } from 'antd';

import Loading from '@/common/components/loading/Loading';
import Concert from '@/model/Concert';
import Venue from '@/model/Venue';
import { findAllConcerts } from '@/services/concert.service';
import { findAllVenues } from '@/services/venue.service';
import dayjs from 'dayjs';
import { useEffect, useState } from 'react';
import styles from '../styles/search.module.scss';

interface Option {
  label: string;
  value: string;
}

const SearchBar = () => {
  const [venueOptions, setVenueOptions] = useState<Option[]>([]);
  const [searchParams, setSearchParams] = useState({
    venueId: '',
    performer: '',
  });
  const [concerts, setConcerts] = useState<Concert[]>([]);
  const [venueMap, setVenueMap] = useState<any>(new Map());

  const [form] = Form.useForm();
  useEffect(() => {
    findAllVenues()
      .then((response) => {
        const venues: Venue[] = response.data;
        let temp: any = [];
        let tempMap = new Map();
        venues.forEach((venue) => {
          temp.push({
            value: venue.id,
            label: venue.name,
          });
          tempMap.set(venue.id, venue);
        });
        setVenueOptions(temp);
        setVenueMap(tempMap);
      })
      .catch((error) => console.log(error));
    findAllConcerts()
      .then((response) => setConcerts(response.data))
      .catch((error) => console.log(error));
  }, []);

  const handleFinish = async () => {
    console.log(form.getFieldsValue());
    await findAllConcerts(
      form.getFieldValue('venue'),
      form.getFieldValue('performer')
    )
      .then((response) => setConcerts(response.data))
      .catch((error) => console.log(error));
  };

  return concerts?.length > 0 ? (
    <>
      <Form
        form={form}
        className={styles.searchBarContainer}
        onFinish={handleFinish}
      >
        <Form.Item
          name="performer"
          style={{ width: '1240px', maxWidth: '1440px' }}
        >
          <Input
            allowClear
            placeholder="Performer"
            bordered={false}
            style={{
              backgroundColor: 'white',
              padding: '1.2rem',
              borderRadius: '0',
              borderBottomLeftRadius: '2rem',
              borderTopLeftRadius: '2rem',
              height: '54.4px',
            }}
          />
        </Form.Item>
        <Form.Item name="venue" style={{ width: '100%' }}>
          <Select
            showSearch
            placeholder="Venue"
            optionFilterProp="children"
            filterOption={(input, option) =>
              (option?.label ?? '').toLowerCase().includes(input.toLowerCase())
            }
            options={venueOptions}
            bordered={false}
            allowClear
            style={{
              backgroundColor: 'white',
              padding: '0.7rem',
              borderRadius: '0',
              height: '54.4px',
            }}
          />
        </Form.Item>
        <Button
          type="primary"
          style={{
            borderTopLeftRadius: 0,
            borderBottomLeftRadius: 0,
            height: '54.4px',
            paddingRight: '2rem',
          }}
        >
          <SearchOutlined style={{ fontSize: '24px' }} />
        </Button>
      </Form>
      <List
        className={styles.list}
        dataSource={concerts}
        renderItem={(concert) => (
          <List.Item
            key={concert.id}
            actions={[
              <a href={`/concerts/${concert.id}`} key="more">
                Get tickets
              </a>,
            ]}
          >
            <List.Item.Meta
              title={concert.name}
              description={dayjs(concert.dateTime).format(
                'dddd, MMMM D, YYYY  HH:mm'
              )}
              style={{ marginBottom: '0.5rem' }}
            />

            {`${concert.performer} | ${venueMap?.get(concert.venueId)?.name}, ${
              venueMap?.get(concert.venueId)?.location
            }`}
          </List.Item>
        )}
      ></List>
    </>
  ) : (
    <Loading />
  );
};

export default SearchBar;
