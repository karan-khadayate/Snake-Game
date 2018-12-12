import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Random;
import java.sql.*;

public class Snake extends JPanel implements KeyListener
{
    Connection con;
    Statement stmt;
    ResultSet rs;
    String Query;
    int i=1,x=200,y=180,fx,fy;
    int sLength=4,score=0;
    Positions p[]=new Positions[1600];
    char move='d';
    Random r=new Random();
    boolean fcheck=false;
    Play pf;
    Label scoreLabel;
    String name1;
    public void startGame(Label sLabel,String name)
    {  
        name1=name;
        addKeyListener(this);
        scoreLabel=sLabel;
        for(int j=0;j<sLength;j++)
        {
            p[j]=new Positions(x-(j*20),y);
        }
        p[sLength]=new Positions();
        fy=r.nextInt(20)*20;
        fx=r.nextInt(20)*20;
        scoreLabel.setText("Score is "+score);
        fcheck=true;
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.green);
        g.fillRect(0,0,400,400);
        g.setColor(Color.red);
        if(!fcheck)
        {
            fy=r.nextInt(20)*20;
            fx=r.nextInt(20)*20;
            fcheck=true;
        }
        g.fillOval(fx,fy,20,20);
        updatePositions();
        for(int k=0;k<sLength;k++)
        {
            if(k==0)
                g.setColor(Color.blue);
            else
                g.setColor(Color.black);
            g.fillRect(p[k].px,p[k].py,20,20);
        }
        delay();
        if(p[0].px==fx && p[0].py==fy)
        {
            score++;
            p[++sLength]=new Positions();
            fcheck=false;
            scoreLabel.setText("Score is "+score);
        }
        if(!endCheck())
            repaint();
        else
        {
            g.setColor(Color.YELLOW);
            g.fillRect(0,0,400,400);
            g.setColor(Color.black);
            g.drawString("Your score is : "+score, 160, 200);
            JOptionPane.showMessageDialog(null,score+"");
            addScore();
        }
    }
    public void addScore()
    {
        try
            {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/karan?zeroDateTimeBehavior=convertToNull","userk","tiger");
                stmt=con.createStatement();
                Query="UPDATE abc SET score='"+new Integer(score).toString()+"' WHERE Name ='"+name1+"'";
                stmt.execute(Query);
            }
            catch(SQLException se)
            {
                JOptionPane.showMessageDialog(null,se.toString());
            }
    }

    public void updatePositions()
    {
        for(int k=sLength;k>0;k--)
        {
            p[k].px=p[k-1].px;
            p[k].py=p[k-1].py;
        }
        if(move=='d')
        {
            x+=20;
            if(x>380)x=0;
        }
        if(move=='a')
        {
            x-=20;
            if(x<0)x=380;
        }
        if(move=='w')
        {
            y-=20;
            if(y<0)y=380; 
        }
        if(move=='s')
        {
            y+=20;
            if(y>380)y=0;
        }
        p[0].px=x;
        p[0].py=y;
    } 
    public boolean endCheck()
    {
        if(move==' ')
            return true;
        for(int i=1;i<sLength;i++)
        {
            if(p[0].px==p[i].px && p[0].py==p[i].py)
                return true;
        }
        return false;
    }
    public void delay()
    {
        try
        {
            Thread.sleep(90);
        }
        catch(Exception e){}
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int nmove=e.getKeyCode();
        if(nmove==39 && move!='a')move='d';  
        else if(nmove==37 && move!='d')move='a';
        else if(nmove==40 && move!='w')move='s';
        else if(nmove==38 && move!='s')move='w';
        else move=(char)nmove;
    }
    //Other abstract methods(not used)
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
}
