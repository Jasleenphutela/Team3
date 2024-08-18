package in.co.hsbc.ecommerceApp.adminDao.Impl;

import in.co.hsbc.ecommerceApp.adminDao.AdminDao;
import in.co.hsbc.ecommerceApp.entity.Product;
import in.co.hsbc.ecommerceApp.entity.Subscriptions;
import in.co.hsbc.ecommerceApp.model.exceptions.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminJdbcDaoImpl implements AdminDao {
    private Connection con;
    private Scanner sc;
    private int result;

    @Override
    public int addProduct(Product p) throws ClassNotFoundException, ProductNotFoundException, ProductAlreadyPresentException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1 = "SELECT * FROM ecommerceappdb.products WHERE ProductId=?";
        String query2 = "INSERT INTO ecommerceappdb.products VALUES (?,?,?)";
        sc=new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query1);
             PreparedStatement pstmt2 = conn.prepareStatement(query2)) {

            int id=p.getId();
            pstmt.setInt(1,id);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next())
                throw new ProductAlreadyPresentException();

            String name=p.getName();
            double price=p.getPrice();
            pstmt2.setInt(1,id);
            pstmt2.setString(2, name);
            pstmt2.setDouble(3,price);

            result=pstmt2.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public int alterProduct(Product p) throws ClassNotFoundException, ProductNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1="SELECT * FROM ecommerceappdb.products WHERE ProductId=?";
        String query = "UPDATE ecommerceappdb.products SET ProductName=?,ProductPrice=? WHERE ProductId=?";
        sc=new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            int id=p.getId();
            pstmt1.setInt(1,id);
            ResultSet rs =pstmt1.executeQuery();
            if(!rs.next())
                throw new ProductNotFoundException();

            String new_name=p.getName();
            double new_price=p.getPrice();
            pstmt.setString(1, new_name);
            pstmt.setDouble(2, new_price);
            pstmt.setInt(3,id);

            result=pstmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public int eradicateProduct(int id) throws ProductNotFoundException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1 = "SELECT * FROM ecommerceappdb.products WHERE ProductId=?";
        String query = "DELETE FROM ecommerceappdb.products WHERE ProductId=?";
        sc=new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt1.setInt(1,id);
            ResultSet rs=pstmt1.executeQuery();
            if(!rs.next())
                throw new ProductNotFoundException();

            pstmt.setInt(1,id);

            result=pstmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public int insertSubscription(Subscriptions subscriptions, int id) throws ClassNotFoundException, SubscriptionAlreadyExistsException, SubscriptionNotFoundException {
        LocalDate expiry=subscriptions.getExpiry();
        String subscriptionType=subscriptions.getSubscriptionType();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1 = "SELECT * FROM ecommerceappdb.subscriptions WHERE ProductId=? AND SubscriptionType=?";
        String query = "INSERT INTO ecommerceappdb.subscriptions VALUES (?,?,?,?)";
        sc=new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt1.setInt(1,id);
            pstmt1.setString(2,subscriptionType);
            ResultSet rs=pstmt1.executeQuery();

            if(rs.next())
                throw new SubscriptionAlreadyExistsException();

            pstmt.setInt(1,id);
            pstmt.setString(2,subscriptionType);
            pstmt.setString(3,"active");
            pstmt.setDate(4, Date.valueOf(expiry));


            result=pstmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new SubscriptionNotFoundException();
        }
    }

    @Override
    public List<Subscriptions> listAllSubscriptions() throws ClassNotFoundException, SubscriptionNotFoundException {
        List<Subscriptions> subscriptionsList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1 = "SELECT * FROM ecommerceappdb.subscriptions";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1)){
            ResultSet rs=pstmt1.executeQuery();
            if(!rs.next()) {
                throw new SubscriptionNotFoundException();
            }
           do{
                int id=rs.getInt(1);
                String type=rs.getString(2);
                String status=rs.getString(3);
                LocalDate date= rs.getDate(4).toLocalDate();
                Subscriptions subscriptions=new Subscriptions(type,status,date,id);
                subscriptionsList.add(subscriptions);
            }while(rs.next());
            return subscriptionsList;
        } catch (SQLException e) {
            throw new SubscriptionNotFoundException();
        }
    }

        @Override
    public int deactivateSubscription(String subscriptionType, int id) throws ClassNotFoundException, SubscriptionAlreadyInactiveException, SubscriptionNotFoundException {

        LocalDate expiry=LocalDate.now();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1 = "SELECT * FROM ecommerceappdb.subscriptions WHERE ProductId=? AND SubscriptionType=? AND SubscriptionStatus=?";
        String query = "UPDATE ecommerceappdb.subscriptions SET SubscriptionStatus=? WHERE ProductId=? AND SubscriptionType=?";
        sc=new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt1.setInt(1,id);
            pstmt1.setString(2,subscriptionType);
            pstmt1.setString(3,"inactive");
            ResultSet rs=pstmt1.executeQuery();

            if(rs.next())
                throw new SubscriptionAlreadyInactiveException();

            pstmt.setString(1,"inactive");
            pstmt.setInt(2,id);
            pstmt.setString(3,subscriptionType);

            result=pstmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new SubscriptionNotFoundException();
        }
    }

    @Override
    public int activateSubscription(String subscriptionType, int id) throws SubscriptionAlreadyActiveException, SubscriptionNotFoundException, ClassNotFoundException {
        LocalDate expiry=LocalDate.now();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String query1 = "SELECT * FROM ecommerceappdb.subscriptions WHERE ProductId=? AND SubscriptionType=? AND SubscriptionStatus=?";
        String query = "UPDATE ecommerceappdb.subscriptions SET SubscriptionStatus=? WHERE ProductId=? AND SubscriptionType=?";
        sc=new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt1.setInt(1,id);
            pstmt1.setString(2,subscriptionType);
            pstmt1.setString(3,"active");
            ResultSet rs=pstmt1.executeQuery();

            if(rs.next())
                throw new SubscriptionAlreadyActiveException();

            pstmt.setString(1,"active");
            pstmt.setInt(2,id);
            pstmt.setString(3,subscriptionType);

            result=pstmt.executeUpdate();
            return result;

        } catch (SQLException e) {
            throw new SubscriptionNotFoundException();
        }
    }


}
