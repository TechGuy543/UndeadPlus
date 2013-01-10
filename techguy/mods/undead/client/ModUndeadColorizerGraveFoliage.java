package techguy.mods.undead.client;

public class ModUndeadColorizerGraveFoliage
{
    private static int foliageBuffer[] = new int[0x10000];

    public ModUndeadColorizerGraveFoliage()
    {
    }

    public static void getFoilageBiomeColorizer(int par0ArrayOfInteger[])
    {
        foliageBuffer = par0ArrayOfInteger;
    }

    /**
     * Gets foliage color from temperature and humidity. Args: temperature, humidity
     */
    public static int getFoliageColor(double par0, double par2)
    {
        par2 *= par0;
        int i = (int)((1.0D - par0) * 255D);
        int j = (int)((1.0D - par2) * 255D);
        return foliageBuffer[j << 8 | i];
    }
    
    public static int getFoliageColorBasic()
    {
        return 0x222222;
    }
    
    public static int getFoliageColorBirch()
    {
        return 0x222222;
    }

    public static int getFoliageColorPine()
    {
        return 0x222222;
    }
}
