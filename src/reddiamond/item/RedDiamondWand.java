package reddiamond.item;

import reddiamond.RedDiamondInfo;
import reddiamond.RedDiamondMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class RedDiamondWand extends Item {
	
	public static int id = 5006;
	public static String internalName = "redDiamondWand";
	public static String externalName = "Red Diamond Wand";
	
	public static Object[] recipe = {"*  ", " X ", "  X", 'X', Item.stick, '*', RedDiamondMod.redDiamondItem};

	public RedDiamondWand(int id) {
		super(id);
		setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabCombat);
		setUnlocalizedName(internalName);
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase target, EntityLivingBase player){
		System.out.println("Enter hitEntity");
		if(!target.worldObj.isRemote){
			if(target instanceof EntityMob){
				target.motionY = 2.5;
				return true;
			}
			else { 
				// need to recast to get the subclass functionality
				// original code should have used an interface instead
				EntityPlayer entityPlayer = (EntityPlayer) player;
				entityPlayer.addChatMessage("This only works on monsters");
			}
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondWand");
	}
	
}
