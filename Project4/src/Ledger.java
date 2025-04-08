import java.util.Comparator;

/**
 * This class is responsible for storing LedgerEntry objects
 * 
 * @author Joseph Rosemond
 * 
 * @version 4/7/2025
 */
public class Ledger {

    /**
     * Default Maximum number of ledger entries
     */
    public static final int DEFAULT_MAX_SIZE = 30;

    /**
     * Maxium number of entries
     */
    private int size;

    /**
     * Number of entries currently in the Ledger
     */
    private int numEntries;

    /**
     * Array to store the LedgerEntry objects
     */
    private LedgerEntry[] entries;

    /**
     * Array to store the current balance after each entry
     */
    private int[] balances;

    /**
     * The current overall balance
     */
    private int balance;

    /**
     * Creates a ledger with the default max size;
     */
    public Ledger() {
        this(DEFAULT_MAX_SIZE);
    }

    /**
     * Creates a ledger with the actual max size
     * 
     * @param size the max number of ledger entries
     * @throws IllegalArgumentException if the size is negative
     */
    public Ledger(int size) {
        //Checks to make sure the size is valid
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid Size");
        }
        this.size = size;
        this.numEntries = 0;
        this.entries = new LedgerEntry[size];
        this.balances = new int[size];
        this.balance = 0;
    }

    /**
     * Checks if the Ledger is full 
     * 
     * @return true if the ledger has reached the max amount of entries
     */
    public boolean isFull() {
        return numEntries >= size;
    }

    /**
     * Returns the max size of ledger
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the current number of entries
     * 
     * @return the number of entries
     */
    public int getNumEntries() {
        return numEntries;
    }

    /**
     * Returns the current total balance of the ledger 
     * 
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * Returns the description of the entry 
     * 
     * @param i the index of the entry
     * @return the description of the entry
     * @throws IllegalArgumentException if the index was not valid
     */
    public String getDescriptionAt(int i) {
        if (i < 0 || i >= numEntries) {
            throw new IllegalArgumentException("Invalid date");
        }
        return entries[i].getDescription();
    }

    /**
     * Returns the date of the entry
     * 
     * @param i index of the entry 
     * @return the Ledgerdate of the entry
     * @throws IllegalArgumentException if the index is not valid
     */
    public LedgerDate getDateAt(int i) {
        if (i < 0 || i >= numEntries) {
            throw new IllegalArgumentException("Invalid index");
        }
        return entries[i].getDate();
    }

    /**
     * Returns the amount of the entry 
     * 
     * @param i the index of the entry 
     * @return the amount
     * @throws IllegalArgumentException if the index is not valid
     */
    public int getAmountAt(int i) {
        if (i < 0 || i >= numEntries) {
            throw new IllegalArgumentException("Invalid index");
        }
        return entries[i].getAmount();
    }

    /**
     * Returns the running balance after the entry
     * 
     * @param i the index of the entry
     * @return the balance after the entry
     * @throws IllegalArgumentException if the index is not valid
     */
    public int getBalanceAt(int i) {
        if (i < 0 || i >= numEntries) {
            throw new IllegalArgumentException("Invalid index");
        }
        return balances[i];
    }

    /**
     * Sorts the array of entries and updates balance array and balance
     */
    private void sort() {
        //Sorting elements 
        for (int i = 1; i < numEntries; i++) {
            LedgerEntry insert = entries[i];
            int j = i - 1;
    
            //Compares insert with each entry in the sorted part 
            while (j >= 0 && entries[j].compareTo(insert) > 0) {

                //shifts the entry
                entries[j + 1] = entries[j];
                j--;
            }
            entries[j + 1] = insert;
        }  
    
        // After sorting, update the running balances
        int currentTotal = 0;
        for (int i = 0; i < numEntries; i++) {
            currentTotal += entries[i].getAmount();
            balances[i] = currentTotal;
        }
        balance = currentTotal;
    }

    /**
     * Adds ledger entry to the ledger
     * 
     * @param entry the LedgerEntry to add
     * @throws IllegalArgumentException if the ledger is full or if the entry is null
     */
    public void addEntry(LedgerEntry entry) {
        //Checks if the entry is valid
        if (entry == null) {
            throw new IllegalArgumentException("Null entry");
        }

        //Checks if the Ledger is full
        if (isFull()) {
            throw new IllegalArgumentException("Ledger is full");
        }

        //Add the entry to the first free position
        entries[numEntries] = entry;
        numEntries++;

        //Sort the ledger so that entries remain in order
        sort();
    }

    /**
     * Creates and adds a LedgerEntry to the ledger from the given parameters
     * 
     * @param month The month of the entry
     * @param day The day of the entry
     * @param year The year of the entry
     * @param description The description of the entry
     * @param amount The amount of the entry
     * @throws IllegalArgumentException if any parameter is not valid
     */
    public void addEntry(int month, int day, int year, String description, int amount) {
        LedgerEntry entry = new LedgerEntry(month, day, year, description, amount);
        addEntry(entry);
    }

    /**
     * Returns a new Ledger containing only credit entries
     * 
     * @return a Ledger of credits
     */
    public Ledger getCredits() {
        Ledger creditsLedger = new Ledger(this.size);
        for (int i = 0; i < numEntries; i++) {
            if (entries[i].getAmount() > 0) {
                creditsLedger.addEntry(entries[i]);
            }
        }
        return creditsLedger;
    }

    /**
     * Returns a new Ledger containing on debit entries
     * 
     * @return a Ledger of debits
     */
    public Ledger getDebits() {
        Ledger debitsLedger = new Ledger(this.size);
        for (int i = 0; i < numEntries; i++) {
            if (entries[i].getAmount() < 0) {
                debitsLedger.addEntry(entries[i]);
            }
        }
        return debitsLedger;
    }

    /**
     * Returns a new Ledger with only entries with a date range
     * 
     * @param start The start date
     * @param end The end date 
     * @return A ledger of entries within a date range
     * @throws IllegalArgumentException if either date is null
     */
    public Ledger getDateRange(LedgerDate start, LedgerDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Null date");
        }
        Ledger rangeLedger = new Ledger(this.size);
        for (int i = 0; i < numEntries; i++) {
            LedgerDate entryDate = entries[i].getDate();
            if (entryDate.compareTo(start) >= 0 && entryDate.compareTo(end) <= 0) {
                rangeLedger.addEntry(entries[i]);
            }
        }
        return rangeLedger;
    }

    /**
     * Returns a string of the ledger
     * 
     * @return The Ledger in the correct format
     */
    public String toString() {

        //Start with the header
        String result = "Date,Description,Amount,Balance\n";
        
        //Loop through the ledger entries
        for (int i = 0; i < numEntries; i++) {

            //Proper format
            result = result + entries[i].toString() + "," + balances[i];
            // If this is not the last entry, add a newline character.
            if (i < numEntries - 1) {
                result = result + "\n";
            }
        }
        return result;
    }

    /**
     * Checks if Ledger is equal to another object
     * 
     * @param o The object being compared
     * @return true if the ledgers are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !(o instanceof Ledger))
            return false;
        Ledger other = (Ledger) o;
        if (this.size != other.size || this.numEntries != other.numEntries)
            return false;
        for (int i = 0; i < numEntries; i++) {
            if (!this.entries[i].equals(other.entries[i]) || this.balances[i] != other.balances[i])
                return false;
        }
        return true;
    }


    /** Comparator used to sort LedgerEntry objects */
    private static Comparator<LedgerEntry> entryCompWithNull = new Comparator<LedgerEntry>() {
        @Override
        public int compare(LedgerEntry o1, LedgerEntry o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return o1.compareTo(o2);
        }
    };

}