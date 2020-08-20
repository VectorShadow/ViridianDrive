package definitions;

import gamestate.gamezone.PreDefinedGameZoneBuilder;
import gamestate.coordinates.ZoneCoordinate;
import gamestate.gamezone.GameZoneBuilder;
import gamestate.theme.VerdigrisWasteTownTheme;

import java.util.ArrayList;

public class ViridianDriveGameZoneGenerator extends GameZoneGenerator {

    //todo - define ZoneCoordinates for < > passages in pre-defined zones by tile coordinates,
    // such that issuing a move up or move down command will check the player's tile coordinate and decide whether
    // to change depths, change location IDs, or do nothing

    public static final int WORLD_LOCATION_STEADROCK_SETTLEMENT = ZoneCoordinate.ORIGIN_ZONE_ID;

    private static final String[] PRE_DEFINED_TERRAIN_STEADROCK_SETTLEMENT = {
            "################################",
            "#...............==.....,,%%.%%.#",
            "#...&&&&b.,.....==.....,.,.%%%,#",
            "#...&&&&b=========....,.,,..,%.#",
            "#...&&&&b..,....==...H&&&&.,..,#",
            "#...............=====H&&&&..%..#",
            "#...,.....%.....==...H&&&&...,.#",
            "#..............==....&&&&&.....#",
            "#....&&&&.,...==...............#",
            "#....&&&&.....==....,..&&&&&&&.#",
            "#....&&&&.....==.......&&SS&&&.#",
            "#....&TTT...====.........==....#",
            "#......=...=...==....=========.#",
            "#.......===.....===============#",
            "#..,......=......====..=...=..=#",
            "#...%%..,vvv...........=...=...#",
            "#.,.....,&&&....,..,..WWW&ggg..#",
            "#..,.%...&&&......,%..&&&&&&&..#",
            "#.................,...&&&&&&&..#",
            "################################",
    };

    @Override
    public GameZoneBuilder getGameZoneBuilder(ZoneCoordinate zc) {
        switch (zc.LOCATION_ID) {
            case (WORLD_LOCATION_STEADROCK_SETTLEMENT):
                return new PreDefinedGameZoneBuilder(new ArrayList<>(), PRE_DEFINED_TERRAIN_STEADROCK_SETTLEMENT, new VerdigrisWasteTownTheme());
                default:
                    throw new IllegalArgumentException("Unable to find a builder definition for " + zc +
                            " - location ID " + zc.LOCATION_ID + " not supported.");
        }
    }
}
