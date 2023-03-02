package com.company;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;


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
    JPanel difficultyPanel = new JPanel();
    ButtonGroup difficultyButtonGroup = new ButtonGroup();
    JRadioButton easiestRadioButton = new JRadioButton();
    JRadioButton easyRadioButton = new JRadioButton();
    JRadioButton hardRadioButton = new JRadioButton();
    JRadioButton hardestRadioButton = new JRadioButton();
    JPanel buttonsPanel = new JPanel();
    JButton startStopButton = new JButton();
    JButton exitButton = new JButton();
    ImageIcon[] photos = new ImageIcon[10];
    ImageIcon cover = new ImageIcon();
    int[] photoIndex = new int[20];
    int photosRemaining;
    int[] score = new int[2];
    boolean[] photosFound = new boolean[20];
    int playerNumber, choiceNumber;
    int[] choice = new int[2];
    boolean canClick = false;
    boolean gameOver;
    AudioClip correctSound;
    AudioClip gameOverSound;
    AudioClip loserSound;
    int labelSelected;



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
        playWhoPanel.setBorder(BorderFactory.createTitledBorder("Play Who?"));
        playWhoPanel.setLayout(new GridBagLayout());
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


        difficultyPanel.setPreferredSize(new Dimension(160, 125));
        difficultyPanel.setBackground(Color.LIGHT_GRAY);
        difficultyPanel.setBorder(BorderFactory.createTitledBorder("Difficulty?"));
        difficultyPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5,10,5,10);
        getContentPane().add(difficultyPanel, gridBagConstraints);

        easiestRadioButton.setText("Easiest");
        easiestRadioButton.setBackground(Color.gray);
        easiestRadioButton.setSelected(true);
        difficultyButtonGroup.add(easiestRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(easiestRadioButton, gridBagConstraints);
        easiestRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easiestRadioButtonAction(e);
            }
        });

        easyRadioButton.setText("Easy");
        easyRadioButton.setBackground(Color.gray);
        difficultyPanel.add(easyRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(easyRadioButton, gridBagConstraints);
        easyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyRadioButtonAction(e);
            }
        });

        hardRadioButton.setText("Hard");
        hardRadioButton.setBackground(Color.gray);
        difficultyPanel.add(hardRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(hardRadioButton, gridBagConstraints);
        hardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hardRadioButtonAction(e);
            }
        });

        hardestRadioButton.setText("Hardest");
        hardestRadioButton.setBackground(Color.gray);
        difficultyPanel.add(hardestRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(hardestRadioButton, gridBagConstraints);
        hardestRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hardestRadioButtonAction(e);
            }
        });


        buttonsPanel.setPreferredSize(new Dimension(160,70));
        buttonsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        getContentPane().add(buttonsPanel, gridBagConstraints);

        startStopButton.setText("Start Game");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(startStopButton, gridBagConstraints);
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopButtonAction(e);
            }
        });

        exitButton.setText("Exit Game");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10,0,0,0);
        buttonsPanel.add(exitButton, gridBagConstraints);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonAction(e);
            }
        });






        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int)(0.5 * (screenSize.width - getWidth())), (int)(0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());

        photos[0] = new ImageIcon("./src/com/company/Images/Breach.jpg");
        photos[1] = new ImageIcon("./src/com/company/Images/Chamber.jpg");
        photos[2] = new ImageIcon("./src/com/company/Images/Neon.jpg");
        photos[3] = new ImageIcon("./src/com/company/Images/Omen.jpg");
        photos[4] = new ImageIcon("./src/com/company/Images/Pheonix.jpg");
        photos[5] = new ImageIcon("./src/com/company/Images/Raze.jpg");
        photos[6] = new ImageIcon("./src/com/company/Images/Sage.jpg");
        photos[7] = new ImageIcon("./src/com/company/Images/Sova.jpg");
        photos[8] = new ImageIcon("./src/com/company/Images/Viper.jpg");
        photos[9] = new ImageIcon("./src/com/company/Images/Yoru.jpg");
        cover = new ImageIcon("./src/com/company/Images/DOOM.jpg");

        for(int i = 0; i < 20; i++)
        {
            photoLabel[i].setIcon(cover);
        }
        setPlayerWhoButton(false);
        setDifficultyByButtons(false);

        try
        {
            correctSound = Applet.newAudioClip(new URL("file:" + "./src/com/company/Sounds/Correct.wav"));
            loserSound = Applet.newAudioClip(new URL("file:" + "./src/com/company/Sounds/Loser.wav"));
            gameOverSound = Applet.newAudioClip(new URL("file:" + "./src/com/company/Sounds/GameOver.wav"));
        }
        catch(Exception e)
        {
            System.out.println("Sound could not load");
        }



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
        setPlayerWhoButton(false);
        setDifficultyByButtons(false);
        player2Label.setText("Player 2");
        player1Label.setText("Player 1");
    }

    public void oneplayerRadioButtonAction(ActionEvent actionEvent)
    {
      setPlayerWhoButton(true);
      player1Label.setText("You");
      if(playAloneRadioButton.isSelected())
      {
          player2Label.setText("Guesses");
          setDifficultyByButtons(false);
      }
      else
      {
          player2Label.setText("Computer");
          setDifficultyByButtons(true);
      }
    }

    public void playAloneRadioButtonAction(ActionEvent actionEvent)
    {
        setDifficultyByButtons(false);
        player2Label.setText("Guesses");
    }

    public void playComputerRadioButtonAction(ActionEvent actionEvent)
    {
        player2Label.setText("Computer");
        setDifficultyByButtons(true);
    }

    public void easiestRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void easyRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void hardRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void hardestRadioButtonAction(ActionEvent actionEvent)
    {

    }

    public void startStopButtonAction(ActionEvent actionEvent)
    {
        if(startStopButton.getText().equals("Start Game"))
        {
            startStopButton.setText("Stop Game");
            score[0] = 0;
            score[1] = 0;
            scoreTextField[0].setText("0");
            scoreTextField[1].setText("0");
            photosRemaining = 20;
            photoIndex = integerShuffling(20);
            for(int i = 0; i < 20; i++)
            {
                if(photoIndex[i] > 9)
                {
                    photoIndex[i] -= 10;
                }
                photosFound[i] = false;
                photoLabel[i].setIcon(cover);
            }
            playerNumber = 1;
            choiceNumber = 1;
            if(twoplayerRadioButton.isSelected())
            {
                messageLabel.setText("Player 1 pick a box");
            }
            else
            {
                messageLabel.setText("Pick a box");
            }
            setPlayerWhoButton(false);
            setNumberPlayersButton(false);
            setDifficultyByButtons(false);
            exitButton.setEnabled(false);
            canClick = true;
            gameOver = false;
        }
        else
        {
            //stop game
            startStopButton.setText("Start Game");
            setNumberPlayersButton(true);
            if(oneplayerRadioButton.isSelected())
            {
                setPlayerWhoButton(true);
                if(playComputerRadioButton.isSelected())
                {
                    setDifficultyByButtons(true);
                }
            }
            exitButton.setEnabled(true);
            canClick = false;
            if(!gameOver)
            {
                messageLabel.setText("Game Stopped");
            }
        }
    }

    public void exitButtonAction(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    public void setPlayerButtons(boolean a)
    {
        oneplayerRadioButton.setEnabled(a);
        twoplayerRadioButton.setEnabled(a);

    }

    public void setPlayerWhoButton(boolean b)
    {
        playAloneRadioButton.setEnabled(b);
        playComputerRadioButton.setEnabled(b);

    }

    public void setDifficultyByButtons(boolean c)
    {
        easyRadioButton.setEnabled(c);
        easiestRadioButton.setEnabled(c);
        hardestRadioButton.setEnabled(c);
        hardRadioButton.setEnabled(c);
    }

    public int[] integerShuffling(int n)
    {
        int[] integers = new int[n];
        int temps, s;
        Random sortRandom = new Random();
        for(int i = 0; i < n; i++)
        {
            integers[i] = i;
        }
        for(int i = n; i >= 1; i--)
        {
            s = sortRandom.nextInt(i);
            temps = integers[s];
            integers[s] = integers[i-1];
            integers[i-1] = temps;
        }
        return integers;
    }

    public void setNumberPlayersButton(boolean a)
    {
        oneplayerRadioButton.setEnabled(a);
        twoplayerRadioButton.setEnabled(a);
    }

    public void showSelectedLabel() {
        photoLabel[labelSelected].setIcon(photos[photoIndex[labelSelected]]);
        photosFound[labelSelected] = true;
        if (choiceNumber == 1) {
            choice[0] = labelSelected;
            choiceNumber = 2;
            if (twoplayerRadioButton.isSelected()) {
                messageLabel.setText("Player " + String.valueOf(playerNumber) + "pick another");
                canClick = true;

            } else {

            }
        } else {
            choice[1] = labelSelected;
            choiceNumber = 1;
            if (photoIndex[choice[0]] == photoIndex[choice[1]]) {
                correctSound.play();
                photoLabel[choice[0]].setIcon(null);
                photoLabel[choice[1]].setIcon(null);
                score[playerNumber - 1]++;
                scoreTextField[playerNumber - 1].setText(String.valueOf(score[playerNumber - 1]));
                photosRemaining -= 2;
                if (photosRemaining == 0) {
                    gameOver = true;
                    gameOverSound.play();
                }

                if (twoplayerRadioButton.isSelected()) {
                    if (score[0] > score[1]) {
                        messageLabel.setText("Player 1 Wins!");
                    } else if (score[1] > score[0]) {
                        messageLabel.setText("Player 1 wins");
                    } else {
                        messageLabel.setText("It is  at tie");
                    }
                }
            }
            else
            {

            }
            startStopButton.doClick();
            return;
        }
        if(twoplayerRadioButton.isSelected())
        {
            messageLabel.setText("Player" + String.valueOf(playerNumber) + "Pick again");
            canClick = true;
        }
        else
        {

        }
        else {
            loserSound.play();
            photosFound[choice[0]] = false;
            photosFound[choice[1]] = false;
            photoLabel[choice[0]].setIcon(cover);
            photoLabel[choice[1]].setIcon(cover);
            if(twoplayerRadioButton.isSelected())
            {
                if(playerNumber == 1)
                {
                    playerNumber = 2;
                }
                else
                {
                    playerNumber = 1;
                }
                messageLabel.setText("Player " + String.valueOf(playerNumber) + "Pick a box");
                canClick = true;
            }
            else
            {

            }
        }
    }

}
