package controller;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import command.Command;
import entity.character.*;
import entity.Entity;
import entity.item.*;
import board.Board;
import sound.SoundAssets;

import static cells.Cell.*;
import static game.Game.DEFAULT_WIDTH;
import static game.Game.DEFAULT_HEIGHT;

/**
 * Represents a game controller. Manages the initialization and movements of game entities (characters and items), board and walls.
 *
 * @author Adrienne Kwan
 * @version 1.0
 * @since 1.0
 */
public class Controller {
    private static Controller instance;
    public MainCharacter mainCharacter;
    public Board board;
    //Changed the use of HashMap to ConcurrentHashMap to avoid errors -Wai Lee
    public Map<Point, Enemy> enemies = new ConcurrentHashMap<>();
    public Map<Point, RegularReward> regularRewards = new ConcurrentHashMap<>();
    public Map<Point, BonusReward> bonusRewards = new ConcurrentHashMap<>();
    public Map<Point, Punishment> punishments = new ConcurrentHashMap<>();
    private int bonusesLeft = 6;
    private Rectangle[] walls;

    /**
     * Initializes board and all entities in a private constructor
     */
    private Controller() {
        initBoard();
        initMainCharacter();
        initEnemies();
        initRegs();
        manageBonusRewards();
        initPuns();
    }

    /**
     * Assigns new Controller to instance field if instance is null, and returns instance
     *
     * @return instance of Controller
     */
    public static Controller getInstance(){
        if (instance == null) { instance = new Controller(); }
        return instance;
    }

    /**
     * Assigns new Controller to instance field
     *
     * @return new instance of Controller
     */
    public static Controller resetInstance() {
        instance = new Controller();
        return instance;
    }

    /**
     * Initializes the board
     */
    public void initBoard() {
        //board = new Board("Grim\\src\\main\\resources\\board.txt");
        board = new Board("board.txt");
        walls = board.walls();
    }

    /**
     * Initializes mainCharacter at start cell
     */
    public void initMainCharacter() {
        Point startCell = new Point(256, 0);
        mainCharacter = new MainCharacter(startCell);
    }

    /**
     * Initializes enemies
     */
    public void initEnemies() {
        Point pt1 = new Point(340, 332);
        Point pt2 = new Point(625, 410);
        enemies.put(pt1, new Enemy(pt1));
        enemies.put(pt2, new Enemy(pt2));
    }

    /**
     * Initializes regular items
     */
    public void initRegs() {
        Point pt1 = new Point(50, 50);
        Point pt2 = new Point(240, 140);
        Point pt3 = new Point(656, 240);
        Point pt4 = new Point(530, 430);
        Point pt5 = new Point(305, 496);
        Point pt6 = new Point(560, 50);

        regularRewards.put(pt1, new RegularReward(pt1));
        regularRewards.put(pt2, new RegularReward(pt2));
        regularRewards.put(pt3, new RegularReward(pt3));
        regularRewards.put(pt4, new RegularReward(pt4));
        regularRewards.put(pt5, new RegularReward(pt5));
        regularRewards.put(pt6, new RegularReward(pt6));
    }

    /**
    * Initializes punishments
    */
    public void initPuns() {
        int totalPuns = 4;
        for (int i = 0; i < totalPuns; i++) {
            Point pt = randPoint();
            Punishment pun = new Punishment(pt);
            while ( checkSpawnPoint(pun) )
                pun.setPosition(randPoint());
            punishments.put(pun.getPosition(), pun);
        }
    }

    /**
     * Calls mainCharacter's move method
     *
     * @param move user's input command
     */
    public Point moveCharacter(Command move) {
        return mainCharacter.move(move);
    }

    /**
     * Updates movements of enemies by calling enemies' move methods
     */
    public void moveMovingEnemies() { enemies.forEach((point, enemy) -> enemy.move(mainCharacter.getPosition())); }

    /**
     * Updates collection of bonus rewards. Creates new bonuses at random, and removes expired bonuses.
     * Currently we only want to have one bonus reward at a time, but have chosen to leave bonuses as a Map for flexibility and consistency.
     */
    public void manageBonusRewards() {
        if (bonusesLeft > 0 && bonusRewards.isEmpty()) {
            int rarity = 200;
            if (randInt() % rarity == 0) {
                Point pt = randPoint();
                BonusReward bon =  new BonusReward(pt);
                while ( checkSpawnPoint(bon) )
                    bon.setPosition(randPoint());
                bonusRewards.put(bon.getPosition(), bon);
                bonusesLeft--;
            }
        }
        bonusRewards.forEach((point, bonus) -> bonus.countDown() );
    }

    /**
     * Checks whether an entity will spawn in an occupied or invalid space
     *
     * @param entity the entity to be checked
     * @return whether or not the point overlaps with other entities or walls, or is outside the board space
     */
    private boolean checkSpawnPoint(Entity entity) {
        Point pt = entity.getPosition();
        Rectangle bound = entity.getBound();
        return ( checkBoard(pt) || checkWall(pt)
                || checkRegCollision(bound, false) != 0
                || checkBonusCollision(bound, false) != 0
                || checkPunCollision(bound, false) != 0 );
    }

