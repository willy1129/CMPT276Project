package entity.item;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RegularRewardTest {
    Point pt = new Point(200,200);
    Rectangle bd = new Rectangle(pt.x,pt.y,18,18);
    RegularReward r = new RegularReward(pt);

    //Test constructor
    @Test
    void constructorTest(){
            assertEquals(50, r.getValue());
            assertEquals(pt,r.getPosition());
            assertEquals(bd,r.getBound());
            assertFalse(r.collected);
    }

    @Test
    void setPosition() {
        pt.setLocation(300,300);
        bd.setLocation(pt);
        r.setPosition(pt);
        assertEquals(pt,r.getPosition());
        assertEquals(bd,r.getBound());
    }

    @Test
    void setCollected() {
        r.setCollected();
        assertTrue(r.collected);
    }




}