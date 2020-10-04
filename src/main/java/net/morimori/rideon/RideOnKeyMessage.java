package net.morimori.rideon;

import net.minecraft.network.PacketBuffer;

public class RideOnKeyMessage {
    public final KeyTyapes keyTyape;

    public RideOnKeyMessage(KeyTyapes keyTyape) {
        this.keyTyape = keyTyape;
    }

    public static RideOnKeyMessage decodeMessege(PacketBuffer buffer) {
        return new RideOnKeyMessage(KeyTyapes.valueOf(buffer.readString()));
    }

    public static void encodeMessege(RideOnKeyMessage messegeIn, PacketBuffer buffer) {
        buffer.writeString(messegeIn.keyTyape.name());
    }

    public static enum KeyTyapes {
        TOGGLE,
        JUMP;
    }

}
