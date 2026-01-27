import axios from "axios";

const BASE_URL = "http://localhost:8080/api/customers";

export const registerCustomer = (customerData) => {
  return axios.post(`${BASE_URL}/register`, customerData)
    .then(res => res.data);
};

export const loginCustomer = (loginData) => {
  return axios.post(`${BASE_URL}/login`, loginData)
    .then(res => res.data);
};
