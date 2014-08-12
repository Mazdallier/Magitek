package democretes.block.altar;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.api.altar.RitualType;
import democretes.block.BlocksMT;
import democretes.block.TilePurityBase;
import democretes.block.dummy.TileAltarDummy;
import democretes.utils.crafting.AltarRecipes;
import democretes.utils.crafting.RitualRecipes;
import democretes.utils.crafting.RitualRecipes.RitualRecipe;

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
	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) {
			return;
		}
		if(this.ritual != null ) {
			if(!dummiesExist) {
				if(!createDummies()) {
					return;
				}
			}else{
				for(TileEntity tile : dummies) {
					TileAltarDummy dummy = (TileAltarDummy)tile;
					dummy.deathTime = 1000;
				}
			}
			if(input != null) {
				energy += this.extractMacht(RitualRecipes.getMachtForCatalyst(inventory)/(this.dummies.size()*20));
				count++;
				if(count >= 40) {
					count = 0;
					((TileAltarDummy)dummies.get(slot)).removeItem();;
				}
				if(energy >= RitualRecipes.getMachtForCatalyst(inventory)) {
					this.energy = 0;
					this.energy = 0;
					ItemStack stack = RitualRecipes.getOutputForCatalyst(this.inventory).copy();
					this.inventory = stack.copy();
				}
			}
			if(this.inventory != null) {
				if(RitualRecipes.recipeExists(this.inventory)) {
					if(RitualRecipes.getTypeForCatalyst(this.inventory) == this.ritual) {
						input = new ItemStack[dummies.size()];
						for(int i = 0; i < dummies.size(); i++) {
							TileAltarDummy dummy = (TileAltarDummy)dummies.get(i);
							input[i] = dummy.inventory;
						}
						if(!RitualRecipes.inputsMatch(input, RitualRecipes.getRecipe(this.inventory).getInput())) {
							this.input = null;
							return;
						}
					}
				}
			}else{
				if(this.energy > 0) {
					this.energy = 0;
				}
			}
		}
		if(this.inventory != null) {
			if(AltarRecipes.recipeExists(this.inventory)) {
				energy += this.extractMacht(AltarRecipes.getMacht(inventory)/20);
				if(this.energy >= AltarRecipes.getMacht(inventory)*inventory.stackSize) {
					this.energy = 0;
					ItemStack stack = AltarRecipes.getResult(this.inventory).copy();
					stack.stackSize = this.inventory.stackSize;
					this.inventory = stack.copy();
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
			boolean a = world.isAirBlock(x, y, z + 2);
			boolean b = world.isAirBlock(x + 2, y, z - 2);
			boolean c = world.isAirBlock(x - 2, y, z - 2);
			if(!a || !b || !c) {
				return false;
			}
			world.setBlock(x, y, z + 2, BlocksMT.altarDummy);
			world.setBlock(x + 2, y, z - 2, BlocksMT.altarDummy);
			world.setBlock(x - 2, y, z - 2, BlocksMT.altarDummy);
			this.dummies.add(world.getTileEntity(x, y, z + 2));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 2));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 2));
		}else if(this.ritual == RitualType.ADVANCED) {
			boolean a = world.isAirBlock(x, y, z + 3);
			boolean b = world.isAirBlock(x + 3, y, z + 1);
			boolean c = world.isAirBlock(x - 3, y, z + 1);
			boolean d = world.isAirBlock(x + 2, y, z - 2);
			boolean e = world.isAirBlock(x - 2, y, z - 2);
			if(!a || !b || !c || !d || !e) {
				return false;
			}
			world.setBlock(x, y, z + 3, BlocksMT.altarDummy);
			world.setBlock(x + 3, y, z + 1, BlocksMT.altarDummy);
			world.setBlock(x - 3, y, z + 1, BlocksMT.altarDummy);
			world.setBlock(x + 2, y, z - 2, BlocksMT.altarDummy);
			world.setBlock(x - 2, y, z - 2, BlocksMT.altarDummy);
			this.dummies.add(world.getTileEntity(x, y, z + 3));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 2));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 2));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 2));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 2));
		}else{
			boolean a = world.isAirBlock(x, y, z + 5);
			boolean b = world.isAirBlock(x + 2, y, z + 3);
			boolean c = world.isAirBlock(x - 2, y, z + 3);
			boolean d = world.isAirBlock(x + 4, y, z + 2);
			boolean e = world.isAirBlock(x - 4, y, z + 2);
			boolean f = world.isAirBlock(x + 3, y, z);
			boolean g = world.isAirBlock(x - 3, y, z);
			boolean h = world.isAirBlock(x + 4, y, z - 2);
			boolean i = world.isAirBlock(x - 4, y, z - 2);
			boolean j = world.isAirBlock(x + 2, y, z - 3);
			boolean k = world.isAirBlock(x - 2, y, z - 3);
			boolean l = world.isAirBlock(x, y, z - 5);
			if(!a || !b || !c || !d || !e || !f || !g || !h || !i || !j || !k || !l) {
				return false;
			}
			world.setBlock(x, y, z + 5, BlocksMT.altarDummy);
			world.setBlock(x + 2, y, z + 3, BlocksMT.altarDummy);
			world.setBlock(x - 2, y, z + 3, BlocksMT.altarDummy);
			world.setBlock(x + 4, y, z + 2, BlocksMT.altarDummy);
			world.setBlock(x - 4, y, z + 2, BlocksMT.altarDummy);
			world.setBlock(x + 3, y, z, BlocksMT.altarDummy);
			world.setBlock(x - 3, y, z, BlocksMT.altarDummy);
			world.setBlock(x + 4, y, z - 2, BlocksMT.altarDummy);
			world.setBlock(x - 4, y, z - 2, BlocksMT.altarDummy);
			world.setBlock(x + 2, y, z - 3, BlocksMT.altarDummy);
			world.setBlock(x - 2, y, z - 3, BlocksMT.altarDummy);
			world.setBlock(x, y, z - 5, BlocksMT.altarDummy);
			this.dummies.add(world.getTileEntity(x, y, z + 5));
			this.dummies.add(world.getTileEntity(x + 2, y, z + 3));
			this.dummies.add(world.getTileEntity(x - 2, y, z + 3));
			this.dummies.add(world.getTileEntity(x + 4, y, z + 2));
			this.dummies.add(world.getTileEntity(x - 4, y, z + 2));
			this.dummies.add(world.getTileEntity(x + 3, y, z));
			this.dummies.add(world.getTileEntity(x - 3, y, z));
			this.dummies.add(world.getTileEntity(x + 4, y, z - 2));
			this.dummies.add(world.getTileEntity(x - 4, y, z - 2));
			this.dummies.add(world.getTileEntity(x + 2, y, z - 3));
			this.dummies.add(world.getTileEntity(x - 2, y, z - 3));
			this.dummies.add(world.getTileEntity(x, y, z - 5));
		}
		this.dummiesExist = true;
		return true;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory;
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
		return 64;
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
		return true;
	}	
	
	int index;
	public IIcon getRitualIcon() {
		BlockAltar altar = (BlockAltar)this.getBlockType();
		if(this.ritual != null) {
			index = this.ritual == RitualType.BASIC ? 0 : this.ritual == RitualType.ADVANCED ? 1 : 2;
			return altar.circle[index];
		}
		return null;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Index", this.index);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.index = pkt.func_148857_g().getInteger("Index");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("Dummies", dummiesExist);
		nbt.setInteger("Size", dummies.size());
		for(int i = 0; i < dummies.size(); i++) {
			nbt.setInteger("x" + i, dummies.get(i).xCoord);
			nbt.setInteger("y" + i, dummies.get(i).yCoord);
			nbt.setInteger("z" + i, dummies.get(i).zCoord);
		}
		if(this.ritual != null) {
			nbt.setInteger("Ritual", this.ritual.size);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.dummiesExist = nbt.getBoolean("Dummies");
		int size = nbt.getInteger("Size");
		for(int i = 0; i < size; i++) {
			int x = nbt.getInteger("x" + i);
			int y = nbt.getInteger("y" + i);
			int z = nbt.getInteger("z" + i);
			this.dummies.add(this.worldObj.getTileEntity(x, y, z));
		}	
		this.ritual = RitualType.getRitual(nbt.getInteger("Ritual"));
	}
		
}
