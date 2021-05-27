package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

class FlashCardProcessor {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, String> flashCards = new HashMap<>();
    ArrayList<String> termStorage = new ArrayList<>();

    public void play() {

        while(true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String input = scanner.nextLine();

            switch (input) {

                case "add":
                    addCard();
                    break;

                case "remove":
                    removeCard();
                    break;

                case "import":
                    importCard();
                    break;

                case "export":
                    exportCard();
                    break;

                case "ask":
                    ask();
                    break;

                case "exit":
                    System.out.println("Bye bye!");
                    return;
            }
        }
    }

    private void addCard() {

        System.out.println("The card:");
        String term = scanner.nextLine();

        if (flashCards.containsKey(term)) {
            System.out.printf("The card \"%s\" already exists.\n", term);
            System.out.println();
            return;
        }

        System.out.println("The definition of the card:");
        String definition = scanner.nextLine();

        if (flashCards.containsValue(definition)) {
            System.out.printf("The definition \"%s\" already exists.\n", definition);
            System.out.println();
            return;
        }
        flashCards.put(term, definition);
        termStorage.add(term);
        System.out.printf("The pair (\"%s\":\"%s\") has been added.\n", term, definition);
        System.out.println();
    }

    private void removeCard() {

        System.out.println("Which card?");
        String term = scanner.nextLine();

        String s = flashCards.remove(term);

        if (s == null) {
            System.out.printf("Can't remove \"%s\": there is no such card.\n", term);
        } else {
            System.out.println("The card has been removed.");
            termStorage.remove(term);
        }
        System.out.println();
    }

    private void importCard() {
        System.out.println("File name:");
        File file = new File(scanner.nextLine());

        try (Scanner fileScanner = new Scanner(file)){

            int cardsToLoad = fileScanner.nextInt();
            fileScanner.nextLine();

            for (int i = 0; i < cardsToLoad; i++) {

                String[] arr = fileScanner.nextLine().split(" : ");

                flashCards.put(arr[0], arr[1]);

                if (!termStorage.contains(arr[0])) {
                    termStorage.add(arr[0]);
                }
            }
            System.out.println(cardsToLoad + " cards have been loaded.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println();
    }

    private void exportCard() {
        System.out.println("File name:");
        File file = new File(scanner.nextLine());

        try (PrintWriter printWriter = new PrintWriter(file)) {

            printWriter.println(flashCards.size());

            for (var entry : flashCards.entrySet()) {
                printWriter.println(entry.getKey() + " : " + entry.getValue());
            }

            System.out.println(flashCards.size() + " cards have been saved.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        System.out.println();
    }

    private void ask() {
        System.out.println("How many times to ask?");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {

            String termToAsk = termStorage.get(i);

            System.out.printf("Print the definition of \"%s\":\n", termToAsk);
            String answer = scanner.nextLine();

            if (flashCards.get(termToAsk).equals(answer)) {
                System.out.println("Correct!");
            } else {

                if (flashCards.containsValue(answer)) {

                    for (var entry : flashCards.entrySet()) {

                        if (entry.getValue().equals(answer)) {
                            System.out.printf("Wrong. The right answer is \"%s\"," +
                                    " but your definition is correct for " +
                                    "\"%s\".\n", flashCards.get(termToAsk), entry.getKey());
                        }
                    }
                } else {
                    System.out.printf("Wrong. The right answer is \"%s\".\n", flashCards.get(termToAsk));
                }
            }
        }
        System.out.println();
    }

    private void print() {
        for (var entry : flashCards.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new FlashCardProcessor().play();
    }
}
