package sovelluslogiikka;

import java.util.ArrayList;

/**
 * Luokka OikeaRivi arpoo oikean rivin, jota pelaaja arvailee.
 * Luokassa on metodit oikean rivin tarkistamiseen eli kuinka monta väriä
 * meni oikein oikealle paikalle ja oikein, mutta väärälle paikalle
 * pelaajan rivissä.
 * @author Sini
 */

public class OikeaRivi {
    
    /**
     * ohjelman arpoma oikea rivi
     */
    private ArrayList<Integer> arvottuRivi;
    
    /**
     * Konstruktori luo oikeaa riviä varten taulukon ja kutsuu metodia,
     * joka arpoo oikean rivin.
     */
    public OikeaRivi() {
        arvottuRivi=new ArrayList<Integer>();
        arvoRivi();
    }
    
    /**
     * Metodi arpoo neljä numeroa väliltä 1-6 ja 
     * asettaa nämä numerot oikeaksi riviksi.
     */
    private void arvoRivi() {
        for(int i=0; i<4; i++) {
            int numero=(int) (6*Math.random()) +1;
            arvottuRivi.add(numero);
        }
    }
    
    /**
     * Metodi palauttaa oikean rivin taulukkona.
     * 
     * @return oikea rivi eli ohjelman arpoma rivi taulukkona
     */
    public ArrayList<Integer> annaArvottuRivi() {
        return arvottuRivi;
    }
    
    /**
     * Metodi palauttaa oikean rivin tekstinä.
     * @return oikea rivi tekstinä
     */
    public String tulostaOikeaRivi() {
        return arvottuRivi.toString();
    }
    
    
    /**
     * Metodi tarkistaa pelaajan rivistä montako väriä eli numeroa siinä on 
     * oikein ja oikealla paikalla. Jos parametrina saatava pelaajan rivi on 
     * väärän mittainen, metodi palauttaa -1.
     * 
     * @param pelaajanRivi pelaajan arvaama rivi
     * @return montako numeroa pelaajan rivistä oli oikein ja oikealla paikalla
     */
    public int tarkistaOikeatOikeallaPaikalla(ArrayList<Integer> pelaajanRivi) {
        if(pelaajanRivi.size()==4) {
            int oikeallaPaikalla=0;
            for(int i=0; i<4; i++) 
                if(pelaajanRivi.get(i)==arvottuRivi.get(i))
                    oikeallaPaikalla++;
            return oikeallaPaikalla;
        }
        else 
            return -1;
    }
    
    /**
     * Metodi tarkistaa pelaajan rivistä montako väriä eli numeroa siinä on 
     * oikein mutta väärällä paikalla. Metodi laskee ensin kaikki oikeat värit,
     * eli sekä oikealla että väärällä paikalla olevat. Sen jälkeen metodi kutsuu
     * metodia tarkistaOikeatOikeallaPaikalla ja vähentää kaikista oikeista 
     * oikealla paikalla olevat. Näin saadaan tietää, kuinka monta väreistä
     * on oikein mutta väärällä paikalla. Metodi palauttaa -1, jos parametrina
     * saatava taulukko oli väärän mittainen.
     * 
     * @param pelaajanRivi pelaajan arvaama rivi
     * @return montako numeroa oli oikein mutta väärällä paikalla
     */
    public int tarkistaOikeatVaarallaPaikalla(ArrayList<Integer> pelaajanRivi) {
        if(pelaajanRivi.size()==4) {
            ArrayList<Integer> apuRivi=new ArrayList<Integer>();
            for(int i=0; i<4; i++)
                apuRivi.add(arvottuRivi.get(i));
                
            int oikeita=0;
            for(int i=0; i<4; i++) 
                for(int j=0; j<4; j++) 
                    if(pelaajanRivi.get(i)==apuRivi.get(j)) {
                        oikeita++;
                        apuRivi.set(j, -1);
                        break;
                    }
            
            int oikeitaOikeallaPaikalla=tarkistaOikeatOikeallaPaikalla(pelaajanRivi);
            return oikeita-oikeitaOikeallaPaikalla;
        }
        else 
            return -1;
    }
    
    /**
     * Metodi asettaa syötteenä saamansa rivin oikeaksi riviksi. Metodi on 
     * vain yllä olevien tarkistaOikeatOikealla- ja VaarallaPaikalla testausta
     * varten.
     * 
     * @param oikeaRivi Testaajan asettama oikea eli arvattava rivi.
     */
    public void asetaOikeaksiRiviksi(ArrayList<Integer> oikeaRivi) {
        arvottuRivi=oikeaRivi;
    }
    
    
}
  