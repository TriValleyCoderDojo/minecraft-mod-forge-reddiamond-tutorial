package reddiamond.block;

import java.util.Random;

import reddiamond.RedDiamondInfo;
import reddiamond.entity.NukePrimed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockNuke extends Block {
	
	public static int id = 3001;
	public static String internalName = "nuke";
	public static String externalName = "Nuke";
	public int fuse;
	
	@SideOnly(Side.CLIENT)
    private Icon topIcon;
    @SideOnly(Side.CLIENT)
    private Icon bottomIcon;

    public BlockNuke(int par1, int fuse)
    {
        super(par1, Material.tnt);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.fuse = fuse;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.bottomIcon : (par1 == 1 ? this.topIcon : this.blockIcon);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
        {
            this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
        if (!par1World.isRemote) {
        	NukePrimed nukePrimed = new NukePrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), par5Explosion.getExplosivePlacedBy());
        	nukePrimed.setFuse(fuse);
        	nukePrimed.fuse = par1World.rand.nextInt(nukePrimed.fuse / 4) + nukePrimed.fuse / 8;
            par1World.spawnEntityInWorld(nukePrimed);
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
        this.primeTnt(par1World, par2, par3, par4, par5, (EntityLivingBase)null);
    }

    /**
     * spawns the primed tnt and plays the fuse sound.
     */
    public void primeTnt(World par1World, int par2, int par3, int par4, int par5, EntityLivingBase par6EntityLivingBase) {
        if (!par1World.isRemote) {
            if ((par5 & 1) == 1) {
                NukePrimed nukePrimed = new NukePrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), par6EntityLivingBase);
                par1World.spawnEntityInWorld(nukePrimed);
                par1World.playSoundAtEntity(nukePrimed, "random.fuse", 1.0F, 1.0F);
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
        {
            this.primeTnt(par1World, par2, par3, par4, 1, par5EntityPlayer);
            par1World.setBlockToAir(par2, par3, par4);
            par5EntityPlayer.getCurrentEquippedItem().damageItem(1, par5EntityPlayer);
            return true;
        }
        else
        {
            return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        if (par5Entity instanceof EntityArrow && !par1World.isRemote)
        {
            EntityArrow entityarrow = (EntityArrow)par5Entity;

            if (entityarrow.isBurning())
            {
                this.primeTnt(par1World, par2, par3, par4, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase)entityarrow.shootingEntity : null);
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion par1Explosion) {
        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister iconRegister) {
    	this.blockIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":Nuke_side");
    	this.topIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":Nuke_top");
    	this.bottomIcon = iconRegister.registerIcon(RedDiamondInfo.MOD_ID + ":Nuke_bottom");
    }

}
