package net.cyberflame.lumberharvester.commands;

import net.cyberflame.lumberharvester.Main;
import net.cyberflame.lumberharvester.utils.FormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BypassCommand implements CommandExecutor {

    private final Main instance = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final String permission = "lh.bypass";
        if (!sender.hasPermission(permission)) {
            String noPermission = FormatUtils.NO_PERMISSION;
            FormatUtils.sendMessage(sender, noPermission);
            return true;
        }
        if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                String targetName = Bukkit.getPlayer(args[0]).getName();
                instance.setBypassing(Bukkit.getPlayer(args[0]).getUniqueId());
                FormatUtils.sendMessage(sender, (instance.getBypassing(target.getUniqueId()) ?
                                                         FormatUtils.BYPASS_OTHER_TOGGLE_ON.replaceAll("%player%",
                                                                                                    targetName) :
                                                         FormatUtils.BYPASS_OTHER_TOGGLE_OFF.replaceAll("%player%",
                                                                                                     targetName)));
                FormatUtils.sendMessage(target, (instance.getBypassing(target.getUniqueId()) ?
                                                         FormatUtils.BYPASS_TOGGLE_ON.replaceAll("%player%",
                                                                                               targetName) :
                                                         FormatUtils.BYPASS_TOGGLE_OFF.replaceAll("%player%",
                                                                                               targetName)));
            }
            else FormatUtils.sendMessage(sender, FormatUtils.UNKNOWN_PLAYER.replaceAll("%player%", args[0]));
            return true;
        }
        Player player = (Player) sender;
        instance.setBypassing(player.getUniqueId());
        FormatUtils.sendMessage(player, (instance.getBypassing(player.getUniqueId()) ?
                                                 FormatUtils.BYPASS_TOGGLE_ON.replaceAll("%player%", player.getName()) :
                                                 FormatUtils.BYPASS_TOGGLE_OFF.replaceAll("%player%",
                                                                                         player.getName())));
        return true;
    }
}