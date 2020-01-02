package com.morimori.rideon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRideOnHandler implements IMessageHandler<MessageRideOn, IMessage> {

	@Override
	public IMessage onMessage(MessageRideOn message, MessageContext ctx) {
		EntityPlayer entityPlayer = ctx.getServerHandler().player;
		Entity en = entityPlayer.world.getEntityByID(message.entity);
		entityPlayer.startRiding(en);
		return null;
	}

}
