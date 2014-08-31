package democretes.render.fx;

import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderWorldLastEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public final class ParticleRenderDispatcher {
	
	//Entirely stolen from Vaz with permission of course.
	public static void dispatch() {
		Tessellator t = Tessellator.instance;

		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
		FXOrb.dispatchQueuedRenders(t);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
	}
	
	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		ParticleRenderDispatcher.dispatch();
	}

}
