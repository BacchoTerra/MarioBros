package com.bacchoterra.mariobros.sprites;

import com.bacchoterra.mariobros.Jogo;
import com.bacchoterra.mariobros.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Mario extends Sprite {

    public World world;
    public Body body;
    private TextureRegion marioStand;

    //Animaçao
    public enum State {FALLING, JUMPING, RUNNING, STANDING}


    private State currentState;
    private State previousState;
    private Animation<TextureRegion> marioRun;
    private Animation<TextureRegion> marioJump;
    private float stateTimer;
    private boolean runningRight;

    public Mario(World world, PlayScreen ps) {
        super(ps.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 3, 11, 14, 16);
        setBounds(0, 0, 16 / Jogo.PPM, 16 / Jogo.PPM);
        setRegion(marioStand);

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<>();

        for (int i = 1; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * 16, 11, 16, 16));
        }
        marioRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();


        frames.add(new TextureRegion(getTexture(), 5 * 16, 11, 16, 16));

        marioJump = new Animation<TextureRegion>(0.1f, frames);


    }

    public State getState() {

        if ((body.getLinearVelocity().x > 0 || body.getLinearVelocity().x < 0) && body.getLinearVelocity().y == 0) {
            return State.RUNNING;
        } else if (body.getLinearVelocity().y > 0 || body.getLinearVelocity().y < 0) {
            return State.JUMPING;
        } else {
            return State.STANDING;
        }
    }

    public TextureRegion getFrame(float dt) {

        currentState = getState();
        TextureRegion tr;

        switch (currentState) {

            case JUMPING:
                tr = marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                tr = marioRun.getKeyFrame(stateTimer, true);
                break;
            default:
                tr = marioStand;
        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !tr.isFlipX()) {
            tr.flip(true, false);
            runningRight = false;
        } else if ((body.getLinearVelocity().x > 0 || runningRight) && tr.isFlipX()) {
            tr.flip(true, false);
            runningRight = true;

        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;


        return tr;


    }

    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    private void defineMario() {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(64 / Jogo.PPM, 32 / Jogo.PPM);
        body = world.createBody(bodyDef);

        circleShape.setRadius(6f / Jogo.PPM);

        fixtureDef.filter.categoryBits = Jogo.MARIO_BIT;
        fixtureDef.filter.maskBits = Jogo.BRICK_BIT |Jogo.DEFAULT_BIT |Jogo.COIN_BIT;

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/Jogo.PPM,6/Jogo.PPM),new Vector2(2/Jogo.PPM,6/Jogo.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData("head");

    }

}
