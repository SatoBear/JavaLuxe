import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Search extends JFrame {

	JCheckBox checkBox;
	Choice choice;
	JTable table;
	JTable table1;
	DefaultTableModel model;
	
	Object[][] rooms = {
			
//	First Floor
		{"101", "The Citizen Rooms", "Available", "₱2,199/night", "1st Floor","Efficiency & High-speed WIFI"},
		{"102", "The Citizen Rooms", "Occupied", "₱2,199/night", "1st Floor","Efficiency & High-speed WIFI"},
		{"103", "The Citizen Rooms", "Available", "₱2,199/night", "1st Floor","Efficiency & High-speed WIFI"},
		{"104", "Family Rooms", "Available", "₱4,999/night", "1st Floor","Extra Space & Amenities"},
		{"105", "Family Rooms", "Available", "₱4,999/night", "1st Floor","Extra Space & Amenities"},
		{"106", "Family Rooms", "Occupied", "₱4,999/night", "1st Floor","Extra Space & Amenities"},
		{"107", "Family Rooms", "Occupied", "₱4,999/night", "1st Floor","Extra Space & Amenities"},
		{"108", "Couple Rooms", "Occupied", "₱3,599/night", "1st Floor","Intimate Design & Comfort"},
		{"109", "Couple Rooms", "Available", "₱3,599/night", "1st Floor","Intimate Design & Comfort"},
		{"110", "Vip Rooms", "Available", "₱10,599/night", "1st Floor","Luxury Suite & Panoramic Views"},
		
//		2nd Floor
		{"201","The Centurion","Available","₱3,999/night","2nd Floor","Bold, metallic elegance"},
		{"202","The Centurion","Available","₱3,999/night","2nd Floor","Bold, metallic elegance"},
		{"203","The Centurion","Occupied","₱3,999/night","2nd Floor","Bold, metallic elegance"},
		{"204","The Centurion","Occupied","₱3,999/night","2nd Floor","Bold, metallic elegance"},
		{"205","The Centurion","Available","₱3,999/night","2nd Floor","Bold, metallic elegance"},
		{"206","The Velvet Lounge","Available","₱5,599/night","2nd Floor","Deep, moody comfort."},
		{"207","The Velvet Lounge","Available","₱5,599/night","2nd Floor","Deep, moody comfort."},
		{"208","The Velvet Lounge","Occupied","₱5,599/night","2nd Floor","Deep, moody comfort."},
		{"209","The Velvet Lounge","Occupied","₱5,599/night","2nd Floor","Deep, moody comfort."},
		{"210","The Velvet Lounge","Available","₱5,599/night","2nd Floor","Deep, moody comfort."},
		{"211","The Pantheon","Available","₱6,399/night","2nd Floor","Grand, timeless design"},
		{"212","The Pantheon","Available","₱6,399/night","2nd Floor","Grand, timeless design"},
		{"213","The Pantheon","Occupied","₱6,399/night","2nd Floor","Grand, timeless design"},
		{"214","The Pantheon","Available","₱6,399/night","2nd Floor","Grand, timeless design"},
		{"215","The Pantheon","Occupied","₱6,399/night","2nd Floor","Grand, timeless design"},
		{"216","The Rendezvous","Occupied","₱10,299/night","2nd Floor","Stylish, modern vibes"},
		{"217","The Rendezvous","Occupied","₱10,299/night","2nd Floor","Stylish, modern vibes"},
		{"218","The Rendezvous","Available","₱10,299/night","2nd Floor","Stylish, modern vibes"},
		{"219","The Rendezvous","Available","₱10,299/night","2nd Floor","Stylish, modern vibes"},
		{"220","The Presidential Suite","Available","₱15,999/night","2nd Floor","Expansive, multi-room layout. High-level privacy."},
		
		
		
	};
	
	Search(){
		ImageIcon image1 = new ImageIcon("logo.png");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 62, 80));
		panel.setBounds(5, 5, 690, 490);
		panel.setLayout(null);
		add(panel);
		

		JLabel searchForRoom = new JLabel("Search For Room");
		searchForRoom.setBounds(250, 11, 186, 31);
		searchForRoom.setForeground(Color.white);
		searchForRoom.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(searchForRoom);

		checkBox = new JCheckBox("Only Display Available Rooms");
		checkBox.setBounds(350, 69, 250, 23);
		checkBox.setForeground(Color.white);
		checkBox.setBackground(new Color(44, 62, 80));
		panel.add(checkBox);

		choice = new Choice();
		choice.add("All Types");
		choice.add("The Citizen Rooms");
		choice.add("Couple Rooms");
		choice.add("Family Rooms");
		choice.add("Vip Rooms");
		choice.add("The Centurion");
		choice.add("The Velvet Lounge");
		choice.add("The Pantheon");
		choice.add("The Rendezvous");
		choice.add("The Presidential Suite");
		choice.setBounds(153, 70, 120, 20);
		panel.add(choice);

//		Add Columns
		String[] columns = {"Room No.", "Bed Type", "Status", "Price", "Floor","Descriptions"};
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(135);
		table.setFont(new Font("Calibri",Font.BOLD,12));
	
		// Assign a "Color"  to Column Index 2 (Status)
		table.getColumnModel().getColumn(2).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
		    public Component getTableCellRendererComponent(JTable table, Object value, 
		            boolean isSelected, boolean hasFocus, int row, int column) {
		        
		        // Get the basic cell setup from Java
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		        // Check if the cell is not empty
		        if (value != null) {
		            String status = value.toString(); // Convert the cell data to Text

		            // Logic: Set color based on the word found
		            if (status.equals("Available")) {
		                c.setForeground(new Color(0, 153, 51)); // Set text to Green
		            } else if (status.equals("Occupied")) {
		                c.setForeground(Color.RED);             // Set text to Red
		            }
		        }
		        return c; // Show the colored cell on the screen
		    }
		});
		
//		Enable Scroll if data is too long
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 120, 670, 350);
		panel.add(scrollPane);
		
		showRooms();
		
// Auto update if you change choice selection it call showRooms() to update the table
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				showRooms();
			}
		});

		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRooms();
			}
		});

		setLayout(null);
		setLocation(500, 200);
		setSize(700, 530);
		setVisible(true);
		setResizable(false);
		setIconImage(image1.getImage());
	}

	private void showRooms() {
		model.setRowCount(0); // Clear all rows from table
		
		String bedType = choice.getSelectedItem(); // Get selected bed type
		
		for (Object[] room : rooms) { // Loop through all rooms
			String roomBedType = (String) room[1];  // Get bed type (column 1)
			String status = (String) room[2];   // Get status (column 2)
			
			  // Check if bed type matches
			boolean typeMatch = bedType.equals("All Types") || roomBedType.equals(bedType);
			
			  // Check if availability matches
//			Also if you check the radio but the status is occupied it became false
			boolean availMatch = !checkBox.isSelected() || status.equals("Available");
			
			  // If BOTH conditions are true, add the room to table
			if (typeMatch && availMatch) {
				model.addRow(room);
			}
		}
		
	}
}

	