import axios from "axios";

const BASE_URL = "http://localhost:8080/api/bookings";

export const createBooking = (payload) => {
  return axios.post(`${BASE_URL}/create`, payload);
};

export const getBookingById = (bookingId) => {
  return axios.get(`${BASE_URL}/${bookingId}`);
};
