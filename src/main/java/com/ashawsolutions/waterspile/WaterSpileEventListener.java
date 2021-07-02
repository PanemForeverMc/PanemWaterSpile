package com.ashawsolutions.waterspile;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaterSpileEventListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){



        Player player = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_BLOCK){
            Material clickedBlockMat = e.getClickedBlock().getType();
            ItemStack playerItemStack = e.getItem();

            if(playerItemStack == null)
                return;

            if(WaterSpile.Instance.isItemWaterSpile(playerItemStack)){
                if(WaterSpile.Instance.isMaterialLog(clickedBlockMat)){


                    long timeLeft = WaterSpile.Instance.CooldownManager.getCooldown(player.getUniqueId().toString());
                    if(timeLeft <= 0){
                        //Player has 20% chance of getting water from the spile
                        if(new Random().nextDouble() <= 0.2){

                            if(!player.getInventory().contains(Material.GLASS_BOTTLE)){
                                player.sendMessage(ChatColor.RED + "" + "I got water, but I forgot my bottle.");
                                WaterSpile.Instance.CooldownManager.setCooldown(player.getUniqueId().toString());
                                return;
                            }

                            player.getInventory().remove(Material.GLASS_BOTTLE);
                            player.getInventory().addItem(WaterSpile.Instance.generateWaterBottle());
                            player.sendMessage(ChatColor.GREEN + "" + "Ahh, yes! At last, water!");
                        }
                        else{
                            player.sendMessage(ChatColor.RED + "" + "Darn waterspile. It seems we didn't get anything from it.");
                        }

                        WaterSpile.Instance.CooldownManager.setCooldown(player.getUniqueId().toString());
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "" + "I'm tired. I'll try again in " + (timeLeft) + " seconds.");
                    }
                }
                else{
                    player.sendMessage(ChatColor.RED +  "" + "This cannot be used here.");
                }
            }
        }

    }
}
