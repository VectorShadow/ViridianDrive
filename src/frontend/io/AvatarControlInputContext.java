package frontend.io;

import backend.EngineManager;
import frontend.PlayerSession;
import gamestate.order.MovementOrder;
import gamestate.order.Order;
import gamestate.order.RotationOrder;
import link.instructions.OrderTransmissionInstructionDatum;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * Handle all input in the main game mode and convert it to avatar commands.
 */
//todo - this should probably do nothing (or very little) if we are using a remote connection but not currently connected.
public class AvatarControlInputContext extends InputContext {
    @Override
    protected void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        Order inputOrder = null;
        boolean orderChanged = false;
        switch (keyCode) {
            case VK_W:
                inputOrder = new MovementOrder(true);
                orderChanged = PlayerSession.getPlayerActor().setMovementOrder((MovementOrder)inputOrder);
                break;
            case VK_A:
                inputOrder = new RotationOrder(false);
                orderChanged = PlayerSession.getPlayerActor().setRotationOrder((RotationOrder)inputOrder);
                break;
            case VK_S:
                inputOrder = new MovementOrder(false);
                orderChanged = PlayerSession.getPlayerActor().setMovementOrder((MovementOrder)inputOrder);
                break;
            case VK_D:
                inputOrder = new RotationOrder(true);
                orderChanged = PlayerSession.getPlayerActor().setRotationOrder((RotationOrder)inputOrder);
                break;
        }
        if (orderChanged)
            EngineManager.frontEndDataLink.transmit(new OrderTransmissionInstructionDatum(inputOrder));
    }

    @Override
    protected void handleKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        Class<? extends Order> orderClass = Order.class;
        boolean orderChanged = false;
        switch (keyCode) {
            case VK_W: case VK_S:
                orderClass = MovementOrder.class;
                orderChanged = PlayerSession.getPlayerActor().setMovementOrder(null);
                break;
            case VK_A: case VK_D:
                orderClass = RotationOrder.class;
                orderChanged = PlayerSession.getPlayerActor().setRotationOrder(null);
                break;
        }
        if (orderChanged)
            EngineManager.frontEndDataLink.transmit(new OrderTransmissionInstructionDatum(orderClass));
    }
}
