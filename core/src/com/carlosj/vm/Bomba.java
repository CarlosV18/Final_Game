package com.carlosj.vm;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 * Created by PC on 04/12/2014.
 */
public class Bomba extends Actor {

    ArrayList <Image> bombas;
    int dibujo_actual=0;
    float tiempo_act=0;
    static float posX=70,posY=1200,width=0,height=0;
    static Personaje personaje;
    static int velocidad_desplazamiento=-3;

    public Bomba(Personaje p){
        personaje=p;
        bombas= new ArrayList<Image>();
        bombas.add(new Image(new Texture("bomba01.png")));
        bombas.add(new Image(new Texture("bomba02.png")));
        bombas.add(new Image(new Texture("bomba03.png")));
        this.setX(1200);
        this.setY(70);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        tiempo_act+=delta;
        if(tiempo_act>0.1f){
            dibujo_actual++;
            tiempo_act=0;
        }

        if(dibujo_actual>=bombas.size()){
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

    public static void setVelocidadDificil(){
        velocidad_desplazamiento=-7;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        bombas.get(dibujo_actual).setX(this.getX());
        bombas.get(dibujo_actual).setY(this.getY());
        bombas.get(dibujo_actual).draw(batch,parentAlpha);
    }


}
