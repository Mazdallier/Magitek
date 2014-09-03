package democretes.render.tile;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import democretes.block.altar.TileAltar;
import democretes.block.machines.TileRuneConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public class RenderRuneConstructor extends TileEntitySpecialRenderer {
	
	EntityItem item;
	EntityItem item2;

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {	    
		if(tile.getWorldObj() != null) {
			if(((TileRuneConstructor)tile).inventory[0] != null) {
				ItemStack stack = ((TileRuneConstructor)tile).inventory[0];
				if(item == null) {
					item = new EntityItem(tile.getWorldObj(), 0.0D, 0.0D, 0.0D, stack);
				}
				item.setEntityItemStack(stack);
			    GL11.glPushMatrix();
			    GL11.glPushAttrib(8192);
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glTranslated(x, y + 0.25D, z);
				GL11.glTranslatef(0.45F, 0.825F, 0.35F);
				float yy = 0;
				if(stack.stackSize >= 32) {
					yy = 0;
				}else if(stack.stackSize >= 16) {
					yy = -0.015F;
				}else if(stack.stackSize >= 2) {
					yy = -0.0375F;
				}else if(stack.stackSize == 1) {
					yy = -0.06F;
				}
				GL11.glTranslatef(0.0F, yy, 0.0F);
				GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			    GL11.glEnable(3042);
			    GL11.glBlendFunc(770, 771);
			    net.minecraft.client.renderer.entity.RenderItem.renderInFrame = true;
			    RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			    net.minecraft.client.renderer.entity.RenderItem.renderInFrame = false;
			    GL11.glDisable(3042);
				GL11.glPopAttrib();
				GL11.glPopMatrix();
			}
			if(((TileRuneConstructor)tile).inventory[1] != null) {
				GL11.glPushMatrix();
				if(item2 == null) {
					item2 = new EntityItem(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord, ((TileRuneConstructor)tile).inventory[1]);
				}
				item2.age = (int)tile.getWorldObj().getTotalWorldTime();
				item2.setEntityItemStack(((TileRuneConstructor)tile).inventory[1]);
				GL11.glColor4f(1F, 1F, 1F, 1F);
				GL11.glTranslatef(0.5F, 1.25F, 0.5F);
				((Render)RenderManager.instance.entityRenderMap.get(EntityItem.class)).doRender(item2, x, y, z, 1F, f);
				GL11.glTranslatef(-0.5F, -1.25F, -0.5F);
				GL11.glPopMatrix();
			}
		}
	}

}
