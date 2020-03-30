package net.morimori.rideon;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
public class KeyEvent {
	public static KeyBinding RideOn = new KeyBinding("key.rideon", Keyboard.KEY_O, "key.categories.movement");
	public static boolean RideOnE;
	public static Minecraft Mc = Minecraft.getMinecraft();

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

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		EntityPlayer pl = Mc.thePlayer;

		if (pl == null)
			return;

		if (Mc.gameSettings.keyBindJump.getIsKeyPressed()) {

			if (pl.isRiding() && (pl.ridingEntity instanceof EntityLiving)) {
				EntityLiving li = (EntityLiving) pl.ridingEntity;
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 2));
			}
		}
		if (Mc.gameSettings.keyBindForward.getIsKeyPressed()) {

			if (pl.isRiding() && pl.ridingEntity instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.ridingEntity;
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 3));
			}
		}
		if (Mc.gameSettings.keyBindBack.getIsKeyPressed()) {

			if (pl.isRiding() && pl.ridingEntity instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.ridingEntity;
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 4));
			}
		}
		if (Mc.gameSettings.keyBindRight.getIsKeyPressed()) {

			if (pl.isRiding() && pl.ridingEntity instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.ridingEntity;
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 5));
			}
		}
		if (Mc.gameSettings.keyBindLeft.getIsKeyPressed()) {

			if (pl.isRiding() && pl.ridingEntity instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.ridingEntity;
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 6));
			}
		}

	}
}
