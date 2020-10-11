package com.deeplake.idealland.keys;

import com.deeplake.idealland.IdlFramework;
import com.deeplake.idealland.item.skills.ItemSkillBase;
import com.deeplake.idealland.network.NetworkHandler;
import com.deeplake.idealland.network.protocols.PacketTest;
import com.deeplake.idealland.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class KeyboardManager {
    //ref: https://harbinger.covertdragon.team/chapter-18/keyboard.html

    // 注册快捷键。
// 没有调用的时间限制，但建议在 FMLInitializationEvent 发布时调用。
    public static void init() {
        for (KeyBinding key:
             ClientProxy.KEY_BINDINGS) {
            ClientRegistry.registerKeyBinding(key);
        }
        IdlFramework.Log("Registered %d keys", ClientProxy.KEY_BINDINGS.size());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        if (ClientProxy.CAST_OFFHAND.isPressed() || ClientProxy.CAST_MAINHAND.isPressed()) {

            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.player;
            if(player == null) return;
            if(mc.isGamePaused()) return;
            if(!mc.inGameHasFocus) return;
            if(mc.currentScreen != null) return;

            EnumHand hand = ClientProxy.CAST_OFFHAND.isKeyDown() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
            IdlFramework.Log("pressed key cast :" + hand);

            ItemStack item = player.getHeldItem(hand);
            if(item.isEmpty())
            {
                IdlFramework.LogWarning("Trying to cast an empty item");
            }

            if(item.getItem() instanceof ItemSkillBase)
            {
                ItemSkillBase skill = (ItemSkillBase) item.getItem();
                if (skill.canCast(player.world, player, hand))
                {
                    NetworkHandler.SendToServer(new PacketTest(hand.ordinal()));
                }

            }

            //mc.playerController.updateController();
            //                    NetworkManager.channel.sendToServer(new C2SSpecialAction((byte) 1));
        }
    }
}
