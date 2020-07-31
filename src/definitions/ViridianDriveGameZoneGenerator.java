package definitions;

import gamestate.gamezone.TestGameZoneBuilder;
import gamestate.coordinates.ZoneCoordinate;
import gamestate.gamezone.GameZoneBuilder;

public class ViridianDriveGameZoneGenerator extends GameZoneGenerator {
    @Override
    public GameZoneBuilder getGameZoneBuilder(ZoneCoordinate zc) {
        //todo - switch on zc.id, then on zc.depth if necessary, to return specific builders
        return new TestGameZoneBuilder();
    }
}
