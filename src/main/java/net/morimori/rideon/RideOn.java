package net.morimori.rideon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod(modid = RideOn.MODID, version = RideOn.VERSION, acceptedMinecraftVersions = "[1.9,1.12.2]")
public class RideOn {
	public static final String MODID = "rideon";
	public static final String VERSION = "1.2";

	@SidedProxy(clientSide = "net.morimori.rideon.ClientProxy", serverSide = "net.morimori.rideon.CommonProxy")
	public static CommonProxy Proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		PacketHandler.init();
		Proxy.initBindind();

	}

	@SubscribeEvent
	public void onClick(PlayerInteractEvent.EntityInteract e) {
		EntityPlayer pl = e.getEntityPlayer();
		if (pl.world.isRemote) {
			if (!pl.isSneaking() && KeyEvent.RideOnE) {
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(e.getTarget().getEntityId(), 1));
			}
		}
	}

	@SubscribeEvent
	public void onEntityTick(PlayerTickEvent e) {
		EntityPlayer pl = e.player;

		if (pl.world.isRemote) {
			if (pl.isSneaking())
				pl.dismountRidingEntity();
		}
	}
}
