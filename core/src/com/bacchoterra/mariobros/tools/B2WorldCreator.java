package com.bacchoterra.mariobros.tools;

import com.bacchoterra.mariobros.Jogo;
import com.bacchoterra.mariobros.sprites.Brick;
import com.bacchoterra.mariobros.sprites.Coin;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import sun.corba.Bridge;

public class B2WorldCreator {

    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private PolygonShape polygonShape;
    private Body body;


    public B2WorldCreator(World world, TiledMap map) {

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        polygonShape = new PolygonShape();

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Jogo.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Jogo.PPM);
            body = world.createBody(bodyDef);
            polygonShape.setAsBox(rectangle.getWidth() / 2 / Jogo.PPM, rectangle.getHeight() / 2 / Jogo.PPM);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);


        }

        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;


            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Jogo.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Jogo.PPM);

            body = world.createBody(bodyDef);

            polygonShape.setAsBox(rectangle.getWidth() / 2 / Jogo.PPM, rectangle.getHeight() / 2 / Jogo.PPM);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);


        }

        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new Brick(world,rectangle,map);


        }

        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new Coin(world,rectangle,map);


        }

    }
}
