package democretes.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMachtCoil extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Pole;
    ModelRenderer SphereCore;
    ModelRenderer SphereZ;
    ModelRenderer SphereY;
    ModelRenderer SphereX;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
  
  public ModelMachtCoil() {
    textureWidth = 128;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 8, 49);
      Base.addBox(-7F, 0F, -7F, 14, 1, 14);
      Base.setRotationPoint(0F, 23F, 0F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Pole = new ModelRenderer(this, 0, 35);
      Pole.addBox(-1F, 0F, -1F, 2, 27, 2);
      Pole.setRotationPoint(0F, -3F, 0F);
      Pole.setTextureSize(128, 64);
      Pole.mirror = true;
      setRotation(Pole, 0F, 0F, 0F);
      SphereCore = new ModelRenderer(this, 0, 12);
      SphereCore.addBox(-3F, -3F, -3F, 6, 6, 6);
      SphereCore.setRotationPoint(0F, -4F, 0F);
      SphereCore.setTextureSize(128, 64);
      SphereCore.mirror = true;
      setRotation(SphereCore, 0F, 0F, 0F);
      SphereZ = new ModelRenderer(this, 0, 0);
      SphereZ.addBox(-2F, -2F, -4F, 4, 4, 8);
      SphereZ.setRotationPoint(0F, -4F, 0F);
      SphereZ.setTextureSize(128, 64);
      SphereZ.mirror = true;
      setRotation(SphereZ, 0F, 0F, 0F);
      SphereY = new ModelRenderer(this, 0, 0);
      SphereY.addBox(-2F, -2F, -4F, 4, 4, 8);
      SphereY.setRotationPoint(0F, -4F, 0F);
      SphereY.setTextureSize(128, 64);
      SphereY.mirror = true;
      setRotation(SphereY, 1.570796F, 0F, 0F);
      SphereX = new ModelRenderer(this, 0, 0);
      SphereX.addBox(-2F, -2F, -4F, 4, 4, 8);
      SphereX.setRotationPoint(0F, -4F, 0F);
      SphereX.setTextureSize(128, 64);
      SphereX.mirror = true;
      setRotation(SphereX, 0F, -1.570796F, 0F);
      Shape1 = new ModelRenderer(this, 24, 16);
      Shape1.addBox(-4F, 0F, -4F, 8, 1, 8);
      Shape1.setRotationPoint(0F, 1F, 0F);
      Shape1.setTextureSize(128, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 8, 25);
      Shape2.addBox(-5F, 0F, -5F, 10, 1, 10);
      Shape2.setRotationPoint(0F, 9F, 0F);
      Shape2.setTextureSize(128, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 8, 36);
      Shape3.addBox(-6F, 0F, -6F, 12, 1, 12);
      Shape3.setRotationPoint(0F, 17F, 0F);
      Shape3.setTextureSize(128, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
  }
  
  public void render() {
    final float f5 = 1/16F;
    Base.render(f5);
    Pole.render(f5);
    SphereCore.render(f5);
    SphereZ.render(f5);
    SphereY.render(f5);
    SphereX.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
