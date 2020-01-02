package com.morimori.rideon;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentTranslation;

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
