import api from '@/common/utils/axiosInstance';
import { Login } from '@/model/Login';
import { Register } from '@/model/Register';

const path = '/auth';

export const register = async (data: Register) =>
  await api.post(`${path}/register`, data);

export const login = async (data: Login) =>
  await api.post(`${path}/login`, data);

export const logout = async () => await api.post(`${path}/logout`);
