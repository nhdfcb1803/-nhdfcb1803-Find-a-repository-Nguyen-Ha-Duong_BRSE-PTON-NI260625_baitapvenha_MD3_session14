package main;
import java.util.Scanner;
import database.ConnectDatabase;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalManager {
    // Hiển thị danh sách tất cả phiếu thuê phim
    public void getAllRentals() {

        // Gọi Stored Procedure để lấy danh sách phiếu thuê
        String sql = "{CALL sp_get_all_rentals()}";

        // Kết nối Database và thực hiện truy vấn
        try (Connection conn = ConnectDatabase.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            // Duyệt từng dòng dữ liệu trả về
            while (rs.next()) {

                // Tạo đối tượng Rental từ dữ liệu trong Database
                Rental rental = new Rental(
                        rs.getLong("rental_id"),
                        rs.getString("movie_title"),
                        rs.getString("customer_name"),
                        rs.getTimestamp("rent_date"),
                        rs.getTimestamp("due_date"),
                        rs.getInt("quantity"),
                        rs.getString("status")
                );
                // Hiển thị thông tin phiếu thuê
                System.out.println(rental);
            }
        } catch (SQLException e) {
            // Hiển thị lỗi nếu truy vấn thất bại
            e.printStackTrace();
        }
    }

    // Thêm phiếu thuê phim
    public void addRental(Rental rental) {
        // Gọi Stored Procedure để thêm phiếu thuê
        String sql = "{CALL sp_add_rental(?, ?, ?, ?, ?, ?)}";

        // Kết nối Database và thực hiện truy vấn
        try (Connection conn = ConnectDatabase.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Thiết lập các tham số cho Stored Procedure
            cs.setString(1, rental.getMovieTitle());
            cs.setString(2, rental.getCustomerName());
            cs.setTimestamp(3, rental.getRentDate());
            cs.setTimestamp(4, rental.getDueDate());
            cs.setInt(5, rental.getQuantity());
            cs.setString(6, rental.getStatus());

            // Thực hiện truy vấn
            cs.executeUpdate();
            System.out.println("Rental added successfully!");
        } catch (SQLException e) {
            // Hiển thị lỗi nếu truy vấn thất bại
            e.printStackTrace();
        }
    }

    // Cập nhật phiếu thuê phim
    public void updateRental(Rental rental) {
        // Gọi Stored Procedure để cập nhật phiếu thuê
        String sql = "{CALL sp_update_rental(?, ?, ?, ?, ?, ?, ?)}";

        // Kết nối Database và thực hiện truy vấn
        try (Connection conn = ConnectDatabase.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Thiết lập các tham số cho Stored Procedure
            cs.setLong(1, rental.getRentalId());
            cs.setString(2, rental.getMovieTitle());
            cs.setString(3, rental.getCustomerName());
            cs.setTimestamp(4, rental.getRentDate());
            cs.setTimestamp(5, rental.getDueDate());
            cs.setInt(6, rental.getQuantity());
            cs.setString(7, rental.getStatus());

            // Thực hiện truy vấn
            cs.executeUpdate();
            System.out.println("Rental updated successfully!");
        } catch (SQLException e) {
            // Hiển thị lỗi nếu truy vấn thất bại
            e.printStackTrace();
        }
    }

    // Xóa phiếu thuê phim
    public void deleteRental(long rentalId) {

        // Yêu cầu người dùng xác nhận trước khi xóa
        Scanner scanner = new Scanner(System.in);

        System.out.print("Are you sure you want to delete rental ID "
                + rentalId + "? (Y/N): ");

        String confirm = scanner.nextLine();

        // Nếu người dùng không xác nhận thì hủy thao tác
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Delete cancelled!");
            return;
        }

        // Gọi Stored Procedure để xóa phiếu thuê
        String sql = "{CALL sp_delete_rental(?)}";

        // Kết nối Database và thực hiện truy vấn
        try (Connection conn = ConnectDatabase.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Thiết lập tham số cho Stored Procedure
            cs.setLong(1, rentalId);

            // Thực hiện truy vấn
            int rowsAffected = cs.executeUpdate();

            // Kiểm tra kết quả xóa
            if (rowsAffected > 0) {
                System.out.println("Rental deleted successfully!");
            } else {
                System.out.println("Rental ID not found!");
            }

        } catch (SQLException e) {
            // Hiển thị lỗi nếu truy vấn thất bại
            e.printStackTrace();
        }
    }

    // Tìm kiếm phiếu thuê phim theo tên khách hàng
    public void searchRentalsByCustomerName(String customerName) {

        // Gọi Stored Procedure để tìm kiếm phiếu thuê
        String sql = "{CALL sp_get_rentals_by_customer(?)}";

        // Kết nối Database và thực hiện truy vấn
        try (Connection conn = ConnectDatabase.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Thiết lập tham số cho Stored Procedure
            cs.setString(1, customerName);

            // Thực hiện truy vấn và nhận kết quả
            try (ResultSet rs = cs.executeQuery()) {

                // Kiểm tra có tìm thấy dữ liệu hay không
                boolean found = false;

                // Duyệt từng dòng dữ liệu trả về
                while (rs.next()) {
                    found = true;

                    // Tạo đối tượng Rental từ dữ liệu trong Database
                    Rental rental = new Rental(
                            rs.getLong("rental_id"),
                            rs.getString("movie_title"),
                            rs.getString("customer_name"),
                            rs.getTimestamp("rent_date"),
                            rs.getTimestamp("due_date"),
                            rs.getInt("quantity"),
                            rs.getString("status")
                    );

                    // Hiển thị thông tin phiếu thuê
                    System.out.println(rental);
                }

                // Thông báo nếu không tìm thấy dữ liệu
                if (!found) {
                    System.out.println("No rentals found for this customer!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
