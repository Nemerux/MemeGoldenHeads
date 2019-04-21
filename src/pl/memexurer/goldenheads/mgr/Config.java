package pl.memexurer.goldenheads.mgr;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.memexurer.goldenheads.MemeGoldenHeads;
import pl.memexurer.goldenheads.util.Color;

import java.util.ArrayList;
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
         effects.add(new PotionEffect(effect, time, strength));
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
        s.shape("1","2","3","4","5","6","7","8","9");
        String numbers = "123456789";
        for(int i = 0; i < 9;i++) {
            s.setIngredient(numbers.charAt(i), Material.getMaterial(materials.get(i)));
        }
        return s;
    }
}
