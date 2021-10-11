package jp.yama2211.sbex;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
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

    @EventHandler
    public void sbExplosion(ProjectileHitEvent event){

        if(getConfig().getBoolean("Snowball")){
            Projectile p = event.getEntity();

            if(p instanceof Snowball){
                if(getConfig().getBoolean("Explosion")){
                World world = p.getWorld();
                Location loc = p.getLocation();

                Float ft = (float)getConfig().getDouble("kibo");
                Boolean fire = getConfig().getBoolean("fire");
                Boolean block = getConfig().getBoolean("block");
                world.createExplosion(loc,ft,fire,block);
                } else {
                    if(getConfig().getBoolean("Thunder")){
                        Location loc = p.getLocation();
                        loc.getWorld().strikeLightning(loc);
                    } else {
                        Location loc = p.getLocation();
                        loc.getWorld().strikeLightningEffect(loc);
                    }
                }
            }
        }

        if(getConfig().getBoolean("Arrow")){
            Projectile p = event.getEntity();

            if(p instanceof Arrow){
                if(getConfig().getBoolean("Explosion")){
                    World world = p.getWorld();
                    Location loc = p.getLocation();

                    Float ft = (float)getConfig().getDouble("kibo");
                    Boolean fire = getConfig().getBoolean("fire");
                    Boolean block = getConfig().getBoolean("block");
                    world.createExplosion(loc,ft,fire,block);
                } else {
                    if(getConfig().getBoolean("Thunder")){
                        Location loc = p.getLocation();
                        loc.getWorld().strikeLightning(loc);
                    } else {
                        Location loc = p.getLocation();
                        loc.getWorld().strikeLightningEffect(loc);
                    }
                }
            }
        }

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("sbex")) {
            if (args.length == 0) {
                sender.sendMessage("----| " + ChatColor.GREEN + "sbExplosion" + ChatColor.RESET + " |----");
                sender.sendMessage("/sbex reload : Configリロード");
            }
            if (args.length == 1) {
                if(sender.hasPermission("sbex.reload")){
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadConfig();
                        sender.sendMessage(ChatColor.GREEN + "リロードしました");
                    }
                }
            }
        }
        return false;
    }
}
