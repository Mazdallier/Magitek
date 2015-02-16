package democretes.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBounceGenerator extends ModelBase
{
  //fields
    ModelRenderer Center;
    ModelRenderer FrontBlock;
    ModelRenderer BackBlock;
    ModelRenderer Point;
    ModelRenderer OrbA1;
    ModelRenderer OrbA2;
    ModelRenderer OrbA3;
    ModelRenderer OrbA4;
    ModelRenderer OrbB1;
    ModelRenderer OrbB2;
    ModelRenderer OrbB3;
    ModelRenderer OrbB4;
  
  public ModelBounceGenerator()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Center = new ModelRenderer(this, 0, 22);
      Center.addBox(-4F, -2.5F, -2.5F, 10, 5, 5);
      Center.setRotationPoint(0F, 16F, 0F);
      Center.setTextureSize(64, 32);
      Center.mirror = true;
      setRotation(Center, 0F, 0F, 0F);
      FrontBlock = new ModelRenderer(this, 0, 16);
      FrontBlock.addBox(-1F, -1.5F, -1.5F, 3, 3, 3);
      FrontBlock.setRotationPoint(7F, 16F, 0F);
      FrontBlock.setTextureSize(64, 32);
      FrontBlock.mirror = true;
      setRotation(FrontBlock, 0F, 0F, 0F);
      BackBlock = new ModelRenderer(this, 0, 16);
      BackBlock.addBox(-1F, -1.5F, -1.5F, 3, 3, 3);
      BackBlock.setRotationPoint(-6F, 16F, 0F);
      BackBlock.setTextureSize(64, 32);
      BackBlock.mirror = true;
      setRotation(BackBlock, 0F, 0F, 0F);
      Point = new ModelRenderer(this, 0, 12);
      Point.addBox(0F, -1F, -1F, 2, 2, 2);
      Point.setRotationPoint(9F, 16F, 0F);
      Point.setTextureSize(64, 32);
      Point.mirror = true;
      setRotation(Point, 0F, 0F, 0F);
      OrbA1 = new ModelRenderer(this, 0, 0);
      OrbA1.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbA1.setRotationPoint(-6F, 11F, 0F);
      OrbA1.setTextureSize(64, 32);
      OrbA1.mirror = true;
      setRotation(OrbA1, 0F, 0F, 0F);
      OrbA2 = new ModelRenderer(this, 0, 0);
      OrbA2.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbA2.setRotationPoint(-6F, 16F, -5F);
      OrbA2.setTextureSize(64, 32);
      OrbA2.mirror = true;
      setRotation(OrbA2, 0F, 0F, 0F);
      OrbA3 = new ModelRenderer(this, 0, 0);
      OrbA3.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbA3.setRotationPoint(-6F, 16F, 5F);
      OrbA3.setTextureSize(64, 32);
      OrbA3.mirror = true;
      setRotation(OrbA3, 0F, 0F, 0F);
      OrbA4 = new ModelRenderer(this, 0, 0);
      OrbA4.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbA4.setRotationPoint(-6F, 21F, 0F);
      OrbA4.setTextureSize(64, 32);
      OrbA4.mirror = true;
      setRotation(OrbA4, 0F, 0F, 0F);
      OrbB1 = new ModelRenderer(this, 0, 2);
      OrbB1.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbB1.setRotationPoint(-4F, 20F, 4F);
      OrbB1.setTextureSize(64, 32);
      OrbB1.mirror = true;
      setRotation(OrbB1, 0F, 0F, 0F);
      OrbB2 = new ModelRenderer(this, 0, 2);
      OrbB2.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbB2.setRotationPoint(-4F, 12F, 4F);
      OrbB2.setTextureSize(64, 32);
      OrbB2.mirror = true;
      setRotation(OrbB2, 0F, 0F, 0F);
      OrbB3 = new ModelRenderer(this, 0, 2);
      OrbB3.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbB3.setRotationPoint(-4F, 12F, -4F);
      OrbB3.setTextureSize(64, 32);
      OrbB3.mirror = true;
      setRotation(OrbB3, 0F, 0F, 0F);
      OrbB4 = new ModelRenderer(this, 0, 2);
      OrbB4.addBox(0F, -0.5F, -0.5F, 1, 1, 1);
      OrbB4.setRotationPoint(-4F, 20F, -4F);
      OrbB4.setTextureSize(64, 32);
      OrbB4.mirror = true;
      setRotation(OrbB4, 0F, 0F, 0F);
  }
  
  public void render() {
    float f5 = 1/16F;
    Center.render(f5);
    FrontBlock.render(f5);
    BackBlock.render(f5);
    Point.render(f5);
  }
  
  public void renderOrbA() {
	    float f5 = 1/16F;
	    OrbA1.render(f5);
	    OrbA2.render(f5);
	    OrbA3.render(f5);
	    OrbA4.render(f5);	  
  }
  
  public void renderOrbB() {
	    float f5 = 1/16F;
	    OrbB1.render(f5);
	    OrbB2.render(f5);
	    OrbB3.render(f5);
	    OrbB4.render(f5);	  
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }


}
