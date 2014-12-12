package com.InfinityRaider.AgriCraft.compatibility.waila;

import com.InfinityRaider.AgriCraft.blocks.BlockCrop;
import com.InfinityRaider.AgriCraft.init.Items;
import com.InfinityRaider.AgriCraft.tileentity.TileEntityCrop;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public class AgriCraftCropDataProvider implements IWailaDataProvider {

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        Block block = dataAccessor.getBlock();
        if(block instanceof BlockCrop) {
            return new ItemStack(Items.crops, 1, 0);
        }
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        return list;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        Block block = dataAccessor.getBlock();
        TileEntity te = dataAccessor.getTileEntity();
        if(block!=null && block instanceof BlockCrop && te!=null && te instanceof TileEntityCrop) {
            TileEntityCrop crop = (TileEntityCrop) te;
            int growth = crop.growth;
            int gain = crop.gain;
            int strength = crop.strength;
            String seedName = ((ItemSeeds) crop.seed).getItemStackDisplayName(new ItemStack((ItemSeeds) crop.seed, 1, crop.seedMeta));
            list.add("Seed: "+seedName);
            list.add(" - Growth: "+growth);
            list.add(" - Gain: "+gain);
            list.add(" - Strength: "+strength);
        }
        return list;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor dataAccessor, IWailaConfigHandler configHandler) {
        return list;
    }
}