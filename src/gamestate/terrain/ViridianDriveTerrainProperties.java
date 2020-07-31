package gamestate.terrain;

public class ViridianDriveTerrainProperties extends TerrainProperties {

    public static final ViridianDriveTerrainProperties EMPTY_TILE =
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_TRANSPARENT,
                    TerrainProperties.MATTER_PERMISSION_FREE
            );
    public static final ViridianDriveTerrainProperties MAP_BOUNDARY =
            new ViridianDriveTerrainProperties(
                    TerrainProperties.ENERGY_PERMISSION_OPAQUE,
                    TerrainProperties.MATTER_PERMISSION_IMPASSABLE
            );

    //todo - more here - probably stuff for the gui, an ImageSource or so
    public ViridianDriveTerrainProperties(int energyPermission, int matterPermission) {
        super(energyPermission, matterPermission);
    }
}
