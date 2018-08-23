package hotel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import java.util.regex.Pattern;

public class RoomBooking extends JFrame implements MouseListener,ActionListener,ItemListener{
    JLabel lbl_title,lbl_checkin,lbl_checkout,lbl_npersons,lbl_nrooms,lbl_offers,lbl_carhire,lbl_dinner,lbl_deluxe,lbl_premium,lbl_normal,lbl_car,lbl_bike,lbl_dine,lbl_lun,lbl_fname,lbl_lname,lbl_mobile,lbl_email,lbl_address,lbl_city,lbl_state,lbl_country;;
    JComboBox cmb_indate,cmb_outdate,cmb_inmonth,cmb_outmonth,cmb_inyear,cmb_outyear;
    JTextField txt_npersons,txt_nrooms,txt_fname,txt_lname,txt_mobile,txt_email,txt_city,txt_state,txt_country;
    TextArea txt_address;
    JButton btn_next,btn_back;
    JList lst_prevroom,lst_addroom;
    JPanel jp1,jp2,j1,j2,j3;
    JScrollPane scr1,scr2;
    String checkin,checkout,npersons,nrooms,inmonth,outmonth,deluxe,premium,normal,fname,lname,mobile,address,email,country,state,city,roomtype,carhire,food;
    DefaultListModel model= new DefaultListModel();
    DefaultListModel model1= new DefaultListModel();
    CheckboxGroup cg_offers,cg_carhire,cg_dinner;
    Checkbox cb_deluxe,cb_premium,cb_normal;
    Checkbox cb_fourwheel,cb_twowheel,cb_nowheel;
    Checkbox cb_lunch,cb_dinner,cb_both,cb_no;
    int total=0,countroom=0;
    RoomBooking(){
        JPanel j1=new JPanel();
        j1.setLayout(null);
        j1.setBackground(new Color(250,235,215));
        JPanel j2=new JPanel();
        j2.setLayout(null);
        j2.setBackground(new Color(250,235,215));
        JPanel j3=new JPanel();
        j3.setLayout(null);
        j3.setBackground(new Color(250,235,215));
        lbl_title=new JLabel("Hotel Paradise");
        lbl_title.setFont(new Font("Arial", Font.BOLD, 45));
        lbl_checkin=new JLabel("Check In: ");
        lbl_checkin.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_checkout=new JLabel("Check Out: ");
        lbl_checkout.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_npersons=new JLabel("No. of People: ");
        lbl_npersons.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_nrooms=new JLabel("No. of Rooms: ");
        lbl_nrooms.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_npersons= new JTextField();
        txt_nrooms= new JTextField();
        btn_next=new JButton("NEXT");
        btn_next.setFont(new Font("Arial", Font.PLAIN, 12));
        btn_next.setBackground(Color.WHITE);
        btn_next.addActionListener(this);
        btn_back=new JButton("BACK");
        btn_back.addActionListener(this);
        btn_back.setFont(new Font("Arial", Font.PLAIN, 14));
        lst_prevroom= new JList();
        lst_addroom=new JList();
        //Converting date into a atring array such that arr[0]=date arr[1]=month arr[2]=year
        String currentday=java.time.LocalDate.now().toString();
        String currentdate[]=currentday.split("-",3);
        //Combo box for checkin and checkout date
        Integer[] date=new Integer[31];
        int i=0,c=1;
        while(i<=30){
            date[i]=c;
            i++;
            c++;
        }
        cmb_indate = new JComboBox(date);
        cmb_indate.setSelectedIndex(Integer.parseInt(currentdate[2])-1);
        cmb_outdate= new JComboBox(date);
        cmb_outdate.setSelectedIndex(Integer.parseInt(currentdate[2])-1);
        String[] month={"Jan","Feb","Mar","April","May","June","July","Aug","Sept","Oct","Nov","Dec"};
        cmb_inmonth = new JComboBox(month);
        cmb_inmonth.setSelectedIndex(Integer.parseInt(currentdate[1])-1);
        cmb_outmonth= new JComboBox(month);
        cmb_outmonth.setSelectedIndex(Integer.parseInt(currentdate[1])-1);
        Integer[] year=new Integer[50];
        int j=0,d=2018;
        while(j<=49){
            year[j]=d;
            d++;
            j++;
        }
        cmb_inyear = new JComboBox(year);
        cmb_inyear.setSelectedItem(Integer.parseInt(currentdate[0]));
        cmb_outyear= new JComboBox(year);
        cmb_inyear.setSelectedItem(Integer.parseInt(currentdate[0])); 
        //Clearing the status for today's checkout
        String url = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;integratedSecurity=true";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            try (Connection con = DriverManager.getConnection(url)) {
                PreparedStatement stat = con.prepareStatement("UPDATE hmtable SET stats=? WHERE checkout=?");
                stat.setString(1,"AVAILABLE");
                Date dat=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate= formatter.format(dat);
                stat.setString(2,strDate);
            }
        }        
        catch(Exception m){
            System.out.println(m.getMessage());
        }
        //Make a listbox of available rooms
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            try (Connection con = DriverManager.getConnection(url)) {
                PreparedStatement stat = con.prepareStatement("SELECT room FROM hmtable WHERE stats=?");
                stat.setString(1,"AVAILABLE");
                ResultSet rs= stat.executeQuery();
                while(rs.next()){
                    model.addElement(rs.getString("room"));
                }
            }
        }        
        catch(Exception m){
            System.out.println(m.getMessage());
        }
        lst_prevroom.setModel(model);
        lst_prevroom.addMouseListener(this);
        lst_prevroom.setPreferredSize(new Dimension(150, 690));
        jp1= new JPanel();
        jp1.setLayout(new FlowLayout());
        scr1= new JScrollPane(lst_prevroom);
        jp1.add(scr1);
        lst_addroom.setModel(model1);
        lst_addroom.setPreferredSize(new Dimension(150, 690));
        jp2= new JPanel();
        jp2.setLayout(new FlowLayout());
        scr2= new JScrollPane(lst_addroom);
        jp2.add(scr2);
        
        //second panel
        lbl_offers=new JLabel("Offers Available: ");
        lbl_offers.setFont(new Font("Arial", Font.BOLD, 16));
        lbl_deluxe=new JLabel();
        lbl_deluxe.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl_premium=new JLabel();
        lbl_premium.setFont(new Font("Arial", Font.PLAIN, 12));
        lbl_normal=new JLabel();
        lbl_normal.setFont(new Font("Arial", Font.PLAIN, 12));
        deluxe="Deluxe:  i)Double Bed  ii)lunch and dinner  iii)Swimming pool     PRICE: Rs 2000/day";
        premium="Premium:  i)Double Bed  ii)Breakfast,lunch and dinner     PRICE: Rs 1500/day";
        normal="Normal:  i)Single Bed     PRICE: Rs 700/day";
        lbl_carhire=new JLabel("Car Hire Available: ");
        lbl_carhire.setFont(new Font("Arial", Font.BOLD, 16));
        lbl_car=new JLabel("i)Car     PRICE: Rs 1000/day");
        lbl_car.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_bike=new JLabel("ii)Bike     PRICE: Rs 400/day");
        lbl_bike.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_dinner=new JLabel("Dinner/Lunch: ");
        lbl_dinner.setFont(new Font("Arial", Font.BOLD, 16));
        lbl_dine=new JLabel("ii)Dinner     PRICE: Rs 300/person");
        lbl_dine.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_lun=new JLabel("ii)Lunch     PRICE: Rs 250/person");
        lbl_lun.setFont(new Font("Arial", Font.PLAIN, 14));
        cg_offers=new CheckboxGroup();
        cb_deluxe=new Checkbox("Deluxe",cg_offers,false);
        cb_premium=new Checkbox("Premium",cg_offers,false);
        cb_normal=new Checkbox("Normal",cg_offers,true);
        cb_deluxe.addItemListener(this);
        cb_premium.addItemListener(this);
        cb_normal.addItemListener(this);
        cg_carhire=new CheckboxGroup();
        cb_fourwheel=new Checkbox("Car",cg_carhire,false);
        cb_twowheel=new Checkbox("Bike/Scooty",cg_carhire,false);
        cb_nowheel=new Checkbox("None",cg_carhire,true);
        cb_fourwheel.addItemListener(this);
        cb_twowheel.addItemListener(this);
        cg_dinner=new CheckboxGroup();
        cb_lunch=new Checkbox("Lunch",cg_dinner,false);
        cb_dinner=new Checkbox("Dinner",cg_dinner,false);
        cb_both=new Checkbox("Both",cg_dinner,false);
        cb_lunch.addItemListener(this);
        cb_dinner.addItemListener(this);
        cb_both.addItemListener(this);
        cb_no=new Checkbox("None",cg_dinner,true);
        
        //third panel
        lbl_fname=new JLabel("First Name: ");
        lbl_fname.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_lname=new JLabel("Last Name: ");
        lbl_lname.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_mobile=new JLabel("Mobile No: ");
        lbl_mobile.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_email=new JLabel("Email ID: ");
        lbl_email.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_address=new JLabel("Address: ");
        lbl_address.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_city=new JLabel("City: ");
        lbl_city.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_state=new JLabel("State: ");
        lbl_state.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_country=new JLabel("Country: ");
        lbl_country.setFont(new Font("Arial", Font.PLAIN, 14));
        txt_address=new TextArea(200,100);
        txt_fname=new  JTextField();
        txt_lname=new  JTextField();
        txt_mobile=new  JTextField();
        txt_email=new  JTextField();
        txt_city=new  JTextField();
        txt_state=new  JTextField();
        txt_country=new  JTextField();
        
        //Setting java frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(null);
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250,235,215));
        
        
        //Adding components to frame
        add(lbl_title);
        lbl_title.setBounds((screenSize.width/3+30),40,550,60);
        
        //first panel
        add(j1);
        j1.setBounds(18,140,400,550);
        j1.add(lbl_checkin);
        lbl_checkin.setBounds(10,30,100,30);
        j1.add(cmb_indate);
        cmb_indate.setBounds(120,30,50,30);
        j1.add(cmb_inmonth);
        cmb_inmonth.setBounds(180,30,80,30);
        j1.add(cmb_inyear);
        cmb_inyear.setBounds(270,30,60,30);
        j1.add(lbl_checkout);
        lbl_checkout.setBounds(10,85,100,30);
        j1.add(cmb_outdate);
        cmb_outdate.setBounds(120,85,50,30);
        j1.add(cmb_outmonth);
        cmb_outmonth.setBounds(180,85,80,30);
        j1.add(cmb_outyear);
        cmb_outyear.setBounds(270,85,60,30);
        j1.add(lbl_npersons);
        lbl_npersons.setBounds(10,145,100,20);
        j1.add(txt_npersons);
        txt_npersons.setBounds(120,140,60,30);
        j1.add(lbl_nrooms);
        lbl_nrooms.setBounds(200,145,100,20);
        j1.add(txt_nrooms);
        txt_nrooms.setBounds(310,140,60,30);
        j1.add(jp1);
        jp1.setBounds(50,190,278,140);
        j1.add(jp2);
        jp2.setBounds(50,350,278,140);
        
        //second panel
        add(j2);
        j2.setBounds(428,140,500,550);
        j2.add(lbl_offers);
        lbl_offers.setBounds(10,30,300,20);
        lbl_deluxe.setText(deluxe);
        j2.add(lbl_deluxe);
        lbl_deluxe.setBounds(20,70,480,20);
        lbl_premium.setText(premium);
        j2.add(lbl_premium);
        lbl_premium.setBounds(20,100,480,20);
        lbl_normal.setText(normal);
        j2.add(lbl_normal);
        lbl_normal.setBounds(20,130,480,20);
        j2.add(cb_deluxe);
        cb_deluxe.setBounds(40,170,80,20);
        j2.add(cb_premium);
        cb_premium.setBounds(140,170,80,20);
        j2.add(cb_normal);
        cb_normal.setBounds(240,170,80,20);
        j2.add(lbl_carhire);
        lbl_carhire.setBounds(10,220,180,20);
        j2.add(lbl_car);
        lbl_car.setBounds(20,260,400,20);
        j2.add(lbl_bike);
        lbl_bike.setBounds(20,300,400,20);
        j2.add(cb_fourwheel);
        cb_fourwheel.setBounds(40,340,80,20);
        j2.add(cb_twowheel);
        cb_twowheel.setBounds(140,340,80,20);
        j2.add(cb_nowheel);
        cb_nowheel.setBounds(240,340,80,20);
        j2.add(lbl_dinner);
        lbl_dinner.setBounds(10,390,200,20);
        j2.add(lbl_lun);
        lbl_lun.setBounds(20,430,400,20);
        j2.add(lbl_dine);
        lbl_dine.setBounds(20,460,400,20);
        j2.add(cb_lunch);
        cb_lunch.setBounds(40,500,80,20);
        j2.add(cb_dinner);
        cb_dinner.setBounds(140,500,60,20);
        j2.add(cb_both);
        cb_both.setBounds(220,500,60,20);
        j2.add(cb_no);
        cb_no.setBounds(300,500,80,20);
       
        //third panel
        add(j3);
        j3.setBounds(938,140,400,550);
        lbl_fname.setBounds(20,30,100,40);
        j3.add(lbl_fname);
        txt_fname.setBounds(130,30,200,30);
        j3.add(txt_fname);
        lbl_lname.setBounds(20,80,100,40);
        j3.add(lbl_lname);
        txt_lname.setBounds(130,80,200,30);
        j3.add(txt_lname);
        lbl_mobile.setBounds(20,130,100,40);
        j3.add(lbl_mobile);
        txt_mobile.setBounds(130,130,200,30);
        j3.add(txt_mobile);
        lbl_email.setBounds(20,180,100,40);
        j3.add(lbl_email);
        txt_email.setBounds(130,180,200,30);
        j3.add(txt_email);
        lbl_address.setBounds(20,230,100,40);
        j3.add(lbl_address);
        txt_address.setBounds(130,230,200,85);
        j3.add(txt_address);
        lbl_city.setBounds(20,335,100,40);
        j3.add(lbl_city);
        txt_city.setBounds(130,335,200,30);
        j3.add(txt_city);
        lbl_state.setBounds(20,385,100,40);
        j3.add(lbl_state);
        txt_state.setBounds(130,385,200,30);
        j3.add(txt_state);
        lbl_country.setBounds(20,435,100,40);
        j3.add(lbl_country);
        txt_country.setBounds(130,435,200,30);
        j3.add(txt_country);
        btn_next.setBounds(150,490,100,40);
        j3.add(btn_next);        
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_next){
            inmonth=String.valueOf(cmb_inmonth.getSelectedIndex());
            if(cmb_inmonth.getSelectedIndex()<10)
                inmonth="0"+String.valueOf(cmb_inmonth.getSelectedIndex());
            outmonth=String.valueOf(cmb_outmonth.getSelectedIndex());
            if(cmb_inmonth.getSelectedIndex()<10)
                outmonth="0"+String.valueOf(cmb_outmonth.getSelectedIndex());
            checkin= String.valueOf(cmb_indate.getSelectedItem())+"/"+inmonth+"/"+String.valueOf(cmb_inyear.getSelectedItem());
            checkout= String.valueOf(cmb_outdate.getSelectedItem())+"/"+outmonth+"/"+String.valueOf(cmb_outyear.getSelectedItem());
            npersons=String.valueOf(txt_npersons.getText());
            nrooms=String.valueOf(txt_nrooms.getText());
            
            if(cb_deluxe.getState()==true){
                roomtype="deluxe";
            }
            if(cb_premium.getState()==true){
                roomtype="premium";
            }
            if(cb_normal.getState()==true){
                roomtype="normal";
            }
            if(cb_fourwheel.getState()==true){
                carhire="Car";
            }
            if(cb_twowheel.getState()==true){
                carhire="Bike";
            }
            if(cb_nowheel.getState()==true){
                carhire="none";
            }
            if(cb_lunch.getState()==true){
                food="lunch";
            }
            if(cb_dinner.getState()==true){
                food="dinner";
            }
            if(cb_both.getState()==true){
                food="lunch&dinner";
            }
            if(cb_no.getState()==true){
                food="no";
            }
            int error=0;
            
            if(txt_fname.getText().toString().equals("") || txt_fname.getText().toString().chars().allMatch(Character::isLetter)==false){
                txt_fname.requestFocus();
                txt_fname.setBorder(BorderFactory.createLineBorder(Color.RED));
                error=1;
            }
            if(txt_lname.getText().toString().equals("") || txt_lname.getText().toString().chars().allMatch(Character::isLetter)==false){
                txt_lname.requestFocus();
                txt_lname.setBorder(BorderFactory.createLineBorder(Color.RED));
                error=1;
            }
            if(txt_mobile.getText().toString().equals("") || txt_mobile.getText().toString().chars().allMatch(c -> c >= 0 || c <= 9)==false){
                txt_mobile.requestFocus();
                txt_mobile.setBorder(BorderFactory.createLineBorder(Color.RED));
                if(txt_mobile.getText().toString().length()!=10 )
                    JOptionPane.showMessageDialog(getContentPane(), "Incorrect Mobile Number!!");
                error=1;
            }
            if(txt_email.getText().toString().equals("")){
                txt_email.setBorder(BorderFactory.createLineBorder(Color.RED));
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                    "[a-zA-Z0-9_+&*-]+)*@" +
                                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                    "A-Z]{2,7}$";
                Pattern pat = Pattern.compile(emailRegex);
                if(pat.matcher(txt_email.getText().toString()).matches()==false){
                    JOptionPane.showMessageDialog(getContentPane(), "Incorrect Email ID!!");
                    error=1;
                }
            }
            if(txt_address.getText().toString().equals("")){
                txt_address.requestFocus();
                error=1;
            }
            if(txt_city.getText().toString().equals("") || txt_city.getText().toString().chars().allMatch(Character::isLetter)==false){
                txt_city.requestFocus();
                txt_city.setBorder(BorderFactory.createLineBorder(Color.RED));
                error=1;
            }
            if(txt_state.getText().toString().equals("") || txt_state.getText().toString().chars().allMatch(Character::isLetter)==false){
                txt_state.requestFocus();
                txt_state.setBorder(BorderFactory.createLineBorder(Color.RED));
                error=1;
            }
            if(txt_country.getText().toString().equals("") || txt_country.getText().toString().chars().allMatch(Character::isLetter)==false){
                txt_country.requestFocus();
                txt_country.setBorder(BorderFactory.createLineBorder(Color.RED));
                error=1;
            }
            if(error==0){
                
                fname=txt_fname.getText();
                lname=txt_lname.getText();
                mobile=txt_mobile.getText();
                email=txt_email.getText();
                address=txt_address.getText();
                city=txt_city.getText();
                state=txt_state.getText();
                country=txt_country.getText();
                
                //Connecting to the database../
                String url = "jdbc:sqlserver://localhost:1433;databaseName=hotelmanagement;integratedSecurity=true"; 
                int size;
                size = Integer.parseInt(txt_nrooms.getText().toString());
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
                    try (Connection con = DriverManager.getConnection(url)) {
                        for(int x=0; x<size; x++){
                           PreparedStatement stat = con.prepareStatement("UPDATE hmtable SET fname=?,lname=?,mobile=?,email=?,street=?,town=?,states=?,country=?,checkin=?,checkout=?,stats=?,roomtype=?,carhire=?,food=?,total=? WHERE room=?");
                           stat.setString(1, fname);
                           stat.setString(2, lname);
                           stat.setString(3, mobile);
                           stat.setString(4, email);
                           stat.setString(5, address);
                           stat.setString(6, city);
                           stat.setString(7, state);
                           stat.setString(8, country);
                           stat.setString(9, checkin);
                           stat.setString(10, checkout);
                           stat.setString(11, "BOOKED");
                           stat.setString(12, roomtype);
                           stat.setString(13, carhire);
                           stat.setString(14, food);
                           stat.setInt(15, total);
                           stat.setString(16, String.valueOf(model1.getElementAt(x)));
                           stat.executeUpdate();
                           System.out.println("dtabase updated");
                           PayandBill pab=new PayandBill(total,mobile,model1);
                           dispose();
                        }
                    }
                }
                catch(Exception m){
                    System.out.println(m.getMessage());
                    
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Please fill in all the fields!!");     
            }        
        }
        if(e.getSource()==btn_back){
            Hotel h= new Hotel();
            dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            if(e.getClickCount()==1 && countroom<Integer.parseInt(txt_nrooms.getText())){
            model1.addElement(lst_prevroom.getSelectedValue());
            lst_addroom.setModel(model1);
            countroom++;
            }
            else{
                JOptionPane.showMessageDialog(getContentPane(), "Room Selection Exceeded!!");
            }
        }
        catch(Exception ae){
            JOptionPane.showMessageDialog(null,"Please enter number of rooms!!"); 
        }
        
    }
    @Override
    public void mousePressed(MouseEvent e) {   
    }
    @Override
    public void mouseReleased(MouseEvent e) {  
    }
    @Override
    public void mouseEntered(MouseEvent e) {   
    }
    @Override
    public void mouseExited(MouseEvent e) {
        
    } 
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource()==cb_deluxe){
            cb_both.setState(true);
            total=total+(2000*Integer.parseInt(txt_nrooms.getText().toString()))+(800*Integer.parseInt(txt_npersons.getText().toString()));
        } 
        if(e.getSource()==cb_premium){
            cb_both.setState(true);
            total=total+(1500*Integer.parseInt(txt_nrooms.getText().toString()))+(800*Integer.parseInt(txt_npersons.getText().toString()));
        }
        if(e.getSource()==cb_normal){
            cb_no.setState(true);
            total=total+(700*Integer.parseInt(txt_nrooms.getText().toString()));
        }
        if(e.getSource()==cb_fourwheel){
            total=total+1000;
        }
        if(e.getSource()==cb_twowheel){
            total=total+400;
        }
        if(e.getSource()==cb_both){
            if(cb_normal.getState()==true){
                total=total+(800*Integer.parseInt(txt_npersons.getText().toString()));
                System.out.println("hello");
            } 
        }
        if(e.getSource()==cb_lunch){
            if(cb_normal.getState()==true){
                total=total+(250*Integer.parseInt(txt_npersons.getText().toString()));
            }
        }
        if(e.getSource()==cb_dinner){
            if(cb_normal.getState()==true){
                total=total+(300*Integer.parseInt(txt_npersons.getText().toString()));
            }
        }
    } 
}
