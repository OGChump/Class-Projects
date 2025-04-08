import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests LedgerDate
 * 
 * @author Jessica Young Schmidt
 * @author Joseph Rosemond
 */
public class LedgerDateTest {
    @Test
    public void testPublicConstants() {
        assertEquals(2020, LedgerDate.MIN_YEAR,
                "Tests value of LedgerDate.MIN_YEAR");
        assertEquals(2050, LedgerDate.MAX_YEAR,
                "Tests value of LedgerDate.MAX_YEAR");
    }

    @Test
    public void testConstructorAndGetters() {
        LedgerDate date = new LedgerDate(7, 19, 2024);
        assertEquals(2024, date.getYear());
        assertEquals(7, date.getMonth());
        assertEquals(19, date.getDay());
        assertEquals(20240719, date.getDate());

        date = new LedgerDate(11, 8, 2040);
        assertEquals(2040, date.getYear());
        assertEquals(11, date.getMonth());
        assertEquals(8, date.getDay());
        assertEquals(20401108, date.getDate());
    }

    @Test
    public void testConstructorAndGettersStudent() {
        LedgerDate date = new LedgerDate(12, 25, 2025);
        assertEquals(2025, date.getYear());
        assertEquals(12, date.getMonth());
        assertEquals(25, date.getDay());
        assertEquals(20251225, date.getDate());
    }

    @Test
    public void testEqualsAndCompareTo() {
        LedgerDate date = new LedgerDate(2, 28, 2024);
        LedgerDate date2 = new LedgerDate(2, 28, 2024);
        assertTrue(date.equals(date));
        assertTrue(date.equals(date2));
        assertTrue(date2.equals(date));
        assertEquals(0, date.compareTo(date));
        assertEquals(0, date2.compareTo(date));
        LedgerDate date3 = new LedgerDate(2, 28, 2025);
        assertFalse(date.equals(date3));
        assertTrue(date.compareTo(date3) < 0);
        assertTrue(date3.compareTo(date) > 0);
        date3 = new LedgerDate(2, 27, 2024);
        assertFalse(date.equals(date3));
        assertTrue(date.compareTo(date3) > 0);
        date3 = new LedgerDate(3, 28, 2024);
        assertFalse(date.equals(date3));
        assertTrue(date.compareTo(date3) < 0);
        assertFalse(date.equals("Hello"));
        assertFalse(date.equals(null));
    }

    @Test
    public void testToString() {
        LedgerDate date = new LedgerDate(2, 29, 2024);
        assertEquals("02/29/2024", date.toString());
        date = new LedgerDate(6, 15, 2040);
        assertEquals("06/15/2040", date.toString());
    }

    @Test
    public void testToStringStudent() {
        LedgerDate date = new LedgerDate(1, 1, 2025);
        assertEquals("01/01/2025", date.toString());
    }

    @Test
    public void testConstructorExceptions() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(1, 2, 2000), "new LedgerDate(1, 2, 2000)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(1, 2, 3000), "new LedgerDate(1, 2, 3000)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(11, 31, 2020),
                "new LedgerDate(11, 31, 2020)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(11, -11, 2020),
                "new LedgerDate(11, -11, 2020)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(2, 29, 2021),
                "new LedgerDate(2, 29, 2021)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(2, 30, 2020),
                "new LedgerDate(2, 31, 2021)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(13, 1, 2040),
                "new LedgerDate(13, 1, 2040)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(1, 1, -2040),
                "new LedgerDate(1, 1, -2040)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");
                
        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(1, 0, 2040),
                "new LedgerDate(1, 0, 2040)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");   
              
        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(0, 2, 2040),
                "new LedgerDate(0, 2, 2040)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message"); 

        exception = assertThrows(IllegalArgumentException.class,
                () -> new LedgerDate(-1, 2, 2040),
                "new LedgerDate(-1, 2, 2040)");
        assertEquals("Invalid date", exception.getMessage(),
                "Testing LedgerDate constructor exception message");                

    }

}