package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.text.MessageFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class fly {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("fly").executes((ctx) -> {
            ctx.getSource().getPlayer().getAbilities().allowFlying = !ctx.getSource().getPlayer().getAbilities().allowFlying;
            if (ctx.getSource().getPlayer().getAbilities().allowFlying) {
                ctx.getSource().sendFeedback(Text.literal(MessageFormat.format("Flight on {0}", ctx.getSource().getPlayer().getAbilities().getFlySpeed())));
            } else {
                ctx.getSource().sendFeedback(Text.literal("Flight off"));
            }
            return 1;
        }));
    }
}
