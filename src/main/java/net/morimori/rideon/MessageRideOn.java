package net.morimori.rideon;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageRideOn implements IMessage {
	public int entity;
	public int state;

	public MessageRideOn() {
	}

	public MessageRideOn(int entityIn, int state) {
		this.entity = entityIn;
		this.state = state;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entity = buf.readInt();
		this.state = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entity);
		buf.writeInt(this.state);
	}

}