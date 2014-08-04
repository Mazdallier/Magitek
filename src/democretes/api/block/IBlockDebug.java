package democretes.api.block;

import net.minecraft.entity.player.EntityPlayer;

/** Used as a block debugger. You don't really need to use this unless you really want to.
 * 
 * @author Democretes
 *
 */
public interface IBlockDebug {
	
	public BlockInfo getInfo(EntityPlayer player, int x, int y, int z);

}
