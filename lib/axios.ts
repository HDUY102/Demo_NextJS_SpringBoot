import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:10000/api',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: false, // Nếu dùng cookie để auth thì true
});

export default axiosInstance;