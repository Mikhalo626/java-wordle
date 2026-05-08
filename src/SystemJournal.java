import java.io.*;
import java.time.LocalDateTime;

public class SystemJournal {
    private final PrintWriter writer;

    public SystemJournal(String fileName) throws IOException {
        this.writer = new PrintWriter(new FileWriter(fileName, true));
    }

    public SystemJournal(PrintWriter writer) {
        this.writer = writer;
    }

    public void log(String message) {
        writer.println(LocalDateTime.now() + ": " + message);
        writer.flush();
    }
}