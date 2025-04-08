/**
 * This class is responsible for entries in the ledger
 * 
 * @author Joseph Rosemond
 * 
 * @version 4/7/2025
 */
public class LedgerEntry implements Comparable<LedgerEntry> {

    /**
     * The date of the Ledger entry
     */
    private LedgerDate date;

    /**
     * The description of the Ledger entry 
     */
    private String description;

    /**
     * The amount for the Ledger entry
     */
    private int amount;

    /**
     * Creates a LedgerEntry with dates descriptions and amounts
     * 
     * @param month The month of the entry
     * @param day The day of the entry
     * @param year The year of the entry
     * @param description The description of the entry
     * @param amount The amount of the entry
     * @throws IllegalArgumentException If any of the parameters are invalid
     */
    public LedgerEntry(int month, int day, int year, String description, int amount) {

        //Creates the Ledger date and uses it from LedgerDate class
        this.date = new LedgerDate(month, day, year);

        //Checks if the description is null 
        if (description == null) {
            throw new IllegalArgumentException("Null description");
        }

        //Removes the whitespace for the start and end.
        if (description.trim().length() == 0) {
            throw new IllegalArgumentException("Empty or all whitespace description");
        }

        if (amount == 0) {
            throw new IllegalArgumentException("Amount is zero");
        }

        this.description = description;
        this.amount = amount;
    }

    /**
     * Returns the date of this Ledger entry
     * 
     * @return The Ledger entry dates
     */
    public LedgerDate getDate() {
        return this.date;
    }

    /**
     * Returns the description of the Ledger entry
     * 
     * @return the description as a string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the amount of the Ledger entry
     * 
     * @return the amount 
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Returns the correct format for the Ledger entry
     * 
     * @return the correct format for the entry
     */
    @Override
    public String toString() {
        return date.toString() + "," + description + "," + amount;
    }

    /**
     * Checks if this LedgerEntry is equal to another object
     * 
     * @param o The object being compared
     * @return true if the entries are equal
     */
    public boolean equals(Object o) {
        //Checks if this and o are equal
        if (this == o) {
            return true;
        }
        //Chekcs if o is null and if o is a Ledger entry
        if (o == null || !(o instanceof LedgerEntry)) {
            return false;
        }
        LedgerEntry other = (LedgerEntry) o;
        return this.date.equals(other.date) &&
                this.description.equals(other.description) &&
                this.amount == other.amount;
    }

    /**
     * This method is used for sorting LedgerEntry objects where earliest date
     * is first then description alphabetically then amount increasing
     * 
     * @param o
     *            The LedgerEntry object to which this LedgerEntry is being
     *            compared.
     * @return negative value if this LedgerEntry should be before the other
     *         LedgerEntry, positive value if this LedgerEntry should be after  
     *         the other LedgerEntry.
     */
    @Override
    public int compareTo(LedgerEntry o) {
        if (this.equals(o)) {
            return 0;
        }
        if (!this.date.equals(o.date)) {
            return this.date.compareTo(o.date);
        }
        if (!this.description.equals(o.description)) {
            return this.description.compareTo(o.description);
        }
        return this.amount - o.amount;
    }
}