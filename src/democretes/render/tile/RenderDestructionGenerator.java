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
import democretes.render.fx.LaserRenderer;

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
	    	LaserRenderer.renderLaser(tile.getWorldObj(), x, y + 0.25F, z, ((TileDestructionGenerator)tile).yCoord - ((TileDestructionGenerator)tile).layer + 1, ForgeDirection.DOWN, f);
	    }
	    GL11.glPopMatrix();
	}
	
	

}
