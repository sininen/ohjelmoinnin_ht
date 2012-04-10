package gui;

/**
 * Tämä luokka on graafinen käyttöliittymä Mastermind-pelille.
 * Käyttöliittymä käyttää luokkia OikeaRivi, PelaajanRivi ja Laskuri.
 */

import sovelluslogiikka.Laskuri;
import sovelluslogiikka.OikeaRivi;
import sovelluslogiikka.PelaajanRivi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Mastermind extends JFrame {
    
    /**
     * ilmentymä luokasta OikeaRivi, ohjelman arpoma oikea rivi
     */
    private OikeaRivi arvottuRivi;
    /**
     * ilmentymä luokasta PelaajanRivi, pelaajan arvaama rivi
     */
    private PelaajanRivi pelaajanRivi;
    /**
     * ilmentymä luokasta Laskuri, kierrosLaskuri kierrosten laskemiseen
     */
    private Laskuri kierrosLaskuri;
    
    /**
     * Taulukko (matriisi) pelaajan arvaamia rivejä.
     */
    private JTextField[][] pelaajanArvaukset;
    /**
     * Taulukko arvotun rivin eli oikean rivin näyttämiseen.
     */
    private JTextField[] oikeaRivi;
    /**
     * Taulukko, joka kertoo jokaisesta pelaajan rivistä montako meni oikein 
     * oikealle ja väärälle paikalle.
     */
    private JTextField[] montakoOikein;
    /**
     * Nappula, jota painamalla saa näkyville ohjeen.
     */
    private JButton ohje;
    /**
     * Nappulasta uusi peli saa aloitettua uuden pelin.
     */
    private JButton uusiPeli;
    /**
     * Nappula lisää sinisen värin pelaajan riviin.
     */
    private JButton sininen;
    /**
     * Nappula lisää punaisen värin pelaajan riviin.
     */
    private JButton punainen;
    /**
     * Nappula lisää pinkin värin pelaajan riviin.
     */
    private JButton pinkki;
    /**
     * Nappula lisää liilan värin pelaajan riviin.
     */
    private JButton liila;
    /**
     * Nappula lisää keltaisen värin pelajan riviin.
     */
    private JButton keltainen;
    /**
     * Nappula lisää vihreän värin pelaajan riviin.
     */
    private JButton vihrea;
    
    /**
     * Konstruktorissa luodaan attribuuteille ilmentymät ja asetetaan
     * käyttöliittymäoliot alkutilaansa.
     * Tehdään paneeleja, joiden avulla eri kentät saadaan asemoitua ja asetetaan
     * käyttöliittymäelementit näkymään lay-outissa. Asetetaan paneeleille kiinteät
     * koot. Lisäksi luodaan nappuloille tapahtumankuuntelijat.
     */

    public Mastermind() {
        arvottuRivi = new OikeaRivi();
        pelaajanRivi = new PelaajanRivi();
        kierrosLaskuri = new Laskuri();

        pelaajanArvaukset = new JTextField[8][4];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                pelaajanArvaukset[i][j] = new JTextField();
                pelaajanArvaukset[i][j].setEditable(false);
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
                arvaukset.add(pelaajanArvaukset[a][b]);
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
        
        asetaTaustavariJaTyhjennaKentat();

        sininen.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(1);
                        int rivi = kierrosLaskuri.monesko();
                        pelaajanArvaukset[7 - rivi][sarake].setBackground(Color.BLUE);

                        toimi(sarake);

                    }
                });

        punainen.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(2);
                        int rivi = kierrosLaskuri.monesko();
                        pelaajanArvaukset[7 - rivi][sarake].setBackground(Color.RED);

                        toimi(sarake);

                    }
                });

        pinkki.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(3);
                        int rivi = kierrosLaskuri.monesko();
                        pelaajanArvaukset[7 - rivi][sarake].setBackground(Color.PINK);

                        toimi(sarake);
                    }
                });

        liila.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(4);
                        int rivi = kierrosLaskuri.monesko();
                        pelaajanArvaukset[7 - rivi][sarake].setBackground(Color.MAGENTA);

                        toimi(sarake);
                    }
                });

        keltainen.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(5);
                        int rivi = kierrosLaskuri.monesko();
                        pelaajanArvaukset[7 - rivi][sarake].setBackground(Color.YELLOW);

                        toimi(sarake);
                    }
                });

        vihrea.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake = pelaajanRivi.lisaaVari(6);
                        int rivi = kierrosLaskuri.monesko();
                        pelaajanArvaukset[7 - rivi][sarake].setBackground(Color.GREEN);

                        toimi(sarake);
                    }
                });

        ohje.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        String viesti="Mastermind-pelissä on tarkoitus arvailla koneen arpomaa eri \n"
                                + "väreistä koostuvaa riviä. Rivi on neljän värin mittainen ja \n"
                                + "mahdollisia värivaihtoehtoja on kuusi. Arvaa tai päättele värejä \n"
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
                        arvottuRivi = new OikeaRivi();
                        pelaajanRivi = new PelaajanRivi();
                        kierrosLaskuri = new Laskuri();
                        
                        asetaTaustavariJaTyhjennaKentat();
                        nappuloidenToiminta(true);

                    }
                });
        
    }
    
    /**
     * Metodi toimi testaa lisättiinkö edellinen väri pelaajan rivin viimeiseen
     * paikkaan. Jos lisättiin, metodi kutsuu metodeita, jotka tarkistavat oikeilla
     * ja väärillä paikoilla olevien värien määrän, ja tulostaa nämä tiedot. Jos 
     * pelaajan rivi meni oikein tai arvaus oli kahdeksas, metodi kutsuu metodia
     * oikean rivin tulostamiseen ja nappuloiden asettamiseen toimimattomiksi. 
     * Muussa tapauksessa, metodi tyhjentää pelaajan rivin seuraavaa kierrosta 
     * varten ja siirtää kierroslaskuria yhden eteenpäin.
     * 
     * @param sarake kertoo, monenneksi uusi väri lisättiin pelaajan riviin
     */
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
    
    /**
     * Metodi asettaa nappulat joko toimiviksi tai toimimattomiksi.
     * @param totuus true tai false, riippuen halutaanko nappulat toimintakuntoon
     * vai toimimattomiksi
     */
    private void nappuloidenToiminta(boolean totuus) {
        sininen.setEnabled(totuus);
        punainen.setEnabled(totuus);
        pinkki.setEnabled(totuus);
        liila.setEnabled(totuus);
        keltainen.setEnabled(totuus);
        vihrea.setEnabled(totuus);
    }
    
    /**
     * Metodi tulostaa oikean rivin pelaajan nähtäville.
     */
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
    
    /**
     * Metodi asettaa kaikille kentille taustavärin ja tyhjentää kentät. 
     * Eli metodi tyhjentää kaikki kentät väreistä ja teksteistä eli 
     * ikäänkuin nollaa tilanteen. 
     */
    private void asetaTaustavariJaTyhjennaKentat() {
        
        for (int i = 0; i < pelaajanArvaukset.length; i++) 
            for (int j = 0; j < pelaajanArvaukset[0].length; j++) 
                pelaajanArvaukset[i][j].setBackground(Color.getHSBColor(0.36f, 0.1f, 0.9f));
            
        for (int i = 0; i < oikeaRivi.length; i++) 
            oikeaRivi[i].setBackground(Color.getHSBColor(0.36f, 0.1f, 0.9f));
        
        for (int i = 0; i < montakoOikein.length; i++) {
            montakoOikein[i].setText("");
            montakoOikein[i].setBackground(Color.getHSBColor(0.36f, 0.1f, 0.9f));
        }
    }
    
    /**
     * Metodi muodostaa ponnahdusikkunan, jossa voi ilmoittaa pelaajalle asioita.
     * Eli ponnahdusikkunalle annetaan viesti, jonka se sitten ponnauttaa käyttäjälle.
     * 
     * @param viesti Teksti, joka lukee ponnahdusikkunassa.
     * @param otsikko Ponnahdusikkunan otsikko
     */
    private void ponnahdusIkkuna(String viesti, String otsikko) {
        JOptionPane.showMessageDialog(null, viesti, otsikko, JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Pääohjelma, joka luo ilmentymän pelistä ja asettaa ikkunan näkyville. 
     * Lisäksi pääohjelma asettaa ikkunalle koon ja luo ominaisuuden ikkunan
     * sulkemista varten.
     * @param args 
     */

    public static void main(String args[]) {
        Mastermind ikkuna = new Mastermind();
        ikkuna.setTitle("Mastermind");
        ikkuna.pack();
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);
        ikkuna.setSize(550, 500);
    }
    
}
