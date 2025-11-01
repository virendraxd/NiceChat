package com.knightgost.politechat;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PoliteChatCommand implements CommandExecutor {
   private final PoliteChat plugin;

   public PoliteChatCommand(PoliteChat plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (args.length == 0) {
         sender.sendMessage("PoliteChat Plugin Commands:");
         sender.sendMessage("§e/politechat reload §7- Reload config");
         sender.sendMessage("§e/politechat add <badword> <niceword> §7- Add new replacement");
         sender.sendMessage("§e/politechat remove <badword> §7- Remove replacement");
         return true;
      } else {
         String var5 = args[0].toLowerCase();
         byte var6 = -1;
         switch(var5.hashCode()) {
         case -934641255:
            if (var5.equals("reload")) {
               var6 = 0;
            }
            break;
         case -934610812:
            if (var5.equals("remove")) {
               var6 = 2;
            }
            break;
         case 96417:
            if (var5.equals("add")) {
               var6 = 1;
            }
         }

         String removeWord;
         switch(var6) {
         case 0:
            this.plugin.reloadPluginConfig();
            sender.sendMessage("§a✅ PoliteChat config.yml reloaded successfully!");
            this.plugin.getLogger().info("Config reloaded by " + sender.getName());
            break;
         case 1:
            if (args.length < 3) {
               sender.sendMessage("§cUsage: /politechat add <badword> <niceword>");
               return true;
            }

            removeWord = args[1].toLowerCase();
            String niceWord = String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 2, args.length));
            this.plugin.getConfig().set("words." + removeWord, niceWord);
            this.plugin.saveConfig();
            this.plugin.loadWordReplacer();
            sender.sendMessage("§aAdded replacement: §e" + removeWord + " §f→ §b" + niceWord);
            break;
         case 2:
            if (args.length != 2) {
               sender.sendMessage("§cUsage: /politechat remove <badword>");
               return true;
            }

            removeWord = args[1].toLowerCase();
            if (this.plugin.getConfig().contains("words." + removeWord)) {
               this.plugin.getConfig().set("words." + removeWord, (Object)null);
               this.plugin.saveConfig();
               this.plugin.loadWordReplacer();
               sender.sendMessage("§aRemoved replacement for: §e" + removeWord);
            } else {
               sender.sendMessage("§cWord not found in replacements: §e" + removeWord);
            }
            break;
         default:
            sender.sendMessage("§cUnknown subcommand. Use /politechat reload | add | remove");
         }

         return true;
      }
   }
}
