package frontend.io;

import frontend.PlayerSession;
import gamestate.gameobject.actor.order.MovementOrder;
import gamestate.gameobject.actor.order.RotationOrder;

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
                PlayerSession.getPlayerActor().setMovementOrder(new MovementOrder(true));
                break;
            case VK_A:
                PlayerSession.getPlayerActor().setRotationOrder(new RotationOrder(false));
                break;
            case VK_S:
                PlayerSession.getPlayerActor().setMovementOrder(new MovementOrder(false));
                break;
            case VK_D:
                PlayerSession.getPlayerActor().setRotationOrder(new RotationOrder(true));
                break;
        }
    }

    @Override
    protected void handleKeyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int keyMod = e.getModifiersEx();
        switch (keyCode) {
            case VK_W: case VK_S:
                PlayerSession.getPlayerActor().setMovementOrder(null);
                break;
            case VK_A: case VK_D:
                PlayerSession.getPlayerActor().setRotationOrder(null);
                break;
        }
    }
}
