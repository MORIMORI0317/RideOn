package net.morimori.rideon;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
            if (isRideOnActive((ServerPlayerEntity) e.getPlayer())) {
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


            // EntityUtil.jump(entity);
        }

    }
}
