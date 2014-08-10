package democretes.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import democretes.block.coils.TileMachtCoil;
import democretes.lib.RenderIds;

public class RenderBlockCoil implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher dispatch = TileEntityRendererDispatcher.instance;
		switch(meta) {
		case 0:	
			dispatch.renderTileEntityAt(new TileMachtCoil(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RenderIds.idNODE;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}
