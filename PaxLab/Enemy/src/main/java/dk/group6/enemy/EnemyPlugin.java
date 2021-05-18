package dk.group6.enemy;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private String enemyID;
    int[][] spawnPoints = {{46, 629}, {100, 629}, {675, 495}, {46, 629}, {39, 523}, {55, 232}, {125, 211}};

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        
            Entity enemy = createEnemy(gameData);
            enemyID = world.addEntity(enemy);
        
    }

    private Entity createEnemy(GameData gameData) {
        int[] spawnPoint = spawnPoints[3];
        
        Entity enemy = new Enemy();
        
        enemy.add(new LifePart(1));
        enemy.setRadius(4);
        enemy.add(new MovingPart());
        enemy.add(new PositionPart(spawnPoint[0], spawnPoint[1]));
        enemy.add(new SpritePart("assets/enemy.png", this.getClass()));

        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }

}
