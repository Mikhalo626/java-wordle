import java.util.Collections;
import java.util.List;

public class WordleDictionary {
    public static final int WORD_LENGTH = 5;
    private final List<String> words;

    public WordleDictionary(List<String> words) {
        this.words = Collections.unmodifiableList(words);
    }

    public List<String> getWords() {
        return words;
    }

    public boolean contains(String word) {
        return words.contains(word);
    }

    public static String checkMatch(String input, String target) {
        StringBuilder result = new StringBuilder(".".repeat(WORD_LENGTH));
        boolean[] targetUsed = new boolean[WORD_LENGTH];
        boolean[] inputUsed = new boolean[WORD_LENGTH];

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (input.charAt(i) == target.charAt(i)) {
                result.setCharAt(i, '+');
                targetUsed[i] = true;
                inputUsed[i] = true;
            }
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            if (!inputUsed[i]) {
                for (int j = 0; j < WORD_LENGTH; j++) {
                    if (!targetUsed[j] && input.charAt(i) == target.charAt(j)) {
                        result.setCharAt(i, '^');
                        targetUsed[j] = true;
                        break;
                    }
                }
            }
        }
        return result.toString();
    }
}