import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

public class Signup extends JFrame {
    JPanel containerPanel = new JPanel(new BorderLayout()) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
       
            ImageIcon backgroundImage = new ImageIcon("bgg.jpg");
      
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    };

    JTextField userIdField = new JTextField();
    JTextField nameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField addressField = new JTextField();
    JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Others"});
    JButton submitButton = new JButton("Sign Up");

    public Signup() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLayout(new BorderLayout());

        containerPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.decode("#f44336"));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        containerPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(9, 1, 0, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        generateRandomUserId();
        userIdField.setEditable(false);
        userIdField.setBorder(BorderFactory.createTitledBorder("User ID"));
        formPanel.add(userIdField);

        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        formPanel.add(nameField);

        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        formPanel.add(emailField);

        phoneField.setBorder(BorderFactory.createTitledBorder("Phone"));
        formPanel.add(phoneField);

        addressField.setBorder(BorderFactory.createTitledBorder("Address"));
        formPanel.add(addressField);

        genderComboBox.setBorder(BorderFactory.createTitledBorder("Gender"));
        formPanel.add(genderComboBox);

        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        formPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        submitButton.setBackground(Color.decode("#4CAF50"));
        submitButton.setForeground(Color.white);
        submitButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(submitButton);
        formPanel.add(buttonPanel);

        containerPanel.add(formPanel, BorderLayout.CENTER);
        add(containerPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        JLabel backLabel = new JLabel("Back to Homepage");
        backLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        backLabel.setForeground(Color.decode("#f44336"));
        footerPanel.add(backLabel);
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.decode("#f44336"));
        backButton.setForeground(Color.white);
        backButton.setPreferredSize(new Dimension(100, 30));
        footerPanel.add(backButton);
        containerPanel.add(footerPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(emailField.getText(), "")) {
                    JOptionPane.showMessageDialog(null, "Enter email!", "Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    dispose();
                    new Login();

                    String url = "jdbc:mysql://localhost:3306/CRMS";
                    String username = "root";
                    String Password = "";
                    try {
                        
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String pass = passwordField.getText();
                        String name = nameField.getText();
                        String email = emailField.getText();
                        String phone = phoneField.getText();
                        String address = addressField.getText();
                        String gender = Objects.requireNonNull(genderComboBox.getSelectedItem()).toString();

                        
                        Connection connection = DriverManager.getConnection(url, username, Password);

                        String insertQuery = "INSERT INTO userdata (username, password, email, phone, address, gender) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                     
                        preparedStatement.setString(1, generateRandomUserId());
                        preparedStatement.setString(2, pass);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, phone);
                        preparedStatement.setString(5, address);
                        preparedStatement.setString(6, gender);

                       
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "User added:\n" + "Name: " + name + "\nEmail: " + email, "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to add user", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // 6. Close the resources
                        preparedStatement.close();
                        connection.close();
                    } catch (ClassNotFoundException | SQLException x) {
                        x.printStackTrace();
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Homepage();
            }
        });

        setVisible(true);
    }

    private String generateRandomUserId() {
        // Simple method to generate a random userid
        Random random = new Random();
        int randomUserId = 100000 + random.nextInt(900000);
        userIdField.setText(String.valueOf(randomUserId));
        return String.valueOf(randomUserId);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}
