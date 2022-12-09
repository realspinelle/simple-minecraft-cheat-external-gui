package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.text.MessageFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class fly {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("fly").executes((ctx) -> {
            ctx.getSource().getPlayer().getAbilities().flying = !ctx.getSource().getPlayer().getAbilities().flying;
            if (ctx.getSource().getPlayer().getAbilities().flying) {
                ctx.getSource().getPlayer().setPosition(ctx.getSource().getPlayer().getPos().add(0,1,0));
                ctx.getSource().sendFeedback(Text.literal(MessageFormat.format("Flight on {0}", ctx.getSource().getPlayer().getAbilities().getFlySpeed())));
            } else {
                ctx.getSource().sendFeedback(Text.literal("Flight off"));
            }
            return 1;
        }));
    }
}
