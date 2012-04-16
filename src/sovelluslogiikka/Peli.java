package sovelluslogiikka;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *Luokka Peli käyttää luokkia OikeaRivi, PelaajanRivi ja Laskuri. Luokka kokoaa
 *yhteen Mastermind-pelissä tarvittavan toiminnallisuuden yllä mainituista luokista.
 *Luokassa on siis samoja metodeita kuin yllä mainituissa luokissa ja lisäksi
 *yhteenkokoavia metodeita pelin toiminnallisuuden avuksi. Pelistä toteutettavan käyttö-
 *liittymän ei tarvitse tuntea kuin tämä luokka.
 * @author Sini
 */
public class Peli {
    
    
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
     * Konstruktorissa luodaan ilmentymät OikeaRivi, PelaajanRivi ja Laskuri
     * luokista.
     */
    public Peli() {
        arvottuRivi = new OikeaRivi();
        pelaajanRivi = new PelaajanRivi();
        kierrosLaskuri = new Laskuri();
    }
    
    /**
     * Metodi lisaaVari lisää värejä pelaajan riviin. Metodi käyttää suoraan
     * PelaajanRivi-luokan lisaaVari-metodia.
     * @param variNro pelaajan arvaaman värin vastine numerona
     * @return monenneksi numero lisättiin riviin ja -1, jos ei lisätty
     */
    public int lisaaVari(int variNro) {
        return pelaajanRivi.lisaaVari(variNro);
    }
    
    /**
     * Metodi kertoo millä kierroksella pelissä ollaan menossa. Metodi käyttää
     * Laskurin monesko-metodia.
     * @return monennellako kierroksella pelissä ollaan menossa
     */
    public int moneskoKierros() {
        return kierrosLaskuri.monesko();
    }
    
    /**
     * Metodi palauttaa arvotun eli oikean rivin taulukkona.
     * @return oikea rivi eli ohjelman arpoma rivi taulukkona
     */ 
    public ArrayList<Integer> annaArvottuRivi() {
        return arvottuRivi.annaArvottuRivi();
    }
    
    /**
     * Metodi tarkistaa onko parametrina saatava sarakenumero rivin viimeinen. Jos
     * on, metodi tarkistaa kutsumalla luokan OikeaRivi tarkistusmetodeita, montako 
     * väriä pelaajan rivistä meni oikein oikealle ja väärälle paikalle. Lisäksi
     * metodi tyhjentää pelaajan rivin seuraavaa kierrosta varten ja siirtää 
     * kierroslaskuria yhdellä eteenpäin.
     * Jos sarake ei ollut viimeinen, metodi palauttaa taulukon, jonka ensimmäinen
     * alkio on -1.
     * @param sarake kertoo, monenneksi uusi väri lisättiin pelaajan riviin
     * @return taulukko, jonka ensimmäisessa paikassa on oikeat oikealla paikalla
     * ja toisessa paikassa oikeat väärällä paikalla
     */

    public int[] toimi(int sarake) {
        int[] oikeatJaVaarat = new int[2];
        if (sarake == 3) {
            int oikeillaPaikoilla = arvottuRivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi.annaPelaajanRivi());
            int vaarillaPaikoilla = arvottuRivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi.annaPelaajanRivi());

            oikeatJaVaarat[0] = oikeillaPaikoilla;
            oikeatJaVaarat[1] = vaarillaPaikoilla;

            pelaajanRivi.tyhjennaPelaajanRivi();
            kierrosLaskuri.etene();
        } else {
            oikeatJaVaarat[0] = -1;
        }

        return oikeatJaVaarat;
    }
    
    /**
     * Metodi tarkistaa päättyikö peli eli saiko pelaaja kaikki neljä väriä oikein
     * ja oikealle paikalle tai oliko pelattu kierros kahdeksas. Metodi palauttaa
     * true, jos peli päättyi, muuten false.
     * @param oikeillaPaikoilla montako väriä pelaajan rivistä oli oikein oikealla paikalla
     * @return true tai false riippuen päättyikö peli vai ei
     */
    public boolean paattyikoPeli(int oikeillaPaikoilla) {
        if(oikeillaPaikoilla == 4 || kierrosLaskuri.monesko() == 8) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kertoo ponnahdusikkunoin pelin tuloksen, jos peli päättyi.
     * Eli jos rivissä oli neljä oikein oikealla paikalla, metodi kertoo pelaajan 
     * voittaneen, ja jos pelaajan arvaama rivi oli kahdeksas, metodi kertoo pelaajan
     * hävinneen (ellei rivi ollut oikea).
     * @param oikeillaPaikoilla montako väriä pelaajan rivistä oli oikein oikealla paikalla
     */
    public void tarkistaTulos(int oikeillaPaikoilla) {
        if(oikeillaPaikoilla == 4) {
            ponnahdusIkkuna("Onneksi olkoon! Voitit pelin! : )", "VOITTO");
        }
        else if(kierrosLaskuri.monesko() == 8) {
           ponnahdusIkkuna("Peli päättyi. \nValitettavasti hävisit tällä kertaa : (", "PELI PÄÄTTYI");
        }        
    }
    
    /**
     * Metodi kertoo ponnahdusikkunassa ohjeen pelin pelaamiseen. Ohje koskee osittain
     * vain graafista käyttöliittymää.
     */

    public void tulostaOhje() {
        String viesti = "Mastermind-pelissä on tarkoitus arvailla koneen arpomaa eri \n"
                + "väreistä koostuvaa riviä. Rivi on neljän värin mittainen ja \n"
                + "mahdollisia värivaihtoehtoja on kuusi. Arvaa tai päättele värejä \n"
                + "riviin painelemalla oikeassa reunassa olevia nappuloita. Kun rivissäsi \n"
                + "on neljä väriä, peli ilmoittaa, kuinka monta väreistä meni oikein oikealle \n"
                + "paikalle ja kuinka monta väriä on oikein, mutta väärällä paikalla. Sinulla \n"
                + "on kahdeksan mahdollisuutta arvata oikea rivi. Voitat pelin, mikäli \n"
                + "arvaat oikean rivin viimeistään kahdeksannella arvauksella.";
        
        ponnahdusIkkuna(viesti, "OHJE");
    }
    
    /**
     * Metodi muodostaa ponnahdusikkunan saamastaan viestistä. Ponnahdusikkunan saa
     * suljettua OK- tai raksi-painikkeista.
     * @param viesti Teksti, jonka haluaa näkyvän ponnahdusikkunassa.
     * @param otsikko Ponnahdusikkunalle otsikko
     */

    public void ponnahdusIkkuna(String viesti, String otsikko) {
        JOptionPane.showMessageDialog(null, viesti, otsikko, JOptionPane.PLAIN_MESSAGE);
    }
}
