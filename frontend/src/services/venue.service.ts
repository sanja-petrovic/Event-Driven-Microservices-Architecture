import api from '@/common/utils/axiosInstance';

const path = '/venues';

export const findAllVenues = async () => await api.get(`${path}`);
export const findVenue = async (id: string) => await api.get(`${path}/${id}`);
