package reddiamond.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import reddiamond.RedDiamondInfo;
import reddiamond.RedDiamondMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class RedDiamondPickaxe extends ItemPickaxe {
	
	public static int id = 5003;
	public static String internalName = "redDiamondPickaxe";
	public static String externalName = "Red Diamond Pickaxe";
	
	public static Object[] recipe = {
		"***", 
		" X ", 
		" X ", 
		'X', Item.stick, 
		'*', RedDiamondMod.redDiamondItem
	};

	public RedDiamondPickaxe(int id, EnumToolMaterial material) {
		super(id, material);
		this.setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName(internalName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondPickaxe");
	}

}
