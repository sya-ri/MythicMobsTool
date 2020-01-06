package me.syari.mmtool.view

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.Pane
import tornadofx.*
import javafx.scene.input.ClipboardContent
import javafx.scene.input.Clipboard.getSystemClipboard
import javafx.scene.input.DataFormat
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import java.net.URI
import kotlin.math.round
import org.jsoup.Jsoup
import java.awt.Desktop
import javafx.stage.FileChooser
import org.yaml.snakeyaml.Yaml
import java.io.IOException
import java.io.PrintWriter

class MainView : View("MythicMobsTool") {
    @FXML private var id = TextField()
    @FXML private var display = TextField()
    @FXML private var material = ComboBox<String>()
    @FXML private var data = TextField()
    @FXML private var amount = TextField()
    @FXML private var lore = TextArea()
    @FXML private var optUnb = CheckBox()
    @FXML private var optShi = CheckBox()
    @FXML private var optCol = CheckBox()
    @FXML private var optColV = ColorPicker()
    @FXML private var optHea = CheckBox()
    @FXML private var optHeaV = TextField()
    @FXML private var hideAtt = CheckBox()
    @FXML private var hideEnc = CheckBox()
    @FXML private var hideDes = CheckBox()
    @FXML private var hidePla = CheckBox()
    @FXML private var hidePot = CheckBox()
    @FXML private var hideUnb = CheckBox()
    @FXML private var encList = ComboBox<String>()
    @FXML private var encLv = TextField()
    @FXML private var encView = ListView<String>()
    @FXML private var attSlot = ComboBox<String>()
    @FXML private var attList = ComboBox<String>()
    @FXML private var attLv = TextField()
    @FXML private var attView = ListView<String>()
    @FXML private var potList = ComboBox<String>()
    @FXML private var potLv = TextField()
    @FXML private var potTime = TextField()
    @FXML private var potView = ListView<String>()
    @FXML private var skill = TextArea()

    override val root: Pane by fxml("/MainView.fxml")

