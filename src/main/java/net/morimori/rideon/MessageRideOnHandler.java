package net.morimori.rideon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRideOnHandler implements IMessageHandler<MessageRideOn, IMessage> {

	@Override
	public IMessage onMessage(MessageRideOn message, MessageContext ctx) {
		EntityPlayerMP pl = ctx.getServerHandler().playerEntity;
		Entity en = pl.worldObj.getEntityByID(message.entity);
		int state = message.state;
		if (state == 1)
			pl.mountEntity(en);

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
		Vec3 v = li.getLookVec();
		float speed = 1;
		if (state == 3) {
			li.motionX = v.xCoord / 3 * speed;
			li.motionZ = v.zCoord / 3 * speed;
		} else if (state == 4) {
			li.motionX = -v.xCoord / 3 * speed;
			li.motionZ = -v.zCoord / 3 * speed;
		} else if (state == 5) {
			li.motionX = -v.zCoord / 3 * speed;
			li.motionZ = v.xCoord / 3 * speed;
		} else if (state == 6) {
			li.motionX = v.zCoord / 3 * speed;
			li.motionZ = -v.xCoord / 3 * speed;
		}

		return null;
	}

}
