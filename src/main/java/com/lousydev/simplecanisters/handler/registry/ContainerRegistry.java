package com.lousydev.simplecanisters.handler.registry;

import com.lousydev.simplecanisters.Reference;
import com.lousydev.simplecanisters.container.ContainerPendant;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ContainerRegistry
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MODID);

    public static final RegistryObject<ContainerType<ContainerPendant>> PENDANT_CONTAINER = CONTAINERS.register("pendant", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        ItemStack pendant = new ItemStack(ItemRegistry.HEART_AMULET.get());
        return new ContainerPendant(windowId, world, pos, inv, inv.player, pendant);
    }));

    public static void registerContainers()
    {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}

