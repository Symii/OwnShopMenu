package eu.owncraft.ownshopmenu.gui;

import eu.owncraft.ownshopmenu.Main;
import eu.owncraft.ownshopmenu.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class ShopMenu {

    private Main plugin;
    private Inventory mainMenu, sellMenu, buyMenu;
    private String mainMenuTitle, sellMenuTitle, buyMenutitle;

    private HashMap<Integer, String> mainMenuActions = new HashMap<>();

    public ShopMenu(Main plugin)
    {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        loadShopMenu();
    }

    private void loadShopMenu()
    {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        mainMenuTitle = config.getString("mainMenu.title");

        loadMainMenu(config);
    }

    private void loadMainMenu(FileConfiguration config)
    {
        Inventory inventory = Bukkit.createInventory(null, config.getInt("mainMenu.size"),
                ChatUtil.fixColors(config.getString("mainMenu.title")));
        for(String s : config.getConfigurationSection("mainMenu.gui").getKeys(true))
        {
            ItemStack item = new ItemStack(Material.valueOf(config.getString("mainMenu.gui." + s + ".material").toUpperCase()),
                    config.getInt("mainMenu.gui." + s + ".amount"));
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(ChatUtil.fixColors(config.getString("mainMenu.gui." + s + ".name")));
            itemMeta.setLore(ChatUtil.fixColors(config.getStringList("mainMenu.gui." + s + ".lore")));
            item.setItemMeta(itemMeta);

            inventory.setItem(Integer.parseInt(s), item);

            String cmd = config.getString("mainMenu.gui." + s + ".command");
            if(cmd.contains("/"))
            {
                mainMenuActions.put(Integer.parseInt(s), cmd);
            }
        }
    }

    public HashMap<Integer, String> getMainMenuActions() {
        return mainMenuActions;
    }

    public Inventory getMainMenu() {
        return mainMenu;
    }

    public Inventory getSellMenu() {
        return sellMenu;
    }

    public Inventory getBuyMenu() {
        return buyMenu;
    }

    public String getMainMenuTitle() {
        return mainMenuTitle;
    }

    public String getSellMenuTitle() {
        return sellMenuTitle;
    }

    public String getBuyMenutitle() {
        return buyMenutitle;
    }
}
