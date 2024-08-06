package cinemas.models;

import cinemas.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "booking_refunds")
public class BookingRefund {
    @Id
    @Column(name = "booking_id")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Booking booking;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "refunded_at")
    private ZonedDateTime refundedAt;
    @Column(name = "refunded_reason")
    private String refundedReason;

    @ManyToOne
    @JoinColumn(name = "refunded_by", insertable = false, updatable = false)
    private User refundedByUser;

    // Getters and Setters
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public ZonedDateTime getRefundedAt() {
        return refundedAt;
    }

    public void setRefundedAt(ZonedDateTime refundedAt) {
        this.refundedAt = refundedAt;
    }

    public String getRefundedReason() {
        return refundedReason;
    }

    public void setRefundedReason(String refundedReason) {
        this.refundedReason = refundedReason;
    }

    public User getRefundedByUser() {
        return refundedByUser;
    }

    public void setRefundedByUser(User refundedByUser) {
        this.refundedByUser = refundedByUser;
    }
}
