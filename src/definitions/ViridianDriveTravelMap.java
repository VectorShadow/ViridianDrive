package definitions;

import gamestate.coordinates.ZoneCoordinate;
import util.Direction;

public class ViridianDriveTravelMap extends TravelMap {
    //todo - define ZoneCoordinates for < > passages in pre-defined zones by tile coordinates,
    // such that issuing a move up or move down command will check the player's tile coordinate and decide whether
    // to change depths, change location IDs, or do nothing
    public ZoneCoordinate travel(Direction direction, ZoneCoordinate origin) {
        return null; //todo - return the ZoneCoordinate of the world location in the specified direction from the origin. handle instances?
    }

    @Override
    public ZoneCoordinate climbDown(ZoneCoordinate origin) {
        return null; //todo
    }

    @Override
    public ZoneCoordinate climbUp(ZoneCoordinate origin) {
        return null; //todo
    }
}
