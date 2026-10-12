import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Expense {

    int id;
    String date;
    String category;
    String description;
    double amount;

    Expense(int id, String date, String category,
            String description, double amount) {

        this.id = id;
        this.date = date;
        this.category = category;
        this.description = description;
        this.amount = amount;
    }
}

public class ExpenseTracker extends JFrame {

    private ArrayList<Expense> expenses = new ArrayList<>();

    private int nextId = 1;

    private JTextField dateField;
    private JComboBox<String> categoryBox;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField searchField;

    private JTable expenseTable;
    private DefaultTableModel tableModel;

    private JLabel totalAmountLabel;
    private JLabel expenseCountLabel;
    private JLabel averageAmountLabel;

    private final Color BACKGROUND =
            new Color(15, 18, 30);

    private final Color CARD =
            new Color(25, 29, 45);

    private final Color CARD_LIGHT =
            new Color(32, 37, 56);

    private final Color INPUT =
            new Color(19, 23, 37);

    private final Color PURPLE =
            new Color(124, 92, 255);

    private final Color PURPLE_DARK =
            new Color(91, 65, 205);

    private final Color TEXT =
            new Color(245, 247, 255);

    private final Color TEXT_SECONDARY =
            new Color(160, 166, 190);

    private final Color BORDER =
            new Color(55, 61, 84);

    private final Color RED =
            new Color(239, 83, 80);

    private final Color GREEN =
            new Color(67, 201, 145);


    public ExpenseTracker() {

        setTitle("ExTra - Personal Expense Tracker");

        setSize(1200, 750);

        setMinimumSize(
                new Dimension(1000, 650)
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();
    }


    private void createGUI() {

        getContentPane()
                .setBackground(BACKGROUND);

        setLayout(
                new BorderLayout()
        );

        createHeader();

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(20, 20)
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20, 25, 20, 25
                )
        );


        JPanel dashboard =
                createDashboard();

        mainPanel.add(
                dashboard,
                BorderLayout.NORTH
        );


        JPanel content =
                new JPanel(
                        new BorderLayout(20, 0)
                );

        content.setBackground(
                BACKGROUND
        );


        JPanel addPanel =
                createAddExpensePanel();

        content.add(
                addPanel,
                BorderLayout.WEST
        );


        JPanel tablePanel =
                createTablePanel();

        content.add(
                tablePanel,
                BorderLayout.CENTER
        );


