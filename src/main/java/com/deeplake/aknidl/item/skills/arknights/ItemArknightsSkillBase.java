package com.deeplake.aknidl.item.skills.arknights;

import com.deeplake.aknidl.IdlFramework;
import com.deeplake.aknidl.item.skills.ItemSkillBase;
import com.deeplake.aknidl.util.CommonFunctions;
import com.deeplake.aknidl.util.IDLSkillNBT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static com.deeplake.aknidl.util.CommonDef.TICK_PER_SECOND;

public class ItemArknightsSkillBase extends ItemSkillBase {

    protected int[] dura = {10};
    //int[] cd = {90};
    protected int[] initPower = {0};

    protected int[] max_charge = {5};

    public boolean isAutoCharge = true;
    public boolean isAttackCharge = false;
    public boolean isHurtCharge = false;

    public ItemArknightsSkillBase(String name) {
        super(name);
        CommonFunctions.addToEventBus(this);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 100;
    }

    public double getValue(ItemStack stack, double[] values)
    {
        int level = IDLSkillNBT.getLevel(stack) - 1;

        if (level < 0){
            return values[0];
        }
        else if(level >= values.length)
        {
            return values[values.length - 1];
        }

        return values[level];
    }

    public float getInitPower(ItemStack stack)
    {
        int level = IDLSkillNBT.getLevel(stack) - 1;

        if (level < 0){
            return initPower[0];
        }
        else if(level >= initPower.length)
        {
            return initPower[initPower.length - 1];
        }

        return initPower[level];
    }

    public float getDurationMax(ItemStack stack)
    {
        int level = IDLSkillNBT.getLevel(stack) - 1;

        if (level < 0){
            return dura[0];
        }
        else if(level >= dura.length)
        {
            return dura[dura.length - 1];
        }

        return dura[level];
    }

    public float getChargeMax(ItemStack stack)
    {
        int level = IDLSkillNBT.getLevel(stack) - 1;

        if (level < 0){
            return max_charge[0];
        }
        else if(level >= max_charge.length)
        {
            return max_charge[max_charge.length - 1];
        }

        return max_charge[level];
    }

    @Override
    public float getCoolDown(ItemStack stack) {
        return getChargeMax(stack);
    }

    @Override
    public float getDura(ItemStack stack) {
        return getDurationMax(stack);
    }

    public void upkeep(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (worldIn.isRemote || worldIn.getWorldTime() % TICK_PER_SECOND != 0)
        {
            return;
        }

        if (entityIn instanceof EntityPlayer)
        {
            boolean casting = IDLSkillNBT.IsCasting(stack);
            EntityPlayer player = (EntityPlayer) entityIn;
            player.getCooldownTracker().setCooldown(stack.getItem(), casting ? 3600 : 0);
            if (casting)
            {
                float dura = IDLSkillNBT.GetDura(stack);
                if (worldIn.getWorldTime() % TICK_PER_SECOND == 0)
                {
                    dura -= 1f;
                }

                if (dura <= 0)
                {
                    dura = 0;
                    IDLSkillNBT.SetCasting(stack, false);
                    activateCoolDownArknights(stack);
                    IdlFramework.Log("%s casting complete.", entityIn);
                }

                IDLSkillNBT.SetDura(stack, dura);
            }
            else {
                if (isAutoCharge)
                {
                    float charge = IDLSkillNBT.GetCharge(stack);
                    float curMaxCharge = getChargeMax(stack);

                    if (charge >= curMaxCharge)
                    {
                        if (charge > curMaxCharge)
                        {
                            charge = curMaxCharge;
                            IDLSkillNBT.SetCharge(stack, curMaxCharge);
                        }
                    }
                    else {
                        charge += 1f;
                        IDLSkillNBT.SetCharge(stack, charge);
                    }
                }
            }

            if (casting)
            {
                float maxDura = getDurationMax(stack);
                if (maxDura == 0)
                {
                    stack.setItemDamage((int) ((1 - IDLSkillNBT.GetDura(stack) / 1) * getMaxDamage(stack)));
                }
                else {
                    stack.setItemDamage((int) ((1 - IDLSkillNBT.GetDura(stack) / getDurationMax(stack)) * getMaxDamage(stack)));
                }

            }else {
                stack.setItemDamage((int) ((1 - IDLSkillNBT.GetCharge(stack) / getChargeMax(stack)) * getMaxDamage(stack)));
            }
        }
    }

