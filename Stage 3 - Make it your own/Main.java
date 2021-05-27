package flashcards;

import java.util.Scanner;

class FlashCard {

    private String term;
    private String definition;

    public FlashCard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Input the number of cards:");
        int numberOfCards = scanner.nextInt();
        scanner.nextLine();

        FlashCard[] cards = new FlashCard[numberOfCards];

        for (int i = 0; i < cards.length; i++) {

            System.out.printf("Card #%d:\n", (i + 1));
            String term = scanner.nextLine();
            System.out.printf("The definition for card #%d:\n", (i + 1));
            String definition = scanner.nextLine();
            cards[i] = new FlashCard(term, definition);
        }

        for (FlashCard card : cards) {

            System.out.printf("Print the definition of \"%s\":\n", card.getTerm());
            String answer = scanner.nextLine();

            if (answer.equals(card.getDefinition())) {
                System.out.println("Correct!");
            } else {
                System.out.printf("Wrong. The right answer is \"%s\".\n", card.getDefinition());
            }
        }
    }
}
