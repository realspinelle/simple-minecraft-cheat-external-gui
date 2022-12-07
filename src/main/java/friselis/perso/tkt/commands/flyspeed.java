package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class flyspeed {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("flyspeed").then(argument("speed", FloatArgumentType.floatArg(0.00001f, 0.5f)).executes((context) -> {
            context.getSource().getPlayer().getAbilities().setFlySpeed(FloatArgumentType.getFloat(context, "speed"));
            return 1;
        })));
    }
}
