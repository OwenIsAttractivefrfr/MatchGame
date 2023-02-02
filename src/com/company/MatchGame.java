package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class MatchGame extends JFrame
{

    JPanel gamePanel = new JPanel();
    JLabel[] photoLabel = new JLabel[20];




    public MatchGame()
    {
        setTitle("Match Game");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints;

        gamePanel.setPreferredSize(new Dimension(625,530));
        gamePanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        getContentPane().add(gamePanel, gridBagConstraints);


        for(int i = 0; i < 20; i++)
        {
            photoLabel[i] = new JLabel();
            photoLabel[i].setPreferredSize(new Dimension(150,100));
            photoLabel[i].setOpaque(true);
            photoLabel[i].setBackground(Color.BLUE);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = i%4;
            gridBagConstraints.gridy = i/4;
            gridBagConstraints.insets = new Insets(5,5,0,0);
            if(gridBagConstraints.gridx == 3)
            {
                gridBagConstraints.insets = new Insets(5,5,0,5);
            }
            if(gridBagConstraints.gridy == 4)
            {
                gridBagConstraints.insets = new Insets(5,5,5,0);
            }
            if(gridBagConstraints.gridx == 3 && gridBagConstraints.gridy == 4)
            {
                gridBagConstraints.insets = new Insets(5,5,5,5);
            }
            gamePanel.add(photoLabel[i], gridBagConstraints);
            photoLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    photoLabelMousePressed(e);
                }
            });
        }


        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int)(0.5 * (screenSize.width - getWidth())), (int)(0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
    }


    public void exitForm(WindowEvent windowEvent)
    {
        System.exit(0);
    }

    public void photoLabelMousePressed(MouseEvent mouseEvent)
    {

    }

}
