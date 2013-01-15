package techguy.mods.undead.common;

import techguy.mods.undead.client.ModUndeadColorizerGraveFoliage;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class ModUndeadBiomeGenGrave extends BiomeGenBase
{

	protected ModUndeadBiomeGenGrave(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		topBlock = (byte)ModUndeadMainRegistry.dimGrass.blockID;
		fillerBlock = (byte)ModUndeadMainRegistry.dimDirt.blockID;
		minHeight = 0.1F;
		maxHeight = 0.3F;
		theBiomeDecorator.treesPerChunk = -999;
		theBiomeDecorator.deadBushPerChunk = 10;
		theBiomeDecorator.flowersPerChunk = -999;
		theBiomeDecorator.grassPerChunk = 4;
		theBiomeDecorator.mushroomsPerChunk = 10;
		theBiomeDecorator.waterlilyPerChunk = -999;
		theBiomeDecorator.reedsPerChunk = -999;
		waterColorMultiplier = 0xbe0000;
		theBiomeDecorator = new ModUndeadGraveBiomeDecorator(this);

		spawnableMonsterList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityInfectedZombie.class, 4, 6, 8));
		spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 4, 6, 8));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityCoolZombie.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityGhoul.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityBrute.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityBuccaneer.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityFeralZombie.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityKnight.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityFrostbite.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityMudman.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityMummy.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityNecromancer.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityRotter.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityThinker.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityVent.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityZombrine.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityMaggot.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityWidower.class, 2, 2, 4));
		spawnableMonsterList.add(new SpawnListEntry(ModUndeadEntityEndermark.class, 2, 2, 4));


	}


	public int getBiomeGrassColor()
	{
		double d = (int)((1.0D + 1.0D) / 255D);
		double d1 = (int)((1.0D + 1.0D) / 255D);
		d1 /= d;
		return (ColorizerGrass.getGrassColor(d, d1) & 0x222222);
	}

	public int getBiomeFoliageColor()
	{
		double d = getFloatTemperature();
		double d1 = getFloatRainfall();
		return ((ModUndeadColorizerGraveFoliage.getFoliageColor(d, d1) + 0x222222));
	}

	/**
	 * Return true if the biome supports lightning bolt spawn, either by have the bolts enabled and have rain enabled.
	 */
	public boolean canSpawnLightningBolt()
	{
		return true;
	}

}
