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
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class RedDiamondWand extends Item {
	
	public static int id = 5006;
	public static String internalName = "redDiamondWand";
	public static String externalName = "Red Diamond Wand";
	
	public static Object[] recipe = {
		"*  ", 
		" X ", 
		"  X", 
		'X', Item.stick, 
		'*', RedDiamondMod.redDiamondItem
	};

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
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.damageItem(10, entityplayer);
		if (!world.isRemote) {
			Vec3 look = entityplayer.getLookVec();
			EntityWitherSkull witherSkull = new EntityWitherSkull(world, entityplayer, 1, 1, 1);
			witherSkull.setPosition(
					entityplayer.posX + look.xCoord * 5,
					entityplayer.posY + look.yCoord * 5,
					entityplayer.posZ + look.zCoord * 5);
			witherSkull.accelerationX = look.xCoord * 0.1;
			witherSkull.accelerationY = look.yCoord * 0.1;
			witherSkull.accelerationZ = look.zCoord * 0.1;
			world.spawnEntityInWorld(witherSkull);
		}
		return itemstack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister){
		itemIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":RedDiamondWand");
	}
	
}
