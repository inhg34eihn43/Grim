package ac.grim.grimac.checks.impl.flight;

import ac.grim.grimac.checks.Check;
import ac.grim.grimac.checks.type.PositionCheck;
import ac.grim.grimac.player.GrimPlayer;
import ac.grim.grimac.utils.anticheat.update.PositionUpdate;

public class FlightA extends Check implements PositionCheck {
    public FlightA(GrimPlayer player) {
        super(player);
    }

    private int suspicious;

    @Override
    public void onPositionUpdate(final PositionUpdate positionUpdate) {
        if (positionUpdate.getTo() != positionUpdate.getFrom()) {
            // is to constant Y position
            if (positionUpdate.getTo().getY() == positionUpdate.getFrom().getY()) {
                suspicious++;
            }else {
                suspicious = 0;
            }
        }
    }

}
