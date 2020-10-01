package net.morimori.rideon;

import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RideOnSyncMessageHandler {

    public static boolean isClientActive;

    public static void reversiveMessage(RideOnSyncMessage message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
        isClientActive = message.active;
    }
}
