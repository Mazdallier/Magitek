package democretes.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderEnergyCoil extends TileEntitySpecialRenderer { 

		//private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("democretes","misc/models/coil.obj"));
		private ResourceLocation texture = new ResourceLocation("democretes", "textures/models/dodecahedron.png");
		
		@Override
		public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			GL11.glColor4f(0.95F, 0.95F, 0.95F, 0.7F);
			
		    float t = (float)Minecraft.getSystemTime();
		    GL11.glTranslatef((float)x + 0.5F, (float)y + 0.2F, (float)z + 0.5F);
		    GL11.glTranslated(0D, Math.sin(t/700D)/20 - 0.025, 0D);	    
		    
		    GL11.glScalef(0.4F, 0.4F, 0.4F);
		    GL11.glRotatef(t/250, 0.0F, 1.0F, 0.0F);
		    
		    Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
		    //this.model.renderAll();
		    	    
		    GL11.glPopMatrix();		    
		}

}
