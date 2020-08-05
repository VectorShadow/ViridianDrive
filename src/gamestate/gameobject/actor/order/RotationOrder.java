package gamestate.gameobject.actor.order;

public class RotationOrder extends Order {
    public final boolean CLOCKWISE;

    public RotationOrder(boolean clockwise) {
        CLOCKWISE = clockwise;
    }
}
