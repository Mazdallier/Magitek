package democretes.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSolarGenerator extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
   public ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
  
  public ModelSolarGenerator()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 46);
      Shape1.addBox(-8F, -1F, -8F, 16, 2, 16);
      Shape1.setRotationPoint(0F, 23F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 24);
      Shape2.addBox(-1F, -4F, -7F, 2, 8, 14);
      Shape2.setRotationPoint(-6F, 18F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 24);
      Shape3.addBox(-1F, -4F, -7F, 2, 8, 14);
      Shape3.setRotationPoint(6F, 18F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 14);
      Shape4.addBox(-5F, -2F, -3F, 10, 4, 6);
      Shape4.setRotationPoint(0F, 20F, 0F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, -1, 11);
      Shape5.addBox(-5F, 0F, -1.5F, 10, 0, 3);
      Shape5.setRotationPoint(0F, 17F, 2F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, -0.7853982F, 0F, 0F);
      Shape6 = new ModelRenderer(this, -1, 11);
      Shape6.addBox(-5F, 0F, -1.5F, 10, 0, 3);
      Shape6.setRotationPoint(0F, 17F, -2F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0.7853982F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 9);
      Shape7.addBox(-5F, 0F, -1F, 10, 0, 2);
      Shape7.setRotationPoint(0F, 16F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 24, 0);
      Shape8.addBox(-5F, -0.5F, -5F, 10, 1, 10);
      Shape8.setRotationPoint(0F, 14F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 0);
      Shape9.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
      Shape9.setRotationPoint(1F, 14F, 0F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(-0.5F, 0F, -0.5F, 1, 3, 1);
      Shape10.setRotationPoint(-2F, 14F, 0F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
  }
  
  public void render()
  {
    final float f5 = 1/16F;
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
