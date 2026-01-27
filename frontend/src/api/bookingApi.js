import axios from "axios";

const BOOKING_URL = "http://localhost:8080/api/bookings";
const PAYMENT_URL = "http://localhost:8080/api/razorpay";
const INVOICE_URL = "http://localhost:8080/api/invoices";

// ========== BOOKING ENDPOINTS ==========

export const createBooking = (payload) => {
  return axios.post(`${BOOKING_URL}/create`, payload);
};

export const getBookingById = (bookingId) => {
  return axios.get(`${BOOKING_URL}/${bookingId}`);
};

export const getBookingsByCustomer = (customerId) => {
  return axios.get(`${BOOKING_URL}/customer/${customerId}`);
};

export const getAllBookings = () => {
  return axios.get(BOOKING_URL);
};

export const cancelBooking = (bookingId) => {
  return axios.delete(`${BOOKING_URL}/${bookingId}`);
};

// ========== PAYMENT ENDPOINTS ==========

export const createRazorpayOrder = (bookingId, amount) => {
  return axios.post(`${PAYMENT_URL}/create-order`, {
    bookingId: bookingId,
    amount: amount,
  });
};

export const verifyPayment = (paymentData) => {
  return axios.post(`${PAYMENT_URL}/verify-payment`, {
    bookingId: paymentData.bookingId,
    razorpayOrderId: paymentData.razorpay_order_id,
    razorpayPaymentId: paymentData.razorpay_payment_id,
    razorpaySignature: paymentData.razorpay_signature,
  });
};

// ========== INVOICE ENDPOINTS ==========

export const downloadInvoice = (bookingId) => {
  return axios.get(`${INVOICE_URL}/${bookingId}/download`, {
    responseType: "blob",
  });
};

export const resendInvoiceEmail = (bookingId) => {
  return axios.post(`${INVOICE_URL}/${bookingId}/resend-email`);
};
