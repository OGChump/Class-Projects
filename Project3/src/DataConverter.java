import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * This project is meant to convert dates, weights and heights from
 * month-day-year and U.S. customary units to day-month-year and
 * metric units.
 * 
 * @author Joseph Rosemond
 * 
 * @version 3/24/2025
 */
public class DataConverter {

    /**
     * Starts the program.
     * 
     * @param args Used for command line arguemnts.
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java -cp DataConverter infile");
            return;
        }
        
        Path inPath = Path.of(args[0]);
        String inputFileName = inPath.getFileName().toString();
        
        if (inputFileName.length() < 4 || 
            !inputFileName.toLowerCase().substring(inputFileName.length() - 4).equals(".csv")) {
            System.out.println("Invalid input file extension");
            return;
        }
        
        if (inputFileName.length() < 3) {
            System.out.println("Invalid input file prefix");
            return;
        }
        String filePrefix = inputFileName.substring(0, 3);
        if (!filePrefix.equals("US_") && !filePrefix.equals("OT_")) {
            System.out.println("Invalid input file prefix");
            return;
        }
        
        boolean convertToOtherFormat;
        String outputPrefix;
        if (filePrefix.equals("US_")) {
            convertToOtherFormat = true;
            outputPrefix = "OT_";
        } else {
            convertToOtherFormat = false;
            outputPrefix = "US_";
        }
        
        String outputFileName = outputPrefix + inputFileName.substring(3);
        String directory = ".";
        Path outPath = Path.of(directory, outputFileName);
        
        if (Files.exists(outPath)) {
            Scanner in = new Scanner(System.in);
            System.out.print(outPath.toString() + " exists - OK to overwrite (y,n)?: ");
            String response = in.nextLine().trim();
            if (!response.toLowerCase().substring(0, 1).equals("y")) {
                System.out.println("");
                return;
            }
        }
        
        Scanner fileInput;
        try {
            fileInput = new Scanner(inPath.toFile());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to access input file: " + inPath.toString());
            return;
        }
        
        PrintWriter fileOutput;
        try {
            fileOutput = new PrintWriter(new FileOutputStream(outPath.toFile()));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create output file");
            return;
        }
        
        boolean success = processFile(convertToOtherFormat, fileInput, fileOutput);
        fileOutput.close();
        fileInput.close();
        
        if (!success) {
            System.out.println("Invalid input file format");
        }
    }

    /**
     * Converts the date from month-day-year format
     * to day-month-year format.
     * 
     * @param date Not sure what this does yet
     * @return Returns the date in day-month-year format
     *         or returns null if the inputed date is not in
     *         (mm/dd/yyyy) format.
     * @throws IllegalArgumentExecption Throws an IllegalArgumentException
     *                                  with a message, if the date is null.
     */
    public static String convertUSDateToOtherDate(String date) {
    if (date == null) {
        throw new IllegalArgumentException("Null date");
    }
    int firstSlash = date.indexOf("/");
    if (firstSlash == -1) {
        return null;
    }
    int secondSlash = date.indexOf("/", firstSlash + 1);
    if (secondSlash == -1) {
        return null;
    }
    int thirdSlash = date.indexOf("/", secondSlash + 1);
    if (thirdSlash != -1) {
        return null;
    }
    
    String month = date.substring(0, firstSlash);
    String day = date.substring(firstSlash + 1, secondSlash);
    String year = date.substring(secondSlash + 1);
    return day + "." + month + "." + year;
}

    /**
     * Converts the date from day-month-year format
     * to month-day-year format.
     * 
     * @param date Not sure what this does yet
     * @return Returns the date in month-day-year format
     *         or returns null if the inputed date is not in
     *         (dd.mm.yyyy) format.
     * @throws IllegalArgumentExecption Throws an IllegalArgumentException
     *                                  with a message, if the date is null.
     */
    public static String convertOtherDateToUSDate(String date) {
        if (date == null) {
            throw new IllegalArgumentException("Null date");
        }
        int firstDot = date.indexOf(".");
        if (firstDot == -1) {
            return null;
        }
        int secondDot = date.indexOf(".", firstDot + 1);
        if (secondDot == -1) {
            return null;
        }
        int thirdDot = date.indexOf(".", secondDot + 1);
        if (thirdDot != -1) {
            return null;
        }
        
        String day = date.substring(0, firstDot);
        String month = date.substring(firstDot + 1, secondDot);
        String year = date.substring(secondDot + 1);
        return month + "/" + day + "/" + year;
    }

