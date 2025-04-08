import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Ledger
 * 
 * @author Jessica Young Schmidt
 * @author Joseph Rosemond
 */
public class LedgerTest {

    private Ledger ledger;

    @BeforeEach
    public void setUp() {
        ledger = new Ledger();
    }

    @Test
    public void testPublicConstant() {
        assertEquals(30, Ledger.DEFAULT_MAX_SIZE);
    }

    @Test
    public void testConstructorNoParameters() {
        // checks that no entries and default values
        assertEquals(0, ledger.getNumEntries());
        assertEquals(30, ledger.getSize());
        assertEquals(0, ledger.getBalance());
        assertEquals("Date,Description,Amount,Balance\n", ledger.toString());
    }

    @Test
    public void testConstructorOneParameterStudent() {
        Ledger customLedger = new Ledger(50);
        assertEquals(0, customLedger.getNumEntries(), "New ledger should have 0 entries");
        assertEquals(50, customLedger.getSize(), "Ledger size should match parameter");
        assertEquals(0, customLedger.getBalance(), "New ledger should have balance 0");
        assertEquals("Date,Description,Amount,Balance\n", customLedger.toString(), "Initial toString format incorrect");
    }

    @Test
    public void testEquals() {
        Ledger b = new Ledger(30);
        // Different size so c and ledger will never be equal
        Ledger c = new Ledger(10);
        assertTrue(ledger.equals(b));
        assertFalse(ledger.equals(c));
        assertTrue(b.equals(ledger));
        assertTrue(ledger.equals(ledger));
        assertFalse(ledger.equals(c));

        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        assertFalse(ledger.equals(b));
        assertFalse(ledger.equals(c));

        b.addEntry(3, 3, 2033, "Dinner out", -100);
        c.addEntry(3, 3, 2033, "Dinner out", -100);
        assertTrue(ledger.equals(b));
        assertFalse(ledger.equals(c));

        assertFalse(ledger.equals(null));

        assertFalse(ledger.equals("CSC"));
    }

    @Test
    public void testEqualsMultipleEntriesStudent() {
        Ledger ledger1 = new Ledger(30);
        Ledger ledger2 = new Ledger(30);
    
        ledger1.addEntry(1, 1, 2030, "Deposit", 500);
        ledger1.addEntry(1, 2, 2030, "Rent", -200);
    
        ledger2.addEntry(1, 1, 2030, "Deposit", 500);
        ledger2.addEntry(1, 2, 2030, "Rent", -200);
    
        assertTrue(ledger1.equals(ledger2), "Ledgers with same entries should be equal");
        assertTrue(ledger2.equals(ledger1), "Equality should be symmetric");
    
        ledger2.addEntry(1, 3, 2030, "Groceries", -100);
        assertFalse(ledger1.equals(ledger2), "Ledgers with different entries should not be equal");
    }

    @Test
    public void testIsFull() {
        ledger = new Ledger(2);
        assertFalse(ledger.isFull(),
                "Test empty ledger with size 2 - not full");
        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        assertFalse(ledger.isFull(),
                "Test ledger with size 2 and one entry - not full");
        ledger.addEntry(4, 3, 2033, "Dinner out", -100);
        assertTrue(ledger.isFull(),
                "Test ledger with size 2 and two entries - full");
    }

    @Test
    public void testAddEntryTwoEntries() {
        Ledger ledger1 = new Ledger(30);
        Ledger ledger2 = new Ledger(30);
    
        ledger1.addEntry(1, 1, 2030, "Deposit", 500);
        ledger1.addEntry(1, 2, 2030, "Rent", -200);
    
        ledger2.addEntry(1, 1, 2030, "Deposit", 500);
        ledger2.addEntry(1, 2, 2030, "Rent", -200);
    
        assertTrue(ledger1.equals(ledger2), "Ledgers with same entries should be equal");
        assertTrue(ledger2.equals(ledger1), "Equality should be symmetric");
    
        // Change one entry
        ledger2.addEntry(1, 3, 2030, "Groceries", -100);
        assertFalse(ledger1.equals(ledger2), "Ledgers with different entries should not be equal");

        ledger.addEntry(3, 3, 2033, "Dinner out - friend's portion", 30);
        assertEquals(2, ledger.getNumEntries());
        assertEquals(30, ledger.getSize());
        assertEquals(-70, ledger.getBalance());
        assertEquals(
                "Date,Description,Amount,Balance\n"
                        + "20330303,Dinner out,-100,-100\n"
                        + "20330303,Dinner out - friend's portion,30,-70\n",
                ledger.toString());
    }

