import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * This class is responsible for reading and processing the CSV files
 * 
 * @author Joseph Rosemond
 * 
 * @version 4/7/2025
 */
public class LedgerFileProcessing {
    /**
     * Expected Date length
     */
    private static final int EXPECTED_DATE_LENGTH = 8;

    /**
     * Year end
     */
    private static final int YEAR_END = 4;

    /**
     * Month start
     */
    private static final int MONTH_START = 4;

    /**
     * Month end
     */
    private static final int MONTH_END = 6;

    /**
     * Day start
     */
    private static final int DAY_START = 6;

    /**
     * Day end
     */
    private static final int DAY_END = 8;

    /**
     * Reads a ledger from a CSV file
     * 
     * @param filepath the path to a CSV file
     * @param sizeLedger the max number of entries for the ledger
     * @return a ledger object with the entries from the file
     * @throws IllegalArgumentException If anything goes wrong
     */
    public static Ledger readLedgerFromFile(String filepath, int sizeLedger) {

        if (filepath == null || filepath.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid filepath");
        }

        if (sizeLedger <= 0) {
            throw new IllegalArgumentException("Invalid size");
        }
        try {
            Path path = Paths.get(filepath);

            if (!Files.exists(path)) {
                throw new IllegalArgumentException("Input file not found.");
            }

            InputStream inStream = Files.newInputStream(path);
            Scanner scanner = new Scanner(inStream);

            if (!scanner.hasNextLine()) {
                scanner.close();
                throw new IllegalArgumentException("Input file is empty.");
            }

            scanner.nextLine();
            Ledger ledger = new Ledger(sizeLedger);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.length() == 0) {
                    continue;
                }
                String[] tokens = line.split(",");
                if (tokens.length < 3) {
                    scanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }

                String dateStr = tokens[0].trim();
                if (dateStr.length() != EXPECTED_DATE_LENGTH) {
                    scanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }
                int year, month, day;
                try {
                    year = Integer.parseInt(dateStr.substring(0, YEAR_END));
                    month = Integer.parseInt(dateStr.substring(MONTH_START, MONTH_END));
                    day = Integer.parseInt(dateStr.substring(DAY_START, DAY_END));
                } catch (NumberFormatException nfe) {
                    scanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }
                String description = tokens[1].trim();
                int amount;
                try {
                    amount = Integer.parseInt(tokens[2].trim());
                } catch (NumberFormatException nfe) {
                    scanner.close();
                    throw new IllegalArgumentException("Invalid file");
                }
                ledger.addEntry(month, day, year, description, amount);
            }
            scanner.close();
            return ledger;
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file");
        }
    }

    /**
     * Writes a CSV file 
     * 
     * @param filepath the path to write the CSV file to
     * @param ledger the Ledger object to write
     * @throws IllegalArgumentException If anything goes wrong
     */
    public static void writeLedgerToFile(String filepath, Ledger ledger) {

        if (filepath == null || filepath.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid filepath");
        }
        if (ledger == null) {
            throw new IllegalArgumentException("null ledger");
        }
        try {
            Path path = Paths.get(filepath);
            if (Files.exists(path)) {
                throw new IllegalArgumentException("Output file already exists.");
            }
            String content = ledger.toString();
            OutputStream os = Files.newOutputStream(path);
            for (int i = 0; i < content.length(); i++) {
                os.write((byte) content.charAt(i));
            }
            os.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file");
        }
    }
}