package reddiamond.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import reddiamond.RedDiamondInfo;
import reddiamond.RedDiamondMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class RedDiamondAxe extends ItemAxe {
	
	public static int id = 5005;
	public static String internalName = "redDiamondAxe";
	public static String externalName = "Red Diamond Axe";
	
	public static Object[] recipe = {
		"** ", 
		"*X ", 
		" X ", 
		'X', Item.stick, 
		'*', RedDiamondMod.redDiamondItem
	};

	public RedDiamondAxe(int id, EnumToolMaterial material) {
		super(id, material);
		this.setCreativeTab(CreativeTabs.tabTools);
		setUnlocalizedName(internalName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondAxe");
	}

}