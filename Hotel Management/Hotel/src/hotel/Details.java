package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Details extends JFrame implements ActionListener{
    int i=0;
    JLabel lbl_title;
    JButton btn_back;
    Details(){
    lbl_title=new JLabel("Hotel Oriental");
    lbl_title.setFont(new Font("Arial", Font.PLAIN, 28));
    btn_back=new JButton("Back");
    btn_back.setBackground(Color.WHITE);
    btn_back.setFont(new Font("Arial", Font.PLAIN, 12)); 
    btn_back.addActionListener(this);
    String data[][]=new String[50][50];
    String url = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;integratedSecurity=true";
    //Clearing the status for today's checkout
        
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
    try {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
    try (Connection con = DriverManager.getConnection(url)) {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM hmtable");
        ResultSet rs=stat.executeQuery();
        while(rs.next()){
            data[i][0]=rs.getString("room");
            data[i][1]=rs.getString("fname");
            data[i][2]=rs.getString("lname");
            data[i][3]=rs.getString("mobile");
            data[i][4]=rs.getString("email");
            data[i][5]=rs.getString("street");
            data[i][6]=rs.getString("town");
            data[i][7]=rs.getString("states");
            data[i][8]=rs.getString("country");
            data[i][9]=rs.getString("checkin");
            data[i][10]=rs.getString("checkout");
            data[i][11]=rs.getString("stats");
            i++;
        }
        
        }
    }        
    catch(ClassNotFoundException | SQLException m){
        System.out.println(m.getMessage());
    }
    String column[]=new String[12];
    column[0]="Room No";
    column[1]="fname";
    column[2]="lname";
    column[3]="mobile";
    column[4]="email";
    column[5]="street";
    column[6]="town";
    column[7]="state";
    column[8]="country";
    column[9]="checkin";
    column[10]="checkout";
    column[11]="status";
    
    JTable jt=new JTable(data,column);
    setLayout(null);
    setSize(1010,740);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
    getContentPane().setBackground(new Color(250,235,215));
    add(lbl_title);
    lbl_title.setBounds(400,10,250,20);
    jt.setBounds(10,40,980,600);
    add(jt);
    add(btn_back);
    btn_back.setBounds(470,660,100,30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hotel h=new Hotel();
        dispose();
    }
}
