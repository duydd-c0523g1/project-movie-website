package com.example.movie_ticket.service.impl;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.repository.IBookingRepo;
import com.example.movie_ticket.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class BookingServiceImpl implements IBookingService {
    @Autowired
    private IBookingRepo bookingRepo;

    @Override
    public void saveBooking(Booking booking) {
        bookingRepo.save(booking);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<Booking> findBookingUsername(String username, Pageable pageable) {
        return bookingRepo.findBookingUsername(username, pageable);
    }

    @Override
    public Optional<Booking> findBookingById(Long id) {
        return bookingRepo.findById(id);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    @Override
    public Page<Booking> showAllBooking(Pageable pageable, String phone, String name) {
        return bookingRepo.showAllBooking(pageable, "%" + phone + "%", "%" + name + "%");
    }

    @Override
    public Booking findByIdBooking(Long id) {
        return bookingRepo.findById(id).get();
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepo.cancelBooking(id);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepo.findById(id).get();
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepo.save(booking);
        booking.setDatePurchased(booking.getDatePurchased());
    }

    @Override
    public void sendEmail(Booking booking) {
        String toAddress = booking.getCustomer().getEmail();
        String fromAddress = "trung11041990a1@gmail.com";
        String senderName = "DATPHIM";
        String subject = "Thư Xác Nhận Đặt Vé Xem Phim";
        String content = "<body style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;\">\n" +
                "      <p>Thân gửi :<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> " + booking.getCustomer().getFullName() + "</span></p>\n" +
                "      <p>MOVIES24H xin xác nhận bạn đã huỷ vé xem phim thành công.Thông tin vé như sau:</p>\n" +
                "      <ul>\n" +
                "        <li>Tên phim: " + booking.getShowTime().getMovie().getTitle() + "</li>\n" +
                "        <li>Ngày: " + booking.getShowTime().getShowDate() + "</li>\n" +
                "        <li>Giá: " + booking.getTotalPrice() + "</li>\n" +
                "        <li>Chúng tôi sẽ hoàn tiền trong 2 - 4 giờ</li>\n" +
                "        <li>Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ tại MOVIES24H!</li>\n" +
                "      </ul>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8"); // Đặt encoding là UTF-8
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true); // Sử dụng HTML
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }

    @Override
    public List<Booking> showHistoryBookingDate() {
        return bookingRepo.showHistoryBookingDate();
    }

    @Override
    public List<Booking> showHistoryBookingMonth() {
       return bookingRepo.showHistoryBookingMonth();
    }

    @Override
    public List<Booking> showHistoryBookingYear() {
       return bookingRepo.showHistoryBookingYear();
    }

    @Override
    public List<Booking> showHistoryBookingOfMonth(int month) {
        return bookingRepo.showHistoryBookingOfMonth(month);
    }
}
