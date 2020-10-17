package com.deeplake.aknidl.events;

import com.deeplake.aknidl.IdlFramework;
import com.deeplake.aknidl.util.CommonFunctions;
import com.deeplake.aknidl.util.IDLNBT;
import com.deeplake.aknidl.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.deeplake.aknidl.util.IDLNBT.*;
import static com.deeplake.aknidl.util.NBTStrDef.IDLNBTDef.*;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModStarterEvents {
	  @SubscribeEvent
	  public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		  EntityPlayer player = event.player;
		  //IdlFramework.Log(getPlyrIdlTagSafe(player).toString());
		  int lastStarterVer = getPlayerIdeallandIntSafe(player, STARTER_KIT_VERSION_TAG);
		  if(lastStarterVer < CUR_STARTER_KIT_VERSION) {
			  IDLNBT.setPlayerIdeallandTagSafe(player, STARTER_KIT_VERSION_TAG, CUR_STARTER_KIT_VERSION);

			  ItemStack scry = new ItemStack(Items.QUARTZ);
			  player.addItemStackToInventory(scry);

			  if (player instanceof EntityPlayerMP) {
				  CommonFunctions.SendMsgToPlayerStyled((EntityPlayerMP)player, "idlframework.msg.starter_kit_given", TextFormatting.AQUA);
			  }
			  IdlFramework.Log(String.format("Given starter items to player %s, ver %d", player.getDisplayNameString(), CUR_STARTER_KIT_VERSION));
		  }
	  }
	
}
