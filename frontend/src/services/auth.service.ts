import api from '@/common/utils/axiosInstance';
import { Register } from '@/model/Register';

const path = '/auth';

export const register = async (data: Register) =>
  await api.post(`${path}/register`, data);
