package net.morimori.rideon;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageRideOnHandler {
	public static void reversiveMessage(MessageRideOn message, Supplier<NetworkEvent.Context> ctx) {

		EntityPlayer pl = ctx.get().getSender();
		int state = message.state;
		Entity en = pl.world.getEntityByID(message.entity);

		//	pl.sendMessage(new TextComponentString("test=" + state));

		if (state == 1)
			pl.startRiding(en);

		if (!(en instanceof EntityLiving))
			return;

		EntityLiving li = (EntityLiving) en;

		if (state == 2) {
			li.getJumpHelper().setJumping();
			return;
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
	}

}
