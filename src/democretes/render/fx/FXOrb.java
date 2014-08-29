package democretes.render.fx;

import java.util.ArrayDeque;
import java.util.Queue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import democretes.block.BlocksMT;
import democretes.block.generators.BlockGenerator;

public class FXOrb extends EntityFX {	

	public static final ResourceLocation texture = new ResourceLocation("democretes", "textures/particles/orb.png");

	boolean distanceLimit = true;

	public FXOrb(World world, double x, double y, double z, float size, float red, float blue, float green, int maxAge) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		FMLLog.info("Spawned");
		motionX = motionY = motionZ = 0;
		particleRed = red;
		particleGreen = green;
		particleBlue = blue;
		particleGravity = 0;
		particleScale *= size;
		particleMaxAge = (int)(28D / (Math.random() * 0.3D + 0.7D) * maxAge);
		
		noClip = true;
		setSize(0.01F, 0.01F);
		EntityLivingBase renderentity = FMLClientHandler.instance().getClient().renderViewEntity;

		if(distanceLimit) {
			int visibleDistance = 50;
			if (!FMLClientHandler.instance().getClient().gameSettings.fancyGraphics)
				visibleDistance = 25;

			if (renderentity == null || renderentity.getDistance(posX, posY, posZ) > visibleDistance)
				particleMaxAge = 0;
		}
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}

	@Override
	public void renderParticle(Tessellator t, float tick, float par3, float par4, float par5, float par6, float par7) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		t.setBrightness(getBrightnessForRender(tick));
		float scale = 0.1F * particleScale;
		float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)tick - interpPosX);
		float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)tick - interpPosY);
		float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)tick - interpPosZ);
		t.addVertexWithUV((double)(x - par3 * scale - par6 * scale), (double)(y - par4 * scale), (double)(z - par5 * scale - par7 * scale), 1, 1);
		t.addVertexWithUV((double)(x - par3 * scale + par6 * scale), (double)(y + par4 * scale), (double)(z - par5 * scale + par7 * scale), 1, 0);
		t.addVertexWithUV((double)(x + par3 * scale + par6 * scale), (double)(y + par4 * scale), (double)(z + par5 * scale + par7 * scale), 0, 0);
		t.addVertexWithUV((double)(x + par3 * scale - par6 * scale), (double)(y - par4 * scale), (double)(z + par5 * scale - par7 * scale), 0, 1);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if (particleAge++ >= particleMaxAge) {
			setDead();
		}
		motionY -= 0.04D * particleGravity;
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		motionX *= 0.98000001907348633D;
		motionY *= 0.98000001907348633D;
		motionZ *= 0.98000001907348633D;
	}

}
