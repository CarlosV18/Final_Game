package com.carlosj.vm;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by PC on 04/12/2014.
 */
public class Plataforma extends Image{

    int velocidad=500;

    public Plataforma(){
        super (new Texture("tierra.png"));
        this.setX(1300);
        this.setY(-200);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setX(this.getX()-delta*velocidad);
    }
}

