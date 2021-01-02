package com.bacchoterra.mariobros.screens;

import com.bacchoterra.mariobros.Jogo;
import com.bacchoterra.mariobros.scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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

    BodyDef bodyDef;
    FixtureDef fixtureDef;
    PolygonShape polygonShape;
    Body body;


    public PlayScreen(Jogo jogo) {
        this.jogo = jogo;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Jogo.V_WIDTH / Jogo.PPM, Jogo.V_HEIGHT / Jogo.PPM, gameCam);

        hud = new Hud(jogo.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / Jogo.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2f, 0);

        world = new World(new Vector2(0, 0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygonShape = new PolygonShape();

        criarObjetos();


    }

    private void criarObjetos() {

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2) /Jogo.PPM,(rectangle.getY() + rectangle.getHeight()/2) /Jogo.PPM);
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rectangle.getWidth()/2 / Jogo.PPM,rectangle.getHeight()/2/ Jogo.PPM);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);


        }

        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;


            bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2) /Jogo.PPM,(rectangle.getY() + rectangle.getHeight()/2) /Jogo.PPM);

            body = world.createBody(bodyDef);

            polygonShape.setAsBox(rectangle.getWidth()/2 / Jogo.PPM,rectangle.getHeight()/2/ Jogo.PPM);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);


        }


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
        box2DDebugRenderer.render(world,gameCam.combined);


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

    }

    private void update(float dt) {
        handleInput(dt);
        gameCam.update();
        mapRenderer.setView(gameCam);


    }

    private void handleInput(float dt) {

        /*if (Gdx.input.isTouched()){

            vector3.set(Gdx.input.getX(), Gdx.input.getY(),0);

            Gdx.app.log("TouchCoord","X pos -> " + vector3.x + " // " + "Y pos -> " + vector3.y);


        }

         */

        if (Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() / 2) {
            gameCam.position.x += 7 * dt;
            Gdx.app.log("TouchCoord", "X pos -> " + Gdx.input.getX() + " // " + "Y pos -> " + Gdx.input.getY());
        }

        if (Gdx.input.isTouched() && Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
            gameCam.position.x -= 7 * dt;
            Gdx.app.log("TouchCoord", "X pos -> " + Gdx.input.getX() + " // " + "Y pos -> " + Gdx.input.getY());

        }


    }
}
