package democretes.render.fx;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class LaserRenderer {
	
	public static void renderLaser(World world, double x, double y, double z, int length, ForgeDirection dir, float f) {
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		
		double xStart = x;
		double yStart = y;
		double zStart = z;
		if(dir == ForgeDirection.DOWN) {
			yStart -= length;
		}
		if(dir == ForgeDirection.NORTH) {
			GL11.glRotatef(270, 1, 0, 0);
			yStart = -z;
			zStart = y;
		}else if(dir == ForgeDirection.SOUTH) {
			GL11.glRotatef(90, 1, 0, 0);
			yStart = z + 1;
			zStart = -y - 1;
		}else if(dir == ForgeDirection.EAST) {
			GL11.glRotatef(270, 0, 0, 1);
			yStart = x + 1;
			xStart = -y - 1;
		}else if(dir == ForgeDirection.WEST) {
			GL11.glRotatef(90, 0, 0, 1);
			yStart = -x;
			xStart = y;
		}
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
		GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
		RenderHelper.disableStandardItemLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		float ticks = world.getTotalWorldTime() + f;
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
