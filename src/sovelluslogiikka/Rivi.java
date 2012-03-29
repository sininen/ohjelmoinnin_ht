package sovelluslogiikka;


import java.util.ArrayList;

/**
 * Luokka Rivi arpoo oikean rivin, jota pelaaja arvailee.
 * Luokassa on metodit oikean rivin tarkistamiseen eli kuinka monta väriä
 * meni oikein oikealle paikalle ja oikein, mutta väärälle paikalle
 * pelaajan rivissä.
 * @author Sini
 */

public class Rivi {
    
    /**
     * ohjelman arpoma oikea rivi
     */
    
    private ArrayList<Integer> arvottuRivi;
    
    /**
     * Konstruktori luo oikeaa riviä varten taulukon ja kutsuu metodia,
     * joka arpoo oikean rivin.
     */
    
    public Rivi() {
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
     * Metodi palauttaa oikean rivin
     * 
     * @return oikea rivi eli ohjelman arpoma rivi taulukkona
     */
    
    public ArrayList annaArvottuRivi() {
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
     * Metodi tarkistaa pelaajan rivistä, montako väriä eli numeroa siinä on 
     * oikein ja oikealla paikalla ja oikein, mutta väärällä paikalla. Metodi
     * laskee ensin kaikki oikeat värit, eli sekä oikealla ja väärällä paikalla
     * olevat ja vähentää tästä luvusta oikealla paikalla olevat oikeat. Näin 
     * saadaan myös oikeat väärällä paikalla.
     * 
     * @param pelaajanRivi pelaajan arvaama rivi
     * @return oikeat oikealla paikalla ja oikeat väärällä paikalla taulukkona
     */
    
    private int[] tarkistaOikeat(ArrayList pelaajanRivi) {
        
        int oikeallaPaikalla=0;
        for(int i=0; i<4; i++) 
            if(pelaajanRivi.get(i)==arvottuRivi.get(i))
                oikeallaPaikalla++;
        
        ArrayList<Integer> apuRivi=new ArrayList<Integer>();
        for(int i=0; i<4; i++) {
            int numero=arvottuRivi.get(i);
            apuRivi.add(numero);
        } 
        
        int oikeita=0;
        for(int i=0; i<4; i++) 
            for(int j=0; j<4; j++) 
                if(pelaajanRivi.get(i)==apuRivi.get(j)) {
                    oikeita++;
                    apuRivi.set(j, -1);
                    break;
                }
        
        int[] merkit=new int[2];
        merkit[0]=oikeallaPaikalla;
        merkit[1]=oikeita-oikeallaPaikalla;
        
        return merkit;
    }
    
    /**
     * Metodi kutsuu metodia tarkistaOikeat ja palauttaa saamastaan taulukosta
     * montako luvuista oli oikein ja oikealla paikalla. Jos parametrina 
     * saatu taulukko oli väärän mittainen metodi ei kutsu tarkistaOikeat-metodia
     * vaan palauttaa -1;
     * 
     * @param pelaajanRivi pelaajan arvaama rivi
     * @return montako numeroa pelaajan rivistä oli oikein ja oikella paikalla
     */
    
    public int tarkistaOikeatOikeallaPaikalla(ArrayList pelaajanRivi) {
        if(pelaajanRivi.size()==4) {
            int[] merkit=tarkistaOikeat(pelaajanRivi);
            return merkit[0];
        }
        else
            return -1;
    }
    
    /**
     * Metodi kutsuu metodia tarkistaOikeat, jos paremetrina saatava taulukko
     * on oikean mittainen. Metodi palauttaa saamastaan taulukosta
     * montako luvuista oli oikein mutta väärällä paikalla, ja -1, jos
     * parametrina saatava taulukko oli väärän mittainen.
     * 
     * @param pelaajanRivi Pelaajan arvaama rivi.
     * @return montako numeroa oli oikein mutta väärällä paikalla
     */
    
    public int tarkistaOikeatVaarallaPaikalla(ArrayList pelaajanRivi) {
        if(pelaajanRivi.size()==4) {
            int[] merkit=tarkistaOikeat(pelaajanRivi);
            return merkit[1];
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
    
    public void asetaOikeaksiRiviksi(ArrayList oikeaRivi) {
        arvottuRivi=oikeaRivi;
    }
    
    
}
  