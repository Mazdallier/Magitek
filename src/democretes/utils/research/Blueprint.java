package democretes.utils.research;

public class Blueprint {
	
	String name;
	ResearchType type;
	BlueprintPage[] page;	
	
	public Blueprint(String blueprintName, ResearchType type, BlueprintPage[] page) {
		this.name = blueprintName;
		this.type = type;
		this.page = page;
		BlueprintHelper.blueprints.add(this);
	}
	
	public enum ResearchType {
		MINING,
		CRAFTING,
		SMELTING,
		ARMOR,
		DAMAGE;		
	}

}
