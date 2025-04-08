import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Tests LedgerEntry
 * 
 * @author Jessica Young Schmidt
 * @author Joseph Rosemond
 */
public class LedgerEntryTest {

    @Test
    public void testConstructorAndGetters() {
        LedgerEntry entry = new LedgerEntry(1, 2, 2040, "Birthday", 100);
        LedgerDate date = new LedgerDate(1, 2, 2040);
        assertEquals(date, entry.getDate());
        assertEquals("Birthday", entry.getDescription());
        assertEquals(100, entry.getAmount());
    }

    @Test
    public void testConstructorAndGettersStudent() {
        LedgerEntry entry = new LedgerEntry(12, 25, 2045, "Christmas", 250);
        LedgerDate date = new LedgerDate(12, 25, 2045);
        assertEquals(date, entry.getDate(), "Testing getter for date");
        assertEquals("Christmas", entry.getDescription(), "Testing getter for description");
        assertEquals(250, entry.getAmount(), "Testing getter for amount");
    }

    @Test
    public void testToString() {
        LedgerEntry entry = new LedgerEntry(1, 2, 2040, "Birthday", 100);
        assertEquals("20400102,Birthday,100", entry.toString());
    }

    @Test
    public void testToStringStudent() {
        LedgerEntry entry = new LedgerEntry(10, 31, 2042, "Halloween", 150);
        assertEquals("20421031,Halloween,150", entry.toString(), "Testing toString for Halloween entry");
    }

    @Test
    public void testConstructorExceptions() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerEntry(1, 2, 2000, "Birthday", 100),
                "Constructor - invalid date");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerEntry constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerEntry(1, 2, 2040, null, 100),
                "Constructor - invalid description");
        assertEquals("Null description", exception.getMessage(),
                "Testing LedgerEntry constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerEntry(1, 2, 2040, "", 100),
                "Constructor - invalid description");
        assertEquals("Empty or all whitespace description",
                exception.getMessage(),
                "Testing LedgerEntry constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerEntry(1, 2, 2040, " \t", 100),
                "Constructor - invalid description");
        assertEquals("Empty or all whitespace description",
                exception.getMessage(),
                "Testing LedgerEntry constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerEntry(1, 2, 2040, "Birthday", 0),
                "Constructor - invalid amount");
        assertEquals("Amount is zero", exception.getMessage(),
                "Testing LedgerEntry constructor exception message");

    }

    @Test
    public void testEqualsAndCompareTo() {
        LedgerEntry entry = new LedgerEntry(1, 2, 2040, "Birthday", 100);
        LedgerEntry entry2 = new LedgerEntry(1, 2, 2040, "Birthday", 100);
        assertTrue(entry.equals(entry));
        assertTrue(entry.equals(entry2));
        assertTrue(entry2.equals(entry));
        assertEquals(0, entry.compareTo(entry2));
        assertEquals(0, entry2.compareTo(entry));

        // different amount
        LedgerEntry entry3 = new LedgerEntry(1, 2, 2040, "Birthday", 200);
        assertFalse(entry.equals(entry3));
        assertFalse(entry3.equals(entry));
        assertTrue(entry.compareTo(entry3) < 0);
        assertTrue(entry3.compareTo(entry) > 0);

        // different description
        entry3 = new LedgerEntry(1, 2, 2040, "My Birthday", 100);
        assertFalse(entry.equals(entry3));
        assertFalse(entry3.equals(entry));
        assertTrue(entry.compareTo(entry3) < 0);
        assertTrue(entry3.compareTo(entry) > 0);

        // different date
        entry3 = new LedgerEntry(1, 12, 2040, "Birthday", 100);
        assertFalse(entry.equals(entry3));
        assertFalse(entry3.equals(entry));
        assertTrue(entry.compareTo(entry3) < 0);
        assertTrue(entry3.compareTo(entry) > 0);

        assertFalse(entry.equals("Hello"));
        assertFalse(entry.equals(null));
    }

}