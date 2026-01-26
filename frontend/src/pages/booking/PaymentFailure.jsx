const PaymentFailure = () => {
  return (
    <div className="p-8 text-center">
      <h1 className="text-2xl font-bold text-red-600">
        Payment Failed
      </h1>
      <button className="mt-4 bg-blue-600 text-white px-6 py-2 rounded">
        Try Again
      </button>
    </div>
  );
};

export default PaymentFailure;
