import api from '@/common/utils/axiosInstance';

const path = '/tickets';

export const findAllTicketsByConcert = async (concertId: string) =>
  await api.get(`${path}/concerts/${concertId}`);

export const checkTicketAvailability = async (ticketId: string) =>
  await api.get(`${path}/${ticketId}/availability`);

export const selectTicket = async (ticketId: string) =>
  await api.post(`${path}/${ticketId}/select`);

export const getTicket = async (ticketId: string) =>
  await api.get(`${path}/${ticketId}`);

export const cancelTicket = async (ticketId: string) =>
  await api.post(`${path}/${ticketId}/cancel`);

export const purchaseTicket = async (ticketId: string) =>
  await api.post(`${path}/${ticketId}/purchase`);
