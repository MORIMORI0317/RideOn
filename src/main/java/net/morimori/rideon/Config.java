package net.morimori.rideon;

import net.minecraft.entity.EntityType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static ConfigValue<Boolean> CanControl;
    public static ConfigValue<Boolean> GetOffIfDead;
    public static ConfigValue<Boolean> CanNoMOBRide;
    public static ConfigValue<Boolean> CanNoBossRide;
    public static ConfigValue<Boolean> CanNoDragonRide;
    public static ConfigValue<List<String>> RideOnBlackList;
    public static ConfigValue<List<String>> RideOnWhiteList;
    public static ConfigValue<List<String>> RideControlBlackList;
    public static ConfigValue<List<String>> RideControlWhiteList;

    public static void init() {
        Pair<ConfigLoder, ForgeConfigSpec> server_config = new ForgeConfigSpec.Builder().configure(ConfigLoder::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, server_config.getRight());
    }

    static class ConfigLoder {
        public ConfigLoder(ForgeConfigSpec.Builder builder) {
            builder.push("RideOn");
            CanControl = builder.define("Can Control Ride MOB", true);
            GetOffIfDead = builder.define("Get Off If Dead", true);
            CanNoMOBRide = builder.define("Can No MOB Riding", true);
            CanNoBossRide = builder.define("Can Boss Riding", false);
            CanNoDragonRide = builder.define("Can Dragon Riding(not work)", false);
            List<String> robl = new ArrayList<>();
            robl.add(EntityType.HORSE.getRegistryName().toString());
            robl.add(EntityType.DONKEY.getRegistryName().toString());
            robl.add(EntityType.LLAMA.getRegistryName().toString());
            robl.add(EntityType.MULE.getRegistryName().toString());
            robl.add(EntityType.field_233589_aE_.getRegistryName().toString());
            robl.add(EntityType.PIG.getRegistryName().toString());
            robl.add(EntityType.TNT_MINECART.getRegistryName().toString());
            robl.add(EntityType.FURNACE_MINECART.getRegistryName().toString());
            robl.add(EntityType.CHEST_MINECART.getRegistryName().toString());
            robl.add(EntityType.HOPPER_MINECART.getRegistryName().toString());
            robl.add(EntityType.SPAWNER_MINECART.getRegistryName().toString());
            robl.add(EntityType.COMMAND_BLOCK_MINECART.getRegistryName().toString());
            RideOnBlackList = builder.define("Ride Black List", robl);
            List<String> rowl = new ArrayList<>();
            RideOnWhiteList = builder.define("Ride White List", rowl);
            List<String> rcbl = new ArrayList<>();
            rcbl.add(EntityType.HORSE.getRegistryName().toString());
            rcbl.add(EntityType.DONKEY.getRegistryName().toString());
            rcbl.add(EntityType.MULE.getRegistryName().toString());
            rcbl.add(EntityType.PIG.getRegistryName().toString());
            rcbl.add(EntityType.field_233589_aE_.getRegistryName().toString());
            RideControlBlackList = builder.define("Control Black List", rcbl);
            List<String> rcwl = new ArrayList<>();
            RideControlWhiteList = builder.define("Control White List", rcwl);
            builder.pop();
        }
    }
}
