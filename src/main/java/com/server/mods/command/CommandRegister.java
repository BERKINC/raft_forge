package com.server.mods.command;

import net.minecraft.server.commands.StopCommand;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandRegister {
    private static final List<ICommand> commands = new ArrayList<>();

    static {
        // Добавляем все команды в список
        commands.add(new StartCommand());
        commands.add(new RaftCommand());

    }

    public static void registerAll(RegisterCommandsEvent event) {
        for (ICommand command : commands) {
            command.register(event);
        }
    }
}