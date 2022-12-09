package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.Tkt;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class nofood {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("nofood")
                .executes(ctx -> {
                    Tkt.NoFood = !Tkt.NoFood;
                    if (Tkt.NoFood) {
                        ctx.getSource().sendFeedback(Text.literal("NoFood On"));
                        ctx.getSource().getPlayer().getHungerManager().setFoodLevel(20);
                        ctx.getSource().getPlayer().getHungerManager().setSaturationLevel(5.0F);
                    } else {
                        ctx.getSource().sendFeedback(Text.literal("NoFood Off"));
                    }
                    return 1;
                }));
    }
}