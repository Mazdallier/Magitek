package democretes.render.tile;

import org.lwjgl.opengl.GL11;

import democretes.block.machines.TileChipCrafter;
import democretes.block.machines.TileReconstructor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;

public class RenderReconstructor extends TileEntitySpecialRenderer {

	EntityItem item;
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		if(tile.getWorldObj() != null) {
			if(((TileReconstructor)tile).inventory != null) {
				GL11.glPushMatrix();
				if(item == null) {
					item = new EntityItem(tile.getWorldObj(), tile.xCoord, tile.yCoord + 1, tile.zCoord, ((TileReconstructor)tile).inventory);
				}
				item.age = (int)tile.getWorldObj().getTotalWorldTime();
				item.setEntityItemStack(((TileReconstructor)tile).inventory);
				tile.getWorldObj().getBlock((int)x, (int)y, (int)z).setLightOpacity(0);
				tile.getWorldObj().getBlock((int)x, (int)y, (int)z).setLightLevel(15.0F);
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glTranslatef(0.5F, 1.0F, 0.5F);
				((Render)RenderManager.instance.entityRenderMap.get(EntityItem.class)).doRender(item, x, y, z, 1F, f);
				GL11.glTranslatef(-0.5F, -1.25F, -0.5F);
				GL11.glPopMatrix();
			}
		}
	}
	
	

}