    void chargeItem(ItemStack stack)
    {
        boolean casting = IDLSkillNBT.IsCasting(stack);
        if (!casting)
        {
            float charge = IDLSkillNBT.GetCharge(stack);
            float curMaxCharge = getChargeMax(stack);

            if (charge >= curMaxCharge)
            {
                if (charge > curMaxCharge)
                {
                    charge = curMaxCharge;
                    IDLSkillNBT.SetCharge(stack, curMaxCharge);
                }
            }
            else {
                charge += 1f;
                IDLSkillNBT.SetCharge(stack, charge);
            }
        }
    }

    protected void onUserDamageOther(LivingHurtEvent event)
    {

    }

    protected void onUserHurt(LivingHurtEvent event)
    {

    }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent event)
    {
        //todo: test if it works.
        EntityLivingBase hurtOne = event.getEntityLiving();
        World world = hurtOne.world;

        if (world.isRemote)
        {
            return;
        }

        ItemStack stack = hurtOne.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
        if (stack.getItem() instanceof ItemArknightsSkillBase)
        {
            ItemArknightsSkillBase skill = (ItemArknightsSkillBase) stack.getItem();
            skill.onUserHurt(event);
            if (skill.isHurtCharge)
            {
                chargeItem(stack);
            }
        }

        Entity attacker = event.getSource().getTrueSource();
        if (attacker instanceof EntityLivingBase)
        {
            stack = ((EntityLivingBase)(attacker)).getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
            if (stack.getItem() instanceof ItemArknightsSkillBase)
            {
                ItemArknightsSkillBase skill = (ItemArknightsSkillBase) stack.getItem();
                skill.onUserDamageOther(event);
                if (skill.isAttackCharge)
                {
                    chargeItem(stack);
                }
            }
        }
    }

    public void activateCoolDownArknights(ItemStack stack)
    {
        Item item = stack.getItem();
        if (item instanceof ItemSkillBase)
        {
            IDLSkillNBT.SetCasting(stack, false);
            IDLSkillNBT.SetCharge(stack, 0);
        }
    }

    public boolean canCast(EntityLivingBase livingBase)
    {
        return tryCast(livingBase.world, livingBase, EnumHand.OFF_HAND);
    }

    @Override
    public boolean canCast(World worldIn, EntityLivingBase livingBase, EnumHand handIn) {
        ItemStack stack = livingBase.getHeldItem(handIn);

        boolean casting = IDLSkillNBT.IsCasting(stack);
        if (casting)
        {
            return false;
        }

        float charge = IDLSkillNBT.GetCharge(stack);
        float curMaxCharge = getChargeMax(stack);

        if (charge >= curMaxCharge)
        {
            return super.canCast(worldIn, livingBase, handIn);
        }
        else {
            return false;
        }
    }

    public boolean tryCast(EntityLivingBase livingBase)
    {
        return tryCast(livingBase.world, livingBase, EnumHand.OFF_HAND);
    }

    @Override
    public boolean tryCast(World worldIn, EntityLivingBase livingBase, EnumHand handIn) {

        ItemStack stack = livingBase.getHeldItem(handIn);
        IDLSkillNBT.SetCasting(stack, true);
        IDLSkillNBT.SetCharge(stack, 0);
        IDLSkillNBT.SetDura(stack, getDurationMax(stack));
        trySayDialogue(livingBase, stack);

        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        upkeep(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    public void tryToInitializeCharge(EntityPlayer player)
    {
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack itemstack = player.inventory.getStackInSlot(i);
            {
                if (itemstack.getItem() == this)
                {
                    if (!IDLSkillNBT.IsCasting(itemstack))
                    {
                        float power = getInitPower(itemstack);
                        IDLSkillNBT.SetCharge(itemstack, power);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLogIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (!event.player.world.isRemote)
            tryToInitializeCharge(event.player);
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event)
    {
        if (event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if (!player.world.isRemote)
                tryToInitializeCharge(player);
        }
    }
}
