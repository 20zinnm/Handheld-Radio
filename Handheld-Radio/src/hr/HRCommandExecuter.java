package hr;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HRCommandExecuter implements CommandExecutor {
	private final static HashMap<String, Long> lastCommands = new HashMap<String, Long>();
	static HandheldRadio hr = new HandheldRadio();
    static long cooldown = hr.getConfig().getLong("delay")*20;
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("shout")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Lol, only ingame players can shout!");
				return true;
			}
			if (args.length < 1) {
				sender.sendMessage("What's your message?");
				return true;
			}
			Long lastCommand = lastCommands.get(sender.getName());
	        if (lastCommand == null || lastCommand + cooldown < System.currentTimeMillis()) {
	        	Player p = (Player) sender;
				HandheldRadio hr = new HandheldRadio();
				if (p.getInventory().getItemInHand().getTypeId() == hr.getConfig().getInt("item")) {
					sender.sendMessage("Tuning into radio frequency 10...");					
					Bukkit.broadcastMessage(ChatColor.RED + "[G]" + ChatColor.BLACK + p.getDisplayName() +  ": " + ChatColor.RESET  + args.toString());
					lastCommands.put(sender.getName(), System.currentTimeMillis());
				} else if (lastCommand != null) {
		            sender.sendMessage("You have to wait "+ HandheldRadio.formatTime(cooldown - (System.currentTimeMillis() - lastCommand))+" before you can use this command again!");
		        }
			}
				return true;
	        
		}
		return false;
	}

}
