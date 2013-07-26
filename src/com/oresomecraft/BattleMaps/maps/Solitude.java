package com.oresomecraft.BattleMaps.maps;

import java.util.List;

import com.oresomecraft.BattleMaps.IBattleMap;
import com.oresomecraft.BattleMaps.api.InvUtils;
import com.oresomecraft.OresomeBattles.BattlePlayer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.oresomecraft.BattleMaps.BattleMap;
import com.oresomecraft.OresomeBattles.Gamemode;
import com.oresomecraft.OresomeBattles.events.InventoryEvent;
import com.oresomecraft.OresomeBattles.gamemodes.TDM;
import com.oresomecraft.OresomeBattles.Utility;

public class Solitude extends BattleMap implements IBattleMap, Listener {

    public Solitude() {
        super.initiate(this);
        setDetails(name, fullName, creators, modes);
    }

    String name = "solitude";
    String fullName = "Solitude";
    String creators = "R3creat3, dutchy336, tarko2411 and PMC";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA};

    public void readyTDMSpawns() {
        World w = Bukkit.getServer().getWorld(name);

        redSpawns.add(new Location(w, 380, 52, 960));
        redSpawns.add(new Location(w, 342, 72, 965));
        redSpawns.add(new Location(w, 260, 95, 939));
        redSpawns.add(new Location(w, 231, 69, 965));
        redSpawns.add(new Location(w, 231, 69, 965));
        redSpawns.add(new Location(w, 218, 88, 940));
        redSpawns.add(new Location(w, 269, 57, 893));
        redSpawns.add(new Location(w, 221, 57, 842));
        redSpawns.add(new Location(w, 216, 61, 870));
        redSpawns.add(new Location(w, 231, 53, 798));
        redSpawns.add(new Location(w, 231, 59, 748));
        redSpawns.add(new Location(w, 269, 57, 893));
        redSpawns.add(new Location(w, 351, 13, 889));
        redSpawns.add(new Location(w, 451, 35, 947));
        redSpawns.add(new Location(w, 406, 46, 966));
        redSpawns.add(new Location(w, 267, 92, 987));
        redSpawns.add(new Location(w, 296, 60, 951));

        blueSpawns.add(new Location(w, 380, 52, 960));
        blueSpawns.add(new Location(w, 342, 72, 965));
        blueSpawns.add(new Location(w, 260, 95, 939));
        blueSpawns.add(new Location(w, 231, 69, 965));
        blueSpawns.add(new Location(w, 231, 69, 965));
        blueSpawns.add(new Location(w, 218, 88, 940));
        blueSpawns.add(new Location(w, 269, 57, 893));
        blueSpawns.add(new Location(w, 221, 57, 842));
        blueSpawns.add(new Location(w, 216, 61, 870));
        blueSpawns.add(new Location(w, 231, 53, 798));
        blueSpawns.add(new Location(w, 231, 59, 748));
        blueSpawns.add(new Location(w, 269, 57, 893));
        blueSpawns.add(new Location(w, 351, 13, 889));
        blueSpawns.add(new Location(w, 451, 35, 947));
        blueSpawns.add(new Location(w, 406, 46, 966));
        blueSpawns.add(new Location(w, 267, 92, 987));
        blueSpawns.add(new Location(w, 296, 60, 951));

        setRedSpawns(name, redSpawns);
        setBlueSpawns(name, blueSpawns);
    }

    public void readyFFASpawns() {

        World w = Bukkit.getServer().getWorld(name);

        FFASpawns.add(new Location(w, 380, 52, 960));
        FFASpawns.add(new Location(w, 342, 72, 965));
        FFASpawns.add(new Location(w, 260, 95, 939));
        FFASpawns.add(new Location(w, 231, 69, 965));
        FFASpawns.add(new Location(w, 231, 69, 965));
        FFASpawns.add(new Location(w, 218, 88, 940));
        FFASpawns.add(new Location(w, 269, 57, 893));
        FFASpawns.add(new Location(w, 221, 57, 842));
        FFASpawns.add(new Location(w, 216, 61, 870));
        FFASpawns.add(new Location(w, 231, 53, 798));
        FFASpawns.add(new Location(w, 231, 59, 748));
        FFASpawns.add(new Location(w, 269, 57, 893));
        FFASpawns.add(new Location(w, 351, 13, 889));
        FFASpawns.add(new Location(w, 451, 35, 947));
        FFASpawns.add(new Location(w, 406, 46, 966));
        FFASpawns.add(new Location(w, 267, 92, 987));
        FFASpawns.add(new Location(w, 296, 60, 951));

        setFFASpawns(name, FFASpawns);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void applyInventory(InventoryEvent event) {
        if (event.getMessage().equalsIgnoreCase(name)) {
            final BattlePlayer p = event.getPlayer();
            Inventory i = p.getInventory();
            clearInv(p);

            // Items
            ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
            ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
            ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack ARROWS = new ItemStack(Material.ARROW, 64);

            // Armor
            ItemStack LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
            ItemStack LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
            ItemStack LEATHER_PANTS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
            ItemStack LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
            ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 1);
            ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);
            ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
            fishing_rod.setDisplayName(ChatColor.GOLD + "Grappling Hook");
            FISHING_ROD.setItemMeta(fishing_rod);

            InvUtils.colourArmourAccordingToTeam(p, new ItemStack[]{LEATHER_HELMET, LEATHER_CHESTPLATE, LEATHER_PANTS, LEATHER_BOOTS});

            ItemMeta cooked_beef = STEAK.getItemMeta();
            cooked_beef.setDisplayName(ChatColor.GOLD + "Roast Beef");
            STEAK.setItemMeta(cooked_beef);

            ItemMeta arrows = ARROWS.getItemMeta();
            arrows.setDisplayName(ChatColor.GOLD + "Steel Arrow");
            ARROWS.setItemMeta(arrows);

            ItemMeta health_potion = HEALTH_POTION.getItemMeta();
            health_potion.setDisplayName(ChatColor.RED + "Potion of grand healing");
            HEALTH_POTION.setItemMeta(health_potion);

            ItemMeta iron_sword = IRON_SWORD.getItemMeta();
            iron_sword.setDisplayName(ChatColor.GOLD + "Steel Sword");
            IRON_SWORD.setItemMeta(iron_sword);

            ItemMeta exp = EXP.getItemMeta();
            exp.setDisplayName(ChatColor.GOLD + "Potion of Levelling");
            EXP.setItemMeta(exp);

            if (TDM.isBlue(p.getName())) {

                ItemMeta bow = BOW.getItemMeta();
                bow.setDisplayName(ChatColor.GOLD + "Steel Bow");
                BOW.setItemMeta(bow);

                ItemMeta leather_helmet = LEATHER_HELMET.getItemMeta();
                leather_helmet.setDisplayName(ChatColor.BLUE + "StormCloak Helmet");
                LEATHER_HELMET.setItemMeta(leather_helmet);

                // Sets boots name "StormCloak Boots"
                ItemMeta leather_boots = LEATHER_BOOTS.getItemMeta();
                leather_boots.setDisplayName(ChatColor.BLUE + "StormCloak Boots");
                LEATHER_BOOTS.setItemMeta(leather_boots);

                // Sets pants name "StormCloak Leggings"
                ItemMeta leather_pants = LEATHER_PANTS.getItemMeta();
                leather_pants.setDisplayName(ChatColor.BLUE + "StormCloak Leggings");
                LEATHER_PANTS.setItemMeta(leather_pants);

                // Sets chestplate name "StormCloak ChestPlate"
                ItemMeta leather_chestplate = LEATHER_CHESTPLATE.getItemMeta();
                leather_chestplate.setDisplayName(ChatColor.BLUE + "StormCloak ChestPlate");
                LEATHER_CHESTPLATE.setItemMeta(leather_chestplate);

            }

            if (TDM.isRed(p.getName())) {

                // Sets bow name "Imperial Bow"
                ItemMeta bow = BOW.getItemMeta();
                bow.setDisplayName(ChatColor.GOLD + "Imperial Bow");
                BOW.setItemMeta(bow);

                // Sets helmet name "Imperial Helmet"
                ItemMeta leather_helmet = LEATHER_HELMET.getItemMeta();
                leather_helmet.setDisplayName(ChatColor.RED + "Imperial Helmet");
                LEATHER_HELMET.setItemMeta(leather_helmet);

                // Sets boots name "Imperial Boots"
                ItemMeta leather_boots = LEATHER_BOOTS.getItemMeta();
                leather_boots.setDisplayName(ChatColor.RED + "Imperial Boots");
                LEATHER_BOOTS.setItemMeta(leather_boots);

                // Sets pants name "Imperial Leggings"
                ItemMeta leather_pants = LEATHER_PANTS.getItemMeta();
                leather_pants.setDisplayName(ChatColor.RED + "Imperial Leggings");
                LEATHER_PANTS.setItemMeta(leather_pants);

                // Sets chestplate name "Imperial ChestPlate"
                ItemMeta leather_chestplate = LEATHER_CHESTPLATE.getItemMeta();
                leather_chestplate.setDisplayName(ChatColor.RED + "Imperial ChestPlate");
                LEATHER_CHESTPLATE.setItemMeta(leather_chestplate);

            }

            p.getInventory().setBoots(LEATHER_BOOTS);
            p.getInventory().setLeggings(LEATHER_PANTS);
            p.getInventory().setChestplate(LEATHER_CHESTPLATE);
            p.getInventory().setHelmet(LEATHER_HELMET);

            i.setItem(0, IRON_SWORD);
            i.setItem(1, BOW);
            i.setItem(2, STEAK);
            i.setItem(3, HEALTH_POTION);
            i.setItem(4, ARROWS);
            i.setItem(5, EXP);
            i.setItem(5, FISHING_ROD);

            p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 9);
            //Do you know how many freaking fall deaths there are?

        }
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = 525;
    public int y1 = 0;
    public int z1 = 578;

    // Bottom right corner.
    public int x2 = -44;
    public int y2 = 232;
    public int z2 = 1136;

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockbreak(BlockBreakEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void preventblockplace(BlockPlaceEvent event) {

        Block b = event.getBlock();
        Location loc = b.getLocation();

        if (loc.getWorld().getName().equals(name)) {

            event.setCancelled(true);

        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        PlayerFishEvent.State state = event.getState();
        Player p = event.getPlayer();
        ItemStack is = p.getItemInHand();
        Material mat = is.getType();
        Location loc = p.getLocation();

        if (contains(loc, x1, x2, y1, y2, z1, z2)) {

            if (mat == Material.FISHING_ROD) {

                if (state == State.IN_GROUND) {
                    p.launchProjectile(Snowball.class);

                }
            }
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void grapple(ProjectileHitEvent event) {
        Entity proj = event.getEntity();
        Location hit = proj.getLocation();

        if (contains(hit, x1, x2, y1, y2, z1, z2)) {

            if (proj instanceof Snowball) {
                Snowball fish = (Snowball) proj;
                Entity shooter = fish.getShooter();

                if (shooter instanceof Player) {
                    Player p = (Player) shooter;
                    Location loc = p.getLocation();
                    ItemStack is = p.getItemInHand();
                    Material mat = is.getType();

                    if (mat == Material.FISHING_ROD) {

                        p.setFallDistance(0);
                        p.playSound(loc, Sound.ARROW_HIT, 1, 1);

                        int hitx = hit.getBlockX();
                        int hity = hit.getBlockY();
                        int hitz = hit.getBlockZ();
                        int locx = loc.getBlockX();
                        int locy = loc.getBlockY();
                        int locz = loc.getBlockZ();
                        double co[] = new double[3];

                        if (hitx > locx) {
                            co[0] = 1.2;
                        } else if (hitx < locx) {
                            co[0] = -1.2;
                        } else if (hitx == locx) {
                            co[0] = 0;
                        }

                        if (hity > locy) {
                            co[1] = 1.4;
                        } else if (hity < locy) {
                            co[1] = -0.8;
                        } else if (hity == locy) {
                            co[1] = 0;
                        }

                        if (hitz > locz) {
                            co[2] = 1.2;
                        } else if (hitz < locz) {
                            co[2] = -1.2;
                        } else if (hitz == locz) {
                            co[2] = 0;
                        }

                        p.setVelocity(new Vector(co[0], co[1], co[2]));

                    }
                }
            }

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void death(PlayerDeathEvent event) {

        List<ItemStack> drops = event.getDrops();
        int amount = drops.size();
        int count = 0;

        for (int none = 0; none < amount; none++) {

            ItemStack i = drops.get(count);
            count++;
            Material mat = i.getType();

            if (mat == Material.BOW || mat == Material.LEATHER_BOOTS
                    || mat == Material.LEATHER_LEGGINGS
                    || mat == Material.LEATHER_CHESTPLATE
                    || mat == Material.LEATHER_HELMET) {

                i.setType(Material.AIR);

            }

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void teamDeath(PlayerDeathEvent event) {

        if (Utility.getArena().equals(name)) {

            Player p = event.getEntity();
            Location l = p.getLocation();
            World world = Bukkit.getWorld(name);

            if (TDM.isRed(p.getName())) {
                // Show red particles (small)
                world.playEffect(l, Effect.STEP_SOUND, 152);
            } else {
                if (TDM.isBlue(p.getName())) {
                    // Show blue particles (small)
                    world.playEffect(l, Effect.STEP_SOUND, 22);
                }
            }
        }
    }
}