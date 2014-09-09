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
import democretes.utils.handlers.ConfigHandler;

public class FXOrb extends EntityFX {	


	public static Queue<FXOrb> queuedRenders = new ArrayDeque();
	
	// Queue values
	float f;
	float f1;
	float f2;
	float f3;
	float f4;
	float f5;
	
	public static final ResourceLocation texture = new ResourceLocation("democretes", "textures/particles/orb.png");

	boolean distanceLimit = true;
	boolean shrink;

	public FXOrb(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float red, float green, float blue, float size, int maxAge, boolean shrink) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		particleRed = red;
		particleGreen = green;
		particleBlue = blue;
		particleGravity = 0;
		particleScale *= size;
		particleMaxAge = (int)(10D / (Math.random() * 0.3D + 0.7D) * maxAge);
		this.shrink = shrink;

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
	
	public static void dispatchQueuedRenders(Tessellator t) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		t.startDrawingQuads();
		for(FXOrb orb : queuedRenders)
			orb.renderQueued(t);
		t.draw();		

		queuedRenders.clear();
	}

	boolean other = true;
	private void renderQueued(Tessellator t) {
		if(shrink)  {
			scale -= 0.00075F;
			if(other) {
				particleScale *= (scale+0.95)/2;
				other = false;
			}else{
				other = true;
			}
		}
		
		float f10 = 0.5F * particleScale;
		float f11 = (float)(prevPosX + (posX - prevPosX) * f - interpPosX);
		float f12 = (float)(prevPosY + (posY - prevPosY) * f - interpPosY);
		float f13 = (float)(prevPosZ + (posZ - prevPosZ) * f - interpPosZ);

		t.setBrightness(240);
		t.setColorRGBA_F(particleRed, particleGreen, particleBlue, 0.5F);
		t.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, 0, 1);
		t.addVertexWithUV(f11 - f1 * f10 + f4 * f10, f12 + f2 * f10, f13 - f3 * f10 + f5 * f10, 1, 1);
		t.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, 1, 0);
		t.addVertexWithUV(f11 + f1 * f10 - f4 * f10, f12 - f2 * f10, f13 + f3 * f10 - f5 * f10, 0, 0);
	}

	@Override
	public void renderParticle(Tessellator t, float tick, float f1, float f2, float f3, float f4, float f5) {
		this.f = tick;
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
		this.f4 = f4;
		this.f5 = f5;

		queuedRenders.add(this);
	}

	float scale = 0.99F;
	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if(particleAge++ >= particleMaxAge) {
			setDead();
		}
		motionY -= 0.04D * particleGravity;
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		
	}

}
