package com.morimori.rideon;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RideOn.MODID);

	public static void init() {

		INSTANCE.registerMessage(MessageRideOnHandler.class, MessageRideOn.class, 0, Side.SERVER);
	}
}
