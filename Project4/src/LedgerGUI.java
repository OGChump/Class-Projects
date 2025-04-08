import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

/**
 * This class creates a graphical representation of a Ledger System.
 * 
 * @author Jessica Young Schmidt
 * @author Michelle Glatz
 * @author Suzanne Balik
 */
@SuppressWarnings("serial")
public class LedgerGUI extends JFrame implements ActionListener {
    
    /** Width */
    public static final int WIDTH = 700; 
    
    /** Height */
    public static final int HEIGHT = 675; 
    
    /** X position */
    public static final int X = 100;
    
    /** Y position */
    public static final int Y = 5;
    
    /** Grid rows */
    public static final int ROWS = 10;

    /** Grid columns */
    public static final int COLS = 2;    
    
    /** Inset size */
    public static final int INSET_SIZE = 5;
    
    /** Large field width */
    public static final int LARGE_FIELD_WIDTH = 30;
    
    /** Small field width */
    public static final int SMALL_FIELD_WIDTH = 7;  

    /** Ledger columns */
    public static final int LEDGER_COLS = 4;
    
    /** Ledger column width */
    public static final int COL_WIDTH = 20;
    
    /** Ledger description width */
    public static final int DESC_WIDTH = 150;
    
    /** Table width */
    public static final int TABLE_WIDTH = 250;
    
    /** Table height */
    public static final int TABLE_HEIGHT = 500;    
    
    /** Label for size */
    private JLabel sizeLabel;

    /** Text field for size */
    private JTextField sizeText;

    /** Label for input file */
    private JLabel fileLabel;

    /** Text field input file */
    private JTextField fileText;

    /** Button for New */
    private JButton newButton;

    /** Label for adding entry */
    private JLabel addEntryLabel;

    /** Label for month */
    private JLabel monthLabel;

    /** Text field for month */
    private JTextField monthText;

    /** Label for day */
    private JLabel dayLabel;

    /** Text field for day */
    private JTextField dayText;

    /** Label for year */
    private JLabel yearLabel;

    /** Text field for year */
    private JTextField yearText;

    /** Label for description */
    private JLabel descriptionLabel;

    /** Text field for description */
    private JTextField descriptionText;

    /** Label for amount */
    private JLabel amountLabel;

    /** Text field for amount */
    private JTextField amountText;

    /** Add button */
    private JButton addButton;
    
    /** Ledger button */
    private JButton ledgerButton;

    /** Credit Only button */
    private JButton creditOnlyButton;

    /** Debit Only button */
    private JButton debitOnlyButton;
    
    /** Save button */
    private JButton saveToFileButton;

    /** Label output file */
    private JLabel fileOutputLabel;

    /** Text field for output file */
    private JTextField fileOutputText;

    /** Ledger for system */
    private Ledger ledger;

    /** Table for displaying ledger */
    private JTable table;

    /** Panel for data */
    private JPanel datapanel;

