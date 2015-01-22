package democretes.api.macht;

/** TileEntities that extend IMachtStorage are able to store macht.
 * 	Please note that this extends IMachtHandler.
 * 
 * @author Democretes
 *
 */
public interface IMachtStorage extends IMachtHandler {
	
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