    /**
     * Checks whether a point is outside the board space
     *
     * @param pt the point to be checked
     * @return whether or not the point is outside of the board space
     */
    private boolean checkBoard(Point pt) {
        int minW = (int) (CELL_WIDTH * 1.5);
        int maxW = (int) (DEFAULT_WIDTH - CELL_WIDTH * 1.5);
        int minH = (int) (CELL_HEIGHT * 1.5);
        int maxH = (int) (DEFAULT_HEIGHT - CELL_HEIGHT * 1.5);
        return ( pt.getX() < minW || pt.getX() > maxW || pt.getY() < minH || pt.getY() > maxH );
    }

    /**
     * Checks whether a point overlaps with a wall
     *
     * @param pt the point to be checked
     * @return whether or not the point overlaps with a wall
     */
    public boolean checkWall(Point pt){
        int i = 0;
        Rectangle rect = new Rectangle(pt.x, pt.y, 28, 32);
        while ( walls[i + 1] != null ) {
            if ( walls[i].intersects(rect) ) { return true; }
            i++;
        }
        return false;
    }

    /**
     * Calls render methods of all entities
     */
    public void render(Graphics g) {
        board.render(g);
        regularRewards.forEach( (point, reg) -> reg.render(g));
        bonusRewards.forEach( (point, bonus) -> bonus.render(g));
        punishments.forEach( (point, pun) -> pun.render(g));
        mainCharacter.render(g);
        enemies.forEach( (point, enemy) -> enemy.render(g));
    }

    /**
     * Generates a random new Point
     *
     * @return a random new Point
     */
    private Point randPoint() {
        return new Point(randInt(), randInt());
    }

    /**
     * Generates a random integer between 0 and board's width minus 1
     *
     * @return random integer
     */
    private int randInt() {
        Random rand = new Random();
        int boardWidth = DEFAULT_WIDTH + 1;
        return rand.nextInt(boardWidth);
    }

    /**
     * Checks for collision between given Rectangle and regular rewards locations
     *
     * @param mBound Rectangle to check
     * @param remove whether to remove colliding regular reward or not
     * @return value of regular reward if collision, and 0 if no collision
     */
    public int checkRegCollision(Rectangle mBound, boolean remove) {
        int val = 0;
        for (Map.Entry<Point, RegularReward> entry : regularRewards.entrySet()) {
            Point point = entry.getKey();
            RegularReward reg = entry.getValue();
            if (reg.getBound().intersects(mBound)) {
                val = reg.getValue();
                if (remove) {
                    reg.setCollected();
                    regularRewards.remove(point, reg);
                }
            }
        }
        return val;
    }

    /**
     * Checks for collision between given Rectangle and bonus rewards locations
     *
     * @param mBound Rectangle to check
     * @param remove whether to remove colliding regular reward or not
     * @return value of bonus reward if collision, and 0 if no collision
     */
    public int checkBonusCollision(Rectangle mBound, boolean remove) {
        int val = 0;
        for (Map.Entry<Point, BonusReward> entry : bonusRewards.entrySet()) {
            Point point = entry.getKey();
            BonusReward bonus = entry.getValue();
            if (bonus.getBound().intersects(mBound)) {
                if (remove) bonus.setCollected();
                val = bonus.getValue();
            }
            if (bonus.getCollected()) {
                bonusRewards.remove(point, bonus);
            }
        }
        return val;
    }

    /**
     * Checks for collision between given Rectangle and punishment locations
     *
     * @param mBound Rectangle to check
     * @param remove whether to remove colliding regular reward or not
     * @return value of punishment if collision, and 0 if no collision
     */
    public int checkPunCollision(Rectangle mBound, boolean remove) {
        int val = 0;
        for (Map.Entry<Point, Punishment> entry : punishments.entrySet()) {
            Point point = entry.getKey();
            Punishment pun = entry.getValue();
            if (pun.getBound().intersects(mBound)) {
                if (remove) {
                    pun.setCollected();
                    SoundAssets.damage_sound();
                    punishments.remove(point, pun);
                }
                val = pun.getValue();
            }
        }
        return val;
    }

    /**
     * Checks for collision between given Rectangle and Enemy locations
     *
     * @param mBound Rectangle to check
     * @param remove whether to remove colliding regular reward or not
     * @return large negative integer value if collision, and 0 if no collision
     */
    public int checkEnemyCollision(Rectangle mBound, boolean remove) {
        int val = 0;
        for (Map.Entry<Point, Enemy> entry : enemies.entrySet()) {
            Point point = entry.getKey();
            Enemy enemy = entry.getValue();
            if (enemy.getBound().intersects(mBound)) {
                if (remove) enemies.remove(point, enemy);
                val = -9999999;
                SoundAssets.lose_sound();
            }
        }
        return val;
    }
}