package com.morimori.rideon;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public void initBindind() {
		FMLCommonHandler.instance().bus().register(new KeyEvent());
		ClientRegistry.registerKeyBinding(KeyEvent.RideOn);
	}
}
