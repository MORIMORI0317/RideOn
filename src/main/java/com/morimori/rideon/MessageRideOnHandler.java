package com.morimori.rideon;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageRideOnHandler {
	public static void reversiveMessage(MessageRideOn message, Supplier<NetworkEvent.Context> ctx) {

		PlayerEntity entityPlayer = ctx.get().getSender();
		Entity en = entityPlayer.world.getEntityByID(message.entity);
		entityPlayer.startRiding(en);

	}

}
