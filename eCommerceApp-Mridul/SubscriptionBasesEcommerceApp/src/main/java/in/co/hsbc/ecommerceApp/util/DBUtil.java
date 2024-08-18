package in.co.hsbc.ecommerceApp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
//import static java.lang.Class.forName;
    public class DBUtil  {
//         Class.forName("com.mysql.cj.jdbc.Driver");
        private static final String URL ="jdbc:mysql://localhost:3306/ecommerce";
        private static final String USER = "root";
        private static final String PASSWORD = "root";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }


