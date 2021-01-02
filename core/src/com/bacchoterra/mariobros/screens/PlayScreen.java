package com.bacchoterra.mariobros.screens;

import com.bacchoterra.mariobros.Jogo;
import com.bacchoterra.mariobros.scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

    //Main class
    public Jogo jogo;

    //Camera management
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    //Hud
    private Hud hud;

    //Map
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    //TouchPosition
    private Vector3 vector3;

    public PlayScreen(Jogo jogo) {
        this.jogo = jogo;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Jogo.V_WIDTH/Jogo.PPM,Jogo.V_HEIGHT/Jogo.PPM,gameCam);

        hud = new Hud(jogo.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map,1/Jogo.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2f,0);

        vector3 = new Vector3();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();


        jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void update(float dt){
        handleInput(dt);
        gameCam.update();
        mapRenderer.setView(gameCam);


    }

    private void handleInput(float dt) {

        if (Gdx.input.justTouched()){

            vector3.set(Gdx.input.getX(), Gdx.input.getY(),0);
            gamePort.unproject(vector3);

            Gdx.app.log("sexo",String.valueOf((int)vector3.x));

        }

       /* if (Gdx.input.isTouched() && vector3.x >1){
            gameCam.position.x += 10 *dt;
        }

        if (Gdx.input.isTouched() && vector3.x <=1){
            gameCam.position.x -= 10 *dt;
        }

        */

    }
}
