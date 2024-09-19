import java.util.*;

class User {

    String username;
    String pass;
    HashMap<Integer, Object> userHashMap = new HashMap<Integer, Object>();

    Bank bankObj = new Bank(userHashMap);
    Scanner sc = new Scanner(System.in);

    User() {

    }

    User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    void login(int accNo, String username, String password) {
        if (userHashMap.containsKey(accNo)) {
            User storedUser = (User) userHashMap.get(accNo);
            if (storedUser.username.equals(username) && storedUser.pass.equals(password)) {
                System.out.println("-----------------LOGIN SUCCESSFUL-----------------");
                bankObj.serviceMenu();
            } else {
                System.out.println("INVALID CREDENTIALS. PLEASE TRY AGAIN.");
            }
        } else {
            System.out.println("INVALID ACCOUNT NUMBER. PLEASE TRY AGAIN.");
        }
    }

    void register(User user) {
        int accNo = generateAccNo();

        if (!userHashMap.containsKey(accNo)) {
            userHashMap.put(accNo, user);
            System.out.println("---------------USER REGISTERED SUCCESSFULLY---------------");
            System.out.println("YOUR NEW ACCOUNT NUMBER IS : " + accNo);
        }
    }

    int generateAccNo() {
        Random random = new Random();
        int accNo = random.nextInt(90000000);
        return 1000000 + accNo;

    }
}

class Bank {
    Scanner sc = new Scanner(System.in);
    int choice2 = 0;
    ArrayList<Integer> transactionHistoryList = new ArrayList<Integer>();
    int balance = 0;

    HashMap <Integer,Object> bankUserHashMap;

    Bank(HashMap <Integer,Object>userHashMap){
        this.bankUserHashMap = userHashMap;
    }
    void serviceMenu() {
        while (true) {
            System.out.println("-----------------------MENU-----------------------");
            System.out.println("1. TRANSACTION HISTORY");
            System.out.println("2. WITHDRAW");
            System.out.println("3. DEPOSIT");
            System.out.println("4. TRANSFER");
            System.out.println("5. QUIT");
            System.out.print("CHOOSE OPERATION YOU WANT TO PERFORM :");

            choice2 = sc.nextInt();
            switch (choice2) {
                case 1:
                    transactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    quit();
                    break;

                default:
                    System.out.println("INVALID OPTION.PLEASE TRY AGAIN...");
                    serviceMenu();
            }

        }
    }

    void transactionHistory() {
        if (!transactionHistoryList.isEmpty()) {
            System.out.println("*************** TRANSACTION HISTORY ***************");
            for (Integer i : transactionHistoryList) {
                if (i > 0) {
                    System.out.println("Rs " + i + " CREDITED (+)");
                } else {
                    System.out.println("Rs " + (-i) + " DEBITED (-)");
                }
            }
            System.out.println("****************************************************");
            System.out.println("Balance : " + balance);
            System.out.println("****************************************************");
        } else {
            System.out.println("****************************************************");
            System.out.println("No Transactions Carried out yet");
            System.out.println("****************************************************");

        }
    }

    void withdraw() {
        System.out.print("Enter Amount to Withdraw : ");
        int amount = sc.nextInt();
        if (balance == 0) {
            System.out.println("****************************************************");
            System.out.println("Deposit Some Amount First");
            System.out.println("Current balance : " + balance);
            System.out.println("****************************************************");

        } else if (balance < amount || amount > 0) {
            balance -= amount;
            System.out.println("****************************************************");
            System.out.println("Rs " + amount + " has been debited from your account!!!");
            System.out.println("Balance After Transaction : " + balance);
            System.out.println("****************************************************");
            transactionHistoryList.add(-amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    void deposit() {
        System.out.print("Enter Amount to Deposit : ");
        int amount = sc.nextInt();
        if (amount > 0) {
            balance += amount;
            System.out.println("****************************************************");
            System.out.println("Rs " + amount + " has been credited to  your account!!!");
            System.out.println("Balance After Transaction : " + balance);
            System.out.println("****************************************************");
            transactionHistoryList.add(amount);
        }
    }

    void transfer() {
        System.out.println("Enter Account Number to which Amount is to be transferred : ");
        int accNo = sc.nextInt();
        System.out.println("Enter Amount to be Transfered : ");
        int transferAmt = sc.nextInt();
        if(bankUserHashMap.containsKey(accNo)  ){
            if(transferAmt <= balance){
            balance -= transferAmt;
            System.out.println("****************************************************");
            System.out.println("Rs " + transferAmt + " has been transfered to  Account number " + accNo);
            System.out.println("****************************************************");
            transactionHistoryList.add(-transferAmt);

        }
        else{
            System.out.println("Insufficient Balance");
            System.out.println("Balance : " + balance);
        }
    }
        else{
            System.out.println("Invalid Account Number . Please Try Again");
            serviceMenu();
        }
        

    }

    void quit() {
        atmInterface.main(null);
        return;
    }
}

class atmInterface {

    public static void main(String[] args) {
        int choice = 0;
        User userObj = new User("dummy", "dummy");

        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("----------------------MENU-----------------------");
            System.out.println("1. CREATE ACCOUNT");
            System.out.println("2. GO TO ATM INTERFACE");
            System.out.println("3. QUIT");
            System.out.print("CHOOSE OPERATION YOU WANT TO PERFORM :");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Your Name : ");
                    String userName = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Enter the Password :");
                    String Password = sc.nextLine();

                    User newUser = new User(userName, Password);
                    userObj.register(newUser);
                    break;
                case 2:
                    System.out.print("Enter Registered Name : ");
                    String userName2 = sc.nextLine();
                    sc.nextLine();

                    System.out.print("Enter Registed Password :");
                    String Password2 = sc.nextLine();

                    System.out.print("Enter Account Number : ");
                    int accNo = sc.nextInt();

                    userObj.login(accNo, userName2, Password2);
                    break;
                case 3:
                    System.out.println("THANKS FOR USING OUR ATM");
                    System.exit(0);
                default:
                    System.out.println("INVALID OPTION.PLEASE TRY AGAIN...");
                    main(args);
            }
        }
    }
}