package democretes.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import democretes.render.models.ModelMachtCoil;

public class RenderMachtCoil extends TileEntitySpecialRenderer { 
	

		ModelMachtCoil model = new ModelMachtCoil();
		private ResourceLocation texture = new ResourceLocation("democretes", "textures/models/ModelMachtCoil.png");
		
		
		@Override
		public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x, (float)y, (float)z);
			GL11.glScalef(-1F, -1F, 1F);
			GL11.glTranslatef(-.5F, -1.5F, .5F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture(texture);
			model.render();

			GL11.glPopMatrix();	  
		}

}
