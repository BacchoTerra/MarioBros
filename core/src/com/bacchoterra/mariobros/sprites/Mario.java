package com.bacchoterra.mariobros.sprites;

import com.bacchoterra.mariobros.Jogo;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Mario extends Sprite {

    public World world;
    public Body body;

    public Mario (World world){

        this.world = world;
        defineMario();

    }

    private void defineMario() {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(64/Jogo.PPM,32/Jogo.PPM);
        body = world.createBody(bodyDef);

        circleShape.setRadius(10f/ Jogo.PPM);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

    }

}
