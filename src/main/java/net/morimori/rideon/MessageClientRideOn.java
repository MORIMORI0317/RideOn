package net.morimori.rideon;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageClientRideOn implements IMessage {

	public String name;
	public String ridername;
	public int state;

	public MessageClientRideOn() {
	}

	public MessageClientRideOn(String name, String ridername, int state) {
		this.name = name;
		this.ridername = ridername;
		this.state = state;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.name = ByteBufUtils.readUTF8String(buf);
		this.ridername = ByteBufUtils.readUTF8String(buf);
		this.state = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.name);
		ByteBufUtils.writeUTF8String(buf, this.ridername);
		buf.writeInt(this.state);
	}

}
