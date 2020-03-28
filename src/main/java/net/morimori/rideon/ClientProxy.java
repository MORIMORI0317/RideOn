package net.morimori.rideon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void initBindind() {
		MinecraftForge.EVENT_BUS.register(new KeyEvent());
		ClientRegistry.registerKeyBinding(KeyEvent.RideOn);
	}
}
