package gamestate.gameobject.actor.order;

import event.Event;

public class MovementOrder extends Order {
    public final boolean FORWARD;

    public MovementOrder(boolean forward) {
        FORWARD = forward;
    }
}