    /**
     * Constructs GUI for Ledger
     */
    public LedgerGUI() {

        super("Ledger GUI");
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
        ledger = null;

        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cont = getContentPane();
        
        // Create a JPanel
        JPanel panel = new JPanel(new GridBagLayout());
    
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(INSET_SIZE,INSET_SIZE,INSET_SIZE,INSET_SIZE); 
        cont.add(panel, BorderLayout.CENTER);
        
        // first row for top panel
        c.gridx = 0;
        c.gridy = 0;    
        c.anchor = GridBagConstraints.WEST;
        
        JPanel toppanel = new JPanel(new GridLayout(ROWS, COLS));
        panel.add(toppanel, c);
         
        sizeLabel = new JLabel("  Size: ");
        toppanel.add(sizeLabel);
        sizeText = new JTextField(SMALL_FIELD_WIDTH);
        toppanel.add(sizeText);

        fileLabel = new JLabel(
                "  Input filename (leave blank if no input file): ");
        toppanel.add(fileLabel);
        fileText = new JTextField(LARGE_FIELD_WIDTH);
        toppanel.add(fileText);

        newButton = new JButton("New Ledger");
        toppanel.add(newButton);

        addEntryLabel = new JLabel("Add Entry to Ledger: ");
        toppanel.add(addEntryLabel);
        monthLabel = new JLabel("Month: ");
        toppanel.add(monthLabel);
        monthText = new JTextField(SMALL_FIELD_WIDTH);
        toppanel.add(monthText);
        dayLabel = new JLabel("Day: ");
        toppanel.add(dayLabel);
        dayText = new JTextField(SMALL_FIELD_WIDTH);
        toppanel.add(dayText);
        yearLabel = new JLabel("Year: ");
        toppanel.add(yearLabel);
        yearText = new JTextField(SMALL_FIELD_WIDTH);
        toppanel.add(yearText);

        descriptionLabel = new JLabel("Description: ");
        toppanel.add(descriptionLabel);
        descriptionText = new JTextField(LARGE_FIELD_WIDTH);
        toppanel.add(descriptionText);

        amountLabel = new JLabel("Amount: ");
        toppanel.add(amountLabel);
        amountText = new JTextField(SMALL_FIELD_WIDTH);
        toppanel.add(amountText);

        addButton = new JButton("Add Entry");
        toppanel.add(addButton);

        datapanel = new JPanel();
        
        //ledger table
        c.gridx = 0;
        // row 1
        c.gridy = 1;       
        c.anchor = GridBagConstraints.CENTER;

        panel.add(datapanel, c);
        
        ledgerButton = new JButton("Full Ledger");
        toppanel.add(ledgerButton);
        creditOnlyButton = new JButton("Credits Only");
        toppanel.add(creditOnlyButton);
        debitOnlyButton = new JButton("Debits Only");
        toppanel.add(debitOnlyButton);

        JPanel outputpanel = new JPanel();

        fileOutputLabel = new JLabel("Output filename: ");
        
        outputpanel.add(fileOutputLabel);
        fileOutputText = new JTextField(LARGE_FIELD_WIDTH);
        outputpanel.add(fileOutputText);

        saveToFileButton = new JButton("Save to File");
        outputpanel.add(saveToFileButton);
        
     
        //outpanel
        c.gridx = 0;
        // row 3
        c.gridy = COLS;           
        c.anchor = GridBagConstraints.WEST;     
        panel.add(outputpanel, c);
              
        
        JPanel space = new JPanel();
        //column 0 for bottom space
        c.gridx = 0;
        // row 4
        c.gridy = COLS + 1;       
        c.anchor = GridBagConstraints.WEST;
        c.weighty = 1;       
        panel.add(space, c);        


        addEntryLabel.setVisible(false);
        monthLabel.setVisible(false);
        monthText.setVisible(false);
        dayLabel.setVisible(false);
        dayText.setVisible(false);
        yearLabel.setVisible(false);
        yearText.setVisible(false);
        descriptionLabel.setVisible(false);
        descriptionText.setVisible(false);
        amountLabel.setVisible(false);
        amountText.setVisible(false);
        addButton.setVisible(false);
        fileOutputLabel.setVisible(false);
        fileOutputText.setVisible(false);
        saveToFileButton.setVisible(false);
        ledgerButton.setVisible(false);
        creditOnlyButton.setVisible(false);
        debitOnlyButton.setVisible(false);
        
        newButton.addActionListener(this);
        addButton.addActionListener(this);
        saveToFileButton.addActionListener(this);
        ledgerButton.addActionListener(this);
        creditOnlyButton.addActionListener(this);
        debitOnlyButton.addActionListener(this);

        setVisible(true);
    }

