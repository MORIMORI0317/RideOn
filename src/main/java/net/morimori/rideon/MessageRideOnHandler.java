package net.morimori.rideon;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.LivingEntityGetter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageRideOnHandler {
	public static void reversiveMessage(MessageRideOn message, Supplier<NetworkEvent.Context> ctx) {

		PlayerEntity pl = ctx.get().getSender();
		int state = message.state;
		Entity en = pl.world.getEntityByID(message.entity);

		if (state == 1)
			pl.startRiding(en);

		if (!(en instanceof LivingEntity))
			return;

		LivingEntity li = (LivingEntity) en;

		if (state == 2) {
			LivingEntityGetter.jump(li);
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
		double x = 0;
		double z = 0;

		float speed = 1;
		if (state == 3) {
			x = v.x / 3 * speed;
			z = v.z / 3 * speed;
		} else if (state == 4) {
			x = -v.x / 3 * speed;
			z = -v.z / 3 * speed;
		} else if (state == 5) {
			x = -v.z / 3 * speed;
			z = v.x / 3 * speed;
		} else if (state == 6) {
			x = v.z / 3 * speed;
			z = -v.x / 3 * speed;
		}

		li.setMotion(new Vec3d(x, li.getMotion().y, z));

	}

}
