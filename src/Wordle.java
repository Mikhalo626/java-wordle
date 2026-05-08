import java.util.Scanner;

public class Wordle {
    public static void main(String[] args) {
        SystemJournal journal = null;
        try {
            journal = new SystemJournal("game.log");
            WordleDictionaryLoader loader = new WordleDictionaryLoader();

            WordleDictionary dictionary = loader.load("dictionary.txt");
            WordleGame game = new WordleGame(dictionary);

            journal.log("Игра запущена. Загадано слово: " + game.getTargetWord());
            Scanner scanner = new Scanner(System.in);

            System.out.println("--- Игра WORDLE ---");
            System.out.println("Введите слово из 5 букв или нажмите Enter для подсказки.");

            while (game.getAttemptsLeft() > 0) {
                System.out.print("> ");
                String input = scanner.nextLine().trim().toLowerCase().replace('ё', 'е');

                if (input.isEmpty()) {
                    input = game.getCalculateHint();
                    if (input == null) {
                        System.out.println("Подходящих слов для подсказки не найдено.");
                        continue;
                    }
                    System.out.println("Подсказка компьютера: " + input);
                }

                if (!input.matches("[а-я]{5}") || !dictionary.contains(input)) {
                    System.out.println("Ошибка: слово должно быть из 5 русских букв и быть в словаре.");
                    continue;
                }

                String result = game.makeStep(input);
                journal.log("Попытка: " + input + " | Результат: " + result + " | Осталось: " + game.getAttemptsLeft());
                System.out.println("> " + result);

                if (game.isWin(input)) {
                    System.out.println("Поздравляем! Вы угадали слово.");
                    journal.log("Игра завершена победой пользователя.");
                    return;
                }
            }

            System.out.println("Попытки закончились. Загаданное слово было: " + game.getTargetWord());
            journal.log("Игра завершена поражением.");

        } catch (Exception e) {
            if (journal != null) {
                journal.log("Критическая ошибка: " + e.getMessage());
            }
            System.err.println("Произошла ошибка при запуске игры: " + e.getMessage());
        }
    }
}