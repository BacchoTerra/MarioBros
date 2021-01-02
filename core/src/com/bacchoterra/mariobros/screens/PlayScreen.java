package com.bacchoterra.mariobros.screens;

import com.bacchoterra.mariobros.Jogo;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

    //Main class
    public Jogo jogo;

    //Camer managment
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public PlayScreen(Jogo jogo) {
        this.jogo = jogo;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Jogo.V_WIDTH/Jogo.PPM,Jogo.V_HEIGHT/Jogo.PPM);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        jogo.batch.setProjectionMatrix(gameCam.combined);


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
