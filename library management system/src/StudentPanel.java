import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class StudentPanel extends JFrame
{
    private JPanel contentPane;
    
    public StudentPanel(final int id) {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setTitle("Library Management System");
        this.setLocationRelativeTo(null);
        final JButton btnNewButton = new JButton("My Info");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StudentInfo frame = new StudentInfo(id);
                frame.setVisible(true);
                StudentPanel.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(10, 34, 109, 23);
        this.contentPane.add(btnNewButton);
        final JButton btnNewButton_1 = new JButton("Borrow Book");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final BorrowBooks frame = new BorrowBooks(id);
                frame.setVisible(true);
                StudentPanel.this.setVisible(false);
            }
        });
        btnNewButton_1.setBounds(10, 115, 109, 23);
        this.contentPane.add(btnNewButton_1);
        final JButton btnNewButton_2 = new JButton("Log Out");
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final LoginStudent frame = new LoginStudent();
                frame.setVisible(true);
                StudentPanel.this.setVisible(false);
            }
        });
        btnNewButton_2.setBounds(10, 203, 109, 23);
        this.contentPane.add(btnNewButton_2);
    }
}
