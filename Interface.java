import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main {
    private JFrame frame;
    private JPanel contentPanel;

    // Define colors
    private final Color TEAL = new Color(0, 128, 128);
    private final Color LIGHT_TEAL = new Color(0, 150, 136);
    private final Color WHITE = Color.WHITE;
    private final Color LIGHT_GRAY = Color.LIGHT_GRAY;

    public Main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        // Create header
        JPanel headerPanel = createHeaderPanel();
        frame.getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Create sidebar
        JPanel sidebarPanel = createSidebarPanel();
        frame.getContentPane().add(sidebarPanel, BorderLayout.WEST);

        // Create content panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(LIGHT_GRAY);
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
        showStrandsTable();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(TEAL);
        headerPanel.setPreferredSize(new Dimension(1000, 50));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Logo
        JLabel logoLabel = createStyledLabel("K-12 PS", Font.BOLD, 18);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Center panel for buttons and title
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);

        JButton refreshButton = createStyledButton("⟳");
        JButton settingsButton = createStyledButton("⚙");
        JLabel titleLabel = createStyledLabel("Faculty", Font.PLAIN, 16);

        centerPanel.add(refreshButton);
        centerPanel.add(settingsButton);
        centerPanel.add(titleLabel);

        headerPanel.add(centerPanel, BorderLayout.CENTER);

        // Profile icon
        JLabel profileLabel = createStyledLabel("○", Font.PLAIN, 24);
        headerPanel.add(profileLabel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(LIGHT_TEAL);
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(200, 550));
        sidebarPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel menuLabel = createStyledLabel("≡ Menu", Font.BOLD, 18);
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebarPanel.add(menuLabel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] menuItems = {"Account Settings", "Student Information", "Questionnaires", "Results", "Strands"};
        for (String item : menuItems) {
            JButton button = createStyledButton(item);
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        return sidebarPanel;
    }

    private void showStrandsTable() {
        contentPanel.removeAll();

        String[] columnNames = {"No.", "Strand", "Description", "Total Score", "Time Limit"};
        Object[][] data = {
                {"", "", "", "", ""},
                {"", "", "", "", ""},
                {"", "", "", "", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setBackground(WHITE);
        table.getTableHeader().setBackground(TEAL);
        table.getTableHeader().setForeground(WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JLabel createStyledLabel(String text, int fontStyle, int fontSize) {
        JLabel label = new JLabel(text);
        label.setForeground(WHITE);
        label.setFont(new Font("Arial", fontStyle, fontSize));
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
