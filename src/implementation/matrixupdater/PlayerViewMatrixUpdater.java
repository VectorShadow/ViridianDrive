package implementation.matrixupdater;

import definitions.DefinitionsManager;
import frontend.io.GUIConstants;
import gamestate.coordinates.Coordinate;
import gamestate.gamezone.GameZone;
import gamestate.terrain.TerrainProperties;
import gamestate.terrain.ViridianDriveTerrainProperties;
import images.ImageMatrix;
import images.ImageSource;
import images.TextImageSource;
import user.PlayerSession;
import util.Direction;

import java.awt.*;
import java.util.ArrayList;

public class PlayerViewMatrixUpdater extends MatrixUpdater {
    private static final int SUPPORTED_LAYERS = 2;

    private static ArrayList<Coordinate> visibleTiles = new ArrayList<>();
    //todo - figure out memory

    public PlayerViewMatrixUpdater() {
        super(SUPPORTED_LAYERS);
    }

    @Override
    protected ImageMatrix doUpdate() {
        ImageMatrix imageMatrix = LAYERS[currentLayer];
        if (PlayerSession.getActor() != null && PlayerSession.getActor().getAt() != null) {
            updateVisibleTiles();
            for (int i = 0; i < imageMatrix.getMatrixHeight(); ++i) {
                for (int j = 0; j < imageMatrix.getMatrixWidth(); ++j) {
                    imageMatrix.set(
                            i,
                            j,
                            imageAt(j, i)
                    );
                }
            }
        }
        return imageMatrix;
    }

    private ImageSource imageAt(int viewCol, int viewRow) {
        GameZone gameZone = GameZone.frontEnd;
        Coordinate gameZoneCoordinate = viewToGameZone(viewCol, viewRow);
        int x = gameZoneCoordinate.COLUMN;
        int y = gameZoneCoordinate.ROW;
        if (
                !visibleTiles.contains(gameZoneCoordinate) ||
                        x < 0 ||
                        x >= gameZone.countColumns() ||
                        y < 0 ||
                        y >= gameZone.countRows()
        )
            return null;
        switch (currentLayer) {
            case 0: //terrain layer
                return (
                        (ViridianDriveTerrainProperties)DefinitionsManager.
                                getTerrainLookup().
                                getProperties(
                                        gameZone.tileAt(gameZoneCoordinate)
                                )
                ).getImageSource();
            case 1: //for now, actor layer. if we had items, features, etc., those should draw before actors
                //todo - this is a hack, we simply draw an @ if some actor is here
                return gameZone.tileAt(gameZoneCoordinate).actorList.isEmpty() ? null : new TextImageSource(Color.BLACK, Color.WHITE, '@');
            //todo - more cases here, projectiles probably
                default:
                    throw new IllegalStateException();
        }
    }

    private Coordinate viewToGameZone(int viewCol, int viewRow) {
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        int x = playerAt.COLUMN;
        int y = playerAt.ROW;
        int regionMidPointX = GUIConstants.REGION_VIEW_WIDTH / 2;
        int regionMidPointY = GUIConstants.REGION_VIEW_HEIGHT / 2;
        int xOffset = viewCol - regionMidPointX;
        int yOffset = viewRow - regionMidPointY;
        return new Coordinate(x + xOffset, y + yOffset);
    }

    private static void updateVisibleTiles() {
        visibleTiles = new ArrayList<>();
        Coordinate playerAt = PlayerSession.getActor().getAt().getParentTileCoordinate();
        visibleTiles.add(playerAt);
        for (Direction direction : Direction.values()) {
            if (direction == Direction.SELF) continue;
            Coordinate c = new Coordinate(playerAt, direction);
            radiateSight(8.0, direction, c);
        }
    }

    private static void radiateSight(double sightPower, Direction primaryDirection, Coordinate radiateFrom) {
        visibleTiles.add(radiateFrom);
        sightPower -= primaryDirection.isDiagonal() ? 1.5 : 1.0;
        if (
                sightPower < 1.0 ||
                DefinitionsManager.
                        getTerrainLookup().
                        getProperties(
                                GameZone.
                                        frontEnd.
                                        tileAt(
                                                radiateFrom
                                        )
                        ).
                        ENERGY_PERMISSION == TerrainProperties.ENERGY_PERMISSION_OPAQUE
        )
            return;
        radiateSight(sightPower, primaryDirection.rotateClockwise(), new Coordinate(radiateFrom, primaryDirection.rotateClockwise()));
        radiateSight(sightPower, primaryDirection, new Coordinate(radiateFrom, primaryDirection));
        radiateSight(sightPower, primaryDirection.rotateCounterClockwise(), new Coordinate(radiateFrom, primaryDirection.rotateCounterClockwise()));
    }
}
