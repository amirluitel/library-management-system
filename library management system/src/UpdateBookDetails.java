import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class UpdateBookDetails extends JFrame
{
    private JPanel contentPane;
    private JTextField getId;
    private JTextField tid;
    private JTextField tname;
    private JTextField tauthor;
    private JTextField tprice;
    private JButton update;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JTextField tavai;
    private JLabel lblNewLabel_2;
    
    public UpdateBookDetails() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Library Management System");
        final JLabel lblEnterBookId = new JLabel("Enter Book ID to Update details");
        lblEnterBookId.setBounds(10, 29, 202, 14);
        this.contentPane.add(lblEnterBookId);
        (this.getId = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    e.consume();
                }
            }
        });
        this.getId.setBounds(193, 26, 96, 20);
        this.contentPane.add(this.getId);
        this.getId.setColumns(10);
        (this.tid = new JTextField()).setBounds(193, 54, 96, 20);
        this.contentPane.add(this.tid);
        this.tid.setColumns(10);
        (this.tname = new JTextField()).setBounds(193, 85, 96, 20);
        this.contentPane.add(this.tname);
        this.tname.setColumns(10);
        (this.tauthor = new JTextField()).setBounds(193, 116, 96, 20);
        this.contentPane.add(this.tauthor);
        this.tauthor.setColumns(10);
        (this.tprice = new JTextField()).setBounds(193, 147, 96, 20);
        this.contentPane.add(this.tprice);
        this.tprice.setColumns(10);
        (this.lblId = new JLabel("Id")).setHorizontalAlignment(4);
        this.lblId.setBounds(118, 54, 45, 14);
        this.contentPane.add(this.lblId);
        (this.lblName = new JLabel("Name")).setHorizontalAlignment(4);
        this.lblName.setBounds(118, 88, 48, 14);
        this.contentPane.add(this.lblName);
        (this.lblNewLabel = new JLabel("Author")).setHorizontalAlignment(4);
        this.lblNewLabel.setBounds(115, 119, 48, 14);
        this.contentPane.add(this.lblNewLabel);
        (this.lblNewLabel_1 = new JLabel("Price")).setHorizontalAlignment(4);
        this.lblNewLabel_1.setBounds(115, 150, 48, 14);
        this.contentPane.add(this.lblNewLabel_1);
        (this.tavai = new JTextField()).setBounds(193, 178, 96, 20);
        this.contentPane.add(this.tavai);
        this.tavai.setColumns(10);
        (this.lblNewLabel_2 = new JLabel("Availability")).setHorizontalAlignment(4);
        this.lblNewLabel_2.setBounds(56, 181, 107, 14);
        this.contentPane.add(this.lblNewLabel_2);
        final JComboBox ttype = new JComboBox();
        ttype.setModel(new DefaultComboBoxModel<String>(new String[] { "Art", "Literature", "Sport", "Music", "Other", "null" }));
        ttype.setBounds(193, 209, 96, 22);
        this.contentPane.add(ttype);
        final JLabel lblNewLabel_3 = new JLabel("Type");
        lblNewLabel_3.setHorizontalAlignment(4);
        lblNewLabel_3.setBounds(115, 213, 48, 14);
        this.contentPane.add(lblNewLabel_3);
        final JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final AdminPanel frame = new AdminPanel();
                frame.setVisible(true);
                UpdateBookDetails.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(315, 238, 89, 23);
        this.contentPane.add(btnNewButton);
        final JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String url = "jdbc:mysql://localhost:3306/library";
                final String username = "root";
                final String password = "root";
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            final String query = "SELECT * FROM bookdetails where Id='" + UpdateBookDetails.this.getId.getText() + "'";
                            final Statement st = connection.createStatement();
                            final ResultSet rs = st.executeQuery(query);
                            while (rs.next()) {
                                UpdateBookDetails.this.tid.setText(rs.getString("Id"));
                                UpdateBookDetails.this.tname.setText(rs.getString("name"));
                                UpdateBookDetails.this.tauthor.setText(rs.getString("author"));
                                UpdateBookDetails.this.tprice.setText(rs.getString("price"));
                                UpdateBookDetails.this.tavai.setText(rs.getString("availability"));
                                ttype.setSelectedItem(rs.getString("type"));
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
        btnOk.setBounds(315, 25, 89, 23);
        this.contentPane.add(btnOk);
        (this.update = new JButton("Update")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String url = "jdbc:mysql://localhost:3306/library";
                final String username = "root";
                final String password = "root";
                final int mId = Integer.parseInt(UpdateBookDetails.this.getId.getText());
                final int id = Integer.parseInt(UpdateBookDetails.this.tid.getText());
                final String name = UpdateBookDetails.this.tname.getText();
                final String author = UpdateBookDetails.this.tauthor.getText();
                final int price = Integer.parseInt(UpdateBookDetails.this.tprice.getText());
                final int avai = Integer.parseInt(UpdateBookDetails.this.tavai.getText());
                final String type = ttype.getSelectedItem().toString();
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            final String query = "update bookdetails set Id='" + id + "', name='" + name + "',author='" + author + "',price='" + price + "',availability='" + avai + "',type='" + type + "' Where Id= '" + mId + "'";
                            final Statement st = connection.createStatement();
                            st.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Book details updated Successfully");
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
        this.update.setBounds(193, 238, 96, 23);
        this.contentPane.add(this.update);
    }
}
