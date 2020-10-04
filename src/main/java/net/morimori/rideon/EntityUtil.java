package net.morimori.rideon;

import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

public class EntityUtil {

    public static void jump(LivingEntity entity) {
            if (!entity.func_233570_aj_())
                return;
            try {
                Method jmethod = ObfuscationReflectionHelper.findMethod(LivingEntity.class, DeobfNames.jump.name());
                jmethod.setAccessible(true);
                jmethod.invoke(entity);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public static void move(LivingEntity entity, Vector3d vector) {
        if (entity instanceof MobEntity) {
            MobEntity mob = (MobEntity) entity;
            for (Goal.Flag value : Goal.Flag.values()) {
                mob.goalSelector.disableFlag(value);
            }
        }
        try {
            Method jmethod = ObfuscationReflectionHelper.findMethod(LivingEntity.class, DeobfNames.isMovementBlocked.name());
            jmethod.setAccessible(true);
            if ((boolean) jmethod.invoke(entity))
                return;
            double speed = entity.getAttribute(Attributes.field_233821_d_).getBaseValue();
            entity.addVelocity(vector.getX() / speed, 0, vector.getZ() / speed);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void facing(LivingEntity entity, float yaw, float pitch) {
        entity.rotationYawHead = yaw;
        entity.prevRotationYawHead = yaw;
        entity.rotationYaw = yaw;
        entity.prevRotationYaw = yaw;
        entity.rotationPitch = pitch;
        entity.prevRotationPitch = pitch;
    }
}

