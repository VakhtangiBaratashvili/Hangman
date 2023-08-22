import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    // Print the welcome message and rules of the game
    private static void printWelcomeMessage() {
        System.out.println("                                             WELCOME TO                   ");
        System.out.println("                            _                                             ");
        System.out.println("                           | |                                            ");
        System.out.println("                           | |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  ");
        System.out.println("                           | '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ ");
        System.out.println("                           | | | | (_| | | | | (_| | | | | | | (_| | | | |");
        System.out.println("                           |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|");
        System.out.println("                                               __/ |                      ");
        System.out.println("                                              |___/                       ");
        System.out.println(" ---------------------------------------------RULES----------------------------------------------");
        System.out.println("| The host chooses a word or phrase to be guessed.                                               |");
        System.out.println("| The host will display a series of blank spaces representing the letters in the word or phrase. |");
        System.out.println("| Guess letters one at a time to fill in the blank spaces.                                       |");
        System.out.println("| If the guessed letter is in the word, it will be revealed in the corresponding blank space(s). |");
        System.out.println("| If the guessed letter is not in the word, it will be marked as an incorrect guess.             |");
        System.out.println("| Keep guessing letters until you guess the word correctly or make too many incorrect guesses.   |");
        System.out.println("| Be careful! With each incorrect guess, a part of the hangman will be drawn.                    |");
        System.out.println("| The play ends when you guess the word correctly or when the hangman is fully drawn.            |");
        System.out.println(" -------------------------------------------GOOD LUCK--------------------------------------------");
    }

    // Print the hangman figure based on the number of incorrect guesses
    private static void printHangman(int index) {
        switch (index) {
            case 0 -> {
                System.out.println("+---+");
                System.out.println("|   |");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("    |");
                System.out.println("=========");
            }
            case 1 -> {
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
            }
            case 2 -> {
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
            }
            case 3 -> {
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
            }
            case 4 -> {
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("=========");
            }
            case 5 -> {
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("/    |");
                System.out.println("     |");
                System.out.println("=========");
            }
            case 6 -> {
                System.out.println(" +---+");
                System.out.println(" |   |");
                System.out.println(" O   |");
                System.out.println("/|\\  |");
                System.out.println("/ \\  |");
                System.out.println("     |");
                System.out.println("=========");
            }
            default -> System.out.println("Wrong index");
        }
    }

    // Print the current state of the word with guessed letters filled in
    private static void printWord(List<Character> letters, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (letters.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            }
            else {
                System.out.print('_');
            }
        }
        System.out.println();
    }

    // Check if a letter is present in the word
    private static boolean isInWord(char letter, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }

    // Play a single game of hangman
    private static void playGame() throws FileNotFoundException {
        // Read the words from a file
        Scanner scanner = new Scanner(new File("C:\\Users\\sandro\\IdeaProjects\\Hangman\\words"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        // Choose a random word
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        // Initialize variables for the game
        Scanner scan = new Scanner(System.in);
        int index = 0; // Hangman figure index
        int count = 0; // Number of correct guesses
        List<Character> letters = new ArrayList<>(); // Guessed letters

        while (true) {
            printHangman(index);
            printWord(letters, word);
            System.out.println();
            System.out.print("Enter a letter: ");
            char letter = scan.next().toUpperCase().charAt(0);
            letters.add(letter);

            // Check if the guessed letter is correct
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    count++;
                }
            }

            // Check if the game is won or lost
            if (count == word.length()) {
                System.out.println(word);
                System.out.println("Congratulations! You guessed correctly.");
                break;
            }

            if (!isInWord(letter, word)) {
                index++;
                if (index == 6) {
                    printHangman(index);
                    System.out.println("You lose. The word was: " + word);
                    break;
                }
            }
        }
    }

    // Ask the player if they want to play again
    private static void playAgain() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.nextLine();
            while (!playAgain.equalsIgnoreCase("yes") && !playAgain.equalsIgnoreCase("no")) {
                System.out.print("Enter 'yes' or 'no': ");
                playAgain = scanner.nextLine();
            }
            if (playAgain.equalsIgnoreCase("yes")) {
                playGame();
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        printWelcomeMessage();
        playGame();
        playAgain();
    }
}