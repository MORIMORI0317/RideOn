package com.morimori.rideon;

import net.minecraft.network.PacketBuffer;

public class MessageRideOn {
	public int entity;

	public MessageRideOn(int entityIn) {
		this.entity = entityIn;
	}

	public int read(PacketBuffer buffer) {
		return entity;
	}

	public static void write(MessageRideOn message, PacketBuffer buffer) {
		buffer.writeInt(message.entity);
	}

	public static void encodeMessege(MessageRideOn messegeIn, PacketBuffer buffer) {
		buffer.writeInt(messegeIn.read(buffer));
		write(messegeIn, buffer);
	}

	public static MessageRideOn decodeMessege(PacketBuffer buffer) {

		return new MessageRideOn(buffer.readInt());
	}
}
