package techguy.mods.undead.common;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModUndeadGraveDimensionWorldProvider extends WorldProvider
{
    /**
     * creates a new world chunk manager for WorldProvider
     */
	
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(ModUndeadMainRegistry.grave, 1.0F, 0.0F);
        //this.worldChunkMgr = new ModUndeadWorldChunkManagerGrave(ModUndeadMainRegistry.grave, 1.0F, 0.0F);
        this.isHellWorld = true;
        this.hasNoSky = true;
        this.dimensionId = 70;
    }

    protected void generateLightBrightnessTable()
    {
        float var1 = 0.2F;

        for (int var2 = 0; var2 <= 15; ++var2)
        {
            float var3 = 1.0F - (float)var2 / 15.0F;
            this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }
    }    
    
    /*
     * Creates the light to brightness table
     
    protected void generateLightBrightnessTable()
    {
        float var1 = 0.1F;

        for (int var2 = 0; var2 <= 15; ++var2)
        {
            float var3 = 1.0F - (float)var2 / 15.0F;
            this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
        }
    }*/

    /**
     * Returns the chunk provider back for the world provider
     */
    public IChunkProvider getChunkProvider()
    {
        return new ModUndeadGraveDimensionChunkProvider(this.worldObj, this.worldObj.getSeed(), true);
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

	@Override
	public String getSaveFolder() 
	{
		return "DIM_GRAVE";
	}

	@Override
	public String getWelcomeMessage() 
	{
		return "Entering the Grave...";
	}

	@Override
	public String getDepartMessage() 
	{
		return "Leaving the Grave";
	}

	@Override
	public String getDimensionName() 
	{
		return "Grave Dimension";
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }
	
	/**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
	@Override
    public boolean isSurfaceWorld()
    {
        return false;
    }


    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    public boolean doesXZShowFog(int par1, int par2)
    {
        return true;
    }

}

