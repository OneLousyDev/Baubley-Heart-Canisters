package com.lousydev.simplecanisters.handler;

import com.google.common.base.Preconditions;
import com.lazy.baubles.api.cap.BaublesCapabilities;
import com.lazy.baubles.api.cap.IBaublesItemHandler;
import com.lousydev.simplecanisters.Reference;
import com.lousydev.simplecanisters.items.canisters.BaseHeartCanister;
import com.lousydev.simplecanisters.items.ItemHeartAmulet;
import com.lousydev.simplecanisters.util.HeartType;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class HealthHandler
{
    private static final UUID HEALTH_MODIFIER = UUID.fromString("caa44aa0-9e6e-4a57-9759-d2f64abfb7d3");

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && !event.player.world.isRemote && event.player.openContainer == event.player.container && event.player instanceof ServerPlayerEntity && ((ServerPlayerEntity) event.player).world.getGameTime() % 10 == 0)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) event.player;

            ModifiableAttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);

            if(player.getCapability(BaublesCapabilities.BAUBLES).isPresent())
            {
                LazyOptional<IBaublesItemHandler> baublesInventory = player.getCapability(BaublesCapabilities.BAUBLES);
                float diff = player.getMaxHealth() - player.getHealth();
                int[] hearts = new int[HeartType.values().length];

                for(int slot = 0; slot < baublesInventory.resolve().get().getSlots(); slot++)
                {
                    ItemStack slotStack = baublesInventory.resolve().get().getStackInSlot(slot);
                    if(!slotStack.isEmpty())
                    {
                        if(slotStack.getItem() instanceof BaseHeartCanister)
                        {
                            HeartType type = ((BaseHeartCanister) slotStack.getItem()).type;
                            hearts[type.ordinal()] += slotStack.getCount() * 2;
                        }

                        else if(slotStack.getItem() instanceof ItemHeartAmulet)
                        {
                            int[] pendantHearts = ((ItemHeartAmulet) slotStack.getItem()).getHeartCount(slotStack);
                            Preconditions.checkArgument(pendantHearts.length == HeartType.values().length, "Array must be same length as enum length!");

                            for(int i = 0; i < hearts.length; i++)
                            {
                                hearts[i] += pendantHearts[i];
                            }
                        }
                    }
                }
                int extraHearts = 0;

                for(int i = 0; i < hearts.length; i++)
                {
                    extraHearts += MathHelper.clamp(hearts[i], 0, 10 * 2); //make sure to not bypass the limit; bugfix: this is half hearts, so we need to double the limit...
                }

                AttributeModifier modifier = health.getModifier(HEALTH_MODIFIER);
                if(modifier != null)
                {
                    if(modifier.getAmount() == extraHearts) return;
                    else health.removeModifier(modifier);
                }

                health.applyPersistentModifier(new AttributeModifier(HEALTH_MODIFIER, Reference.MODID + ":extra_hearts", extraHearts, AttributeModifier.Operation.byId(0)));
                float amount = MathHelper.clamp(player.getMaxHealth() - diff, 0.0F, player.getMaxHealth()); //bugfix: death by removing heart canisters could cause loss of items!
                if(amount > 0.0F) 
                {
                    player.setHealth(amount);
                }

                else
                {
                    player.closeContainer();
                    player.onKillCommand();
                }
            }
        }
    }
}
