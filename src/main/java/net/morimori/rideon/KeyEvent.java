package net.morimori.rideon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

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
                Mc.player.sendStatusMessage(new TranslationTextComponent(
                        "messege.rideon.switching", I18n.format("messege.rideon.invalid")), true);
            } else {
                RideOnE = true;

                Mc.player.sendStatusMessage(new TranslationTextComponent(
                        "messege.rideon.switching", I18n.format("messege.rideon.enabled")), true);
            }

        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent e) {
        PlayerEntity pl = Mc.player;


        if (pl == null || pl.getRidingEntity() == null || !CommonConfig.CANCONTOROL.get())
            return;


        if (CommonConfig.CONTOROLFILTERMODE.get().equals("whitelist")) {
            if (!CommonConfig.CONTOROLFILTERLIST.get().contains(pl.getRidingEntity().getEntityString())) {
                return;
            }
        } else {
            if (CommonConfig.CONTOROLFILTERLIST.get().contains(pl.getRidingEntity().getEntityString())) {
                return;
            }
        }

        if (Mc.gameSettings.keyBindJump.isKeyDown()) {

            if (pl.getRidingEntity() != null && (pl.getRidingEntity() instanceof LivingEntity)) {
                LivingEntity li = (LivingEntity) pl.getRidingEntity();
                PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 2));
            }
        }
        if (Mc.gameSettings.keyBindForward.isKeyDown()) {

            if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof LivingEntity) {
                LivingEntity li = (LivingEntity) pl.getRidingEntity();
                PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 3));
            }
        }
        if (Mc.gameSettings.keyBindBack.isKeyDown()) {

            if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof LivingEntity) {
                LivingEntity li = (LivingEntity) pl.getRidingEntity();
                PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 4));
            }
        }
        if (Mc.gameSettings.keyBindRight.isKeyDown()) {

            if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof LivingEntity) {
                LivingEntity li = (LivingEntity) pl.getRidingEntity();
                PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 5));
            }
        }
        if (Mc.gameSettings.keyBindLeft.isKeyDown()) {

            if (pl.getRidingEntity() != null && pl.getRidingEntity() instanceof LivingEntity) {
                LivingEntity li = (LivingEntity) pl.getRidingEntity();
                PacketHandler.INSTANCE.sendToServer(new MessageRideOn(li.getEntityId(), 6));
            }
        }

    }
}
