package democretes.api.purity;

/** 
 * 
 * @author Democretes
 *
 */
public interface IPurityHandler {
	
	/** Returns the amount of purity is in the tile.
	 * 
	 * @return purity
	 */
	public int getPurity();

	/** Used to determine whether or not the tile is considered dark based on its purity.
	 *  
	 * 
	 * @return dark
	 */
	public boolean isDark();
	
	/** Used to determine whether or not the tile is considered neutral based on its purity.
	 * 
	 * @return
	 */
	public boolean isNeutral();
	
	/** Used to determine whether or not the tile is considered light based on its purity.
	 * 
	 * @return
	 */
	public boolean isLight();
	
	/** Increases the purity of the tile by the amount.
	 * 
	 * @param amount
	 */
	public void increasePurity(int amount);
	
	/** Decreases the purity of the tile by the amount
	 * 
	 * @param amount
	 */
	public void decreasePurity(int amount);
	
	/** Used to determine if the purity is at it's max.
	 *  This should account for both positive and negative Purities.
	 * 
	 * @return
	 */
	public boolean isFull();
}
