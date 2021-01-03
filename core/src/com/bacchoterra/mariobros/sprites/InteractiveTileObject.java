package com.bacchoterra.mariobros.sprites;

import com.bacchoterra.mariobros.Jogo;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class InteractiveTileObject {

    private World world;
    private TiledMap tiledMap;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;

    protected Fixture fixture;

    public InteractiveTileObject(World world,Rectangle rectangle,TiledMap map) {

        this.world = world;
        this.bounds = rectangle;
        this.tiledMap = map;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth()/2) / Jogo.PPM,(bounds.getY() + bounds.getHeight()/2) / Jogo.PPM );

        body = world.createBody(bodyDef);

        polygonShape.setAsBox(bounds.getWidth()/2/Jogo.PPM,bounds.getHeight()/2/Jogo.PPM);
        fixtureDef.shape = polygonShape;
        fixture = body.createFixture(fixtureDef);

    }

    public abstract void onHeadHit();
}
