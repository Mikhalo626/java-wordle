import java.util.*;

public class WordleGame {
    private static final int MAX_ATTEMPTS = 6;
    private final String targetWord;
    private final WordleDictionary dictionary;
    private int attemptsLeft = MAX_ATTEMPTS;
    private final List<String> history = new ArrayList<>();
    private final List<String> hintsFeedback = new ArrayList<>();
    private final Set<String> usedHints = new HashSet<>();

    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        List<String> words = dictionary.getWords();
        this.targetWord = words.get(new Random().nextInt(words.size()));
    }

    public String makeStep(String input) {
        String feedback = WordleDictionary.checkMatch(input, targetWord);
        history.add(input);
        hintsFeedback.add(feedback);
        attemptsLeft--;
        return feedback;
    }

    public String getCalculateHint() {
        List<String> candidates = new ArrayList<>();
        for (String word : dictionary.getWords()) {
            if (isPossible(word) && !usedHints.contains(word)) {
                candidates.add(word);
            }
        }
        if (candidates.isEmpty()) return null;
        String hint = candidates.get(new Random().nextInt(candidates.size()));
        usedHints.add(hint);
        return hint;
    }

    private boolean isPossible(String word) {
        for (int i = 0; i < history.size(); i++) {
            String hWord = history.get(i);
            String feedback = hintsFeedback.get(i);
            for (int j = 0; j < WordleDictionary.WORD_LENGTH; j++) {
                char f = feedback.charAt(j);
                char wc = word.charAt(j);
                char hc = hWord.charAt(j);
                if (f == '+' && wc != hc) return false;
                if (f == '.' && word.indexOf(hc) != -1) return false;
                if (f == '^' && (word.indexOf(hc) == -1 || wc == hc)) return false;
            }
        }
        return true;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public boolean isWin(String input) {
        return targetWord.equals(input);
    }
}