package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.text.MessageFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class fly {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("fly").executes((context) -> {
            context.getSource().getPlayer().getAbilities().flying = !context.getSource().getPlayer().getAbilities().flying;
            if (context.getSource().getPlayer().getAbilities().flying) {
                context.getSource().getPlayer().setPosition(context.getSource().getPlayer().getPos().add(0,1,0));
                context.getSource().sendFeedback(Text.literal(MessageFormat.format("Flight on {0}", context.getSource().getPlayer().getAbilities().getFlySpeed())));
            } else {
                context.getSource().sendFeedback(Text.literal("Flight off"));
            }
            return 1;
        }));
    }
}
