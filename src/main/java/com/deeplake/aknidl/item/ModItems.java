package com.deeplake.aknidl.item;

import com.deeplake.aknidl.item.skills.ItemSkillBase;
import com.deeplake.aknidl.item.skills.arknights.ItemSkillTrueSL;
import com.deeplake.aknidl.item.skills.arknights.silverash.ItemSvrashPowerStrike;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

	//https://mrfz.fandom.com/wiki/Material_List

	public static final List<Item> ITEMS = new ArrayList<Item>();

	//Basic
	public static final Item ICON = new ItemBase("icon");

	public static final ItemMiscMaterial[] M_a =
			new ItemMiscMaterial[]{
					new ItemMiscMaterial("m_a_1"),
					new ItemMiscMaterial("m_a_2"),
					new ItemMiscMaterial("m_a_3"),
					new ItemMiscMaterial("m_a_4"),
			};

	public static final ItemMiscMaterial[] M_b =
			new ItemMiscMaterial[]{
					new ItemMiscMaterial("m_b_1"),
					new ItemMiscMaterial("m_b_2"),
					new ItemMiscMaterial("m_b_3"),
					new ItemMiscMaterial("m_b_4"),
			};

	public static final ItemMiscMaterial[] M_c =
			new ItemMiscMaterial[]{
					new ItemMiscMaterial("m_c_1"),
					new ItemMiscMaterial("m_c_2"),
					new ItemMiscMaterial("m_c_3"),
					new ItemMiscMaterial("m_c_4"),
			};

	public static final ItemMiscMaterial[] M_d =
			new ItemMiscMaterial[]{
					new ItemMiscMaterial("m_d_1"),
					new ItemMiscMaterial("m_d_2"),
					new ItemMiscMaterial("m_d_3"),
					new ItemMiscMaterial("m_d_4"),
			};

	public static final ItemMiscMaterial[] M_e =
			new ItemMiscMaterial[]{
					new ItemMiscMaterial("m_e_1"),
					new ItemMiscMaterial("m_e_2"),
					new ItemMiscMaterial("m_e_3"),
					new ItemMiscMaterial("m_e_4"),
			};

	public static final ItemMiscMaterial[] M_f =
			new ItemMiscMaterial[]{
					new ItemMiscMaterial("m_f_1"),
					new ItemMiscMaterial("m_f_2"),
					new ItemMiscMaterial("m_f_3"),
					new ItemMiscMaterial("m_f_4"),
			};

	public static final ItemMiscMaterial[] M_g =
			new ItemMiscMaterial[]{
					null,//new ItemMiscMaterial("m_g_1"),
					null,//new ItemMiscMaterial("m_g_2"),
					new ItemMiscMaterial("m_g_3"),
					new ItemMiscMaterial("m_g_4"),
			};

	public static final ItemMiscMaterial[] M_h =
			new ItemMiscMaterial[]{
					null,//new ItemMiscMaterial("m_h_1"),
					null,//new ItemMiscMaterial("m_h_2"),
					new ItemMiscMaterial("m_h_3"),
					new ItemMiscMaterial("m_h_4"),
			};

	public static final ItemMiscMaterial[] M_i =
			new ItemMiscMaterial[]{
					null,//new ItemMiscMaterial("m_i_1"),
					null,//new ItemMiscMaterial("m_i_2"),
					new ItemMiscMaterial("m_i_3"),
					new ItemMiscMaterial("m_i_4"),
			};

	public static final ItemMiscMaterial[] M_j =
			new ItemMiscMaterial[]{
					null,//new ItemMiscMaterial("m_j_1"),
					null,//new ItemMiscMaterial("m_j_2"),
					new ItemMiscMaterial("m_j_3"),
					new ItemMiscMaterial("m_j_4"),
			};

	public static final ItemMiscMaterial[] M_k =
			new ItemMiscMaterial[]{
					null,//new ItemMiscMaterial("m_k_1"),
					null,//new ItemMiscMaterial("m_k_2"),
					new ItemMiscMaterial("m_k_3"),
					new ItemMiscMaterial("m_k_4"),
			};

	public static final ItemMiscMaterial[] M_l =
			new ItemMiscMaterial[]{
					null,//new ItemMiscMaterial("m_l_1"),
					null,//new ItemMiscMaterial("m_l_2"),
					new ItemMiscMaterial("m_l_3"),
					new ItemMiscMaterial("m_l_4"),
			};

	public static final ItemMiscMaterial M_ABC_5 = new ItemMiscMaterial("m_abc_5");
	public static final ItemMiscMaterial M_FGG_5 = new ItemMiscMaterial("m_fgg_5");
	public static final ItemMiscMaterial M_HIJ_5 = new ItemMiscMaterial("m_hij_5");

	public static final ItemMiscMaterial[][] MaterialsElite = new ItemMiscMaterial[][]{M_a, M_b, M_c, M_d, M_e, M_f, M_g, M_h, M_i, M_j, M_k, M_l};

	public static ItemMiscMaterial getMaterial(int type, int tier)
	{
		type--;
		tier--;
		if (type < 0 || type >= MaterialsElite.length)
			return null;

		ItemMiscMaterial[] group = MaterialsElite[type];
		if (tier < 0 || tier >= group.length)
			return  group[tier];

		return null;
	}

	//skill
	public static final Item SILVERASH_1 = new ItemSvrashPowerStrike("skill_pwr_strike_silverash");
	public static final Item SILVERASH_2 = new ItemSkillBase("skill_rule_of_survival");
	public static final Item SILVERASH_3 = new ItemSkillTrueSL("skill_truesilver_slash");

	/*
	WOOD(0, 59, 2.0F, 0.0F, 15),
    STONE(1, 131, 4.0F, 1.0F, 5),
    IRON(2, 250, 6.0F, 2.0F, 14),
    DIAMOND(3, 1561, 8.0F, 3.0F, 10),
    GOLD(0, 32, 12.0F, 0.0F, 22);

    harvestLevel, maxUses, efficiency, damage, enchantability
	*/

	//Tool Material
