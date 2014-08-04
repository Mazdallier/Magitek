package democretes.api.energy;

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
	
	/** 
	 * 
	 * @return
	 */
	public int getCapacity();
	
	/**
	 * 
	 * @return
	 */
	public int getMachtStored();

}
