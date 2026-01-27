import { useParams, useNavigate } from "react-router-dom";

const PaymentPage = () => {
  const { bookingId } = useParams();
  const navigate = useNavigate();

  return (
    <div className="p-8 text-center">
      <h2 className="text-xl font-bold">Choose Payment Method</h2>

      <button
        className="mt-6 bg-green-600 text-white px-6 py-2 rounded"
        onClick={() => navigate(`/payment/success/${bookingId}`)}
      >
        Pay
      </button>
    </div>
  );
};

export default PaymentPage;
