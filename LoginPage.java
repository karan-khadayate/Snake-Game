
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener
{
    JLabel nL;
    JTextField nT;
    JButton rB;
    Connection con;
    Statement stmt;
    ResultSet rs;
    String Query;
    String name,hname;
    public static void main(String args[])
    {
        new LoginPage();
    }
    public LoginPage()
    {
        initComponents();
        rB.addActionListener(this);
        setFocusable(true);
        setVisible(true);
    }
    public void initComponents()
    {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        nL=new JLabel("Enter your Name");
        nL.setFont(new Font("Comic Sans MS",0,15));
        nT=new JTextField();
        rB=new JButton("Click to Play");
        setMinimumSize(new Dimension(500,400));
        nL.setBounds(80,70,130,70);
        nT.setBounds(290,70,130,70);
        rB.setBounds(80,210,340,70);
        add(nL);
        add(nT);
        add(rB);
        pack();
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==rB)
        {
            name=nT.getText();
            try
            {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/karan?zeroDateTimeBehavior=convertToNull","userk","tiger");
                stmt=con.createStatement();
                Query="INSERT INTO abc VALUES('"+name+"','0')";
                stmt.execute(Query);
                new Play(name);
            }
            catch(SQLException se)
            {
                JOptionPane.showMessageDialog(null,se.toString());
            }
        }
    }
}