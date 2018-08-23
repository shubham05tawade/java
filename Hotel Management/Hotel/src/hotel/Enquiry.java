package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Enquiry extends JFrame implements ActionListener{
    
    JLabel lbl_title,lbl_fname;
    JTextField txt_fname;
    TextArea txt_result;
    JButton btn_search,btn_back;
    String checkin,checkout,npersons,nrooms,fname,lname,mobile,address,email,country,state,city,roomtype,carhire,food,status,room;
    int total;
    Enquiry(){
        lbl_title=new JLabel("Hotel Oriental");
        lbl_title.setFont(new Font("Arial", Font.PLAIN, 28));
        lbl_fname=new JLabel("Enter a name: ");
        lbl_fname.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_fname=new JTextField();
        txt_fname.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_result=new TextArea();
        btn_search=new JButton("Search");
        btn_search.setFont(new Font("Arial", Font.PLAIN, 12)); 
        btn_search.addActionListener(this);
        btn_search.setBackground(Color.WHITE);
        btn_back=new JButton("Back");
        btn_back.setFont(new Font("Arial", Font.PLAIN, 12)); 
        btn_back.addActionListener(this);
        btn_back.setBackground(Color.WHITE);
        
        //Clearing the status for today's checkout
        String url = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;integratedSecurity=true";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            try (Connection con = DriverManager.getConnection(url)) {
                PreparedStatement stat = con.prepareStatement("UPDATE hmtable SET stats=?,fname=?,lname=?,mobile=?,email=?,street=?,town=?,states=?,country=?,checkin=?,checkout=?,roomtype=?,carhire=?,food=?,total=? WHERE checkout=?");
                stat.setString(1,"AVAILABLE");
                stat.setString(2,null);
                stat.setString(2,null);
                stat.setString(3,null);
                stat.setString(4,null);
                stat.setString(5,null);
                stat.setString(6,null);
                stat.setString(7,null);
                stat.setString(8,null);
                stat.setString(9,null);
                stat.setString(10,null);
                stat.setString(11,null);
                stat.setString(12,null);
                stat.setString(13,null);
                stat.setString(14,null);
                stat.setString(15,null);
                
                java.util.Date dat=new java.util.Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate= formatter.format(dat);
                stat.setString(16,strDate);
            }
        }        
        catch(ClassNotFoundException | SQLException m){
            System.out.println(m.getMessage());
        }
        
        setLayout(null);
        setSize(1000,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250,235,215));
        
        add(lbl_title);
        lbl_title.setBounds(400,20,250,30);
        add(lbl_fname);
        lbl_fname.setBounds(10,70,100,30);
        add(txt_fname);
        txt_fname.setBounds(130,70,200,30);
        add(btn_search);
        btn_search.setBounds(350,70,100,30);
        add(btn_back);
        btn_back.setBounds(470,70,100,30);
        add(txt_result);
        txt_result.setBounds(10,120,970,500);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_search){
            if(String.valueOf(txt_fname.getText())==null)
                JOptionPane.showMessageDialog(getContentPane(), "Please enter valid name!!");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;integratedSecurity=true";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
                try (Connection con = DriverManager.getConnection(url)) {
                    PreparedStatement stat = con.prepareStatement("SELECT * FROM hmtable WHERE fname=?");
                    stat.setString(1,txt_fname.getText());
                    ResultSet rs=stat.executeQuery();
                    while(rs.next()){
                        fname=rs.getString("fname");
                        lname=rs.getString("lname");
                        mobile=rs.getString("mobile");
                        email=rs.getString("email");
                        address=rs.getString("street");
                        city=rs.getString("town");
                        state=rs.getString("states");
                        country=rs.getString("country");
                        checkin=rs.getString("checkin");
                        checkout=rs.getString("checkout");
                        room=rs.getString("room");
                        status=rs.getString("stats");
                        roomtype=rs.getString("roomtype");
                        carhire=rs.getString("carhire");
                        food=rs.getString("food");
                        total=rs.getInt("total");
                        txt_result.setFont(new Font("Arial", Font.BOLD, 18));
                        txt_result.append("\n\n\n\t\t\t\t\t\t      Hotel Oriental\n\n");
                        txt_result.setFont(new Font("Arial", Font.PLAIN, 14));
                        txt_result.append("\n First Name: "+fname);
                        txt_result.append("\n Last Name: "+lname);
                        txt_result.append("\n Mobile Number: "+mobile);
                        txt_result.append("\n Email-Id: "+email);
                        txt_result.append("\n Street: "+address);
                        txt_result.append("\n Town: "+city);
                        txt_result.append("\n State: "+state);
                        txt_result.append("\n Country: "+country);
                        txt_result.append("\n Rooms: "+room); 
                        txt_result.append("\n CheckOut: "+checkout);
                        txt_result.append("\n Status: "+status);
                        txt_result.append("\n Room Type: "+roomtype);
                        txt_result.append("\n Vehicle Hire: "+carhire);
                        txt_result.append("\n Food Order: "+food);  
                    }
                }
            }        
            catch(ClassNotFoundException | SQLException m){
                System.out.println(m.getMessage());
                JOptionPane.showMessageDialog(getContentPane(), "Customer not found!!");
            }
        }
        if(e.getSource()==btn_back){
            Hotel h=new Hotel();
            dispose();
        }
    }
    
}
