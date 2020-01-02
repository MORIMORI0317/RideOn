package com.morimori.rideon;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyEvent {
	public static KeyBinding RideOn = new KeyBinding("key.rideon", Keyboard.KEY_O, "key.categories.movement");
	public static boolean RideOnE;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent e) {
		if (RideOn.isPressed()) {
			if (RideOnE) {
				RideOnE = false;
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation(
						"messege.rideon.switching", I18n.format("messege.rideon.invalid")));
			} else {
				RideOnE = true;
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation(
						"messege.rideon.switching", I18n.format("messege.rideon.enabled")));
			}
		}
	}

}
