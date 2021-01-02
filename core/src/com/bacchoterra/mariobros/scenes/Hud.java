package com.bacchoterra.mariobros.scenes;

import com.bacchoterra.mariobros.Jogo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class Hud implements Disposable {

    public Stage stage;
    public Viewport viewport;

    private Label timeLabel,worldLabel,scoreLabel;


    public Hud(SpriteBatch sb) {

        viewport = new FitViewport(Jogo.V_WIDTH,Jogo.V_HEIGHT,new OrthographicCamera());

        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        initLabels();

        table.add(timeLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);



    }

    private void initLabels(){

        timeLabel = new Label("300", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label("45", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
