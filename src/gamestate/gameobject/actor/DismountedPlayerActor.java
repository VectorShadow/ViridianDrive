package gamestate.gameobject.actor;

import event.Event;
import gamestate.terrain.TerrainProperties;
import util.Direction;

import java.util.ArrayList;

public class DismountedPlayerActor extends ViridianDriveActor {

    @Override
    public int getMovementAccess() {
        return TerrainProperties.MATTER_PERMISSION_OBSTACLE;
    }

    @Override
    public int getMovementSpeed() {
        return 8;
    }

    @Override
    public double getTurningSpeed() {
        return Math.PI / 4;
    }

    @Override
    public ArrayList<Event> scheduleEvents() {
        ArrayList<Event> events = super.scheduleEvents();
        //todo - more stuff here?
        return events;
    }

    /**
     * These vision arcs correspond to good frontal vision, diminishing toward the periphery.
     */
    @Override
    public double[] getVisionArcs() {
        double[] arcs = new double[ARC_LENGTH];
        System.arraycopy(NO_VISION, 0, arcs, 0, ARC_LENGTH);
        //find the direction this actor is facing
        Direction facingDirection = Direction.toDirection(getFacing());
        //ensure we can see our own tile
        arcs[Direction.SELF.ordinal()] = 1.0;
        //optimal vision straight ahead
        arcs[facingDirection.ordinal()] = getVisionPower();
        Direction nextClockwiseArc = facingDirection;
        Direction nextCounterClockwiseArc = facingDirection;
        //rotate to either side - note that the visual power of the arc decreases significantly the farther we rotate
        for (int i = 0; i < 3; ++i) {
            nextClockwiseArc = nextClockwiseArc.rotateClockwise();
            arcs[nextClockwiseArc.ordinal()] = getVisionPower() / (1.0 + i);
            nextCounterClockwiseArc = nextCounterClockwiseArc.rotateCounterClockwise();
            arcs[nextCounterClockwiseArc.ordinal()] = getVisionPower() / (1.0 + i);
        }
        //no rear arc at all - we cannot see directly behind us
        return arcs;
    }

    @Override
    public double getVisionPower() {
        //todo - time of day and weather effects? pilot wound conditions?
        return 7.5;
    }
}
