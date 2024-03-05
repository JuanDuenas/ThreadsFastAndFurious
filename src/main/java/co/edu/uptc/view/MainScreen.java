package co.edu.uptc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainScreen extends JFrame {
    JPanel pnlBackground;
    JPanel pnlWhiteBG;
    JLabel lblhead;
    JLabel lblInfo;
    JLabel lblnames;
    JButton btnAbout;

    public MainScreen() {
        setSize(400, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Rápidos y Furiosos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        pnlBackground = new JPanel();

        createHead();
        createPanel();
        createLabel();
        createButton();


        setVisible(true);
    }

    private void createPanel() {
        pnlBackground.setBackground(Color.black);
        pnlBackground.setBounds(0,150,400,450);
        pnlBackground.setLayout(null);
        add(pnlBackground);

        pnlWhiteBG = new JPanel();
        pnlWhiteBG.setBackground(Color.white);
        pnlWhiteBG.setBounds(0,120,400,200);
        pnlWhiteBG.setLayout(null);
        pnlBackground.add(pnlWhiteBG);
    }
    private void createLabel() {
        lblnames = new JLabel("<html>" +"CREADORES: <br>Julian Andres Velandia <br>Juan Pablo Dueñas B. "+ "</html>");
        lblnames.setBounds(25,0,250,120);
        lblnames.setForeground(Color.white);
        lblnames.setFont(new Font("SansSerif", Font.BOLD, 16));
        pnlBackground.add(lblnames);

        lblInfo = new JLabel("<html>Los lanzamientos de dados se dan en rangos de 2 a 6 segundos, " +
                "<br>en base a esto los autos se moveran en el circuíto. " +
                "<br>Gana el primer jugador en superar los 32 puntos.</html>");
        lblInfo.setBounds(30,10,250,200);
        lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pnlWhiteBG.add(lblInfo);

    }

    private void createButton() {
        btnAbout = new JButton("Salir");
        btnAbout.setBounds(120,350,140,40);
        btnAbout.setBackground(Color.white);
        btnAbout.setForeground(Color.BLACK);
        btnAbout.setFont(new Font("SansSerif", Font.BOLD, 14));
        pnlBackground.add(btnAbout);
        btnAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private void createHead(){
        try {
            Image raceImage = ImageIO.read(new File("Image/RaceHead.png"));
            ImageIcon raceIcon = new ImageIcon(raceImage);
            lblhead = new JLabel(raceIcon);
            lblhead.setBounds(-10, 0, 400, 150);
            add(lblhead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
