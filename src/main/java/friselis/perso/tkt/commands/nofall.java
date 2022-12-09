package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.Tkt;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class nofall {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("nofall").executes((ctx) -> {
            Tkt.NoFall = !Tkt.NoFall;
            if (Tkt.NoFall) {
                ctx.getSource().sendFeedback(Text.literal("NoFall On"));
            } else {
                ctx.getSource().sendFeedback(Text.literal("NoFall Off"));
            }
            return 1;
        }));
    }
}
