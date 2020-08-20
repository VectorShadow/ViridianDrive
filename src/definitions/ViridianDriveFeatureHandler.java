package definitions;

import gamestate.coordinates.Coordinate;
import gamestate.gameobject.GameActor;
import gamestate.gamezone.GameZone;
import gamestate.terrain.TerrainFeature;

public class ViridianDriveFeatureHandler extends FeatureHandler {
    @Override
    public void interact(GameActor activator, Coordinate coordinate, GameZone gameZone, TerrainFeature terrainFeature) {
        //todo - lots here. this should primarily function on the front end, sending instructions if necessary
    }
}
