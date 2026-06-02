import java.awt.*;
import javax.swing.*;




@SuppressWarnings("serial")
class BackgroundPanel extends JPanel {
	 private Image backgroundImage;
	 
	 BackgroundPanel(String imagePath){
		 backgroundImage = new ImageIcon(imagePath).getImage();
		 this.setLayout(new CardLayout());
	 }
	 @Override
	  protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	    }
}
