package com.ashawsolutions.waterspile;

import com.ashawsolutions.waterspile.Items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.text.MessageFormat;
import java.util.Locale;

public final class WaterSpile extends JavaPlugin {

    public static WaterSpile Instance;

    private boolean isWaterSpileEnabled = false;
    public CooldownManager CooldownManager = new CooldownManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        getServer().getPluginManager().registerEvents(new WaterSpileEventListener(), this);
        ItemManager.Init();
        getLogger().info("WaterSpile has loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Instance = null;
        getLogger().info("WaterSpile has unloaded");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(label.equalsIgnoreCase("waterspile")){

            if(!sender.hasPermission("panemforever.gamemaker")){
                sender.sendMessage(ChatColor.RED + "" + "You do not have permission to execute this command.");
                return false;
            }

            if(args.length == 0 || args.length > 1){
                sender.sendMessage(ChatColor.RED + "" + "Invalid parameters. Please do /waterspile [on/off]");
                return false;
            }


            String mode = args[0];

            switch(mode.toLowerCase(Locale.ROOT)){
                case "on":
                    isWaterSpileEnabled = true;
                    Player localPlayer = (Player)sender;
                    localPlayer.getInventory().addItem(ItemManager.spile);
                    break;
                case "off":
                    isWaterSpileEnabled = false;
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "" + "Invalid parameters. Please do /waterspile [on/off]");
                    return false;
            }
        }

        return true;
    }



    public boolean isMaterialLog(Material mat){
        switch(mat){
            case ACACIA_LOG:
            case BIRCH_LOG:
            case DARK_OAK_LOG:
            case JUNGLE_LOG:
            case OAK_LOG:
            case SPRUCE_LOG:
                return true;
            default:
                return false;
        }
    }

    public boolean isItemWaterSpile(ItemStack itemStack){

        //This probably needs a better solution.
        if(itemStack.getItemMeta().getDisplayName().equals(ItemManager.spile.getItemMeta().getDisplayName())){
            return true;
        }
        return false;
    }

    public ItemStack generateWaterBottle(){
        ItemStack waterBottleStack = new ItemStack(Material.POTION, 1);
        PotionMeta waterBottleMeta = (PotionMeta)waterBottleStack.getItemMeta();
        waterBottleMeta.setBasePotionData(new PotionData(PotionType.WATER));
        waterBottleStack.setItemMeta(waterBottleMeta);
        return waterBottleStack;
    }
}
