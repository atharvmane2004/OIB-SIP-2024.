package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

class User {
    Scanner sc = new Scanner(System.in);
    int PRN;
    int trainNo;
    String trainName;
    String classType;
    int seatNo;
    String date;
    String toPlace;
    String fromPlace;

    int generatePRN() {
        Random random = new Random();
        int PRN = random.nextInt(10000000, 99999999);
        return 10000000 + PRN;
    }

    void register() {
        try {

            String url = "jdbc:mysql://localhost:3306/onlineReservationSystem";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String userRegisterQuery = "INSERT INTO user VALUES(?,?,?)";
            PreparedStatement userRegisterPreparedStatement = conn.prepareStatement(userRegisterQuery);

            System.out.print("Enter Username : ");
            String userName = sc.nextLine();

            System.out.print("Enter Password : ");
            String passWord = sc.nextLine();

            PRN = generatePRN();

            userRegisterPreparedStatement.setInt(1, PRN);
            userRegisterPreparedStatement.setString(2, userName);
            userRegisterPreparedStatement.setString(3, passWord);

            int rowsAffected = userRegisterPreparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("**********************************************************************");
                System.out.println("----------------- User Registered Sucessfully!!! -----------------");
                System.out.println("Your PRN number is : " + PRN);
                System.out.println("**********************************************************************");

            } else {
                System.out.println("---------------------- User Not Registered  ----------------------");
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void login() {
        try {

            String url = "jdbc:mysql://localhost:3306/onlineReservationSystem";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String userAuthQuery = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement userAuthPreparedStatement = conn.prepareStatement(userAuthQuery);

            System.out.print("Enter User Name : ");
            String userName = sc.nextLine();
            System.out.print("Enter Password : ");
            String passWord = sc.nextLine();

            userAuthPreparedStatement.setString(1, userName);
            userAuthPreparedStatement.setString(2, passWord);

            ResultSet rs = userAuthPreparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("**********************************************************************");
                System.out.println("-------------------------- Login Sucessfull!!! -----------------------");
                System.out.println("**********************************************************************");

                displayUserMenu();
            } else {

                System.out.println("Invalid Credentials");
            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void displayUserMenu() {
        int choice = 0;

        while (choice != 3) {
            System.out.println();
            System.out.println("*****************************************************");
            System.out.println("----------------------MAIN MENU----------------------");
            System.out.println("1. Book A Seat");
            System.out.println("2. Cancel A Seat");
            System.out.println("3. Back");
            System.out.println("*****************************************************");
            System.out.println();

            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    bookSeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    onlineReservationSystem.main(null);
                    break;
                default:
                    System.out.println("INVALID OPTION . PLEASE TRY AGAIN");
                    break;

            }
        }
    }

    void bookSeat() {
        System.out.print("Enter PRN :");
        PRN = sc.nextInt();

        System.out.print("Enter Train Number : ");
        trainNo = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Train Name : ");
        trainName = sc.nextLine();

        System.out.println("Enter Class Type(JUST TYPE SHORT FORM): ");
        System.out.println();
        System.out.println("--------- AC --------");
        System.out.println("AC Executive Chair Class(EC)");
        System.out.println("AC First Class(FC)");
        System.out.println("AC Two - Tier  Class(2AC)");
        System.out.println("AC Three - Tier  Class(3AC)");
        System.out.println();
        System.out.println("------ NON -AC ------");
        System.out.println("First Class(FC)");
        System.out.println("AC Chair Class(CC)");
        System.out.println("Sleeper Class(SL)");
        System.out.println("Second Class(2S)");
        System.out.println("Unreserved / General Class(2S)");
        classType = sc.nextLine();

        System.out.print("Enter Seat no : ");
        seatNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Date of Journey(YYYY-MM-DD) : ");
        date = sc.nextLine().trim();

        Date dateSQL = Date.valueOf(date);

        System.out.print("Enter From(place) : ");
        fromPlace = sc.nextLine();

        System.out.print("Enter To(Place) : ");
        toPlace = sc.nextLine();

        try {

            String url = "jdbc:mysql://localhost:3306/onlineReservationSystem";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String trainBookQuery = "INSERT INTO train VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement trainBookPrepStmt = conn.prepareStatement(trainBookQuery);

            trainBookPrepStmt.setInt(1, PRN);
            trainBookPrepStmt.setInt(2, trainNo);
            trainBookPrepStmt.setString(3, trainName);
            trainBookPrepStmt.setInt(4, seatNo);
            trainBookPrepStmt.setString(5, classType);
            trainBookPrepStmt.setDate(6, dateSQL);
            trainBookPrepStmt.setString(7, fromPlace);
            trainBookPrepStmt.setString(8, toPlace);

            int rowsAffected = trainBookPrepStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("**************************************************************************");
                System.out.println("-------------------------- SEAT BOOKED SUCESSFULLY -----------------------");
                System.out.println("**************************************************************************");
                System.out.println();
                System.out.println();
                generateTicket();
                displayUserMenu();
            } else {

                System.out.println("Seat Not Booked Check Details Again");
            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void cancelSeat() {
        try {

            String url = "jdbc:mysql://localhost:3306/onlineReservationSystem";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            System.out.print("Enter PRN :");
            PRN = sc.nextInt();
            sc.nextLine();
            
            String cancelBookQuery = "DELETE FROM train WHERE PRN = ?";
            PreparedStatement cancelBookPrepStmt = conn.prepareStatement(cancelBookQuery);
            cancelBookPrepStmt.setInt(1, PRN);

            boolean flag = generateDetails(PRN);
            if(flag == true){
            System.out.print("Do You want To Confirm Your Ticket Cancellation ? (y/n) ");
            String opt = sc.nextLine();
            if (opt.equalsIgnoreCase("y")) {

                int rowsAffected = cancelBookPrepStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("****************************************************************************");
                    System.out.println("-------------------------- SEAT CANCELLED SUCESSFULLY -----------------------");
                    System.out.println("****************************************************************************");
                    System.out.println();
                    System.out.println();
                    displayUserMenu();
                }
            } else {

                System.out.println("Reservation Not Cancelled Check Details Again");
            }
            conn.close();
        }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    boolean generateDetails(int PRN) {
   
        try {

            String url = "jdbc:mysql://localhost:3306/onlineReservationSystem";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            int found = 0;
            String getDetailsQuery = "SELECT * FROM train WHERE PRN = ?";
            PreparedStatement getDetailPreparedStatement = conn.prepareStatement(getDetailsQuery);
            getDetailPreparedStatement.setInt(1, PRN);

            ResultSet rs = getDetailPreparedStatement.executeQuery();
            while (rs.next()) {
                found = 1;
                int trainNo = rs.getInt("trainNo");
                String trainName = rs.getString("trainName");
                String classType = rs.getString("classType");
                int seatNo = rs.getInt("seatNo");
                String date = rs.getString("date");
                String toPlace = rs.getString("toPlace");
                String fromPlace = rs.getString("fromPlace");

                System.out.println("******************************************************");
                System.out.println("----------------------- DETAILS -----------------------");
                System.out.println("******************************************************");
                System.out.println("PRN : " + PRN);
                System.out.println("Train Name : " + trainName + "      " + " Train Number : " + trainNo);
                System.out.println("Seat Number : " + seatNo + "          " + " Class Type : " + classType);
                System.out.println("Date : " + date);
                System.out.println("FROM : " + fromPlace + "            " + " To : " + toPlace);

                System.out.println("******************************************************");
                conn.close();
            }

            if(found != 1){
                System.out.println("No Seat Booked With Provided PRN");
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return true;

    }

    void generateTicket() {
        System.out.println("******************************************************");
        System.out.println("----------------------- TICKET -----------------------");
        System.out.println("******************************************************");
        System.out.println("PRN : " + PRN);
        System.out.println("Train Name : " + trainName + "      " + " Train Number : " + trainNo);
        System.out.println("Seat Number : " + seatNo + "          " + " Class Type : " + classType);
        System.out.println("Date : " + date);
        System.out.println("FROM : " + fromPlace + "            " + " To : " + toPlace);

        System.out.println("******************************************************");
    }
}

public class onlineReservationSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        User userObj = new User();
        System.out.println();
        System.out.println("**********************************************************************");
        System.out.println("---------------- WELCOME TO ONLINE RESERVATION SYSTEM ----------------");
        System.out.println("**********************************************************************");
        System.out.println();

        int choice = 0;
        while (choice != 3) {

            System.out.println("---------------------------- MAIN MENU ----------------------------");
            System.out.println("1. REGISTER ");
            System.out.println("2. LOGIN");
            System.out.println("3. EXIT");
            System.out.println();
            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    userObj.register();
                    break;
                case 2:
                    userObj.login();
                    break;
                case 3:
                    System.out.println("THANK YOU");
                    System.exit(0);
                default:
                    System.out.println("INVALID OPTION . PLEASE TRY AGAIN");
                    break;
            }
        }
    }
}