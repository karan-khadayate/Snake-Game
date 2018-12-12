import java.awt.Dimension;
import javax.swing.*;
import java.awt.Label;

public class Play extends JFrame
{
    Snake s;
    String name1;
    Label scoreLabel;
    public Play(String name)
    {
        name1=name;
        initComponents();
        s.setFocusable(true);
        setVisible(true);
        s.startGame(scoreLabel,name);
    }
    public void initComponents()
    {
        s=new Snake();
        setLayout(null);
        setMinimumSize(new Dimension(435,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreLabel=new Label();
        scoreLabel.setBounds(10,420,200,20);
        add(scoreLabel);
        s.setBounds(10,10,400,400);
        add(s);
        pack();
    }
}