    /**
     * Starts LedgerGUI
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        new LedgerGUI();
    }

    /**
     * Handle processing of button presses
     * 
     * @param e
     *            event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newButton) {
            try {
                int size = Integer.parseInt(sizeText.getText());
                String filename = fileText.getText().trim();

                // Create ledger either empty or from file
                if (filename.equals("")) {
                    ledger = new Ledger(size);
                } else {
                    ledger = LedgerFileProcessing.readLedgerFromFile(filename,
                            size);
                }

                // make initial fields and text boxes invisible
                sizeText.setEditable(false);
                fileText.setEditable(false);
                sizeText.setVisible(false);
                fileText.setVisible(false);
                sizeLabel.setVisible(false);
                fileLabel.setVisible(false);
                newButton.setVisible(false);

                // Make fields and text boxes visible
                addEntryLabel.setVisible(true);
                monthLabel.setVisible(true);
                monthText.setVisible(true);
                dayLabel.setVisible(true);
                dayText.setVisible(true);
                yearLabel.setVisible(true);
                yearText.setVisible(true);
                descriptionLabel.setVisible(true);
                descriptionText.setVisible(true);
                amountLabel.setVisible(true);
                amountText.setVisible(true);
                addButton.setVisible(true);
                fileOutputLabel.setVisible(true);
                fileOutputText.setVisible(true);
                saveToFileButton.setVisible(true);
                ledgerButton.setVisible(true);
                creditOnlyButton.setVisible(true);
                debitOnlyButton.setVisible(true);

                // Initialize table for displaying ledger
                String[] columnNames = { " Date", "Description", "Amount",
                    "Balance" };

                Object[][] data = new Object[Integer
                        .parseInt(sizeText.getText())][LEDGER_COLS];

                table = new JTable(data, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                TableColumn column = null;
                for (int i = 0; i < LEDGER_COLS; i++) {
                    column = table.getColumnModel().getColumn(i);
                    if (i == 1) {
                        column.setPreferredWidth(DESC_WIDTH); //second column is bigger
                    } else {
                        column.setPreferredWidth(COL_WIDTH);
                    }
                }
                
                
                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

                
                table.setPreferredScrollableViewportSize(
                        new Dimension(TABLE_HEIGHT, TABLE_WIDTH));
                table.setFillsViewportHeight(true);
                // Create the scroll pane and add the table to it.
                JScrollPane scrollPane = new JScrollPane(table);

                // Add the scroll pane to this panel.
                datapanel.add(scrollPane);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid integer.");
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, iae.getMessage());
            }
            fillTable(ledger);
        } else if (e.getSource() == addButton) {
            try {
                int month = Integer.parseInt(monthText.getText());
                int day = Integer.parseInt(dayText.getText());
                int year = Integer.parseInt(yearText.getText());
                int amount = Integer.parseInt(amountText.getText());
                String description = descriptionText.getText().trim();

                ledger.addEntry(month, day, year, description, amount);

                monthText.setText("");
                dayText.setText("");
                yearText.setText("");
                descriptionText.setText("");
                amountText.setText("");

                if (ledger.isFull()) {
                    monthLabel.setVisible(false);
                    monthText.setVisible(false);
                    dayLabel.setVisible(false);
                    dayText.setVisible(false);
                    yearLabel.setVisible(false);
                    yearText.setVisible(false);
                    descriptionLabel.setVisible(false);
                    descriptionText.setVisible(false);
                    amountLabel.setVisible(false);
                    amountText.setVisible(false);
                    addButton.setVisible(false);
                    addEntryLabel.setText("Ledger is full.");

                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid integer.");
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, iae.getMessage());
            }
            fillTable(ledger);
        } else if (e.getSource() == saveToFileButton) {
            System.out.println("Clicked save button");
            try {
                String filename = fileOutputText.getText().trim();
                LedgerFileProcessing.writeLedgerToFile(filename, ledger);
                JOptionPane.showMessageDialog(null, "Saved to " + filename);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, iae.getMessage());
            }
        } else if (e.getSource() == creditOnlyButton) {
            fillTable(ledger.getCredits());
        } else if (e.getSource() == debitOnlyButton) {
            fillTable(ledger.getDebits());
        } else if(e.getSource() == ledgerButton) {
            fillTable(ledger);
        }
    }

    /**
     * Fills table with updated values
     * @param ledger ledger
     */
    private void fillTable(Ledger ledger) {
        int numEntries = ledger.getNumEntries();
        int size = ledger.getSize();
        for (int i = 0; i < size; i++) {
            if (i < numEntries) {
                table.setValueAt(ledger.getDateAt(i).toString(), i, 0);
                table.setValueAt(ledger.getDescriptionAt(i), i, 1);
                table.setValueAt(ledger.getAmountAt(i), i, 2);
                table.setValueAt(ledger.getBalanceAt(i), i, 3);
            } else {
                table.setValueAt("", i, 0);
                table.setValueAt("", i, 1);
                table.setValueAt("", i, 2);
                table.setValueAt("", i, 3);
            }
        }
        table.revalidate();
    }


}