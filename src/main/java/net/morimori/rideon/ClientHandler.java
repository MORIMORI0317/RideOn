package net.morimori.rideon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientHandler {
    @SubscribeEvent
    public static void onKey(InputEvent.KeyInputEvent e) {
        if (RideOn.rideOnToggle.isPressed())
            PacketHandler.INSTANCE.sendToServer(new RideOnKeyMessage(RideOnKeyMessage.KeyTyapes.TOGGLE));

    }
}
