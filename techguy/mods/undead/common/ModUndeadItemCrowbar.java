package techguy.mods.undead.common;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemTool;

public class ModUndeadItemCrowbar extends ItemTool
{
    private static Block blocksEffectiveAgainst[];

    protected ModUndeadItemCrowbar(int i, EnumToolMaterial toolCrowbar)
    {
        super(i, 3, toolCrowbar, blocksEffectiveAgainst);
    }

    static
    {
        blocksEffectiveAgainst = (new Block[]
                {
        		 Block.fenceIron, Block.chest, Block.doorWood, Block.doorSteel
                });
    }
    
    public String getTextureFile()
    {
    	return "/undeadPlus/items.png";
    }
}