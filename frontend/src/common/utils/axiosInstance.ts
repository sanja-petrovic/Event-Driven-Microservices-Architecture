import axios from 'axios';

const api = axios.create({
  withCredentials: true,
  baseURL: 'http://localhost:8081/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
