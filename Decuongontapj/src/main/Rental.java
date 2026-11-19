package main;

import java.sql.Timestamp;

public class Rental {
    // Mã thuê phim
    private long rentalId;
    // Tên phim
    private String movieTitle;
    // Tên khách hàng
    private String customerName;
    // Ngày thuê
    private Timestamp rentDate;
    // Ngày trả
    private Timestamp dueDate;
    // Số lượng phim thuê
    private int quantity;
    // Trạng thái thuê (Rented, Returned, Late)
    private String status;

    // Constructor mặc định (không tham số)
    public Rental() {
    }

    // Constructor có tham số để khởi tạo đối tượng Rental
    public Rental(long rentalId, String movieTitle, String customerName, Timestamp rentDate, Timestamp dueDate, int quantity, String status) {
        this.rentalId = rentalId;
        this.movieTitle = movieTitle;
        this.customerName = customerName;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
        this.quantity = quantity;
        this.status = status;
    }
// Getter và Setter cho các thuộc tính

    public long getRentalId() {
        return rentalId;
    }

    public void setRentalId(long rentalId) {
        this.rentalId = rentalId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getRentDate() {
        return rentDate;
    }

    public void setRentDate(Timestamp rentDate) {
        this.rentDate = rentDate;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// toString
    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", movieTitle='" + movieTitle + '\'' +
                ", customerName='" + customerName + '\'' +
                ", rentDate=" + rentDate +
                ", dueDate=" + dueDate +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}
