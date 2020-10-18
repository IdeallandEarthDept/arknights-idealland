package com.deeplake.aknidl.item.skills.arknights.silverash;

import com.deeplake.aknidl.item.skills.arknights.ItemArknightsSkillBase;
import com.deeplake.aknidl.util.IDLSkillNBT;
import com.deeplake.aknidl.util.NBTStrDef.IDLNBTUtil;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.UUID;

import static com.deeplake.aknidl.util.NBTStrDef.IDLNBTDef.LEVEL;

public class ItemSvrashPowerStrike extends ItemArknightsSkillBase {

    //need to figure out why it triggers twice.

    UUID uuid = UUID.fromString("48e0a424-20f2-4b4d-b24d-478fe24dc73d");
    double[] atkPlus = {0.9, 0.95, 1, 1.05, 1.1, 1.15, 1.25, 1.45, 1.65, 1.90};

    public ItemSvrashPowerStrike(String name) {
        super(name);
        isAutoCharge = false;
        isAttackCharge = true;
        offHandCast = false;

        dura = new int[]{1};
        max_charge = new int[]{4,4,4,3,3,3,3,3,3,2};
        //max_charge = new int[]{3};
        initPower = new int[]{0,0,0,0,0,0,0,0,0,0};

        showCDDesc = false;

        logNBT = true;
    }

    @Override
    public void onMouseFire(EntityPlayer player) {
        super.onMouseFire(player);
        if (canCast(player))
        {
            tryCast(player);
        }
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

        float charge = IDLSkillNBT.GetCharge(stack);
        float curMaxCharge = getChargeMax(stack);

        boolean isOn = charge == curMaxCharge;

        if (isOn && equipmentSlot == EntityEquipmentSlot.OFFHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(uuid, "Power Strike Modifier", (double)getValue(stack, atkPlus), 1));
        }

        return multimap;
    }
}
