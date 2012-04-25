package sovelluslogiikka;

/**
 * Luokka käsittää hyvin yksinkertaisen laskurin,
 * jolla voi laskea esim. kierrosten lukumäärää 
 * tai rivin kohtaa, jossa ollaan menossa.
 * @author Sini Lehtonen
 */

public class Laskuri {
    
    /**
     * numero, jossa laskuri on menossa
     */
    private int numero;
    
    /**
     * Konstruktori asettaa laskurin nollaan.
     */
    public Laskuri() {
        numero=0;
    }
    
    /**
     * Metodi kertoo, missä kohdassa eli monennessako numerossa
     * laskuri on menossa.
     * 
     * @return numero, jossa laskuri on menossa. 
     */
    public int monesko() {
        return numero;
    }
    
    /**
     * Metodi kasvattaa laskurin arvoa yhdellä eli laskuri etenee yhden
     * askeleen eteenpäin.
     */
    public void etene() {
        numero++;
    }
    
    /**
     * Metodi nollaa laskurin.
     */
    public void nollaa() {
        numero=0;
    }

}
