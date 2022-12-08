package friselis.perso.tkt.mixin;

import friselis.perso.tkt.Tkt;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.text.Text;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LightmapTextureManager.class)
public class LightmapTextureManagerMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;getGamma()Lnet/minecraft/client/option/SimpleOption;", opcode = Opcodes.INVOKEVIRTUAL), method = "update(F)V")
    private SimpleOption<Double> getFieldValue(GameOptions options) {
        if (Tkt.XrayEnabled) {
            return new SimpleOption<>("options.gamma", SimpleOption.emptyTooltip(), (optionText, value) -> Text.empty(), SimpleOption.DoubleSliderCallbacks.INSTANCE.withModifier(
                    d -> (double) 20, d -> 1
            ), 0.5, value -> {
            });
        } else {
            return options.getGamma();
        }
    }
}