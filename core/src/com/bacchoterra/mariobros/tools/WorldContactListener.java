package com.bacchoterra.mariobros.tools;

import com.bacchoterra.mariobros.sprites.InteractiveTileObject;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {

            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixB ? fixA : fixB;

            if (object.getUserData() != null &&
                    InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {

                ((InteractiveTileObject) object.getUserData()).onHeadHit();

            }

        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
