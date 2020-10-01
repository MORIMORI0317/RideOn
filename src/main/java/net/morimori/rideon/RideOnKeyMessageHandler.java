package net.morimori.rideon;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RideOnKeyMessageHandler {
    public static void reversiveMessage(RideOnKeyMessage message, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayerEntity player = ctx.get().getSender();
        boolean isActive = ServerHandler.isRideOnActive(player);
        ServerHandler.setRideOnActive(player, !isActive);
        player.sendStatusMessage(new TranslationTextComponent("messege.rideon.switching", new TranslationTextComponent(!isActive ? "messege.rideon.enabled" : "messege.rideon.invalid")), true);
    }
}
