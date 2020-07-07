package net.morimori.rideon;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config {
    private static Configuration config;

    public static String ridefiltermode;
    public static String[] ridefilterlist;

    public static boolean cancontorol;
    public static String contorolfiltermode;
    public static String[] contorolfilterlist;

    public static float HPMin;
    public static float HPMax;

    public static void init(FMLPreInitializationEvent e) {

        config = new Configuration(e.getSuggestedConfigurationFile(), RideOn.VERSION, true);

        config.addCustomCategoryComment("CanRide", "Can Ride On");
        config.setCategoryLanguageKey("CanRide", "rideon.config.can_ride");

        config.addCustomCategoryComment("CanControl", "Can Ride Control");
        config.setCategoryLanguageKey("CanControl", "rideon.config.can_control");
        cancontorol = config.getBoolean("EnabledControl", "CanControl", true, "enabled");

        ridefiltermode = config.getString("mode", "CanRide", "blacklist", "blacklist or whitelist");
        String[] ridedefalists = {"Boat", "EntityHorse", "MinecartRideable", "MinecartChest", "MinecartFurnace",
                "MinecartTNT", "MinecartHopper", "MinecartSpawner"};
        ridefilterlist = config.getStringList("entitys", "CanRide", ridedefalists, "lists");

        contorolfiltermode = config.getString("mode", "CanControl", "blacklist", "blacklist or whitelist");
        String[] contorolfalists = {"EntityHorse"};
        contorolfilterlist = config.getStringList("entitys", "CanControl", contorolfalists, "lists");

        config.addCustomCategoryComment("HPFilter", "Max HP and Min Hp");
        config.setCategoryLanguageKey("HPFilter", "rideon.config.hpfilter");

        HPMax = config.getFloat("max", "HPFilter", -1, -1, Integer.MAX_VALUE, "Set to -1 to disable");
        HPMin = config.getFloat("min", "HPFilter", 0.5f, -1, Integer.MAX_VALUE, "Set to -1 to disable");

        config.save();

    }

}
