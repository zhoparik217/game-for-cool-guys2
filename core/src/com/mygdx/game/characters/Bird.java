package com.mygdx.game.characters;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {

    public int x, y;
    public int width, height;

    int speed;
    int baseSpeed;
    int lastSpeedScore=0;
    int jumpHeight;
    final int maxHeightOfJump = 100;
    boolean jump = true;

    int frameCounter;
    Texture[] framesArray;
    Sound speedUpSound;

    public Bird(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.baseSpeed = speed;
        this.width = width;
        this.height = height;
        frameCounter = 0;
        speedUpSound =Gdx.audio.newSound(Gdx.files.internal("speed.wav"));

        framesArray = new Texture[]{
                new Texture("kenny0.png"),
                new Texture("kenny1.png"),
                new Texture("kenny2.png"),
                new Texture("kenny3.png"),
                new Texture("kenny0.png"),
        };

    }
    public void updateSpeed(int  points){
        if(points>=lastSpeedScore+20){
            lastSpeedScore=points;
            int speedBoost= points/20;
            speed= baseSpeed+(speedBoost*2);
            speedUpSound.play(1.0f);
        }

    }
    public void setY(int y) {
        this.y = y;
        lastSpeedScore=0;
    }

    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void fly() {
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;
        return true;
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
        speedUpSound.dispose();
    }
}
