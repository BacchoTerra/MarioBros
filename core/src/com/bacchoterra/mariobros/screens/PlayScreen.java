package com.bacchoterra.mariobros.screens;

import com.bacchoterra.mariobros.Jogo;
import com.bacchoterra.mariobros.scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

    //Main class
    public Jogo jogo;

    //Camer managment
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    //Hud
    private Hud hud;

    public PlayScreen(Jogo jogo) {
        this.jogo = jogo;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Jogo.V_WIDTH/Jogo.PPM,Jogo.V_HEIGHT/Jogo.PPM);

        hud = new Hud(jogo.batch);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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
}
