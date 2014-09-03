package democretes.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;



public class ModelRuneGenerator extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
  
  public ModelRuneGenerator()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape1 = new ModelRenderer(this, 0, 18);
      Shape1.addBox(-6F, 0F, -6F, 12, 2, 12);
      Shape1.setRotationPoint(0F, 22F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 6);
      Shape2.addBox(-5F, 0F, -5F, 10, 2, 10);
      Shape2.setRotationPoint(0F, 20F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 32, 0);
      Shape3.addBox(-4F, -3F, -4F, 8, 6, 8);
      Shape3.setRotationPoint(0F, 17F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
  }
  
  public void render()
  {
	 final float f5 = 1/16F;
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
