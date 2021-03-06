package pl.memexurer.goldenheads.mgr;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.memexurer.goldenheads.util.Color;
import pl.memexurer.goldenheads.util.HeadTexture;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Config {
    private Configuration config;

    public Config(Configuration config) {
        this.config =config;
    }


    public HeadType getHeadType() {
        return HeadType.valueOf(config.getString("head_type"));
    }

    public ArrayList<PotionEffect> getHeadEffects() {
        ArrayList<PotionEffect> effects = new ArrayList<>();
        List<String> unparsedEffects = config.getStringList("head_effects");
        for(String s: unparsedEffects) {
            String[] split = s.split(" ");
         PotionEffectType effect = PotionEffectType.getByName(split[0]);
         int strength = Integer.parseInt(split[1]);
         int time = Integer.parseInt(split[2]);
         effects.add(new PotionEffect(effect, time * 20, strength));
        }
return effects;
    }

    public ItemStack getHeadItem() {
        Material material = Material.getMaterial(config.getString("head.material"));
        String name = Color.color(config.getString("head.name"));
        ArrayList<String> lore = new ArrayList<>();
        for(String s: config.getStringList("head.lore")) {
            lore.add(Color.color(s));
        }

        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(name);
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        if(item.getType().equals(Material.SKULL_ITEM)) return HeadTexture.setHeadTexture(getHeadUrlBase64(), item);
        return item;
    }

    public int getHeadDelay() {
        return config.getInt("head_delay");
    }

    public boolean autoGiveSkulls() {
        return config.getBoolean("auto_give_skulls");
    }

    public ShapedRecipe getRecipe() {
        ShapedRecipe s = new ShapedRecipe(getHeadItem());
        List<String> materials = config.getStringList("head_crafting");
        s.shape("123","456", "789");
        String numbers = "123456789";
        for(int i = 0; i < 9;i++) {
            if(materials.get(i).equalsIgnoreCase("GLOWKA")) {
                MaterialData d = new MaterialData(Material.SKULL_ITEM, (byte) 3);
                s.setIngredient(numbers.charAt(i), d);
                        continue;
            }
            s.setIngredient(numbers.charAt(i), Material.getMaterial(materials.get(i)));
        }
        return s;
    }
    public boolean fgAntiSkulls() { return config.getBoolean("fg_anti_skulls"); }

    public String getHeadUrlBase64() {
      return Base64.getEncoder().encodeToString(String.format("{\"textures\":{\"SKIN\":{\"url\":\"%s\"}}}", config.getString("head_url")).getBytes());
    }
}
