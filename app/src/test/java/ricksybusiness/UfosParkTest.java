package ricksybusiness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UfosParkTest {
    UfosPark ovnisPark;
    CreditCard tarjeta1;
    CreditCard tarjeta2;
    CreditCard tarjeta3;

    @Before
    public void setUp(){
        ovnisPark = new UfosPark();
        tarjeta1 = new CreditCard("yo", "1");
        tarjeta2 = new CreditCard("tu", "2");
        tarjeta3 = new CreditCard("el", "3");
        tarjeta3.pay(2900.0);
    }

    @Test
    public void constructorTest(){
        assertEquals(500.0, ovnisPark.getFee(),0);
        assertEquals(0,ovnisPark.getNumberUfos());
    }

    @Test
    public void addUfoTest(){
        ovnisPark.add("Luni");
        assertEquals(1, ovnisPark.getNumberUfos());
        assertFalse(ovnisPark.hasUfo(tarjeta1));
        assertFalse(ovnisPark.hasUfo(tarjeta2));
        ovnisPark.add("Marti");
        assertEquals(2, ovnisPark.getNumberUfos());
        assertFalse(ovnisPark.hasUfo(tarjeta1));
        assertFalse(ovnisPark.hasUfo(tarjeta2));
    }

    @Test
    public void AddUfoTest_UfoAlreadyExists(){
        ovnisPark.add("Luni");
        ovnisPark.add("Marti");
        ovnisPark.add("Luni");
        assertEquals(2,ovnisPark.getNumberUfos());
    }

    @Test
    public void availableUfoTest(){
        assertFalse(ovnisPark.getAvailableUfo().isPresent());
        ovnisPark.add("Luni");
        ovnisPark.add("Marti");
        assertTrue(ovnisPark.getAvailableUfo().isPresent());
    }

    @Test
    public void setUfoUserTest(){
        ovnisPark.add("Luni");
        ovnisPark.add("Marti");
        ovnisPark.setUfoUser("Luni", tarjeta1);
        assertTrue(ovnisPark.hasUfo(tarjeta1));
        assertFalse(ovnisPark.hasUfo(tarjeta2));
        assertEquals("Luni", ovnisPark.getUfoOf(tarjeta1.number()));
        assertNull(ovnisPark.getUfoOf(tarjeta2.number()));
    }

    @Test
    public void allUfosOccupiedTest(){
        ovnisPark.add("Luni");
        ovnisPark.add("Marti");
        ovnisPark.setUfoUser("Luni", tarjeta1);
        ovnisPark.setUfoUser("Marti", tarjeta1);
        assertTrue(ovnisPark.getAvailableUfo().isEmpty());
    }

    @Test
    public void dispatchTest(){
        ovnisPark.add("Luni");
        ovnisPark.dispatch(tarjeta1);
        assertEquals(2500.0, tarjeta1.credit(),0);
        assertTrue(ovnisPark.hasUfo(tarjeta1));
    }

    @Test
    public void dispatchTest_AlreadyHasUfo(){
        ovnisPark.add("Luni");
        ovnisPark.add("Marti");
        ovnisPark.add("Miercuri");
        ovnisPark.add("Joi");
        ovnisPark.add("Vineri");
        ovnisPark.dispatch(tarjeta1);
        String ovni = ovnisPark.getUfoOf(tarjeta1.number());
        ovnisPark.dispatch(tarjeta1);
        assertEquals(2500.0, tarjeta1.credit(),0);
        assertTrue(ovnisPark.hasUfo(tarjeta1));
        assertEquals(ovni, ovnisPark.getUfoOf(tarjeta1.number()));
    }

    @Test
    public void dispatchTest_severalUsers(){
        ovnisPark.add("Luni");
        ovnisPark.add("Marti");
        ovnisPark.add("Miercuri");
        ovnisPark.add("Joi");
        ovnisPark.add("Vineri");
        ovnisPark.dispatch(tarjeta1);
        String ovni = ovnisPark.getUfoOf(tarjeta1.number());
        ovnisPark.dispatch(tarjeta2);
        assertEquals(2500.0, tarjeta2.credit(),0);
        assertTrue(ovnisPark.hasUfo(tarjeta2));
        assertNotEquals(ovnisPark.getUfoOf(tarjeta2.number()),ovni);
    }


    @Test
    public void dispatchTest_Alldispatched(){
        ovnisPark.add("Luni");
        ovnisPark.dispatch(tarjeta1);
        ovnisPark.dispatch(tarjeta2);
        assertEquals(3000.0, tarjeta2.credit(),0);
        assertTrue(ovnisPark.hasUfo(tarjeta1));
        assertFalse(ovnisPark.hasUfo(tarjeta2));
    }

    @Test
    public void dispatchTest_NoMoney(){
        ovnisPark.add("Luni");
        ovnisPark.dispatch(tarjeta3);
        assertFalse(ovnisPark.hasUfo(tarjeta3));
        assertEquals(100.0, tarjeta3.credit(), 0);
    }

}
