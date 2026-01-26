package com.etour.app.razorpay.service.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.etour.app.entity.BookingHeader;
import com.etour.app.repository.BookingHeaderRepository;
import com.etour.app.razorpay.dto.RazorpayOrderRequestDTO;
import com.etour.app.razorpay.dto.RazorpayOrderResponseDTO;
import com.etour.app.razorpay.service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayServiceImpl implements RazorpayService {

    private final RazorpayClient razorpayClient;
    private final BookingHeaderRepository bookingRepository;

    public RazorpayServiceImpl(
            RazorpayClient razorpayClient,
            BookingHeaderRepository bookingRepository
    ) {
        this.razorpayClient = razorpayClient;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public RazorpayOrderResponseDTO createOrder(RazorpayOrderRequestDTO request) {

        // 1️⃣ Validate booking
        BookingHeader booking = bookingRepository.findById(
                request.getBookingId().intValue()
        ).orElseThrow(() ->
                new RuntimeException("Booking not found with ID: " + request.getBookingId())
        );

        if (!"PENDING".equalsIgnoreCase(booking.getBookingStatus())) {
            throw new RuntimeException("Only PENDING bookings can be paid");
        }

        try {
            // 2️⃣ Amount strictly from DB (INR → paise)
            long amountInPaise = booking.getTotalAmount()
                    .multiply(new java.math.BigDecimal(100))
                    .longValue();

            JSONObject options = new JSONObject();
            options.put("amount", amountInPaise);
            options.put("currency", "INR");
            options.put("receipt", "booking_" + booking.getId());

            // 3️⃣ Create Razorpay order
            Order order = razorpayClient.orders.create(options);

            // 4️⃣ Prepare response safely
            RazorpayOrderResponseDTO response = new RazorpayOrderResponseDTO();
            response.setOrderId(order.get("id"));
            response.setCurrency(order.get("currency"));

            // ✅ SAFE numeric conversion (IMPORTANT)
            Number amount = (Number) order.get("amount");
            response.setAmount(amount.longValue());

            response.setBookingId(booking.getId().longValue());

            return response;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Razorpay error while creating order: " + e.getMessage(), e
            );
        }
    }
}

