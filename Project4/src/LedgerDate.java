/**
 * This class is responsible for handeling all date implementation
 * 
 * @author Joseph Rosemond
 * 
 * @version 4/7/2025
 */
public class LedgerDate implements Comparable<LedgerDate> {

    /**
     * Minimum year constant
     */
    public static final int MIN_YEAR = 2020;

    /**
     * Maximum year constant
     */
    public static final int MAX_YEAR = 2050;

    /**
     * Minimum month constant
     */
    public static final int MIN_MONTH = 1;

    /**
     * Maximum month constant
     */
    public static final int MAX_MONTH = 12;

    /**
     * Minimum day constant
     */
    public static final int MIN_DAY = 1;

    /**
     * Maximum day constant
     */
    public static final int MAX_DAY_FEB = 28;

    /**
     * Maximum day constant
     */
    public static final int MAX_DAY_FEB_LEAP = 29;

    /**
     * Maximum day constant
     */
    public static final int MAX_DAY_SHORT = 30;

    /**
     * Maximum day constant
     */
    public static final int MAX_DAY_LONG = 31;

    /**
     * Year multiplier
     */
    public static final int YEAR_MULTIPLIER = 10000;

    /**
     * Month multiplier
     */
    public static final int MONTH_MULTIPLIER = 100;

    /**
     * Leap Year
     */
    public static final int LEAP_YEAR = 4;

    /**
     * Leap Year 2
     */
    public static final int LEAP_YEAR_TWO = 100;

    /**
     * Leap Year 3
     */
    public static final int LEAP_YEAR_THREE = 400;

    /**
     * Private instance field for the date
     */
    private int date;

    /**
     * Creates a ledger object with that month day and year
     * 
     * @param month the month of the year 
     * @param day the day of the year
     * @param year the year
     * @throws IllegalArgumentException If the date is not between 2020 and 2050 and valid
     */
    public LedgerDate(int month, int day, int year) {

        //Checks if the year is valid so that an invalid year is not stored
        if (year > MAX_YEAR || year < MIN_YEAR ) {
            throw new IllegalArgumentException("Invalid date");
        }

        //Checks if the month is valid so that an invalid month is not stored
        if (month > MAX_MONTH || month < MIN_MONTH) {
            throw new IllegalArgumentException("Invalid date");
        }

        //Creates an array for all of the valid dates in order
        int[] maxDays = {MAX_DAY_LONG, MAX_DAY_FEB, MAX_DAY_LONG, MAX_DAY_SHORT,
            MAX_DAY_LONG, MAX_DAY_SHORT, MAX_DAY_LONG, MAX_DAY_LONG, 
            MAX_DAY_SHORT, MAX_DAY_LONG, MAX_DAY_SHORT, MAX_DAY_LONG};
        int maxDay = maxDays[month - 1];

        //Checks if it is a leap year and changes the amount of days in Feburary, accordingly
        if (month == 2 && (year % LEAP_YEAR == 0 && (year % LEAP_YEAR_TWO != 0 || 
            year % LEAP_YEAR_THREE == 0))) {
            maxDay = MAX_DAY_FEB_LEAP;
        }

        //Checks if the day is valid so that an invalid day is not stored
        if (day < MIN_DAY || day > maxDay) {
            throw new IllegalArgumentException("Invalid date");
        }
        
        //Using multiplication and addation, the correct date structure is created
        this.date = year * YEAR_MULTIPLIER + month * MONTH_MULTIPLIER + day;
    }

    /**
     * The getter method for the instance field
     * 
     * @return Returns the day in YYYYMMDD format
     */
    public int getDate() {
        return this.date;
    }

    /**
     * The getter for the month portion of the instance field
     * 
     * @return Returns the month
     */
    public int getMonth() {
        return (this.date % YEAR_MULTIPLIER) / MONTH_MULTIPLIER;
    }

    /**
     * The getter for the day portion of the instance field
     * 
     * @return Returns the day
     */
    public int getDay() {
        return this.date % MONTH_MULTIPLIER;
    }

    /**
     * The getter for the year portion of the instance field
     * 
     * @return Returns the year
     */
    public int getYear() {
        return this.date / YEAR_MULTIPLIER;
    }

    /**
     * Puts the date into the correct format
     * 
     * @return Returns the date in the correct formatting
     */
    @Override
    public String toString() {
        String stringMonth = "";
        String stringDay = "";

        if(getMonth() < 10) {
            stringMonth += "0" + getMonth();
        } else {
            stringMonth += "" + getMonth();
        }

        if(getDay() < 10) {
            stringDay += "0" + getDay();
        } else {
            stringDay += "" + getDay();
        }

        return stringMonth + "/" + stringDay + "/" + getYear();
    }

    /**
     * Checks if LedgerDate is equal to another object,
     * 
     * @param o The object being compared
     * @return true if the object is a LedgerDate with the same date
     */
    @Override
    public boolean equals(Object o) {
        //Checks that this and o are equal
        if (this == o) {
            return true;
        }
        //Checks if o is null and if o is a Ledger date
        if (o == null || !(o instanceof LedgerDate)) {
            return false;
        }
        LedgerDate other = (LedgerDate) o;
        return this.date == other.date;
    }

    /**
     * This method is used for sorting LedgerDate objects where earliest date is
     * first
     * 
     * @param o The LedgerDate object to which this LedgerDate is being
     *           compared.
     * 
     * @return negative value if this LedgerDate should be before the other
     *         LedgerDate, positive value if this LedgerDate should be after the
     *         other LedgerDate.
     */
    @Override
    public int compareTo(LedgerDate o) {
        return this.date - o.date;
    }
}