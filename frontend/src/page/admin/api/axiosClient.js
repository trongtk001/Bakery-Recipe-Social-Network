import axios from 'axios';

const axiosClient = axios.create({
  baseURL: 'http://35.198.245.224/api',
});

axiosClient.interceptors.request.use(
  function(config) {
    if (localStorage.getItem('token')) {
      // config
      config.headers['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
    }
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);

// Add a response interceptor
axiosClient.interceptors.response.use(
  function(response) {
    return response.data;
  },
  function(error) {
    return Promise.reject(error);
  }
);

export default axiosClient;
