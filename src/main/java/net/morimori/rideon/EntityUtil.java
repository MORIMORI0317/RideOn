package net.morimori.rideon;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

public class EntityUtil {

    public static void jump(LivingEntity entity) {
        if (!entity.func_233570_aj_())
            return;
        try {
            Method jmethod = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "jump");
            jmethod.setAccessible(true);
            jmethod.invoke(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

