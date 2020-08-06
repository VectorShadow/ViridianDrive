package definitions;

import gamestate.gameobject.GameActor;
import gamestate.order.Order;

public class ViridianDriveOrderExecutor extends OrderExecutor {
    @Override
    protected void implementationHandleDisconnection(GameActor actor) {
        //todo - handle this as we add new order types that may need specific handling
    }

    @Override
    protected void clearImplementationOrder(GameActor actor, Class<? extends Order> orderClass) {
        //todo - handle order types as we add them
    }

    @Override
    protected void setImplementationOrder(GameActor actor, Order order) {
        //todo - handle order types as we add them
    }
}
