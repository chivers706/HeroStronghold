package main.java.multitallented.plugins.herostronghold.listeners;

import com.herocraftonline.dev.heroes.Heroes;
import java.util.logging.Logger;
import main.java.multitallented.plugins.herostronghold.HeroStronghold;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Multitallented
 */
public class PluginServerListener extends ServerListener {
    private HeroStronghold plugin;
    private Heroes heroes;

    public PluginServerListener(HeroStronghold p) {
        this.plugin = p;
    }

    @Override
    public void onPluginDisable(PluginDisableEvent event) {
        Plugin currentPlugin = event.getPlugin();
        String name = currentPlugin.getDescription().getName();

        if (name.equals("Heroes")) {
            Logger log = Logger.getLogger("Minecraft");
            String message = "[HeroStronghold] is disabling itself because " + name + " has been disabled!";
            log.info(message);
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        } else if (name.equals("iConomy") || name.equals("BOSEconomy") || name.equals("Essentials")) {
            Heroes.econ = null;
        }
    }

    @Override
    public void onPluginEnable(PluginEnableEvent event) {
        Plugin currentPlugin = event.getPlugin();
        String name = currentPlugin.getDescription().getName();
        
        if (name.equals("Heroes")) {
            heroes = (Heroes) currentPlugin;
        } else if (name.equals("iConomy") || name.equals("BOSEconomy") || name.equals("Essentials")) {
            if (Heroes.econ == null) {
                this.plugin.setupEconomy();
            }
        }
    }
    
    public void setupHeroes(Heroes heroes) {
        if (this.heroes != null && heroes != null) {
            this.heroes = heroes;
        }
    }
    
    public Heroes getHeroes() {
        return heroes;
    }
}