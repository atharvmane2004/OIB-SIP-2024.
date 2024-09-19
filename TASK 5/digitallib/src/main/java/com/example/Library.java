package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class User {
    Scanner sc = new Scanner(System.in);
    String userName;
    String bookName;
    int issues;
    Map<String, List<String>> issueHashMap = new HashMap<String, List<String>>();

    void userLogin() {
        try {

            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String userAuthQuery = "SELECT * FROM USERS WHERE username = ? AND password = ?";
            PreparedStatement userAuthPreparedStatement = conn.prepareStatement(userAuthQuery);

            System.out.print("Enter User Name : ");
            userName = sc.nextLine();
            System.out.print("Enter Password : ");
            String passWord = sc.nextLine();

            userAuthPreparedStatement.setString(1, userName);
            userAuthPreparedStatement.setString(2, passWord);

            ResultSet rs = userAuthPreparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("----------------- Login Sucessfull!!! -----------------");
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

        while (choice != 7) {
            System.out.println();
            System.out.println("*****************************************************");
            System.out.println("----------------------MAIN MENU----------------------");
            System.out.println("1. View Books");
            System.out.println("2. Search For A Book");
            System.out.println("3. Return A Book");
            System.out.println("4. Issue a Book");
            System.out.println("5. Search Using Catergory");
            System.out.println("6. Email us");
            System.out.println("7. Back");
            System.out.println("*****************************************************");
            System.out.println();

            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    viewBook();
                    break;
                case 2:
                    searchBook();
                case 3:
                    returnBook();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    searchByCategory();
                    break;
                case 6:
                    emailUs();
                    break;
                case 7:
                    digitalLibrary.main(null);
                    break;
                default:
                    System.out.println("INVALID OPTION . PLEASE TRY AGAIN");
                    break;

            }
        }
    }

    void viewBook() {
        try {

            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String viewBookQuery = "SELECT * FROM books";
            PreparedStatement viewBookPreparedStatement = conn.prepareStatement(viewBookQuery);

            ResultSet rs = viewBookPreparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("name");
                String authorName = rs.getString("author");
                String category = rs.getString("category");
                System.out.println();
                System.out.println("ID       :   " + id);
                System.out.println("Name     : " + bookName);
                System.out.println("Author   : " + authorName);
                System.out.println("Category : " + category);

            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void searchByCategory() {

        System.out.println("---------------- CATEGORIES ----------------");
        System.out.println("1.  Algorithms");
        System.out.println("2.  Programming");
        System.out.println("3.  Software Engineering");
        System.out.println("4.  Computer Systems");
        System.out.println("5.  Computer Science");
        System.out.println("6.  Mathematics");
        System.out.println("7.  Theory of Computation ");
        System.out.println("8.  Artificial Intelligence");
        System.out.println("9.  Networking ");
        System.out.println("10. Operating Systems");
        System.out.println("11. Compilers");
        System.out.println("12. Computer Architecture");
        System.out.println("13. Software Architecture");
        System.out.println("14. DevOps");
        System.out.println("15. Data Systems");
        System.out.println("16. Machine Learning");
        System.out.println("17. Data Science");
        System.out.println("18. Data Science Insights");

        Scanner sc = new Scanner(System.in);

        try {
            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String searchCategory = "SELECT * FROM books WHERE category = ?";

            PreparedStatement searchCatPreparedStatement = conn.prepareStatement(searchCategory);

            System.out.print("Enter Category: ");
            String category = sc.nextLine();

            searchCatPreparedStatement.setString(1, category);

            ResultSet rs = searchCatPreparedStatement.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String bookName = rs.getString("name");
                String authorName = rs.getString("author");
                System.out.println();
                System.out.println("ID       :   " + id);
                System.out.println("Name     :   " + bookName);
                System.out.println("Author   :   " + authorName);
            }

            if (!found) {
                System.out.println("No books found in the '" + category + "' category.");
            }
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void searchBook() {
        Scanner sc = new Scanner(System.in);

        try {
            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String books = "SELECT * FROM books WHERE name = ?";

            PreparedStatement searchBookPrepStatement = conn.prepareStatement(books);

            System.out.print("Enter Books Name: ");
            String bookNameStr = sc.nextLine();

            searchBookPrepStatement.setString(1, bookNameStr);

            ResultSet rs = searchBookPrepStatement.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String bookName = rs.getString("name");
                String authorName = rs.getString("author");
                String category = rs.getString("category");
                System.out.println();
                System.out.println("ID       :   " + id);
                System.out.println("Name     :   " + bookName);
                System.out.println("Author   :   " + authorName);
                System.out.println("Category :   " + category);

            }

            if (!found) {
                System.out.println("No books found of " + bookNameStr + "name");
            }
            conn.close();
            displayUserMenu();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void returnBook() {
        System.out.println("------------------------------- Books Issuesed -------------------------------");
        for (Map.Entry<String, List<String>> entry : issueHashMap.entrySet()) {
            String userName = entry.getKey();
            List<String> bookIssued = entry.getValue();

            System.out.println("Username : " + userName);
            System.out.println("Book Issued : ");
            for (String book : bookIssued) {
                System.out.println(" - " + book);
            }
            System.out.println();

        }
    }

    void issueBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Books Details to be Issued : ");

        System.out.print("Enter Book Id : ");
        int id = sc.nextInt();

        try {
            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String issueStr = "SELECT name FROM books WHERE id = ?";

            PreparedStatement getBookName = conn.prepareStatement(issueStr);
            getBookName.setInt(1, id);

            ResultSet rs = getBookName.executeQuery();
            if (rs.next()) {
                bookName = rs.getString("name");

                if (!issueHashMap.containsKey(userName)) {
                    issueHashMap.put(userName, new ArrayList<>());
                }

                List<String> booksIssued = issueHashMap.get(userName);
                booksIssued.add(bookName);
                System.out.println("Book '" + bookName + "' has been issued to " + userName);
            } else {
                System.out.println("No book found with the given ID.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hashmap: " + issueHashMap.get(bookName));
        displayUserMenu();
    }

    void emailUs() {
        System.out.print("Enter Your Email Id : ");
        String email = sc.nextLine();
        sc.nextLine();

        System.out.println("Enter Your Query :(PRESS ENTER TO SEND AFTER WRING YOUR QUERY ) ");
        String message = sc.nextLine();

        System.out.println();

        System.out.println();
        System.out.println("*************************************************************************************");
        System.out.println("Your Query Has Been Recieved Sucessfully. We will get back to you as soon as possible");
        System.out.println("*************************************************************************************");
        System.out.println();
    }

}

class Admin {

    Scanner sc = new Scanner(System.in);
    int flag = 0;

    void adminLogin() {
        try {

            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String adminname = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, adminname, password);

            String adminAuthQuery = "SELECT * FROM admins WHERE username = ? AND password = ?";
            PreparedStatement adminAuthPreparedStatement = conn.prepareStatement(adminAuthQuery);

            System.out.print("Enter Name : ");
            String adminName = sc.nextLine();
            System.out.print("Enter Password : ");
            String passWord = sc.nextLine();

            adminAuthPreparedStatement.setString(1, adminName);
            adminAuthPreparedStatement.setString(2, passWord);

            ResultSet rs = adminAuthPreparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("----------------- Login Sucessfull!!! -----------------");
                displayadminMenu();
            } else {
                System.out.println("Invalid Credentials");
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }

    void displayadminMenu() {
        int choice = 0;

        while (choice != 4) {
            System.out.println();
            System.out.println("*****************************************************");
            System.out.println("----------------------MAIN MENU----------------------");
            System.out.println("1. ADD BOOK");
            System.out.println("2. DELETE BOOK");
            System.out.println("3. MODIFY BOOK RECORD");
            System.out.println("4. BACK");
            System.out.println("*****************************************************");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    modifyRecord();
                    break;
                case 4:
                    digitalLibrary.main(null);
                default:
                    break;
            }
        }
    }

    void addBook() {
        Scanner sc = new Scanner(System.in);

        try {
            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String addBookStmt = "INSERT INTO books VALUES(?,?,?,?)";
            String bookNameStmt = "SELECT name from books WHERE id = ?";

            PreparedStatement addBkPrepStmt = conn.prepareStatement(addBookStmt);
            PreparedStatement selecPreparedStatement = conn.prepareStatement(bookNameStmt);

            System.out.print("Enter Book Id : ");
            int id = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter Book Name : ");
            String bookName = sc.nextLine();

            System.out.print("Enter Author Name : ");
            String authorName = sc.nextLine();

            System.out.print("Enter Category : ");
            String category = sc.nextLine();

            addBkPrepStmt.setInt(1, id);
            addBkPrepStmt.setString(2, bookName);
            addBkPrepStmt.setString(3, authorName);
            addBkPrepStmt.setString(4, category);

            int rowsAffected = addBkPrepStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book " + bookName + " added to the database.");
            } else {
                System.out.println("Failed to add the book.");
            }
            ;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteBook() {
        Scanner sc = new Scanner(System.in);

        try {
            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String bookName = "SELECT name from books WHERE id = ?";
            String deleteBookStmt = "DELETE FROM books WHERE id = ?";

            PreparedStatement deleteBkPrepStmt = conn.prepareStatement(deleteBookStmt);
            PreparedStatement selecPreparedStatement = conn.prepareStatement(bookName);
            System.out.print("Enter Book Id : ");
            int id = sc.nextInt();
            deleteBkPrepStmt.setInt(1, id);

            selecPreparedStatement.setInt(1, id);

            ResultSet rs = selecPreparedStatement.executeQuery();
            int rowsAffected = deleteBkPrepStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book deleted from the database.");
            } else {
                System.out.println("Failed to add the book.");
            }
            ;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void modifyRecord() {
        Scanner sc = new Scanner(System.in);

        try {
            String url = "jdbc:mysql://localhost:3306/digitallibrary";
            String username = "root";
            String password = "7030503374";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);

            String updateIdBookStmt = "UPDATE books SET id = ? where id =?";
            String updateNameBookStmt = "UPDATE books SET name = ? where id =?";
            String updateAuthorBookStmt = "UPDATE books SET author = ? where id =?";
            String updateCategoryBookStmt = "UPDATE books SET category = ? where id =?";

            PreparedStatement updateIdBkPrepStmt = conn.prepareStatement(updateIdBookStmt);
            PreparedStatement updateNameBkPrepStmt = conn.prepareStatement(updateNameBookStmt);
            PreparedStatement updateAuthorBkPrepStmt = conn.prepareStatement(updateAuthorBookStmt);
            PreparedStatement updateCategoryBkPrepStmt = conn.prepareStatement(updateCategoryBookStmt);

            System.out.println("--------------------- UPDATE MENU ---------------------");
            System.out.println("1. ID");
            System.out.println("2. NAME");
            System.out.println("3. AUTHOR");
            System.out.println("4. CATEGOTY");
            System.out.println("5. BACK");
            System.out.println("Enter Your Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Id to be Updated(OLD ID): ");
                    int oldId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new book ID : ");
                    int newId = sc.nextInt();

                    updateIdBkPrepStmt.setInt(2, oldId);
                    updateIdBkPrepStmt.setInt(1, newId);
                    int rowsAffected = updateIdBkPrepStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Book ID updated to the database.");
                    } else {
                        System.out.println("Failed to update the book ID.");
                    }

                    break;
                case 2:
                    System.out.print("Enter Book Id to be Updated : ");
                    int id2 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Book Name : ");
                    String bookName2 = sc.nextLine();

                    updateNameBkPrepStmt.setString(1, bookName2);
                    updateNameBkPrepStmt.setInt(2, id2);
                    int rowsAffected2 = updateNameBkPrepStmt.executeUpdate();

                    if (rowsAffected2 > 0) {
                        System.out.println("Book Name updated to the database.");
                    } else {
                        System.out.println("Failed to update the book Name.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Book Id to be Updated : ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Author Name : ");
                    String Author = sc.nextLine();

                    updateAuthorBkPrepStmt.setString(1, Author);
                    updateAuthorBkPrepStmt.setInt(2, id3);
                    int rowsAffected3 = updateAuthorBkPrepStmt.executeUpdate();

                    if (rowsAffected3 > 0) {
                        System.out.println("Book Author updated to the database.");
                    } else {
                        System.out.println("Failed to update the book NameAuthor");
                    }
                    break;
                case 4:
                    System.out.print("Enter Book Id to be Updated : ");
                    int id4 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Catergory Name : ");
                    String Catergory = sc.nextLine();

                    updateCategoryBkPrepStmt.setString(1, Catergory);
                    updateCategoryBkPrepStmt.setInt(2, id4);
                    int rowsAffected4 = updateCategoryBkPrepStmt.executeUpdate();

                    if (rowsAffected4 > 0) {
                        System.out.println("Book Category updated to the database.");
                    } else {
                        System.out.println("Failed to update the book Category.");
                    }
                    break;
                case 5:
                    displayadminMenu();
                    break;

                default:
                    System.out.println("Invalid Choice . Please Try again");
                    modifyRecord();
                    break;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}

class digitalLibrary {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin adminObj = new Admin();
        User userObj = new User();
        System.out.println();
        System.out.println("**********************************************************");
        System.out.println("----------------WELCOME TO DIGITAL LIBRARY----------------");
        System.out.println("**********************************************************");
        System.out.println();

        int choice = 0;
        while (choice != 3) {

            System.out.println("----------------------MAIN MENU----------------------");
            System.out.println("1. LOGIN AS ADMIN");
            System.out.println("2. LOGIN AS USER");
            System.out.println("3. EXIT");
            System.out.println();
            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminObj.adminLogin();
                    break;
                case 2:
                    userObj.userLogin();
                    break;
                case 3:
                    System.out.println("THANK YOU FOR VISTIONG OUR LIBRARY");
                    System.exit(0);
                default:
                    System.out.println("INVALID OPTION . PLEASE TRY AGAIN");
                    break;
            }
        }
    }
}
