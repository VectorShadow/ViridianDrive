package definitions;

import gamestate.gamezone.ArenaGameZoneBuilder;
import gamestate.coordinates.ZoneCoordinate;
import gamestate.gamezone.GameZoneBuilder;
import gamestate.gamezone.PreDefinedGameZoneBuilder;
import gamestate.theme.VerdigrisWasteTheme;
import gamestate.theme.VerdigrisWasteTownTheme;

import java.util.ArrayList;

public class ViridianDriveGameZoneGenerator extends GameZoneGenerator {

    public static final int WORLD_LOCATION_STEADROCK_SETTLEMENT = ZoneCoordinate.ORIGIN_ZONE_ID; //0
    public static final int WORLD_LOCATION_STEADROCK_ARENA = 1;

    private static final String[] PRE_DEFINED_FEATURES_STEADROCK_SETTLEMENT = {
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
            "#...............4.,...&&&&&&&..#",
            "################################",
    };

    private static final String[] PRE_DEFINED_TERRAIN_STEADROCK_SETTLEMENT = {
            "################################",
            "#...............==.....,,%%.%%.#",
            "#...&&&&..,.....==.....,.,.%%%,#",
            "#...&&&&.=========....,.,,..,%.#",
            "#...&&&&...,....==....&&&&.,..,#",
            "#...............=====.&&&&..%..#",
            "#...,.....%.....==....&&&&...,.#",
            "#..............==....&&&&&.....#",
            "#....&&&&.,...==...............#",
            "#....&&&&.....==....,..&&&&&&&.#",
            "#....&&&&.....==.......&&..&&&.#",
            "#....&......====.........==....#",
            "#......=...=...==....=========.#",
            "#.......===.....===============#",
            "#..,......=......====..=...=..=#",
            "#...%%..,..............=...=...#",
            "#.,.....,&&&....,..,.....&.....#",
            "#..,.%...&&&......,%..&&&&&&&..#",
            "#...............4.,...&&&&&&&..#",
            "################################",
    };

    @Override
    public GameZoneBuilder getGameZoneBuilder(ZoneCoordinate zc) {
        switch (zc.LOCATION_ID) {
            case WORLD_LOCATION_STEADROCK_SETTLEMENT:
                return new PreDefinedGameZoneBuilder(
                        new ArrayList<>(),
                        PRE_DEFINED_FEATURES_STEADROCK_SETTLEMENT,
                        PRE_DEFINED_TERRAIN_STEADROCK_SETTLEMENT,
                        new VerdigrisWasteTownTheme()
                );
            case WORLD_LOCATION_STEADROCK_ARENA:
                return new ArenaGameZoneBuilder(63, new VerdigrisWasteTheme());
                default:
                    throw new IllegalArgumentException("Unable to find a builder definition for " + zc +
                            " - location ID " + zc.LOCATION_ID + " not supported.");
        }
    }
}
