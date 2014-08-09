package democretes.render.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SmokeFX extends EntityFX {
	
	ResourceLocation texture = new ResourceLocation("democretes", "textures/misc/smoke.png");

	public SmokeFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY,	motionZ);

		particleAlpha = 0.75F * rand.nextFloat();
	}
	
	@Override
	public void renderParticle(Tessellator t, float tick, float par3, float par4, float par5, float par6, float par7) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		t.startDrawingQuads();
		t.setBrightness(getBrightnessForRender(tick));
		float scale = 0.1F * particleScale;
		float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)tick - interpPosX);
		float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)tick - interpPosY);
		float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)tick - interpPosZ);
		t.addVertexWithUV((double)(x - par3 * scale - par6 * scale), (double)(y - par4 * scale), (double)(z - par5 * scale - par7 * scale), 1, 1);
		t.addVertexWithUV((double)(x - par3 * scale + par6 * scale), (double)(y + par4 * scale), (double)(z - par5 * scale + par7 * scale), 1, 0);
		t.addVertexWithUV((double)(x + par3 * scale + par6 * scale), (double)(y + par4 * scale), (double)(z + par5 * scale + par7 * scale), 0, 0);
		t.addVertexWithUV((double)(x + par3 * scale - par6 * scale), (double)(y - par4 * scale), (double)(z + par5 * scale - par7 * scale), 0, 1);
		t.draw();
	}

}
