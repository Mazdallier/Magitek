package democretes.api.purity;

public interface IPurityHandler {
	
	public int getPurity();

	public boolean isDark();
	
	public boolean isNeutral();
	
	public boolean isLight();
	
	public void increasePurity(int amount);
	
	public void decreasePurity(int amount);
}
