package com.lousydev.simplecanisters.items;

import com.lazy.baubles.api.bauble.BaubleType;
import com.lazy.baubles.api.bauble.IBauble;

import com.lousydev.simplecanisters.SimpleCanisters;
import com.lousydev.simplecanisters.container.ContainerPendant;
import com.lousydev.simplecanisters.handler.ConfigHandler;
import com.lousydev.simplecanisters.handler.registry.ItemRegistry;
import com.lousydev.simplecanisters.util.HeartType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class ItemHeartAmulet extends BaseItem implements IBauble
{
    public ItemHeartAmulet()
    {

    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack)

    {
        return BaubleType.AMULET;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if(playerIn.isSneaking())
        {
            ItemStack stack = playerIn.getHeldItem(handIn);

            INamedContainerProvider provider = new INamedContainerProvider()
            {
                @Override
                public ITextComponent getDisplayName()
                {
                    return new TranslationTextComponent("screen.simplecanisters.amulet.name");
                }

                @Nullable
                @Override
                public Container createMenu(int i, PlayerInventory inventory, PlayerEntity playerEntity)
                {
                    return new ContainerPendant(i, playerEntity.getEntityWorld(), playerEntity.getPosition(), inventory, playerEntity, new ItemStack(ItemRegistry.HEART_AMULET.get()));
                }
            };

            if(!worldIn.isRemote)
            {
                playerIn.openContainer(provider);
            }

            return new ActionResult<>(ActionResultType.SUCCESS, stack);
        }

        else
        {
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }
    }

    public int[] getHeartCount(ItemStack stack)
    {
        if(stack.hasTag())
        {
            CompoundNBT nbt = stack.getTag();
            if(nbt.contains(ContainerPendant.HEART_AMOUNT, Constants.NBT.TAG_INT_ARRAY))
            {
                return nbt.getIntArray(ContainerPendant.HEART_AMOUNT);
            }
        }

        return new int[HeartType.values().length];
    }
}
