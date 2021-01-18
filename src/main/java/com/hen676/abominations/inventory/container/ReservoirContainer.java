package com.hen676.abominations.inventory.container;

import com.hen676.abominations.init.BlockInit;
import com.hen676.abominations.init.ContainerInit;
import com.hen676.abominations.inventory.ReservoirAddSlot;
import com.hen676.abominations.inventory.ReservoirRemoveSlot;
import com.hen676.abominations.inventory.ReservoirResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;

public class ReservoirContainer extends Container {
    private TileEntity tileEntity;
    private IInventory inventory;
    private PlayerEntity playerEntity;

    public ReservoirContainer(int windowId, World world, BlockPos pos, PlayerInventory inv, PlayerEntity player) {
        super(ContainerInit.RESERVOIR_CONTAINER.get(),windowId);
        tileEntity = world.getTileEntity(pos);
        this.inventory = inv;
        this.playerEntity = player;
        if (tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new ReservoirAddSlot(h, 0, 8, 93));
                addSlot(new ReservoirResultSlot(h, 1, 44, 93));
                addSlot(new ReservoirRemoveSlot(h, 2, 116, 93));
                addSlot(new ReservoirResultSlot(h, 3, 152, 93));
            });
        }
        layoutPlayerInventorySlots(8, 125);
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        // 39 - 31 hot bar
        // 30 - 4 inv
        // 0 - 3 container
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(index < 3) {
                if(!this.mergeItemStack(itemstack1,4,this.inventory.getSizeInventory(),true)) return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1,itemstack);
            } else if (index < 31) {
                if (!this.mergeItemStack(itemstack1, 31, 39, false)) return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1,itemstack);
            } else if (index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
                return ItemStack.EMPTY;
            }


            if (index < this.inventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 4, this.inventory.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void putStackInSlot(int slotID, ItemStack stack) {
        super.putStackInSlot(slotID, stack);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(),tileEntity.getPos()), playerEntity, BlockInit.RESERVOIR.get());
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(new InvWrapper(this.inventory), 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(new InvWrapper(this.inventory), 0, leftCol, topRow, 9, 18);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }
}
