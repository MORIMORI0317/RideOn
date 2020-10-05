package net.morimori.rideon;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerHandler {

    public static final Map<UUID, Boolean> RideOnActives = new HashMap<>();

    public static boolean isRideOnActive(ServerPlayerEntity player) {
        UUID pluuid = player.getGameProfile().getId();
        return RideOnActives.containsKey(pluuid) && RideOnActives.get(pluuid);
    }

    public static void setRideOnActive(ServerPlayerEntity player, boolean active) {
        RideOnActives.put(player.getGameProfile().getId(), active);
    }

    @SubscribeEvent
    public static void onClick(PlayerInteractEvent.EntityInteract e) {
        if (!e.getPlayer().world.isRemote) {
            boolean flag1 = Config.CanNoMOBRide.get();
            boolean flag2 = e.getTarget() instanceof MobEntity;
            boolean flag3 = !Config.RideOnBlackList.get().contains(e.getTarget().getType().getRegistryName().toString());

            boolean flag4 = Config.RideOnWhiteList.get().isEmpty();
            boolean flag5 = Config.RideOnWhiteList.get().contains(e.getTarget().getType().getRegistryName().toString());

            boolean flag6 = !Config.CanNoBossRide.get() || e.getTarget().isNonBoss();
            boolean flag7 = !Config.CanNoDragonRide.get() || !(e.getTarget() instanceof EnderDragonEntity);


            if (flag7 && flag6 && (flag4 || flag5) && flag3 && (flag1 || flag2) && isRideOnActive((ServerPlayerEntity) e.getPlayer())) {
                e.setCanceled(true);
                e.getPlayer().startRiding(e.getTarget());
                e.setCancellationResult(ActionResultType.func_233537_a_(e.getPlayer().world.isRemote));
            }
        } else {
            if (RideOnSyncMessageHandler.isClientActive) {
                e.setCanceled(true);
                e.setCancellationResult(ActionResultType.func_233537_a_(e.getPlayer().world.isRemote));
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent e) {
        MinecraftServer ms = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
        for (String name : ms.getPlayerList().getOnlinePlayerNames()) {
            ServerPlayerEntity player = ms.getPlayerList().getPlayerByUsername(name);
            PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new RideOnSyncMessage(isRideOnActive(player)));
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.player.getRidingEntity() != null && e.player.getRidingEntity() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) e.player.getRidingEntity();
            if (e.player.world.isRemote) {
                if (e.player instanceof ClientPlayerEntity) {
                    if (((ClientPlayerEntity) e.player).movementInput.jump)
                        PacketHandler.INSTANCE.sendToServer(new RideOnKeyMessage(RideOnKeyMessage.KeyTyapes.JUMP));
                }
            } else {
                boolean flag1 = !Config.RideControlBlackList.get().contains(entity.getType().getRegistryName().toString());
                boolean flag2 = Config.RideControlWhiteList.get().isEmpty();
                boolean flag3 = Config.RideControlWhiteList.get().contains(entity.getType().getRegistryName().toString());


                if ((flag2 || flag3) && flag1 && Config.CanControl.get()) {
                    EntityUtil.move(entity, e.player.getMotion());
                    EntityUtil.facing(entity, e.player.getRotationYawHead(), e.player.rotationPitch);
                }

                boolean flage1 = Config.GetOffIfDead.get() && !entity.isAlive();

                if (flage1)
                    e.player.stopRiding();

            }
        }
    }
}
