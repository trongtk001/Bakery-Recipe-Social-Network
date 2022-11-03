import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

function callApi(endpoint, method = "GET", data, token, withCredentials = false) {
  return axios({
    method: method,
    url: `${API_URL}/${endpoint}`,
    headers: {
      Authorization: token ? `Bearer ${token}` : null
    },
    data: data ? data.body : null,
    params: data ? data.params : null,
    withCredentials: withCredentials
  }).catch(error => {
    if (error.response) {
      throw error.response;
    } else {
      console.log(error);
    }
  })
}

export default callApi;
