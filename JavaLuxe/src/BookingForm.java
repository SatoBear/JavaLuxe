import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BookingForm extends JFrame {
    private JTextField nameField, emailField, phoneField, nightsField;
    private JComboBox<String> roomTypeCombo, roomNumberCombo, guestsCombo, servicesCombo, paymentCombo;
    private JTextArea summaryArea;
    private JButton bookButton, clearButton, addRoomButton,checkoutButton;
    private JLabel totalLabel;
    
    // Tracking Occupied Rooms
    private HashSet<String> occupiedRooms;
    
    // Store multiple room bookings
    private ArrayList<RoomBooking> roomBookings;
    
    // Store completed bookings for checkout
    private ArrayList<BookingRecord> completedBookings;
    
    private final String[] roomTypes = {
        "The Citizen Rooms", "Family Rooms", "Couple Rooms", "Vip Rooms",
        "The Centurion", "The Velvet Lounge", "The Pantheon", "The Rendezvous",
        "The Presidential Suite"
    };
    private final double[] roomPrices = {2199, 4999, 3599, 10599, 
    		3999, 5599, 6399, 10299, 15999};
    private DecimalFormat df = new DecimalFormat("#,##0.00");
    ImageIcon image = new ImageIcon("logo.png");
    
    // Inner class to store room booking details
    class RoomBooking {
        String roomType;
        String roomNumber;
        int nights;
        int guests;
        String service;
        double roomPrice;
        boolean checkedOut; // Track checkout status per room
        
        RoomBooking(String roomType, String roomNumber, int nights, int guests, String service, double roomPrice) {
            this.roomType = roomType;
            this.roomNumber = roomNumber;
            this.nights = nights;
            this.guests = guests;
            this.service = service;
            this.roomPrice = roomPrice;
            this.checkedOut = false;
        }
    }
    
    // Inner class to store completed booking records
    class BookingRecord {
        String referenceNo;
        String guestName;
        String email;
        String phone;
        ArrayList<RoomBooking> rooms;
        String paymentMethod;
        LocalDateTime bookingDate;
        double grandTotal;
        
        BookingRecord(String ref, String name, String email, String phone, 
                      ArrayList<RoomBooking> rooms, String payment, double total) {
            this.referenceNo = ref;
            this.guestName = name;
            this.email = email;
            this.phone = phone;
            this.rooms = new ArrayList<>(rooms);
            this.paymentMethod = payment;
            this.bookingDate = LocalDateTime.now();
            this.grandTotal = total;
        }
    }
    
   public BookingForm(){
	   initOccupiedRooms();
	   roomBookings = new ArrayList<>();
	   completedBookings = new ArrayList<>();
	   setTitle("JavaLuxe - Booking System");
	   setSize(950,780); 
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setLocationRelativeTo(null);
	   setResizable(false);
	   setIconImage(image.getImage());
	   
	   JPanel mainPanel = new JPanel(new BorderLayout(15,15));
	   mainPanel.setBackground(new Color(45, 13, 13));
	   mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
	   
	   JPanel headerPanel = createHeaderPanel();
       JPanel formPanel = createFormPanel();
       JPanel summaryPanel = createSummaryPanel();
       JPanel buttonPanel = createButtonPanel();

       mainPanel.add(headerPanel, BorderLayout.NORTH);
       mainPanel.add(formPanel, BorderLayout.WEST);
       mainPanel.add(summaryPanel, BorderLayout.CENTER);
       mainPanel.add(buttonPanel, BorderLayout.SOUTH);

       updateRoomNumbers();
       
       add(mainPanel);
       setVisible(true);
   }

   private void initOccupiedRooms() {
	   occupiedRooms = new HashSet<>();
	   String[] occupied = {"102", "106", "107", "108", "203", "204", "208", "209", "213", "215", "216", "217"};
	   for (String r : occupied) occupiedRooms.add(r);
   }

   private JPanel createHeaderPanel() {
	   JPanel panel = new JPanel();
	   panel.setBackground(new Color(13, 71, 161));
	   panel.setPreferredSize(new Dimension(900,100));
	   panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	   
	   JLabel titleLabel = new JLabel("JAVALUXE HOTEL");
	   titleLabel.setFont(new Font("Consolas", Font.ITALIC, 32));
	   titleLabel.setForeground(Color.white);
	   titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	   
	   JLabel subtitleLabel = new JLabel("Redefining Elegance, One Stay at a Time");
	   subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 16));
	   subtitleLabel.setForeground(new Color(200, 230, 255));
	   subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	   
	   panel.add(Box.createVerticalGlue());
       panel.add(titleLabel);
       panel.add(Box.createRigidArea(new Dimension(0, 5)));
       panel.add(subtitleLabel);
       panel.add(Box.createVerticalGlue());
	   
	   return panel;
   }

   private JPanel createFormPanel() {
	   JPanel panel = new JPanel(new GridBagLayout());
       panel.setBackground(new Color(33, 33, 33));
       panel.setBorder(BorderFactory.createTitledBorder(
    		BorderFactory.createLineBorder(new Color(255, 215, 0), 2),
           "Booking Information",
           0, 0,
           new Font("Arial", Font.BOLD, 14),
           new Color(240, 248, 255)
       ));
       panel.setPreferredSize(new Dimension(450,550));
       
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10,12,10,12);
       gbc.fill = GridBagConstraints.HORIZONTAL;
       
       addSectionLabel(panel,gbc," Guest Information",0);
       	
       	addFormField(panel,gbc,"Full Name:",1);
       	nameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        panel.add(nameField,gbc);
        
    	addFormField(panel,gbc,"Email:",2);
       	emailField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(emailField,gbc);
        
    	addFormField(panel,gbc,"Phone Number:",3);
       	phoneField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(phoneField,gbc);
       
        addSectionLabel(panel, gbc, "Booking Details", 4);
        
        addFormField(panel,gbc,"Room Type:",5);
        roomTypeCombo = new JComboBox<>(roomTypes);
        roomTypeCombo.addActionListener(e ->  updateRoomNumbers());
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(roomTypeCombo,gbc);
        
        addFormField(panel,gbc,"Room Number:", 6);
        roomNumberCombo = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 6;
        panel.add(roomNumberCombo,gbc);
        
        addFormField(panel,gbc,"Nights:", 7);
        nightsField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 7;
        panel.add(nightsField,gbc);
        
        addFormField(panel,gbc,"Guests:",8);
        guestsCombo = new JComboBox<>(new String[]{"1","2","3","4"});
        gbc.gridx = 1; gbc.gridy = 8;
        panel.add(guestsCombo, gbc);
        
        addFormField(panel,gbc, "Extra Service:", 9); 
        servicesCombo = new JComboBox<>(new String[]{
        		"None",
        		"Luxury Breakfast Buffet – ₱1,800",
        		"In-Room Spa Treatment – ₱5,500",
        		"Luxury Airport Transfer (Sedan) – ₱4,000",
        		"Helicopter Transfer (VIP) – ₱45,000 (optional premium service)",
        		"In-Room Fine Dining Service – ₱2,000"
        });
        gbc.gridx = 1; gbc.gridy = 9;
        panel.add(servicesCombo, gbc);
        
        addFormField(panel, gbc, "Payment:", 10);
        paymentCombo = new JComboBox<>(new String[]{"Cash", "Credit Card", "Debit Card", "GCash", "PayMaya"});
        gbc.gridx = 1; gbc.gridy = 10;
        panel.add(paymentCombo, gbc);
        
	   return panel;
   }

   private void addSectionLabel(JPanel panel, GridBagConstraints gbc, String text, int row) {
	   gbc.gridx = 0; gbc.gridy = row;
	   gbc.gridwidth = 2;
	   gbc.insets = new Insets(15,12,5,12); 
	   JLabel label = new JLabel(text);
	   label.setFont(new Font("Georgia", Font.BOLD , 14));
	   label.setForeground(new Color(255, 215, 0)); 
	   panel.add(label, gbc);
	   gbc.gridwidth = 1;
	   gbc.insets = new Insets(10,12,10,12);
   }

   private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, int row) {
       gbc.gridx = 0; gbc.gridy = row;
       gbc.weightx = 0.3; 
       JLabel label = new JLabel(labelText);
       label.setFont(new Font("Arial", Font.BOLD, 12));
       label.setForeground(Color.white);
       panel.add(label, gbc);
   }

   private void updateRoomNumbers() {
	   roomNumberCombo.removeAllItems();
	   String selectedType = (String) roomTypeCombo.getSelectedItem();
	   if(selectedType.equals("The Citizen Rooms")) roomNumRange(101,103);
	   else if (selectedType.equals("Family Rooms")) roomNumRange(104,107);
	   else if (selectedType.equals("Couple Rooms")) roomNumRange(108,109);
	   else if (selectedType.equals("Vip Rooms")) roomNumRange(110,110);
	   else if (selectedType.equals("The Centurion")) roomNumRange(201,205);
	   else if (selectedType.equals("The Velvet Lounge")) roomNumRange(206,210);
	   else if (selectedType.equals("The Pantheon")) roomNumRange(211, 215);
       else if (selectedType.equals("The Rendezvous")) roomNumRange(216, 219);
       else if (selectedType.equals("The Presidential Suite")) roomNumRange(220, 220);
   }

   private void roomNumRange(int start, int end) {
	   for(int i = start; i <= end; i++) {
		   roomNumberCombo.addItem(String.valueOf(i));
	   }
   }
	   
   private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(76, 175, 80), 2),
            "Booking Summary", 0, 0, new Font("Arial", Font.BOLD, 14), new Color(76, 175, 80)
        ));
        
	     summaryArea = new JTextArea();
	     summaryArea.setEditable(false);
	     summaryArea.setFont(new Font("Monospaced", Font.BOLD,13));
	     summaryArea.setBackground(Color.black);
	     summaryArea.setForeground(Color.white);
	     summaryArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	     
	     panel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);
	     
	     JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	     totalPanel.setBackground(new Color(76, 175, 80));
	     totalLabel = new JLabel("TOTAL: ₱0.00");
	     totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
	     totalLabel.setForeground(Color.WHITE);
	     totalPanel.add(totalLabel);
	        
	     panel.add(totalPanel, BorderLayout.SOUTH);
	     return panel;
   }

   private JPanel createButtonPanel(){
	   JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
	   panel.setBackground(new Color(240,248,255));
	   
	   addRoomButton = new JButton("Add Room");
	   addRoomButton.setPreferredSize(new Dimension(150,45));
	   addRoomButton.setBackground(new Color(33, 150, 243));  
	   addRoomButton.setForeground(Color.WHITE);           
	   addRoomButton.setFont(new Font("Arial", Font.BOLD, 14));
	   addRoomButton.setToolTipText("Add this room and book another (optional)");
	   addRoomButton.addActionListener(e-> addAnotherRoom());
	   
	   bookButton = new JButton("Submit Booking");
	   bookButton.setPreferredSize(new Dimension(180,45));
	   bookButton.setBackground(new Color(76, 175, 80));  
	   bookButton.setForeground(Color.WHITE);           
	   bookButton.setFont(new Font("Arial", Font.BOLD, 14));
	   bookButton.setToolTipText("Finalize booking (current room + any added rooms)");
	   bookButton.addActionListener(e-> submitBooking());
	   
	   clearButton = new JButton("Clear All");
	   clearButton.setPreferredSize(new Dimension(150,45));
	   clearButton.setBackground(new Color(255,152,0));
	   clearButton.setForeground(Color.white);
	   clearButton.setToolTipText("Clear all fields and added rooms");
	   clearButton.addActionListener(e -> clearFields());
	   
	   checkoutButton = new JButton("Check-Out");
	   checkoutButton.setPreferredSize(new Dimension(140,45));
	   checkoutButton.setBackground(new Color(183, 28, 28));
	   checkoutButton.setForeground(Color.WHITE);
	   checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
	   checkoutButton.addActionListener(e -> showCheckoutDialog());
	  
	   panel.add(addRoomButton);
	   panel.add(bookButton); 
	   panel.add(clearButton); 
	   panel.add(checkoutButton);
	   return panel;
   }
   
