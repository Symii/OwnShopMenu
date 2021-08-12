package eu.owncraft.ownshopmenu.commands;

import eu.owncraft.ownshopmenu.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;
        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("kup"))
            {
                player.openInventory(Main.getInstance().getShopMenu().getBuyMenu());
            }
            else if(args[0].equalsIgnoreCase("sprzedaj"))
            {
                player.openInventory(Main.getInstance().getShopMenu().getSellMenu());
            }
            else
            {
                player.openInventory(Main.getInstance().getShopMenu().getMainMenu());
            }
        }
        else
        {
            player.openInventory(Main.getInstance().getShopMenu().getMainMenu());
        }

        return true;
    }
}
