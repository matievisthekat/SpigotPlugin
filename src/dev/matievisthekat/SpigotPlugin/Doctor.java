package dev.matievisthekat.SpigotPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Doctor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot use this in the console");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("doctor.use")) {
			player.sendMessage(ChatColor.RED + "You do not have permission!");
			return true;
		}
		
		if (args.length == 0) {
			TextComponent message = new TextComponent("Would you like to be healed?");
			message.setColor(ChatColor.GOLD);
			message.setBold(true);
			message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/doctor healme"));
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					new ComponentBuilder("Click here to be healed!").color(ChatColor.GRAY).italic(true).create()));
			
			player.spigot().sendMessage(message);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("healme")) {
				player.setHealth(20.0);
				// Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + player.getName());
				player.sendMessage(ChatColor.GREEN + "You have been healed!");
				return true;
			}
		}
		
		return false;
	}

}
