package com.morimori.rideon;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageRideOn implements IMessage {
	public int entity;

	public MessageRideOn() {
	}

	public MessageRideOn(int entityIn) {
		this.entity = entityIn;

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entity = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entity);
	}

}
