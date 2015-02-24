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
	
	public String getName() {
		return name;
	}
	
	public ResearchType getResearchType() {
		return type;
	}
	
	public BlueprintPage[] getPages() {
		return page;
	}
	
	public enum ResearchType {
		COMBAT,
		CRAFTING,
		FARMING,
		MACHINE,
		MINING,
		MISC,
		SMELTING,
		WOODCUTTING;		
	}

}
