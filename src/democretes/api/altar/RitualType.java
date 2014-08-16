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
		switch(size) {
		case 3:
			return BASIC;
		case 5:
			return ADVANCED;
		case 12:
			return COMPLEX;
		}
		return null;
	}
}
