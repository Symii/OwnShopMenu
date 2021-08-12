package eu.owncraft.ownshopmenu.listeners;

import eu.owncraft.ownshopmenu.Main;
import eu.owncraft.ownshopmenu.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;

        if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getShopMenu().getMainMenuTitle())) {
            event.setCancelled(true);

            if(!(event.getWhoClicked() instanceof Player)) { return; }
            Player player = (Player) event.getWhoClicked();

            for(int slotKey : Main.getInstance().getShopMenu().getMainMenuActions().keySet())
            {
                if(event.getSlot() == slotKey)
                {
                    player.chat(Main.getInstance().getShopMenu().getMainMenuActions().get(slotKey));
                    break;
                }
            }
        }
    }

}
