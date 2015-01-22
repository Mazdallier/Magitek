package democretes.block.altar;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.Magitek;
import democretes.api.RitualType;
import democretes.api.helpers.AltarHelper;
import democretes.api.helpers.RitualHelper;
import democretes.api.recipe.RitualRecipe;
import democretes.api.spells.Spell;
import democretes.block.MTBlocks;
import democretes.block.TilePurityBase;
import democretes.block.dummy.TileAltarDummy;
import democretes.item.MTItems;

public class TileAltar extends TilePurityBase implements IInventory{

	public ItemStack inventory;
	public RitualType ritual;
	
	public TileAltar() {
		super(10000);
	}
	
	int energy;
	boolean dummiesExist;
	ItemStack[] input;
	int count;
	ArrayList<TileEntity> dummies = new ArrayList();
	int slot;
	boolean hasInputs;
	ItemStack output;
	@Override
	public void updateEntity() {
		if(this.ritual != null) {
			if(!dummiesExist) {
				if(!createDummies()) {
					this.ritual = null;
					return;
				}
			}
			if(dummies != null) {
				for(TileEntity tile : dummies) {
					if(tile == null) {
						break;
					}
					TileAltarDummy dummy = (TileAltarDummy)tile;
					dummy.deathTime = 40;
				}	
			}
			if(input != null && this.inventory != null) {
				energy += this.extractMacht(RitualHelper.getMachtForCatalyst(inventory)/(this.dummies.size()*20));
				TileAltarDummy dummy = !hasInputs ? (TileAltarDummy)dummies.get(slot) : null;
				if(energy > 0 && !hasInputs && dummy != null) {
					double motionX = (this.xCoord - dummy.xCoord + Math.random() - Math.random())/40.0D;
					double motionY = 0.045D;
					double motionZ = (this.zCoord - dummy.zCoord + Math.random() - Math.random())/40.0D;
					Color color = new Color(dummy.inventory.getItem().getColorFromItemStack(dummy.inventory, dummy.inventory.getItemDamage()));
					if(Math.random() > 0.8D) {
						Magitek.proxy.orbFX(this.worldObj, dummy.xCoord + 0.5D, dummy.yCoord+ 0.2D, dummy.zCoord + 0.5D, motionX, motionY, motionZ, color.getRed()/256.0F, color.getGreen()/256.0F, color.getBlue()/256.0F, (float)Math.random(), 3, true);	
					}
					if(count++ >= 80) {
						count = 0;
						dummy.decrStackSize(0, 1);;
						slot++;
						if(slot == dummies.size()) {
							hasInputs = true;
						}
					}
				}
				if(energy >= RitualHelper.getMachtForCatalyst(this.inventory) && hasInputs && output != null) {
					this.energy = 0;
					this.input = null;
					this.slot = 0;
					this.hasInputs = false;
					this.inventory = null;
					ItemStack stack = output.copy();
					this.output = null;
					if(stack.getItem() == MTItems.binder) {
						stack.stackTagCompound = new NBTTagCompound();
						stack.stackTagCompound.setString("SpellName", (String)Spell.spells.keySet().toArray()[stack.getItemDamage()]);						
					}
					this.inventory = stack.copy();
					for(int i = 0; i < 15; i++) {
						Magitek.proxy.orbFX(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.0D, this.zCoord + 0.5D, (Math.random() - Math.random())/10, (Math.random() - Math.random())/10, (Math.random() - Math.random())/10, (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 2, true);
					}
					this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
				}
			}else if(this.inventory != null) {
				ArrayList<RitualRecipe> recipes = RitualHelper.getRecipes(this.inventory);
				for(RitualRecipe recipe : recipes) {
					if(recipe.getRitual() == this.ritual) {
						input = new ItemStack[dummies.size()];
						for(int i = 0; i < dummies.size(); i++) {
							TileAltarDummy dummy = (TileAltarDummy)dummies.get(i);
							input[i] = dummy.getStackInSlot(0);
							worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
						}
						if(!RitualHelper.inputsMatch(input, recipe.getInput())) {
							this.input = null;
						}else{
							this.output = recipe.getOutput();
							return;
						}
					}
				}
			}else{
				this.energy = 0;
				this.input = null;
				this.output = null;
				this.hasInputs = false;
				this.slot = 0;
			}
		}else if(this.inventory != null) {
			if(AltarHelper.recipeExists(this.inventory)) {
				energy += this.extractMacht(AltarHelper.getMacht(inventory)/20);
				if(this.energy >= AltarHelper.getMacht(inventory)*inventory.stackSize) {
					this.energy = 0;
					ItemStack stack = AltarHelper.getResult(this.inventory).copy();
					stack.stackSize = this.inventory.stackSize;
					this.inventory = stack.copy();
					worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
					for(int i = 0; i < 15; i++) {
						Magitek.proxy.orbFX(this.worldObj, this.xCoord + 0.5D, this.yCoord + 1.0D, this.zCoord + 0.5D, (Math.random() - Math.random())/10, (Math.random() - Math.random())/10, (Math.random() - Math.random())/10, (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 2, true);
					}
				}				
			}
		}else{
			if(this.energy > 0) {
				this.energy = 0;
			}
		}
	}
	
	public boolean createDummies() {
		World world = this.worldObj;
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		if(this.ritual == RitualType.BASIC) {
			boolean a = world.isAirBlock(x, y, z + 2) || world.getBlock(x, y, z + 2) == MTBlocks.altarDummy;
			boolean b = world.isAirBlock(x + 2, y, z - 2) || world.getBlock(x + 2, y, z - 2) == MTBlocks.altarDummy;
			boolean c = world.isAirBlock(x - 2, y, z - 2) || world.getBlock(x - 2, y, z - 2) == MTBlocks.altarDummy;
			if(!a || !b || !c) {
				return false;
			}
			world.setBlock(x, y, z + 2, MTBlocks.altarDummy);
			world.setBlock(x + 2, y, z - 2, MTBlocks.altarDummy);
			world.setBlock(x - 2, y, z - 2, MTBlocks.altarDummy);
			this.dummies.add(world.getTileEntity(x, y, z + 2));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 2));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 2));
		}else if(this.ritual == RitualType.ADVANCED) {
			boolean a = world.isAirBlock(x, y, z + 3) || world.getBlock(x, y, z + 3) == MTBlocks.altarDummy;
			boolean b = world.isAirBlock(x + 3, y, z + 1) || world.getBlock(x + 3, y, z + 1) == MTBlocks.altarDummy;
			boolean c = world.isAirBlock(x - 3, y, z + 1) || world.getBlock(x - 3, y, z + 1) == MTBlocks.altarDummy;
			boolean d = world.isAirBlock(x + 2, y, z - 2) || world.getBlock(x + 2, y, z - 2) == MTBlocks.altarDummy;
			boolean e = world.isAirBlock(x - 2, y, z - 2) || world.getBlock(x - 2, y, z - 2) == MTBlocks.altarDummy;
			if(!a || !b || !c || !d || !e) {
				return false;
			}
			world.setBlock(x, y, z + 3, MTBlocks.altarDummy);
			world.setBlock(x + 3, y, z + 1, MTBlocks.altarDummy);
			world.setBlock(x - 3, y, z + 1, MTBlocks.altarDummy);
			world.setBlock(x + 2, y, z - 2, MTBlocks.altarDummy);
			world.setBlock(x - 2, y, z - 2, MTBlocks.altarDummy);
			this.dummies.add(world.getTileEntity(x, y, z + 3));
			this.dummies.add(world.getTileEntity(x + 3, y, z + 1));
			this.dummies.add(world.getTileEntity(x - 3, y, z + 1));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 2));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 2));
		}else{
			boolean a = world.isAirBlock(x, y, z + 4) || world.getBlock(x, y, z + 4) == MTBlocks.altarDummy;
			boolean b = world.isAirBlock(x + 2, y, z + 3) || world.getBlock(x + 2, y, z + 3) == MTBlocks.altarDummy;
			boolean c = world.isAirBlock(x - 2, y, z + 3) || world.getBlock(x - 2, y, z + 3) == MTBlocks.altarDummy;
			boolean d = world.isAirBlock(x + 4, y, z + 1) || world.getBlock(x + 4, y, z + 1) == MTBlocks.altarDummy;
			boolean e = world.isAirBlock(x - 4, y, z + 1) || world.getBlock(x - 4, y, z + 1) == MTBlocks.altarDummy;
			boolean h = world.isAirBlock(x + 4, y, z - 1) || world.getBlock(x + 4, y, z - 1) == MTBlocks.altarDummy;
			boolean i = world.isAirBlock(x - 4, y, z - 1) || world.getBlock(x - 4, y, z - 1) == MTBlocks.altarDummy;
			boolean j = world.isAirBlock(x + 2, y, z - 3) || world.getBlock(x + 2, y, z - 3) == MTBlocks.altarDummy;
			boolean k = world.isAirBlock(x - 2, y, z - 3) || world.getBlock(x - 2, y, z - 3) == MTBlocks.altarDummy;
			boolean l = world.isAirBlock(x, y, z - 4) || world.getBlock(x, y, z - 4) == MTBlocks.altarDummy;;
			if(!a || !b || !c || !d || !e || !h || !i || !j || !k || !l) {
				return false;
			}
			world.setBlock(x, y, z + 4, MTBlocks.altarDummy);
			world.setBlock(x + 2, y, z + 3, MTBlocks.altarDummy);
			world.setBlock(x - 2, y, z + 3, MTBlocks.altarDummy);
			world.setBlock(x + 4, y, z + 1, MTBlocks.altarDummy);
			world.setBlock(x - 4, y, z + 1, MTBlocks.altarDummy);
			world.setBlock(x + 4, y, z - 1, MTBlocks.altarDummy);
			world.setBlock(x - 4, y, z - 1, MTBlocks.altarDummy);
			world.setBlock(x + 2, y, z - 3, MTBlocks.altarDummy);
			world.setBlock(x - 2, y, z - 3, MTBlocks.altarDummy);
			world.setBlock(x, y, z - 4, MTBlocks.altarDummy);
			this.dummies.add(world.getTileEntity(x, y, z + 4));
			this.dummies.add(world.getTileEntity(x + 2, y, z + 3));
			this.dummies.add(world.getTileEntity(x - 2, y, z + 3));
			this.dummies.add(world.getTileEntity(x + 4, y, z + 1));
			this.dummies.add(world.getTileEntity(x - 4, y, z + 1));
			this.dummies.add(world.getTileEntity(x + 4, y, z - 1));
			this.dummies.add(world.getTileEntity(x - 4, y, z - 1));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 3));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 3));
			this.dummies.add(world.getTileEntity(x, y, z - 4));
		}
		this.dummiesExist = true;
		return true;
	}
	
	public void setRitual(RitualType type) {
		this.ritual = type;
		this.input = null;
		this.energy = 0;
		if(this.dummies.size() > 0) {
			for(TileEntity tile : this.dummies) {
				this.worldObj.setBlockToAir(tile.xCoord, tile.yCoord, tile.zCoord);
				this.worldObj.removeTileEntity(tile.xCoord, tile.yCoord, tile.zCoord);
			}
		}
		this.dummies = new ArrayList();
		this.dummiesExist = false;
		this.hasInputs = false;
		this.slot = 0;
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory == null ? null : this.inventory.copy();
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		this.inventory.stackSize -= amount;
		if(this.inventory.stackSize <= 0) {
			this.inventory = null;
		}
		return this.inventory;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory = stack;
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public String getInventoryName() {
		return "TileAltarInventory";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return this.ritual == null ? 64 : 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return AltarHelper.recipeExists(stack);
	}	
	
	
	public IIcon getRitualIcon() {
		BlockAltar altar = (BlockAltar)this.getBlockType();
		if(this.ritual != null) {
			int index = this.ritual == RitualType.BASIC ? 1 : this.ritual == RitualType.ADVANCED ? 2 : this.ritual == RitualType.COMPLEX ? 3 : 0;
			return altar.circle[index-1];
		}
		return null;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		if(this.ritual != null) {
			nbt.setInteger("Ritual", this.ritual.size);
		}
		NBTTagCompound tag = new NBTTagCompound();
		if(this.inventory != null) {
			this.inventory.writeToNBT(tag);
		}
		nbt.setTag("Item", tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.ritual = RitualType.getRitual(pkt.func_148857_g().getInteger("Ritual"));
		NBTTagCompound tag = pkt.func_148857_g().getCompoundTag("Item");
		this.inventory = ItemStack.loadItemStackFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);	
			
		nbt.setInteger("Energy", this.energy);
		nbt.setInteger("Slot", this.slot);
		nbt.setBoolean("Has", hasInputs);
		
		if(this.ritual != null) {
			nbt.setInteger("Ritual", this.ritual.size);
		}
		
		NBTTagCompound tag = new NBTTagCompound();
		if(this.inventory != null) {
			this.inventory.writeToNBT(tag);
		}
		nbt.setTag("Item", tag);
		
		if(this.input != null) {
			nbt.setBoolean("Exists", true);
			NBTTagList list = new NBTTagList();
			for(int i = 0; i < this.input.length; ++i) {
				if (this.input[i] != null) {
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte)i);
					this.input[i].writeToNBT(nbttagcompound1);
					list.appendTag(nbttagcompound1);
				}
			}
			nbt.setTag("Items", list);
        }
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {		
		super.readFromNBT(nbt);
		
		this.energy = nbt.getInteger("Energy");
		this.hasInputs = nbt.getBoolean("Has");
		this.slot = nbt.getInteger("Slot");
		this.ritual = RitualType.getRitual(nbt.getInteger("Ritual"));
		
		NBTTagCompound tag = nbt.getCompoundTag("Item");
		this.inventory = ItemStack.loadItemStackFromNBT(tag);	

		if(nbt.getBoolean("Exists")) {
			this.input = new ItemStack[RitualHelper.getRecipe(this.inventory).getInput().length];
			NBTTagList list = nbt.getTagList("Items", 10);
			for(int i = 0; i < list.tagCount(); ++i) {
				NBTTagCompound nbttagcompound1 = list.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j >= 0 && j < this.input.length) {
					this.input[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
				}
			}
        }
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536;
	}
		
}
