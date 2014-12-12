package com.carlosj.vm;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.math.Rectangle;

public class ClasePrincipal extends ApplicationAdapter implements InputProcessor{

    Stage juego,menu_inicial,game_over,instrucciones,dificultad;
    Personaje p,pers;
    Bomba bon;
    Fantasmita fan;
    int rep=0;
    Texture fondo_juego;
    Rectangle pe,b;
    int touched_x=0,touched_y=0;
    boolean inMenu=true,inJuego=false,inInstrucciones=false,inSalir=false,inDificultad=false, inGameOver =false,inGameOVer2=false;
    boolean menuMusic,gameMusic;
    Image regresar1,regresar2,fondo_menu;
    SpriteBatch batch;
    Texture instruciones2;
    Music menu_music,game_music;

    boolean mostrarFantasmita;

    @Override
    public void create () {
        regresar1= new Image(new Texture("regresar1.png"));
        regresar2= new Image(new Texture("regresar2.png"));
        instruciones2 =new Texture("instrucciones2.png");
        fondo_menu= new Image(new Texture("G112_OT000_002A_background.jpg"));

        menuMusic=true;
        gameMusic=true;

        mostrarFantasmita=false;
        //menu_music=Gdx.audio.newMusic(Gdx.files.getFileHandle("Running through worlds.wav", Files.FileType.Internal));
        game_music=Gdx.audio.newMusic(Gdx.files.getFileHandle("Theme_0.mp3", Files.FileType.Internal));

       /* if(menuMusic){
            menu_music.play();
            menu_music.setLooping(true);
        }*/

        if(gameMusic){
            game_music.play();
            game_music.setLooping(true);
        }
        batch= new SpriteBatch();



        juego= new Stage();
        menu_inicial= new Stage();
        instrucciones= new Stage();
        dificultad = new Stage();
        game_over= new Stage();

        p= new Personaje();
        bon= new Bomba(p);
        fan = new Fantasmita(p);
        pers= new Personaje();

        menu_inicial.addActor(fondo_menu);
        menu_inicial.addActor(pers);

        agregarBotones();
        agregarInstrucciones();
        dificultad();
        game_over();

        fondo_juego= new Texture("mountains.png");
        juego.addActor(new Image(fondo_juego));
        juego.addActor(p);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(inMenu){
            menu_inicial.draw();
            menu_inicial.act();

            if(rep%450==0){
            titulo();
            }

            int touched_x= Gdx.input.getX();
            int touched_y= Gdx.input.getY();

            if((touched_x<=710 && touched_x>=520) && (touched_y<=260 && touched_y>=200)){
                inMenu=false;
                inJuego=true;
                menuMusic=false;
                gameMusic=true;
                touched_x=0;
                touched_y=0;

            }


            if((touched_x<=770 && touched_x>=515) && (touched_y<=646 && touched_y>=585)){

                inMenu=false;
                inDificultad=true;
                touched_x=0;
                touched_y=0;

            }

            if((touched_x<=757 && touched_x>=494) && (touched_y<=360 && touched_y>=320)){
                inMenu=false;
                inInstrucciones=true;
                touched_x=0;
                touched_y=0;

            }

            if((touched_x<=780 && touched_x>=500) && (touched_y<=530 && touched_y>=450)){
                inMenu=false;
                inSalir=true;
             }

        }


        if(inJuego){
            juego.draw();
            juego.act();

            if(rep%50==0){
                tierra();
            }

            if(mostrarFantasmita){
                if(rep%200==0){
                    fantasmita();
                }

            }


            if(rep%500==0){
                bomba();
            }

        }
        inGameOver =colision(Bomba.personaje,Fantasmita.personaje);
        //inGameOVer2=colision2(Fantasmita.personaje);

        if (inGameOver){
            game_over.draw();
            inJuego=false;
            touched_x= Gdx.input.getX();
            touched_y= Gdx.input.getY();

            //if((touched_x<=1175 && touched_x>=970) && (touched_y<=575 && touched_y>=500)){
              if(Gdx.input.isTouched()){
                  inMenu=true;
                  Bomba.posX=70;
                  Bomba.posY=1200;
                  game_music.stop();
                  create();
                  inGameOver =false;

              }


            //}
        }

        if(inInstrucciones){
          instrucciones.draw();
           int  touched_x= Gdx.input.getX();
            int touched_y= Gdx.input.getY();

            if((touched_x<=1175 && touched_x>=970) && (touched_y<=575 && touched_y>=500)){
                inMenu=true;
                inInstrucciones=false;

            }


        }

        if(inDificultad){

            dificultad.draw();
            dificultad.act();
            int touched_x= Gdx.input.getX();
            int touched_y= Gdx.input.getY();

            if(rep%200==0){
                personaje();
            }

            if((touched_x<=321 && touched_x>=171) && (touched_y<=140 && touched_y>=90)){
                Personaje.setMover_facil();
            }

            if((touched_x<=715 && touched_x>=495) && (touched_y<=145 && touched_y>=110)){
                Personaje.setMover_normal();
                setFantasmita();
                Fantasmita.setVelocidadNormal();
                Personaje.setMover_normal();
            }

            if((touched_x<=1075 && touched_x>=860) && (touched_y<=147 && touched_y>=96)){
                Personaje.setMover_dificil();
                setFantasmita();
                Fantasmita.setVelocidadNormal();
                Bomba.setVelocidadDificil();
                Personaje.setMover_dificil();
            }

            if((touched_x<=1203 && touched_x>=1120) && (touched_y<=283 && touched_y>=223)){
                inMenu=true;
                inDificultad=false;
                Personaje.setPosIni_inicial();
                Personaje.setInicial();
            }

        }


        if(inSalir){
            System.exit(0);
        }

        rep ++;

    }

