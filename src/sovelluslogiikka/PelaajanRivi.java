package sovelluslogiikka;


import java.util.ArrayList;

/**
 * Luokka tuntee yhden, kulloinkin käsittelyssä olevan pelaajan rivin eli rivin,
 * johon pelaaja arvaa värejä. Värit käsitellään numeroina väliltä 1-6.
 * Riviin voi siis lisätä numeroita ja rivin voi tyhjentää lopuksi. Luokka 
 * tuntee myös laskuri-olion, joka tietää missä kohdassa rivissä ollaan menossa.
 * @author Sini
 */

public class PelaajanRivi {
    
    /**
     * pelaajan arvailema rivi 
     */
    
    private ArrayList<Integer> pelaajanRivi;
    
    /**
     * laskuri laskemaan missä kohdassa pelaajan rivissä ollaan menossa
     */
    
    private Laskuri laskuri;
    
    /**
     * Konstruktori luo taulukon pelaajan riviä varten ja laskurin numeroiden
     * paikan laskemiseen rivissä.
     */
    
    public PelaajanRivi() {
        pelaajanRivi=new ArrayList<Integer>();
        laskuri=new Laskuri();
    }
    
    /**
     * Metodi lisää värejä eli numeroita 1-6 pelaajan arvaamaan riviin ja
     * palauttaa monenneksi numero lisättiin riviin.
     * 
     * @param variNro pelaajan arvaaman värin vastine numerona
     * @return monenneksi numero lisättiin riviin ja -1, jos ei lisätty
     */
    
    public int lisaaVari(int variNro) {
        if(variNro>=1 && variNro<=6) {
            pelaajanRivi.add(variNro);
            laskuri.etene();
        }
        return laskuri.monesko()-1;
    }
    
    /**
     * Metodi palauttaa pelaajan arvaaman rivin
     * @return pelaajan rivi taulukkona
     */
    
    public ArrayList annaPelaajanRivi() {
        return pelaajanRivi;
    }
    
    /**
     * Metodi palauttaa pelaajan rivin tekstinä.
     * @return pelaajan rivi tekstinä
     */
    
    public String tulostaPelaajanRivi() {
        return pelaajanRivi.toString();
    }
    
    /**
     * Metodi palauttaa pelaajan rivin tämänhetkisen pituuden
     * @return pelaajan rivin pituus
     */
    
    public int pelaajanRivinPituus() {
        return pelaajanRivi.size();
    }
    
    /**
     * Metodi tyhjentää pelaajan rivin ja nollaa laskurin, 
     * jotta käyttäjä voi arvailla seuraavaa riviä.
     */
    
    public void tyhjennaPelaajanRivi() {
        pelaajanRivi.clear();
        laskuri.nollaa();
    }
}
