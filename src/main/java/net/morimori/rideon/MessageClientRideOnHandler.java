package net.morimori.rideon;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageClientRideOnHandler implements IMessageHandler<MessageClientRideOn, IMessage> {

	@Override
	public IMessage onMessage(MessageClientRideOn message, MessageContext ctx) {
		EntityPlayer pl = (EntityPlayer) Minecraft.getMinecraft().world.getPlayerEntityByName(message.name);
		EntityPlayer pen = (EntityPlayer) Minecraft.getMinecraft().world.getPlayerEntityByName(message.ridername);
		int state = message.state;

		if (state == 1)
			pen.startRiding(pl);

		return null;
	}

}
