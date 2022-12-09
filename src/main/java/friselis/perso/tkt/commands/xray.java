package friselis.perso.tkt.commands;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.Tkt;
import friselis.perso.tkt.commands.arguments.Vec3ArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.text.Text;

import java.util.Objects;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class xray {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess) {
        dispatcher.register(literal("xray").then(literal("add").then(argument("item", ItemStackArgumentType.itemStack(commandRegistryAccess)).executes((ctx) -> {
            String itemName = ItemStackArgumentType.getItemStackArgument(ctx, "item").asString();
            boolean exist = false;
            for (String t : Tkt.XrayBlocks) {
                if (Objects.equals(t, itemName)) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                ctx.getSource().sendFeedback(Text.literal("The item " + itemName + " is already in the list"));
            } else {
                Tkt.XrayBlocks.add(itemName);
                if (Tkt.XrayEnabled) {
                    MinecraftClient.getInstance().worldRenderer.reload();
                }
                ctx.getSource().sendFeedback(Text.literal("You added " + itemName + " block to xray view"));
            }
            return 1;
        }))).then(literal("remove").then(argument("item", ItemStackArgumentType.itemStack(commandRegistryAccess)).executes((ctx) -> {
            String itemName = ItemStackArgumentType.getItemStackArgument(ctx, "item").asString();
            boolean exist = false;
            int index = 0;
            for (String t : Tkt.XrayBlocks) {
                if (Objects.equals(t, itemName)) {
                    exist = true;
                    break;
                }
                index++;
            }
            if (exist) {
                Tkt.XrayBlocks.remove(index);
                if (Tkt.XrayEnabled) {
                    MinecraftClient.getInstance().worldRenderer.reload();
                }
                ctx.getSource().sendFeedback(Text.literal("You removed " + itemName + " block to xray view"));
            } else {
                ctx.getSource().sendFeedback(Text.literal("The item " + itemName + " is not in the list"));
            }
            return 1;
        }))).executes((ctx) -> {
            Tkt.XrayEnabled = !Tkt.XrayEnabled;
            if (Tkt.XrayEnabled) {
                MinecraftClient.getInstance().worldRenderer.reload();
                ctx.getSource().sendFeedback(Text.literal("Xray on"));
            } else {
                MinecraftClient.getInstance().worldRenderer.reload();
                ctx.getSource().sendFeedback(Text.literal("Xray off"));
            }
            return 1;
        }));
    }
}
