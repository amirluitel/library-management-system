import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;

//Adding books details
public class AddBookDetails extends JFrame
{
    private JPanel contentPane;
    private JTextField bookId;
    private JTextField bookName;
    private JTextField bookAuthor;
    private JTextField bookPrice;
    private JTextField bookAvailability;
    
    public AddBookDetails() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setTitle("Library Management System");
        this.setLocationRelativeTo(null);
        final JLabel lblBookId = new JLabel("Book ID");
        lblBookId.setBounds(21, 50, 48, 14);
        this.contentPane.add(lblBookId);
        final JLabel lblNewLabel = new JLabel("Book Name");
        lblNewLabel.setBounds(21, 86, 70, 14);
        this.contentPane.add(lblNewLabel);
        final JLabel lblNewLabel_1 = new JLabel("Author Name");
        lblNewLabel_1.setBounds(21, 122, 91, 14);
        this.contentPane.add(lblNewLabel_1);
        final JLabel lblNewLabel_2 = new JLabel("Price");
        lblNewLabel_2.setBounds(21, 155, 48, 14);
        this.contentPane.add(lblNewLabel_2);
        final JLabel lblNewLabel_3 = new JLabel("Availability");
        lblNewLabel_3.setBounds(21, 180, 81, 14);
        this.contentPane.add(lblNewLabel_3);
        final JLabel lblType = new JLabel("Type");
        lblType.setBounds(21, 205, 48, 14);
        this.contentPane.add(lblType);
        (this.bookId = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    e.consume();
                }
            }
        });
        this.bookId.setBounds(152, 47, 131, 20);
        this.contentPane.add(this.bookId);
        this.bookId.setColumns(10);
        (this.bookName = new JTextField()).setBounds(152, 83, 131, 20);
        this.contentPane.add(this.bookName);
        this.bookName.setColumns(10);
        (this.bookAuthor = new JTextField()).setBounds(152, 114, 131, 20);
        this.contentPane.add(this.bookAuthor);
        this.bookAuthor.setColumns(10);
        (this.bookPrice = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    e.consume();
                }
            }
        });
        this.bookPrice.setBounds(152, 145, 131, 20);
        this.contentPane.add(this.bookPrice);
        this.bookPrice.setColumns(10);
        (this.bookAvailability = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    e.consume();
                }
            }
        });
        this.bookAvailability.setBounds(152, 177, 131, 20);
        this.contentPane.add(this.bookAvailability);
        this.bookAvailability.setColumns(10);
        final JComboBox bookType = new JComboBox();
        bookType.setModel(new DefaultComboBoxModel<String>(new String[] { "Art", "Literature", "Music", "Sport", "null" }));
        bookType.setBounds(152, 201, 131, 22);
        this.contentPane.add(bookType);
        final JButton addBook = new JButton("Add");
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (AddBookDetails.this.bookId.getText().isEmpty() || AddBookDetails.this.bookName.getText().isEmpty() || AddBookDetails.this.bookAuthor.getText().isEmpty() || AddBookDetails.this.bookPrice.getText().isEmpty() || AddBookDetails.this.bookAvailability.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please check all fields");
                }
                else {
                    final int id = Integer.parseInt(AddBookDetails.this.bookId.getText());
                    final String name = AddBookDetails.this.bookName.getText();
                    final String author = AddBookDetails.this.bookAuthor.getText();
                    final int price = Integer.parseInt(AddBookDetails.this.bookPrice.getText());
                    final int availability = Integer.parseInt(AddBookDetails.this.bookAvailability.getText());
                    final String type = bookType.getSelectedItem().toString();
                    final String url = "jdbc:mysql://localhost:3306/library";
                    final String username = "root";
                    final String password = "root";
                    try {
                        Throwable t = null;
                        try {
                            final Connection connection = DriverManager.getConnection(url, username, password);
                            try {
                                final String query = "Insert into bookdetails values('" + id + "','" + name + "','" + author + "','" + price + "','" + availability + "','" + type + "')";
                                final Statement st = connection.createStatement();
                                st.executeUpdate(query);
                                JOptionPane.showMessageDialog(null, "Book is added");
                                connection.close();
                                AddBookDetails.this.bookId.setText("");
                                AddBookDetails.this.bookName.setText("");
                                AddBookDetails.this.bookAuthor.setText("");
                                AddBookDetails.this.bookPrice.setText("");
                                AddBookDetails.this.bookAvailability.setText("");
                            }
                            finally {
                                if (connection != null) {
                                    connection.close();
                                }
                            }
                        }
                        finally {
                            if (t == null) {
                                final Throwable exception = null;
                                t = exception;
                            }
                            else {
                                final Throwable exception = null;
                                if (t != exception) {
                                    t.addSuppressed(exception);
                                }
                            }
                        }
                    }
                    catch (Exception err) {
                        JOptionPane.showMessageDialog(null, "error on adding book" + err);
                    }
                }
            }
        });
        addBook.setBounds(152, 234, 132, 23);
        this.contentPane.add(addBook);
        final JLabel lblAddBookDetails = new JLabel("Add Book Details");
        lblAddBookDetails.setBounds(166, 11, 131, 14);
        this.contentPane.add(lblAddBookDetails);
        final JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final AdminPanel main = new AdminPanel();
                main.setVisible(true);
                AddBookDetails.this.setVisible(false);
            }
        });
        btnBack.setBounds(309, 234, 89, 23);
        this.contentPane.add(btnBack);
    }
}
