package net.morimori.rideon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRideOnHandler implements IMessageHandler<MessageRideOn, IMessage> {

	@Override
	public IMessage onMessage(MessageRideOn message, MessageContext ctx) {
		EntityPlayer pl = ctx.getServerHandler().player;
		Entity en = pl.world.getEntityByID(message.entity);
		int state = message.state;
		if (state == 1) {
			pl.startRiding(en);
			if (en instanceof EntityPlayer)
				PacketHandler.INSTANCE.sendToAllTracking(
						new MessageClientRideOn(((EntityPlayer) en).getName(), pl.getName(), 1),
						pl);

		}

		if (!(en instanceof EntityLiving))
			return null;

		EntityLiving li = (EntityLiving) en;

		if (state == 2) {
			li.getJumpHelper().setJumping();
			return null;
		}

		li.setAIMoveSpeed(0);
		li.prevRotationYawHead = pl.prevRotationYawHead;
		li.rotationYawHead = pl.rotationYawHead;

		li.prevRotationYaw = pl.prevRotationYawHead;
		li.rotationYaw = pl.rotationYawHead;
		li.prevRotationPitch = 0;
		li.rotationPitch = 0;
		Vec3d v = li.getLookVec();
		float speed = 1;
		if (state == 3) {
			li.motionX = v.x / 3 * speed;
			li.motionZ = v.z / 3 * speed;

			if (en instanceof EntityPlayer) {

				PacketHandler.INSTANCE.sendToAllTracking(
						new MessageClientRideOn(((EntityPlayer) en).getName(), pl.getName(), 2),
						pl);
			}
		} else if (state == 4) {
			li.motionX = -v.x / 3 * speed;
			li.motionZ = -v.z / 3 * speed;
		} else if (state == 5) {
			li.motionX = -v.z / 3 * speed;
			li.motionZ = v.x / 3 * speed;
		} else if (state == 6) {
			li.motionX = v.z / 3 * speed;
			li.motionZ = -v.x / 3 * speed;
		}
		return null;
	}

}
