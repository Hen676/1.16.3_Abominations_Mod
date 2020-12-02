package com.hen676.abominations.block;

import com.hen676.abominations.tileEntity.IncineratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class IncineratorBlock extends AbstractMachineBlock {
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    public IncineratorBlock() {
        super(Properties
                .create(Material.IRON)
                .hardnessAndResistance(5.0f,30.0f)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
        );
        this.getDefaultState().with(ENABLED, Boolean.valueOf(false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(ENABLED, Boolean.valueOf(false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ENABLED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        boolean flag = worldIn.isBlockPowered(pos);
        if (flag != state.get(ENABLED)) {
            worldIn.setBlockState(pos, state.with(ENABLED, Boolean.valueOf(flag)), 2);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new IncineratorTileEntity();
    }
}
