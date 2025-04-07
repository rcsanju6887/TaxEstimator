package TX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Process {

    public static void main(String[] args) {
        new Process(" ", " ", " ", " ", " ", " ");
    }

    public Process(String name, String mobile, String nid, String email, String division, String address) {
        JFrame frame = new JFrame("Tax Estimator - Process");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        
        JPanel headerPanel = new JPanel();    //header Panel
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

        
        JLabel userInfoLabel = new JLabel("  User Information :");  //userinfo
        userInfoLabel.setBounds(30, 130, 499, 25);
        userInfoLabel.setFont(new Font("Arial", Font.BOLD, 25));
        frame.add(userInfoLabel);

        
        JPanel infoBox = new JPanel();   //box
        infoBox.setBounds(30, 160, 620, 150);
        infoBox.setBackground(new Color(98, 104, 126)); 
        infoBox.setLayout(new GridLayout(6, 1, 5, 5));

        JLabel[] labels = {
            new JLabel("    Name :"), new JLabel(name),
            new JLabel("    Mobile Number :"), new JLabel(mobile),
            new JLabel("    NID Number :"), new JLabel(nid),
            new JLabel("    Email address :"), new JLabel(email),
            new JLabel("    Division :"), new JLabel(division),
            new JLabel("    Address :"), new JLabel(address)
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Arial", i % 2 == 0 ? Font.BOLD : Font.PLAIN, 15));
            labels[i].setForeground(i % 2 == 0 ? Color. WHITE :  new Color(23,23,23)); 
            infoBox.add(labels[i]);
        }

        frame.add(infoBox);

        
        JLabel incomeLabel = new JLabel("Enter Income (BDT - ৳) :");
        incomeLabel.setBounds(80, 330, 200, 25);
        frame.add(incomeLabel);

        JTextField incomeField = new JTextField();
        incomeField.setBounds(250, 330, 200, 25);
        frame.add(incomeField);

        JLabel incomeError = new JLabel();
        incomeError.setBounds(470, 330, 300, 25);
        incomeError.setFont(new Font("Arial", Font.BOLD, 13));
        frame.add(incomeError);

       
        JLabel taxCategoryLabel = new JLabel("Tax Category :");     //tax category
        taxCategoryLabel.setBounds(80, 380, 150, 25);
        frame.add(taxCategoryLabel);

        String[] taxCategories = {"General", "Senior citizen"};
        JComboBox<String> taxCategoryComboBox = new JComboBox<>(taxCategories);
        taxCategoryComboBox.setBounds(250, 380, 200, 25);
        frame.add(taxCategoryComboBox);

       
        JLabel incomeTypeLabel = new JLabel("Income Type :");    //income type 
        incomeTypeLabel.setBounds(80, 450, 150, 25);
        frame.add(incomeTypeLabel);

        JRadioButton monthlyRadio = new JRadioButton("Monthly");
        monthlyRadio.setBounds(230, 450, 80, 25);

        JRadioButton yearlyRadio = new JRadioButton("Yearly");
        yearlyRadio.setBounds(320, 450, 80, 25);

        ButtonGroup incomeTypeGroup = new ButtonGroup();
        incomeTypeGroup.add(yearlyRadio);
        incomeTypeGroup.add(monthlyRadio);

        frame.add(yearlyRadio);
        frame.add(monthlyRadio);
        yearlyRadio.setSelected(true);

        
        JCheckBox deductionCheckbox = new JCheckBox(" Apply Tax Deduction");    //tax deduction
        deductionCheckbox.setBounds(55, 485, 200, 25);
        frame.add(deductionCheckbox);

        //buttons
        
        JButton calculateButton = new JButton("Calculate Tax");
        calculateButton.setBounds(250, 520, 200, 40);
        calculateButton.setBackground(new Color(188, 150, 100));
        calculateButton.setForeground(Color.BLACK);
        frame.add(calculateButton);

        JButton previousButton = new JButton("Previous");
        previousButton.setBounds(220, 600, 100, 30);
        frame.add(previousButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(360, 600, 100, 30);
        frame.add(clearButton);
        
        //actions
        previousButton.addActionListener(e -> {
            frame.dispose();
            new UserInformation(name, mobile, nid, email, division, address);
        });

        clearButton.addActionListener(e -> {
            incomeField.setText("");
            taxCategoryComboBox.setSelectedIndex(0);
            incomeTypeGroup.clearSelection();
            deductionCheckbox.setSelected(false);
            incomeError.setText("");
        });

        calculateButton.addActionListener(e -> {
            String input = incomeField.getText().trim();
            try {
                double income = Double.parseDouble(input);
                if (income <= 0) throw new NumberFormatException();
                incomeError.setText("");
                frame.dispose();
                new Result(name, income,
                    taxCategoryComboBox.getSelectedItem().toString(),
                    yearlyRadio.isSelected() ? "Yearly" : "Monthly",
                    deductionCheckbox.isSelected());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please , fill in the field correctly before proceeding.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        

        // Income Field Validation on Focus Lost
        incomeField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String input = incomeField.getText().trim();
                if (input.isEmpty()) {
                    incomeError.setText("Empty");
                    incomeError.setForeground(Color.DARK_GRAY);
                } else {
                    try {
                        double income = Double.parseDouble(input);
                        if (income <= 0) throw new NumberFormatException();
                        incomeError.setText("");
                    } catch (NumberFormatException ex) {
                        incomeError.setText("Please,input a valid number");
                        incomeError.setForeground(new Color (139,0,0));
                    }
                }
            }
        });

       
        frame.setVisible(true);
    }

	private Color Color(int i, int j, double d) {
		// TODO Auto-generated method stub
		return null;
	}
}