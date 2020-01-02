package com.morimori.rideon;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyEvent {
	public static boolean RideOnE;
	public static KeyBinding RideOn = new KeyBinding("key.rideon", GLFW.GLFW_KEY_O, "key.categories.movement");

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onKeyInput(KeyInputEvent e) {

		if (RideOn.isPressed()) {

			if (RideOnE) {
				RideOnE = false;
				Minecraft.getInstance().player.sendStatusMessage(new TranslationTextComponent(
						"messege.rideon.switching", I18n.format("messege.rideon.invalid")), true);
			} else {
				RideOnE = true;

				Minecraft.getInstance().player.sendStatusMessage(new TranslationTextComponent(
						"messege.rideon.switching", I18n.format("messege.rideon.enabled")), true);
			}

		}
	}

}
