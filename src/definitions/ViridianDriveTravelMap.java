package definitions;

import gamestate.coordinates.ZoneCoordinate;
import util.Direction;

import static definitions.ViridianDriveGameZoneGenerator.*;

public class ViridianDriveTravelMap extends TravelMap {
    //todo - define ZoneCoordinates for < > passages in pre-defined zones by tile coordinates,
    // such that issuing a move up or move down command will check the player's tile coordinate and decide whether
    // to change depths, change location IDs, or do nothing
    public ZoneCoordinate travel(Direction direction, ZoneCoordinate origin) {
        switch (origin.LOCATION_ID) {
            case WORLD_LOCATION_STEADROCK_SETTLEMENT:
                switch (direction) {
                    case SOUTH:
                        return new ZoneCoordinate(WORLD_LOCATION_STEADROCK_ARENA, 0, 0);
                }
                break;
            case WORLD_LOCATION_STEADROCK_ARENA:
                switch (direction) {
                    case SOUTH:
                        return new ZoneCoordinate(WORLD_LOCATION_STEADROCK_SETTLEMENT, 0, 0);
                }
                break;
        }
        throw new IllegalArgumentException(
                "Valid destination not found for Zone Coordinate " + origin + " in direction " + direction
        );
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
