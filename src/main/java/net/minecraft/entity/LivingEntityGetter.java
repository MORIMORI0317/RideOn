package net.minecraft.entity;

public class LivingEntityGetter {
	public static void jump(LivingEntity li) {
		if (li.onGround)
			li.jump();
	}
}
