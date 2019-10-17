import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class BorrowBooks extends JFrame
{
    private JPanel contentPane;
    private JTextField id;
    private JTable table;
    int bookCount=0;
    public BorrowBooks(int MyId) {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Library Management System");
        final JLabel lblNewLabel = new JLabel("Book Id");
        lblNewLabel.setBounds(10, 30, 69, 14);
        this.contentPane.add(lblNewLabel);
        (this.id = new JTextField()).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
            }
        });
        this.id.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    e.consume();
                }
            }
        });
        this.id.setBounds(53, 27, 167, 20);
        this.contentPane.add(this.id);
        this.id.setColumns(10);
        final JTextArea textArea = new JTextArea();
        this.getContentPane().add(textArea);
        textArea.setEditable(true);
        textArea.append("ID\tName\tAuthor\tItems Available\n");
        final JScrollPane scrollBar = new JScrollPane(textArea);
        scrollBar.setBounds(10, 60, 400, 136);
        this.getContentPane().add(scrollBar);
        final JButton btnNewButton = new JButton("Search");
        btnNewButton.addActionListener(new ActionListener() {
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
                            final String query = "SELECT * FROM bookdetails where Id='" + BorrowBooks.this.id.getText() + "'";
                            final Statement st = connection.createStatement();
                            final ResultSet rs = st.executeQuery(query);
                            while (rs.next()) {
                                flag = false;
                                final int id1 = rs.getInt("Id");
                                final String name1 = rs.getString("name");
                                final String author1 = rs.getString("author");
                                final int avai = rs.getInt("availability");
                                final int k;
                                if ((k = avai) >= 1) {
                                    final JButton btnNewButton_1 = new JButton("Borrow");
                                    scrollBar.setRowHeaderView(btnNewButton_1);
                                    btnNewButton_1.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(final ActionEvent e) {
                                            JOptionPane.showMessageDialog(null, "Book borrowed successful");
                                            textArea.setText("");
                                            btnNewButton_1.setVisible(false);
                                            textArea.append("ID\tName\tAuthor\tItems Available\n");
                                            try {
                                                Throwable t = null;
                                                try {
                                                    final Connection connection = DriverManager.getConnection(url, username, password);
                                                    try {
                                                        final String queryUpdate = "Update bookdetails set availability='" + (k - 1) + "'";
                                                        final Statement st = connection.createStatement();
                                                        st.executeUpdate(queryUpdate);
                                                        bookCount++;
                                                        //update book information
                                                        final String queryUpdate1 = "Update login set numberOfBooks='" + bookCount + "' where Id='"+MyId+"'";
                                                        final Statement st1 = connection.createStatement();
                                                        st.executeUpdate(queryUpdate1);
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
                                    textArea.append(String.valueOf(String.valueOf(id1)) + "\t" + name1 + "\t" + author1 + "\t" + String.valueOf(avai) + "\n");
                                }
                                else {
                                    textArea.append("The Book " + id1 + " is not available\n");
                                }
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
        btnNewButton.setBounds(230, 27, 96, 20);
        this.contentPane.add(btnNewButton);
        final JButton btnClearAll = new JButton("Clear All");
        btnClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                textArea.setText("");
                textArea.append("ID\tName\tAuthor\tItems Available\n");
            }
        });
        btnClearAll.setBounds(324, 207, 86, 23);
        this.contentPane.add(btnClearAll);
        final JButton btnMyPanel = new JButton("My Panel");
        btnMyPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StudentPanel frame = new StudentPanel(MyId);
                frame.setVisible(true);
                BorrowBooks.this.setVisible(false);
            }
        });
        btnMyPanel.setBounds(10, 213, 89, 23);
        this.contentPane.add(btnMyPanel);
        
        
    }
}
