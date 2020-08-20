package gamestate.gamezone;

import gamestate.gameobject.actor.ViridianDriveActor;
import gamestate.terrain.TerrainTile;
import gamestate.theme.ViridianDriveTheme;

import java.util.ArrayList;

public class PreDefinedGameZoneBuilder extends ViridianDriveGameZoneBuilder {

    private final ArrayList<ViridianDriveActor> ACTORS;
    private final String[] FEATURES;
    private final String[] TERRAIN;
    private final ViridianDriveTheme THEME;

    public PreDefinedGameZoneBuilder(
            ArrayList<ViridianDriveActor> actors,
            String[] features,
            String[] terrain,
            ViridianDriveTheme theme
    ) {
        super(terrain.length + 2, terrain[0].length() + 2, theme);
        ACTORS = actors;
        FEATURES = features;
        TERRAIN = terrain;
        THEME = theme;
    }

    @Override
    protected TerrainTile generateTile(int row, int column) {
        TerrainTile terrainTile = THEME.readTerrain(TERRAIN[row - 1].charAt(column - 1));
        terrainTile.terrainFeature = THEME.readFeature(FEATURES[row - 1].charAt(column - 1));
        return terrainTile;
    }
}
