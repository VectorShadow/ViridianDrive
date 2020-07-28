package gamestate;

public class TestGameZoneGenerator extends GameZoneGenerator {
    @Override
    public GameZone generate() {
        GameZone gz = new GameZone(32, 32);
        for (int i = 0; i < gz.ROWS; ++i) {
            for (int j = 0; j < gz.COLUMNS; ++j) {
                gz.terrainMap[i][j] = new TerrainTile(0);
            }
        }
        return gz;
    }
}
