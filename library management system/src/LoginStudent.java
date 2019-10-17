import javax.swing.JLabel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class LoginStudent extends JFrame
{
    private JPanel contentPane;
    private JTextField AUserName;
    private JPasswordField passwordField;
    private JTextField APassword;
    
    public LoginStudent() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Library Management System");
        (this.AUserName = new JTextField()).setHorizontalAlignment(0);
        this.AUserName.setBounds(156, 68, 138, 20);
        this.contentPane.add(this.AUserName);
        this.AUserName.setColumns(10);
        (this.APassword = new JPasswordField()).setHorizontalAlignment(0);
        this.APassword.setBounds(156, 115, 138, 20);
        this.contentPane.add(this.APassword);
        this.APassword.setColumns(10);
        final JButton Login = new JButton("Login");
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String un = LoginStudent.this.AUserName.getText();
                final String pa = LoginStudent.this.APassword.getText();
                boolean flag = false;
                final String url = "jdbc:mysql://localhost:3306/library";
                final String username = "root";
                final String password = "root";
                try {
                    
                    
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        
                            final String query = "SELECT * FROM login ";
                            final Statement st = connection.createStatement();
                            final ResultSet rs = st.executeQuery(query);
                            while (rs.next()) {
                                final String user = rs.getString("userName");
                                final String pas = rs.getString("password");
                                final int id = Integer.parseInt(rs.getString("Id"));
                                final int length = rs.getRow();
                                if (un.contentEquals(user) && pa.contentEquals(pas)) {
                                    flag = true;
                                    final StudentPanel frame = new StudentPanel(id);
                                    frame.setVisible(true);
                                    LoginStudent.this.setVisible(false);
                                }
                            }
                            if (!flag) {
                                JOptionPane.showMessageDialog(null, "UserName/Password did not match");
                            }
                            connection.close();
                     
                }
                catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "error" + err);
                }
            }
        });
        Login.setBounds(156, 166, 138, 23);
        this.contentPane.add(Login);
        final JLabel lblUserName = new JLabel("User Name");
        lblUserName.setBounds(45, 71, 87, 14);
        this.contentPane.add(lblUserName);
        final JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(45, 118, 87, 14);
        this.contentPane.add(lblPassword);
        final JButton Register = new JButton("Register");
        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StudentRegistration frame = new StudentRegistration();
                frame.setVisible(true);
                LoginStudent.this.setVisible(false);
            }
        });
        Register.setBounds(156, 211, 138, 23);
        this.contentPane.add(Register);
    }
}
