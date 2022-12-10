package friselis.perso.tkt.mixin;

import friselis.perso.tkt.Tkt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onGameStateChange", at = @At("HEAD"), cancellable = true)
    public void cancelGameStateChange(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        if (packet.getReason() == GameStateChangeS2CPacket.GAME_MODE_CHANGED
                || packet.getReason() == GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN
        ) {
            ci.cancel();
        }
    }

    @Inject(method = "onWorldBorderInitialize", at = @At("HEAD"), cancellable = true)
    public void cancelWorldBorderInitialize(WorldBorderInitializeS2CPacket packet, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "onHealthUpdate", at = @At("HEAD"), cancellable = true)
    public void cancelFoodAndSaturation(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        if (MinecraftClient.getInstance().player != null && Tkt.NoFood) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            player.updateHealth(packet.getHealth());
            player.getHungerManager().setFoodLevel(20);
            player.getHungerManager().setSaturationLevel(5.0F);
            ci.cancel();
        }
    }
}