//	public static final Item BLOOD_IRON_INGOT = new ItemBase("blood_iron_ingot");
//
//    public static final Item.ToolMaterial TOOL_MATERIAL_BLOOD =
//			EnumHelper.addToolMaterial("material_blood", 3, 512, 3.0F, 4F, 20).setRepairItem(new ItemStack( ModItems.BLOOD_IRON_INGOT));
//
//	public static final ItemKinshipSword KINSHIP_SWORD = new ItemKinshipSword("kinship_sword", TOOL_MATERIAL_BLOOD);

	//Armor
//    LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
//    CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
//    IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
//    GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
//    DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
	//Note that if you want to set a mod thing as repair material, define them before the material, otherwise it will be empty.

//    public static final ItemArmor.ArmorMaterial moroonArmorMaterial = EnumHelper.addArmorMaterial(
//            "aknidl:armor_moroon", "aknidl:armor_moroon", 80, new int[] {3, 6, 8, 3}, 2, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2)
//            .setRepairItem(new ItemStack(Items.QUARTZ));
//

	//Food
//	static PotionEffect eff = new PotionEffect(MobEffects.LEVITATION, TICK_PER_SECOND * 60, 0);
//	public static final ItemFoodBase FIGHT_BREAD = (ItemFoodBase) new ItemFoodBase("war_bread", 10, 10, false).
//			setPotionEffect(eff, 1.0f).
//			setAlwaysEdible();
//    public static final ItemFoodBase MEMORY_BREAD = new ItemFoodBase("memory_bread", 3, 0.6f, false).SetXP(10);





	//Armor
//	public static final ItemHelmSniper helmetSniper = (ItemHelmSniper) new ItemHelmSniper("helmet_sniper", moroonArmorMaterialSniper, 1, EntityEquipmentSlot.HEAD);
//
//	public static final ItemArmorBase[] MOR_GENERAL_ARMOR =
//			{        new ItemArmorBase("mor_armor_1", moroonArmorMaterial, 1, EntityEquipmentSlot.HEAD)
//					,new ItemArmorBase("mor_armor_2", moroonArmorMaterial, 1, EntityEquipmentSlot.CHEST)
//					,new ItemArmorBase("mor_armor_3", moroonArmorMaterial, 1, EntityEquipmentSlot.LEGS)
//					,new ItemArmorBase("mor_armor_4", moroonArmorMaterial, 1, EntityEquipmentSlot.FEET)
//			};

	//public static final ItemSkillDecodeItem skillDecodeItem = (ItemSkillDecodeItem) new ItemSkillDecodeItem("skill_decode_item").setRarity(EnumRarity.RARE);

	//Package Example
//	public static final ItemPackage RANDOM_SKILL = (ItemPackage) new ItemPackage("random_skill", new Item[]{
//			Items.BLAZE_ROD, Items.PAPER
//	}).setMaxStackSize(1);
}
