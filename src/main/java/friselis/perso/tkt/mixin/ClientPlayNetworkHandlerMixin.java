package friselis.perso.tkt.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onGameStateChange", at = @At("HEAD"), cancellable = true)
    public void cancelGameStateChange(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        if (packet.getReason() == GameStateChangeS2CPacket.GAME_MODE_CHANGED ||
                packet.getReason() == GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN ||
                packet.getReason() == GameStateChangeS2CPacket.GAME_WON) {
            ci.cancel();
        }
    }

    @Inject(method = "onWorldBorderInitialize", at = @At("HEAD"), cancellable = true)
    public void cancelWorldBorderInitialize(WorldBorderInitializeS2CPacket packet, CallbackInfo ci) {
        ci.cancel();
    }
}