    @Test
    public void testAddEntryThreeEntriesStudent() {
        ledger = new Ledger(5);
        ledger.addEntry(1, 1, 2025, "Gift", 100);
        ledger.addEntry(1, 5, 2025, "Dinner", -30);
        ledger.addEntry(2, 1, 2025, "Refund", 50);
    
        assertEquals(3, ledger.getNumEntries(), "Ledger should contain three entries");
        assertEquals(120, ledger.getBalance(), "Balance should reflect total after 3 entries");
    
        String expected = "Date,Description,Amount,Balance\n"
                        + "20250101,Gift,100,100\n"
                        + "20250105,Dinner,-30,70\n"
                        + "20250201,Refund,50,120\n";
        assertEquals(expected, ledger.toString(), "Ledger string format or content incorrect");
    }

    @Test
    public void testGetAtMethodsWithTwoEntries() {
        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        ledger.addEntry(3, 1, 2033, "Dinner out - friend's portion", 30);

        assertEquals(new LedgerDate(3, 1, 2033), ledger.getDateAt(0),
                "Get date at index 0");
        assertEquals("Dinner out - friend's portion",
                ledger.getDescriptionAt(0), "Get description at index 0");
        assertEquals(30, ledger.getAmountAt(0), "Get amount at index 0");
        assertEquals(30, ledger.getBalanceAt(0), "Get balance at index 0");
    }

    @Test
    public void testGetAtMethodsWithThreeEntriesStudent() {
        ledger = new Ledger(5);
        ledger.addEntry(3, 15, 2025, "Income", 500);
        ledger.addEntry(3, 20, 2025, "Utilities", -100);
        ledger.addEntry(3, 25, 2025, "Bonus", 200);
    }

    @Test
    public void testGetCredits() {
        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        ledger.addEntry(3, 3, 2033, "Dinner out - friend's portion", 30);
        ledger.addEntry(1, 1, 2023, "Gift", 50);
        assertEquals(
                "Date,Description,Amount,Balance\n" + "20230101,Gift,50,50\n"
                        + "20330303,Dinner out,-100,-50\n"
                        + "20330303,Dinner out - friend's portion,30,-20\n",
                ledger.toString());

        Ledger credits = ledger.getCredits();
        assertEquals(2, credits.getNumEntries());
        assertEquals(30, credits.getSize());
        assertEquals(80, credits.getBalance());
        assertEquals(
                "Date,Description,Amount,Balance\n" + "20230101,Gift,50,50\n"
                        + "20330303,Dinner out - friend's portion,30,80\n",
                credits.toString());
    }

    @Test
    public void testGetDebits() {
        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        ledger.addEntry(3, 3, 2033, "Dinner out - friend's portion", 30);
        ledger.addEntry(1, 1, 2023, "Gift", 50);
        assertEquals(
                "Date,Description,Amount,Balance\n" + "20230101,Gift,50,50\n"
                        + "20330303,Dinner out,-100,-50\n"
                        + "20330303,Dinner out - friend's portion,30,-20\n",
                ledger.toString());

        Ledger debits = ledger.getDebits();
        assertEquals(1, debits.getNumEntries());
        assertEquals(30, debits.getSize());
        assertEquals(-100, debits.getBalance());
        assertEquals("Date,Description,Amount,Balance\n"
                + "20330303,Dinner out,-100,-100\n", debits.toString());
    }

