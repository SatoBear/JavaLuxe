import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



@SuppressWarnings("serial")
public class JavaLuxeHotelBooking extends JPanel implements ActionListener {
	JFrame frame;
    JButton BookNow;
    Image backgroundImage;
    

    JavaLuxeHotelBooking() {

        ImageIcon image = new ImageIcon("logo.png");
        ImageIcon image2 = new ImageIcon("logo4.png");
       
       
        
        backgroundImage = new ImageIcon("hotelroom.jpg").getImage();
        this.setLayout(new BorderLayout());
   

        Image scaledImage = image2.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
      

        JLabel label = new JLabel(resizedIcon, SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setFont(new Font("Consolas",Font.BOLD,25));
        label.setIconTextGap(60);
        this.add(label, BorderLayout.CENTER);

        BookNow = new JButton("Book Now!");
        BookNow.setFont(new Font("Georgia", Font.BOLD, 18));
        BookNow.setBackground(new Color(239, 191, 4));
        BookNow.setForeground(Color.BLACK);
        BookNow.addActionListener(this);
        BookNow.setFocusPainted(false); 
        BookNow.setBorderPainted(false);
        BookNow.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(BookNow);
        this.add(buttonPanel, BorderLayout.SOUTH);

        frame = new JFrame("JavaLuxe Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setContentPane(this);
        frame.setIconImage(image.getImage());
        frame.setLayout(new java.awt.BorderLayout());
        frame.getContentPane().setBackground(new Color(102,102,255));
        frame.setResizable(false);

        frame.add(label, java.awt.BorderLayout.CENTER);
        frame.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        RegistrationandLoginSystem frame = new RegistrationandLoginSystem();
        frame.setVisible(true);
 
    }

    public static void main(String[] args) {
        new JavaLuxeHotelBooking();
      
      
      
       
    }
    
}

