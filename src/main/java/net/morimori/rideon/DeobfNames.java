package net.morimori.rideon;

import cpw.mods.modlauncher.Launcher;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nonnull;

public class DeobfNames {
    public static final RefName jump = of("jump", "func_70664_aZ");
    public static final RefName isMovementBlocked = of("isMovementBlocked", "func_70610_aX");


    public static class RefName {
        private final String mcpName;
        private final String srgName;

        public RefName(String mcpName, String srgName) {
            this.mcpName = mcpName;
            this.srgName = srgName;
        }


        public String name() {
            return Launcher.INSTANCE.environment().findNameMapping("srg").isPresent() ? Validate.notEmpty(this.mcpName) : Validate.notEmpty(this.srgName);
        }
    }

    public static RefName of(@Nonnull String mcpName, @Nonnull String srgName) {
        return new RefName(mcpName, srgName);
    }

}
