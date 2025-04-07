
package TX;

import javax.swing.*;
import java.awt.*;

public class Tax {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Welcome to Tax Estimator");   //create frame
        frame.setSize(500, 525);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new BackgroundPanel();   //custom JPanel
        backgroundPanel.setLayout(null);   //absolute layout

        JLabel titleLabel = new JLabel("TAX - ESTIMATOR", SwingConstants.CENTER);  //title Label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(199, 0, 0));  //red
        titleLabel.setBounds(100, 50, 300, 50);
        backgroundPanel.add(titleLabel);

        JButton exitButton = new JButton("EXIT");  //exit button
        exitButton.setBounds(70, 400, 150, 30);
        exitButton.setBackground(new Color(0, 139, 0)); //dark green
        exitButton.setForeground(new Color(144, 238, 144)); //light green
        exitButton.setFocusPainted(false);  //remove focusborder
        exitButton.setContentAreaFilled(false); //remove whitecolor
        exitButton.setOpaque(true);
        exitButton.addActionListener(e -> System.exit(0)); 
        backgroundPanel.add(exitButton);

        // Enter Button
        JButton enterButton = new JButton("ENTER");
        enterButton.setBounds(270, 400, 150, 30);
        enterButton.setBackground(new Color(178, 0, 0)); //dark red
        enterButton.setForeground(new Color(255, 182, 193)); //light red 
        enterButton.setFocusPainted(false);
        enterButton.setContentAreaFilled(false);
        enterButton.setOpaque(true);
        enterButton.addActionListener(e -> {
            frame.dispose();
           new UserInformation(null, null, null, null, null, null); 
        });
        
        backgroundPanel.add(enterButton);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}


class BackgroundPanel extends JPanel {   //background Image
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/picture1.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Image Not Found! Check the path.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
