import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class MainWindow extends JFrame
{
    private JPanel contentPane;
    
    public MainWindow() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Amir\\Desktop\\ProjectsDone\\lab_img.png"));
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        this.setTitle("Welcome to Library Management System");
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setTitle("Library Management System");
        this.setLocationRelativeTo(null);
        final JButton btnAdmin = new JButton("Admin");
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final LoginAdmin AdminFrame = new LoginAdmin();
                AdminFrame.setVisible(true);
                MainWindow.this.setVisible(false);
            }
        });
        btnAdmin.setBounds(40, 100, 122, 50);
        this.contentPane.add(btnAdmin);
        final JButton btnStudent = new JButton("Student");
        btnStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final LoginStudent frame = new LoginStudent();
                frame.setVisible(true);
                MainWindow.this.setVisible(false);
            }
        });
        btnStudent.setBounds(283, 100, 109, 50);
        this.contentPane.add(btnStudent);
        final JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(0);
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Amir\\Desktop\\ProjectsDone\\lab_img.png"));
        lblNewLabel.setBounds(10, -5, 424, 261);
        this.contentPane.add(lblNewLabel);
    }
}
