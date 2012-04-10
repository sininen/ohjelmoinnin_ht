
package sovelluslogiikka;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class OikeaRiviTest {
    
    OikeaRivi rivi;
    
    public OikeaRiviTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        rivi=new OikeaRivi();
    }
    
    @After
    public void tearDown() {
    }
    
    
    
    @Test
    public void arvottuRiviOikeanMittainen() {
        ArrayList arvottuRivi=rivi.annaArvottuRivi();
        assertEquals(4, arvottuRivi.size());
    }
    
    @Test
    public void arvotussaRivissaEnsimmainenNumeroOikeanlainen() {
        ArrayList<Integer> arvottuRivi=rivi.annaArvottuRivi();
        int ensimmainenAlkio=arvottuRivi.get(0);
        assertTrue(ensimmainenAlkio>0 && ensimmainenAlkio<7);  
    }
    
    @Test 
    public void arvotussaRivissaOikeanlaisiaNumeroita() {
        ArrayList<Integer> arvottuRivi=rivi.annaArvottuRivi();
        
        for(int i=0; i<arvottuRivi.size(); i++) {
            int alkio=arvottuRivi.get(i);
            assertTrue(alkio>0 && alkio<7);
        }   
    }
    
    @Test
    public void oikeatOikeallaPaikallaAntaaOikeanlaisenTuloksen() {
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=0; i<4; i++)
            pelaajanRivi.add(1);
        
        int oikeallaPaikalla=rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi);
        assertTrue(oikeallaPaikalla>=0  && oikeallaPaikalla<=4);
    }
    
    @Test
    public void oikeatVaarallaPaikallaAntaaOikeanlaisenTuloksen() {
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=0; i<4; i++)
            pelaajanRivi.add(1);
        
        int vaarallaPaikalla=rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi);
        assertTrue(vaarallaPaikalla>=0  && vaarallaPaikalla<=4);
    }
    
    @Test
    public void oikeallaJaVaarallaPaikallaYhtMaxNeljaAlkiota() {
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=0; i<4; i++)
            pelaajanRivi.add(1);
        
        int oikeallaPaikalla=rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi);
        int vaarallaPaikalla=rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi);
        int yhteensa=oikeallaPaikalla+vaarallaPaikalla;
        
        assertTrue(yhteensa>=0 && yhteensa<=4);
    }
    
    
    @Test
    public void KunRiviOikeinOikeallaJaVaarallaPaikallaToimiiOikein() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        for(int i=0; i<4; i++)
            oikeaRivi.add(1);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=0; i<4; i++)
            pelaajanRivi.add(1);
        
        assertEquals(4, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        assertEquals(0, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
        
    }
    
    @Test
    public void oikeallaJaVaarallaPaikallaToimiiOikeinKunKaksiOikein() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        for(int i=1; i<=4; i++)
            oikeaRivi.add(i);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        pelaajanRivi.add(1);
        pelaajanRivi.add(2);
        pelaajanRivi.add(5);
        pelaajanRivi.add(5);
        
        assertEquals(2, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        assertEquals(0, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
        
    }
    
    @Test
    public void vaarallaJaOikeallaPaikallaToimiiOikeinKunKaikkiVaarillaPaikoilla() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        for(int i=1; i<=4; i++)
            oikeaRivi.add(i);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        pelaajanRivi.add(4);
        pelaajanRivi.add(3);
        pelaajanRivi.add(2);
        pelaajanRivi.add(1);
        
        assertEquals(4, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
        assertEquals(0, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        
    }
    
    @Test
    public void kaksiVaarillaJaKaksiOikeillaPaikoillaOikein() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        for(int i=1; i<=4; i++)
            oikeaRivi.add(i);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        pelaajanRivi.add(2);
        pelaajanRivi.add(1);
        pelaajanRivi.add(3);
        pelaajanRivi.add(4);
        
        assertEquals(2, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        assertEquals(2, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
        
    }
    
    @Test
    public void yksiOikeallaPaikalla() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        oikeaRivi.add(1);
        oikeaRivi.add(1);
        oikeaRivi.add(5);
        oikeaRivi.add(5);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=1; i<=4; i++)
            pelaajanRivi.add(i);
        
        
        assertEquals(1, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        assertEquals(0, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
    }
    
    @Test
    public void yksiOikeillaJaNollaVaarillaPaikoilla() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        oikeaRivi.add(1);
        oikeaRivi.add(6);
        oikeaRivi.add(3);
        oikeaRivi.add(3);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=1; i<=4; i++)
            pelaajanRivi.add(1);
        
        
        assertEquals(1, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        assertEquals(0, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
    }
    
    @Test
    public void tarkistusPalauttaaMiinusYksiJosAnnettuRiviVaaranMittainen() {
        ArrayList<Integer> pelaajanRivi=new ArrayList<Integer>();
        for(int i=1; i<=6; i++)
            pelaajanRivi.add(i);
        
        assertEquals(-1, rivi.tarkistaOikeatOikeallaPaikalla(pelaajanRivi));
        assertEquals(-1, rivi.tarkistaOikeatVaarallaPaikalla(pelaajanRivi));
    }
    
    
    
    @Test
    public void oikeanRivinTulostusToimiiOikein() {
        ArrayList<Integer> oikeaRivi=new ArrayList<Integer>();
        for(int i=1; i<=4; i++)
            oikeaRivi.add(i);
        
        rivi.asetaOikeaksiRiviksi(oikeaRivi);
        
        assertEquals("[1, 2, 3, 4]", rivi.tulostaOikeaRivi());
    }
}
