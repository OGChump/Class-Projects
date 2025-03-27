import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Program to test DataConverter methods
 * @author Suzanne Balik
 * @author Michelle Glatz
 * @author Joseph Rosemond
 */
public class DataConverterTest {

    /** line in US format */
    public static final String US_LINE1 = "Brown,James,04/18/1991,68,167,99.30";
 
    /** line in other format */
    public static final String OTHER_LINE1 = "Brown,James,18.04.1991,0,0,0";
 
    /** expected line in other format */
    public static final String EXP_OTHER_LINE1 = "Brown,James,18.04.1991,172.72,75.82,37.39";
   
     /** expected line in US format */
    public static final String EXP_US_LINE1 = "Brown,James,04/18/1991,0.00,0.00,32.00";
    
    /** date in US format */
    public static final String US_DATE1 = "03/26/2029";

    /** date in US format */
    public static final String US_DATE2 = "19/48/8050";

    /** date in other format */
    public static final String OTHER_DATE1 = "26.03.2029";  

    /** date in other format */
    public static final String OTHER_DATE2 = "48.19.8050";

    /** invalid US date format - invalid separators */
    public static final String US_DATE_BAD = "03-26-2029";   

    /** invalid other date format - no spearators */
    public static final String OTHER_DATE_BAD = "26032029"; 
    
    /** invalid US date format - incorrect number of digits */
    public static final String US_DATE_BAD2 = "03/26/999";   

    /** invalid other date format - incorrect number of digits */
    public static final String OTHER_DATE_BAD2 = "26.3.2029"; 
    

    /** invalid US input - not enough tokens on line */
    public static final String US_TOO_FEW = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00\n";
    
    /** invalid US input - too many tokens on line */
    public static final String US_TOO_MANY = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00,32.00,0.0\n";
           
    /** invalid US input - invalid height */
    public static final String US_INVALID_HEIGHT = 
                               "O'Donnell,Betty Jo,12/28/1981,height,0.00,32.00\n";
          
    /** invalid US input - invalid weight */
    public static final String US_INVALID_WEIGHT = "O'Donnell,Betty Jo,12/28/1981,0.00,-1,32.00\n";
          
    /** invalid US input - invalid temperature */
    public static final String US_INVALID_TEMP = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00,20.00\n";

    /** invalid Other input - not enough tokens on line */
    public static final String OTHER_TOO_FEW = "O'Donnell,Betty Jo,28.12.1981,0.00,0.00\n";
    
    /** invalid Other input - too many tokens on line */
    public static final String OTHER_TOO_MANY = 
                               "O'Donnell,Betty Jo,28.12.1981,0.00,0.00,0.00,0.0\n";
           
    /** invalid Other input - invalid height */
    public static final String OTHER_INVALID_HEIGHT = 
                               "O'Donnell,Betty Jo,28.12.1981,height,0.00,0.00\n";
          
    /** invalid Other input - invalid weight */
    public static final String OTHER_INVALID_WEIGHT = 
                               "O'Donnell,Betty Jo,28.12.1981,0.00,-1,0.00\n";
          
    /** invalid input - invalid temperature */
    public static final String OTHER_INVALID_TEMP = "O'Donnell,Betty Jo,28.12.1981,0.00,0.00,0C\n";
   
    
    @Test
    public void testConvertUSDateToOtherDate1() {
        String description = "Convert Date to Other 1";
        String actual = DataConverter.convertUSDateToOtherDate(US_DATE1);
        assertEquals(OTHER_DATE1, actual, description);
    }

    /* This needs to be fixed to be a working test, it needs to test an
     * invalid date (A string with the correct format but not a real date)
    */
    @Test
    public void testConvertUSDateToOtherDate2() {
        String description = "Convert Date to Other 2";
        String actual = DataConverter.convertUSDateToOtherDate(US_DATE2);
        assertEquals(OTHER_DATE2, actual, description);
    }
    
