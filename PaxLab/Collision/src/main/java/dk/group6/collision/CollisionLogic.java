/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.collision;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponPart;
import dk.group6.common.services.IPostEntityProcessingService;

/**
 *
 * @author Yonus
 */
public class CollisionLogic implements IPostEntityProcessingService {

    TiledMapTileLayer sdf;

    @Override
    public void process(GameData gameData, World world) {
        entityCollision(world);
        wallCollision(world);
    }

    public void entityCollision(World world) {
        for (Entity entity : world.getEntities()) {
            SpritePart spritePart = entity.getPart(SpritePart.class);
            Rectangle r1 = spritePart.getSprite().getBoundingRectangle();   
            
            for (Entity entity1 : world.getEntities()) {
                SpritePart spritePart1 = entity1.getPart(SpritePart.class);
                Rectangle r2 = spritePart1.getSprite().getBoundingRectangle();

                if (entity.getID().equals(entity1.getID()) && spritePart.getSprite().equals(spritePart1.getSprite())) {
                    continue;
                }
                if (r1.overlaps(r2)) {
                    System.out.println("Collision between entities!");
                    // Player & Weapon
                    if ((entity1.getClass().toString().contains("Player") && entity.getClass().toString().contains("Weapon")) || (entity.getClass().toString().contains("Player") && entity1.getClass().toString().contains("Weapon"))){
                        System.out.println("PLAYER touches a WEAPON");
                    }
                    // Player & Enemy
                    if ((entity1.getClass().toString().contains("Player") && entity.getClass().toString().contains("Enemy")) || (entity.getClass().toString().contains("Player") && entity1.getClass().toString().contains("Enemy"))){
                        System.out.println("PLAYER touches a ENEMY");
                    }
                    // Enemy & Bullet
                    if ((entity1.getClass().toString().contains("Bullet") && entity.getClass().toString().contains("Enemy")) || (entity.getClass().toString().contains("Bullet") && entity1.getClass().toString().contains("Enemy"))){
                        System.out.println("BULLET touches a ENEMY");
                        // NOT IMPLEMENTED YET
                    }
                }
            }
        }
    }
    
    public void wallCollision(World world) {
        for (Entity entity : world.getEntities()) {
            
            sdf = world.getMapTileLayer();
            PositionPart pp = entity.getPart(PositionPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);
            
            // Bottom    
            if (sdf.getCell(((int)spritePart.getSpriteLeftBottom()[0]+1)/45, (int)spritePart.getSpriteLeftBottom()[1]/45).getTile().getProperties().containsKey("Wall") ||
                    sdf.getCell(((int)spritePart.getSpriteRightBottom()[0]-1)/45, (int)spritePart.getSpriteLeftBottom()[1]/45).getTile().getProperties().containsKey("Wall")) {
                pp.setY(pp.getY()+1);
            }
            
            // Right
            if (sdf.getCell((int)spritePart.getSpriteRightBottom()[0]/45, ((int)spritePart.getSpriteRightBottom()[1]+1)/45).getTile().getProperties().containsKey("Wall") ||
                    sdf.getCell((int)spritePart.getSpriteRightTop()[0]/45, ((int)spritePart.getSpriteRightTop()[1]-1)/45).getTile().getProperties().containsKey("Wall")) {
                pp.setX(pp.getX()-1);
            }
            
            // Top
            if (sdf.getCell(((int)spritePart.getSpriteLeftTop()[0]+1)/45, (int)spritePart.getSpriteLeftTop()[1]/45).getTile().getProperties().containsKey("Wall") ||
                    sdf.getCell(((int)spritePart.getSpriteRightTop()[0]-1)/45, ((int)spritePart.getSpriteRightTop()[1])/45).getTile().getProperties().containsKey("Wall")) {
                pp.setY(pp.getY()-1);
            }
            
            // Left
            if (sdf.getCell((int)spritePart.getSpriteLeftTop()[0]/45, ((int)spritePart.getSpriteLeftTop()[1]-1)/45).getTile().getProperties().containsKey("Wall") ||
                    sdf.getCell((int)spritePart.getSpriteLeftBottom()[0]/45, ((int)spritePart.getSpriteLeftBottom()[1]+1)/45).getTile().getProperties().containsKey("Wall")) {
                pp.setX(pp.getX()+1);
            }            
        }
    }
}
