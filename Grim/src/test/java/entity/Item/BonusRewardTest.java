package entity.item;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BonusRewardTest {

    Point pt = new Point(200,200);
    Rectangle bd = new Rectangle(pt.x,pt.y,18,18);
    BonusReward b = new BonusReward(pt);

    //Test constructor
    @Test
    void constructorTest(){
        assertEquals(100, b.getValue());
        assertEquals(pt,b.getPosition());
        assertEquals(bd,b.getBound());
        assertFalse(b.collected);
    }

    @Test
    void setPosition() {
        pt.setLocation(300,300);
        bd.setLocation(pt);
        b.setPosition(pt);
        assertEquals(pt,b.getPosition());
        assertEquals(bd,b.getBound());
    }

    @Test
    void countDown() {
        for(int i=0; i<=300 ;i++ ){
            b.countDown();
        }
        assertTrue(b.getCollected());
    }
}