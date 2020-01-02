package com.morimori.rideon;

import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements IProxy {
	@Override
	public void initBindind() {
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, KeyInputEvent.class,
				new KeyEvent()::onKeyInput);
		ClientRegistry.registerKeyBinding(KeyEvent.RideOn);
	}
}
