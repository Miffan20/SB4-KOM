package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;

/**
 *
 * @author jcs
 */
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            //setting random enemy movement
            Random rand = new Random();

            //rng to get next random float
            float rng = rand.nextFloat();
            System.out.println(rng);

            //setting forward movement if rng is bigger than 0.1 and smaller than 0.9
            if (rng > 0.1f && rng < 0.9f) {
                movingPart.setUp(true);
            }
            //steering left if rng is smaller than 0.2
            if (rng < 0.2f){
                movingPart.setLeft(true);
            }

            //steering right if rng is bigger than 0.8
            if (rng > 0.8f){
                movingPart.setRight(true);
            }

            //therefore it moves forward between 0.1-0.9. steers left is rng is between 0-0.2 and steers right if rng is between 0.8 and 1





            
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);

            //these are needed in order to
            movingPart.setRight(false);
            movingPart.setLeft(false);
            movingPart.setUp(false);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 4);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 4);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 15);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 15);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 4);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 4);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