    init {
        material.items.addAll("---", "AIR", "STONE", "GRASS", "DIRT", "COBBLESTONE", "WOOD", "SAPLING", "BEDROCK", "WATER", "STATIONARY_WATER", "LAVA", "STATIONARY_LAVA", "SAND", "GRAVEL", "GOLD_ORE", "IRON_ORE", "COAL_ORE", "LOG", "LEAVES", "SPONGE", "GLASS", "LAPIS_ORE", "LAPIS_BLOCK", "DISPENSER", "SANDSTONE", "NOTE_BLOCK", "BED_BLOCK", "POWERED_RAIL", "DETECTOR_RAIL", "PISTON_STICKY_BASE", "WEB", "LONG_GRASS", "DEAD_BUSH", "PISTON_BASE", "PISTON_EXTENSION", "WOOL", "PISTON_MOVING_PIECE", "YELLOW_FLOWER", "RED_ROSE", "BROWN_MUSHROOM", "RED_MUSHROOM", "GOLD_BLOCK", "IRON_BLOCK", "DOUBLE_STEP", "STEP", "BRICK", "TNT", "BOOKSHELF", "MOSSY_COBBLESTONE", "OBSIDIAN", "TORCH", "FIRE", "MOB_SPAWNER", "WOOD_STAIRS", "CHEST", "REDSTONE_WIRE", "DIAMOND_ORE", "DIAMOND_BLOCK", "WORKBENCH", "CROPS", "SOIL", "FURNACE", "BURNING_FURNACE", "SIGN_POST", "WOODEN_DOOR", "LADDER", "RAILS", "COBBLESTONE_STAIRS", "WALL_SIGN", "LEVER", "STONE_PLATE", "IRON_DOOR_BLOCK", "WOOD_PLATE", "REDSTONE_ORE", "GLOWING_REDSTONE_ORE", "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON", "STONE_BUTTON", "SNOW", "ICE", "SNOW_BLOCK", "CACTUS", "CLAY", "SUGAR_CANE_BLOCK", "JUKEBOX", "FENCE", "PUMPKIN", "NETHERRACK", "SOUL_SAND", "GLOWSTONE", "PORTAL", "JACK_O_LANTERN", "CAKE_BLOCK", "DIODE_BLOCK_OFF", "DIODE_BLOCK_ON", "STAINED_GLASS", "TRAP_DOOR", "MONSTER_EGGS", "SMOOTH_BRICK", "HUGE_MUSHROOM_1", "HUGE_MUSHROOM_2", "IRON_FENCE", "THIN_GLASS", "MELON_BLOCK", "PUMPKIN_STEM", "MELON_STEM", "VINE", "FENCE_GATE", "BRICK_STAIRS", "SMOOTH_STAIRS", "MYCEL", "WATER_LILY", "NETHER_BRICK", "NETHER_FENCE", "NETHER_BRICK_STAIRS", "NETHER_WARTS", "ENCHANTMENT_TABLE", "BREWING_STAND", "CAULDRON", "ENDER_PORTAL", "ENDER_PORTAL_FRAME", "ENDER_STONE", "DRAGON_EGG", "REDSTONE_LAMP_OFF", "REDSTONE_LAMP_ON", "WOOD_DOUBLE_STEP", "WOOD_STEP", "COCOA", "SANDSTONE_STAIRS", "EMERALD_ORE", "ENDER_CHEST", "TRIPWIRE_HOOK", "TRIPWIRE", "EMERALD_BLOCK", "SPRUCE_WOOD_STAIRS", "BIRCH_WOOD_STAIRS", "JUNGLE_WOOD_STAIRS", "COMMAND", "BEACON", "COBBLE_WALL", "FLOWER_POT", "CARROT", "POTATO", "WOOD_BUTTON", "SKULL", "ANVIL", "TRAPPED_CHEST", "GOLD_PLATE", "IRON_PLATE", "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "DAYLIGHT_DETECTOR", "REDSTONE_BLOCK", "QUARTZ_ORE", "HOPPER", "QUARTZ_BLOCK", "QUARTZ_STAIRS", "ACTIVATOR_RAIL", "DROPPER", "STAINED_CLAY", "STAINED_GLASS_PANE", "LEAVES_2", "LOG_2", "ACACIA_STAIRS", "DARK_OAK_STAIRS", "SLIME_BLOCK", "BARRIER", "IRON_TRAPDOOR", "PRISMARINE", "SEA_LANTERN", "HAY_BLOCK", "CARPET", "HARD_CLAY", "COAL_BLOCK", "PACKED_ICE", "DOUBLE_PLANT", "STANDING_BANNER", "WALL_BANNER", "DAYLIGHT_DETECTOR_INVERTED", "RED_SANDSTONE", "RED_SANDSTONE_STAIRS", "DOUBLE_STONE_SLAB2", "STONE_SLAB2", "SPRUCE_FENCE_GATE", "BIRCH_FENCE_GATE", "JUNGLE_FENCE_GATE", "DARK_OAK_FENCE_GATE", "ACACIA_FENCE_GATE", "SPRUCE_FENCE", "BIRCH_FENCE", "JUNGLE_FENCE", "DARK_OAK_FENCE", "ACACIA_FENCE", "SPRUCE_DOOR", "BIRCH_DOOR", "JUNGLE_DOOR", "ACACIA_DOOR", "DARK_OAK_DOOR", "END_ROD", "CHORUS_PLANT", "CHORUS_FLOWER", "PURPUR_BLOCK", "PURPUR_PILLAR", "PURPUR_STAIRS", "PURPUR_DOUBLE_SLAB", "PURPUR_SLAB", "END_BRICKS", "BEETROOT_BLOCK", "GRASS_PATH", "END_GATEWAY", "COMMAND_REPEATING", "COMMAND_CHAIN", "FROSTED_ICE", "MAGMA", "NETHER_WART_BLOCK", "RED_NETHER_BRICK", "BONE_BLOCK", "STRUCTURE_VOID", "OBSERVER", "WHITE_SHULKER_BOX", "ORANGE_SHULKER_BOX", "MAGENTA_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", "YELLOW_SHULKER_BOX", "LIME_SHULKER_BOX", "PINK_SHULKER_BOX", "GRAY_SHULKER_BOX", "SILVER_SHULKER_BOX", "CYAN_SHULKER_BOX", "PURPLE_SHULKER_BOX", "BLUE_SHULKER_BOX", "BROWN_SHULKER_BOX", "GREEN_SHULKER_BOX", "RED_SHULKER_BOX", "BLACK_SHULKER_BOX", "WHITE_GLAZED_TERRACOTTA", "ORANGE_GLAZED_TERRACOTTA", "MAGENTA_GLAZED_TERRACOTTA", "LIGHT_BLUE_GLAZED_TERRACOTTA", "YELLOW_GLAZED_TERRACOTTA", "LIME_GLAZED_TERRACOTTA", "PINK_GLAZED_TERRACOTTA", "GRAY_GLAZED_TERRACOTTA", "SILVER_GLAZED_TERRACOTTA", "CYAN_GLAZED_TERRACOTTA", "PURPLE_GLAZED_TERRACOTTA", "BLUE_GLAZED_TERRACOTTA", "BROWN_GLAZED_TERRACOTTA", "GREEN_GLAZED_TERRACOTTA", "RED_GLAZED_TERRACOTTA", "BLACK_GLAZED_TERRACOTTA", "CONCRETE", "CONCRETE_POWDER", "STRUCTURE_BLOCK", "IRON_SPADE", "IRON_PICKAXE", "IRON_AXE", "FLINT_AND_STEEL", "APPLE", "BOW", "ARROW", "COAL", "DIAMOND", "IRON_INGOT", "GOLD_INGOT", "IRON_SWORD", "WOOD_SWORD", "WOOD_SPADE", "WOOD_PICKAXE", "WOOD_AXE", "STONE_SWORD", "STONE_SPADE", "STONE_PICKAXE", "STONE_AXE", "DIAMOND_SWORD", "DIAMOND_SPADE", "DIAMOND_PICKAXE", "DIAMOND_AXE", "STICK", "BOWL", "MUSHROOM_SOUP", "GOLD_SWORD", "GOLD_SPADE", "GOLD_PICKAXE", "GOLD_AXE", "STRING", "FEATHER", "SULPHUR", "WOOD_HOE", "STONE_HOE", "IRON_HOE", "DIAMOND_HOE", "GOLD_HOE", "SEEDS", "WHEAT", "BREAD", "LEATHER_HELMET", "LEATHER_CHESTPLATE", "LEATHER_LEGGINGS", "LEATHER_BOOTS", "CHAINMAIL_HELMET", "CHAINMAIL_CHESTPLATE", "CHAINMAIL_LEGGINGS", "CHAINMAIL_BOOTS", "IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS", "DIAMOND_HELMET", "DIAMOND_CHESTPLATE", "DIAMOND_LEGGINGS", "DIAMOND_BOOTS", "GOLD_HELMET", "GOLD_CHESTPLATE", "GOLD_LEGGINGS", "GOLD_BOOTS", "FLINT", "PORK", "GRILLED_PORK", "PAINTING", "GOLDEN_APPLE", "SIGN", "WOOD_DOOR", "BUCKET", "WATER_BUCKET", "LAVA_BUCKET", "MINECART", "SADDLE", "IRON_DOOR", "REDSTONE", "SNOW_BALL", "BOAT", "LEATHER", "MILK_BUCKET", "CLAY_BRICK", "CLAY_BALL", "SUGAR_CANE", "PAPER", "BOOK", "SLIME_BALL", "STORAGE_MINECART", "POWERED_MINECART", "EGG", "COMPASS", "FISHING_ROD", "WATCH", "GLOWSTONE_DUST", "RAW_FISH", "COOKED_FISH", "INK_SACK", "BONE", "SUGAR", "CAKE", "BED", "DIODE", "COOKIE", "MAP", "SHEARS", "MELON", "PUMPKIN_SEEDS", "MELON_SEEDS", "RAW_BEEF", "COOKED_BEEF", "RAW_CHICKEN", "COOKED_CHICKEN", "ROTTEN_FLESH", "ENDER_PEARL", "BLAZE_ROD", "GHAST_TEAR", "GOLD_NUGGET", "NETHER_STALK", "POTION", "GLASS_BOTTLE", "SPIDER_EYE", "FERMENTED_SPIDER_EYE", "BLAZE_POWDER", "MAGMA_CREAM", "BREWING_STAND_ITEM", "CAULDRON_ITEM", "EYE_OF_ENDER", "SPECKLED_MELON", "MONSTER_EGG", "EXP_BOTTLE", "FIREBALL", "BOOK_AND_QUILL", "WRITTEN_BOOK", "EMERALD", "ITEM_FRAME", "FLOWER_POT_ITEM", "CARROT_ITEM", "POTATO_ITEM", "BAKED_POTATO", "POISONOUS_POTATO", "EMPTY_MAP", "GOLDEN_CARROT", "SKULL_ITEM", "CARROT_STICK", "NETHER_STAR", "PUMPKIN_PIE", "FIREWORK", "FIREWORK_CHARGE", "ENCHANTED_BOOK", "REDSTONE_COMPARATOR", "NETHER_BRICK_ITEM", "QUARTZ", "EXPLOSIVE_MINECART", "HOPPER_MINECART", "PRISMARINE_SHARD", "PRISMARINE_CRYSTALS", "RABBIT", "COOKED_RABBIT", "RABBIT_STEW", "RABBIT_FOOT", "RABBIT_HIDE", "ARMOR_STAND", "IRON_BARDING", "GOLD_BARDING", "DIAMOND_BARDING", "LEASH", "NAME_TAG", "COMMAND_MINECART", "MUTTON", "COOKED_MUTTON", "BANNER", "END_CRYSTAL", "SPRUCE_DOOR_ITEM", "BIRCH_DOOR_ITEM", "JUNGLE_DOOR_ITEM", "ACACIA_DOOR_ITEM", "DARK_OAK_DOOR_ITEM", "CHORUS_FRUIT", "CHORUS_FRUIT_POPPED", "BEETROOT", "BEETROOT_SEEDS", "BEETROOT_SOUP", "DRAGONS_BREATH", "SPLASH_POTION", "SPECTRAL_ARROW", "TIPPED_ARROW", "LINGERING_POTION", "SHIELD", "ELYTRA", "BOAT_SPRUCE", "BOAT_BIRCH", "BOAT_JUNGLE", "BOAT_ACACIA", "BOAT_DARK_OAK", "TOTEM", "SHULKER_SHELL", "IRON_NUGGET", "KNOWLEDGE_BOOK", "GOLD_RECORD", "GREEN_RECORD", "RECORD_3", "RECORD_4", "RECORD_5", "RECORD_6", "RECORD_7", "RECORD_8", "RECORD_9", "RECORD_10", "RECORD_11", "RECORD_12")
        material.makeAutocompletable { s -> material.items.filter { f -> f.startsWith(s.toUpperCase()) } }
        encList.items.addAll("---", "PROTECTION_ENVIRONMENTAL", "PROTECTION_FIRE", "PROTECTION_FALL", "PROTECTION_EXPLOSIONS", "PROTECTION_PROJECTILE", "OXYGEN", "WATER_WORKER", "THORNS", "DEPTH_STRIDER", "FROST_WALKER", "BINDING_CURSE", "DAMAGE_ALL", "DAMAGE_UNDEAD", "DAMAGE_ARTHROPODS", "KNOCKBACK", "FIRE_ASPECT", "LOOT_BONUS_MOBS", "SWEEPING_EDGE", "DIG_SPEED", "SILK_TOUCH", "DURABILITY", "LOOT_BONUS_BLOCKS", "ARROW_DAMAGE", "ARROW_KNOCKBACK", "ARROW_FIRE", "ARROW_INFINITE", "LUCK", "LURE", "MENDING", "VANISHING_CURSE")
        encList.makeAutocompletable { s -> encList.items.filter { f -> f.startsWith(s.toUpperCase())} }
        attSlot.items.addAll("All", "MainHand", "OffHand", "Head", "Chest", "Legs", "Feet")
        attSlot.makeAutocompletable { s -> attSlot.items.filter { f -> f.toLowerCase().startsWith(s.toLowerCase())} }
        attList.items.addAll("---", "AttackSpeed", "Armor", "ArmorToughness", "Damage", "FollowRange", "Health", "Luck", "KnockbackResistance", "MovementSpeed")
        attList.makeAutocompletable { s -> attList.items.filter { f -> f.toLowerCase().startsWith(s.toLowerCase())} }
        potList.items.addAll("---", "ABSORPTION", "BLINDNESS", "CONDUIT_POWER", "CONFUSION", "DAMAGE_RESISTANCE", "DOLPHINS_GRACE", "FAST_DIGGING", "FIRE_RESISTANCE", "GLOWING", "HARM", "HEAL", "HEALTH_BOOST", "HUNGER", "INCREASE_DAMAGE", "INVISIBILITY", "JUMP", "LEVITATION", "LUCK", "NIGHT_VISION", "POISON", "REGENERATION", "SATURATION", "SLOW", "SLOW_DIGGING", "SLOW_FALLING", "SPEED", "UNLUCK", "WATER_BREATHING", "WEAKNESS", "WITHER")
        potList.makeAutocompletable { s -> potList.items.filter { f -> f.startsWith(s.toUpperCase())} }
    }

    private fun getYaml(): String{
        val s = StringBuilder()
        s.append("${if(id.text.isNotEmpty()) id.text else "MM_ID"}:\n")
        if(material.value != null && material.value.isNotEmpty()) s.append("  Id: ${material.value}\n")
        if(display.text != null && display.text.isNotEmpty()) s.append("  Display: '${display.text}'\n")
        if(data.text != "0" && data.text.isNotEmpty()) s.append("  Data: ${data.text}\n")
        if(amount.text != "1" && amount.text.isNotEmpty()) s.append("  Amount: ${amount.text}\n")
        val l = lore.text?.split("\n".toRegex())?.filter { f -> f.isNotEmpty() }
        if(l != null && l.isNotEmpty()){
            s.append("  Lore:\n")
            l.forEach { f ->
                s.append("  - '$f'\n")
            }
        }
        var section = true
        if(optUnb.isSelected){
            if(section){
                s.append("  Options:\n")
                section = false
            }
            s.append("    Unbreakable: true\n")
        }
        if(optCol.isSelected){
            if(section){
                s.append("  Options:\n")
                section = false
            }
            s.append("    Color: ${round(optColV.value.red * 255).toInt()},${round(optColV.value.green * 255).toInt()},${round(optColV.value.blue * 255).toInt()}\n")
        }
        if(optHea.isSelected && optHeaV.text.isNotEmpty()){
            if(section){
                s.append("  Options:\n")
            }
            if(optHeaV.text.matches("""https://minecraft-heads.com/.+/.+/.+""".toRegex())){
                val document = Jsoup.connect(optHeaV.text)?.get()
                val id = document?.getElementById("UUID-Value")?.text()
                s.append("    SkinTexture: '${id}'\n")
            } else {
                s.append("    Player: ${optHeaV.text}\n")
            }
        }
        val hide = listOf(hideAtt, hideEnc, hideDes, hidePla, hidePot, hideUnb).filter { h -> h.isSelected }
        if(hide.isNotEmpty()){
            s.append("  Hide:\n")
            hide.forEach { h ->
                s.append("  - ${h.text}\n")
            }
        }
        if(encView.items.isNotEmpty()){
            s.append("  Enchantments:\n")
            encView.items.forEach { e ->
                val t = e.split("\\s+".toRegex())
                s.append("  - ${t[0]}:${t[1].substringAfter("Lv.")}\n")
            }
        }
        if(attView.items.isNotEmpty()){
            val attSeed = mutableMapOf<String, MutableMap<String, String>>()
            s.append("  Attributes:\n")
            attView.items.forEach { f ->
                val r = f.split("\\s+".toRegex())
                attSeed[r[0]]?.set(r[1], r[2])
            }
            attSeed.forEach { f ->
                s.append("    ${f.key}\n")
                f.value.forEach { v ->
                    s.append("      ${v.key}: ${v.value}\n")
                }
            }
        }
        if(potView.items.isNotEmpty()){
            s.append("  PotionEffects:\n")
            potView.items.forEach { p ->
                val r = p.split("\\s+".toRegex())
                s.append("  - ${r[0]} ${r[1].substringBefore("tick")} ${r[2].substringAfter("Lv.")}\n")
            }
        }
        val r = skill.text?.split("\\s+".toRegex())
        if(r != null && r.isNotEmpty()){
            s.append("  Skills:\n")
            r.forEach { f ->
                s.append("  - ${f}\n")
            }
        }
        return s.toString()
    }

    @FXML fun onClear(e: ActionEvent){
        id.text = null
        display.text = null
        material.value = "---"
        data.text = null
        amount.text = "1"
        lore.text = null
        optUnb.isSelected = false
        optShi.isSelected = false
        optCol.isSelected = false
        optColV.value = Color.WHITE
        optHea.isSelected = false
        optHeaV.text = null
        hideAtt.isSelected = false
        hideEnc.isSelected = false
        hideDes.isSelected = false
        hidePla.isSelected = false
        hidePot.isSelected = false
        hideUnb.isSelected = false
        encList.value = "---"
        encLv.text = null
        encView.items.clear()
        attSlot.value = "All"
        attList.value = "---"
        attLv.text = null
        attView.items.clear()
        potList.value = "---"
        potTime.text = null
        potLv.text = null
        potView.items.clear()
    }

    @FXML fun onShinyClick(e: ActionEvent){
        hideEnc.isSelected = optShi.isSelected
        if(hideEnc.isSelected){
            encView.items = observableList("ARROW_INFINITE Lv.1")
        } else {
            encView.items.remove("ARROW_INFINITE Lv.1")
        }
    }

    @FXML fun onHeadValueWrite(e: ActionEvent){
        optHea.isSelected = optHeaV.text.isNotEmpty()
    }

    @FXML fun onURLClick(e: MouseEvent){
        Desktop.getDesktop().browse(URI("https://minecraft-heads.com"));
    }

    @FXML fun onEncAdd(e: ActionEvent){
        val t = encList.value ?: return
        if(!encList.items.contains(t) || t == "---") return
        val l = encLv.text?.toIntOrNull() ?: return
        encView.items.removeIf{ f -> f.split("\\s+".toRegex())[0] == t }
        encView.items.add("$t Lv.$l")
        encList.value = "---"
        encLv.text = null
    }

    @FXML fun onEncKey(e: KeyEvent){
        if(e.code.getName() == "Delete"){
            val s = encView.selectedItem ?: return
            encView.items.remove(s)
        }
    }

    @FXML fun onAttAdd(e: ActionEvent){
        val t = attList.value ?: return
        if(!attList.items.contains(t) || t == "---") return
        val s = if(attSlot.items.contains(attSlot.value)) attSlot.value else "All"
        val l = attLv.text?.toIntOrNull() ?: return
        attView.items.removeIf{ f ->
            val r = f.split("\\s+".toRegex())
            r[0] == s && r[1] == t
        }
        attView.items.add("$s $t $l")
        attSlot.value = "All"
        attList.value = "---"
        attLv.text = null
    }

    @FXML fun onAttKey(e: KeyEvent){
        if(e.code.getName() == "Delete"){
            val s = attView.selectedItem ?: return
            attView.items.remove(s)
        }
    }

    @FXML fun onPotAdd(e: ActionEvent){
        val p = potList.value ?: return
        if(!potList.items.contains(p) || p == "---") return
        val t = potTime.text?.toIntOrNull() ?: return
        if(t < 0) return
        val l = potLv.text?.toIntOrNull() ?: "1"
        potView.items.removeIf{ f ->
            val r = f.split("\\s+".toRegex())
            r[0] == p && r[1] == "${t}tick"
        }
        potView.items.add("$p ${t}tick Lv.$l")
        potList.value = "---"
        potTime.text = null
        potLv.text = null
    }

    @FXML fun onPotKey(e: KeyEvent){
        if(e.code.getName() == "Delete"){
            val s = potView.selectedItem ?: return
            potView.items.remove(s)
        }
    }

    @FXML fun onSave(e: ActionEvent){
        val s = getYaml()
        val fileChooser = FileChooser()
        fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("Yaml File", "*.yml"))
        val file = fileChooser.showSaveDialog(primaryStage) ?: return
        try {
            val writer = PrintWriter(file)
            writer.println(s)
            writer.close()
            Desktop.getDesktop().open(file.parentFile)
        } catch (ex: IOException) { }
    }

    @FXML fun onCopy(e: ActionEvent) {
        val clipboard = getSystemClipboard()
        val content = ClipboardContent()
        content[DataFormat.PLAIN_TEXT] = getYaml()
        clipboard.setContent(content)
    }
}
