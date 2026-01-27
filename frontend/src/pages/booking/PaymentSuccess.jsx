import { useParams } from "react-router-dom";

const PaymentSuccess = () => {
  const { bookingId } = useParams();

  return (
    <div className="p-8 text-center">
      <h1 className="text-2xl font-bold text-green-600">
        Payment Successful
      </h1>
      <p>Booking ID: {bookingId}</p>
    </div>
  );
};

export default PaymentSuccess;
