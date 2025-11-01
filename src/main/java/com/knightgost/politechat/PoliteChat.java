package com.knightgost.politechat;

import org.bukkit.plugin.java.JavaPlugin;

public class PoliteChat extends JavaPlugin {
   private static PoliteChat instance;
   private WordReplacer wordReplacer;
   private ChatListener chatListener;

   public void onEnable() {
      instance = this;
      this.saveDefaultConfig();
      this.loadWordReplacer();
      this.chatListener = new ChatListener(this);
      this.getServer().getPluginManager().registerEvents(this.chatListener, this);
      if (this.getCommand("politechat") != null) {
         this.getCommand("politechat").setExecutor(new PoliteChatCommand(this));
      } else {
         this.getLogger().warning("Command 'politechat' not found in plugin.yml");
      }

      this.getLogger().info("PoliteChat enabled — spreading positivity!");
   }

   public void onDisable() {
      this.getLogger().info("PoliteChat disabled — stay kind!");
   }

   public void loadWordReplacer() {
      this.wordReplacer = new WordReplacer(this);
   }

   public void reloadPluginConfig() {
      this.reloadConfig();
      this.loadWordReplacer();
   }

   public WordReplacer getWordReplacer() {
      return this.wordReplacer;
   }

   public static PoliteChat getInstance() {
      return instance;
   }
}