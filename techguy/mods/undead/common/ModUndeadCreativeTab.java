package techguy.mods.undead.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

final class ModUndeadCreativeTab extends CreativeTabs
{
    ModUndeadCreativeTab(String par2Str)
    {
        super(par2Str);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return ModUndeadMainRegistry.necronomicon.itemID;
    }
}
