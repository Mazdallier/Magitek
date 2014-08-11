package democretes.api.altar;

public enum RitualType {
	BASIC(3), 
	ADVANCED(5), 
	COMPLEX(12);
	
	public int size;
	RitualType(int recipeSize) {
		size = recipeSize;
	}
	
	public static RitualType getRitual(int size) {
		return RitualType.values()[size];
	}
}
