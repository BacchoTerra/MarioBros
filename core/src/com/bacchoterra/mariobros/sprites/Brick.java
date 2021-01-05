package com.bacchoterra.mariobros.sprites;

import com.bacchoterra.mariobros.Jogo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import java.util.jar.JarEntry;

public class Brick extends InteractiveTileObject{

    public Brick(World world, Rectangle rectangle, TiledMap map) {
        super(world, rectangle, map);

        fixture.setUserData(this);
        setCategoryFilter(Jogo.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Collision");
        setCategoryFilter(Jogo.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
