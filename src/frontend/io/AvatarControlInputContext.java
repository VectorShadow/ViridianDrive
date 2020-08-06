package frontend.io;

import backend.EngineManager;
import frontend.PlayerSession;
import gamestate.order.MovementOrder;
import gamestate.order.RotationOrder;
import link.instructions.OrderTransmissionInstructionDatum;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * Handle all input in the main game mode and convert it to avatar commands.
 */
public class AvatarControlInputContext extends InputContext {
    @Override
    protected void handleKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        switch (keyCode) {
            case VK_W:
                EngineManager.frontEndDataLink.transmit(
                        new OrderTransmissionInstructionDatum(
                                new MovementOrder(true)
                        )
                );
                break;
            case VK_A:
                EngineManager.frontEndDataLink.transmit(
                        new OrderTransmissionInstructionDatum(
                                new RotationOrder(false)
                        )
                );
                break;
            case VK_S:
                EngineManager.frontEndDataLink.transmit(
                        new OrderTransmissionInstructionDatum(
                                new MovementOrder(false)
                        )
                );
                break;
            case VK_D:
                EngineManager.frontEndDataLink.transmit(
                        new OrderTransmissionInstructionDatum(
                                new RotationOrder(true)
                        )
                );
                break;
        }
    }

    @Override
    protected void handleKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        switch (keyCode) {
            case VK_W: case VK_S:
                EngineManager.frontEndDataLink.transmit(
                        new OrderTransmissionInstructionDatum(MovementOrder.class)
                );
                break;
            case VK_A: case VK_D:
                EngineManager.frontEndDataLink.transmit(
                        new OrderTransmissionInstructionDatum(RotationOrder.class)
                );
                break;
        }
    }
}