    public void agregarBotones(){
        Image b1 = new Image(new Texture("inicio1.png"));
        b1.setY(250);
        b1.setX(400);
        menu_inicial.addActor(b1);

        Image b2 = new Image(new Texture("Instrucciones1.png"));
        b2.setY(130);
        b2.setX(400);
        menu_inicial.addActor(b2);

        Image b3= new Image(new Texture("salir1.png"));
        b3.setY(-5);
        b3.setX(400);
        menu_inicial.addActor(b3);

        Image b4 = new Image (new Texture("dificultad1.png"));
        b4.setY(-80);
        b4.setX(470);
        menu_inicial.addActor(b4);
    }

    public void setFantasmita(){
        mostrarFantasmita=true;
    }
    public void agregarInstrucciones(){
        Image instructions = new Image(new Texture("pantallaInstrucciones.png"));
        instructions.setX(-80);
        instrucciones.addActor(instructions);

        regresar1.setX(900);
        regresar1.setY(-30);

        instrucciones.addActor(regresar1);
    }

    public void dificultad(){
        Image fondo_dificulad= new Image(new Texture("G049_OT000_002A__background.png"));
        dificultad.addActor(fondo_dificulad);

        Image difi1 = new Image(new Texture("dificultad_facil1.png"));
        difi1.setY(400);
        difi1.setX(80);
        dificultad.addActor(difi1);

        Image difi2 = new Image(new Texture("dificultad_normal1.png"));
        difi2.setY(400);
        difi2.setX(440);
        dificultad.addActor(difi2);

        Image difi3= new Image(new Texture("dificultad_dificil1.png"));
        difi3.setY(400);
        difi3.setX(800);
        dificultad.addActor(difi3);

        Image regresar= new Image(new Texture("regresar3.png"));
        regresar.setX(1000);
        regresar.setY(300);
        dificultad.addActor(regresar);

    }


    public void game_over(){
        Image go= new Image(new Texture("game over.png"));
        game_over.addActor(go);

        regresar2.setX(420);
        regresar2.setY(-60);

        game_over.addActor(regresar2);

    }


    boolean colision(Personaje otro_personaje,Personaje otro_personaje2)
    {
        Rectangle r1 = new Rectangle(Bomba.posX+Bomba.width/4,
                Bomba.posY+Bomba.height/4,
                256/2,
                256/2);

        Rectangle rec1 = new Rectangle(Fantasmita.posX+Fantasmita.width/4,
                Fantasmita.posY+Fantasmita.height/4,
                256/2,
                256/2);

        Rectangle r2 = new Rectangle(otro_personaje.getX()+otro_personaje.getWidth()/4,
                otro_personaje.getY()+otro_personaje.getHeight()/4,
               256/2,
                256/2);

        Rectangle rec2 = new Rectangle(otro_personaje2.getX()+otro_personaje2.getWidth()/4,
                otro_personaje2.getY()+otro_personaje2.getHeight()/4,
                256/2,
                256/2);
        System.out.println("Enemigo X:"+rec1.getX()+" Y:"+rec1.getY());
        System.out.println("Personaje X:"+r2.getX()+" Y:"+r2.getHeight());

        return r1.overlaps(r2)||rec1.overlaps(rec2);
    }

    /*boolean colision2(Personaje otro_personaje)
    {
        Rectangle rec1 = new Rectangle(Fantasmita.posX+Fantasmita.width/4,
                Fantasmita.posY+Fantasmita.height/4,
                256/2,
                256/2);
        Rectangle rec2 = new Rectangle(otro_personaje.getX()+otro_personaje.getWidth()/4,
                otro_personaje.getY()+otro_personaje.getHeight()/4,
                256/2,
                256/2);

        System.out.println("Enemigo X:"+rec1.getX()+" Y:"+rec1.getY());
        System.out.println("Personaje X:"+rec2.getX()+" Y:"+rec2.getHeight());

        return rec1.overlaps(rec2);
    }*/




    public void titulo(){
        Titulo t = new Titulo();
        menu_inicial.addActor(t);
    }


    public void tierra (){
        Plataforma p = new Plataforma();
        juego.addActor(p);
    }

    public void fantasmita (){
        Fantasmita f = new Fantasmita(p);
        juego.addActor(f);

    }

    public void bomba(){
        Bomba b = new Bomba(p);
        juego.addActor(b);
    }


    public  void personaje(){
        Personaje per = new Personaje();
        per.setPosIni();
        Personaje.set_Mover();
        dificultad.addActor(per);
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
       p.saltar();
       return true;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