    @Test
    public void testGetRange() {
        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        ledger.addEntry(3, 3, 2033, "Dinner out - friend's portion", 30);
        ledger.addEntry(1, 1, 2023, "Gift", 50);
        assertEquals(
                "Date,Description,Amount,Balance\n" + "20230101,Gift,50,50\n"
                        + "20330303,Dinner out,-100,-50\n"
                        + "20330303,Dinner out - friend's portion,30,-20\n",
                ledger.toString());

        Ledger range = ledger.getDateRange(new LedgerDate(12, 10, 2022),
                new LedgerDate(3, 4, 2033));
        assertEquals(3, range.getNumEntries());
        assertEquals(30, range.getSize());
        assertEquals(-20, range.getBalance());
        assertEquals(
                "Date,Description,Amount,Balance\n" + "20230101,Gift,50,50\n"
                        + "20330303,Dinner out,-100,-50\n"
                        + "20330303,Dinner out - friend's portion,30,-20\n",
                range.toString());

        range = ledger.getDateRange(new LedgerDate(2, 28, 2033),
                new LedgerDate(3, 3, 2033));
        assertEquals(2, range.getNumEntries());
        assertEquals(30, range.getSize());
        assertEquals(-70, range.getBalance());
        assertEquals(
                "Date,Description,Amount,Balance\n"
                        + "20330303,Dinner out,-100,-100\n"
                        + "20330303,Dinner out - friend's portion,30,-70\n",
                range.toString());
    }

    @Test
    public void testForExceptions() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Ledger(-116),
                "constructor with non-positive constructor");
        assertEquals("Invalid size", exception.getMessage(),
                "Testing Ledger constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Ledger(0),
                "constructor with non-positive constructor");
        assertEquals("Invalid size", exception.getMessage(),
                "Testing Ledger constructor exception message");

        ledger = new Ledger(1);
        ledger.addEntry(3, 3, 2033, "Dinner out", -100);
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDescriptionAt(-1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDescriptionAt(1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDateAt(-1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDateAt(1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getAmountAt(-1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getAmountAt(1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getBalanceAt(-1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getBalanceAt(1), "Checking index");
        assertEquals("Invalid index", exception.getMessage(),
                "Testing index exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(null), "Checking null entry");
        assertEquals("Null entry", exception.getMessage(),
                "Testing null entry exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(3, 4, 2033, "Dinner out", -100),
                "Checking full ledger when adding");
        assertEquals("Ledger is full", exception.getMessage(),
                "Testing full ledger when adding entry exception message");

        ledger = new Ledger(20);
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(1, 2, 2000, "Birthday", 100),
                "addEntry invalid date");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing addEntry exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(1, 2, 2040, null, 100),
                "addEntry invalid description");
        assertEquals("Null description", exception.getMessage(),
                "Testing addEntry exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(1, 2, 2040, "", 100),
                "addEntry invalid description");
        assertEquals("Empty or all whitespace description",
                exception.getMessage(), "Testing addEntry exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(1, 2, 2040, " \t", 100),
                "addEntry invalid description");
        assertEquals("Empty or all whitespace description",
                exception.getMessage(), "Testing addEntry exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.addEntry(1, 2, 2040, "Birthday", 0),
                "addEntry invalid amount");
        assertEquals("Amount is zero", exception.getMessage(),
                "Testing addEntry exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDateRange(null, new LedgerDate(1, 2, 2025)),
                "null date in range");
        assertEquals("Null date", exception.getMessage(),
                "Testing getDateRange exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDateRange(new LedgerDate(1, 2, 2025), null),
                "null date in range");
        assertEquals("Null date", exception.getMessage(),
                "Testing getDateRange exception message");
        exception = assertThrows(IllegalArgumentException.class,
                () -> ledger.getDateRange(null, null), "null date in range");
        assertEquals("Null date", exception.getMessage(),
                "Testing getDateRange exception message");

    }
}