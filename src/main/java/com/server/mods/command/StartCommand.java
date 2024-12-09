package com.server.mods.command;

import com.server.mods.world.WorldGenerationHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;

public class StartCommand implements ICommand {
    @Override
    public void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(net.minecraft.commands.Commands.literal("start").executes(context -> {
            CommandSourceStack source = context.getSource();
            if (source.getEntity() instanceof ServerPlayer player) {
                ServerLevel oceanWorld = source.getServer().getLevel(ServerLevel.OVERWORLD);
                if (oceanWorld != null) {
                    BlockPos spawnPos = WorldGenerationHandler.generateRaftForPlayer(oceanWorld, player);
                    player.teleportTo(
                            oceanWorld,
                            spawnPos.getX() + 0.5,
                            spawnPos.getY(),
                            spawnPos.getZ() + 0.5,
                            player.getYRot(),
                            player.getXRot()
                    );
                }
            }
            return 1;
        }));
    }

}

