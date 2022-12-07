package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.commands.arguments.Vec3ArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class tp {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("tp").then(argument("location", Vec3ArgumentType.vec3()).executes((context) -> {
            assert MinecraftClient.getInstance().player != null;
            Vec3d pos = Vec3ArgumentType.getPosArgument(context, "location").toAbsolutePos(context.getSource());
            MinecraftClient.getInstance().player.setPosition(pos);
            return 1;
        })));
    }
}
