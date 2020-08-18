package gamestate.theme;

import gamestate.terrain.ViridianDriveTerrainProperties;

/**
 * A theme for flavoring game zones.
 * Themes exist to provide context for zone builder default patterns.
 */
public abstract class ViridianDriveTheme {
    /**
     * Read an input character and translate it to a set of terrain properties appropriate for this theme.
     */
    public ViridianDriveTerrainProperties readTerrain(char c){
        switch (c) {
            case '.':
                return getFloorTerrain(0);
            case ',':
                return getFloorTerrain(1);
            case ';':
                return getFloorTerrain(2);
                //todo - rubble or other clearable obstacles as features - see dydx
            case '#':
                return getWallTerrain(0);
            case '%':
                return getWallTerrain(1);
            case '&':
                return getWallTerrain(2);
                //todo - doors, chests(or other loot objects) and mineable walls as features - see dydx
            case '~':
                return getSpecialTerrain(0);
            case '=':
                return getSpecialTerrain(1);
            case '*':
                return getSpecialTerrain(2);
                //todo - traps and other hidden objects as features - see dydx
            default:
                return getUntypedTerrain(c);
        }
    }

    //todo - readFeature - see dydx

    /**
     * Return a set of floor type terrain properties for this theme.
     * The general contract is as follows:
     * 0(.) - the standard, most common ground terrain of the theme.
     * 1(,) - the most common flavor terrain of the theme, vegetation, fungi, rubble, etc.
     * 2(;) - a less common flavor terrain of the theme.
     * Note that implementations need not follow this contract if not appropriate for their theme.
     * Additionally, implementations may randomly select terrain properties within a category if desired -
     * for example, if the floor for a specific theme is a mix of barren dust and sparse vegetation, calling
     * getFloorTerrain(0) might provide a 65% chance of returning barren dust and a 35% chance of returning sparse
     * vegetation.
     */
    public abstract ViridianDriveTerrainProperties getFloorTerrain(int terrainIndex);

    /**
     * Return a set of wall type terrain properties for this theme.
     * The general contract is as follows:
     * 0(#) - the standard, most common wall terrain of the theme.
     * 1(%) - the most common flavor terrain of the theme, trees, rock spires, ruined pillars, etc.
     * 2(&) - a less common flavor terrain of the theme.
     * Note that implementations need not follow this contract if not appropriate for their theme.
     * Additionally, implementations may randomly select terrain properties within a category if desired -
     * for example, if the floor for a specific theme is a mix of barren dust and sparse vegetation, calling
     * getFloorTerrain(0) might provide a 65% chance of returning barren dust and a 35% chance of returning sparse
     * vegetation.
     */
    public abstract ViridianDriveTerrainProperties getWallTerrain(int terrainIndex);

    /**
     * Return a set of special terrain properties for this theme.
     * The general contract is as follows:
     * 0(~) - a liquid terrain type such as lakes or rivers.
     * 1(=) - a road terrain type.
     * 2(*) - a theme specific flavor terrain.
     * Note that implementations need not follow this contract if not appropriate for their theme.
     * Additionally, implementations may randomly select terrain properties within a category if desired -
     * for example, if the floor for a specific theme is a mix of barren dust and sparse vegetation, calling
     * getFloorTerrain(0) might provide a 65% chance of returning barren dust and a 35% chance of returning sparse
     * vegetation.
     */
    public abstract ViridianDriveTerrainProperties getSpecialTerrain(int terrainIndex);

    /**
     * Return a set of untyped terrain properties for this theme.
     * This is a catchall category for theme unique terrain which may not fall into the pre-defined categories.
     */
    public abstract ViridianDriveTerrainProperties getUntypedTerrain(char terrainSymbol);
}
