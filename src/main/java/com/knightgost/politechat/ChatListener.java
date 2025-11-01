package com.knightgost.politechat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
   private final PoliteChat plugin;

   public ChatListener(PoliteChat plugin) {
      this.plugin = plugin;
   }

   @EventHandler
   public void onChat(AsyncPlayerChatEvent event) {
      String original = event.getMessage();
      WordReplacer replacer = this.plugin.getWordReplacer();
      if (replacer != null) {
         String filtered = replacer.replaceBadWords(original);
         if (!filtered.equals(original)) {
            event.setMessage(filtered);
         }

      }
   }
}
