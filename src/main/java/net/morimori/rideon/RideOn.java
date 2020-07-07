package net.morimori.rideon;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

import java.util.Arrays;

@Mod(modid = RideOn.MODID, version = RideOn.VERSION)
public class RideOn {
    public static final String MODID = "rideon";
    public static final String VERSION = "1.4";

    @SidedProxy(clientSide = "net.morimori.rideon.ClientProxy", serverSide = "net.morimori.rideon.CommonProxy")
    public static CommonProxy Proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        Config.init(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        PacketHandler.init();
        Proxy.initBindind();

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {

        if (e.player.ridingEntity == null)
            return;

        if (!e.player.worldObj.isRemote) {
            if (Config.contorolfiltermode.equals("whitelist")) {
                if (!Arrays.asList(Config.contorolfilterlist).contains(EntityList.getEntityString(e.player.ridingEntity))) {
                    return;
                }
            } else {
                if (Arrays.asList(Config.contorolfilterlist).contains(EntityList.getEntityString(e.player.ridingEntity))) {
                    return;
                }
            }

            if (e.player.ridingEntity instanceof EntityLiving) {
                float hp = ((EntityLiving) e.player.ridingEntity).getHealth();
                if ((Config.HPMin != -1 && Config.HPMin > hp) || (Config.HPMax != -1 && Config.HPMax < hp)) {
                    e.player.mountEntity(null);
                }
            }
        }
    }


    @SubscribeEvent
    public void onClick(EntityInteractEvent e) {
        EntityPlayer pl = e.entityPlayer;
        if (pl.worldObj.isRemote) {
            if (!pl.isSneaking() && KeyEvent.RideOnE) {

                if (Config.ridefiltermode.equals("whitelist")) {
                    if (Arrays.asList(Config.ridefilterlist).contains(EntityList.getEntityString(e.target))) {
                        PacketHandler.INSTANCE.sendToServer(new MessageRideOn(e.target.getEntityId(), 1));
                    }
                } else {
                    if (!Arrays.asList(Config.ridefilterlist).contains(EntityList.getEntityString(e.target))) {
                        PacketHandler.INSTANCE.sendToServer(new MessageRideOn(e.target.getEntityId(), 1));
                    }
                }

            }

        }
    }

}
