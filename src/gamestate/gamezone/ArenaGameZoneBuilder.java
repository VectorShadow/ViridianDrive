package gamestate.gamezone;

import gamestate.coordinates.Coordinate;
import gamestate.terrain.TerrainTile;
import gamestate.theme.ViridianDriveTheme;

public class ArenaGameZoneBuilder extends ViridianDriveGameZoneBuilder {

    final Coordinate CENTER;
    final int RADIUS;

    public ArenaGameZoneBuilder(int size, ViridianDriveTheme theme) {
        super(size, theme);
        CENTER = new Coordinate(size / 2, size /  2);
        RADIUS = size / 2 - 2;
    }

    @Override
    protected TerrainTile generateTile(int row, int column) {
        Coordinate tileCoordinate = new Coordinate(column, row);
        //hack! put a gate here
        if (row == CENTER.ROW - RADIUS && column == CENTER.COLUMN) {
            TerrainTile gate = THEME.readTerrain('0');
            setTravelPoints(row, column, gate);
            return gate;
        }
        if (CENTER.distanceTo(tileCoordinate) > RADIUS)
            return THEME.readTerrain('#');
        else {
            double d = BUILD_RANDOM.nextDouble();
            return
                    d < 0.01
                    ? THEME.readTerrain('#')
                    : d < 0.033
                    ? THEME.readTerrain('%')
                    : d < 0.067
                    ? THEME.readTerrain(',')
                    : THEME.readTerrain('.');
        }
    }
}
