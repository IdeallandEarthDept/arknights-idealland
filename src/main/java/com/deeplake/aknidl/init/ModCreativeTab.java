package com.deeplake.aknidl.init;

import com.deeplake.aknidl.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab {
	public static final CreativeTabs IDL_MISC = new CreativeTabs(CreativeTabs.getNextID(), "akn_misc")
	{
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.ICON);
        }
    };

    public static final CreativeTabs AKN_MATERIAL = new CreativeTabs(CreativeTabs.getNextID(), "akn_material")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.M_ABC_5);
        }
    };

    public static final CreativeTabs AKN_SKILL = new CreativeTabs(CreativeTabs.getNextID(), "akn_skill")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.SILVERASH_3);
        }
    };
}
