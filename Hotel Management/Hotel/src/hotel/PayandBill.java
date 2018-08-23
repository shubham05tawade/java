package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;

public class PayandBill extends JFrame implements ActionListener{
    JLabel lbl_total;
    JTextField txt_total;
    TextArea txt_bill;
    JButton btn_pay;
    int total;
    String checkin,checkout,npersons,nrooms,fname,lname,mobile,address,email,country,state,city,roomtype,carhire,food,status;
    DefaultListModel model1;
    PayandBill(int total,String mobile,DefaultListModel model1){
        this.total=total;
        this.mobile=mobile;
        this.model1=model1;
        lbl_total=new JLabel("Total Cost: ");
        lbl_total.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_total=new JTextField();
        txt_total.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_total.setText(String.valueOf(total));
        txt_total.setEnabled(false);
        txt_bill=new TextArea();
        btn_pay= new JButton("PAY");
        btn_pay.setFont(new Font("Arial", Font.PLAIN, 12));
        btn_pay.setBackground(Color.WHITE);
        
        setLayout(null);
        setSize(400,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250,235,215));
        
        add(lbl_total);
        lbl_total.setBounds(10,20,100,30);
        add(txt_total);
        txt_total.setBounds(130,20,150,30);
        add(btn_pay);
        btn_pay.setBounds(310,20,60,30);
        add(txt_bill);
        txt_bill.setBounds(10,70,380,400);
        
        btn_pay.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_pay){
            String url = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;integratedSecurity=true";
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
                try (Connection con = DriverManager.getConnection(url)) {
                    PreparedStatement stat = con.prepareStatement("SELECT * FROM hmtable WHERE mobile=?");
                    stat.setString(1,mobile);
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
                        status=rs.getString("stats");
                        roomtype=rs.getString("roomtype");
                        carhire=rs.getString("carhire");
                        food=rs.getString("food");
                        total=rs.getInt("total");
                        txt_bill.setFont(new Font("Arial", Font.BOLD, 18));
                        txt_bill.setText("\n\n\t\t      Hotel Oriental\n\n");
                        txt_bill.setFont(new Font("Arial", Font.PLAIN, 14));
                        txt_bill.append("\n First Name: "+fname);
                        txt_bill.append("\n Last Name: "+lname);
                        txt_bill.append("\n Mobile Number: "+mobile);
                        txt_bill.append("\n Email-Id: "+email);
                        txt_bill.append("\n Street: "+address);
                        txt_bill.append("\n Town: "+city);
                        txt_bill.append("\n State: "+state);
                        txt_bill.append("\n Country: "+country);
                        txt_bill.append("\n Rooms: "); 
                        for(int i=0;i<model1.size();i++)
                            txt_bill.append(model1.getElementAt(i)+" ");
                        txt_bill.append("\n CheckIn: "+checkin);
                        txt_bill.append("\n CheckOut: "+checkout);
                        txt_bill.append("\n Status: "+status);
                        txt_bill.append("\n Room Type: "+roomtype);
                        txt_bill.append("\n Vehicle Hire: "+carhire);
                        txt_bill.append("\n Food Order: "+food);  
                        System.out.println("bill updated");
                        JOptionPane.showMessageDialog(getContentPane(), "Payment Successfull!!");
                        break;
                    }
                }
            }        
            catch(ClassNotFoundException | SQLException m){
                System.out.println(m.getMessage());
            }
        }
    }
}
