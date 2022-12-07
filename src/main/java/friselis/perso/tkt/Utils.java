package friselis.perso.tkt;

import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public class Utils {
    public static void patchPacket(Args args) {
        double patchedX = (((double) args.get(0) * 1000) - ((double) args.get(0) * 1000 % 10)) / 1000;
        double patchedZ = (((double) args.get(2) * 1000) - ((double) args.get(2) * 1000 % 10)) / 1000;
        args.set(0, patchedX);
        args.set(2, patchedZ);
    }
}
