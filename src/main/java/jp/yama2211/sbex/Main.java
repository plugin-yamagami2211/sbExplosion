package jp.yama2211.sbex;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void sbExplosion(ProjectileHitEvent event){
        Projectile p = event.getEntity();

        if(p instanceof Snowball){
            World world = p.getWorld();
            Location loc = p.getLocation();

            Float ft = (float)getConfig().getDouble("kibo");
            world.createExplosion(loc,ft);
        }
    }
}
