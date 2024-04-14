package ricksybusiness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {
    CreditCard tarjeta1;

    @Before
    public void Setup(){
        tarjeta1 = new CreditCard("yo", "1");
    }

    @Test
    public void ConstructorTest(){
        assertEquals(3000.0,tarjeta1.credit(), 0);
        assertEquals("yo",tarjeta1.getOwner());
        assertEquals("1",tarjeta1.number());
    }

    @Test
    public void canAffordTest(){
        assertTrue(tarjeta1.canAfford(1000.0));
        assertFalse(tarjeta1.canAfford(4000.0));
    }

    @Test
    public void payTest(){
        tarjeta1.pay(1000.0);
        assertEquals(2000.0, tarjeta1.credit(), 0);
        tarjeta1.pay(10000.0);
        assertEquals(2000.0, tarjeta1.credit(), 0);
    }
}
