package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Booking;
import com.example.movie_ticket.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBookingRepo extends JpaRepository<Booking, Long> {
    @Query(value = "select * from bookings bk join customer c on bk.customer_id = c.id join accounts a on c.account_id = a.id where a.username = :param1",
            nativeQuery = true)
    Page<Booking> findBookingUsername(@Param("param1") String username, Pageable pageable);

    @Query(value = "select bk.id,cus.full_name,cus.phone_number,cus.email,st.movie_id,st.price,st.show_date," +
            "bk.customer_id,bk.showtime_id,bk.is_deleted,bk.date_purchased,bk.total_price,bk.code_booking " +
            "            from bookings bk " +
            "            join customer cus on cus.id = bk.customer_id " +
            "            join show_time st on st.id = bk.showtime_id " +
            "            where is_deleted = 0 and cus.phone_number like:phone and st.show_date like :dateSearch" +
            "            order by ABS(DATEDIFF(CURDATE(), st.show_date)) ", nativeQuery = true)
    Page<Booking> showAllBooking(Pageable pageable, @Param("phone") String phone, @Param("dateSearch") String dateSearch);

    @Modifying
    @Transactional
    @Query(value = " update bookings set is_deleted = 1" +
            " where bookings.id =:id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = " update bookings set is_deleted = 2" +
            " where bookings.id =:id", nativeQuery = true)
    void cancelBooking(@Param("id") Long id);

    @Query(value = "select * " +
            " from bookings " +
            "            where date(date_purchased) = date(now()) ",nativeQuery = true)
    List<Booking> showHistoryBookingDate();
    @Query(value = "select * from bookings " +
            "            where month(date_purchased) = month(now()) " +
            "            order by date(date_purchased) ",nativeQuery = true)
    List<Booking> showHistoryBookingMonth();
    @Query(value = "select * from bookings " +
            "            where year(date_purchased) = year(now()) " +
            "            order by date(date_purchased) ",nativeQuery = true)
    List<Booking> showHistoryBookingYear();
    @Query(value = "select * from bookings " +
            "            where month(date_purchased) =:month " +
            "            order by date(date_purchased) ",nativeQuery = true)
    List<Booking> showHistoryBookingOfMonth(@Param("month") int month);

}



