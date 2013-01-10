package techguy.mods.undead.common;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ModUndeadGraveBiomeDecorator extends BiomeDecorator
{
	protected WorldGenerator treeGen;
	protected WorldGenerator castleGen;
	public WorldGenerator immortiumGen;
	public WorldGenerator volatiteGen;
	public WorldGenerator inferniteGen;
	public WorldGenerator charcoalGen;
	public WorldGenerator endoreGen;
	public WorldGenerator cyriteGen;


	public ModUndeadGraveBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		super(par1BiomeGenBase);
		this.treeGen = new ModUndeadWorldGenGraveTrees();
		//this.castleGen = new ModUndeadWorldGenCastle();
		
		this.immortiumGen = new WorldGenMinable(ModUndeadMainRegistry.immortiumOre.blockID, 8); 
		this.volatiteGen = new WorldGenMinable(ModUndeadMainRegistry.volatiteOre.blockID, 8); 
		this.inferniteGen = new WorldGenMinable(ModUndeadMainRegistry.inferniteOre.blockID, 8); 
		this.charcoalGen = new WorldGenMinable(ModUndeadMainRegistry.charcoalOre.blockID, 8); 
		this.endoreGen = new WorldGenMinable(ModUndeadMainRegistry.endoreOre.blockID, 8); 
		this.cyriteGen = new WorldGenMinable(ModUndeadMainRegistry.cyriteOre.blockID, 8); 

	}

	
	protected void generateOres()
	{
		genStandardOre1(65, this.charcoalGen, 0, 64);
		genStandardOre1(50, this.immortiumGen, 0, 64);
		genStandardOre1(20, this.volatiteGen, 0, 32);
		genStandardOre1(12, this.inferniteGen, 0, 16);
		genStandardOre1(10, this.cyriteGen, 0, 16);
		genStandardOre1(8, this.endoreGen, 0, 16);
	}
	
	@Override
	protected void decorate()
	{
		generateOres();

		if (this.randomGenerator.nextInt(5) == 0)
		{
			int var1 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			int var2 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			int var3 = this.currentWorld.getTopSolidOrLiquidBlock(var1, var2);

			if (var3 > 0)
			{
				;
			}

			this.treeGen.generate(this.currentWorld, this.randomGenerator, var1, var3, var2);
		}
	}
	

}
