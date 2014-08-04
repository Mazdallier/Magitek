package democretes.api.macht;

/** TileEntity that implement this interface are able to receive macht and have macht extracted from it.
 *  TileEntities that store Macht should use {@link}IMachtStorage.
 * 
 * @author Democretes
 *
 */

public interface IMachtHandler {
	
	/** Used to extract macht from an Macht source.
	 * 
	 * @param Amount of macht to be extracted.
	 * 
	 * @return How much macht was exracted.
	 */
	public int extractMacht(int amount);
	
	
	/** Used to by a macht source to receive macht
	 * 
	 * @param Amount of macht to be received
	 * 
	 * @return How much macht was received.
	 */
	public int receiveMacht(int amount);
	
	

}
