package com.inkblur.bukkit.register;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Registration extends JavaPlugin {
	private Mongo mongo;
	@Override
	public void onEnable(){
		Plugin mongodb = getServer().getPluginManager().getPlugin("mongodb");
		if(mongodb != null && mongodb.isEnabled()){
			mongo = new Mongo(this);
		} else {
			getLogger().log(Level.SEVERE, "mongodb plugin not enabled!");
		}
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player))
			return false;
		if(args.length == 1){
			if(mongo.update(sender.getName(), BCrypt.hashpw( args[0], BCrypt.gensalt(12) ))){
				sender.sendMessage(ChatColor.GREEN+"Your webapp password has been changed.");
				return true;
			}
		}
		return false;
	}
}
