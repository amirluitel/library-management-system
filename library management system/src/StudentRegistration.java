import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class StudentRegistration extends JFrame
{
    private JPanel contentPane;
    private JTextField StudentId;
    private JTextField StudentUserName;
    private JTextField StudentPassword;
    private JTextField StudentEmail;
    private JButton btnLogin;
    public static final int MYSQL_DUPLICATE_PK = 1062;
    private static final String email_Pttern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public StudentRegistration() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Library Management System");
        final JLabel lblId = new JLabel("ID");
        lblId.setBounds(39, 11, 48, 14);
        this.contentPane.add(lblId);
        final JLabel lblNewLabel = new JLabel("User Name");
        lblNewLabel.setBounds(39, 55, 81, 14);
        this.contentPane.add(lblNewLabel);
        final JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setBounds(39, 97, 81, 14);
        this.contentPane.add(lblNewLabel_1);
        final JLabel lblNewLabel_2 = new JLabel("Email");
        lblNewLabel_2.setBounds(39, 139, 48, 14);
        this.contentPane.add(lblNewLabel_2);
        (this.StudentId = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent b) {
                final char c = b.getKeyChar();
                if (!Character.isDigit(c) || c == '\b' || c == '\u007f') {
                    b.consume();
                }
            }
        });
        this.StudentId.setBounds(138, 8, 96, 20);
        this.contentPane.add(this.StudentId);
        this.StudentId.setColumns(10);
        (this.StudentUserName = new JTextField()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        this.StudentUserName.setBounds(138, 52, 96, 20);
        this.contentPane.add(this.StudentUserName);
        this.StudentUserName.setColumns(10);
        (this.StudentPassword = new JPasswordField()).setBounds(138, 94, 96, 20);
        this.contentPane.add(this.StudentPassword);
        this.StudentPassword.setColumns(10);
        (this.StudentEmail = new JTextField()).setBounds(138, 136, 96, 20);
        this.contentPane.add(this.StudentEmail);
        this.StudentEmail.setColumns(10);
        final JButton StudentRegister = new JButton("Register");
        StudentRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (StudentRegistration.this.StudentId.getText().isEmpty() || StudentRegistration.this.StudentUserName.getText().isEmpty() || StudentRegistration.this.StudentPassword.getText().isEmpty() || StudentRegistration.this.StudentEmail.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "please fill all fileds");
                }
                else {
                    final int id = Integer.parseInt(StudentRegistration.this.StudentId.getText());
                    final String UserName = StudentRegistration.this.StudentUserName.getText();
                    final String Password = StudentRegistration.this.StudentPassword.getText();
                    final String Email = StudentRegistration.this.StudentEmail.getText();
                    if (!Email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                        JOptionPane.showMessageDialog(null, "Invalid email");
                    }
                    else {
                        final String url = "jdbc:mysql://localhost:3306/library";
                        final String username = "root";
                        final String password = "root";
                        try {
                            Throwable t = null;
                            try {
                                final Connection connection = DriverManager.getConnection(url, username, password);
                                try {
                                    final String query = "Insert into login values('" + UserName + "','" + Password + "','" + id + "','" + Email + "')";
                                    final Statement st = connection.createStatement();
                                    st.executeUpdate(query);
                                    JOptionPane.showMessageDialog(null, "Registrartion Successful");
                                    StudentRegistration.this.StudentId.setText(null);
                                    StudentRegistration.this.StudentUserName.setText(null);
                                    StudentRegistration.this.StudentPassword.setText(null);
                                    StudentRegistration.this.StudentEmail.setText(null);
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
                        catch (SQLException err) {
                            if (err.getErrorCode() == 1062) {
                                JOptionPane.showMessageDialog(null, "ID you have entered is not valid");
                            }
                            StudentRegistration.this.StudentId.setText(null);
                        }
                    }
                }
            }
        });
        StudentRegister.setBounds(138, 187, 96, 23);
        this.contentPane.add(StudentRegister);
        (this.btnLogin = new JButton("Login")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final LoginStudent obj = new LoginStudent();
                obj.setVisible(true);
                StudentRegistration.this.setVisible(false);
            }
        });
        this.btnLogin.setBounds(260, 187, 89, 23);
        this.contentPane.add(this.btnLogin);
    }
}
