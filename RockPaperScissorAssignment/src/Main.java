import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


enum Choice{
    ROCK,
    PAPER,
    SCISSORS
}

class InputOutOfRangeException extends Exception {
    public InputOutOfRangeException(String message) {
        super(message);
    }
}

public class Main {
    private static final int MIN_ROUNDS = 1;
    private static final int MAX_ROUNDS = 10;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String run = "Y";
        do {
            int computerScore = 0;
            int userScore = 0;
            int numOfTies = 0;
            String overAllWinner = "";


            int totalRounds = getTotalRounds();

            for (int round = 1; round <= totalRounds; round++) {
                System.out.println("Round " + round + ":");

                Choice computerChoice = getRandomChoice();
                Choice userChoice = getPlayerChoice();

                System.out.println("---Result for Round " + round + "---");
                System.out.println("Computer chooses: " + computerChoice);
                System.out.println("User chooses: " + userChoice);

                String result = determineWinner(computerChoice, userChoice);
                if (result.equalsIgnoreCase("Tie")) {
                    numOfTies++;
                    System.out.println("It's a tie!");
                } else if (result.equalsIgnoreCase("Computer")) {
                    System.out.println("Computer wins!");
                    computerScore++;
                } else {
                    System.out.println("User wins!");
                    userScore++;
                }
                System.out.println("----------------------");
            }

            System.out.println("\nGame Over! Here below the latest score updated : ");
            System.out.println("--------DASH BOARD--------");
            System.out.println("Computer Score: " + computerScore);
            System.out.println("User Score: " + userScore);
            System.out.println("Total Ties : " + numOfTies);

            if(computerScore > userScore) {
                overAllWinner = "Computer";
            }else if(computerScore < userScore) {
                overAllWinner = "User";
            }else {
                overAllWinner = "Tie";
            }
            System.out.println("The overall winner is : " + overAllWinner);
            System.out.println("-----------------------------------");


            System.out.println("Would you like to play again ? Y/N ");
            run = scanner.nextLine();
            run = scanner.nextLine();
            if(run.equalsIgnoreCase("N")) {
                System.out.println("Thanks for playing!");
            }

        }while(run.equalsIgnoreCase("Y"));
    }

    // Get random choice for computer
    private static Choice getRandomChoice() {
        Random random = new Random();
        int computerChoiceIntValue = random.nextInt(3);  // 0: ROCK, 1: PAPER, 2: SCISSORS
        return Choice.values()[computerChoiceIntValue];
    }

    //Get user choice from user input
    public static Choice getPlayerChoice(){
        int userChoiceIntValue = -1;
        System.out.println("Enter 0 for ROCK, 1 for PAPER, 2 for SCISSORS");
        boolean validChoice = false;
        do {
            try {
                userChoiceIntValue = scanner.nextInt();
                if(userChoiceIntValue < 0|| userChoiceIntValue > 2) {
                    throw new InputOutOfRangeException("Invalid choice. Pease try again.\nPlease enter 0 for ROCK, 1 for PAPER, 2 for SCISSORS. ");
                }
                validChoice = true;
            }catch(InputOutOfRangeException e) {
                System.out.println(e.getMessage());
            }
        }while(!validChoice);
        return Choice.values()[userChoiceIntValue];
    }


    private static int getTotalRounds() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rounds to play (1-10): ");
        int rounds = 0;

        try {
            rounds = scanner.nextInt();
            scanner.nextLine();
            if (rounds < MIN_ROUNDS || rounds > MAX_ROUNDS) {
                scanner.close();
                throw new InputOutOfRangeException("Number of rounds must be between " + MIN_ROUNDS + " and " + MAX_ROUNDS + " . Bye ! ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type! Bye!");
            System.exit(0);
        } catch (InputOutOfRangeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        return rounds;
    }

    private static String determineWinner(Choice computerChoice, Choice userChoice) {
        if (computerChoice == userChoice) {
            return "Tie";  // Tie
        } else if ((computerChoice == Choice.ROCK && userChoice == Choice.SCISSORS) ||
                (computerChoice == Choice.PAPER && userChoice == Choice.ROCK) ||
                (computerChoice == Choice.SCISSORS && userChoice == Choice.PAPER)) {
            return "Computer";  // Computer wins
        } else {
            return "User";  // User wins
        }
    }

}
