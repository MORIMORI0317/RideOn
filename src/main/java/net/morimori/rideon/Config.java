package net.morimori.rideon;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static void init() {
        Pair<ConfigLoder, ForgeConfigSpec> common_config = new ForgeConfigSpec.Builder().configure(ConfigLoder::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, common_config.getRight());
    }

    static class ConfigLoder {
        public ConfigLoder(ForgeConfigSpec.Builder builder) {
            builder.push("RideOn");
            builder.pop();
        }
    }
}
