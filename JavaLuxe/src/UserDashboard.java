import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserDashboard extends JFrame {

	 UserDashboard(User user) {
//		 Icon/Favicon
		 ImageIcon image1 = new ImageIcon("logo.png");

//		 Main Panel
		 JPanel panel = new JPanel();
		 panel.setLayout(null);
		 panel.setBounds(280,5,1238,820);
		 panel.setBackground(new Color(44, 62, 80));
		 add(panel);

		 
		 
		 
		 // Logo in the main panel
		 ImageIcon logoIcon = new ImageIcon("logo4.png");
		 Image logoImage = logoIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		 ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
		 JLabel logoLabel = new JLabel(scaledLogoIcon);
		 logoLabel.setBounds(419, 210, 400, 400);
		 panel.add(logoLabel);

//		 Side panel with background image
		 BackgroundPanel panel1 = new BackgroundPanel("Hotelroom.jpg");
		 panel1.setLayout(null);
		 panel1.setBounds(5,5,270,820);
		 add(panel1);

// Spacing for all btn 
		 int buttonX = 35;
		 int buttonY = 50;
		 int buttonWidth = 200;
		 int buttonHeight = 40;
		 int buttonSpacing = 20;
		 
		 JButton btn = new JButton("Booking Form");
		 btn.setBounds(buttonX, buttonY + 4 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
		 btn.setBackground(new Color(33, 150, 243));
		 btn.setForeground(Color.white);
		 btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		 btn.setFocusPainted(false);
		 btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 panel1.add(btn);
		 btn.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 try {
					new BookingForm();
					 
				 } catch (Exception E) {
					 E.printStackTrace();
				 }
			 }
		 });

		
		
		 JButton btn6 = new JButton("Search Room");
		 btn6.setBounds(buttonX, buttonY + 5 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
		 btn6.setBackground(new Color(33, 150, 243));
		 btn6.setForeground(Color.white);
		 btn6.setFont(new Font("Tahoma", Font.BOLD, 14));
		 btn6.setFocusPainted(false);
		 btn6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 panel1.add(btn6);
		 btn6.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 try {
					 new Search();
				 } catch (Exception E) {
					 E.printStackTrace();
				 }
			 }
		 });
		 JButton backButton = new JButton("Log Out!");
		 	backButton.setBounds(buttonX, buttonY + 7 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);
		    backButton.setBackground(Color.RED);
		    backButton.setForeground(Color.BLACK);
		    backButton.setFocusPainted(false);
		    panel1.add(backButton);
		    backButton.addActionListener(new ActionListener() {
				 @Override
				 public void actionPerformed(ActionEvent e) {
					 try {
						 new RegistrationandLoginSystem();
						 dispose();
					 } catch (Exception E) {
						 E.printStackTrace();
					 }
				 }
			 });

		getContentPane().setBackground(Color.white);
		setLayout(null);
		setSize(1950,1090);
		setVisible(true);
		setIconImage(image1.getImage());
		setTitle("Dashboard");
		
		
		
	}
	
}
