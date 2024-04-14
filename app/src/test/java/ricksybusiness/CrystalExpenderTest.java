package ricksybusiness;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CrystalExpenderTest {
    CrystalExpender packExpender;
    CreditCard tarjeta1;
    CreditCard tarjeta2;

    @Before
    public void setUp(){
        packExpender = new CrystalExpender(3, 50);
        tarjeta1 = new CreditCard("yo", "1");
        tarjeta2 = new CreditCard("tu", "2");
        tarjeta2.pay(2960.0);
    }

    @Test
    public void constructorTest(){
        assertEquals(3, packExpender.stock());
        assertEquals(50.0, packExpender.cost(), 0);
    }

    @Test
    public void sellStockTest(){
        packExpender.sellStock();
        assertEquals(2,packExpender.stock());
        packExpender.sellStock();
        assertEquals(1,packExpender.stock());
        packExpender.sellStock();
        assertEquals(0,packExpender.stock());
        packExpender.sellStock();
        assertEquals(0,packExpender.stock());

    }

    @Test
    public void dispatchTest(){
        packExpender.dispatch(tarjeta1);
        assertEquals(2,packExpender.stock());
        assertEquals(2950.0, tarjeta1.credit(),0);
    }

    @Test
    public void dispatchTest_noStock(){
        packExpender.sellStock();
        packExpender.sellStock();
        packExpender.sellStock();
        packExpender.sellStock();
        packExpender.dispatch(tarjeta1);
        assertEquals(0,packExpender.stock());
        assertEquals(3000.0, tarjeta1.credit(),0);
    }

    @Test
    public void dispatchTest_noMoney(){
        packExpender.dispatch(tarjeta2);
        assertEquals(3,packExpender.stock());
        assertEquals(40.0, tarjeta2.credit(),0);
    }

}
