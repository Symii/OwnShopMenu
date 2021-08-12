package eu.owncraft.ownshopmenu;

import eu.owncraft.ownshopmenu.commands.ShopCommand;
import eu.owncraft.ownshopmenu.gui.ShopMenu;
import eu.owncraft.ownshopmenu.listeners.InventoryListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main INSTANCE;
    private ShopMenu shopMenu;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new InventoryListener(), this);

        shopMenu = new ShopMenu(this);
        getCommand("sklep").setExecutor(new ShopCommand());
    }

    public static Main getInstance() {
        return INSTANCE;
    }

    public ShopMenu getShopMenu() {
        return shopMenu;
    }
}