    /**
     * Outputs data from input file to output file in the other countries format,
     * if convertToOtherFormat is ture, if false then outputs data from input file
     * to output file in the US format.
     * 
     * 
     * @param convertToOtherFormat Im not sure what this does yet
     * @param input I'm not sure what this does yet
     * @param output I'm not sure what this does yet
     * @return Return true if data once data is formatted correctly, or false
     *         if the input file is invalid for any reason.
     * @throws IllegalArgumentExecption Throws an IllegalArgumentException
     *                                  with a message, if the input or output
     *                                  is null.
     */
    public static boolean processFile(boolean convertToOtherFormat, Scanner input, PrintWriter output) {
        if (input == null) {
            throw new IllegalArgumentException("Null input");
        }
        if (output == null) {
            throw new IllegalArgumentException("Null output");
        }
        
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String convertedLine;
            if (convertToOtherFormat) {
                convertedLine = convertLineToOtherFormat(line);
            } else {
                convertedLine = convertLineToUSFormat(line);
            }
            if (convertedLine == null) {
                return false;
            }
            output.print(convertedLine + "\n");
        }
        return true;
    }

    /**
     * Returns a string containing the line of data in the other country's format.
     * The returned string must contain the last name, first name, birthdate,
     * height, weight, and temperature, separated by commas.
     * 
     * @param line Not sure what this does yet
     * @return Returns a string containing the line of data in the other 
     *         country's format. or null if the line does not contain 
     *         exactly six required data items.
     * @throws IllegalArgumentExecption Throws an IllegalArgumentException
     *                                  with a message, if the input or output
     *                                  is null.
     */
    public static String convertLineToOtherFormat(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Null line");
        }
        
        int commaPosition1 = line.indexOf(",");
        if (commaPosition1 == -1) return null;
        String lastName = line.substring(0, commaPosition1).trim();
        
        int commaPosition2 = line.indexOf(",", commaPosition1 + 1);
        if (commaPosition2 == -1) return null;
        String firstName = line.substring(commaPosition1 + 1, commaPosition2).trim();
        
        int commaPosition3 = line.indexOf(",", commaPosition2 + 1);
        if (commaPosition3 == -1) return null;
        String usDate = line.substring(commaPosition2 + 1, commaPosition3).trim();
        
        int commaPosition4 = line.indexOf(",", commaPosition3 + 1);
        if (commaPosition4 == -1) return null;
        String heightStr = line.substring(commaPosition3 + 1, commaPosition4).trim();
        
        int commaPosition5 = line.indexOf(",", commaPosition4 + 1);
        if (commaPosition5 == -1) return null;
        String weightStr = line.substring(commaPosition4 + 1, commaPosition5).trim();
        
        int commaPosition6 = line.indexOf(",", commaPosition5 + 1);
        if (commaPosition6 != -1) return null;
        String tempStr = line.substring(commaPosition5 + 1).trim();
        
        String otherDate = convertUSDateToOtherDate(usDate);
        if (otherDate == null) return null;
        
        double heightInches;
        double weightPounds;
        double tempFahrenheit;
        try {
            heightInches = Double.parseDouble(heightStr);
            weightPounds = Double.parseDouble(weightStr);
            tempFahrenheit = Double.parseDouble(tempStr);
        } catch (NumberFormatException e) {
            return null;
        }
        if (heightInches < 0 || weightPounds < 0 || tempFahrenheit < 32.0) return null;
        
        double heightCm = heightInches * 2.54;
        double weightKg = weightPounds * 0.454;
        double tempCelsius = (tempFahrenheit - 32.0) / 1.8;
        
        String formattedHeight = String.format("%.2f", heightCm);
        String formattedWeight = String.format("%.2f", weightKg);
        String formattedTemp = String.format("%.2f", tempCelsius);
        return lastName + "," + firstName + "," + otherDate + "," +
               formattedHeight + "," + formattedWeight + "," + formattedTemp;
    }

    /**
     * Returns a string containing the line of data in the US format.
     * The returned string must contain the last name, first name, birthdate,
     * height, weight, and temperature, separated by commas.
     * 
     * @param line Not sure what this does yet
     * @return Returns a string containing the line of data in the US format
     *         or Returns null if the line does not contain exactly six required
     *         data items.
     * @throws IllegalArgumentExecption Throws an IllegalArgumentException
     *                                  with a message, if the input or output
     *                                  is null.
     */
    public static String convertLineToUSFormat(String line) {
        if (line == null) throw new IllegalArgumentException("Null line");
        
        int commaPosition1 = line.indexOf(",");
        if (commaPosition1 == -1) return null;
        String lastName = line.substring(0, commaPosition1).trim();
        
        int commaPosition2 = line.indexOf(",", commaPosition1 + 1);
        if (commaPosition2 == -1) return null;
        String firstName = line.substring(commaPosition1 + 1, commaPosition2).trim();
        
        int commaPosition3 = line.indexOf(",", commaPosition2 + 1);
        if (commaPosition3 == -1) return null;
        String otherDate = line.substring(commaPosition2 + 1, commaPosition3).trim();
        
        int commaPosition4 = line.indexOf(",", commaPosition3 + 1);
        if (commaPosition4 == -1) return null;
        String heightStr = line.substring(commaPosition3 + 1, commaPosition4).trim();
        
        int commaPosition5 = line.indexOf(",", commaPosition4 + 1);
        if (commaPosition5 == -1) return null;
        String weightStr = line.substring(commaPosition4 + 1, commaPosition5).trim();
        
        int commaPosition6 = line.indexOf(",", commaPosition5 + 1);
        if (commaPosition6 != -1) return null;
        String tempStr = line.substring(commaPosition5 + 1).trim();
        
        String usDate = convertOtherDateToUSDate(otherDate);
        if (usDate == null) return null;
        
        double heightCm, weightKg, tempCelsius;
        try {
            heightCm = Double.parseDouble(heightStr);
            weightKg = Double.parseDouble(weightStr);
            tempCelsius = Double.parseDouble(tempStr);
        } catch (NumberFormatException e) {
            return null;
        }
        if (heightCm < 0 || weightKg < 0 || tempCelsius < 0) return null;
        
        double heightInches = heightCm / 2.54;
        double weightPounds = weightKg / 0.454;
        double tempFahrenheit = (tempCelsius * 1.8) + 32;
        
        String formattedHeight = String.format("%.2f", heightInches);
        String formattedWeight = String.format("%.2f", weightPounds);
        String formattedTemp = String.format("%.2f", tempFahrenheit);
        return lastName + "," + firstName + "," + usDate + "," +
               formattedHeight + "," + formattedWeight + "," + formattedTemp;
    }
}