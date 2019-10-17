import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPopupMenu;
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


public class AdminPanel extends JFrame
{
    private JPanel contentPane;
    
    public AdminPanel() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Library Management System");
        final JButton AddBooks = new JButton("Add Books");
        AddBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final AddBookDetails add = new AddBookDetails();
                add.setVisible(true);
                AdminPanel.this.setVisible(false);
            }
        });
        AddBooks.setBounds(10, 35, 125, 23);
        this.contentPane.add(AddBooks);
        final JButton SearchBooks = new JButton("Search Books");
        SearchBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final SearchBooks frame = new SearchBooks();
                frame.setVisible(true);
                AdminPanel.this.setVisible(false);
            }
        });
        SearchBooks.setBounds(10, 105, 125, 23);
        this.contentPane.add(SearchBooks);
        final JButton UpdateBooks = new JButton("Update Books");
        UpdateBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final UpdateBookDetails frame = new UpdateBookDetails();
                frame.setVisible(true);
                AdminPanel.this.setVisible(false);
            }
        });
        UpdateBooks.setBounds(10, 169, 125, 23);
        this.contentPane.add(UpdateBooks);
        final JButton btnNewButton = new JButton("Log Out");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final LoginAdmin frame = new LoginAdmin();
                frame.setVisible(true);
                AdminPanel.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(10, 227, 125, 23);
        this.contentPane.add(btnNewButton);
    }
    
    private static void addPopup(final Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                if (e.isPopupTrigger()) {
                    this.showMenu(e);
                }
            }
            
            @Override
            public void mouseReleased(final MouseEvent e) {
                if (e.isPopupTrigger()) {
                    this.showMenu(e);
                }
            }
            
            private void showMenu(final MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
