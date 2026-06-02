import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class RegistrationandLoginSystem extends JFrame {
	
    private CardLayout cardLayout;
    private JPanel containerPanel;
    private IDandPasswords idAndPassword;
    private ImageIcon eyeOpen;
    private ImageIcon eyeClose;
    
    
    public RegistrationandLoginSystem() {
    	
   
        idAndPassword = new IDandPasswords();

        ImageIcon originalOpen = new ImageIcon("eyesopenrbg.png");
	    Image scaledOpen = originalOpen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	    eyeOpen = new ImageIcon(scaledOpen);
	    
	    ImageIcon originalClose = new ImageIcon("eyescloserbg.png");
	    Image scaledClose = originalClose.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	    eyeClose = new ImageIcon(scaledClose);
        
        ImageIcon image1 = new ImageIcon("logo4.png");
        setTitle("Registration And Login Section");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(image1.getImage());

        BackgroundPanel backgroundPanel = new BackgroundPanel("Hotelroom.jpg");
        backgroundPanel.setLayout(null); 
        setContentPane(backgroundPanel);

        SlidingPanel sideGallery = new SlidingPanel();
        sideGallery.setBounds(0, 0, 525, 600);
        backgroundPanel.add(sideGallery);
        
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout) {
        	@Override
        	protected void paintComponent(Graphics g) {
        		g.setColor(getBackground());
        		g.fillRect(0, 0, getWidth(), getHeight());
        		super.paintComponent(g);
        	}
        };
        containerPanel.setOpaque(false); 
        containerPanel.setBackground(new Color(255, 255, 255, 100)); 
        containerPanel.setBounds(525, 0, 500, 600); 
        containerPanel.add(createWelcomePanel(), "Welcome");
        containerPanel.add(createLoginPanel(), "Login");
        containerPanel.add(createRegisterPanel(),"Register");

        backgroundPanel.add(containerPanel);
        
        cardLayout.show(containerPanel, "Welcome");
        
        setVisible(true);
    }


	private JPanel  createWelcomePanel() {
		
	  
	    JPanel panel = new JPanel();
	    panel.setOpaque(false);
	    panel.setBackground(new Color(160,160,160));
	    panel.setLayout(null);


	    ImageIcon image = new ImageIcon("logo4.png");
	    Image scaledImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
	    logoLabel.setBounds(135, 80, 200, 200);

	    JLabel titleLabel = new JLabel("Welcome!");
	    titleLabel.setFont(new Font("Verdana", Font.ITALIC, 36));
	    titleLabel.setBounds(145, 200, 200, 200);
	    titleLabel.setForeground(Color.white);
	    
	    JButton loginBtn = new JButton("Login");
	    loginBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
	    loginBtn.setBackground(new Color(33, 150, 243));
	    loginBtn.setForeground(Color.WHITE);
	    loginBtn.setFocusPainted(false);
	    loginBtn.setBounds(135, 350, 200, 45);
	    loginBtn.addActionListener(e -> cardLayout.show(containerPanel, "Login"));
	    loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    

	    JButton registerBtn = new JButton("Register");
	    registerBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
	    registerBtn.setBackground(new Color(76, 175, 80));
	    registerBtn.setForeground(Color.WHITE);
	    registerBtn.setFocusPainted(false);
	    registerBtn.setBounds(135, 400, 200, 45);
	    registerBtn.addActionListener(e -> cardLayout.show(containerPanel, "Register"));
	    registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    

	    JLabel copyright = new JLabel("© 2026 JavaLuxe Hotel Booking System. All Rights Reserved.");
        copyright.setBounds(60, 525, 500, 25);
        copyright.setFont(new Font("Arial", Font.BOLD, 13));
        copyright.setForeground(Color.black);
        
	    
        panel.add(logoLabel);
        panel.add(titleLabel);
        panel.add(loginBtn);
        panel.add(registerBtn);
        panel.add(copyright);
        
	    return panel;
	}
	
	 private JPanel createLoginPanel() {
	        JPanel panel = new JPanel();
	        panel.setOpaque(false);
	        panel.setLayout(null);
	        panel.setBackground(Color.white);
	        
	        JLabel titleLabel = new JLabel("<html><font color ='black'>Login</font></html>");
	        titleLabel.setFont(new Font("Georgia", Font.BOLD, 32));
	        titleLabel.setBounds(170, 80, 150, 40);
	        panel.add(titleLabel);
	       
	        
	        JLabel userLabel = new JLabel("<html><b><font color ='black'>Username</font></html>");
	        userLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
	        userLabel.setBounds(70, 160, 100, 25);
	        panel.add(userLabel);
	       
	        
	        JTextField userField = new JTextField();
	        userField.setBounds(70, 185, 300, 35);
	        userField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
	        userField.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
	        userField.setBackground(new Color(220, 240, 255));
		    userField.setForeground(Color.black);
	        panel.add(userField);
	        
	        JLabel passLabel = new JLabel("<html><b><font color ='black'>Password</font></html>");
	        passLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
	        passLabel.setBounds(70, 235, 100, 25);
	        panel.add(passLabel);
	       
	        JPanel passContainer = new JPanel(new BorderLayout());
	        passContainer.setBounds(70, 260, 300, 35);
	        passContainer.setBackground(new Color(220, 240, 255));
	        
	        JPasswordField passField = new JPasswordField();
	        passField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
	        passField.setBackground(new Color(220, 240, 255));
	        passField.setFont(new Font("Arial", Font.PLAIN, 14));
	        
	        JButton showPasswordBtn = new JButton(eyeClose);
		    showPasswordBtn.setPreferredSize(new Dimension(40, 35));
		    showPasswordBtn.setContentAreaFilled(false);
		    showPasswordBtn.setBorderPainted(false);
		    showPasswordBtn.setFocusable(false);
		    showPasswordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    
		    showPasswordBtn.addActionListener(e -> {
		        if (passField.getEchoChar() == '\u0000') {
		            passField.setEchoChar('•');
		            showPasswordBtn.setIcon(eyeClose);
		        } else {
		            passField.setEchoChar('\u0000');
		            showPasswordBtn.setIcon(eyeOpen);
		        }
		        passField.requestFocusInWindow();
		    });
		    passContainer.add(passField, BorderLayout.CENTER);
		    passContainer.add(showPasswordBtn, BorderLayout.EAST);
		    
		    panel.add(passContainer);
	        
	        
	        JLabel messageLabel = new JLabel();
	        messageLabel.setBounds(135, 310, 300, 25);
	        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
	        panel.add(messageLabel);
	        
	        JButton loginButton = new JButton("Login");
	        loginButton.setBounds(70, 350, 300, 40);
	        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
	        loginButton.setBackground(new Color(33, 150, 243));
	        loginButton.setForeground(Color.WHITE);
	        loginButton.setFocusPainted(false);
	        
	        KeyAdapter enterKeyListener = new KeyAdapter() {
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    String username = userField.getText();
	                    String password = new String(passField.getPassword());
	                    
	                    if (username.isEmpty() || password.isEmpty()) {
	                        messageLabel.setForeground(Color.RED);
	                        messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
	                        messageLabel.setText("All fields are required!");  
	                    } else {
	                        loginButton.doClick();
	                    }
	                }
	            }
	        };
	        userField.addKeyListener(enterKeyListener);
	        passField.addKeyListener(enterKeyListener);
	        
	        
	        
	    
		    loginButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	
		            String username = userField.getText();
		            String password = new String(passField.getPassword());
		           
		            if (username.isEmpty() || password.isEmpty()) {
		                messageLabel.setForeground(Color.red);
		                messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
		                messageLabel.setText("All fields are required!");
		            } 
		            else if (idAndPassword.login(username, password)) {
		                User user = idAndPassword.getUser(username);

		               
		                    new UserDashboard(user).setVisible(true);
		                    dispose();
		                

		                    try {
		                       
		                        UIManager.put("OptionPane.background", Color.WHITE);
		                        UIManager.put("Panel.background", Color.WHITE);
		                        UIManager.put("OptionPane.messageForeground", new Color(33, 150, 243)); // blue text
		                        UIManager.put("OptionPane.messageFont", new Font("Georgia", Font.BOLD, 16));
		                        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 14));
		                        UIManager.put("Button.background", new Color(33, 150, 243));
		                        UIManager.put("Button.foreground", Color.WHITE);
		                    } catch (Exception ex) {
		                        ex.printStackTrace();
		                    }

		             
		                    String message = "<html>"
		                            + "<h2 style='color:#2196F3;'>Welcome " + username + "!</h2>"
		                            + "<p style='font-size:14px;color:gray;'>Email: <b>" + user.email + "</b></p>"
		                            + "</html>";

		                    JOptionPane.showMessageDialog(
		                            panel,
		                            message,
		                            "Login Successful",
		                            JOptionPane.INFORMATION_MESSAGE
		                    );

		                userField.setText("");
		                passField.setText("");
		                messageLabel.setText("");
		                
		             

		            } else {
		                messageLabel.setForeground(Color.RED);
		                messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
		                messageLabel.setText("Invalid username or password!");
		            }
		        }
		    });
	        panel.add(loginButton);
	        
	        JLabel registerLink = new JLabel("<html> <font color='green'> <b> Don't have an account?</b></font> <font color='blue'> Register Here!</font>");
	        registerLink.setBounds(110, 405, 250, 25);
	        registerLink.setFont(new Font("Arial", Font.PLAIN, 12));
	        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        registerLink.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                cardLayout.show(containerPanel, "Register");
	            }
	        });
	        panel.add(registerLink);
	        
	        JLabel copyright = new JLabel("© 2026 JavaLuxe Hotel Booking System. All Rights Reserved.");
	        copyright.setBounds(60, 525, 500, 25);
	        copyright.setFont(new Font("Arial", Font.BOLD, 13));
	        copyright.setForeground(Color.black);
	        panel.add(copyright);
	        
	        JButton backButton = new JButton("← Back");
	        backButton.setBounds(20, 20, 80, 30);
	        backButton.setBackground(Color.RED);
	        backButton.setFocusPainted(false);
	        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        backButton.addActionListener(e -> cardLayout.show(containerPanel, "Welcome"));
	        panel.add(backButton);
	        
	        return panel;
	    }
	
	 private JPanel createRegisterPanel() {
		    JPanel panel = new JPanel();
		    panel.setOpaque(false);
		    panel.setLayout(null);
		    panel.setBackground(new Color(160, 160, 160));

		
		    JLabel titleLabel = new JLabel("<html><font color='black'>Register</font></html>");
		    titleLabel.setFont(new Font("Georgia", Font.BOLD, 32));
		    titleLabel.setBounds(155, 60, 200, 40);  
		    panel.add(titleLabel);

		   
		    JLabel userLabel = new JLabel("<html><b><font color ='black'>Username</font></html>");
		    userLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
		    userLabel.setBounds(70, 130, 100, 25); 
		    panel.add(userLabel);

		    JTextField userField = new JTextField();
		    userField.setBounds(70, 155, 300, 35);  
		    userField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
		    userField.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
		    userField.setBackground(new Color(220, 240, 255));
		    userField.setForeground(Color.black);
		    panel.add(userField);

	
		    JLabel emailLabel = new JLabel("<html><b><font color ='black'>Email</font></html>");
		    emailLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
		    emailLabel.setBounds(70, 200, 100, 25);  
		    panel.add(emailLabel);

		    JTextField emailField = new JTextField();
		    emailField.setBounds(70, 225, 300, 35);
		    emailField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
		    emailField.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
		    emailField.setBackground(new Color(220, 240, 255));
		    emailField.setForeground(Color.black);
		    panel.add(emailField);

		   
		    JLabel passLabel = new JLabel("<html><b><font color ='black'>Password</font></html>");
		    passLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
		    passLabel.setBounds(70, 270, 100, 25);  
		    panel.add(passLabel);

		    JPanel passContainer = new JPanel(new BorderLayout());
	        passContainer.setBounds(70, 295, 300, 35);
	        passContainer.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
	        passContainer.setBackground(new Color(220, 240, 255));
	        
	        JPasswordField passField = new JPasswordField();
	        passField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
	        passField.setBackground(new Color(220, 240, 255));
	        passField.setFont(new Font("Arial", Font.PLAIN, 14));
	        
	        JButton showPasswordBtn = new JButton(eyeClose);
		    showPasswordBtn.setPreferredSize(new Dimension(40, 35));
		    showPasswordBtn.setContentAreaFilled(false);
		    showPasswordBtn.setBorderPainted(false);
		    showPasswordBtn.setFocusable(false);
		    showPasswordBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    
		    showPasswordBtn.addActionListener(e -> {
		        if (passField.getEchoChar() == '\u0000') {
		            passField.setEchoChar('•');
		            showPasswordBtn.setIcon(eyeClose);
		        } else {
		            passField.setEchoChar('\u0000');
		            showPasswordBtn.setIcon(eyeOpen);
		        }
		        passField.requestFocusInWindow();
		    });
		    passContainer.add(passField, BorderLayout.CENTER);
		    passContainer.add(showPasswordBtn, BorderLayout.EAST);
		    
		    panel.add(passContainer);
		 
		    JLabel confirmLabel = new JLabel("<html><b><font color ='black'>Confirm Password</font></html>");
		    confirmLabel.setFont(new Font("Georgia", Font.ROMAN_BASELINE, 14));
		    confirmLabel.setBounds(70, 340, 150, 25); 
		    panel.add(confirmLabel);

		    JPanel confirmContainer = new JPanel(new BorderLayout());
	        confirmContainer.setBounds(70, 365, 300, 35);
	        confirmContainer.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
	        confirmContainer.setBackground(new Color(220, 240, 255));
	        
	        JPasswordField confirmField = new JPasswordField();
	        confirmField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); 
	        confirmField.setBackground(new Color(220, 240, 255));
	        confirmField.setFont(new Font("Arial", Font.PLAIN, 14));
	        
	        JButton showConfirmBtn = new JButton(eyeClose);
		    showConfirmBtn.setPreferredSize(new Dimension(40, 35));
		    showConfirmBtn.setContentAreaFilled(false);
		    showConfirmBtn.setBorderPainted(false);
		    showConfirmBtn.setFocusable(false);
		    showConfirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    
		    showConfirmBtn.addActionListener(e -> {
		        if (confirmField.getEchoChar() == '\u0000') {
		            confirmField.setEchoChar('•');
		            showConfirmBtn.setIcon(eyeClose);
		        } else {
		            confirmField.setEchoChar('\u0000');
		            showConfirmBtn.setIcon(eyeOpen);
		        }
		        confirmField.requestFocusInWindow();
		    });
		    confirmContainer.add(confirmField, BorderLayout.CENTER);
		    confirmContainer.add(showConfirmBtn, BorderLayout.EAST);
		    
		    panel.add(confirmContainer);
		  
		    JLabel messageLabel = new JLabel();
		    messageLabel.setBounds(135, 410, 300, 25);  
		    messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		    panel.add(messageLabel);

		    
		    JButton registerButton = new JButton("Register");
		    registerButton.setBounds(70, 445, 300, 40); 
		    registerButton.setFont(new Font("Arial", Font.BOLD, 16));
		    registerButton.setBackground(new Color(33, 150, 243));
		    registerButton.setForeground(Color.WHITE);
		    registerButton.setFocusPainted(false);
		    panel.add(registerButton);

		    KeyAdapter enterKeyListener = new KeyAdapter() {
		        public void keyPressed(KeyEvent e) {
		            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		                String username = userField.getText().trim();
		                String email = emailField.getText().trim();
		                String password = new String(passField.getPassword());
		                String confirm = new String(confirmField.getPassword());
		               
		                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
		                    messageLabel.setForeground(Color.RED);
		                    messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
		                    messageLabel.setText("All fields are required!");
		                } else {
		                    registerButton.doClick();
		                }
		            }
		        }
		    };
		    userField.addKeyListener(enterKeyListener);
		    emailField.addKeyListener(enterKeyListener);
		    passField.addKeyListener(enterKeyListener);
		    confirmField.addKeyListener(enterKeyListener);
		    
		    registerButton.addActionListener(e -> {
		        String username = userField.getText().trim();
		        String email = emailField.getText().trim();
		        int atIndex = email.indexOf("@");
		        String emailUser = email.substring(0, atIndex);
		        String password = new String(passField.getPassword());
		        String confirm = new String(confirmField.getPassword());

		        
		        if (atIndex == -1) {
		 		   JOptionPane.showMessageDialog(this, "Invalid Email!", "Validation Error", JOptionPane.ERROR_MESSAGE);
		 		   return;
		 	   } else if (emailUser.length() < 6) {
				   JOptionPane.showMessageDialog(this, "Invalid Email! Email account must be 6 characters above", "Validation Error" ,JOptionPane.ERROR_MESSAGE);
				   return;
		 	   }
		        
		        if (!email.matches("^[A-Za-z0-9_.-]+@[a-z0-9.-]+\\.com$")) {
		   	    	JOptionPane.showMessageDialog(this, "Invalid Email!", "Validation Error", JOptionPane.ERROR_MESSAGE);
		            return;
		   	    } 

		        if (!password.equals(confirm)) {
		            messageLabel.setForeground(Color.RED);
		            messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
		            messageLabel.setText("Passwords do not match!");
		            return;
		        }

		        if (password.length() < 6) {
		            messageLabel.setForeground(Color.RED);
		            messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
		            messageLabel.setText("Password must be at least 6 characters!");
		            return;
		        }

		        if (idAndPassword.register(username, password, email)) {
		            JOptionPane.showMessageDialog(panel,
		                    "Registration successful!\nYou can now login.",
		                    "Success",
		                    JOptionPane.INFORMATION_MESSAGE);
		            
		       
		            userField.setText("");
		            emailField.setText("");
		            passField.setText("");
		            confirmField.setText("");
		            messageLabel.setText("");
		            
		            cardLayout.show(containerPanel, "Login");
		        } else {
		            messageLabel.setForeground(Color.RED);
		            messageLabel.setFont(new Font("Arial", Font.BOLD, 17));
		            messageLabel.setText("Username already exists!");
		        }
		    });

		   
		    JLabel loginLink = new JLabel("<html><b><font color = 'green'>Already have an account?</b></font> <font color='blue'>Login</font></html>");
		    loginLink.setBounds(130, 495, 250, 25);  // ⬅️ CENTERED
		    loginLink.setFont(new Font("Arial", Font.PLAIN, 12));
		    loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		    loginLink.addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            cardLayout.show(containerPanel, "Login");
		        }
		    });
		    panel.add(loginLink);
		    
		    JLabel copyright = new JLabel("© 2026 JavaLuxe Hotel Booking System. All Rights Reserved.");
	        copyright.setBounds(60, 525, 500, 25);
	        copyright.setFont(new Font("Arial", Font.BOLD, 13));
	        copyright.setForeground(Color.black);
	        panel.add(copyright);
		   
		    JButton backButton = new JButton("← Back");
		    backButton.setBounds(20, 20, 80, 30);
		    backButton.setBackground(Color.RED);
		    backButton.setForeground(Color.BLACK);
		    backButton.setFocusPainted(false);
		    backButton.addActionListener(e -> cardLayout.show(containerPanel, "Welcome"));
		    panel.add(backButton);

		    return panel;
		}

	 
} @SuppressWarnings("serial")
class SlidingPanel extends JPanel {
    private JPanel filmstrip;
    private Timer moveTimer;   
    private Timer autoTimer;   
    private int currentX = 0;
    private int targetX = 0;
    private final int WIDTH = 525;
    private final int HEIGHT = 600;
    private int imageIndex = 0;
    private JLabel motto;
    // NEW: Array to hold our 3 dots
    private JLabel[] dots = new JLabel[3];

