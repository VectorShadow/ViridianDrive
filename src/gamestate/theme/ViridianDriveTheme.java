package gamestate.theme;

import gamestate.terrain.TerrainTile;

/**
 * A theme for flavoring game zones.
 * Themes exist to provide context for zone builder default patterns.
 */
public abstract class ViridianDriveTheme {

    /**
     * Read an input character and return a new terrain tile with an id corresponding to the desired properties.
     */
    public TerrainTile readTerrain(char c){
        short s;
        switch (c) {
            case '.':
                s = getFloorTerrain(0);
                break;
            case ',':
                s = getFloorTerrain(1);
                break;
            case ';':
                s = getFloorTerrain(2);
                break;
                //todo - rubble or other clearable obstacles as features - see dydx
            case '#':
                s = getWallTerrain(0);
                break;
            case '%':
                s = getWallTerrain(1);
                break;
            case '&':
                s = getWallTerrain(2);
                break;
                //todo - doors, chests(or other loot objects) and mineable walls as features - see dydx
            case '~':
                s = getSpecialTerrain(0);
                break;
            case '=':
                s = getSpecialTerrain(1);
                break;
            case '<':
                s = getSpecialTerrain(2);
                break;
            case '>':
                s = getSpecialTerrain(3);
                break;
            case '*':
                s = getSpecialTerrain(4);
                break;
                //todo - traps and other hidden objects as features - see dydx
            default:
                s = getUntypedTerrain(c);
        }
        return new TerrainTile(s);
    }

    //todo - readFeature - see dydx

    /**
     * Return a terrain tile ID corresponding to a set of floor type terrain properties for this theme.
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
    protected abstract short getFloorTerrain(int terrainIndex);

    /**
     * Return a terrain tile ID corresponding to a set of wall type terrain properties for this theme.
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
    protected abstract short getWallTerrain(int terrainIndex);

    /**
     * Return a terrain tile ID corresponding to a set of special terrain properties for this theme.
     * The general contract is as follows:
     * 0(~) - a liquid terrain type such as lakes or rivers.
     * 1(=) - a road terrain type.
     * 2(<) - a passage to a shallower floor, or to a connected world location.
     * 3(>) - a passage to a deeper floor, or to a connected world location.
     * 4(*) - a theme specific flavor terrain.
     * Note that implementations need not follow this contract if not appropriate for their theme.
     * Additionally, implementations may randomly select terrain properties within a category if desired -
     * for example, if the floor for a specific theme is a mix of barren dust and sparse vegetation, calling
     * getFloorTerrain(0) might provide a 65% chance of returning barren dust and a 35% chance of returning sparse
     * vegetation.
     */
    protected abstract short getSpecialTerrain(int terrainIndex);

    /**
     * Return a terrain tile ID corresponding to a set of untyped terrain properties for this theme.
     * This is a catchall category for theme unique terrain which may not fall into the pre-defined categories.
     */
    protected abstract short getUntypedTerrain(char terrainSymbol);
}
