package democretes.render.tile;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.block.generators.TileRunicGenerator;
import democretes.block.machines.TileRuneConstructor;
import democretes.render.models.ModelRuneGenerator;

public class RenderRuneGenerator extends TileEntitySpecialRenderer {
	
	EntityItem item;
	
	ModelRuneGenerator model = new ModelRuneGenerator();

	private ResourceLocation texture = new ResourceLocation("democretes", "textures/models/ModelRuneGenerator.png");

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		GL11.glTranslatef(-.5F, -1.5F, .5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(texture);
		model.render();

		GL11.glPopMatrix();	
		if(tile.getWorldObj() != null) {
			if(((TileRunicGenerator)tile).inventory != null) {
				ItemStack stack = ((TileRunicGenerator)tile).inventory;
				if(item == null) {
					item = new EntityItem(tile.getWorldObj(), 0.0D, 0.0D, 0.0D, stack);
				}
				item.setEntityItemStack(stack);
			    GL11.glPushMatrix();
			    GL11.glPushAttrib(8192);
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glTranslated(x, y - 0.125D, z);
				GL11.glTranslatef(0.5F, 0.825F, 0.225F);
				float yy = 0;
				if(stack.stackSize >= 32) {
					yy = 0;
				}else if(stack.stackSize >= 16) {
					yy = -0.015F;
				}else if(stack.stackSize >= 2) {
					yy = -0.0375F;
				}else if(stack.stackSize == 1) {
					yy = -0.06F;
				}
				GL11.glTranslatef(0.0F, yy, 0.0F);
				GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			    GL11.glEnable(3042);
			    GL11.glBlendFunc(770, 771);
			    net.minecraft.client.renderer.entity.RenderItem.renderInFrame = true;
			    RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			    net.minecraft.client.renderer.entity.RenderItem.renderInFrame = false;
			    GL11.glDisable(3042);
				GL11.glPopAttrib();
				GL11.glPopMatrix();
			}
		}
	}

}