    public SlidingPanel() {
        setLayout(null);
        setOpaque(false);
        
        filmstrip = new JPanel(new GridLayout(1, 3));
        filmstrip.setBounds(0, 0, WIDTH * 3, HEIGHT); 
        
        filmstrip.add(createImageLabel("luxury.jpg"));
        filmstrip.add(createImageLabel("luxury1.jpg"));
        filmstrip.add(createImageLabel("luxury2.jpg"));
        add(filmstrip);
        
        motto = new OutlinedLabel("Redefining Elegance, One Stay at a Time", SwingConstants.CENTER);
        motto.setFont(new Font("Arial", Font.BOLD, 20));
        motto.setBounds(0, HEIGHT - 130, WIDTH, 40);
        motto.setForeground(Color.white);
        
        
     // --- THE UPDATED DOT SECTION ---
        JPanel dotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        dotPanel.setOpaque(false);
        // Increase the height slightly to 40 to ensure the 24pt dots aren't clipped
        dotPanel.setBounds(0, HEIGHT - 80, WIDTH, 40); 
        
        for (int i = 0; i < 3; i++) {
            dots[i] = new JLabel("●");
            dots[i].setFont(new Font("Arial", Font.BOLD, 20));
            dots[i].setForeground(i == 0 ? Color.WHITE : Color.GRAY);
            dotPanel.add(dots[i]);
        }

        // Add them in this specific order
        this.add(dotPanel);
        this.add(filmstrip);
        this.add(motto);
        // Re-assert that dotPanel is at the very front (Index 0)
        this.setComponentZOrder(motto, 0);
        this.setComponentZOrder(dotPanel, 1);
        this.setComponentZOrder(filmstrip, 2);
        
        // --- MOVEMENT MOTOR ---
        moveTimer = new Timer(10, e -> {
            if (Math.abs(currentX - targetX) > 1) {
                currentX += (targetX - currentX) * 0.1;
                filmstrip.setLocation(currentX, 0);
            } else {
                currentX = targetX;
                filmstrip.setLocation(currentX, 0);
                moveTimer.stop();
            }
        });

        autoTimer = new Timer(3000, e -> startNextSlide());
        autoTimer.start();

    }

