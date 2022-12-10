package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.Tkt;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.WorldSavePath;

import java.util.Objects;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class test {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("test").executes((ctx) -> {
            return 1;
        }));
    }
}