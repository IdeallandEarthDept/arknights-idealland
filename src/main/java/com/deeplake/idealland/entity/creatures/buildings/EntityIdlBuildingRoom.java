package com.deeplake.idealland.entity.creatures.buildings;

import com.deeplake.idealland.IdlFramework;
import com.deeplake.idealland.init.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.deeplake.idealland.util.CommonDef.TICK_PER_SECOND;

public class EntityIdlBuildingRoom extends EntityIdlBuildingBase {

    private int size = 3;

    public EntityIdlBuildingRoom(World worldIn) {
        super(worldIn);
        setAttr(1, 0, 0, 8, 5);
        size = ModConfig.DEBUG_CONF.ROOM_SIZE;
        buildingCore.ResetTasks();
        InitTaskQueue();
        buildingCore.setSpeed(40f / TICK_PER_SECOND);
        IdlFramework.LogWarning("Summon. Size = " + size);
    }

    void InitTaskQueue()
    {
        BlockPos origin = new BlockPos(0,0,0);

        if (buildingCore == null)
        {
            IdlFramework.LogWarning("Core is null");
        }

        int bottomRange = size;
        int wallHeight = 2 * size + 1;

        IdlFramework.LogWarning("Size = " + size);

        IBlockState blockState = Blocks.IRON_BLOCK.getDefaultState();

        //floor
        buildingCore.AddTaskBuildWallWithBlockCentered(origin.add(0, -1, 0), bottomRange, 1, bottomRange, blockState);

        //ceiling
        buildingCore.AddTaskBuildWallWithBlockCentered(origin.add(0, wallHeight, 0), bottomRange, 1, bottomRange, blockState);

        //surrounding
        buildingCore.AddTaskBuildWallWithBlockCentered(origin.add(0, 0, bottomRange + 1), bottomRange + 1, wallHeight, 0, blockState);
        buildingCore.AddTaskBuildWallWithBlockCentered(origin.add(0, 0, -bottomRange - 1), bottomRange + 1, wallHeight, 0, blockState);
        buildingCore.AddTaskBuildWallWithBlockCentered(origin.add(bottomRange + 1, 0, 0), 0, wallHeight, bottomRange, blockState);
        buildingCore.AddTaskBuildWallWithBlockCentered(origin.add(-bottomRange - 1, 0, 0), 0, wallHeight, bottomRange, blockState);
    }
}
