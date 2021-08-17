package controller;

import entity.character.*;
import entity.item.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static command.Command.*;
import static game.Game.DEFAULT_WIDTH;
import static game.Game.DEFAULT_HEIGHT;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test that examines important interactions between Controller and Entity classes.
 * Tests Entity initialization, movements, and collisions.
 */
public class ControllerIT {
    public static final int DEFAULT_MOVE = 3;
    Controller c = Controller.getInstance();

    /**
     * Tests hardcoded Enemy initialization from controller
     */
    @Test
    public void controllerInitEnemyTest() {
        assertEquals(2, c.enemies.size());
        Point pt1 = new Point(340, 332);
        Point pt2 = new Point(625, 410);
        assertTrue(c.enemies.containsKey(pt1));
        assertTrue(c.enemies.containsKey(pt2));
    }

    /**
     * Tests hardcoded regular reward initialization from controller
     */
    @Test
    public void controllerInitRegTest() {
        assertEquals(6, c.regularRewards.size());
        Point pt1 = new Point(50, 50);
        Point pt2 = new Point(240, 140);
        Point pt3 = new Point(656, 240);
        Point pt4 = new Point(530, 430);
        Point pt5 = new Point(305, 496);
        Point pt6 = new Point(560, 50);
        assertTrue(c.regularRewards.containsKey(pt1));
        assertTrue(c.regularRewards.containsKey(pt2));
        assertTrue(c.regularRewards.containsKey(pt3));
        assertTrue(c.regularRewards.containsKey(pt4));
        assertTrue(c.regularRewards.containsKey(pt5));
        assertTrue(c.regularRewards.containsKey(pt6));
    }

    /**
     * Tests random bonus reward and punishment initialization from controller
     */
    @Test
    public void controllerInitItemTest() {
        assertEquals(4, c.punishments.size());
        assert(c.bonusRewards.size() == 0 || c.bonusRewards.size() == 1);
        c.punishments.forEach( (point, pun) -> {
            assert(0 <= point.x && point.x <= DEFAULT_WIDTH);
            assert(0 <= point.y && point.y <= DEFAULT_HEIGHT);
        });
        c.bonusRewards.forEach( (point, bon) -> {
            assert(0 <= point.x && point.x <= DEFAULT_WIDTH);
            assert(0 <= point.y && point.y <= DEFAULT_HEIGHT);
        });
    }

    /**
     * Tests MainCharacter initialization and movement from controller
     */
    @Test
    public void controllerMoveCharacterTest() {
        MainCharacter mc = c.mainCharacter;
        Point pt = mc.getPosition();
        Point testPt = new Point(256, 0);
        assertEquals(testPt, pt);

        // UP should not change mc position when mainCharacter is at top of board
        pt = c.moveCharacter(UP);                                   assertEquals(testPt, pt);
        pt = c.moveCharacter(NONE);                                 assertEquals(testPt, pt);
        pt = c.moveCharacter(OTHER);                                assertEquals(testPt, pt);
        pt = c.moveCharacter(DOWN);     testPt.y += DEFAULT_MOVE;    assertEquals(testPt, pt);
        pt = c.moveCharacter(LEFT);     testPt.x -= DEFAULT_MOVE;    assertEquals(testPt, pt);
        pt = c.moveCharacter(RIGHT);    testPt.x += DEFAULT_MOVE;    assertEquals(testPt, pt);
        pt = c.moveCharacter(UP);       testPt.y -= DEFAULT_MOVE;    assertEquals(testPt, pt);
    }

    /**
     * Tests controller reset by checking main character position
     */
    @Test
    public void controllerResetTest() {
        MainCharacter mc = c.mainCharacter;
        Point testPt = mc.getPosition();

        // move mc, mc position should be changed
        Point pt = c.moveCharacter(DOWN);     testPt.y += DEFAULT_MOVE;    assertEquals(testPt, pt);

        // reset controller, mc should be at start position
        c = Controller.resetInstance();
        Point startPt = new Point(256, 0);
        assertEquals(startPt, c.mainCharacter.getPosition());

    }

    /**
     * Tests collision-checking methods in controller
     */
    @Test
    public void controllerCheckCollisions() {
        c.regularRewards.clear();
        c.bonusRewards.clear();
        c.punishments.clear();
        c.enemies.clear();

        Point pt = new Point (0,0);
        RegularReward reg = new RegularReward(pt);
        BonusReward bon = new BonusReward(pt);
        Punishment pun = new Punishment(pt);
        Enemy enemy = new Enemy(pt);

        // Test case 1: no collide, no remove if collide
        // Maps are currently empty
        assert(c.checkRegCollision( reg.getBound(), true ) == 0);
        assert(c.checkBonusCollision( bon.getBound(), true ) == 0);
        assert(c.checkPunCollision( pun.getBound(), true ) == 0);
        assert(c.checkEnemyCollision( enemy.getBound(), true ) == 0);

        c.regularRewards.put(pt, reg);
        c.bonusRewards.put(pt, bon);
        c.punishments.put(pt, pun);
        c.enemies.put(pt, enemy);

        Point pt2 = new Point (300,300);
        RegularReward reg2 = new RegularReward(pt2);
        BonusReward bon2 = new BonusReward(pt2);
        Punishment pun2 = new Punishment(pt2);
        Enemy enemy2 = new Enemy(pt2);

        // Test case 2: no collide, remove if collide
        assert(c.checkRegCollision( reg2.getBound(), true ) == 0);
        assert(c.checkBonusCollision( bon2.getBound(), true ) == 0);
        assert(c.checkPunCollision( pun2.getBound(), true ) == 0);
        assert(c.checkEnemyCollision( enemy2.getBound(), true ) == 0);
        assert(c.regularRewards.size() == 1);
        assert(c.bonusRewards.size() == 1);
        assert(c.punishments.size() == 1);
        assert(c.enemies.size() == 1);

        // Test case 3: yes collide, no remove if collide
        assert(c.checkRegCollision( reg.getBound(), false ) != 0);
        assert(c.checkBonusCollision( bon.getBound(), false ) != 0);
        assert(c.checkPunCollision( pun.getBound(), false ) != 0);
        assert(c.checkEnemyCollision( enemy.getBound(), false ) != 0);
        assertEquals(1, c.regularRewards.size());
        assertEquals(1, c.bonusRewards.size());
        assertEquals(1, c.punishments.size());
        assertEquals(1, c.enemies.size());

        // Test case 4: yes collide, yes remove if collide
        assert(c.checkRegCollision( reg.getBound(), true ) != 0);
        assert(c.checkBonusCollision( bon.getBound(), true ) != 0);
        assert(c.checkPunCollision( pun.getBound(), true ) != 0);
        assert(c.checkEnemyCollision( enemy.getBound(), true ) != 0);
        assertTrue(c.regularRewards.isEmpty());
        assertTrue(c.bonusRewards.isEmpty());
        assertTrue(c.punishments.isEmpty());
        assertTrue(c.enemies.isEmpty());
    }

}