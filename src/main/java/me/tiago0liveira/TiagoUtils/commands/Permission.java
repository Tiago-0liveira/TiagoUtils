package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

import static me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods.allMatchesStartWith;
import static me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods.someEqualsIgnore;

public class Permission implements TabExecutor {
    public static List<String> subCommands = Arrays.asList("give", "get", "remove");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                if (args.length >= 2) {
                    List<String> permsList = new ArrayList<>();
                    permsList.addAll(Permissions.Commands.commandsList);
                    permsList.addAll(Permissions.Events.eventsList);
                    if (someEqualsIgnore(subCommands, args[0])) {
                        if (someEqualsIgnore(permsList, args[1]) || args[1].equals("all")) {
                            try {
                                if (args[2] != null) {
                                    Player chosenPlayer = TiagoUtils.getPlugin().getServer().getPlayer(args[2]);
                                    if (chosenPlayer != null) {
                                        applyPerm(player, chosenPlayer, args[0], args[1]);
                                    } else {
                                        player.sendMessage(ChatColor.DARK_GRAY + "The player " + ChatColor.RED + args[2] + ChatColor.DARK_GRAY + "Does not exist!");
                                    }
                                } else {
                                    applyPerm(player, player, args[0], args[1]);
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                applyPerm(player, player, args[0], args[1]);
                            }

                        } else {
                            player.sendMessage(ChatColor.WHITE + "\"" + ChatColor.RED + args[1] + ChatColor.WHITE + "\"" + ChatColor.DARK_GRAY + "Is" + ChatColor.RED + " invalid" + ChatColor.DARK_GRAY + "!");
                        }
                    } else {
                        player.sendMessage(ChatColor.WHITE + "\"" + ChatColor.RED + args[0] + ChatColor.WHITE + "\"" + ChatColor.DARK_GRAY + "Is" + ChatColor.RED + " invalid" + ChatColor.DARK_GRAY + "!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Wrong " + ChatColor.DARK_GRAY + "command format!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "You need " + ChatColor.RED + "permission" + ChatColor.DARK_GRAY + " to use this command!");
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                return subCommands;
            } else if (args.length == 2) {
                List<String> rList = new ArrayList<>();
                if (args[0].equals("get")) rList.add("all");
                try {
                    rList.addAll(allMatchesStartWith(Permissions.Commands.commandsList, args[1]));
                    rList.addAll(allMatchesStartWith(Permissions.Events.eventsList, args[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    rList.addAll(Permissions.Commands.commandsList);
                    rList.addAll(Permissions.Events.eventsList);
                }
                return rList;
            }
        }
        return null;
    }
    public static void applyPerm(Player sender, Player target, String command, String perm) {
        System.out.println("perm -> " + perm);
        switch (command) {
            case "give":
                TiagoUtils.PermManager.givePermission(target, perm);
                if (target.getUniqueId() != sender.getUniqueId()) {
                    sender.sendMessage(ChatColor.DARK_GRAY + "You " + ChatColor.GREEN + "successfully" + ChatColor.DARK_GRAY + " gave " + ChatColor.DARK_AQUA + target.getDisplayName() +  "the permission " + ChatColor.AQUA + perm);
                    target.sendMessage(ChatColor.DARK_GRAY + "You now have " + ChatColor.GREEN + "permission" + ChatColor.DARK_GRAY + " to " + ChatColor.AQUA + perm);
                } else {
                    sender.sendMessage(ChatColor.DARK_GRAY + "You " + ChatColor.GREEN + "gave" + ChatColor.DARK_GRAY + " yourself " + ChatColor.DARK_AQUA + target.getDisplayName() +  "the permission " + ChatColor.AQUA + perm);
                }
                break;
            case "get":
                if (perm.equals("all")) {
                    HashMap<String, Boolean> hashMap = TiagoUtils.PermManager.getAllPermissions(target);
                    if (!hashMap.isEmpty()) {
                        sender.sendMessage(ChatColor.UNDERLINE + "" + ChatColor.AQUA + target.getDisplayName() + ChatColor.DARK_GRAY + "'s " + ChatColor.DARK_GRAY + "permissions" + ChatColor.WHITE + ":");
                        hashMap.forEach((String s, Boolean bool) -> {
                            sender.sendMessage(ChatColor.DARK_GRAY + "  -> " + s + " is " + (bool ? ChatColor.GREEN : ChatColor.RED) + bool);
                        });
                    } else {
                        sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.DARK_GRAY + " has " + ChatColor.RED + "no permissions" + ChatColor.DARK_GRAY + " atm!");
                    }
                } else {
                    boolean bool = TiagoUtils.PermManager.hasPermission(target, perm);
                    sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.DARK_GRAY + " -> " + perm + " is " + (bool ? ChatColor.GREEN : ChatColor.RED) + bool);
                }
                break;
            case "remove":
                if (TiagoUtils.PermManager.hasPermission(target, perm)) {
                    TiagoUtils.PermManager.removePermission(target, perm);
                    if (target.getUniqueId() != sender.getUniqueId()) {
                        sender.sendMessage(ChatColor.DARK_GRAY + "You have " + ChatColor.RED + "taken " + ChatColor.WHITE + perm + ChatColor.DARK_GRAY + " from " + ChatColor.AQUA + target.getDisplayName());
                        target.sendMessage(ChatColor.DARK_GRAY + "The permission" + perm + " has been " + ChatColor.RED + "taken" + ChatColor.DARK_GRAY + " from " + ChatColor.AQUA + "you" + ChatColor.DARK_GRAY + "!");
                    } else {
                        target.sendMessage(ChatColor.DARK_GRAY + "You have just " + ChatColor.RED + "removed" + ChatColor.GREEN + " your " + ChatColor.WHITE + perm + ChatColor.DARK_GRAY + " permission ");
                    }
                } else {
                    if (target.getUniqueId() != sender.getUniqueId()) {
                        sender.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.RED + " Does not" + ChatColor.DARK_GRAY + " have " + ChatColor.WHITE + perm + ChatColor.DARK_GRAY + " already!" );
                    } else {
                        sender.sendMessage(ChatColor.AQUA + "You " + ChatColor.DARK_GRAY + "already do " + ChatColor.RED + "not " + ChatColor.DARK_GRAY + "have " + ChatColor.WHITE + perm + ChatColor.DARK_GRAY + "!");
                    }
                }
                break;
            default:
                break;
        }
    }
}