        mainPanel.add(
                content,
                BorderLayout.CENTER
        );


        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }


    private void createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                CARD
        );

        header.setBorder(
                new EmptyBorder(
                        18, 28, 18, 28
                )
        );


        JPanel logoPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                0
                        )
                );

        logoPanel.setOpaque(false);


        JLabel logo =
                new JLabel("₹");

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        logo.setForeground(
                Color.WHITE
        );

        logo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        logo.setPreferredSize(
                new Dimension(48, 48)
        );

        logo.setOpaque(true);

        logo.setBackground(
                PURPLE
        );


        JLabel title =
                new JLabel("ExTra");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(
                TEXT
        );


        JLabel subtitle =
                new JLabel(
                        "Personal Expense Manager"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                TEXT_SECONDARY
        );


        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.add(title);

        textPanel.add(
                Box.createVerticalStrut(3)
        );

        textPanel.add(subtitle);


        logoPanel.add(logo);
        logoPanel.add(textPanel);


        JLabel status =
                new JLabel(
                        "●  TRACKING YOUR FINANCES"
                );

        status.setForeground(
                GREEN
        );

        status.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );


        header.add(
                logoPanel,
                BorderLayout.WEST
        );

        header.add(
                status,
                BorderLayout.EAST
        );


        add(
                header,
                BorderLayout.NORTH
        );
    }


    private JPanel createDashboard() {

        JPanel dashboard =
                new JPanel(
                        new GridLayout(
                                1, 3, 15, 0
                        )
                );

        dashboard.setOpaque(false);


        JPanel totalCard =
                createCard(
                        "TOTAL SPENT",
                        "₹0.00",
                        "Your overall spending"
                );


        JPanel countCard =
                createCard(
                        "TOTAL EXPENSES",
                        "0",
                        "Number of transactions"
                );


        JPanel averageCard =
                createCard(
                        "AVERAGE EXPENSE",
                        "₹0.00",
                        "Average per transaction"
                );


        totalAmountLabel =
                findMainLabel(totalCard);

        expenseCountLabel =
                findMainLabel(countCard);

        averageAmountLabel =
                findMainLabel(averageCard);


        dashboard.add(totalCard);

        dashboard.add(countCard);

        dashboard.add(averageCard);


        return dashboard;
    }


    private JPanel createCard(
            String title,
            String value,
            String subtitle) {


        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10, 5
                        )
                );


        panel.setBackground(
                CARD
        );


        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                TEXT_SECONDARY
        );


        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        valueLabel.setForeground(
                TEXT
        );


        JLabel subtitleLabel =
                new JLabel(subtitle);

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        subtitleLabel.setForeground(
                TEXT_SECONDARY
        );


        JPanel center =
                new JPanel();

        center.setOpaque(false);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );


        center.add(titleLabel);

        center.add(
                Box.createVerticalStrut(7)
        );

        center.add(valueLabel);

        center.add(
                Box.createVerticalStrut(5)
        );

        center.add(subtitleLabel);


        panel.add(
                center,
                BorderLayout.CENTER
        );


        return panel;
    }


    private JLabel findMainLabel(
            JPanel panel) {

        JPanel center =
                (JPanel) panel
                        .getComponent(0);

        return (JLabel)
                center.getComponent(2);
    }


    private JPanel createAddExpensePanel() {

        JPanel panel =
                new JPanel();


        panel.setPreferredSize(
                new Dimension(285, 0)
        );


        panel.setBackground(
                CARD
        );


        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                22,
                                20,
                                22,
                                20
                        )
                )
        );


        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel heading =
                new JLabel(
                        "Add New Expense"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        heading.setForeground(
                TEXT
        );


        JLabel description =
                new JLabel(
                        "Record where your money goes"
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        description.setForeground(
                TEXT_SECONDARY
        );


        panel.add(heading);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(description);


        panel.add(
                Box.createVerticalStrut(25)
        );


        panel.add(
                createLabel("DATE")
        );


        dateField =
                createTextField();

        panel.add(dateField);


        panel.add(
                Box.createVerticalStrut(15)
        );


        panel.add(
                createLabel("CATEGORY")
        );


        String[] categories = {

                "Food",
                "Transport",
                "Shopping",
                "Entertainment",
                "Education",
                "Bills",
                "Health",
                "Travel",
                "Other"
        };


        categoryBox =
                new JComboBox<>(
                        categories
                );


        styleComboBox(
                categoryBox
        );


        panel.add(
                categoryBox
        );


        panel.add(
                Box.createVerticalStrut(15)
        );


        panel.add(
                createLabel(
                        "DESCRIPTION"
                )
        );


        descriptionField =
                createTextField();


        panel.add(
                descriptionField
        );


        panel.add(
                Box.createVerticalStrut(15)
        );


        panel.add(
                createLabel("AMOUNT")
        );


        amountField =
                createTextField();


        panel.add(
                amountField
        );


        panel.add(
                Box.createVerticalStrut(25)
        );


        JButton addButton =
                createButton(
                        "＋  ADD EXPENSE",
                        PURPLE
                );


        addButton.addActionListener(
                e -> addExpense()
        );


        panel.add(
                addButton
        );


        panel.add(
                Box.createVerticalStrut(10)
        );


        JButton clearButton =
                createButton(
                        "CLEAR FIELDS",
                        CARD_LIGHT
                );


        clearButton.addActionListener(
                e -> clearFields()
        );


        panel.add(
                clearButton
        );


        panel.add(
                Box.createVerticalGlue()
        );


        JLabel tip =
                new JLabel(
                        "<html><center>" +
                                "Tip: Keep your expenses<br>" +
                                "updated for better tracking." +
                                "</center></html>"
                );


        tip.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        tip.setForeground(
                TEXT_SECONDARY
        );


        tip.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );


        panel.add(tip);


        return panel;
    }


    private JPanel createTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0, 15
                        )
                );


        panel.setBackground(
                CARD
        );


        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );


        JPanel top =
                new JPanel(
                        new BorderLayout(
                                10, 0
                        )
                );


        top.setOpaque(false);


        JLabel heading =
                new JLabel(
                        "Recent Expenses"
                );


        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );


        heading.setForeground(
                TEXT
        );


        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                8, 0
                        )
                );


        searchPanel.setOpaque(false);


        searchField =
                new JTextField();


        searchField.setPreferredSize(
                new Dimension(
                        190,
                        36
                )
        );


        styleTextField(
                searchField
        );


        JButton allButton =
                createSmallButton("ALL");


        JButton searchButton =
                createSmallButton(
                        "SEARCH"
                );


        allButton.addActionListener(
                e -> displayExpenses()
        );


        searchButton.addActionListener(
                e -> searchCategory()
        );


        searchPanel.add(
                allButton,
                BorderLayout.WEST
        );


        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );


        searchPanel.add(
                searchButton,
                BorderLayout.EAST
        );


        top.add(
                heading,
                BorderLayout.WEST
        );


        top.add(
                searchPanel,
                BorderLayout.EAST
        );


        panel.add(
                top,
                BorderLayout.NORTH
        );


        String[] columns = {

                "ID",
                "DATE",
                "CATEGORY",
                "DESCRIPTION",
                "AMOUNT"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        expenseTable =
                new JTable(
                        tableModel
                );


        styleTable();


        JScrollPane scrollPane =
                new JScrollPane(
                        expenseTable
                );


        scrollPane.setBackground(
                CARD
        );


        scrollPane.getViewport()
                .setBackground(CARD);


        scrollPane.setOpaque(true);


        scrollPane.getViewport()
                .setOpaque(true);


        scrollPane.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );


        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );


        bottom.setOpaque(false);


        JButton summaryButton =
                createSmallButton(
                        "CATEGORY SUMMARY"
                );


        JButton deleteButton =
                createSmallButton(
                        "DELETE SELECTED"
                );


        deleteButton.setBackground(
                RED
        );


        summaryButton.addActionListener(
                e -> showSummary()
        );


        deleteButton.addActionListener(
                e -> deleteExpense()
        );


        bottom.add(
                summaryButton
        );


        bottom.add(
                deleteButton
        );


        panel.add(
                bottom,
                BorderLayout.SOUTH
        );


        return panel;
    }


    private void styleTable() {

        expenseTable.setOpaque(true);

        expenseTable.setBackground(
                CARD
        );

        expenseTable.setForeground(
                TEXT
        );


        expenseTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );


        expenseTable.setRowHeight(38);


        expenseTable.setGridColor(
                new Color(
                        45,
                        50,
                        70
                )
        );


        expenseTable.setSelectionBackground(
                new Color(
                        70,
                        57,
                        120
                )
        );


        expenseTable.setSelectionForeground(
                Color.WHITE
        );


        expenseTable.setShowVerticalLines(
                false
        );


        expenseTable.setFillsViewportHeight(
                true
        );


        JTableHeader header =
                expenseTable
                        .getTableHeader();


        header.setBackground(
                CARD_LIGHT
        );


        header.setForeground(
                TEXT
        );


        header.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );


        header.setOpaque(true);


        header.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );


        expenseTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(45);


        expenseTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(100);


        expenseTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(120);


        expenseTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(230);


        expenseTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(120);


        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component
                    getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {


                        Component component =
                                super
                                        .getTableCellRendererComponent(
                                                table,
                                                value,
                                                isSelected,
                                                hasFocus,
                                                row,
                                                column
                                        );


                        setBorder(
                                new EmptyBorder(
                                        0,
                                        10,
                                        0,
                                        10
                                )
                        );


                        if (!isSelected) {

                            if (row % 2 == 0) {

                                component
                                        .setBackground(
                                                CARD
                                        );

                            } else {

                                component
                                        .setBackground(
                                                new Color(
                                                        29,
                                                        34,
                                                        51
                                                )
                                        );
                            }


                            component
                                    .setForeground(
                                            TEXT
                                    );
                        }


                        return component;
                    }
                };


        for (int i = 0;
             i < expenseTable
                     .getColumnCount();
             i++) {

            expenseTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            renderer
                    );
        }
    }


    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);


        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );


        label.setForeground(
                TEXT_SECONDARY
        );


        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );


        return label;
    }


    private JTextField createTextField() {

        JTextField field =
                new JTextField();


        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );


        styleTextField(
                field
        );


        return field;
    }


    private void styleTextField(
            JTextField field) {

        field.setBackground(
                INPUT
        );


        field.setForeground(
                TEXT
        );


        field.setCaretColor(
                TEXT
        );


        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );


        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );
    }


    private void styleComboBox(
            JComboBox<String> box) {

        box.setBackground(
                INPUT
        );


        box.setForeground(
                Color.WHITE
        );


        box.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );


        box.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );


        box.setOpaque(true);


        box.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );


        box.setRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component
                    getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus) {


                        JLabel label =
                                (JLabel)
                                        super
                                                .getListCellRendererComponent(
                                                        list,
                                                        value,
                                                        index,
                                                        isSelected,
                                                        cellHasFocus
                                                );


                        label.setBackground(
                                isSelected
                                        ? PURPLE_DARK
                                        : INPUT
                        );


                        label.setForeground(
                                Color.WHITE
                        );


                        label.setBorder(
                                new EmptyBorder(
                                        6,
                                        8,
                                        6,
                                        8
                                )
                        );


                        return label;
                    }
                }
        );
    }


    private JButton createButton(
            String text,
            Color color) {


        JButton button =
                new JButton(text);


        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        button.setBackground(
                color
        );


        button.setForeground(
                Color.WHITE
        );


        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );


        button.setFocusPainted(false);


        button.setOpaque(true);


        button.setContentAreaFilled(
                true
        );


        button.setBorderPainted(
                false
        );


        button.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                color.brighter()
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                color
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }
                }
        );


        return button;
    }


    private JButton createSmallButton(
            String text) {


        JButton button =
                new JButton(text);


        button.setBackground(
                CARD_LIGHT
        );


        button.setForeground(
                Color.WHITE
        );


        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        11
                )
        );


        button.setFocusPainted(
                false
        );


        button.setOpaque(true);


        button.setContentAreaFilled(
                true
        );


        button.setBorderPainted(
                true
        );


        button.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                PURPLE_DARK
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        if (button.getText()
                                .equals(
                                        "DELETE SELECTED"
                                )) {

                            button.setBackground(
                                    RED
                            );

                        } else {

                            button.setBackground(
                                    CARD_LIGHT
                            );
                        }


                        button.setForeground(
                                Color.WHITE
                        );
                    }
                }
        );


        return button;
    }


    private void addExpense() {

        String date =
                dateField
                        .getText()
                        .trim();


        String category =
                categoryBox
                        .getSelectedItem()
                        .toString();


        String description =
                descriptionField
                        .getText()
                        .trim();


        String amountText =
                amountField
                        .getText()
                        .trim();


        if (date.isEmpty() ||
                description.isEmpty() ||
                amountText.isEmpty()) {


            showMessage(
                    "Please fill all required fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );


            return;
        }


        double amount;


        try {

            amount =
                    Double.parseDouble(
                            amountText
                    );


        } catch (NumberFormatException e) {


            showMessage(
                    "Please enter a valid amount.",
                    "Invalid Amount",
                    JOptionPane.ERROR_MESSAGE
            );


            return;
        }


        if (amount <= 0) {


            showMessage(
                    "Amount must be greater than ₹0.",
                    "Invalid Amount",
                    JOptionPane.WARNING_MESSAGE
            );


            return;
        }


        Expense expense =
                new Expense(
                        nextId,
                        date,
                        category,
                        description,
                        amount
                );


        expenses.add(
                expense
        );


        nextId++;


        displayExpenses();


        clearFields();


        showMessage(
                "Expense added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void displayExpenses() {

        tableModel.setRowCount(0);


        for (Expense expense :
                expenses) {


            Object[] row = {

                    expense.id,

                    expense.date,

                    expense.category,

                    expense.description,

                    String.format(
                            "₹%.2f",
                            expense.amount
                    )
            };


            tableModel.addRow(
                    row
            );
        }


        updateDashboard();
    }


    private void searchCategory() {

        String search =
                searchField
                        .getText()
                        .trim()
                        .toLowerCase();


        tableModel.setRowCount(0);


        for (Expense expense :
                expenses) {


            if (expense.category
                    .toLowerCase()
                    .contains(search)) {


                Object[] row = {

                        expense.id,

                        expense.date,

                        expense.category,

                        expense.description,

                        String.format(
                                "₹%.2f",
                                expense.amount
                        )
                };


                tableModel.addRow(
                        row
                );
            }
        }
    }


    private void deleteExpense() {

        int selectedRow =
                expenseTable
                        .getSelectedRow();


        if (selectedRow == -1) {


            showMessage(
                    "Select an expense from the table first.",
                    "No Expense Selected",
                    JOptionPane.WARNING_MESSAGE
            );


            return;
        }


        int id =
                (int)
                        tableModel.getValueAt(
                                selectedRow,
                                0
                        );


        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this expense?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );


        if (result ==
                JOptionPane.YES_OPTION) {


            for (int i = 0;
                 i < expenses.size();
                 i++) {


                if (expenses
                        .get(i)
                        .id == id) {


                    expenses.remove(i);

                    break;
                }
            }


            displayExpenses();
        }
    }


    private void clearFields() {

        dateField.setText("");

        categoryBox.setSelectedIndex(0);

        descriptionField.setText("");

        amountField.setText("");

        dateField.requestFocus();
    }


    private void updateDashboard() {

        double total = 0;


        for (Expense expense :
                expenses) {

            total += expense.amount;
        }


        double average = 0;


        if (!expenses.isEmpty()) {

            average =
                    total /
                            expenses.size();
        }


        totalAmountLabel.setText(
                String.format(
                        "₹%.2f",
                        total
                )
        );


        expenseCountLabel.setText(
                String.valueOf(
                        expenses.size()
                )
        );


        averageAmountLabel.setText(
                String.format(
                        "₹%.2f",
                        average
                )
        );
    }


    private void showSummary() {

        if (expenses.isEmpty()) {


            showMessage(
                    "No expenses available yet.",
                    "Category Summary",
                    JOptionPane.INFORMATION_MESSAGE
            );


            return;
        }


        Map<String, Double> summary =
                new HashMap<>();


        for (Expense expense :
                expenses) {


            summary.put(
                    expense.category,
                    summary.getOrDefault(
                            expense.category,
                            0.0
                    ) + expense.amount
            );
        }


        StringBuilder text =
                new StringBuilder();


        text.append(
                "CATEGORY-WISE SPENDING\n"
        );


        text.append(
                "────────────────────────\n\n"
        );


        for (Map.Entry<String, Double>
                entry :
                summary.entrySet()) {


            text.append(
                    String.format(
                            "%-18s ₹%.2f%n",
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }


        JTextArea area =
                new JTextArea(
                        text.toString()
                );


        area.setEditable(false);


        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );


        area.setBackground(
                CARD
        );


        area.setForeground(
                TEXT
        );


        area.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );


        JOptionPane.showMessageDialog(
                this,
                area,
                "Category Summary",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void showMessage(
            String message,
            String title,
            int type) {


        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                type
        );
    }


    public static void main(
            String[] args) {


        try {

            UIManager.setLookAndFeel(
                    UIManager
                            .getCrossPlatformLookAndFeelClassName()
            );


        } catch (Exception e) {

            e.printStackTrace();
        }


        SwingUtilities.invokeLater(
                () -> {

                    ExpenseTracker app =
                            new ExpenseTracker();

                    app.setVisible(true);
                }
        );
    }
}