package net.morimori.rideon;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

@Mod(RideOn.MODID)
public class RideOn {

    public static final KeyBinding rideOnToggle = new KeyBinding("key.rideon", GLFW.GLFW_KEY_O, "key.categories.movement");
    public static final String MODID = "rideon";

    public RideOn() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        Config.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
        MinecraftForge.EVENT_BUS.register(ServerHandler.class);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
        ClientRegistry.registerKeyBinding(rideOnToggle);
    }

}
