import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame{
	JTextField tfRes;
    JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btn00,btndec,btnadd,btnsub,btnmul,btndiv,btnperc,btnequalto,btnclr,btndel;
    Calculator(){
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btn0 = new JButton("0");
        btn00 = new JButton("00");
        btndec = new JButton(".");
        btnadd = new JButton("+");
        btnsub = new JButton("-");
        btnmul = new JButton("*");
        btndiv = new JButton("/");
        btnperc = new JButton("%");
        btnequalto = new JButton("=");
        btnclr = new JButton("CC");
        btndel = new JButton("C");
        tfRes = new JTextField("0                                          ");

        setLayout(new FlowLayout());
        
        add(tfRes);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
        add(btn9);
        add(btn0);
        add(btn00);
        add(btndec);
        add(btnadd);
        add(btnsub);
        add(btnmul);
        add(btndiv);
        add(btnperc);
        add(btnclr);
        add(btndel);
        add(btnequalto);

        setSize(180,500);
        setVisible(true);
    } 
}
class calci{
    public static void main(String args[]){
        Calculator c = new Calculator();
    }
}