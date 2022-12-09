package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.GameMode;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class gamemode {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        LiteralArgumentBuilder<FabricClientCommandSource> builder = literal("gamemode");
        GameMode[] var2 = GameMode.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            GameMode gameMode = var2[var4];
            builder.then((literal(gameMode.getName()).executes((ctx) -> {
                assert MinecraftClient.getInstance().interactionManager != null;
                MinecraftClient.getInstance().interactionManager.setGameMode(gameMode);
                return 1;
            })));
        }
        dispatcher.register(builder);
    }
}
