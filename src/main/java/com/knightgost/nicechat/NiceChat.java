package com.knightgost.nicechat;

import org.bukkit.plugin.java.JavaPlugin;

public class NiceChat extends JavaPlugin {
   private static NiceChat instance;
   private WordReplacer wordReplacer;
   private ChatListener chatListener;

   public void onEnable() {
      instance = this;
      this.saveDefaultConfig();
      this.loadWordReplacer();
      this.chatListener = new ChatListener(this);
      this.getServer().getPluginManager().registerEvents(this.chatListener, this);
      if (this.getCommand("nicechat") != null) {
         this.getCommand("nicechat").setExecutor(new NiceChatCommand(this));
      } else {
         this.getLogger().warning("Command 'nicechat' not found in plugin.yml");
      }

      this.getLogger().info("NiceChat enabled — spreading positivity!");
   }

   public void onDisable() {
      this.getLogger().info("NiceChat disabled — stay kind!");
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

   public static NiceChat getInstance() {
      return instance;
   }
}
