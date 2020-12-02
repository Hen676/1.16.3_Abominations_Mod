package com.hen676.abominations.block;

import com.hen676.abominations.tileEntity.ForgeTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class ForgeBlock extends AbstractMachineBlock {
    public ForgeBlock() {
        super(Properties
                .create(Material.IRON)
                .hardnessAndResistance(5.0f,30.0f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ForgeTileEntity();
    }
}
