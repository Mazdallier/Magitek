package democretes.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import democretes.block.generators.TileDestructionGenerator;

public class RenderDestructionGenerator extends TileEntitySpecialRenderer {
	
	private IModelCustom model;
	private ResourceLocation texture = new ResourceLocation("democretes", "textures/models/destruction.png");
	
	public RenderDestructionGenerator() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation("democretes","misc/models/destruction.obj"));
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glColor4f((float)7/256, (float)132/256, (float)212/256, 0.95F);
		
	    float t = (float)Minecraft.getSystemTime();
	    GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
	    GL11.glRotatef(t/250, 0.0F, 1.0F, 0.0F);
	    GL11.glScalef(0.3F, 0.275F, 0.3F);
	    
	    Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
	    model.renderAll();
	    	    
	    GL11.glPopMatrix();		
	    
	    GL11.glPushMatrix();
	    if( ((TileDestructionGenerator)tile).layer > 1) {
	    	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("democretes", "textures/misc/beacon_beam.png"));
	    	renderLaser(tile, x, y + 0.25F, z, ((TileDestructionGenerator)tile).yCoord - ((TileDestructionGenerator)tile).layer + 1, f);
	    }
	    GL11.glPopMatrix();
	}
	
	public static void renderLaser(TileEntity host, double x, double y, double z, int length, float partialTicks) {
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		
		double xStart = x;
		double yStart = y;
		double zStart = z;
		yStart -= length;
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		RenderHelper.disableStandardItemLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		float ticks = host.getWorldObj().getTotalWorldTime() + partialTicks;
		float f3 = -ticks * 0.2F - MathHelper.floor_float(-ticks * 0.1F);
		double d3 = ticks * 0.025D * (1.0D - 2.5D);
		t.startDrawingQuads();
		t.setColorRGBA(255, 255, 255, 32);
		double d4 = 0.2D;
		double d5 = 0.5D + Math.cos(d3 + 2.356194490192345D) * d4;
		double d6 = 0.5D + Math.sin(d3 + 2.356194490192345D) * d4;
		double d7 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * d4;
		double d8 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * d4;
		double d9 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * d4;
		double d10 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * d4;
		double d11 = 0.5D + Math.cos(d3 + 5.497787143782138D) * d4;
		double d12 = 0.5D + Math.sin(d3 + 5.497787143782138D) * d4;
		double height = length;
		double uStart = 0.0D;
		double uEnd = 1.0D;
		double vStart = -1.0F + f3;
		double vEnd = (256.0F) * (0.5D / d4) + vStart;
		t.addVertexWithUV(xStart + d5, yStart + height, zStart + d6, uEnd, vEnd);
		t.addVertexWithUV(xStart + d5, yStart, zStart + d6, uEnd, vStart);
		t.addVertexWithUV(xStart + d7, yStart, zStart + d8, uStart, vStart);
		t.addVertexWithUV(xStart + d7, yStart + height, zStart + d8, uStart, vEnd);
		t.addVertexWithUV(xStart + d11, yStart + height, zStart + d12, uEnd, vEnd);
		t.addVertexWithUV(xStart + d11, yStart, zStart + d12, uEnd, vStart);
		t.addVertexWithUV(xStart + d9, yStart, zStart + d10, uStart, vStart);
		t.addVertexWithUV(xStart + d9, yStart + height, zStart + d10, uStart, vEnd);
		t.addVertexWithUV(xStart + d7, yStart + height, zStart + d8, uEnd, vEnd);
		t.addVertexWithUV(xStart + d7, yStart, zStart + d8, uEnd, vStart);
		t.addVertexWithUV(xStart + d11, yStart, zStart + d12, uStart, vStart);
		t.addVertexWithUV(xStart + d11, yStart + height, zStart + d12, uStart, vEnd);
		t.addVertexWithUV(xStart + d9, yStart + height, zStart + d10, uEnd, vEnd);
		t.addVertexWithUV(xStart + d9, yStart, zStart + d10, uEnd, vStart);
		t.addVertexWithUV(xStart + d5, yStart, zStart + d6, uStart, vStart);
		t.addVertexWithUV(xStart + d5, yStart + height, zStart + d6, uStart, vEnd);
		t.draw();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDepthMask(false);
		t.startDrawingQuads();
		t.setColorRGBA(255, 255, 255, 32);
		double d18 = 0.2D;
		double d19 = 0.2D;
		double d20 = 0.8D;
		double d21 = 0.2D;
		double d22 = 0.2D;
		double d23 = 0.8D;
		double d24 = 0.8D;
		double d25 = 0.8D;
		double d29 = -1.0F + f3;
		double d30 = (256.0F) + d29;
		t.addVertexWithUV(xStart + d18, yStart + height, zStart + d19, uEnd, d30);
		t.addVertexWithUV(xStart + d18, yStart, zStart + d19, uEnd, d29);
		t.addVertexWithUV(xStart + d20, yStart, zStart + d21, uStart, d29);
		t.addVertexWithUV(xStart + d20, yStart + height, zStart + d21, uStart, d30);
		t.addVertexWithUV(xStart + d24, yStart + height, zStart + d25, uEnd, d30);
		t.addVertexWithUV(xStart + d24, yStart, zStart + d25, uEnd, d29);
		t.addVertexWithUV(xStart + d22, yStart, zStart + d23, uStart, d29);
		t.addVertexWithUV(xStart + d22, yStart + height, zStart + d23, uStart, d30);
		t.addVertexWithUV(xStart + d20, yStart + height, zStart + d21, uEnd, d30);
		t.addVertexWithUV(xStart + d20, yStart, zStart + d21, uEnd, d29);
		t.addVertexWithUV(xStart + d24, yStart, zStart + d25, uStart, d29);
		t.addVertexWithUV(xStart + d24, yStart + height, zStart + d25, uStart, d30);
		t.addVertexWithUV(xStart + d22, yStart + height, zStart + d23, uEnd, d30);
		t.addVertexWithUV(xStart + d22, yStart, zStart + d23, uEnd, d29);
		t.addVertexWithUV(xStart + d18, yStart, zStart + d19, uStart, d29);
		t.addVertexWithUV(xStart + d18, yStart + height, zStart + d19, uStart, d30);
		
		t.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

}
