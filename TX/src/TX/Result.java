package TX;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;

public class Result {
	DecimalFormat df = new DecimalFormat("0.00");  
    public static void main(String[] args) {
        String name = "N/A"; 
        double income = 0.00; 
        String category = "N/A"; 
        String type = "N/A"; 
        boolean deduction = false; 
        
        new Result(name, income, category, type, deduction);
    }
    
    public Result(String name, double income, String category, String type, boolean deduction) {
        JFrame frame = new JFrame("Tax Estimator - Result");
        frame.setSize(850, 666); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        
        
        JPanel bgPanel = new JPanel() {   //image
            private Image bgImage;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    bgImage = new ImageIcon(getClass().getResource("/picture2.jpg")).getImage();
                } catch (Exception e) {
                    System.out.println("Image not found");
                }
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bgPanel.setLayout(null);

        JLabel nLabel = new JLabel("National Board of Revenue, Bangladesh");
        nLabel.setFont(new Font("Arial", Font.BOLD, 25));
        nLabel.setForeground(new Color(139, 69, 19));  //brown color
        nLabel.setBounds(200, 20, 500, 30);

        bgPanel.add(nLabel);

        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(139, 69, 19)); 
        namePanel.setBounds(10, 60, 270, 40);
        bgPanel.add(namePanel);
        
        JLabel nameLabel = new JLabel("Name: " + (name == null || name.isEmpty() ? "N/A" : name));  //name label 
        nameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        nameLabel.setForeground(new Color(255, 255, 255)); 
        nameLabel.setBounds(20, 180, 200, 30);
        namePanel.add(nameLabel);
        

        
        JPanel infoPanel = new JPanel();  //user info
        infoPanel.setLayout(new GridLayout(6, 0, 5, 3)); 
        infoPanel.setOpaque(false);
        infoPanel.setBounds(40, 110, 300, 140);

        String typeValue = (type == null || type.isEmpty()) ? "N/A" : type;
        String categoryValue = (category == null || category.isEmpty()) ? "N/A" : category;
        double annualIncome = typeValue.equals("Monthly") ? income * 12 : income;
        String incomeStr = income <= 0 ? "0.000000" : df.format(annualIncome);
        String deductionStr = deduction ? "Yes" : "No";
        double tax = (income <= 0) ? 0 : calculateTax(annualIncome, category, deduction);

        JLabel[] labels = {
                new JLabel("Income Type : " + typeValue),
                new JLabel("Tax Category : " + categoryValue),
                new JLabel("Income : " + incomeStr),
                new JLabel("Applied Deduction : " + deductionStr),
                new JLabel("Total Tax : " +df.format(tax)  + " BDT."),
                new JLabel(""),
        };

        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            label.setForeground(new Color(90, 50, 20)); //light browncolor
            infoPanel.add(label);
        }

       
        bgPanel.add(infoPanel);

        
        String[] columnNames = {"Deduction "+"Type", "Amount(BDT)", "Tax Saved(BDT)", "Original Amount(BDT)", "Tax Reduced(BDT)"};   //deduction table 
        Object[][] data;

        if (deduction && income > 0) {
            double[] deductionAmounts = {20000, 10000, 5000, 3000, 8000}; 
        data = new Object[][] {
            {"Equity Investment", df.format(deductionAmounts[0]), df.format(2000.0), df.format(annualIncome), df.format(annualIncome - 20000)},
            {"Health Insurance", df.format(deductionAmounts[1]), df.format(1000.0), df.format(annualIncome), df.format(annualIncome - 10000)},
            {"Academic Expenses", df.format(deductionAmounts[2]), df.format(500.0), df.format(annualIncome), df.format(annualIncome - 5000)},
            {"Donation", df.format(deductionAmounts[3]), df.format(300.0), df.format(annualIncome), df.format(annualIncome - 3000)},
            {"Medical Expenses", df.format(deductionAmounts[4]), df.format(800.0), df.format(annualIncome), df.format(annualIncome - 8000)},
        };
    }
        
        else {
            data = new Object[][] {
                {"Equity Investment", df.format(0), df.format(0), df.format(0), df.format(0)},
                {"Health Insurance", df.format(0), df.format(0), df.format(0), df.format(0)},
                {"Academic Expenses", df.format(0), df.format(0), df.format(0), df.format(0)},
                {"Donation", df.format(0), df.format(0), df.format(0), df.format(0)},
                {"Medical Expenses", df.format(0), df.format(0), df.format(0), df.format(0)}
            };
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(295, 120, 535, 135); //table size 
        table.setRowHeight(23);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
        bgPanel.add(scrollPane);

    
        JLabel taxMessage = new JLabel("Build the nation with your contribution.");  //message 
        taxMessage.setFont(new Font("Arial", Font.PLAIN, 18));
        taxMessage.setForeground(Color.BLACK); 
        taxMessage.setBounds(260, 580, 520, 25);

        bgPanel.add(taxMessage);
        
        
        //button
     
   
        JButton saveBtn = new JButton("Download");
        saveBtn.setBounds(330, 520, 200, 40);
        saveBtn.setBackground(new Color(119, 69, 49));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Downloaded");
        });
        bgPanel.add(saveBtn);

        frame.setContentPane(bgPanel);
        frame.setVisible(true);
    }
     
    
    private double calculateTax(double income, String category, boolean deduction) {
        double tax = 0;
        if (category.equals("General")) {
            if (income <= 300000) tax = 0;
            else if (income <= 600000) tax = (income - 300000) * 0.1;
            else tax = 30000 + (income - 600000) * 0.15;
        } else {
            if (income <= 400000) tax = 0;
            else if (income <= 700000) tax = (income - 400000) * 0.1;
            else tax = 30000 + (income - 700000) * 0.15;
        }
        if (deduction) tax -= 5000;
        return Math.max(tax, 0);
    }
}