package democretes.api.macht;

/** TileEntities that extend IMachtStorage are able to store macht.
 * 	Please note that this extends IMachtHandler.
 * 
 * @author Democretes
 *
 */
public interface IMachtStorage extends IMachtHandler {
	
	/** Checks if the tile is supercharged. Supercharged tiles store and use more energy than normal tiles.
	 * 
	 * @return True if the tile is supercharged.
	 */
	public boolean isSupercharged();
	
	/** Set the tile entities supercharge state to the param charge.
	 * 
	 * @param charge
	 */
	public void setSupercharged(boolean charge);
	
	/** Return the maximum amount of macht the tile can store.
	 * 
	 * @return capacity
	 */
	public int getCapacity();
	
	/** Returns the amount of macht currently stored.
	 * 
	 * @return macht
	 */
	public int getMachtStored();

}