//    Shows all rooms individually
   private void showCheckoutDialog() {
       if (completedBookings.isEmpty()) {
           JOptionPane.showMessageDialog(this, 
               "No active bookings to check out!", 
               "Information", 
               JOptionPane.INFORMATION_MESSAGE);
           return;
       }
       
       JDialog checkoutDialog = new JDialog(this, "Guest Check-Out", true);
       checkoutDialog.setSize(750, 650);
       checkoutDialog.setLocationRelativeTo(this);
       checkoutDialog.setResizable(false);
       
       JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
       mainPanel.setBackground(new Color(45, 13, 13));
       mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
       
       // Header
       JPanel headerPanel = new JPanel();
       headerPanel.setBackground(new Color(13, 71, 161));
       headerPanel.setPreferredSize(new Dimension(720, 70));
       JLabel headerLabel = new JLabel("ACTIVE BOOKINGS - CHECK-OUT");
       headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
       headerLabel.setForeground(Color.WHITE);
       headerPanel.add(headerLabel);
       
       // Bookings panel with scroll
       JPanel bookingsPanel = new JPanel();
       bookingsPanel.setLayout(new BoxLayout(bookingsPanel, BoxLayout.Y_AXIS));
       bookingsPanel.setBackground(Color.WHITE);
       bookingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       
       // Create a panel for each booking
       for (BookingRecord booking : completedBookings) {
           JPanel bookingPanel = createBookingCheckoutPanel(booking, checkoutDialog, bookingsPanel);
           bookingsPanel.add(bookingPanel);
           bookingsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
       }
       
       JScrollPane scrollPane = new JScrollPane(bookingsPanel);
       scrollPane.setBorder(BorderFactory.createEmptyBorder());
       scrollPane.getVerticalScrollBar().setUnitIncrement(16);
       
       // Close button
       JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       bottomPanel.setBackground(new Color(240, 248, 255));
       JButton closeButton = new JButton("Close");
       closeButton.setPreferredSize(new Dimension(120, 40));
       closeButton.setBackground(new Color(158, 158, 158));
       closeButton.setForeground(Color.WHITE);
       closeButton.setFont(new Font("Arial", Font.BOLD, 14));
       closeButton.addActionListener(e -> checkoutDialog.dispose());
       bottomPanel.add(closeButton);
       
       mainPanel.add(headerPanel, BorderLayout.NORTH);
       mainPanel.add(scrollPane, BorderLayout.CENTER);
       mainPanel.add(bottomPanel, BorderLayout.SOUTH);
       
       checkoutDialog.add(mainPanel);
       checkoutDialog.setVisible(true);
   }

   private JPanel createBookingCheckoutPanel(BookingRecord booking, JDialog parentDialog, JPanel bookingsContainer) {
       JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
       mainPanel.setMaximumSize(new Dimension(700, Integer.MAX_VALUE));
       mainPanel.setBackground(Color.WHITE);
       mainPanel.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(new Color(13, 71, 161), 2),
           BorderFactory.createEmptyBorder(15, 15, 15, 15)
       ));
       
       // Guest info panel
       JPanel infoPanel = new JPanel(new GridLayout(4, 1, 3, 3));
       infoPanel.setBackground(Color.WHITE);
       
       JLabel refLabel = new JLabel("Reference: " + booking.referenceNo);
       refLabel.setFont(new Font("Arial", Font.BOLD, 16));
       refLabel.setForeground(new Color(13, 71, 161));
       
       JLabel guestLabel = new JLabel("Guest: " + booking.guestName);
       guestLabel.setFont(new Font("Arial", Font.BOLD, 14));
       
       JLabel emailLabel = new JLabel("Email: " + booking.email);
       emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
       
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
       JLabel dateLabel = new JLabel("Booked: " + booking.bookingDate.format(dtf));
       dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
       
       infoPanel.add(refLabel);
       infoPanel.add(guestLabel);
       infoPanel.add(emailLabel);
       infoPanel.add(dateLabel);
       
       // Rooms panel - Shows each room individually with checkout button
       JPanel roomsPanel = new JPanel();
       roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
       roomsPanel.setBackground(new Color(245, 245, 245));
       roomsPanel.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(new Color(200, 200, 200)),
           BorderFactory.createEmptyBorder(10, 10, 10, 10)
       ));
       
       // Add each room with its own checkout button
       int roomCount = 0;
       for (RoomBooking room : booking.rooms) {
           roomCount++;
           JPanel roomRow = createRoomRowWithCheckout(room, roomCount, booking, parentDialog, mainPanel, bookingsContainer);
           roomsPanel.add(roomRow);
           roomsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
       }
       
       // Total panel
       JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       totalPanel.setBackground(Color.WHITE);
       JLabel totalLabel = new JLabel("Total: ₱" + df.format(booking.grandTotal));
       totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
       totalLabel.setForeground(new Color(76, 175, 80));
       totalPanel.add(totalLabel);
       
       // Combine panels
       JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
       centerPanel.setBackground(Color.WHITE);
       centerPanel.add(infoPanel, BorderLayout.NORTH);
       centerPanel.add(roomsPanel, BorderLayout.CENTER);
       centerPanel.add(totalPanel, BorderLayout.SOUTH);
       
       mainPanel.add(centerPanel, BorderLayout.CENTER);
       
       return mainPanel;
   }

   // NEW METHOD: Create individual room row with checkout button
   private JPanel createRoomRowWithCheckout(RoomBooking room, int roomNumber, BookingRecord booking, 
                                             JDialog parentDialog, JPanel bookingPanel, JPanel bookingsContainer) {
       JPanel panel = new JPanel(new BorderLayout(10, 5));
       panel.setBackground(new Color(255, 255, 255));
       panel.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
           BorderFactory.createEmptyBorder(8, 10, 8, 10)
       ));
       panel.setMaximumSize(new Dimension(650, 70));
       
       // Room details
       JPanel detailsPanel = new JPanel(new GridLayout(3, 1, 2, 2));
       detailsPanel.setBackground(Color.WHITE);
       
       // Room number and type
       JLabel roomLabel = new JLabel(String.format("Room %d: #%s - %s", 
           roomNumber, room.roomNumber, room.roomType));
       roomLabel.setFont(new Font("Arial", Font.BOLD, 14));
       
       // Nights and guests
       JLabel detailsLabel = new JLabel(String.format("%d night(s), %d guest(s)", 
           room.nights, room.guests));
       detailsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
       detailsLabel.setForeground(Color.DARK_GRAY);
       
       // Service info
       String serviceText = room.service.equals("None") ? "No extra service" : room.service;
       JLabel serviceLabel = new JLabel("Service: " + serviceText);
       serviceLabel.setFont(new Font("Arial", Font.PLAIN, 11));
       serviceLabel.setForeground(new Color(100, 100, 100));
       
       detailsPanel.add(roomLabel);
       detailsPanel.add(detailsLabel);
       detailsPanel.add(serviceLabel);
       
       // Checkout button or status label
       if (room.checkedOut) {
           // Show checked out status
           JLabel statusLabel = new JLabel("Checked Out");
           statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
           statusLabel.setForeground(new Color(76, 175, 80));
           statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
           statusLabel.setPreferredSize(new Dimension(120, 50));
           panel.add(statusLabel, BorderLayout.EAST);
           
           // Update styling for checked out room
           roomLabel.setForeground(new Color(150, 150, 150));
           panel.setBackground(new Color(245, 245, 245));
           detailsPanel.setBackground(new Color(245, 245, 245));
       } else {
           // Show checkout button
           JButton checkoutBtn = new JButton("Check-Out");
           checkoutBtn.setPreferredSize(new Dimension(110, 45));
           checkoutBtn.setBackground(new Color(76, 175, 80));
           checkoutBtn.setForeground(Color.WHITE);
           checkoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
           checkoutBtn.setFocusPainted(false);
           
           checkoutBtn.addActionListener(e -> {
               int confirm = JOptionPane.showConfirmDialog(
                   parentDialog,
                   String.format("Confirm check-out for:\n\n" +
                                 "Room #%s - %s\n" +
                                 "Guest: %s\n" +
                                 "Reference: %s\n\n" +
                                 "This room will be available for booking again.",
                                 room.roomNumber, room.roomType, 
                                 booking.guestName, booking.referenceNo),
                   "Confirm Check-Out",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.QUESTION_MESSAGE
               );
               
               if (confirm == JOptionPane.YES_OPTION) {
                   // Mark room as checked out
                   room.checkedOut = true;
                   
                   // Remove from occupied rooms
                   occupiedRooms.remove(room.roomNumber);
                   
                   // Show success message
                   JOptionPane.showMessageDialog(
                       parentDialog,
                       String.format("Check-out successful!\n\n" +
                                     "Room #%s is now available.\n" +
                                     "Thank you for staying at JavaLuxe Hotel!",
                                     room.roomNumber),
                       "Check-Out Successful",
                       JOptionPane.INFORMATION_MESSAGE
                   );
                   
                   // Update the UI
                   panel.remove(checkoutBtn);
                   
                   JLabel statusLabel = new JLabel("Checked Out");
                   statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
                   statusLabel.setForeground(new Color(76, 175, 80));
                   statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
                   statusLabel.setPreferredSize(new Dimension(110, 45));
                   
                   panel.add(statusLabel, BorderLayout.EAST);
                   roomLabel.setForeground(new Color(150, 150, 150));
                   panel.setBackground(new Color(245, 245, 245));
                   detailsPanel.setBackground(new Color(245, 245, 245));
                   
                   panel.revalidate();
                   panel.repaint();
                   
                   // Check if all rooms are checked out
                   boolean allCheckedOut = true;
                   for (RoomBooking rb : booking.rooms) {
                       if (!rb.checkedOut) {
                           allCheckedOut = false;
                           break;
                       }
                   }
                   
                   // If all rooms checked out, remove booking
                   if (allCheckedOut) {
                       completedBookings.remove(booking);
                       bookingPanel.setVisible(false);
                       bookingsContainer.revalidate();
                       bookingsContainer.repaint();
                       
                       // If no more bookings, close dialog
                       if (completedBookings.isEmpty()) {
                           JOptionPane.showMessageDialog(
                               parentDialog,
                               "All bookings have been checked out!",
                               "All Clear",
                               JOptionPane.INFORMATION_MESSAGE
                           );
                           parentDialog.dispose();
                       }
                   }
                   
                   // Update room numbers in main form
                   updateRoomNumbers();
               }
           });
           
           panel.add(checkoutBtn, BorderLayout.EAST);
       }
       
       panel.add(detailsPanel, BorderLayout.CENTER);
       
       return panel;
   }

   private void addAnotherRoom() {
	   String name = nameField.getText().trim();
	   if (nameField.getText().trim().isEmpty()) {
		   JOptionPane.showMessageDialog(this, "Please enter Full Name first!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   } else if (!name.matches("[A-Za-z ]+")) {
		   JOptionPane.showMessageDialog(this, "Invalid Name! Name must only contain letters!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   String phone = phoneField.getText().trim();
	   if (!phone.matches("09\\d{9}")) {
		   JOptionPane.showMessageDialog(this, "Invalid Phone Number! Must start with '09' and contain 11 digits total.", "Validation Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   String email = emailField.getText().trim();
	   int atIndex = email.indexOf("@");
	   if (atIndex == -1) {
		   JOptionPane.showMessageDialog(this, "Invalid Email!", "Validation Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   String emailUser = email.substring(0, atIndex);
	   if (!email.matches("^[A-Za-z0-9_.-]+@[a-z0-9.-]+\\.com$")) {
		   JOptionPane.showMessageDialog(this, "Invalid Email!", "Validation Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   } else if (emailUser.length() < 6) {
		   JOptionPane.showMessageDialog(this, "Invalid Email! Email account must be 6 characters above", "Validation Error" ,JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   String selectedRoom = (String) roomNumberCombo.getSelectedItem();
	   if (occupiedRooms.contains(selectedRoom)) {
		   JOptionPane.showMessageDialog(this,
				   "Room Number " + selectedRoom + " is OCCUPIED!\nPlease choose another room.",
				   "Unavailable", JOptionPane.WARNING_MESSAGE);
		   return;  
	   }
	   
	   for (RoomBooking rb : roomBookings) {
		   if (rb.roomNumber.equals(selectedRoom)) {
			   JOptionPane.showMessageDialog(this,
					   "Room " + selectedRoom + " already added to booking!",
					   "Duplicate Room", JOptionPane.WARNING_MESSAGE);
			   return;
		   }
	   }
	   
	   try {
		   int nights = Integer.parseInt(nightsField.getText());
		   if(nights <= 0) {
			   JOptionPane.showMessageDialog(this, "Nights must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
			   return;
		   }
		   
		   int roomTypeIndex = roomTypeCombo.getSelectedIndex();
		   double roomPrice = roomPrices[roomTypeIndex];
		   
		   RoomBooking newRoom = new RoomBooking(
				   (String) roomTypeCombo.getSelectedItem(),
				   selectedRoom,
				   nights,
				   guestsCombo.getSelectedIndex() + 1,
				   (String) servicesCombo.getSelectedItem(),
				   roomPrice
		   );
		   
		   roomBookings.add(newRoom);
		   updateSummaryPreview();
		   
		   nightsField.setText("");
		   roomTypeCombo.setSelectedIndex(0);
		   guestsCombo.setSelectedIndex(0);
		   servicesCombo.setSelectedIndex(0);
		   updateRoomNumbers();
		   
		   JOptionPane.showMessageDialog(this, 
				   "Room " + selectedRoom + " added!\nTotal rooms: " + roomBookings.size() + 
				   "\n\nYou can add more rooms or click 'Submit Booking' to finalize.",
				   "Room Added", JOptionPane.INFORMATION_MESSAGE);
		   
	   } catch (NumberFormatException e) {
		   JOptionPane.showMessageDialog(this, "Please enter valid number for nights!", "Error", JOptionPane.ERROR_MESSAGE);
	   }
   }

   private void updateSummaryPreview() {
	   StringBuilder preview = new StringBuilder();
	   preview.append("═══════════════════════════════════════════\n");
	   preview.append("       BOOKING PREVIEW\n");
	   preview.append("═══════════════════════════════════════════\n\n");
	   preview.append("GUEST: " + nameField.getText() + "\n\n");
	   preview.append("ROOMS ADDED (" + roomBookings.size() + "):\n\n");
	   
	   double totalPreview = 0;
	   for (int i = 0; i < roomBookings.size(); i++) {
		   RoomBooking rb = roomBookings.get(i);
		   preview.append(String.format("%d. Room %s - %s\n", (i+1), rb.roomNumber, rb.roomType));
		   preview.append(String.format("   %d night(s), %d guest(s)\n", rb.nights, rb.guests));
		   
		   double roomTotal = rb.roomPrice * rb.nights;
		   double sCharge = 0;
		   if (rb.service.contains("Luxury Breakfast Buffet")) sCharge = 1800 * rb.nights;
		   else if (rb.service.contains("In-Room Spa Treatment")) sCharge = 5500;
		   else if (rb.service.contains("Luxury Airport Transfer (Sedan)")) sCharge = 4000;
		   else if (rb.service.contains("Helicopter Transfer (VIP)")) sCharge = 45000;
		   else if (rb.service.contains("In-Room Fine Dining Service")) sCharge = 2000;
		   
		   double subtotal = roomTotal + sCharge;
		   totalPreview += subtotal;
		   preview.append(String.format("   Subtotal: ₱%s\n\n", df.format(subtotal)));
	   }
	   
	   preview.append("───────────────────────────────────\n");
	   preview.append(String.format("ESTIMATED TOTAL: ₱%s\n", df.format(totalPreview)));
	   preview.append("(before tax and discounts)\n\n");
	   preview.append("Click 'Submit Booking' to finalize\n");
	   preview.append("or 'Add Room' for more rooms");
	   
	   summaryArea.setText(preview.toString());
	   totalLabel.setText("ESTIMATED: ₱" + df.format(totalPreview));
   }

   private void submitBooking() {
	   String name = nameField.getText().trim();
	   if (name.isEmpty()) {
		   JOptionPane.showMessageDialog(this, "Please enter Full Name!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   } else if (!name.matches("[A-Za-z ]+")) {
		   JOptionPane.showMessageDialog(this, "Invalid Name! Name must only contain letters!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   String phone = phoneField.getText().trim();
	   if (!phone.matches("09\\d{9}")) {
		   JOptionPane.showMessageDialog(this, "Invalid Phone Number! Must start with '09' and contain 11 digits total.", "Validation Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   } 

	   String email = emailField.getText().trim();
	   int atIndex = email.indexOf("@");
	   if (atIndex == -1) {
		   JOptionPane.showMessageDialog(this, "Invalid Email!", "Validation Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   String emailUser = email.substring(0, atIndex);
	   if (!email.matches("^[A-Za-z0-9_.-]+@[a-z0-9.-]+\\.com$")) {
		   JOptionPane.showMessageDialog(this, "Invalid Email!", "Validation Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   } else if (emailUser.length() < 6) {
		   JOptionPane.showMessageDialog(this, "Invalid Email! Email account must be 6 characters above", "Validation Error" ,JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   boolean hasCurrentBooking = false;
	   try {
		   if (!nightsField.getText().trim().isEmpty()) {
			   int nights = Integer.parseInt(nightsField.getText());
			   if (nights > 0) {
				   String selectedRoom = (String) roomNumberCombo.getSelectedItem();
				   
				   if (occupiedRooms.contains(selectedRoom)) {
					   JOptionPane.showMessageDialog(this,
							   "Current room " + selectedRoom + " is OCCUPIED!\nPlease choose another room or remove it.",
							   "Unavailable", JOptionPane.WARNING_MESSAGE);
					   return;  
				   }
				   
				   hasCurrentBooking = true;
			   }
		   }
	   } catch (NumberFormatException e) {
	   }
	   
	   if (roomBookings.isEmpty() && !hasCurrentBooking) {
		   JOptionPane.showMessageDialog(this, 
				   "Please complete the booking details!\n\nEither fill in the current room info,\n or use 'Add Room' to add rooms first.", 
				   "No Booking Data", JOptionPane.WARNING_MESSAGE);
		   return;
	   }
	   
	   if (hasCurrentBooking) {
		   try {
			   int nights = Integer.parseInt(nightsField.getText());
			   String selectedRoom = (String) roomNumberCombo.getSelectedItem();
			   int roomTypeIndex = roomTypeCombo.getSelectedIndex();
			   double roomPrice = roomPrices[roomTypeIndex];
			   
			   RoomBooking currentRoom = new RoomBooking(
					   (String) roomTypeCombo.getSelectedItem(),
					   selectedRoom,
					   nights,
					   guestsCombo.getSelectedIndex() + 1,
					   (String) servicesCombo.getSelectedItem(),
					   roomPrice
			   );
			   roomBookings.add(currentRoom);
		   } catch (NumberFormatException e) {
		   }
	   }
	   
	   double grandTotal = 0;
	   
	   StringBuilder summary = new StringBuilder();
	   summary.append("═══════════════════════════════════════════\n");
	   summary.append("       JAVALUXE OFFICIAL RECEIPT\n");
	   summary.append("═══════════════════════════════════════════\n\n");
	   
	   String ref = "GRD" + System.currentTimeMillis() % 100000;
	   LocalDate today = LocalDate.now();
	   DateTimeFormatter dfDate = DateTimeFormatter.ofPattern("MMM dd, yyyy");
	   
	   summary.append(String.format("Reference No.: %s\n", ref));
	   summary.append(String.format("Date Issued:   %s\n\n", today.format(dfDate)));
	   summary.append("GUEST INFORMATION:\n");
	   summary.append(String.format("  Name:  %s\n", nameField.getText()));
	   summary.append(String.format("  Email: %s\n", emailField.getText()));
	   summary.append(String.format("  Phone: %s\n\n", phoneField.getText()));
	   
	   summary.append("ROOMS BOOKED (" + roomBookings.size() + "):\n");
	   summary.append("═══════════════════════════════════════════\n");
	   
	   for (int i = 0; i < roomBookings.size(); i++) {
		   RoomBooking rb = roomBookings.get(i);
		   summary.append(String.format("\nROOM %d:\n", (i+1)));
		   summary.append(String.format("  Room: %s (%s)\n", rb.roomNumber, rb.roomType));
		   summary.append(String.format("  Guests: %d person(s)\n", rb.guests));
		   summary.append(String.format("  Nights: %d night(s)\n", rb.nights));
		   
		   LocalDateTime now = LocalDateTime.now();
		   LocalDate checkOut = now.toLocalDate().plusDays(rb.nights);
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy (hh:mm a)");
		   
		   summary.append("  Check-in:  " + now.format(dtf) + "\n");
		   summary.append("  Check-out: " + checkOut.format(dfDate) + " (3:00 PM)\n");
		   
		   double roomCharge = rb.roomPrice * rb.nights;
		   double sCharge = 0;
		   if (rb.service.contains("Luxury Breakfast Buffet")) sCharge = 1800 * rb.nights;
		   else if (rb.service.contains("In-Room Spa Treatment")) sCharge = 5500;
		   else if (rb.service.contains("Luxury Airport Transfer (Sedan)")) sCharge = 4000;
		   else if (rb.service.contains("Helicopter Transfer (VIP)")) sCharge = 45000;
		   else if (rb.service.contains("In-Room Fine Dining Service")) sCharge = 2000;
		   
		   summary.append(String.format("  Room Charges:   ₱%s\n", df.format(roomCharge)));
		   summary.append(String.format("  Extra Services: ₱%s\n", df.format(sCharge)));
		   
		   double subtotal = roomCharge + sCharge;
		   double tax = subtotal * 0.08;
		   double roomTotal = subtotal + tax;
		   
		   double discount = 0;
		   String dt = "";
		   if(rb.nights >=7) {
			   discount = roomTotal * 0.25;
			   dt = "25% (7+nights)";
		   } else if (rb.nights >= 3) {
			   discount = roomTotal * 0.20;
			   dt = "20% (3+nights)";
		   }
		   
		   summary.append(String.format("  VAT (8%%):      ₱%s\n", df.format(tax)));
		   if (discount > 0) {
			   summary.append(String.format("  Discount (%s): -₱%s\n", dt, df.format(discount)));
		   }
		   summary.append(String.format("  Room Total:     ₱%s\n", df.format(roomTotal - discount)));
		   
		   grandTotal += (roomTotal - discount);
	   }
	   
	   summary.append("\n═══════════════════════════════════════════\n");
	   summary.append(String.format("GRAND TOTAL:      ₱%s\n\n", df.format(grandTotal)));
	   summary.append(String.format("Payment Method: %s\n\n", paymentCombo.getSelectedItem()));
	   summary.append("Thank you for choosing JavaLuxe Hotel!\n");
	   summary.append("═══════════════════════════════════════════");
	   
	   summaryArea.setText(summary.toString());
	   totalLabel.setText("TOTAL: ₱" + df.format(grandTotal));
	   
	   BookingRecord record = new BookingRecord(
		   ref,
		   nameField.getText().trim(),
		   emailField.getText().trim(),
		   phoneField.getText().trim(),
		   roomBookings,
		   (String) paymentCombo.getSelectedItem(),
		   grandTotal
	   );
	   completedBookings.add(record);
	   
	   for (RoomBooking rb : roomBookings) {
		   occupiedRooms.add(rb.roomNumber);
	   }
	   
	   JOptionPane.showMessageDialog(this, 
			   "Booking Successful!\nReference: " + ref + "\nTotal Rooms: " + roomBookings.size(),
			   "Success", JOptionPane.INFORMATION_MESSAGE);
	   
	   roomBookings = new ArrayList<>();
   }

    private void clearFields() {
        nameField.setText(""); 
        emailField.setText(""); 
        phoneField.setText(""); 
        nightsField.setText("");
        roomTypeCombo.setSelectedIndex(0); 
        guestsCombo.setSelectedIndex(0);
        servicesCombo.setSelectedIndex(0); 
        paymentCombo.setSelectedIndex(0);
        summaryArea.setText(""); 
        totalLabel.setText("TOTAL: ₱0.00");
        updateRoomNumbers();
        roomBookings.clear();
    }
    public static void main (String [] args) {
    	new BookingForm();
    }
}