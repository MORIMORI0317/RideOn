package net.morimori.rideon;

import net.minecraft.network.PacketBuffer;

public class RideOnKeyMessage {
    public RideOnKeyMessage() {

    }

    public static RideOnKeyMessage decodeMessege(PacketBuffer buffer) {
        return new RideOnKeyMessage();
    }

    public static void encodeMessege(RideOnKeyMessage messegeIn, PacketBuffer buffer) {
    }
}
