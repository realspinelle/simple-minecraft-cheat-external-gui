package friselis.perso.tkt;

import com.mojang.brigadier.CommandDispatcher;
import friselis.perso.tkt.commands.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Tkt implements ClientModInitializer {
    public static Logger logger = LogManager.getLogger();
    public static double FALL_VELOCITY = -0.04;
    private int flyTicks;

    public static boolean XrayEnabled = false;
    public static List<String> XrayBlocks = new LinkedList<>(List.of("minecraft:gold_ore"));

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(this::registerCommands);
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && client.player.getAbilities().flying) {
                flyTicks--;
                Vec3d pos = client.player.getPos();
                if (flyTicks < 0) {
                    if (client.player != null && !client.player.isOnGround() && !client.player.isTouchingWater() && !client.player.isFallFlying()) {
                        PlayerMoveC2SPacket packet = new PlayerMoveC2SPacket.Full(pos.x, pos.y-FALL_VELOCITY, pos.z, 0, 0, false);
                        ClientConnection connection = Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).getConnection();
                        connection.send(packet);
                    }
                    flyTicks = 8;
                }
                if (flyTicks == 7) {
                    if (client.player != null && !client.player.isOnGround() && !client.player.isTouchingWater() && !client.player.isFallFlying()) {
                        PlayerMoveC2SPacket packet = new PlayerMoveC2SPacket.Full(pos.x, pos.y+FALL_VELOCITY, pos.z, 0, 0, false);
                        ClientConnection connection = Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).getConnection();
                        connection.send(packet);
                    }
                }
            } else {
                flyTicks = 0;
            }
        });
    }

    public void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        tp.register(dispatcher);
        gamemode.register(dispatcher);
        //removeBorder.register(dispatcher);
        test.register(dispatcher);
        fly.register(dispatcher);
        flyspeed.register(dispatcher);
        xray.register(dispatcher, registryAccess);
    }
}
