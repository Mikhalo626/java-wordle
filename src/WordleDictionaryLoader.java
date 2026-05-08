import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionaryLoader {
    public WordleDictionary load(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String processed = line.trim().toLowerCase().replace('ё', 'е');
                if (processed.matches("[а-я]{5}")) {
                    words.add(processed);
                }
            }
        }
        if (words.isEmpty()) {
            throw new IOException("Словарь пуст или файл не найден: " + filePath);
        }
        return new WordleDictionary(words);
    }
}