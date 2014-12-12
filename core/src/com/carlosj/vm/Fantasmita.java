package com.carlosj.vm;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by PC on 04/12/2014.
 */
public class Fantasmita extends Actor {

    ArrayList <Image> fantasmas;
    int dibujo_actual=0;
    float tiempo_act=0;
    static float posX=1200,posY=240,width=0,height=0;
    static Personaje personaje;
    static int velocidad_desplazamiento=-3;


    public Fantasmita(Personaje p){
        personaje=p;
        fantasmas = new ArrayList<Image>();
        fantasmas.add(new Image(new Texture("volador01.png")));
        fantasmas.add(new Image(new Texture("volador02.png")));
        fantasmas.add(new Image(new Texture("volador03.png")));
        this.setX(1200);
        this.setY(240);
    }

    public void act(float delta) {
        super.act(delta);
        tiempo_act+=delta;
        if(tiempo_act>0.1f){
            dibujo_actual++;
            tiempo_act=0;
        }

        if(dibujo_actual>=fantasmas.size()){
            dibujo_actual=0;
        }
        this.moveBy(velocidad_desplazamiento,0);
        posX=this.getX();
        posY=this.getY();
        width=this.getWidth();
        height=this.getHeight();

    }

    public static void setVelocidad_desplazamientoFacil(){
        velocidad_desplazamiento=-3;
    }

    public static void setVelocidadNormal(){
        velocidad_desplazamiento=-5;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        fantasmas.get(dibujo_actual).setX(this.getX());
        fantasmas.get(dibujo_actual).setY(this.getY());
        fantasmas.get(dibujo_actual).draw(batch,parentAlpha);
    }
}
