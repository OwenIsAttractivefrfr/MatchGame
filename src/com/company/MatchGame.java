package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MatchGame extends JFrame
{

    JPanel gamePanel = new JPanel();
    JLabel[] photoLabel = new JLabel[20];
    JPanel resultsPanel = new JPanel();
    JLabel player1Label = new JLabel();
    JLabel player2Label = new JLabel();
    JTextField[] scoreTextField = new JTextField[2];
    JLabel messageLabel = new JLabel();
    ButtonGroup playersButtonGroup = new ButtonGroup();
    JRadioButton twoplayerRadioButton = new JRadioButton();
    JRadioButton oneplayerRadioButton = new JRadioButton();
    JPanel playersPanel = new JPanel();
    JPanel playWhoPanel = new JPanel();
    ButtonGroup playWhoButtonGroup = new ButtonGroup();
    JRadioButton playAloneRadioButton = new JRadioButton();
    JRadioButton playComputerRadioButton = new JRadioButton();





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

        resultsPanel.setPreferredSize(new Dimension(160,140));
        resultsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(resultsPanel, gridBagConstraints);


        player1Label.setText("Player 1");
        player1Label.setFont(new Font("Arial", Font.BOLD, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        resultsPanel.add(player1Label, gridBagConstraints);


        scoreTextField[0] = new JTextField();
        scoreTextField[0].setPreferredSize(new Dimension(100,25));
        scoreTextField[0].setText("0");
        scoreTextField[0].setEditable(false);
        scoreTextField[0].setBackground(Color.CYAN);
        scoreTextField[0].setHorizontalAlignment(SwingConstants.CENTER);
        scoreTextField[0].setFont(new Font("Arial", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        resultsPanel.add(scoreTextField[0], gridBagConstraints);


        player2Label.setText("Player2");
        player2Label.setFont(new Font("Arial", Font.BOLD, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5,0,0,0);
        resultsPanel.add(player2Label, gridBagConstraints);


        scoreTextField[1] = new JTextField();
        scoreTextField[1].setPreferredSize(new Dimension(100,25));
        scoreTextField[1].setText("0");
        scoreTextField[1].setEditable(false);
        scoreTextField[1].setBackground(Color.CYAN);
        scoreTextField[1].setHorizontalAlignment((SwingConstants.CENTER));
        scoreTextField[1].setFont(new Font("Arial", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        resultsPanel.add(scoreTextField[1], gridBagConstraints);


        messageLabel.setPreferredSize(new Dimension(160, 40));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(Color.PINK);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setText("");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5,0,0,0);
        resultsPanel.add(messageLabel,gridBagConstraints);

        playersPanel.setPreferredSize(new Dimension(160,75));
        playersPanel.setBackground(Color.lightGray);
        playersPanel.setBorder(BorderFactory.createTitledBorder("Number of players?"));
        playersPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5,10,5,10);
        getContentPane().add(playersPanel, gridBagConstraints);

        twoplayerRadioButton.setText("Two Players");
        twoplayerRadioButton.setBackground(Color.GRAY);
        twoplayerRadioButton.setSelected(true);
        playersButtonGroup.add(twoplayerRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(twoplayerRadioButton, gridBagConstraints);
        twoplayerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoplayerRadioButtonAction(e);
            }
        });

        oneplayerRadioButton.setText("One Player");
        oneplayerRadioButton.setBackground(Color.GRAY);
        playersButtonGroup.add(oneplayerRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(oneplayerRadioButton, gridBagConstraints);
        oneplayerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oneplayerRadioButtonAction(e);
            }
        });


        playWhoPanel.setPreferredSize(new Dimension(160,75));
        playWhoPanel.setBackground(Color.lightGray);
        playersPanel.setBorder(BorderFactory.createTitledBorder("Play Who?"));
        playersPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5,10,5,10);
        getContentPane().add(playWhoPanel, gridBagConstraints);


        playAloneRadioButton.setText("Play Alone");
        playAloneRadioButton.setBackground(Color.gray);
        playAloneRadioButton.setSelected(true);
        playWhoButtonGroup.add(playAloneRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playWhoPanel.add(playAloneRadioButton, gridBagConstraints);
        playAloneRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAloneRadioButtonAction(e);
            }
        });


        playComputerRadioButton.setText("Play Computer");
        playComputerRadioButton.setBackground(Color.gray);
        playWhoButtonGroup.add(playComputerRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playWhoPanel.add(playComputerRadioButton, gridBagConstraints);
        playComputerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playComputerRadioButtonAction(e);
            }
        });


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

    public void twoplayerRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void oneplayerRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void playAloneRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void playComputerRadioButtonAction(ActionEvent actionEvent)
    {

    }
}
