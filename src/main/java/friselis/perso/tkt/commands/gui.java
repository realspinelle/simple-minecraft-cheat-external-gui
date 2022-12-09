package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.HelloWorld;
import javafx.application.Platform;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class gui {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gui").executes(ctx -> {
            Platform.runLater(
                    () -> {
                        if (HelloWorld.pStage.isShowing()) {
                            HelloWorld.pStage.close();
                        } else {
                            HelloWorld.pStage.show();
                        }
                    }
            );
            return 1;
        }));
    }
}
