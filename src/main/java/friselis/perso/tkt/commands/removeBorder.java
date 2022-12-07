package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class removeBorder {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("removeBorder")
                .executes(ctx -> {
                    assert MinecraftClient.getInstance().world != null;
                    MinecraftClient.getInstance().world.getWorldBorder().setSize(999999999);
                    return 1;
                }));
    }
}
