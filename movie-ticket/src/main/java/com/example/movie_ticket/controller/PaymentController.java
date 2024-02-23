package com.example.movie_ticket.controller;

import com.example.movie_ticket.config.ConfigVNPay;
import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.model.SeatBooking;
import com.example.movie_ticket.model.ShowTime;
import com.example.movie_ticket.service.IBookingService;
import com.example.movie_ticket.service.ICustomerService;
import com.example.movie_ticket.service.ISeatBookingService;
import com.example.movie_ticket.service.IShowTimeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IBookingService bookingService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IShowTimeService showTimeService;
    @Autowired
    private ISeatBookingService seatBookingService;

    @PostMapping
    public String getPayment(@RequestParam Set<String> seatSelect,
                             @RequestParam Long customer,
                             @RequestParam Float totalPrice,
                             @RequestParam Long showTimeOrder,
                             @RequestParam String payment
    ) throws Exception {
        if (payment.equals("vnpay")) {
            String orderType = "other";
            String returnUrl = ConfigVNPay.vnp_ReturnUrl;
            returnUrl += "?customer=" + URLEncoder.encode(customer.toString(), StandardCharsets.US_ASCII.toString());
            returnUrl += "&totalPrice=" + URLEncoder.encode(totalPrice.toString(), StandardCharsets.US_ASCII.toString());
            returnUrl += "&showTimeOrder=" + URLEncoder.encode(showTimeOrder.toString(), StandardCharsets.US_ASCII.toString());
            for (String value : seatSelect) {
                returnUrl += "&seatSelect=" + URLEncoder.encode(value, StandardCharsets.US_ASCII.toString());
            }

            long amount = Math.round(totalPrice * 100);
            String vnp_TxnRef = ConfigVNPay.getRandomNumber(8);
            String vnp_IpAddr = "0:0:0:0:0:0:0:1";
            String vnp_TmnCode = ConfigVNPay.vnp_TmnCode;
            Map<String, String> vnp_Params = new HashMap<>();

            vnp_Params.put("vnp_Version", ConfigVNPay.vnp_Version);
            vnp_Params.put("vnp_Command", ConfigVNPay.vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_BankCode", "NCB");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", returnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = ConfigVNPay.hmacSHA512(ConfigVNPay.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = ConfigVNPay.vnp_PayUrl + "?" + queryUrl;
            return "redirect:" + paymentUrl;
        } else {
            String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
            String partnerCode = "MOMOBKUN20180529";
            String accessKey = "klm05TvNBzhg7h7j";
            String secretKey = "at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa";
            String orderInfo = "Thanh toán qua MoMo";
            int amountC = totalPrice.intValue();
            String amount = String.valueOf(amountC);
            String orderId = ConfigVNPay.getRandomNumber(8);
            String redirectUrl = "http://localhost:8080/payment/success";
            String ipnUrl = "http://localhost:8080/payment/success";
            String extraData = "";

            String requestId = String.valueOf(System.currentTimeMillis());
            String requestType = "payWithATM";
            //before sign HMAC SHA256 signature
            String rawHash =
                    "accessKey=" +
                    accessKey + "&amount=" +
                    amount + "&extraData=" +
                    extraData + "&ipnUrl=" +
                    ipnUrl + "&orderId=" +
                    orderId + "&orderInfo=" +
                    orderInfo + "&partnerCode=" +
                    partnerCode + "&redirectUrl=" +
                    redirectUrl + "&requestId=" +
                    requestId + "&requestType=" +
                    requestType;
            byte[] hmacData = hmacSHA256(rawHash, secretKey);
            String signature = bytesToHex(hmacData);

            String data = "{"
                    + "\"partnerCode\": \"" + partnerCode + "\","
                    + "\"partnerName\": \"Test\","
                    + "\"storeId\": \"MomoTestStore\","
                    + "\"requestId\": \"" + requestId + "\","
                    + "\"amount\": " + amount + ","
                    + "\"orderId\": \"" + orderId + "\","
                    + "\"orderInfo\": \"" + orderInfo + "\","
                    + "\"redirectUrl\": \"" + redirectUrl + "\","
                    + "\"ipnUrl\": \"" + ipnUrl + "\","
                    + "\"lang\": \"vi\","
                    + "\"extraData\": \"" + extraData + "\","
                    + "\"requestType\": \"" + requestType + "\","
                    + "\"signature\": \"" + signature + "\""
                    + "}";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(data, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(endpoint, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode name = root.path("payUrl");
            return "redirect:"+name.toString().replace("\"","");
        }
    }

    @GetMapping("/success")
    public String paymentSuccessful(@RequestParam Set<String> seatSelect,
                                    @RequestParam Long customer,
                                    @RequestParam Float totalPrice,
                                    @RequestParam Long showTimeOrder,
                                    @RequestParam String vnp_TxnRef,
                                    Model model) {
        Customer customerFind = customerService.findCustomerbyId(customer);
        ShowTime showTimeFind = showTimeService.getShowTimeById(showTimeOrder);
        Booking booking = new Booking();
        booking.setCustomer(customerFind);
        booking.setCodeBooking(vnp_TxnRef);
        booking.setShowTime(showTimeFind);
        booking.setTotalPrice(totalPrice);
        bookingService.saveBooking(booking);
        for (String seat : seatSelect) {
            SeatBooking seatBooking = new SeatBooking();
            seatBooking.setBooking(booking);
            seatBooking.setSeat(seat);
            seatBookingService.saveSeatBooking(seatBooking);
        }
        model.addAttribute("booking", booking);
        model.addAttribute("seatBookings", seatSelect);
        return "payment-success";
    }


    private static byte[] hmacSHA256(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(data.getBytes());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
