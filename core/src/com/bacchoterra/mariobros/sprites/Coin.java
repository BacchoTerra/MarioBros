package com.bacchoterra.mariobros.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Coin extends InteractiveTileObject{

    public Coin(World world, Rectangle rectangle, TiledMap map) {
        super(world, rectangle, map);
    }
}
