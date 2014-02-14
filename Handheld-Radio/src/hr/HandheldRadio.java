package hr;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class HandheldRadio extends JavaPlugin {

	public static String[] delaylist;
	
	public void onEnable() {
		if (!new File(getDataFolder(), "config.yml").exists()) {
		     saveDefaultConfig();
		}
		getCommand("shout").setExecutor(new HRCommandExecuter());
		getLogger().info("Handheld Radio v0.1 Enabled!");
	}
	public void onDisable() {
		getLogger().info("Handheld Radio v0.1 Enabled!");
	}
	static String formatTime(long time) {
        String r = "";
        if (time >= 60000)
            r += Math.floor(time/60000) + " minutes and ";
        r += Math.ceil(time%60000/1000)+" seconds";
        return r;
    }
	
}
