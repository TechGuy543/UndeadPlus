package techguy.mods.undead.common;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.Teleporter;

public class ModUndeadPortalPosition extends ChunkCoordinates
{
    public long field_85087_d;

    final Teleporter field_85088_e;

    public ModUndeadPortalPosition(Teleporter par1Teleporter, int par2, int par3, int par4, long par5)
    {
        super(par2, par3, par4);
        this.field_85088_e = par1Teleporter;
        this.field_85087_d = par5;
    }
}
