import api from '@/common/utils/axiosInstance';

const path = '/concerts';

export const findAllConcerts = async (venueId?: string, performer?: string) => {
  const params = new URLSearchParams({
    venueId: venueId ?? '',
    performer: performer ?? '',
  });
  return await api.get(`${path}?${params}`);
};

export const findConcertById = async (id: string) =>
  await api.get(`${path}/${id}`);
