package com.knightgost.nicechat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WordReplacer {
   private final Map<String, String> replacements = new HashMap();

   public WordReplacer(NiceChat plugin) {
      this.loadConfig(plugin);
   }

   private void loadConfig(NiceChat plugin) {
      if (plugin.getConfig().getBoolean("enabled", true)) {
         if (plugin.getConfig().contains("words")) {
            Iterator var2 = plugin.getConfig().getConfigurationSection("words").getKeys(false).iterator();

            while(var2.hasNext()) {
               String badWord = (String)var2.next();
               String goodWord = plugin.getConfig().getString("words." + badWord, badWord);
               this.replacements.put(badWord.toLowerCase(), goodWord);
            }
         }

      }
   }

   public String replaceBadWords(String message) {
      String result = message;

      String bad;
      String good;
      for(Iterator var3 = this.replacements.entrySet().iterator(); var3.hasNext(); result = result.replaceAll("(?i)\\b" + bad + "\\b", good)) {
         Entry<String, String> entry = (Entry)var3.next();
         bad = (String)entry.getKey();
         good = (String)entry.getValue();
      }

      return result;
   }
}
