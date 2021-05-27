package flashcards;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String term = scanner.nextLine();
        String definition = scanner.nextLine();
        String answer = scanner.nextLine();

        if (answer.equals(definition)) {
            System.out.println("The answer is right!");
        } else {
            System.out.println("The answer is wrong :(");
        }
    }
}
