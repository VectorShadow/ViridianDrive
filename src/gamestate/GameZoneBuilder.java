package gamestate;

import gamestate.coordinates.ZoneCoordinate;

public class GameZoneBuilder {
    public GameZone build(ZoneCoordinate zc) {
        return getGenerator(zc.LOCATION_ID, zc.DEPTH).generate();
    }
    private GameZoneGenerator getGenerator(int zoneID, int depth) {
        //todo - different Generators for different IDs and even depths
        return new TestGameZoneGenerator();
    }
}
