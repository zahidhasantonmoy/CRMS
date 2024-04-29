import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newcasefile extends JFrame {

    public newcasefile() {
        setTitle("New Case File");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel containerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundImage = new ImageIcon("bgg.jpg");
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        containerPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        JLabel titleLabel = new JLabel("New Case File");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.decode("#f44336"));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        containerPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.white);
        formPanel.setLayout(new GridLayout(5, 2, 0, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField caseTitleField = new JTextField();
        caseTitleField.setBorder(BorderFactory.createTitledBorder("Case Title"));
        formPanel.add(caseTitleField);

        JTextArea caseDescriptionArea = new JTextArea();
        caseDescriptionArea.setBorder(BorderFactory.createTitledBorder("Case Description"));
        JScrollPane scrollPane = new JScrollPane(caseDescriptionArea);
        formPanel.add(scrollPane);

        JCheckBox urgentCheckBox = new JCheckBox("Urgent");
        formPanel.add(urgentCheckBox);

        String[] statusOptions = {"Open", "In Progress", "Closed"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setBorder(BorderFactory.createTitledBorder("Status"));
        formPanel.add(statusComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(Color.decode("#4CAF50"));
        submitButton.setForeground(Color.white);
        submitButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(submitButton);
        formPanel.add(buttonPanel);

        containerPanel.add(formPanel, BorderLayout.CENTER);
        add(containerPanel, BorderLayout.CENTER);

        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                String caseTitle = caseTitleField.getText();
                String caseDescription = caseDescriptionArea.getText();
                boolean isUrgent = urgentCheckBox.isSelected();
                String status = (String) statusComboBox.getSelectedItem();

                System.out.println("Case Title: " + caseTitle);
                System.out.println("Case Description: " + caseDescription);
                System.out.println("Urgent: " + isUrgent);
                System.out.println("Status: " + status);

               
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewCaseFile::new);
    }
}
