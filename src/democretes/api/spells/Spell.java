package democretes.api.spells;

import java.util.LinkedHashMap;

import democretes.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Spell {
	
	int macht;
	int purity;
	String name;
	ResourceLocation texture;
	ISpellActivation activate;
	
	public Spell(String name, int macht, int purity, ISpellActivation activate) {
		this.macht = macht;
		this.purity = purity;
		this.name = name;
		this.texture = new ResourceLocation(Reference.TEXTURE_PREFIX + "spells/" + name + ".png");
		this.spells.put(name, this);
		this.activate = activate;		
	}
	
	public Spell(String name, int macht, int purity, ISpellActivation activate, ResourceLocation texture) {
		this.macht = macht;
		this.purity = purity;
		this.name = name;
		this.texture = texture;
		this.spells.put(name, this);	
		this.activate = activate;	
	}
	
	public int getEnergyUsage() {
		return this.macht;
	}
	
	public int getPurityUsage() {
		return this.purity;
	}
	
	public ResourceLocation getTexture() {
		return this.texture;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ISpellActivation getActivator() {
		return this.activate;
	}
	
	public static LinkedHashMap<String, Spell> spells = new LinkedHashMap();

}