package net.morimori.rideon;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;

import java.util.Arrays;

public class MessageRideOnHandler implements IMessageHandler<MessageRideOn, IMessage> {

    @Override
    public IMessage onMessage(MessageRideOn message, MessageContext ctx) {
        EntityPlayerMP pl = ctx.getServerHandler().playerEntity;
        Entity en = pl.worldObj.getEntityByID(message.entity);

        int state = message.state;

        if (state == 1) {

            if (en instanceof EntityLiving) {
                float hp = ((EntityLiving) en).getHealth();
                if ((Config.HPMin != -1 && Config.HPMin > hp) || (Config.HPMax != -1 && Config.HPMax < hp)) {
                    return null;
                }
            }
            if (Config.ridefiltermode.equals("whitelist")) {
                if (Arrays.asList(Config.ridefilterlist).contains(EntityList.getEntityString(en))) {
                    pl.mountEntity(en);
                }
            } else {
                if (!Arrays.asList(Config.ridefilterlist).contains(EntityList.getEntityString(en))) {
                    pl.mountEntity(en);
                }
            }
        }

        if (!(en instanceof EntityLiving) || !Config.cancontorol)
            return null;

        if (Config.contorolfiltermode.equals("whitelist")) {
            if (!Arrays.asList(Config.contorolfilterlist).contains(EntityList.getEntityString(en))) {
                return null;
            }
        } else {
            if (Arrays.asList(Config.contorolfilterlist).contains(EntityList.getEntityString(en))) {
                return null;
            }
        }

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
