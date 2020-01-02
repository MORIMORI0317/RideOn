package com.morimori.rideon;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

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