    private void startNextSlide() {
        imageIndex = (imageIndex + 1) % 3;
        targetX = -(imageIndex * WIDTH);
        
        // --- NEW: UPDATE DOT COLORS ---
        for (int i = 0; i < dots.length; i++) {
            dots[i].setForeground(i == imageIndex ? Color.WHITE : Color.GRAY);
        }
        
        moveTimer.start();
    }

    private JLabel createImageLabel(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }
    
    
}	@SuppressWarnings("serial")
class OutlinedLabel extends JLabel {
    private Color outlineColor = Color.BLACK;
    private int strokeWidth = 2;

    public OutlinedLabel(String text, int alignment) {
        super(text, alignment);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // This is the trick: We use a TextLayout to get the shape of the font
        FontMetrics fm = g2.getFontMetrics(getFont());
        int x = 0; // Since we use SwingConstants.CENTER and setBounds correctly
        
        // Calculate centered position manually for the draw
        if (getHorizontalAlignment() == SwingConstants.CENTER) {
            x = (getWidth() - fm.stringWidth(getText())) / 2;
        }
        int y = fm.getAscent() + (getHeight() - fm.getHeight()) / 2;

        java.awt.font.TextLayout tl = new java.awt.font.TextLayout(getText(), getFont(), g2.getFontRenderContext());
        java.awt.Shape shape = tl.getOutline(java.awt.geom.AffineTransform.getTranslateInstance(x, y));

        g2.setColor(outlineColor);
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.draw(shape);
        g2.setColor(getForeground());
        g2.fill(shape);
        g2.dispose();
    }
}