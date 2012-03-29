/**
 * Luokka Main sisältää pääohjelman, 
 * jolla peliä voi testata tekstiversiona. Tässä versiossa 
 * värit ovat numeroita väliltä 1-6.
 */


import java.util.Scanner;

public class Main {
    private static Scanner lukija= new Scanner(System.in);
    
    public static void main(String[] args) {
        Rivi peli=new Rivi();
        Laskuri kierrosLaskuri=new Laskuri();
        PelaajanRivi pelaajanRivi=new PelaajanRivi();
        
        while(kierrosLaskuri.monesko()<8) {
            
            for(int i=0; i<4; i++) {
                System.out.print("Anna numero väliltä 1-6: ");
                int numero=lukija.nextInt();
                pelaajanRivi.lisaaVari(numero);
            }
            
            System.out.println("Annoit rivin: " + pelaajanRivi.tulostaPelaajanRivi());
            
            int oikeillaPaikoilla=peli.tarkistaOikeatOikeallaPaikalla(pelaajanRivi.annaPelaajanRivi());
            int vaarillaPaikoilla=peli.tarkistaOikeatVaarallaPaikalla(pelaajanRivi.annaPelaajanRivi());
            
            
            if(oikeillaPaikoilla==4) {
                System.out.println("Voitit!");
                break;
            }
            
            System.out.println("Oikeita oikeilla paikoilla: " + oikeillaPaikoilla + 
                    " \nja oikeita väärillä paikoilla: " + vaarillaPaikoilla);
            
            kierrosLaskuri.etene();
            pelaajanRivi.tyhjennaPelaajanRivi();
            
            System.out.println("Oikea rivi: " + peli.tulostaOikeaRivi());
            
        }
        
        System.out.println("Oikea rivi: " + peli.tulostaOikeaRivi());
        
    }
     
    
}