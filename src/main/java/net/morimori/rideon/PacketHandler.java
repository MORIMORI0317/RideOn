package net.morimori.rideon;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(RideOn.MODID, RideOn.MODID + "_channel")).clientAcceptedVersions(a -> true).serverAcceptedVersions(a -> true).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

    public static void init() {
        INSTANCE.registerMessage(0, RideOnKeyMessage.class, RideOnKeyMessage::encodeMessege, RideOnKeyMessage::decodeMessege, RideOnKeyMessageHandler::reversiveMessage);
        INSTANCE.registerMessage(1, RideOnSyncMessage.class, RideOnSyncMessage::encodeMessege, RideOnSyncMessage::decodeMessege, RideOnSyncMessageHandler::reversiveMessage);
    }
}
