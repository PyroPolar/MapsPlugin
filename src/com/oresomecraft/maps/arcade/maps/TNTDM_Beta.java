package com.oresomecraft.maps.arcade.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.types.ArcadeMap;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "tntdm_beta",
        fullName = "TNTDM (Beta)",
        creators = {"zachoz"},
        gamemodes = {Gamemode.LMS}
)
@Attributes(
        disabledDrops = {Material.COOKED_BEEF},
        allowPhysicalDamage = true
)
public class TNTDM_Beta extends ArcadeMap implements Listener {

    public TNTDM_Beta() {
        super.initiate(this);
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 0, 61, 0));
        FFASpawns.add(new Location(w, 0, 61, -26));
        FFASpawns.add(new Location(w, 0, 61, 26));
        FFASpawns.add(new Location(w, 27, 61, 0));
        FFASpawns.add(new Location(w, -27, 61, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack TNT = new ItemStack(Material.TNT, 128);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 3);

        i.setItem(0, TNT);
        i.setItem(1, STEAK);

    }
}
