package reddiamond;

import reddiamond.block.RedDiamondOre;
import reddiamond.item.RedDiamondAxe;
import reddiamond.item.RedDiamondHoe;
import reddiamond.item.RedDiamondItem;
import reddiamond.item.RedDiamondPickaxe;
import reddiamond.item.RedDiamondShovel;
import reddiamond.item.RedDiamondSword;
import reddiamond.item.RedDiamondWand;
import reddiamond.world.OreManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=RedDiamondInfo.MOD_ID, name=RedDiamondInfo.MOD_NAME, version=RedDiamondInfo.MOD_VERSION)
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class RedDiamondMod {
	
	@Instance(RedDiamondInfo.MOD_ID)
	public static RedDiamondMod instance;
	
	public static EnumToolMaterial materialRedDiamond = EnumHelper.addToolMaterial(
			"REDDIAMOND", 3, 2000, 15.0F, 40.0F, 50);
	
	public static Item redDiamondItem = new RedDiamondItem(RedDiamondItem.id);
	
	public static Block redDiamondOre = new RedDiamondOre(RedDiamondOre.id, Material.rock);
	
	public static Item redDiamondSword = new RedDiamondSword(RedDiamondSword.id, materialRedDiamond);
	public static Item redDiamondShovel = new RedDiamondShovel(RedDiamondShovel.id, materialRedDiamond);
	public static Item redDiamondPickaxe = new RedDiamondPickaxe(RedDiamondPickaxe.id, materialRedDiamond);
	public static Item redDiamondHoe = new RedDiamondHoe(RedDiamondHoe.id, materialRedDiamond);
	public static Item redDiamondAxe = new RedDiamondAxe(RedDiamondAxe.id, materialRedDiamond);
	public static Item redDiamondWand = new RedDiamondWand(RedDiamondWand.id);
	
	private OreManager oreManager = new OreManager();

	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		
		// make the RedDiamondItem available to the game
		GameRegistry.registerItem(redDiamondItem, RedDiamondItem.internalName);
		LanguageRegistry.addName(redDiamondItem, RedDiamondItem.externalName);
		
		// make the RedDiamondOre available to the game
		GameRegistry.registerBlock(redDiamondOre, RedDiamondOre.internalName);
		LanguageRegistry.addName(redDiamondOre, RedDiamondOre.externalName);
		// requires a diamond pick to harvest
		MinecraftForge.setBlockHarvestLevel(redDiamondOre, "pickaxe", 3);
		// make it spawn naturally
		GameRegistry.registerWorldGenerator(oreManager);
		
		// create a smelting recipe to create two RedDiamonds from a diamond
		ItemStack redDiamondStack = new ItemStack(redDiamondItem, 2);
		GameRegistry.addSmelting(Item.diamond.itemID, redDiamondStack, 1.0F);
		
		// make the red diamond tools & weapons available to the game
		GameRegistry.registerItem(redDiamondSword, RedDiamondSword.internalName);
		LanguageRegistry.addName(redDiamondSword, RedDiamondSword.externalName);
		GameRegistry.addRecipe(new ItemStack(redDiamondSword, 1), RedDiamondSword.recipe);
		
		GameRegistry.registerItem(redDiamondShovel, RedDiamondShovel.internalName);
		LanguageRegistry.addName(redDiamondShovel, RedDiamondShovel.externalName);
		GameRegistry.addRecipe(new ItemStack(redDiamondShovel, 1), RedDiamondShovel.recipe);
		
		GameRegistry.registerItem(redDiamondPickaxe, RedDiamondPickaxe.internalName);
		LanguageRegistry.addName(redDiamondPickaxe, RedDiamondPickaxe.externalName);
		GameRegistry.addRecipe(new ItemStack(redDiamondPickaxe, 1), RedDiamondPickaxe.recipe);
		
		GameRegistry.registerItem(redDiamondHoe, RedDiamondHoe.internalName);
		LanguageRegistry.addName(redDiamondHoe, RedDiamondHoe.externalName);
		GameRegistry.addRecipe(new ItemStack(redDiamondHoe, 1), RedDiamondHoe.recipe);
		
		GameRegistry.registerItem(redDiamondAxe, RedDiamondAxe.internalName);
		LanguageRegistry.addName(redDiamondAxe, RedDiamondAxe.externalName);
		GameRegistry.addRecipe(new ItemStack(redDiamondAxe, 1), RedDiamondAxe.recipe);
		
		GameRegistry.registerItem(redDiamondWand, RedDiamondWand.internalName);
		LanguageRegistry.addName(redDiamondWand, RedDiamondWand.externalName);
		GameRegistry.addRecipe(new ItemStack(redDiamondWand, 1), RedDiamondWand.recipe);
		
	}

}
