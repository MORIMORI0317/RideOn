package net.morimori.rideon;

import net.minecraft.network.PacketBuffer;

public class RideOnSyncMessage {
    public boolean active;

    public RideOnSyncMessage(boolean active) {
        this.active = active;
    }

    public static RideOnSyncMessage decodeMessege(PacketBuffer buffer) {
        return new RideOnSyncMessage(buffer.readBoolean());
    }

    public static void encodeMessege(RideOnSyncMessage messegeIn, PacketBuffer buffer) {
        buffer.writeBoolean(messegeIn.active);
    }
}
