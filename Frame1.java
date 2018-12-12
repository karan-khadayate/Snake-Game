import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame1 extends JFrame implements ActionListener
{
    JButton jButton1,jButton2;
    JLabel jLabel1;
    public static void main(String args[])
    {
        new Frame1();
    }
    public Frame1()
    {
        initComponents();
        setVisible(true);
        setFocusable(true);
    }
    public void initComponents()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jButton1=new JButton("Start");
        jButton2=new JButton("High Scores.");
        jLabel1=new JLabel();
        jLabel1.setFont(new Font("Showcard Gothic",0,36));
        jLabel1.setText("     NAGOBA");
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        setLayout(null);
        setMinimumSize(new Dimension(400,400));
        jLabel1.setBounds(100,50,200,50);
        jButton1.setBounds(100,150,200,50);
        jButton2.setBounds(100,250,200,50);
        add(jLabel1);
        add(jButton1);
        add(jButton2);
        pack();
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==jButton1)
        {
            new LoginPage();
        }
        if(ae.getSource()==jButton2)
        {
            new High();
        }
    }
}