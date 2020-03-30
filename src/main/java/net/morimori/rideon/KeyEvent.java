package net.morimori.rideon;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class KeyEvent {
	public static boolean RideOnE;
	public static KeyBinding RideOn = new KeyBinding("key.rideon", GLFW.GLFW_KEY_O, "key.categories.movement");
	public static Minecraft Mc = Minecraft.getInstance();

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onKeyInput(KeyInputEvent e) {

		if (RideOn.isPressed()) {

			if (RideOnE) {
				RideOnE = false;
				Minecraft.getInstance().player.sendStatusMessage(new TextComponentTranslation(
						"messege.rideon.switching", I18n.format("messege.rideon.invalid")), true);
			} else {
				RideOnE = true;

				Minecraft.getInstance().player.sendStatusMessage(new TextComponentTranslation(
						"messege.rideon.switching", I18n.format("messege.rideon.enabled")), true);
			}

		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		EntityPlayer pl = Mc.player;

		if (pl == null)
			return;

		if (Mc.gameSettings.keyBindJump.isKeyDown()) {

			if (pl.getRidingEntity() != null && (pl.getRidingEntity() instanceof EntityLiving)) {
				EntityLiving li = (EntityLiving) pl.getRidingEntity();
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 2));
			}
		}
		if (Mc.gameSettings.keyBindForward.isKeyDown()) {

			if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.getRidingEntity();
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 3));
			}
		}
		if (Mc.gameSettings.keyBindBack.isKeyDown()) {

			if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.getRidingEntity();
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 4));
			}
		}
		if (Mc.gameSettings.keyBindRight.isKeyDown()) {

			if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.getRidingEntity();
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 5));
			}
		}
		if (Mc.gameSettings.keyBindLeft.isKeyDown()) {

			if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof EntityLiving) {
				EntityLiving li = (EntityLiving) pl.getRidingEntity();
				PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 6));
			}
		}

	}
}
