package techguy.mods.undead.client;

import java.io.File;

import techguy.mods.undead.common.ModUndeadCommonProxy;
import techguy.mods.undead.common.ModUndeadEntityBrute;
import techguy.mods.undead.common.ModUndeadEntityBuccaneer;
import techguy.mods.undead.common.ModUndeadEntityCoolZombie;
import techguy.mods.undead.common.ModUndeadEntityCordie;
import techguy.mods.undead.common.ModUndeadEntityCrawler;
import techguy.mods.undead.common.ModUndeadEntityEndermark;
import techguy.mods.undead.common.ModUndeadEntityFeralZombie;
import techguy.mods.undead.common.ModUndeadEntityFlamingArrow;
import techguy.mods.undead.common.ModUndeadEntityFlare;
import techguy.mods.undead.common.ModUndeadEntityFrostbite;
import techguy.mods.undead.common.ModUndeadEntityHellHound;
import techguy.mods.undead.common.ModUndeadEntityImmortiumArrow;
import techguy.mods.undead.common.ModUndeadEntityInfectedZombie;
import techguy.mods.undead.common.ModUndeadEntityInferno;
import techguy.mods.undead.common.ModUndeadEntityKnight;
import techguy.mods.undead.common.ModUndeadEntityMaggot;
import techguy.mods.undead.common.ModUndeadEntityMudman;
import techguy.mods.undead.common.ModUndeadEntityMummy;
import techguy.mods.undead.common.ModUndeadEntityNecromancer;
import techguy.mods.undead.common.ModUndeadEntityPoisonousBall;
import techguy.mods.undead.common.ModUndeadEntityRotter;
import techguy.mods.undead.common.ModUndeadEntityScorcher;
import techguy.mods.undead.common.ModUndeadEntityTeleportationArrow;
import techguy.mods.undead.common.ModUndeadEntityThinker;
import techguy.mods.undead.common.ModUndeadEntityVent;
import techguy.mods.undead.common.ModUndeadEntityWidower;
import techguy.mods.undead.common.ModUndeadEntityZkuba;
import techguy.mods.undead.common.ModUndeadEntityZombrine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ModUndeadClientProxy extends ModUndeadCommonProxy
{

    public static void registerRenderInformation()
    {		

    	MinecraftForgeClient.preloadTexture("/undeadPlus/terrain.png");
    	MinecraftForgeClient.preloadTexture("/undeadPlus/items.png");
    	
        RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityInfectedZombie.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityCoolZombie.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityFeralZombie.class, new ModUndeadRenderFeralZombie(new ModUndeadModelFeralZombie(), 0.5F));
		//RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityKnight.class, new ModUndeadRenderKnight(new ModUndeadModelKnight(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityKnight.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityThinker.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityNecromancer.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityVent.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityScorcher.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityBrute.class, new ModUndeadRenderBrute(new ModUndeadModelBrute(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityHellHound.class, new ModUndeadRenderHellHound(new ModUndeadModelHellHound(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityZombrine.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityWidower.class, new ModUndeadRenderWidower());
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityMaggot.class, new ModUndeadRenderMaggot());
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityFrostbite.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityBuccaneer.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityMummy.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityMudman.class, new RenderBiped(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityCordie.class, new ModUndeadRenderCordie(new ModUndeadModelZombie(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityRotter.class, new ModUndeadRenderRotter(new ModUndeadModelRotter(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityFlare.class, new RenderBiped(new ModUndeadModelFlare(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityInferno.class, new ModUndeadRenderInferno());
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityCrawler.class, new ModUndeadRenderCrawler(new ModUndeadModelCrawler(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityEndermark.class, new ModUndeadRenderEndermark(new ModUndeadModelEndermark(), 0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityZkuba.class, new ModUndeadRenderZkuba(new ModUndeadModelZkuba(), 0.5F));

		//RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityTamedWidower.class, new ModUndeadRenderTamedWidower());
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityPoisonousBall.class, new ModUndeadRenderPoisonousBall(0.5F));
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityFlamingArrow.class, new ModUndeadRenderFlamingArrow());
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityImmortiumArrow.class, new ModUndeadRenderImmortiumArrow());
		RenderingRegistry.instance().registerEntityRenderingHandler(ModUndeadEntityTeleportationArrow.class, new ModUndeadRenderTeleportationArrow());

		
		RenderEngine renderengine = Minecraft.getMinecraft().renderEngine;
        renderengine.registerTextureFX(new ModUndeadTextureInfernoFlamesFX(1));
    }
}