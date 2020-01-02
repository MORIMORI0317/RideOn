package com.morimori.rideon;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyEvent {
	public static KeyBinding RideOn = new KeyBinding("key.rideon", Keyboard.KEY_O, "key.categories.movement");
	public static boolean RideOnE;
	public static Minecraft Mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent e) {

		if (RideOn.isPressed()) {
			if (MCVersionChecker.isMoreVersion("1.11")) {
				if (RideOnE) {
					RideOnE = false;
					Mc.player.sendStatusMessage(new TextComponentTranslation(
							"messege.rideon.switching", I18n.format("messege.rideon.invalid")), true);
				} else {
					RideOnE = true;

					Mc.player.sendStatusMessage(new TextComponentTranslation(
							"messege.rideon.switching", I18n.format("messege.rideon.enabled")), true);
				}
			} else {
				if (RideOnE) {
					RideOnE = false;
					Mc.player.sendMessage(new TextComponentTranslation(
							"messege.rideon.switching", I18n.format("messege.rideon.invalid")));
				} else {
					RideOnE = true;
					Mc.player.sendMessage(new TextComponentTranslation(
							"messege.rideon.switching", I18n.format("messege.rideon.enabled")));
				}
			}
		}
	}

}
