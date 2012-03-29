

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LaskuriTest {
    
    Laskuri laskuri;
    
    public LaskuriTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        laskuri=new Laskuri();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void konstruktoriAsettaaLaskurinNollaan() {
        assertEquals(0, laskuri.monesko());
    }
    
    @Test
    public void eteneminenKerran() {
        laskuri.etene();
        assertEquals(1, laskuri.monesko());
    }
    
    
    @Test
    public void eteneminenKaksiKertaa() {
        laskuri.etene();
        laskuri.etene();
        assertEquals(2, laskuri.monesko());
    }
    
    @Test
    public void nollausNollaaLaskurin() {
        laskuri.etene();
        laskuri.etene();
        laskuri.nollaa();
        assertEquals(0, laskuri.monesko());
    }
    
    
    
}
