package com.carlosj.vm;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by PC on 10/12/2014.
 */
public class Titulo extends Actor {

    ArrayList <Image> title;
    int dibujo_actual=0;
    float tiempo_act=0;

    public Titulo(){
        title= new ArrayList<Image>();
        title.add(new Image(new Texture("Titulo1.png")));
        title.add(new Image(new Texture("Titulo2.png")));
        title.add(new Image(new Texture("Titulo3.png")));
        title.add(new Image(new Texture("Titulo4.png")));
        this.setX(-1100);
        this.setY(400);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        tiempo_act+=delta;
        if(tiempo_act>0.1f){
            dibujo_actual++;
            tiempo_act=0;
        }

        if(dibujo_actual>=title.size()){
            dibujo_actual=0;
        }
        this.moveBy(5,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        title.get(dibujo_actual).setX(this.getX());
        title.get(dibujo_actual).setY(this.getY());

        title.get(dibujo_actual).draw(batch,parentAlpha);
    }
}
