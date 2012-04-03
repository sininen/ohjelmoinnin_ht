package gui;

/**
 * Tämä luokka on graafinen käyttöliittymä Mastermind-pelille.
 * Käyttöliittymä käyttää luokkia Rivi, PelaajanRivi ja Laskuri.
 */

import sovelluslogiikka.Laskuri;
import sovelluslogiikka.Rivi;
import sovelluslogiikka.PelaajanRivi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Mastermind extends JFrame {

    private Rivi arvottuRivi;
    private PelaajanRivi pelaajanRivi;
    private Laskuri kierrosLaskuri;
    
    private JTextField[][] matriisi;
    private JTextField[] oikeaRivi;
    private JTextField[] montakoOikein;
    private JButton ohje;
    private JButton uusiPeli;
    private JButton sininen;
    private JButton punainen;
    private JButton pinkki;
    private JButton liila;
    private JButton keltainen;
    private JButton vihrea;

    public Mastermind() {
        arvottuRivi = new Rivi();
        pelaajanRivi = new PelaajanRivi();
        kierrosLaskuri = new Laskuri();

        matriisi = new JTextField[8][4];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                matriisi[i][j] = new JTextField();
                matriisi[i][j].setEditable(false);
            }
        }


        oikeaRivi = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            oikeaRivi[i] = new JTextField();
            oikeaRivi[i].setEditable(false);
        }

        montakoOikein = new JTextField[16];
        for (int i = 0; i < 16; i++) {
            montakoOikein[i] = new JTextField();
            montakoOikein[i].setEditable(false);
        }

        ohje = new JButton(" ohje ");
        ohje.setBackground(Color.getHSBColor(0.36f, 0.2f, 0.9f));
        uusiPeli = new JButton("uusi peli");
        uusiPeli.setBackground(Color.getHSBColor(0.36f, 0.2f, 0.9f));
        sininen = new JButton("sininen");
        sininen.setBackground(Color.BLUE);
        punainen = new JButton("punainen");
        punainen.setBackground(Color.RED);
        pinkki = new JButton("pinkki");
        pinkki.setBackground(Color.PINK);
        liila = new JButton("liila");
        liila.setBackground(Color.MAGENTA);
        keltainen = new JButton("keltainen");
        keltainen.setBackground(Color.YELLOW);
        vihrea = new JButton("vihreä");
        vihrea.setBackground(Color.GREEN);

        JPanel arvaukset = new JPanel(new GridLayout(8, 4));
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 4; b++) {
                arvaukset.add(matriisi[a][b]);
            }
        }

        JPanel arvattavaRivi = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            arvattavaRivi.add(oikeaRivi[i]);
        }

        JPanel merkit = new JPanel(new GridLayout(16, 1));
        for (int i = 0; i < 16; i++) {
            merkit.add(montakoOikein[i]);
        }

        JPanel nappulat = new JPanel(new GridLayout(4, 2));
        nappulat.add(sininen);
        nappulat.add(punainen);
        nappulat.add(pinkki);
        nappulat.add(liila);
        nappulat.add(keltainen);
        nappulat.add(vihrea);
        nappulat.add(ohje);
        nappulat.add(uusiPeli);

        this.setLayout(new BorderLayout());
        this.add("North", arvattavaRivi);
        this.add("Center", merkit);
        this.add("West", arvaukset);
        this.add("East", nappulat);


        arvaukset.setPreferredSize(new Dimension(200, 1));
        merkit.setPreferredSize(new Dimension(200, 10));
        arvattavaRivi.setPreferredSize(new Dimension(150, 45));
        nappulat.setPreferredSize(new Dimension(185, 1));
        
        asetaTaustavari();

        sininen.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(1);
                        int rivi = kierrosLaskuri.monesko();
                        matriisi[7 - rivi][sarake].setBackground(Color.BLUE);

                        toimi(sarake);

                    }
                });

        punainen.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(2);
                        int rivi = kierrosLaskuri.monesko();
                        matriisi[7 - rivi][sarake].setBackground(Color.RED);

                        toimi(sarake);

                    }
                });

        pinkki.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(3);
                        int rivi = kierrosLaskuri.monesko();
                        matriisi[7 - rivi][sarake].setBackground(Color.PINK);

                        toimi(sarake);
                    }
                });

        liila.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(4);
                        int rivi = kierrosLaskuri.monesko();
                        matriisi[7 - rivi][sarake].setBackground(Color.MAGENTA);

                        toimi(sarake);
                    }
                });

        keltainen.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(5);
                        int rivi = kierrosLaskuri.monesko();
                        matriisi[7 - rivi][sarake].setBackground(Color.YELLOW);

                        toimi(sarake);
                    }
                });

        vihrea.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(6);
                        int rivi = kierrosLaskuri.monesko();
                        matriisi[7 - rivi][sarake].setBackground(Color.GREEN);

                        toimi(sarake);
                    }
                });

        ohje.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        String viesti="Mastermind-pelissä on tarkoitus arvailla koneen arpomaa eri \n"
                                + "väreistä koostuvaa riviä. Rivi on neljän värin mittainen ja \n"
                                + "mahdollisia värivaihtoehtoja on kuusi. Arvaa tai päättele nappuloita \n"
                                + "riviin painelemalla oikeassa reunassa olevia nappuloita. Kun rivissäsi \n"
                                + "on neljä väriä, peli ilmoittaa, kuinka monta väreistä meni oikein oikealle \n"
                                + "paikalle ja kuinka monta väriä on oikein, mutta väärällä paikalla. Sinulla \n"
                                + "on kahdeksan mahdollisuutta arvata oikea rivi. Voitat pelin, mikäli \n"
                                + "arvaat oikean rivin viimeistään kahdeksannella arvauksella.";
                        
                        ponnahdusIkkuna(viesti, "OHJE");
                    
                    }
                });

        uusiPeli.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        arvottuRivi = new Rivi();
                        pelaajanRivi = new PelaajanRivi();
                        kierrosLaskuri = new Laskuri();
                        
                        asetaTaustavari();
                        nappuloidenToiminta(true);

                    }
                });
    }
    

    private void toimi(int sarake) {
        if (sarake == 3) {
            int oikeillaPaikoilla = arvottuRivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi.annaPelaajanRivi());
            int vaarillaPaikoilla = arvottuRivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi.annaPelaajanRivi());

            montakoOikein[15 - (2 * kierrosLaskuri.monesko() + 1)].setText("Oikeita oikeilla paikoilla: "
                    + oikeillaPaikoilla);
            montakoOikein[15 - (2 * kierrosLaskuri.monesko())].setText("Oikeita väärillä paikoilla: "
                    + vaarillaPaikoilla);

            if (oikeillaPaikoilla == 4 || kierrosLaskuri.monesko() == 7) {
                tulostaOikeaRivi();
                nappuloidenToiminta(false);
                if(oikeillaPaikoilla == 4)
                    ponnahdusIkkuna("Onneksi olkoon! Voitit pelin! : )", "VOITTO");
                else if(kierrosLaskuri.monesko() == 7)
                    ponnahdusIkkuna("Peli päättyi. \nValitettavasti hävisit tällä kertaa : (", "PELI PÄÄTTYI");
            }

            pelaajanRivi.tyhjennaPelaajanRivi();
            kierrosLaskuri.etene();
        }
    }
    
    private void nappuloidenToiminta(boolean totuus) {
        sininen.setEnabled(totuus);
        punainen.setEnabled(totuus);
        pinkki.setEnabled(totuus);
        liila.setEnabled(totuus);
        keltainen.setEnabled(totuus);
        vihrea.setEnabled(totuus);
    }

    private void tulostaOikeaRivi() {
        ArrayList<Integer> oRivi = arvottuRivi.annaArvottuRivi();
        for (int i = 0; i < 4; i++) {
            int vari = oRivi.get(i);
            if (vari == 1) {
                oikeaRivi[i].setBackground(Color.BLUE);
            } else if (vari == 2) {
                oikeaRivi[i].setBackground(Color.RED);
            } else if (vari == 3) {
                oikeaRivi[i].setBackground(Color.PINK);
            } else if (vari == 4) {
                oikeaRivi[i].setBackground(Color.MAGENTA);
            } else if (vari == 5) {
                oikeaRivi[i].setBackground(Color.YELLOW);
            } else if (vari == 6) {
                oikeaRivi[i].setBackground(Color.GREEN);
            }
        }
    }

    private void asetaTaustavari() {
        
        for (int i = 0; i < matriisi.length; i++) 
            for (int j = 0; j < matriisi[0].length; j++) 
                matriisi[i][j].setBackground(Color.getHSBColor(0.36f, 0.1f, 0.9f));
            
        for (int i = 0; i < oikeaRivi.length; i++) 
            oikeaRivi[i].setBackground(Color.getHSBColor(0.36f, 0.1f, 0.9f));
        
        for (int i = 0; i < montakoOikein.length; i++) {
            montakoOikein[i].setText("");
            montakoOikein[i].setBackground(Color.getHSBColor(0.36f, 0.1f, 0.9f));
        }
    }
    
    private static void ponnahdusIkkuna(String viesti, String otsikko) {
        JOptionPane.showMessageDialog(null, viesti, otsikko, JOptionPane.PLAIN_MESSAGE);
    }
    

    public static void main(String args[]) {
        Mastermind ikkuna = new Mastermind();
        ikkuna.setTitle("Mastermind");
        ikkuna.pack();
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);
        ikkuna.setSize(550, 500);
    }
    
}
