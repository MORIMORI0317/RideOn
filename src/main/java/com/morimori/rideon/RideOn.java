package com.morimori.rideon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RideOn.MODID)
public class RideOn {

	public static final String MODID = "rideon";
	public static final String VERSION = "1.0";
	public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(),
			() -> () -> new ServerProxy());

	public RideOn() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		PacketHandler.init();
		proxy.initBindind();
	}

	@SubscribeEvent
	public void onClick(PlayerInteractEvent.EntityInteract e) {
		PlayerEntity pl = e.getPlayer();

		if (pl.world.isRemote) {
			if (KeyEvent.RideOnE && !pl.isSneaking())
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(e.getTarget().getEntityId()));
		}
	}

}
