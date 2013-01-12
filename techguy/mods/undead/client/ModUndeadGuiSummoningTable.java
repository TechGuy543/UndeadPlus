package techguy.mods.undead.client;

import techguy.mods.undead.common.ModUndeadEntityHellHound;
import techguy.mods.undead.common.ModUndeadEntityTamedZombie;
import techguy.mods.undead.common.ModUndeadGraveDimensionTeleporter;
import techguy.mods.undead.common.ModUndeadMainRegistry;
import techguy.mods.undead.common.ModUndeadTileEntitySummoningTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ModUndeadGuiSummoningTable extends GuiScreen
{
	ModUndeadTileEntitySummoningTable tileentitysummoningtable = new ModUndeadTileEntitySummoningTable();
	//static int tileentitysummoningtable.usesLeft = tileentitysummoningtable.tileentitysummoningtable.usesLeft;

	private Minecraft mc;
	private World world;
	private EntityPlayer entityPlayer;
	int necronomicon = ModUndeadMainRegistry.necronomicon.shiftedIndex;
	int immortiumShard = ModUndeadMainRegistry.immortiumShard.shiftedIndex;


	public ModUndeadGuiSummoningTable(World world1, EntityPlayer entityPlayer1, Minecraft minecraft)
	{
		world = world1;
		entityPlayer = entityPlayer1;
		mc = minecraft;
	}

	public GuiButton zombie;
	public GuiButton hound;
	public GuiButton widower;

	public void initGui()
	{
		controlList.clear();

		zombie = new GuiButton(0, width / 2 - 60, height / 2 - 50, 120, 20, "Zombie");
		controlList.add(zombie);
		hound = new GuiButton(1, width / 2 - 60, height / 2 - 25, 120, 20, "Hell Hound");
		controlList.add(hound);
		widower = new GuiButton(2, width / 2 - 60, height / 2, 120, 20, "Widower");
		controlList.add(widower);
		controlList.add(new GuiButton(3, width / 2 - 114, height / 2 + 40, 15, 20, "$"));
	}

	public void updateScreen()
	{
		if(tileentitysummoningtable.usesLeft == 0)
		{
			zombie.enabled = false;
			hound.enabled = false;
			widower.enabled = false;
		}

		if(tileentitysummoningtable.usesLeft < 1)
		{
			zombie.enabled = false;
		}
		if(tileentitysummoningtable.usesLeft < 2)
		{
			hound.enabled = false;
		}
		
		if(tileentitysummoningtable.usesLeft < 3)
		{
			widower.enabled = false;
		}
		else
		{
			zombie.enabled = true;
			hound.enabled = true;
			widower.enabled = true;
		}		
	}

	protected void actionPerformed(GuiButton guibutton)
	{
		if (guibutton.id == 0)
		{
			if(tileentitysummoningtable.usesLeft > 0)
			{
				tileentitysummoningtable.usesLeft--;
				ModUndeadEntityTamedZombie entitytamedzombie = (ModUndeadEntityTamedZombie)EntityList.createEntityByName("UDTamedZombie", world);

				double d3 = (double)entityPlayer.posX + (world.rand.nextDouble() - world.rand.nextDouble());
				double d4 = (entityPlayer.posY + world.rand.nextInt(3)) - 1;
				double d5 = (double)entityPlayer.posZ + (world.rand.nextDouble() - world.rand.nextDouble());

				entitytamedzombie.setLocationAndAngles(d3, d4, d5, world.rand.nextFloat() * 360F, 0.0F);
				world.spawnEntityInWorld(entitytamedzombie);
				entitytamedzombie.setTamed(true);
				entitytamedzombie.setPathToEntity(null);
				entitytamedzombie.setAttackTarget(null);
				entitytamedzombie.setEntityHealth(20);
				entitytamedzombie.setOwner(entityPlayer.username);
				//entitytamedzombie.playTameEffect(true);
				world.setEntityState(entitytamedzombie, (byte)7);

				world.playSoundAtEntity(entityPlayer, "undeadPlusAudio.misc.summon", 1.0F, 1.0F * 1.0F + 1.0F);
			}

			
		}


		if(guibutton.id == 1)
		{
			if(tileentitysummoningtable.usesLeft > 0)
			{
				tileentitysummoningtable.usesLeft--;
				tileentitysummoningtable.usesLeft--;

				ModUndeadEntityHellHound entityhellhound = (ModUndeadEntityHellHound)EntityList.createEntityByName("UDHellHound", world);

				double d3 = (double)entityPlayer.posX + (world.rand.nextDouble() - world.rand.nextDouble());
				double d4 = (entityPlayer.posY + world.rand.nextInt(3)) - 1;
				double d5 = (double)entityPlayer.posZ + (world.rand.nextDouble() - world.rand.nextDouble());

				entityhellhound.setLocationAndAngles(d3, d4, d5, world.rand.nextFloat() * 360F, 0.0F);
				entityhellhound.setTamed(true);
				entityhellhound.setPathToEntity(null);
				entityhellhound.setAttackTarget(null);
				//entityhellhound.aiSit.setSitting(true);
				entityhellhound.setEntityHealth(20);
				entityhellhound.setOwner(entityPlayer.username);
				//entityhellhound.playTameEffect(true);
				world.setEntityState(entityhellhound, (byte)7);
				world.spawnEntityInWorld(entityhellhound);

				world.playSoundAtEntity(entityPlayer, "undeadPlusAudio.misc.summon", 1.0F, 1.0F * 1.0F + 1.0F);

			}
		}

		if(guibutton.id == 2)
		{
			if(tileentitysummoningtable.usesLeft > 0)
			{
				//ModUndeadEntityTamedWidower entityTamedWidower = (ModUndeadEntityTamedWidower)EntityList.createEntityByName("UDTamedWidower", world);

				double d3 = (double)entityPlayer.posX + (world.rand.nextDouble() - world.rand.nextDouble());
				double d4 = (entityPlayer.posY);
				double d5 = (double)entityPlayer.posZ + (world.rand.nextDouble() - world.rand.nextDouble());
				//entityTamedWidower.setLocationAndAngles(d3, d4, d5, world.rand.nextFloat() * 360F, 0.0F);

				tileentitysummoningtable.usesLeft--;
				tileentitysummoningtable.usesLeft--;
				tileentitysummoningtable.usesLeft--;

				world.playSoundAtEntity(entityPlayer, "undeadPlusAudio.misc.summon", 1.0F, 1.0F * 1.0F + 1.0F);
			}
		}

		//CASHING UP
		if(guibutton.id == 3)
		{
			if(tileentitysummoningtable.usesLeft < 10)
			{
				if(entityPlayer.inventory.hasItem(necronomicon))
				{
					tileentitysummoningtable.usesLeft = tileentitysummoningtable.usesLeft + 5;
					entityPlayer.inventory.consumeInventoryItem(necronomicon);
				}
			}

			if(tileentitysummoningtable.usesLeft < 10)
			{
				if(entityPlayer.inventory.hasItem(immortiumShard))
				{
					tileentitysummoningtable.usesLeft = tileentitysummoningtable.usesLeft + 1;
					entityPlayer.inventory.consumeInventoryItem(immortiumShard);
				}
			}
		}


	}


	public boolean doesGuiPauseGame()
	{
		return false;
	}

	public void drawScreen(int i, int j, float f) 
	{

		mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtablegui.png"));
		drawTexturedModalRect(width / 2 - 128, height / 2 - 88, 0, 0, 256, 177);
		drawCenteredString(fontRenderer, "Summoning Table", width / 2, height / 2 - 75, 0xffffff);
		drawCenteredString(fontRenderer, "Uses Remaining: " + tileentitysummoningtable.usesLeft, width / 2 - 5, height / 2 + 75, 0xffffff);

		if(tileentitysummoningtable.usesLeft >= 10)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 74, 0, 0, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 9)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 64, 0, 10, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 8)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 54, 0, 20, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 7)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 44, 0, 30, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 6)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 34, 0, 40, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 5)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 24, 0, 50, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 4)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 14, 0, 60, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 3)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 - 04, 0, 70, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 2)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 + 6, 0, 80, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 1)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 + 16, 0, 90, 18, 99);
		}

		if(tileentitysummoningtable.usesLeft == 0)
		{
			mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/undeadPlus/gui/summoningtableguibar.png"));
			drawTexturedModalRect(width / 2 - 116, height / 2 + 26, 0, 99, 18, 99);
		}

		for( int l = 0; l < controlList.size(); l++)
		{
			GuiButton guibutton = (GuiButton)controlList.get(l);
			guibutton.drawButton(mc, i, j);
		}
	}
}
