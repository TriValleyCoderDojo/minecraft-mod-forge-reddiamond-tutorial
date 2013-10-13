package reddiamond.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import reddiamond.RedDiamondInfo;
import reddiamond.RedDiamondMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class RedDiamondSword extends ItemSword {
	
	public static int id = 5001;
	public static String internalName = "redDiamondSword";
	public static String externalName = "Red Diamond Sword";
	
	public static Object[] recipe = {
		" * ", 
		" * ", 
		" X ", 
		'X', Item.stick, 
		'*', RedDiamondMod.redDiamondItem
	};

	public RedDiamondSword(int id, EnumToolMaterial material) {
		super(id, material);
		this.setCreativeTab(CreativeTabs.tabCombat);
		setUnlocalizedName(internalName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondSword");
	}

}