package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.EXT_GUI;
import javafx.application.Platform;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class gui {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("gui").executes(ctx -> {
            Platform.runLater(
                    () -> {
                        if (EXT_GUI.pStage.isShowing()) {
                            EXT_GUI.pStage.close();
                        } else {
                            EXT_GUI.pStage.show();
                        }
                    }
            );
            return 1;
        }));
    }
}
