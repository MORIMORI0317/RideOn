package com.morimori.rideon;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class MessageRideOnHandler implements IMessageHandler<MessageRideOn, IMessage> {

	@Override
	public IMessage onMessage(MessageRideOn message, MessageContext ctx) {
		EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
		Entity en = entityPlayer.worldObj.getEntityByID(message.entity);
		entityPlayer.mountEntity(en);
		return null;
	}

}
