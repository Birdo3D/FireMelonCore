package fr.birdo.firemeloncore.events;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FMCCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command c, String s, String[] args) {

        if (args.length >= 1) {


            List<String> output_args = new ArrayList<>();
            for (String command : Commands.FMC_COMMANDS) {

                String[] command_args = command.split(" ");

                if (command_args.length >= args.length) {

                    String active_arg = command_args[args.length - 1];

                    if (args.length > 1) {
                        if (Arrays.equals(Arrays.copyOf(command_args, args.length - 1), Arrays.copyOf(args, args.length - 1))) {
                            if (active_arg.contains(args[args.length - 1]) && active_arg.indexOf(args[args.length - 1]) == 0) {
                                if (!output_args.contains(active_arg))
                                    output_args.add(active_arg);
                            }
                        }
                    }else{
                        if (active_arg.contains(args[args.length - 1]) && active_arg.indexOf(args[args.length - 1]) == 0) {
                            if (!output_args.contains(active_arg))
                                output_args.add(active_arg);
                        }
                    }
                }
            }
            if(output_args.isEmpty())
                return null;
            return output_args;
        }

        return null;
    }
}