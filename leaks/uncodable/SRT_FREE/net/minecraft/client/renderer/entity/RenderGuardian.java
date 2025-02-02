package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class RenderGuardian extends RenderLiving<EntityGuardian> {
   private static final ResourceLocation GUARDIAN_TEXTURE = new ResourceLocation("textures/entity/guardian.png");
   private static final ResourceLocation GUARDIAN_ELDER_TEXTURE = new ResourceLocation("textures/entity/guardian_elder.png");
   private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");
   int field_177115_a = ((ModelGuardian)this.mainModel).func_178706_a();

   public RenderGuardian(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelGuardian(), 0.5F);
   }

   public boolean shouldRender(EntityGuardian livingEntity, ICamera camera, double camX, double camY, double camZ) {
      if (super.shouldRender(livingEntity, camera, camX, camY, camZ)) {
         return true;
      } else {
         if (livingEntity.hasTargetedEntity()) {
            EntityLivingBase entitylivingbase = livingEntity.getTargetedEntity();
            if (entitylivingbase != null) {
               Vec3 vec3 = this.func_177110_a(entitylivingbase, (double)entitylivingbase.height * 0.5, 1.0F);
               Vec3 vec31 = this.func_177110_a(livingEntity, (double)livingEntity.getEyeHeight(), 1.0F);
               return camera.isBoundingBoxInFrustum(AxisAlignedBB.fromBounds(vec31.xCoord, vec31.yCoord, vec31.zCoord, vec3.xCoord, vec3.yCoord, vec3.zCoord));
            }
         }

         return false;
      }
   }

   private Vec3 func_177110_a(EntityLivingBase entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
      double d0 = entityLivingBaseIn.lastTickPosX + (entityLivingBaseIn.posX - entityLivingBaseIn.lastTickPosX) * (double)p_177110_4_;
      double d1 = p_177110_2_ + entityLivingBaseIn.lastTickPosY + (entityLivingBaseIn.posY - entityLivingBaseIn.lastTickPosY) * (double)p_177110_4_;
      double d2 = entityLivingBaseIn.lastTickPosZ + (entityLivingBaseIn.posZ - entityLivingBaseIn.lastTickPosZ) * (double)p_177110_4_;
      return new Vec3(d0, d1, d2);
   }

   public void doRender(EntityGuardian entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (this.field_177115_a != ((ModelGuardian)this.mainModel).func_178706_a()) {
         this.mainModel = new ModelGuardian();
         this.field_177115_a = ((ModelGuardian)this.mainModel).func_178706_a();
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
      EntityLivingBase entitylivingbase = entity.getTargetedEntity();
      if (entitylivingbase != null) {
         float f = entity.func_175477_p(partialTicks);
         Tessellator tessellator = Tessellator.getInstance();
         WorldRenderer worldrenderer = tessellator.getWorldRenderer();
         this.bindTexture(GUARDIAN_BEAM_TEXTURE);
         GL11.glTexParameterf(3553, 10242, 10497.0F);
         GL11.glTexParameterf(3553, 10243, 10497.0F);
         GlStateManager.disableLighting();
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
         float f1 = 240.0F;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f1, f1);
         GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
         float f2 = (float)entity.worldObj.getTotalWorldTime() + partialTicks;
         float f3 = f2 * 0.5F % 1.0F;
         float f4 = entity.getEyeHeight();
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)x, (float)y + f4, (float)z);
         Vec3 vec3 = this.func_177110_a(entitylivingbase, (double)entitylivingbase.height * 0.5, partialTicks);
         Vec3 vec31 = this.func_177110_a(entity, (double)f4, partialTicks);
         Vec3 vec32 = vec3.subtract(vec31);
         double d0 = vec32.lengthVector() + 1.0;
         vec32 = vec32.normalize();
         float f5 = (float)Math.acos(vec32.yCoord);
         float f6 = (float)Math.atan2(vec32.zCoord, vec32.xCoord);
         GlStateManager.rotate(((float) (Math.PI / 2) - f6) * (180.0F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(f5 * (180.0F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
         int i = 1;
         double d1 = (double)f2 * 0.05 * (1.0 - (double)(i & 1) * 2.5);
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         float f7 = f * f;
         int j = 64 + (int)(f7 * 240.0F);
         int k = 32 + (int)(f7 * 192.0F);
         int l = 128 - (int)(f7 * 64.0F);
         double d2 = (double)i * 0.2;
         double d3 = d2 * 1.41;
         double d4 = 0.0 + Math.cos(d1 + (Math.PI * 3.0 / 4.0)) * d3;
         double d5 = 0.0 + Math.sin(d1 + (Math.PI * 3.0 / 4.0)) * d3;
         double d6 = 0.0 + Math.cos(d1 + (Math.PI / 4)) * d3;
         double d7 = 0.0 + Math.sin(d1 + (Math.PI / 4)) * d3;
         double d8 = 0.0 + Math.cos(d1 + (Math.PI * 5.0 / 4.0)) * d3;
         double d9 = 0.0 + Math.sin(d1 + (Math.PI * 5.0 / 4.0)) * d3;
         double d10 = 0.0 + Math.cos(d1 + (Math.PI * 7.0 / 4.0)) * d3;
         double d11 = 0.0 + Math.sin(d1 + (Math.PI * 7.0 / 4.0)) * d3;
         double d12 = 0.0 + Math.cos(d1 + Math.PI) * d2;
         double d13 = 0.0 + Math.sin(d1 + Math.PI) * d2;
         double d14 = 0.0 + Math.cos(d1 + 0.0) * d2;
         double d15 = 0.0 + Math.sin(d1 + 0.0) * d2;
         double d16 = 0.0 + Math.cos(d1 + (Math.PI / 2)) * d2;
         double d17 = 0.0 + Math.sin(d1 + (Math.PI / 2)) * d2;
         double d18 = 0.0 + Math.cos(d1 + (Math.PI * 3.0 / 2.0)) * d2;
         double d19 = 0.0 + Math.sin(d1 + (Math.PI * 3.0 / 2.0)) * d2;
         double d20 = 0.0;
         double d21 = 0.4999;
         double d22 = (double)(-1.0F + f3);
         double d23 = d0 * (0.5 / d2) + d22;
         worldrenderer.func_181662_b(d12, d0, d13).func_181673_a(0.4999, d23).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d12, 0.0, d13).func_181673_a(0.4999, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d14, 0.0, d15).func_181673_a(0.0, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d14, d0, d15).func_181673_a(0.0, d23).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d16, d0, d17).func_181673_a(0.4999, d23).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d16, 0.0, d17).func_181673_a(0.4999, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d18, 0.0, d19).func_181673_a(0.0, d22).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d18, d0, d19).func_181673_a(0.0, d23).func_181669_b(j, k, l, 255).func_181675_d();
         double d24 = 0.0;
         if (entity.ticksExisted % 2 == 0) {
            d24 = 0.5;
         }

         worldrenderer.func_181662_b(d4, d0, d5).func_181673_a(0.5, d24 + 0.5).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d6, d0, d7).func_181673_a(1.0, d24 + 0.5).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d10, d0, d11).func_181673_a(1.0, d24).func_181669_b(j, k, l, 255).func_181675_d();
         worldrenderer.func_181662_b(d8, d0, d9).func_181673_a(0.5, d24).func_181669_b(j, k, l, 255).func_181675_d();
         tessellator.draw();
         GlStateManager.popMatrix();
      }
   }

   protected void preRenderCallback(EntityGuardian entitylivingbaseIn, float partialTickTime) {
      if (entitylivingbaseIn.isElder()) {
         GlStateManager.scale(2.35F, 2.35F, 2.35F);
      }
   }

   protected ResourceLocation getEntityTexture(EntityGuardian entity) {
      return entity.isElder() ? GUARDIAN_ELDER_TEXTURE : GUARDIAN_TEXTURE;
   }
}
