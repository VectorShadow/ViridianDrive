package gamestate;

import gamestate.terrain.TerrainTile;

public class TestGameZoneGenerator extends GameZoneGenerator {
    @Override
    public GameZone generate() {
        GameZone gz = new GameZone(32, 32);
        for (int i = 0; i < gz.ROWS; ++i) {
            for (int j = 0; j < gz.COLUMNS; ++j) {
                gz.TERRAIN[i][j] =
                        i == 0 || i == gz.ROWS - 1 || j == 0 || j == gz.COLUMNS - 1 ?
                                new TerrainTile(1) : //map boundaries on the perimeter
                                new TerrainTile(0); //empty space to fill
            }
        }
        return gz;
    }
}
