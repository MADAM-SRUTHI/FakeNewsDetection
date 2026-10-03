import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class FakeNewsDetector {

    static Map<String, Integer> realWords = new HashMap<>();
    static Map<String, Integer> fakeWords = new HashMap<>();

    static int realTotal = 0;
    static int fakeTotal = 0;

    public static void trainModel() {

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("news_data.csv"));

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",", 2);

                if (parts.length < 2) {
                    continue;
                }

                String label = parts[0];
                String news = parts[1].toLowerCase();

                String[] words = news.split("\\s+");

                for (String word : words) {

                    word = word.replaceAll("[^a-zA-Z]", "");

                    if (word.isEmpty()) {
                        continue;
                    }

                    if (label.equals("real")) {

                        realWords.put(
                                word,
                                realWords.getOrDefault(word, 0) + 1
                        );

                        realTotal++;

                    } else if (label.equals("fake")) {

                        fakeWords.put(
                                word,
                                fakeWords.getOrDefault(word, 0) + 1
                        );

                        fakeTotal++;
                    }
                }
            }

            reader.close();

            System.out.println("Model trained successfully!");

        } catch (Exception e) {

            System.out.println("Error training model.");
            System.out.println(e.getMessage());
        }
    }

    public static String predict(String news) {

        String[] words = news.toLowerCase().split("\\s+");

        int realScore = 0;
        int fakeScore = 0;

        for (String word : words) {

            word = word.replaceAll("[^a-zA-Z]", "");

            if (realWords.containsKey(word)) {
                realScore += realWords.get(word);
            }

            if (fakeWords.containsKey(word)) {
                fakeScore += fakeWords.get(word);
            }
        }

        if (fakeScore > realScore) {
            return "FAKE NEWS";

        } else if (realScore > fakeScore) {
            return "POSSIBLY REAL NEWS";

        } else {
            return "UNCERTAIN";
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("     FAKE NEWS DETECTION SYSTEM");
        System.out.println("================================");

        trainModel();

        System.out.println("\nTraining completed.");

        System.out.print("\nEnter news: ");

        String news = scanner.nextLine();

        String result = predict(news);

        System.out.println("\n-------------------------------");
        System.out.println("Prediction: " + result);
        System.out.println("-------------------------------");

        scanner.close();
    }
}