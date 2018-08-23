package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hotel extends JFrame implements ActionListener{
    
    JLabel lbl_title;
    JButton btn_booking,btn_enquiry,btn_details;
    
    Hotel(){
        lbl_title=new JLabel("Hotel Oriental");
        lbl_title.setFont(new Font("Arial", Font.PLAIN, 28));
        btn_booking= new JButton("Booking Room");
        btn_booking.setBackground(Color.WHITE);
        btn_booking.setFont(new Font("Arial", Font.PLAIN, 12));
        btn_enquiry=new JButton("Personal Enquiry");
        btn_enquiry.setBackground(Color.WHITE);
        btn_enquiry.setFont(new Font("Arial", Font.PLAIN, 12));
        btn_details=new JButton("Room Details");
        btn_details.setBackground(Color.WHITE);
        btn_details.setFont(new Font("Arial", Font.PLAIN, 12));
        btn_booking.addActionListener(this);
        btn_enquiry.addActionListener(this);
        btn_details.addActionListener(this);
        setLayout(null);
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250,235,215));
        add(lbl_title);
        lbl_title.setBounds(110,20,250,60);
        add(btn_booking);
        btn_booking.setBounds(125,110,130,40);
        add(btn_enquiry);
        btn_enquiry.setBounds(125,190,130,40);
        add(btn_details);
        btn_details.setBounds(125,270,130,40);
    }
    
    public static void main(String[] args) {
        new Hotel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_booking){
            new RoomBooking();
            dispose();
        }
        if(e.getSource()==btn_enquiry){
            new Enquiry();
        }
        if(e.getSource()==btn_details){
            new Details();
            dispose();
        }
    }
    
}
