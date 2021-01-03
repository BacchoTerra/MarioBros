package com.bacchoterra.mariobros.screens;

import com.bacchoterra.mariobros.Jogo;
import com.bacchoterra.mariobros.scenes.Hud;
import com.bacchoterra.mariobros.sprites.Mario;
import com.bacchoterra.mariobros.tools.B2WorldCreator;
import com.bacchoterra.mariobros.tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
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

    //Box2D
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    //Player
    private Mario player;

    //Textures
    private TextureAtlas atlas;


    public PlayScreen(Jogo jogo) {
        this.jogo = jogo;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Jogo.V_WIDTH / Jogo.PPM, Jogo.V_HEIGHT / Jogo.PPM, gameCam);

        hud = new Hud(jogo.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / Jogo.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2f, 0);

        world = new World(new Vector2(0, -10), true);
        box2DDebugRenderer = new Box2DDebugRenderer();


        new B2WorldCreator(world,map);

        atlas = new TextureAtlas("mario_and_enemies.pack");

        player = new Mario(world,this);

        world.setContactListener(new WorldContactListener());



    }

    public TextureAtlas getAtlas(){
        return this.atlas;
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
        box2DDebugRenderer.render(world, gameCam.combined);

        jogo.batch.setProjectionMatrix(gameCam.combined);
        jogo.batch.begin();
        player.draw(jogo.batch);
        jogo.batch.end();


        jogo.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        hud.dispose();

    }

    private void update(float dt) {
        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        player.update(dt);
        gameCam.position.x = player.body.getPosition().x;
        gameCam.update();
        mapRenderer.setView(gameCam);


    }

    private void handleInput(float dt) {

        /*if (Gdx.input.isTouched()){

            vector3.set(Gdx.input.getX(), Gdx.input.getY(),0);

            Gdx.app.log("TouchCoord","X pos -> " + vector3.x + " // " + "Y pos -> " + vector3.y);


        }

         */

        if (Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 && player.body.getLinearVelocity().x <= 2f && Gdx.input.getY() > Gdx.graphics.getHeight() /2) {

            player.body.applyLinearImpulse(new Vector2(0.1f,0),player.body.getWorldCenter(),true);

            Gdx.app.log("TouchCoord", "X pos -> " + Gdx.input.getX() + " // " + "Y pos -> " + Gdx.input.getY());
        }

        if (Gdx.input.isTouched() && Gdx.input.getX() < Gdx.graphics.getWidth() / 2 && player.body.getLinearVelocity().x >= -2f && Gdx.input.getY() > Gdx.graphics.getHeight() /2) {

            player.body.applyLinearImpulse(new Vector2(-0.1f,0),player.body.getWorldCenter(),true);

            Gdx.app.log("TouchCoord", "X pos -> " + Gdx.input.getX() + " // " + "Y pos -> " + Gdx.input.getY());

        }

        if (Gdx.input.justTouched() && Gdx.input.getY() < Gdx.graphics.getHeight() /2){

            player.body.applyLinearImpulse(new Vector2(0,4f),player.body.getWorldCenter(),true);

        }


    }
}
