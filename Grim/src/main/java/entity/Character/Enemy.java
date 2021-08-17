package entity.character;

import command.Command;
import gfx.Assets;

import java.awt.*;


/**
 * This class represents the Enemy
 *
 * @author Wai Lee
 * @version 0.2
 * @since 2021-03-04
 */

public class Enemy extends Character {

    public Enemy(Point pt) {
        super(pt);
        currentAnimation = Command.DOWN;
        up = Assets.angelBack1;
        down = Assets.angelFront1;
        left = Assets.angelLeft1;
        right = Assets.angelRight1;
        upAni = Assets.angelBack2;
        downAni = Assets.angelFront2;
        leftAni1 = Assets.angelLeft2;
        rightAni1 = Assets.angelRight2;
        leftAni2 = Assets.angelLeft3;
        rightAni2 = Assets.angelRight3;
        defaultMove = 1;
    }

    /**
     * Method for moving Enemies closer to the specified Point
     *
     * @param pursuedPosition position of pursued Character's Point
     * @return Point on which the Enemy ends after move
     */
    public Point move(Point pursuedPosition) {
        int dx = pursuedPosition.x - this.position.x;
        int dy = pursuedPosition.y - this.position.y;

        if(java.lang.Math.abs(dx) > java.lang.Math.abs(dy)) {
            if(dx > 0) {
                if (!this.moveRight()) {
                    currentAnimation = Command.RIGHT;
                    if(dy < 0) {
                        if(this.moveUp()) currentAnimation = Command.UP;
                    }
                    else {
                        if(this.moveDown()) currentAnimation = Command.DOWN;
                    }
                }
            }
            else {
                if (!this.moveLeft()) {
                    currentAnimation = Command.LEFT;
                    if(dy < 0) {
                        if(this.moveUp()) currentAnimation = Command.UP;
                    }
                    else {
                        if(this.moveDown()) currentAnimation = Command.DOWN;
                    }
                }
            }
        }
        else {
            if(dy < 0) {
                if (!this.moveUp()) {
                    currentAnimation = Command.UP;
                    if(dx > 0) {
                        if(this.moveRight()) currentAnimation = Command.RIGHT;
                    }
                    else {
                        if(this.moveLeft()) currentAnimation = Command.LEFT;
                    }
                }
            }
            else {
                if (!this.moveDown()) {
                    currentAnimation = Command.DOWN;
                    if(dx > 0) {
                        if(this.moveRight()) currentAnimation = Command.RIGHT;
                    }
                    else {
                        if(this.moveLeft()) currentAnimation = Command.LEFT;
                    }
                }
            }
        }
        return this.position;
    }

    @Override
    public void render(Graphics g){
        switch (currentAnimation){
            case UP :
                img = Assets.angelBack1;
                img2 = Assets.angelBack2;
                img3 = Assets.angelBack1;
                break;
            case DOWN :
                img = Assets.angelFront1;
                img2 = Assets.angelFront2;
                img3 = Assets.angelFront1;
                break;
            case LEFT:
                img = Assets.angelLeft1;
                img2 = Assets.angelLeft2;
                img3 = Assets.angelLeft3;
                break;
            case RIGHT :
                img = Assets.angelRight1;
                img2 = Assets.angelRight2;
                img3 = Assets.angelRight3;
                break;
            default:
        }
        if(currentAnimation == Command.UP || currentAnimation == Command.DOWN) {
            if (renderTiming < 30) {
                g.drawImage(img, position.x, position.y, null);
                renderTiming++;
            } else if(renderTiming < 60) {
                g.drawImage(img2, position.x, position.y, null);
                lastAni = 1;
                renderTiming++;
            }
            else{
                g.drawImage(img, position.x, position.y, null);
                renderTiming = 0;
            }
        }
        else{
            if (renderTiming < 15){
                g.drawImage(img, position.x,position.y,null);
                renderTiming++;
            }
            else if (renderTiming < 30){
                g.drawImage(img2, position.x,position.y,null);
                renderTiming++;
            }
            else if (renderTiming < 45){
                g.drawImage(img, position.x, position.y, null);
                renderTiming++;
            }
            else if (renderTiming < 60){
                g.drawImage(img3, position.x, position.y, null);
                renderTiming++;
            }
            else{
                g.drawImage(img, position.x,position.y,null);
                renderTiming = 0;
            }
        }
    }
}
