package gamestate.gamezone;

import gamestate.gameobject.actor.DriveActor;

import java.util.ArrayList;

public class PreDefinedGameZoneBuilder extends GameZoneBuilder {

    private final String[] TERRAIN;
    private final ArrayList<DriveActor> ACTORS;

    public PreDefinedGameZoneBuilder(String[] terrain, ArrayList<DriveActor> actors) {
        TERRAIN = terrain;
        ACTORS = actors;
    }

    @Override
    public GameZone build() {
        return null; //todo!
    }
}
