package definitions;

import gamestate.coordinates.ZoneCoordinate;
import util.Direction;

public class ViridianDriveTravelMap extends TravelMap {

    public static final int WORLD_LOCATION_STEADROCK_SETTLEMENT = ZoneCoordinate.ORIGIN_ZONE_ID; //0
    public static final int WORLD_LOCATION_STEADROCK_ARENA = 1;

    @Override
    public ZoneCoordinate climbDown(ZoneCoordinate origin) {
        return null; //todo
    }

    @Override
    public ZoneCoordinate climbUp(ZoneCoordinate origin) {
        return null; //todo
    }

    @Override
    public boolean isStaticLocation(ZoneCoordinate zoneCoordinate) {
        switch (zoneCoordinate.LOCATION_ID) {
            case WORLD_LOCATION_STEADROCK_SETTLEMENT: return true;
            case WORLD_LOCATION_STEADROCK_ARENA: return false;
            default: throw new IllegalArgumentException("Zone Coordinate " + zoneCoordinate + " not found.");
        }
    }

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
                    case NORTH:
                        return new ZoneCoordinate(WORLD_LOCATION_STEADROCK_SETTLEMENT, 0, 0);
                }
                break;
        }
        throw new IllegalArgumentException(
                "Valid destination not found for Zone Coordinate " + origin + " in direction " + direction
        );
    }
}
