package net.morimori.rideon;

import java.util.Arrays;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

@Mod(modid = RideOn.MODID, version = RideOn.VERSION)
public class RideOn {
	public static final String MODID = "rideon";
	public static final String VERSION = "1.3";

	@SidedProxy(clientSide = "net.morimori.rideon.ClientProxy", serverSide = "net.morimori.rideon.CommonProxy")
	public static CommonProxy Proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		Config.init(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		PacketHandler.init();
		Proxy.initBindind();

	}

	@SubscribeEvent
	public void onClick(EntityInteractEvent e) {
		EntityPlayer pl = e.entityPlayer;
		if (pl.worldObj.isRemote) {
			if (!pl.isSneaking() && KeyEvent.RideOnE) {

				if (Config.ridefiltermode.equals("whitelist")) {
					if (Arrays.asList(Config.ridefilterlist).contains(EntityList.getEntityString(e.target))) {
						PacketHandler.INSTANCE.sendToServer(new MessageRideOn(e.target.getEntityId(), 1));
					}
				} else {
					if (!Arrays.asList(Config.ridefilterlist).contains(EntityList.getEntityString(e.target))) {
						PacketHandler.INSTANCE.sendToServer(new MessageRideOn(e.target.getEntityId(), 1));
					}
				}

			}

		}
	}

}
