package net.morimori.rideon;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class CommonConfig {
    public static ForgeConfigSpec.ConfigValue<String> RIDEFILTERMODE;
    public static ForgeConfigSpec.ConfigValue<List<String>> RIDEFILTERLIST;
    public static ForgeConfigSpec.ConfigValue<Boolean> CANCONTOROL;
    public static ForgeConfigSpec.ConfigValue<String> CONTOROLFILTERMODE;
    public static ForgeConfigSpec.ConfigValue<List<String>> CONTOROLFILTERLIST;

    public static void init() {
        Pair<ConfigLoder, ForgeConfigSpec> common_config = new ForgeConfigSpec.Builder().configure(ConfigLoder::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, common_config.getRight());
    }

    static class ConfigLoder {
        public ConfigLoder(ForgeConfigSpec.Builder builder) {
            builder.push("Ride On");
            RIDEFILTERMODE = builder.define("CanRideFilterMode", "blacklist");
            List<String> ridedefaalists = new ArrayList<String>();
            ridedefaalists.add("minecraft:boat");
            ridedefaalists.add("minecraft:horse");
            ridedefaalists.add("minecraft:donkey");
            ridedefaalists.add("minecraft:mule");
            ridedefaalists.add("minecraft:llama");
            ridedefaalists.add("minecraft:minecart");
            ridedefaalists.add("minecraft:strider");
            ridedefaalists.add("minecraft:tnt_minecart");
            ridedefaalists.add("minecraft:furnace_minecart");
            ridedefaalists.add("minecraft:chest_minecart");
            ridedefaalists.add("minecraft:hopper_minecart");
            ridedefaalists.add("minecraft:command_block_minecart");
            ridedefaalists.add("minecraft:spawner_minecart");
            RIDEFILTERLIST = builder.define("CanRideFilterList", ridedefaalists);
            CANCONTOROL = builder.define("EnabledControl", true);
            CONTOROLFILTERMODE = builder.define("CanControlFilterMode", "blacklist");
            List<String> contorolfalists = new ArrayList<String>();
            contorolfalists.add("minecraft:horse");
            contorolfalists.add("minecraft:donkey");
            contorolfalists.add("minecraft:mule");
            contorolfalists.add("minecraft:llama");
            contorolfalists.add("minecraft:strider");
            CONTOROLFILTERLIST = builder.define("CanControlFilterList", contorolfalists);
            builder.pop();
        }
    }

}
