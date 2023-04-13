package ac.grim.grimac.checks.impl.flight;

import ac.grim.grimac.GrimAPI;
import ac.grim.grimac.checks.Check;
import ac.grim.grimac.checks.CheckData;
import ac.grim.grimac.checks.type.PositionCheck;
import ac.grim.grimac.player.GrimPlayer;
import ac.grim.grimac.utils.anticheat.update.PositionUpdate;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.util.Vector3d;
import org.bukkit.ChatColor;

@CheckData(name = "FlightA", setback = 5, experimental = true)
public class FlightA extends Check implements PositionCheck {

    private final int maxSuspicious;
    private int suspicious;

    public FlightA(GrimPlayer player) {
        super(player);
        this.maxSuspicious = GrimAPI.INSTANCE.getConfigManager().getConfig().getInt("Flight.A.suspicious");
    }


    @Override
    public void onPositionUpdate(final PositionUpdate positionUpdate) {
        Vector3d from = positionUpdate.getFrom();
        Vector3d to = positionUpdate.getTo();
        boolean isSuspicious = from.getY() - to.getY() == 0 && !positionUpdate.isOnGround() && !positionUpdate.isTeleport() && !player.isFlying;
        if (!from.equals(to)) {
            if (isSuspicious) {
                suspicious++;
                //Debug
              //  player.bukkitPlayer.sendMessage((suspicious >= maxSuspicious ? ChatColor.RED : ChatColor.GRAY) + "Suspicious=" + suspicious + "/" + maxSuspicious);
                if (suspicious >= maxSuspicious) {
                    flag();
                }
            } else {
                suspicious = 0;
            }
        }
    }
}
