import java.util.*;

public class WordleDictionary {
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
        StringBuilder result = new StringBuilder(".....");
        boolean[] targetUsed = new boolean[5];
        boolean[] inputUsed = new boolean[5];

        for (int i = 0; i < 5; i++) {
            if (input.charAt(i) == target.charAt(i)) {
                result.setCharAt(i, '+');
                targetUsed[i] = true;
                inputUsed[i] = true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (!inputUsed[i]) {
                for (int j = 0; j < 5; j++) {
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