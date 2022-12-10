package friselis.perso.tkt;

import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public class Utils {
    public static void patchPacket(Args args) {
        double patchedX = Math.round((double) args.get(0) * 100) / 100d;
        double patchedZ = Math.round((double) args.get(2) * 100) / 100d;
        patchedZ = Math.nextAfter(patchedZ, patchedZ + Math.signum(patchedZ));
        patchedX =  Math.nextAfter(patchedX, patchedX + Math.signum(patchedX));
        args.set(0, patchedX);
        args.set(2, patchedZ);
        if (Tkt.NoFall) {
            args.set(5, true);
        }
    }
}
