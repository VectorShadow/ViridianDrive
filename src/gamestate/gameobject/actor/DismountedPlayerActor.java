package gamestate.gameobject.actor;

import event.Event;
import gamestate.terrain.TerrainProperties;

import java.util.ArrayList;

public class DismountedPlayerActor extends DriveActor {
    @Override
    public int getMovementAccess() {
        return TerrainProperties.MATTER_PERMISSION_OBSTACLE;
    }

    @Override
    public int getMovementSpeed() {
        return 8;
    }

    @Override
    public double getTurningSpeed() {
        return Math.PI / 4;
    }

    @Override
    public ArrayList<Event> scheduleEvents() {
        ArrayList<Event> events = super.scheduleEvents();
        //todo - more stuff here?
        return events;
    }
}
