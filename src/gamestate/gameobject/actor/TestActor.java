package gamestate.gameobject.actor;

import event.ActorMovementEvent;
import event.ActorRotationEvent;
import event.Event;
import util.Direction;

import java.util.ArrayList;
import java.util.Random;

public class TestActor extends ViridianDriveActor {
    @Override
    public int getMovementAccess() {
        return 1;
    }

    @Override
    public int getMovementSpeed() {
        return 16;
    }

    @Override
    public double getTurningSpeed() {
        return Math.PI / 16;
    }

    @Override
    public ArrayList<Event> scheduleEvents() {
        Random r = new Random();
        ArrayList<Event> events = new ArrayList<>();
        double d = r.nextDouble();
        if (d < 0.1) events.add(new ActorMovementEvent(this, false));
        if (d > 0.4) events.add(new ActorMovementEvent(this, true));
        d = r.nextDouble();
        if (d < 0.15) events.add(new ActorRotationEvent(this, false));
        if (d > 0.85) events.add(new ActorRotationEvent(this, true));
        return events;
    }

    /**
     * These vision arcs correspond to optimal vision in all directions.
     */
    @Override
    public double[] getVisionArcs() {
        double[] arcs = new double[ARC_LENGTH];
        for (int i = 0; i < ARC_LENGTH; ++i)
            arcs[i] = getVisionPower();
        return arcs;
    }

    @Override
    public double getVisionPower() {
        return 8.0;
    }
}
