import javax.swing.JMenuBar;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import java.awt.LayoutManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class SearchBooks extends JFrame
{
    private JTextField srchId;
    private JTextField srchName;
    private JTextField srchAuthor;
    private JTextField srchType;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    
    public SearchBooks() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 500, 300);
        this.getContentPane().setLayout(null);
        final JMenuItem mntmGoBack = new JMenuItem("Go back");
        mntmGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final AdminPanel frame = new AdminPanel();
                frame.setVisible(true);
                SearchBooks.this.setVisible(false);
            }
        });
        mntmGoBack.setBounds(230, 213, 90, 26);
        this.getContentPane().add(mntmGoBack);
        (this.srchId = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    e.consume();
                }
            }
        });
        this.srchId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                SearchBooks.this.srchId.setText("");
            }
        });
        this.srchId.setHorizontalAlignment(0);
        this.srchId.setText("ID");
        this.srchId.setBounds(10, 11, 96, 20);
        this.getContentPane().add(this.srchId);
        this.srchId.setColumns(10);
        (this.srchName = new JTextField()).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                SearchBooks.this.srchName.setText("");
            }
        });
        this.srchName.setHorizontalAlignment(0);
        this.srchName.setText("Name");
        this.srchName.setBounds(166, 11, 125, 20);
        this.getContentPane().add(this.srchName);
        this.srchName.setColumns(10);
        (this.srchAuthor = new JTextField()).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                SearchBooks.this.srchAuthor.setText("");
            }
        });
        this.srchAuthor.setHorizontalAlignment(0);
        this.srchAuthor.setText("Author");
        this.srchAuthor.setBounds(364, 11, 96, 20);
        this.getContentPane().add(this.srchAuthor);
        this.srchAuthor.setColumns(10);
        (this.srchType = new JTextField()).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                SearchBooks.this.srchType.setText("");
            }
        });
        this.srchType.setHorizontalAlignment(0);
        this.srchType.setText("Type");
        this.srchType.setBounds(10, 47, 96, 20);
        this.getContentPane().add(this.srchType);
        this.srchType.setColumns(10);
        final JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(131, 46, 48, 22);
        this.getContentPane().add(lblPrice);
        final JComboBox srchFrom = new JComboBox();
        srchFrom.setModel(new DefaultComboBoxModel<String>(new String[] { "From", "100", "200", "300", "400", "500", "600" }));
        srchFrom.setToolTipText("0");
        srchFrom.setBounds(166, 46, 62, 22);
        this.getContentPane().add(srchFrom);
        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "To", "1000", "1200", "1500", "2000", "3000" }));
        comboBox.setBounds(238, 46, 53, 22);
        this.getContentPane().add(comboBox);
        final JTextArea textArea = new JTextArea();
        this.getContentPane().add(textArea);
        textArea.setEditable(true);
        textArea.append("ID\tName\tAuthor\tPrice\tAvailability\tType\n");
        final JScrollPane scrollBar = new JScrollPane(textArea);
        scrollBar.setBounds(10, 78, 450, 136);
        this.getContentPane().add(scrollBar);
        final JButton searchBooks = new JButton("Search");
        searchBooks.addMouseListener(new MouseAdapter() {});
        searchBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                boolean flag = true;
                final String url = "jdbc:mysql://localhost:3306/library";
                final String username = "root";
                final String password = "root";
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            final String query = "SELECT * FROM bookdetails where Id='" + SearchBooks.this.srchId.getText() + "' OR name='" + SearchBooks.this.srchName.getText() + "' OR author='" + SearchBooks.this.srchAuthor.getText() + "' OR type='" + SearchBooks.this.srchType.getText() + "' OR price between'" + srchFrom.getSelectedItem().toString() + "' and '" + comboBox.getSelectedItem().toString() + "'";
                            final Statement st = connection.createStatement();
                            final ResultSet rs = st.executeQuery(query);
                            while (rs.next()) {
                                flag = false;
                                final int id = rs.getInt("Id");
                                final String name = rs.getString("name");
                                final String author = rs.getString("author");
                                final Float price = rs.getFloat("price");
                                final int avai = rs.getInt("availability");
                                final String type = rs.getString("type");
                                textArea.append(String.valueOf(String.valueOf(id)) + "\t" + name + "\t" + author + "\t" + String.valueOf(price) + "\t" + String.valueOf(avai) + "\t" + type + "\n");
                            }
                            if (flag) {
                                textArea.append("No results found\n");
                            }
                            connection.close();
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
                    JOptionPane.showMessageDialog(null, "error\t" + err);
                }
            }
        });
        searchBooks.setBounds(364, 46, 96, 23);
        this.getContentPane().add(searchBooks);
        final JButton Clear = new JButton("Clear All");
        Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                textArea.setText("");
                textArea.append("ID\tName\tAuthor\tPrice\tAvailability\tType\n");
            }
        });
        Clear.setBounds(370, 213, 90, 26);
        this.getContentPane().add(Clear);
        this.setTitle("Library Management System");
        this.setLocationRelativeTo(null);
        final JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
    }
}
