import java.util.*;

class Questions {
    Scanner sc = new Scanner(System.in);
    HashMap hs = new HashMap<String, String>();
    int remainingTime = 180;
    int flag = 0;
    Character ansArr[];
    Character expectedAns[];

    void register() {
        System.out.println("Enter Your Username : ");
        String userName = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String passWord = sc.nextLine();
        if (!hs.containsKey(userName)) {
            hs.put(userName, passWord);
            System.out.println("User Registered Successfully!!!");
        } else {
            System.out.println("Username already exists choose different usename");
        }

    }

    void login() {
        System.out.println("Enter Your Username : ");
        String userName = sc.nextLine();
        System.out.println("Enter Your Password : ");
        String passWord = sc.nextLine();
        if (hs.containsKey(userName) && hs.containsValue(passWord)) {
            flag = 1;
            System.out.println("----------------------LOGIN SUCCESSFULL----------------------");
            displayOptions();

        } else {
            System.out.println("Invalid Credentials");
        }
    }

    void oldUserName() {
        System.out.println("Enter Old Username : ");
        String oldUserName = sc.nextLine();
        System.out.println("Enter Password : ");
        String oldpassWord = sc.nextLine();
        if (hs.containsKey(oldUserName) && hs.containsValue(oldpassWord)) {
            hs.remove(oldUserName);
            System.out.println("Enter New Username : ");
            String newUserName = sc.nextLine();
            hs.put(newUserName, oldpassWord);
            System.out.println("Username Updated Succesfully!!!");

        } else {
            System.out.println("Invalid Credentials");

        }
    }

    void oldPass() {
        System.out.println("Enter Your Username : ");
        String oldName = sc.nextLine();
        System.out.println("Enter Old Password : ");
        String oldpass = sc.nextLine();
        if (hs.containsKey(oldName) && hs.containsValue(oldpass)) {

            System.out.println("Enter New Password : ");
            String newpass = sc.nextLine();
            hs.replace(oldpass, newpass);
            System.out.println("Password Updated Succesfully!!!");
        } else {
            System.out.println("Invalid Credentials");

        }
    }

    void updateProfile() {
        System.out.println("What do you Want to update");
        System.out.println("1.Username");
        System.out.println("2.Password");
        System.out.print("Enter Your Choice : ");
        int ch = sc.nextInt();
        sc.nextLine();
        switch (ch) {
            case 1:
                oldUserName();
                break;
            case 2:
                oldPass();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    void displayOptions() {
        System.out.println("1.Start Test");
        System.out.println("2.Back");
        System.out.print("Enter Your Choice : ");
        int choice2 = sc.nextInt();
        switch (choice2) {
            case 1:
                startTest();
                break;
            case 2:
                System.out.println("Previous all results are deleted. Please Register yourself again!!!");
                JavaQuiz.main(null);
            default:
                System.out.println("Invalid Option");
                break;

        }
    }

    void autoSubmit() {
        if (remainingTime > 0) {
            int score = calculateScore(ansArr, expectedAns);
            System.out.println("Your Score is " + score + "/10");
        }
        int score = calculateScore(ansArr, expectedAns);
        System.out.println();
        System.out.println("Your Score is " + score + "/10");
        System.out.println("Test Submitted Automatically As Time has Ended");
        System.exit(0);

    }

    void startTest() {

        System.out.println("!!!YOU ONLY HAVE 3 MINUTES BEFORE TEST GETS AUTOSUBMITTED !!!");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime--;
                if (remainingTime <= 0) {
                    autoSubmit();
                }
            }
        }, 0, 1000);

        List<String> questions = new ArrayList<String>();

        questions.add(
                "Q1. What is the default value of a boolean variable in Java?\n   a) 0\n   b) true\n   c) false\n   d) null\n");
        questions.add(
                "Q2. Which of the following is not a Java keyword?\n  a) static\n  b) void\n  c) private\n  d) main\n");
        questions.add(
                "Q3. Which of these is used to add text to the console in Java?\n  a) Console.println()\n  b) System.out.println()\n  c) print()\n  d) write()\n");
        questions.add(
                "Q4. Which of the following is a correct way to declare an array in Java?\n  a) int[] arr = new int[5];\n  b) int arr[5];\n  c) array int arr = new int[5];  \n d) int arr = [5];\n");
        questions.add(
                "Q5. What is the size of an int in Java?\n  a) 16 bits\n  b) 32 bits\n  c) 64 bits\n  d) Depends on the platform\n");
        questions.add(
                "Q6. Which method is used to start a thread in Java?\n  a) init()\n  b) run()\n  c) start()\n  d) execute()\n");
        questions.add(
                "Q7. Which of the following is not a valid access modifier in Java?\n  a) public\n  b) protected\n  c) static\n  d) private\n");
        questions.add(
                "Q8. Which of the following is a wrapper class in Java?\n  a) int\n  b) char\n  c) Integer\n  d) void\n");
        questions.add(
                "Q9. What is the purpose of the final keyword in Java?\n  a) It is used to define constant values.\n  b) It is used to inherit a class. \n  c) It is used to create an abstract method.\n  d) It is used to define a mutable object.\n");
        questions.add(
                "Q10. Which of the following is a valid declaration of a Java main method?\n  a) public static void main(String args[])\n  b) public void main(String args[]) \n  c) static public void main() \n  d) void main(String[] args)\n");

        expectedAns = new Character[] { 'c', 'd', 'b', 'a', 'b', 'c', 'c', 'c', 'a', 'a' };

        ansArr = new Character[questions.size()];

        int j = 0;
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i));
            System.out.print("Enter Your Answer : ");
            ansArr[j] = sc.next().charAt(0);
            j++;
            System.out.println();
        }

        for (int i = 0; i < ansArr.length; i++) {
            System.out.println(ansArr[i]);
        }
        int score = calculateScore(ansArr, expectedAns);
        System.out.println("Your Score is " + score + "/10");
        System.exit(0);

    }

    int calculateScore(Character ansArr[], Character expectedAns[]) {

        int correctCnt = 0;
        for (int i = 0; i < ansArr.length; i++) {
            if (ansArr[i] == (expectedAns[i])) {
                correctCnt++;
            }
        }
        return correctCnt;
    }
}

class JavaQuiz {

    public static void main(String[] args) {
        Questions questionObj = new Questions();

        int choice = 0;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (choice != 2) {

            System.out.println("---------------MENU---------------");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Update Profile");
            System.out.println("4.Exit");
            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    questionObj.register();
                    break;
                case 2:
                    questionObj.login();
                    break;
                case 3:
                    questionObj.updateProfile();
                    break;
                case 4:
                    System.out.println("Exiting ...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
                    main(args);
                    break;
            }
        }
    }
}
