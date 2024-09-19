import java.util.*;
import java.util.Random;

class numberGuessingGame {

    void displayMenu() {
        System.out.println("------------MENU------------");
        System.out.println("1. Play Game ");
        System.out.println("2. Display ScoreBoard");
        System.out.println("3. Exit");
    }

    int displayscoreBoard(ArrayList<Integer> guessArr) {
        try {
            if (guessArr.isEmpty()) {
                System.out.println("No games played yet");
            }
        } catch (Exception r) {
            System.out.println("No games played yet");

        }

        int min = Collections.min(guessArr);
        int i = 1;
        System.out.println("---------------SCOREBOARD---------------");
        System.out.println("Attempt number          Guesses");
        for (Object y : guessArr) {
            System.out.println(i + "                         " + y);
            i++;
        }
        System.out.println("----------------------------------------");
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        ArrayList<Integer> guessArr = new ArrayList<>();
        int maxRange = 0;
        int guesses;
        String ch = "y";

        numberGuessingGame obj = new numberGuessingGame();

        while (!ch.equals("n")) {
            obj.displayMenu();
            System.out.println("Enter Your Choice");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("----------SELECT DIFFICULTY--------");
                    System.out.println("1. Easy (Numbers from 1 - 100)");
                    System.out.println("2. Medium (Numbers from 1 - 500)");
                    System.out.println("3. Difficult (Numbers from 1 - 1000)");
                    System.out.println("-----------------------------------");
                    int selectDifficulty = sc.nextInt();
                    if(selectDifficulty > 3){
                        System.out.println("Choose valid option");
                    }
                    if (selectDifficulty == 1) {
                        maxRange = 100;
                    } else if (selectDifficulty == 2) {
                        maxRange = 500;
                    } else {
                        maxRange = 1000;
                    }

                    int number = random.nextInt(maxRange) + 1;
                    System.out.println("Number to guess has been generated.");
                    guesses = 0;

                    int guessNumber = 0;
                    while (number != guessNumber) {
                        System.out.print("Guess the Number: ");
                        guessNumber = sc.nextInt();

                        if (guessNumber > number) {
                            System.out.println("Guessed Number is Higher (Guess Lesser Number) ");
                            guesses++;

                        } else if (guessNumber < number) {
                            System.out.println("Guessed Number is Lower (Guess Greater Number)");
                            guesses++;

                        } else {
                            System.out.println("You Won!!!");
                            System.out.println("-----------------------------------------");
                            System.out.println("You Completed Game in " + guesses + " Attempts");
                            System.out.println("-----------------------------------------");
                            guessArr.add(guesses);
                        }
                    }

                    System.out.println("Do You Want to Play again? (y/n)");
                    sc.nextLine();
                    ch = sc.nextLine();
                    break;

                case 2:
                    obj.displayscoreBoard(guessArr);
                    try {
                        System.out.println("Your Lowest Guesses were " + obj.displayscoreBoard(guessArr));
                    } catch (Exception e) {
                        System.out.println("No games played yet");
                    }
                    break;

                case 3:
                    System.out.println("Thanks for Playing This Game");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice! Please enter a valid option.");
                    break;
            }
        }
        sc.close();
        System.out.println("Goodbye!");
        obj.displayscoreBoard(guessArr);
    }
}
