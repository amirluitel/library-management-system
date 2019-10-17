import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class StudentInfo extends JFrame
{
    private JPanel contentPane;
    private JTextField name;
    private JTextField email;
    private JTextField booksBorrowed;
    private JLabel lblNewLabel_3;
    private JTextField id;
    
    public StudentInfo(final int myId) {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setTitle("Library Management System");
        this.setLocationRelativeTo(null);
        final JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(10, 40, 48, 14);
        this.contentPane.add(lblNewLabel);
        final JLabel lblNewLabel_1 = new JLabel("Email");
        lblNewLabel_1.setBounds(10, 81, 48, 14);
        this.contentPane.add(lblNewLabel_1);
        final JLabel lblNewLabel_2 = new JLabel("No of Books Borrowed");
        lblNewLabel_2.setBounds(10, 124, 140, 14);
        this.contentPane.add(lblNewLabel_2);
        (this.name = new JTextField()).setEditable(false);
        this.name.setBounds(164, 37, 203, 20);
        this.contentPane.add(this.name);
        this.name.setColumns(10);
        (this.email = new JTextField()).setEditable(false);
        this.email.setBounds(164, 78, 203, 20);
        this.contentPane.add(this.email);
        this.email.setColumns(10);
        (this.booksBorrowed = new JTextField()).setEditable(false);
        this.booksBorrowed.setBounds(164, 121, 203, 20);
        this.contentPane.add(this.booksBorrowed);
        this.booksBorrowed.setColumns(10);
        final JButton btnNewButton = new JButton("My Panel");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StudentPanel frame = new StudentPanel(myId);
                frame.setVisible(true);
                StudentInfo.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(164, 167, 96, 23);
        this.contentPane.add(btnNewButton);
        (this.lblNewLabel_3 = new JLabel("ID")).setBounds(10, 11, 48, 14);
        this.contentPane.add(this.lblNewLabel_3);
        (this.id = new JTextField()).setEditable(false);
        this.id.setBounds(164, 6, 203, 20);
        this.contentPane.add(this.id);
        this.id.setColumns(10);
        final String url = "jdbc:mysql://localhost:3306/library";
        final String username = "root";
        final String password = "root";
        try {
            Throwable t = null;
            try {
                final Connection connection = DriverManager.getConnection(url, username, password);
                try {
                    final String query = "SELECT * FROM login where Id='" + myId + "'";
                    final Statement st = connection.createStatement();
                    final ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        final int id1 = rs.getInt("Id");
                        final String user = rs.getString("userName");
                        final String email1 = rs.getString("Email");
                        final int noBook=rs.getInt("numberOfBooks");
                        this.name.setText(user);
                        this.email.setText(email1);
                        this.id.setText(String.valueOf(id1));
                        booksBorrowed.setText(String.valueOf(noBook));
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
            System.out.println("error" + err);
        }
    }
}
