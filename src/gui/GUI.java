package gui;

/**
 * Tämä luokka on graafinen käyttöliittymä Mastermind-pelille.
 * Käyttöliittymä käyttää luokkaa Mastermind, joka sisältää tarvittavat metodit 
 * sovelluslogiikasta.
 * @author Sini Lehtonen
 */
import sovelluslogiikka.Mastermind;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    
    /**
     * ilmentymä luokasta Mastermind, pelin logiikka
     */
    private Mastermind peli;
    
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
     * Konstruktorissa luodaan olio Mastermind-luokasta ja kutsutaan metodeita, 
     * jotka luovat tekstikentät, nappulat ja paneelit kenttien asemoimiseen. 
     * Kutsutaan metodeita paneelien asemointiin lay-outissa ja asettamaan 
     * paneeleille kiinteät koot. Lisäksi kutsutaan metodeita, jotka asettavat
     * kentille taustavärin ja luovat tapahtumankuuntelijat nappuloille.
     */
    public GUI() {
        peli=new Mastermind();
        luoTekstiKentat();
        luoNappulat();
        luoPaneelitJaAsetaLayOut();
        
        asetaTaustavariJaTyhjennaKentat();
        
        Color vari=Color.BLUE;
        luoKuuntelijaVariNappulalle(sininen, 1, vari);
        vari=Color.RED;
        luoKuuntelijaVariNappulalle(punainen, 2, vari);
        vari=Color.PINK;
        luoKuuntelijaVariNappulalle(pinkki, 3, vari);
        vari=Color.MAGENTA;
        luoKuuntelijaVariNappulalle(liila, 4, vari);
        vari=Color.YELLOW;
        luoKuuntelijaVariNappulalle(keltainen, 5, vari);
        vari=Color.GREEN;
        luoKuuntelijaVariNappulalle(vihrea, 6, vari);
        
        luoKuuntelijaOhjeNappulalle();
        luoKuuntelijaUusiPeliNappulalle(); 
    }
    
    /**
     * Metodi luo ohje-, uusi peli- ja värinappulat ja asettaa niille
     * nimen mukaisen taustavärin.
     */
    private void luoNappulat() {
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
    }
    
    /**
     * Metodi luo tekstikentät pelaajan arvauksille, oikealle riville ja 
     * montako väreistä meni oikein tietojen tulostamiselle. Kaikkiin kenttiin
     * asetetaan kirjoituskielto pelaajalle.
     */
    private void luoTekstiKentat() {
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
    }
    
    /**
     * Metodi kutsuu metodeita, jotka luovat tekstikentistä ja nappuloista
     * paneeleja. Paneelit helpottavat kenttien asettamisa näkyville. Lisäksi
     * metodista kutsutaan metodia paneelien asettamiseen näkyville lay-outiin ja 
     * paneelien kiinteän koon asettamiseen.
     */
    private void luoPaneelitJaAsetaLayOut() {
        JPanel arvaukset=luoArvauksetPaneeli();
        JPanel arvattavaRivi=luoArvattavaRiviPaneeli();
        JPanel merkit=luoMerkitPaneeli();
        JPanel nappulat=luoNappulaPaneeli();
        
        asetaLayOutJaPaneelienKoot(arvaukset, arvattavaRivi, merkit, nappulat);
    }
    
    /**
     * Metodi luo paneelin nappuloita varten ja asettaa nappulat paneeliin.
     * @return paneeli, jossa nappulat ovat
     */
    private JPanel luoNappulaPaneeli() {
        JPanel nappulat = new JPanel(new GridLayout(4, 2));
        nappulat.add(sininen);
        nappulat.add(punainen);
        nappulat.add(pinkki);
        nappulat.add(liila);
        nappulat.add(keltainen);
        nappulat.add(vihrea);
        nappulat.add(ohje);
        nappulat.add(uusiPeli);
        return nappulat;
    }
    
    /**
     * Metodi luo paneelin pelaajan arvauksia varten ja asettaa pelaajan arvaukset
     * matriisin paneeliin.
     * @return pelaajan arvaukset sisältävän paneelin
     */
    private JPanel luoArvauksetPaneeli() {
        JPanel arvaukset = new JPanel(new GridLayout(8, 4));
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 4; b++) {
                arvaukset.add(pelaajanArvaukset[a][b]);
            }
        }
        return arvaukset;
    }
    
    /**
     * Metodi luo oikeaa riviä varten paneelin ja lisää oikean eli arvattavan
     * rivin sisältävät tekstikentät paneeliin.
     * @return paneeli, jossa oikean rivin tekstikentät ovat
     */
    private JPanel luoArvattavaRiviPaneeli() {
        JPanel arvattavaRivi = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            arvattavaRivi.add(oikeaRivi[i]);
        }
        return arvattavaRivi;
    }
    
    /**
     * Metodi luo paneelin tuloksia varten eli tietoja, montako väriä rivistä
     * meni oikein oikealle ja väärälle paikalle ja lisää tulokset sisältävän
     * taulukon paneeliin.
     * @return tulokset sisältävä paneeli
     */
    private JPanel luoMerkitPaneeli() {
        JPanel merkit = new JPanel(new GridLayout(16, 1));
        for (int i = 0; i < 16; i++) {
            merkit.add(montakoOikein[i]);
        }
        return merkit;
    }
    
    /**
     * Metodi asettaa parametreinaan saatavat paneelit näkyviin lay-outissa. 
     * Lisäksi metodi asettaa paneeleille kiinteät koot.
     * @param arvaukset paneeli, joka sisältää matriisin pelaajan arvauksista
     * @param arvattavaRivi paneeli, jossa on oikea rivi
     * @param merkit paneeli, jossa kentät rivin tuloksen ilmoittamiseen
     * @param nappulat paneeli, jossa on kaikki pelin nappulat
     */
    private void asetaLayOutJaPaneelienKoot(JPanel arvaukset, JPanel arvattavaRivi, JPanel merkit, JPanel nappulat) {
        this.setLayout(new BorderLayout());
        this.add("North", arvattavaRivi);
        this.add("Center", merkit);
        this.add("West", arvaukset);
        this.add("East", nappulat);

        arvaukset.setPreferredSize(new Dimension(200, 1));
        merkit.setPreferredSize(new Dimension(200, 10));
        arvattavaRivi.setPreferredSize(new Dimension(150, 45));
        nappulat.setPreferredSize(new Dimension(185, 1));
    }
    
    /**
     * Metodi luo tapahtumankuuntelijan parametrina saamalleen nappulalle. Lisäksi
     * metodi asettaa tapahtumankuuntelijan kutsumaan Mastermind-luokan lisaaVari 
     * metodia, joka lisää värin pelaajan riviin aina nappulaa painettaessa. 
     * Metodi vaihtaa pelaajanArvaukset matriisiin lisättävän värin mukaisen 
     * taustavärin oikealle kohdalle ja kutsuu toimi metodia, joka huolehtii 
     * jonkin nappulan painamisesta aiheutuvasta muusta toiminnasta.
     * 
     * @param nappula Nappula, jolle tapahtumankuuntelija luodaan
     * @param variNro numero, jota nappulan väri vastaa
     * @param vari nappulan väri eli väri, jonka pelaaja haluaa lisätä riviin
     */
    private void luoKuuntelijaVariNappulalle(JButton nappula, final int variNro, final Color vari) {
        nappula.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        int sarake=peli.lisaaVari(variNro);
                        int rivi=peli.moneskoKierros();
                        pelaajanArvaukset[7-rivi][sarake].setBackground(vari);
                        toimi(sarake);
                    }
                });
    }
    
    /**
     * Metodi luo tapahtumankuuntelijan ohje nappulalle. Nappulaa painettaessa
     * metodi ponnauttaa näkyville ohjeen kutsumalla ponnahdusikkuna-metodia
     * parametrinaan ohjeteksti.
     */
    private void luoKuuntelijaOhjeNappulalle() {
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

    }
    
    /**
     * Metodi luo tapahtumankuuntelijan uusi peli-nappulalle. Metodi luo uuden 
     * olion Mastermind-luokasta, tyhjentää kentät ja asettaa nappulat toimintakuntoon.
     */
    private void luoKuuntelijaUusiPeliNappulalle() {
        uusiPeli.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent tapahtuma) {
                        peli=new Mastermind();
                        asetaTaustavariJaTyhjennaKentat();
                        nappuloidenToiminta(true);

                    }
                });
    }
    
    /**
     * Metodi kutsuu Mastermind-luokan toimi-metodia ja saa palautuksena taulukon, jonka
     * ensimmäinen alkio on luku, kuinka monta väriä pelaajan rivissä meni oikein
     * oikealle paikalle ja toinen alkio on luku, kuinka monta väriä meni oikein
     * mutta väärälle paikalle. Metodi asettaa nämä tulokset näkyville montakoOikein
     * -kenttään. Jos taulukon ensimmäinen alkio on -1, lisätty väri
     * ei mennyt pelaajan rivin viimeiselle paikalle. Tällöin tulosta ei ilmoiteta.
     * Lisäksi metodi tarkistaa päättyikö peli kutsumalla Mastermind-luokan paattyikoPeli
     * -metodia. Jos päättyi, metodi kutsuu metodeita oikean rivin tulostamiseen, 
     * nappuloiden asettamiseen toimimattomiksi ja tarkistaTulos
     * -metodia, jonka ponnauttaa pelin lopullisen tuloksen näkyville.
     * 
     * @param sarake kertoo, monenneksi uusi väri lisättiin pelaajan riviin
     */
    private void toimi(int sarake) {
        int[] oikeatJaVaarat=peli.toimi(sarake);
        if(oikeatJaVaarat[0]!=-1) {
            montakoOikein[15 - (2 * peli.moneskoKierros()-1)].setText("Oikeita oikeilla paikoilla: "
                    + oikeatJaVaarat[0]);
            montakoOikein[15 - (2 * peli.moneskoKierros()-2)].setText("Oikeita väärillä paikoilla: "
                    + oikeatJaVaarat[1]);
        }
        if (peli.paattyikoPeli(oikeatJaVaarat[0])) {
                tulostaOikeaRivi();
                nappuloidenToiminta(false);
                tarkistaTulos(oikeatJaVaarat[0]);
        }       
    }
    
    /**
     * Metodi kertoo ponnahdusikkunoin pelin tuloksen, jos peli päättyi.
     * Eli jos rivissä oli neljä oikein oikealla paikalla, metodi kertoo pelaajan 
     * voittaneen, ja jos pelaajan arvaama rivi oli kahdeksas, metodi kertoo pelaajan
     * hävinneen (ellei rivi ollut oikea).
     * @param oikeillaPaikoilla montako väriä pelaajan rivistä oli oikein oikealla paikalla
     */
    private void tarkistaTulos(int oikeillaPaikoilla) {
        if(oikeillaPaikoilla == 4) {
            ponnahdusIkkuna("Onneksi olkoon! Voitit pelin! : )", "VOITTO");
        }
        else if(peli.moneskoKierros() == 8) {
           ponnahdusIkkuna("Peli päättyi. \nValitettavasti hävisit tällä kertaa : (", "PELI PÄÄTTYI");
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
        ArrayList<Integer> oRivi = peli.annaArvottuRivi();
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
     * Metodi muodostaa ponnahdusikkunan saamastaan viestistä. Ponnahdusikkunan saa
     * suljettua OK- tai raksi-painikkeista.
     * 
     * @param viesti Teksti, joka lukee ponnahdusikkunassa.
     * @param otsikko Ponnahdusikkunalle otsikko
     */
    private void ponnahdusIkkuna(String viesti, String otsikko) {
        JOptionPane.showMessageDialog(null, viesti, otsikko, JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Pääohjelma, joka luo ilmentymän GUI-luokasta, eli pelin graafisesta
     * käyttöliittymästä, ja asettaa ikkunan näkyville. 
     * Lisäksi pääohjelma asettaa ikkunalle koon ja luo ominaisuuden ikkunan
     * sulkemista varten.
     * @param args 
     */

    public static void main(String args[]) {
        GUI ikkuna = new GUI();
        ikkuna.setTitle("Mastermind");
        ikkuna.pack();
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);
        ikkuna.setSize(550, 500);
    }
    
}