    @Test
    public void testConvertOtherDateToUSDate1() {
        String description = "Convert Date to US 1";
        String actual = DataConverter.convertOtherDateToUSDate(OTHER_DATE1);
        assertEquals(US_DATE1, actual, description);
    } 

    /* This needs to be fixed to be a working test, it needs to test an
     * invalid date (A string with the correct format but not a real date)
    */
    @Test
    public void testConvertOtherDateToUSDate2() {
        String description = "Convert Date to US 2";
        String actual = DataConverter.convertOtherDateToUSDate(OTHER_DATE2);
        assertEquals(US_DATE2, actual, description);
    } 

    @Test
    public void testConvertLineToOther() {
        String description = "Convert Line to Other";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    // Use 00/00/0000 for the US date, 32.00 for the US temperature, and 0.00 for the 
    // height/weight, for test cases that are not testing the conversion of these values.
    
    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatName
     * test a name with more than one first and/or last name.
    */
    @Test
    public void testConvertLineToOtherFormatName() {
        String description = "Convert Line to Other Format: Name";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatDate
     * test any correctly formatted date that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToOtherFormatDate() {
        String description = "Convert Line to Other Format: Date";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatHeight
     * test any numerical height > 0 that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToOtherFormatHeight() {
        String description = "Convert Line to Other Format: Height";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatWeight
     * test any numerical weight > 0 that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToOtherFormatWeight() {
        String description = "Convert Line to Other Format: Weight";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatTemp
     * test any numerical temperature > 32 that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToOtherFormatTemp() {
        String description = "Convert Line to Other Format Tempture";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }


    @Test
    public void testConvertLineToUS() {
        String description = "Convert Line to US";
        String actual = DataConverter.convertLineToUSFormat(OTHER_LINE1);
        assertEquals(EXP_US_LINE1, actual, description);
    }

    // Use 00/00/0000 for the US date, 32.00 for the US temperature, and 0.00 for the 
    // height/weight, for test cases that are not testing the conversion of these values.

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatName
     * test a name with more than one first and/or last name.
    */
    @Test
    public void testConvertLineToUSFormatName() {
        String description = "Convert Line to Other Format: Name";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatDate
     * test any correctly formatted date that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToUSFormatDate() {
        String description = "Convert Line to Other Format: Date";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatHeight
     * test any numerical height > 0 that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToUSFormatHeight() {
        String description = "Convert Line to Other Format: Height";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatWeight
     * test any numerical weight > 0 that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToUSFormatWeight() {
        String description = "Convert Line to Other Format: Weight";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }

    /* This needs to be fixed to be a working test, testconvertLineToOtherFormatTemp
     * test any numerical temperature > 32 that is not tested elsewhere in this test program.
     */
    @Test
    public void testConvertLineToUSFormatTemp() {
        String description = "Convert Line to Other Format: Tempture";
        String actual = DataConverter.convertLineToOtherFormat(US_LINE1);
        assertEquals(EXP_OTHER_LINE1, actual, description);
    }


    /**
     * Test the DataConverter methods with invalid values
     */
    @Test
    public void testInvalidMethods() {

        // Invalid test cases are provided for you below - You do NOT
        // need to add additional invalid tests. Just make sure these
        // pass!

        /* test methods with null inputs */
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> DataConverter.convertUSDateToOtherDate(null),
                                 "DataConverter.convertUSDateToOtherDate(null)");
        assertEquals("Null date", exception.getMessage(),
                     "Testing DataConverter.convertUSDateToOtherDate(null) - " +
                     "Null date exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> DataConverter.convertOtherDateToUSDate(null),
                                 "DataConverter.convertOtherDateToUSDate(null)");
        assertEquals("Null date", exception.getMessage(),
                     "Testing DataConverter.convertOtherDateToUSDate(null) - " +
                     "Null date exception message");

        exception = assertThrows(IllegalArgumentException.class,
            () -> DataConverter.processFile(true, null, null),
                                 "DataConverter.processFile(true, null, null)");
        assertEquals("Null input", exception.getMessage(),
                     "Testing DataConverter.processFile(true, null, null) - " +
                     "Null input exception message");
        
        Scanner input = new Scanner(US_LINE1);
        exception = assertThrows(IllegalArgumentException.class,
            () -> DataConverter.processFile(true, input, null),
                                 "DataConverter.processFile(true, input, null)");
        assertEquals("Null output", exception.getMessage(),
                     "Testing DataConverter.processFile(true, input, null) - " +
                     "Null output exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> DataConverter.convertLineToOtherFormat(null),
                                 "DataConverter.convertLineToOtherFormat(null)");
        assertEquals("Null line", exception.getMessage(),
                     "Testing DataConverter.convertLineToOtherFormat(null) - " +
                     "Null line exception message");
                     
        exception = assertThrows(IllegalArgumentException.class,
            () -> DataConverter.convertLineToUSFormat(null),
                                 "DataConverter.convertLineToUSFormat(null)");
        assertEquals("Null line", exception.getMessage(),
                     "Testing DataConverter.convertLineToUSFormat(null) - " +
                     "Null line exception message");
                     
        /*test methods with invalid input data */
    
        String description = 
               "ConvertUsDateToOther() with invalid date format - invalid separators";
        assertEquals(null, DataConverter.convertUSDateToOtherDate(US_DATE_BAD), description);
        description = "ConvertOtherDateToUS() with invalid date format - no separators";
        assertEquals(null, DataConverter.convertOtherDateToUSDate(OTHER_DATE_BAD), description);
        description = "ConvertUsDateToOther() with invalid date format - incorrect num of digits";
        assertEquals(null, DataConverter.convertUSDateToOtherDate(US_DATE_BAD2), description);
        description = "ConvertOtherDateToUS() with invalid date format - incorrect num of digits";
        assertEquals(null, DataConverter.convertOtherDateToUSDate(OTHER_DATE_BAD2), description);
        description = "ConvertLineToOtherFormat() with too few tokens on line";
        assertEquals(null, DataConverter.convertLineToOtherFormat(US_TOO_FEW), description);
        description = "ConvertLineToOtherFormat() with too many tokens on line";
        assertEquals(null, DataConverter.convertLineToOtherFormat(US_TOO_MANY), description);
        description = "ConvertLineToOtherFormat() with invalid height";
        assertEquals(null, DataConverter.convertLineToOtherFormat(US_INVALID_HEIGHT), description);
        description = "ConvertLineToOtherFormat() with invalid weight";
        assertEquals(null, DataConverter.convertLineToOtherFormat(US_INVALID_WEIGHT), description);
        description = "ConvertLineToOtherFormat() with invalid temperature";
        assertEquals(null, DataConverter.convertLineToOtherFormat(US_INVALID_TEMP), description);
        description = "ConvertLineToUSFormat() with too few tokens on line";
        assertEquals(null, DataConverter.convertLineToUSFormat(OTHER_TOO_FEW), description);
        description = "ConvertLineToUSFormat() with too many tokens on line";
        assertEquals(null, DataConverter.convertLineToUSFormat(OTHER_TOO_MANY), description);
        description = "ConvertLineToUSFormat() with invalid height";
        assertEquals(null, DataConverter.convertLineToUSFormat(OTHER_INVALID_HEIGHT), description);
        description = "ConvertLineToUSFormat() with invalid weight";
        assertEquals(null, DataConverter.convertLineToUSFormat(OTHER_INVALID_WEIGHT), description);
        description = "ConvertLineToUSFormat() with invalid temperature";
        assertEquals(null, DataConverter.convertLineToUSFormat(OTHER_INVALID_TEMP), description);
    } 
    
    /**
     * Tests processFile
     */
    @Test
    public void testProcessFile() throws FileNotFoundException {
        // You do NOT need to add additional processFile tests. Just make sure
        // these pass!
        
        String simulatedUSFile = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00,32.00\n"; 
        String simulatedOtherFile = "O'Donnell,Betty Jo,28.12.1981,0.00,0.00,0.00\n";
        String simulatedTwoFew = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00\n";
        String simulatedTooMany = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00,32.00,0.0\n";
        String simulatedBadDateUS = "O'Donnell,Betty Jo,12.28.1981,0.00,0.00,32.00\n";
        String simulatedBadDateOther = "O'Donnell,Betty Jo,28/12/1981,0.00,0.00,0.00\n";        
        String simulatedBadHeight = "O'Donnell,Betty Jo,12/28/1981,height,0.00,32.00\n";
        String simulatedBadWeight = "O'Donnell,Betty Jo,12/28/1981,0.00,-1,32.00\n";
        String simulatedBadTemp = "O'Donnell,Betty Jo,12/28/1981,0.00,0.00,32.00F\n";
                                                             
     
        Scanner inputUS = new Scanner(simulatedUSFile);
        Scanner inputOther = new Scanner(simulatedOtherFile);
        
        String expectedOT = "test-files/exp_OT_test1.csv";
        String expectedUS = "test-files/exp_US_test2.csv";
        String actual = "test-files/act_file.csv"; 
        // Delete file if it already exists
        Path path = Path.of(actual);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }
  
        PrintWriter output = new PrintWriter(new FileOutputStream(actual));
        assertTrue(DataConverter.processFile(true, inputUS, output), 
                   "test processFile with valid US input data");       
        output.close();
        assertFilesEqual(expectedOT, actual, "act_file.csv should match exp_OT_test1.csv");
 
        output = new PrintWriter(new FileOutputStream(actual));
        assertTrue(DataConverter.processFile(false, inputOther, output), 
                   "test processFile with valid Other input data");       
        output.close();
        assertFilesEqual(expectedUS, actual, "act_file.csv should match exp_US_test2.csv"); 
        
        
        /* test bad input file data, too few/many items on line, 
           invalid date, height, weight, or temp */
        Scanner input = new Scanner(simulatedTwoFew);   
        output = new PrintWriter(new FileOutputStream(actual));
        assertFalse(DataConverter.processFile(true, input, output), 
                   "test processFile with too few tokens on line");
        input = new Scanner(simulatedTooMany); 
        assertFalse(DataConverter.processFile(true, input, output), 
                   "test processFile with too many tokens on line");
        input = new Scanner(simulatedBadDateUS); 
        assertFalse(DataConverter.processFile(true, input, output), 
                   "test processFile with bad US date format");           
        input = new Scanner(simulatedBadDateOther); 
        assertFalse(DataConverter.processFile(false, input, output), 
                   "test processFile with bad Other date format"); 
        input = new Scanner(simulatedBadHeight); 
        assertFalse(DataConverter.processFile(true, input, output), 
                   "test processFile with bad height format");
        input = new Scanner(simulatedBadWeight); 
        assertFalse(DataConverter.processFile(true, input, output), 
                   "test processFile with bad weight format");
        input = new Scanner(simulatedBadTemp); 
        assertFalse(DataConverter.processFile(true, input, output), 
                   "test processFile with bad temperature format");                    
        output.close();   
                     
    }

    
    /**
     * Tests whether files contain the same contents
     * 
     * @param pathToExpected path to file with expected contents
     * @param pathToActual path to file with actual content
     * @param message message for test
     * @throws FileNotFoundException if Scanner cannot be constructed with file
     */
    private void assertFilesEqual(String pathToExpected, String pathToActual, String message)
            throws FileNotFoundException {
        try (Scanner expected = new Scanner(new FileInputStream(pathToExpected));
                Scanner actual = new Scanner(new FileInputStream(pathToActual));) {
            while (expected.hasNextLine()) {
                if (!actual.hasNextLine()) { // checks that actual has line as well
                    fail(message + ": Actual missing line(s)");
                } else { // both have another line
                    assertEquals(expected.nextLine(), actual.nextLine(),
                            message + ": Checking line equality");
                }
            }

            if (actual.hasNextLine()) {
                fail(message + ": Actual has extra line(s)");
            }
        }
    }    

}