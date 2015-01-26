package democretes.render.tile;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.tileentity.RenderEndPortal;
import net.minecraft.tileentity.TileEntityEndPortal;

public class RenderPurityAltar extends RenderEndPortal {
	
	@Override
    public void renderTileEntityAt(TileEntityEndPortal tile, double x, double y, double z, float f) {
    	GL11.glPushMatrix();
    	GL11.glTranslatef(0, -0.4F, 0);
    	super.renderTileEntityAt(tile, x, y, z, f);
    	GL11.glPopMatrix();
    }

}
