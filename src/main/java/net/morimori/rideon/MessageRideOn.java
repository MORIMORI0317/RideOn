package net.morimori.rideon;

import net.minecraft.network.PacketBuffer;

public class MessageRideOn {
	public int entity;
	public int state;

	public MessageRideOn(int entityIn, int state) {
		this.entity = entityIn;
		this.state = state;
	}

	public int[] read(PacketBuffer buffer) {
		int[] n = { entity, state };

		return n;
	}

	public static void write(MessageRideOn message, PacketBuffer buffer) {
		buffer.writeInt(message.entity);
		buffer.writeInt(message.state);
	}

	public static void encodeMessege(MessageRideOn messegeIn, PacketBuffer buffer) {
		int[] n = messegeIn.read(buffer);
		buffer.writeInt(n[0]);
		buffer.writeInt(n[1]);

		write(messegeIn, buffer);
	}

	public static MessageRideOn decodeMessege(PacketBuffer buffer) {

		return new MessageRideOn(buffer.readInt(), buffer.readInt());
	}
}
