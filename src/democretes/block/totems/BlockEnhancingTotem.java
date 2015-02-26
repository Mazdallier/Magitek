package democretes.block.totems;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import democretes.block.BlockMTBase;
import democretes.block.MTBlocks;
import democretes.lib.Reference;

public class BlockEnhancingTotem extends BlockMTBase {
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Reference.MOD_PREFIX + ".totem.enhancer";
	}

	IIcon[] icon = new IIcon[3];
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		icon[0] = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem/totem_enhancer_bot");
		icon[1] = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem/totem_enhancer_top");
		icon[2] = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem/totem_enhancer");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side < 2) {
			return icon[side];
		}
		return icon[2];
	}
}
