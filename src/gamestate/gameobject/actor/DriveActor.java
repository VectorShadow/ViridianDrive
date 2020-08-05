package gamestate.gameobject.actor;

import event.ActorMovementEvent;
import event.ActorRotationEvent;
import event.Event;
import gamestate.gameobject.GameActor;
import gamestate.gameobject.actor.order.MovementOrder;
import gamestate.gameobject.actor.order.RotationOrder;

import java.util.ArrayList;

/**
 * Implementation of GameActor.
 */
public abstract class DriveActor extends GameActor {
    /*
     * todo - we need a scheme for setting orders remotely. Frontend orders will not be recognized or acted on by the
     *  backend. We also need to ensure orders are vague enough that they can be handled by the engine, without
     *  relying on implementation specific concepts.
     */
    protected MovementOrder movementOrder = null;
    protected RotationOrder rotationOrder = null;

    public void setMovementOrder(MovementOrder movementOrder) {
        this.movementOrder = movementOrder;
    }

    public void setRotationOrder(RotationOrder rotationOrder) {
        this.rotationOrder = rotationOrder;
    }

    @Override
    public ArrayList<Event> scheduleEvents() {
        ArrayList<Event> events = new ArrayList<>();
        if (movementOrder != null)
            events.add(new ActorMovementEvent(this, movementOrder.FORWARD));
        if (rotationOrder != null)
            events.add(new ActorRotationEvent(this, rotationOrder.CLOCKWISE));
        return events;
    }
}
