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
public class Personaje extends Actor {

    ArrayList <Image> images;
    int dibujo_actual=0,llamadas_act=0,contSalto=0;
    float frame=0, velocidadY=0,aceleracionY=0,gravedad=200f;
    float posX=0,posY=0,width=0,height=0;
    static float posIni=70,mover_x=0;

    public Personaje(){
        images = new ArrayList<Image>();
        images.add(new Image(new Texture("run01.png")));
        images.add(new Image(new Texture("run02.png")));
        images.add(new Image(new Texture("run03.png")));
        images.add(new Image(new Texture("run04.png")));

        this.setY(posIni);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println(delta);
        frame+=delta;

        if(frame>0.45f){
            dibujo_actual++;
            frame=0;
        }

        if(dibujo_actual==images.size()){
            dibujo_actual=0;
        }
        //Desplazamiento en Y

        velocidadY+=aceleracionY;
        this.setY(this.getY()+velocidadY);

        //Gravedad

        aceleracionY-=0.15;

        if(this.getY()<=70){
            velocidadY=0;
            aceleracionY=0;
            this.setY(70);
        }
        posX= this.getX();
        posY=this.getY();
        width=this.getWidth();
        height=this.getHeight();
        this.moveBy(mover_x,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        images.get(dibujo_actual).setX(this.getX());
        images.get(dibujo_actual).setY(this.getY());

        images.get(dibujo_actual).draw(batch,parentAlpha);
    }

    public void saltar(){
       contSalto++;
        if(contSalto<=2){
            aceleracionY=1.8f;
           System.out.println("hago salto numero: "+contSalto);
        }

        if(images.get(dibujo_actual).getY()==70){
            contSalto=0;
        }
    }

    public static void set_Mover(){
        mover_x=3;
    }
    public static void setMover_facil(){
        mover_x=5;
    }

    public static void setMover_normal(){
        mover_x=7;
    }

    public static void setMover_dificil(){
        mover_x=10;
    }

    public static void setInicial(){
        mover_x=0;
    }

    public static void setPosIni(){
        posIni=90;
    }

    public static void setPosIni_inicial(){
        posIni=70;
    }
}
