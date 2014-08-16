package democretes.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import democretes.block.altar.TileAltar;
import democretes.lib.Reference;
import democretes.render.models.ModelAltar;

public class RenderAltar extends TileEntitySpecialRenderer {
	
	ModelAltar model = new ModelAltar();
	EntityItem item;

	private static final ResourceLocation modelTexture = new ResourceLocation(Reference.MODEL_ALTAR_TEXTURE);
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		GL11.glTranslatef(-.5F, -1.5F, .5F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(modelTexture);
		model.render();

		GL11.glPopMatrix();	
	    
		if(tile.getWorldObj() != null) {
			if(((TileAltar)tile).inventory != null) {
				GL11.glPushMatrix();
				if(item == null) {
					item = new EntityItem(tile.getWorldObj(), tile.xCoord, tile.yCoord + 1, tile.zCoord, ((TileAltar)tile).inventory);
				}
				item.age = (int)tile.getWorldObj().getTotalWorldTime();
				item.setEntityItemStack(((TileAltar)tile).inventory);

				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glTranslatef(0.5F, 1.0F, 0.5F);
				((Render)RenderManager.instance.entityRenderMap.get(EntityItem.class)).doRender(item, x, y, z, 1F, f);
				GL11.glTranslatef(-0.5F, -1.25F, -0.5F);
				GL11.glPopMatrix();
			}
			if(((TileAltar)tile).getRitualIcon() != null && tile.getBlockType() != null) {
				GL11.glPushMatrix();
				GL11.glTranslated(x, y, z);
				
				GL11.glRotated(90F, 1F, 0F, 0F);
				GL11.glTranslatef(-2F, -2F, -0.001F);

				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
		
				int light = 15728880;
				int lightmapX = light % 65536;
				int lightmapY = light / 65536;
				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightmapX, lightmapY);
		
				Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		
				renderIcon(0, 0, ((TileAltar)tile).getRitualIcon(), 5, 5, 240);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glPopMatrix();
			}
		}
	}
	
	//Totally did not steal this from Vazkii.
	public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, int brightness) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setBrightness(brightness);
		tessellator.addVertexWithUV(par1 + 0, par2 + par5, 0, par3Icon.getMinU(), par3Icon.getMaxV());
		tessellator.addVertexWithUV(par1 + par4, par2 + par5, 0, par3Icon.getMaxU(), par3Icon.getMaxV());
		tessellator.addVertexWithUV(par1 + par4, par2 + 0, 0, par3Icon.getMaxU(), par3Icon.getMinV());
		tessellator.addVertexWithUV(par1 + 0, par2 + 0, 0, par3Icon.getMinU(), par3Icon.getMinV());
		tessellator.draw();
	}

	

}
