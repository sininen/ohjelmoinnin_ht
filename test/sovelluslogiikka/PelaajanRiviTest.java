package sovelluslogiikka;



import sovelluslogiikka.PelaajanRivi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PelaajanRiviTest {
    
    PelaajanRivi pelaajanRivi;
    
    public PelaajanRiviTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        pelaajanRivi=new PelaajanRivi();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void lisaaVariLisaaVarejaRiviin() {
        pelaajanRivi.lisaaVari(1);
        assertEquals(1, pelaajanRivi.annaPelaajanRivi().size());
        assertEquals(1, pelaajanRivi.annaPelaajanRivi().get(0));   
    }
    
    @Test
    public void rivinPituusOikeinKunRiviTyhja() {
        assertEquals(0, pelaajanRivi.pelaajanRivinPituus());
    }
    
    @Test
    public void rivinPituusOikeinKunRivissaLukuja() {
        pelaajanRivi.lisaaVari(1);
        pelaajanRivi.lisaaVari(2);
        assertEquals(2, pelaajanRivi.pelaajanRivinPituus());
    }
    
    @Test
    public void lisaysLisaaVainSeiskaaPienempiaNumeroita() {
        pelaajanRivi.lisaaVari(7);
        pelaajanRivi.lisaaVari(10);
        assertEquals(0, pelaajanRivi.pelaajanRivinPituus());
    }
    
    @Test
    public void lisaysLisaaVainNollaaSuurempiaLukuja() {
        pelaajanRivi.lisaaVari(0);
        pelaajanRivi.lisaaVari(-1);
        assertEquals(0, pelaajanRivi.pelaajanRivinPituus());
    }
    
    @Test
    public void lisaysAntaaLisataLukujaYkkosestaKutoseen() {
        pelaajanRivi.lisaaVari(1);
        pelaajanRivi.lisaaVari(6);
        pelaajanRivi.lisaaVari(3);
        
        assertEquals(3, pelaajanRivi.pelaajanRivinPituus());
        assertEquals(1, pelaajanRivi.annaPelaajanRivi().get(0));
        assertEquals(6, pelaajanRivi.annaPelaajanRivi().get(1));
        
    }
    
    @Test
    public void lisaysPalauttaaMonteenkoPaikkaanLukuLisättiin() {
        assertEquals(0, pelaajanRivi.lisaaVari(1));
    }
    
    @Test
    public void lisaysPalauttaaMiinusYksiJosNumeroaEiLisatty() {
        assertEquals(-1, pelaajanRivi.lisaaVari(7));
    }
    
    @Test
    public void lisaysPalauttaaOikeinKunLisataanKaksiNumeroa() {
        pelaajanRivi.lisaaVari(1);
        assertEquals(1, pelaajanRivi.lisaaVari(2));
    }
    
    @Test
    public void lisaysPalauttaaNollanTyhjentamisenJalkeen() {
        pelaajanRivi.lisaaVari(1);
        pelaajanRivi.lisaaVari(2);
        pelaajanRivi.tyhjennaPelaajanRivi();
        assertEquals(0, pelaajanRivi.pelaajanRivinPituus());
    }
    
    @Test
    public void tyhjennaPelaajanRiviTyhjentaaRivin() {
        pelaajanRivi.lisaaVari(1);
        pelaajanRivi.lisaaVari(2);
        
        pelaajanRivi.tyhjennaPelaajanRivi();
        assertEquals(0, pelaajanRivi.pelaajanRivinPituus());
    }
    
    @Test
    public void rivinTulostusOikein() {
        pelaajanRivi.lisaaVari(1);
        assertEquals("[1]", pelaajanRivi.tulostaPelaajanRivi());
    }
}
