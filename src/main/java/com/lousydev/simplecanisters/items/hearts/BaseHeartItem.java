package com.lousydev.simplecanisters.items.hearts;

import com.lousydev.simplecanisters.SimpleCanisters;
import com.lousydev.simplecanisters.util.HeartType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BaseHeartItem extends Item
{

    protected final HeartType type;

    public BaseHeartItem(HeartType type)
    {
        super(new Item.Properties().group(SimpleCanisters.CREATIVE_TAB));

        this.type = type;
    }

    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 30;
    }

    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.EAT;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        playerIn.setActiveHand(handIn);

        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving)
    {
        if(!worldIn.isRemote && entityLiving instanceof PlayerEntity)
        {
            PlayerEntity  player = (PlayerEntity)entityLiving;
            player.heal(this.type.healAmount);

            if(!player.isCreative())
            {
                stack.shrink(1);
            }
        }

        return  stack;
    }
}
