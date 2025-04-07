package TX;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInformation {



    public static void main(String[] args) {
        new UserInformation(" ", " ", " ", " ", "Dhaka", " ");
    }

    public UserInformation(String name, String mobile, String nid, String email, String  division, String address) {
        JFrame frame = new JFrame("Tax Estimator - Personal Information");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(230, 230, 225));
        frame.setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 700, 70);
        headerPanel.setBackground(new Color(30, 60, 150));
        frame.add(headerPanel);

        JLabel titleLabel = new JLabel("TAX ESTIMATOR");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45));
        headerPanel.add(titleLabel);

        JLabel tLabel = new JLabel("   Tax Return Form");
        tLabel.setBounds(550, 80, 120, 28);
        tLabel.setOpaque(true);
        tLabel.setForeground(Color.BLACK);
        tLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(tLabel);

        JLabel nameLabel = new JLabel(" 1. Name:");
        nameLabel.setBounds(30, 140, 120, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField(name);
        nameField.setBounds(160, 140, 200, 25);
        frame.add(nameField);

        JLabel nameError = new JLabel();
        nameError.setBounds(380, 140, 300, 25);
        nameError.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(nameError);

        JLabel mobileLabel = new JLabel(" 2. Mobile Number:");
        mobileLabel.setBounds(30, 180, 120, 25);
        frame.add(mobileLabel);

        JTextField mobileField = new JTextField(mobile);
        mobileField.setBounds(160, 180, 200, 25);
        frame.add(mobileField);

        JLabel mobileError = new JLabel();
        mobileError.setBounds(380, 180, 300, 25);
        mobileError.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(mobileError);

        JLabel nidLabel = new JLabel(" 3. NID Number:");
        nidLabel.setBounds(30, 220, 120, 25);
        frame.add(nidLabel);

        JTextField nidField = new JTextField(nid);
        nidField.setBounds(160, 220, 200, 25);
        frame.add(nidField);

        JLabel nidError = new JLabel();
        nidError.setBounds(380, 220, 300, 25);
        nidError.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(nidError);

        JLabel emailLabel = new JLabel(" 4. Email Address:");
        emailLabel.setBounds(30, 260, 120, 25);
        frame.add(emailLabel);

        JTextField emailField = new JTextField(email);
        emailField.setBounds(160, 260, 200, 25);
        frame.add(emailField);

        JLabel emailError = new JLabel();
        emailError.setBounds(380, 260, 300, 25);
        emailError.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(emailError);

        JLabel divisionLabel = new JLabel(" 5. Division:");
        divisionLabel.setBounds(30, 300, 120, 25);
        frame.add(divisionLabel);

        String[] divisions = {"Dhaka", "Chattogram", "Khulna", "Rajshahi", "Barishal", "Sylhet", "Rangpur", "Mymensingh"};
        JComboBox<String> divisionComboBox = new JComboBox<>(divisions);
        divisionComboBox.setSelectedItem(division);
        divisionComboBox.setBounds(160, 300, 200, 25);
        frame.add(divisionComboBox);

        JLabel addressLabel = new JLabel(" 6. Address:");
        addressLabel.setBounds(30, 340, 120, 25);
        frame.add(addressLabel);

        JTextArea addressArea = new JTextArea(address);
        addressArea.setBounds(160, 340, 200, 50);
        frame.add(addressArea);

        JButton nextButton = new JButton(" Next");
        nextButton.setBounds(300, 485, 100, 30);
        frame.add(nextButton);

        validateField(nameField, nameError, "[A-Z ]+", "Invalid name! Only uppercase letters allowed.", "Empty");
        validateField(mobileField, mobileError, "[0-9]+", "Invalid input! Only numbers are allowed.", "Empty");
        validateField(nidField, nidError, "[0-9]+", "Invalid input! Only numbers are allowed.", "Empty");
        validateEmailField(emailField, emailError);
        
        
        nextButton.addActionListener(e -> {
            boolean allFieldsFilled = !nameField.getText().trim().isEmpty()
                    && !mobileField.getText().trim().isEmpty()
                    && !nidField.getText().trim().isEmpty()
                    && !emailField.getText().trim().isEmpty()
                    && !addressArea.getText().trim().isEmpty();

            boolean noErrors = nameError.getText().trim().isEmpty()
                    && mobileError.getText().trim().isEmpty()
                    && nidError.getText().trim().isEmpty()
                    && emailError.getText().trim().isEmpty();

            if (allFieldsFilled && noErrors) {
                frame.dispose();
                new Process(
                        nameField.getText().trim(),
                        mobileField.getText().trim(),
                        nidField.getText().trim(),
                        emailField.getText().trim(),
                        divisionComboBox.getSelectedItem().toString(),
                        addressArea.getText().trim()
                );
            } else {
                JOptionPane.showMessageDialog(frame, "Please , fill in all fields correctly before proceeding.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        frame.setVisible(true);
    }

    private void validateField(JTextField field, JLabel errorLabel, String regex, String errorMessage, String emptyMessage) {
        field.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String text = field.getText().trim();
                if (text.isEmpty()) {
                    errorLabel.setText(emptyMessage);
                    errorLabel.setForeground(new Color(105, 105, 105));
                } else if (!text.matches(regex)) {
                    errorLabel.setText(errorMessage);
                    errorLabel.setForeground(new Color (139,0,0));
                } else {
                    errorLabel.setText("");
                }
            }
        });
    }

    private void validateEmailField(JTextField field, JLabel errorLabel) {
        field.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                String email = field.getText().trim();
                if (email.isEmpty()) {
                    errorLabel.setText("Empty");
                    errorLabel.setForeground(new Color(105, 105, 105));
                } else if (!email.contains("@") || !email.contains(".")) {
                    errorLabel.setText("Invalid Email!");
                    errorLabel.setForeground(new Color (139,0,0));
                } else {
                    errorLabel.setText("");
                }
            }
        });
    }
}