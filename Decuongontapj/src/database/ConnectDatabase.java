package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {

    // Địa chỉ kết nối đến MySQL và tên Database
    private static final String URL = "jdbc:mysql://localhost:3306/db_movie_rental";

    // Tên tài khoản MySQL
    private static final String USER = "root";

    // Mật khẩu tài khoản MySQL
    private static final String PASSWORD = "12345678";

    // Phương thức tạo kết nối đến Database
    public static Connection getConnection() {
        try {
            // Thực hiện kết nối bằng URL, USER và PASSWORD
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Thông báo khi kết nối thành công
            System.out.println("Connected to the database successfully!");

            // Trả về đối tượng kết nối
            return conn;

        } catch (SQLException e) {
            // Thông báo khi kết nối thất bại
            System.out.println("Connect database failed!");

            // Hiển thị chi tiết lỗi
            e.printStackTrace();

            // Trả về null nếu kết nối thất bại
            return null;
        }
    }
}