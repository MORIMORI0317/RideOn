package net.morimori.rideon;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class ClientHandler {

    public static final KeyBinding rideOnToggle = new KeyBinding("key.rideon", GLFW.GLFW_KEY_O, "key.categories.movement");

    @SubscribeEvent
    public static void onKey(InputEvent.KeyInputEvent e) {
        if (rideOnToggle.isPressed())
            PacketHandler.INSTANCE.sendToServer(new RideOnKeyMessage(RideOnKeyMessage.KeyTyapes.TOGGLE));

    }
}
