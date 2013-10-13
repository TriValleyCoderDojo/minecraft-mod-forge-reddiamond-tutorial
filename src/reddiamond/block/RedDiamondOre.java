package reddiamond.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import reddiamond.RedDiamondInfo;
import reddiamond.RedDiamondMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class RedDiamondOre extends Block {
	
	public static int id = 3000;
	public static String internalName = "redDiamondOre";
	public static String externalName = "Red Diamond Ore";
	
	@SideOnly(Side.CLIENT)
    protected Icon topIcon;
	@SideOnly(Side.CLIENT)
    protected Icon bottomIcon;

	public RedDiamondOre(int id, Material material) {
		super(id, material);
		setHardness(3.5F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName(internalName);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortune) {
		return RedDiamondMod.redDiamondItem.itemID;
	}
	
	@Override
	public int quantityDropped(Random random) {
		int maximum = 3;
		int minimum = 1;
		int n = maximum - minimum + 1;
		int i = random.nextInt() % n;
		int dropped =  minimum + i;
		if (dropped < 1){
			return 1;
		}
		return dropped;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
    	this.blockIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondOre_side");
    	this.topIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondOre_top");
    	this.bottomIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondOre_bottom");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
        if (side == 0){
        	return this.bottomIcon;
        }
        else if (side == 1){
        	return this.topIcon;
        }
		return this.blockIcon;
    }

}
