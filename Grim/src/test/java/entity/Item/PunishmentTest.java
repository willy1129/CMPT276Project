package entity.item;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PunishmentTest {


    Point pt = new Point(200,200);
    Rectangle bd = new Rectangle(pt.x,pt.y,18,18);
    Punishment p = new Punishment(pt);

    //Test constructor
    @Test
    void constructorTest(){
        assertEquals(-75, p.value);
        assertEquals(pt,p.getPosition());
        assertEquals(bd,p.getBound());
        assertFalse(p.collected);
    }


    @Test
    void setPosition() {
        pt.setLocation(300,300);
        bd.setLocation(pt);
        p.setPosition(pt);
        assertEquals(pt,p.getPosition());
        assertEquals(bd,p.getBound());

    }

    @Test
    void setCollected() {
        p.setCollected();
        assertTrue(p.collected);
    }

}