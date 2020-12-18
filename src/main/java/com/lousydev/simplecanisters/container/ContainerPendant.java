package com.lousydev.simplecanisters.container;

import com.lousydev.simplecanisters.handler.registry.ContainerRegistry;
import com.lousydev.simplecanisters.items.canisters.BaseHeartCanister;
import com.lousydev.simplecanisters.handler.ConfigHandler;
import com.lousydev.simplecanisters.util.InventoryUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;


public class ContainerPendant extends Container
{
    public static final String HEART_AMOUNT = "heart_amount";

    private final ItemStackHandler itemHandler;
    private final ItemStack pendant;

    public ContainerPendant(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player, ItemStack pendant)
    {
        super(ContainerRegistry.PENDANT_CONTAINER.get(), windowId);

        this.itemHandler = InventoryUtil.createVirtualInventory(4, pendant);
        this.pendant = pendant;

        this.addSlot(new SlotPendant(this.itemHandler, 0, 80, 9)); //red
        this.addSlot(new SlotPendant(this.itemHandler, 1, 53, 33)); //orange
        this.addSlot(new SlotPendant(this.itemHandler, 2, 107, 33)); //green
        this.addSlot(new SlotPendant(this.itemHandler, 3, 80, 57)); //blue

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 8 + j1 * 18, 84 + l * 18));
            }
        }

        //hotbar
        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 142));
        }
    }



    @Override
    public void onContainerClosed(PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);
        InventoryUtil.serializeInventory(this.itemHandler, this.pendant);
        CompoundNBT nbt = this.pendant.getTag();
        int[] hearts = new int [this.itemHandler.getSlots()];

        for(int i = 0; i < hearts.length; i++)
        {
            ItemStack stack = this.itemHandler.getStackInSlot(i);
            if(!stack.isEmpty())
            {
                hearts[i] = stack.getCount() * 2;
            }
        }

        nbt.putIntArray(HEART_AMOUNT, hearts);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if(slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            stack = slotStack.copy();
            if(index < this.itemHandler.getSlots())
            {
                if(!this.mergeItemStack(slotStack, this.itemHandler.getSlots(), this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }

            else if(!this.mergeItemStack(slotStack, 0, this.itemHandler.getSlots(), false))
            {
                return ItemStack.EMPTY;
            }

            if(slotStack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }

            else
            {
                slot.onSlotChanged();
            }
        }

        return stack;
    }

    private static class SlotPendant extends SlotItemHandler
    {
        public SlotPendant(IItemHandler itemHandler, int index, int xPosition, int yPosition)
        {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack)
        {
            return super.isItemValid(stack) && stack.getItem() instanceof BaseHeartCanister && ((BaseHeartCanister) stack.getItem()).type.ordinal() == this.getSlotIndex();
        }

        @Override
        public int getSlotStackLimit()
        {
            //todo config
            return 10;
        }

    }
}
