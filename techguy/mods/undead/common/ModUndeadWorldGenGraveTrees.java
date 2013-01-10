package techguy.mods.undead.common;

import java.util.Random;


import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ModUndeadWorldGenGraveTrees extends WorldGenerator
{
	public ModUndeadWorldGenGraveTrees()
	{
	}


	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		int trees = rand.nextInt(6);
		if(world.getBlockId(i, j - 1, k) == ModUndeadMainRegistry.dimGrass.blockID || world.getBlockId(i, j - 1, k) == ModUndeadMainRegistry.dimDirt.blockID && world.getBlockId(i, j, k) == 0)
		{
			switch(trees)
			{


			case 0:
				for(int x = 0; x < 1; x++)//Generation of the X-Axis, one blocks long
				{ 
					for(int y = 0; y < 8; y++)//Generation of the Z-Axis, 8 blocks high
					{ 
						world.setBlockWithNotify(i + x, j + y, k, dread); //Creates wood at that point
					}
				}
				world.setBlockWithNotify(i - 1, j + 5, k, dread);
				world.setBlockWithNotify(i + 1, j + 6, k, dread);

				break;

			case 1:
				for(int x = 0; x < 1; x++)
				{ 
					for(int y = 0; y < 6; y++)
					{ 
						world.setBlockWithNotify(i + x, j + y, k, dread);
					}
				}
				world.setBlockWithNotify(i, j + 4, k + 1, dread);

				break;

			case 2:
				for(int x = 0; x < 1; x++)
				{ 
			 		for(int y = 0; y < 8; y++)
					{ 
						world.setBlockWithNotify(i + x, j + y, k, dread);
					}
				}
				world.setBlockWithNotify(i, j + 1, k - 1, dread);
				world.setBlockWithNotify(i, j + 4, k - 1, dread);
				world.setBlockWithNotify(i - 1, j + 3, k, dread);
				world.setBlockWithNotify(i - 1, j + 5, k + 1, dread);
				world.setBlockWithNotify(i + 1, j + 2, k + 1, dread);
								
				break;
				
			case 3:
				for(int x = 0; x < 1; x++)
				{ 
					for(int y = 0; y < 6; y++)
					{ 
						world.setBlockWithNotify(i + x, j + y, k, dread);
					}
				}
				world.setBlockWithNotify(i + 1, j + 3, k, dread);
				world.setBlockWithNotify(i - 1, j + 3, k, dread);
				world.setBlockWithNotify(i, j + 3, k + 1, dread);
				world.setBlockWithNotify(i, j + 3, k - 1, dread);
				break;
				
			case 4:
				for(int x = 0; x < 1; x++)
				{ 
					for(int y = 0; y < 11; y++)
					{ 
						world.setBlockWithNotify(i + x, j + y, k, dread);
					}
				}
				world.setBlockWithNotify(i, j + 5, k - 1, dread);
				world.setBlockWithNotify(i + 1, j + 5, k - 2, dread);
				world.setBlockWithNotify(i + 1, j + 6, k, dread);
				world.setBlockWithNotify(i, j + 8, k + 1, dread);
				world.setBlockWithNotify(i + 1, j + 8, k + 2, dread);
				break;
				
			case 5:
				for(int x = 0; x < 1; x++)
				{ 
					for(int y = 0; y < 8; y++)
					{ 
						world.setBlockWithNotify(i + x, j + y, k, dread);
					}
				}
				world.setBlockWithNotify(i + 1, j + 5, k, dread);
				world.setBlockWithNotify(i - 1, j + 5, k, dread);
				world.setBlockWithNotify(i, j + 3, k + 1, dread);
				world.setBlockWithNotify(i, j + 3, k - 1, dread);
				break;
			}
			return true;
		}


		return false;
	}

	int dread = ModUndeadMainRegistry.dreadWood.blockID;
	int vine = Block.vine.blockID;

}