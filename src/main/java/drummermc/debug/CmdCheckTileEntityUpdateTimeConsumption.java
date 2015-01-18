package drummermc.debug;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import drummermc.debug.jgui._components.STable;
import drummermc.debug.jgui.debug.FrameDebug;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CmdCheckTileEntityUpdateTimeConsumption implements ICommand
{
	private static final String CMD = "tetimecs";
	private static final String S_ARGS = "<int:countRuns>";

	@Override
	public String getCommandName()
	{
		return CMD;
	}

	@Override
	public String getCommandUsage(ICommandSender iCommandSender) 
	{
		return CMD + " " + S_ARGS;
	}

	@Override
	public void processCommand(final ICommandSender iCommandSender, String[] args) 
	{
		final int cntRuns; 
		try
		{
			if (args == null || args[0] == null || (cntRuns = Integer.parseInt(args[0])) == 0)
			{
				iCommandSender.addChatMessage(new ChatComponentTranslation(">> Incorrect parameters: " + getCommandUsage(iCommandSender)));
				return;
			}
		}
		catch(NumberFormatException ex)
		{
			iCommandSender.addChatMessage(new ChatComponentTranslation(">> Incorrect parameters: " + getCommandUsage(iCommandSender)));
			return;
		}
		

		ArrayList<Object[]> aResult = new ArrayList();

		for(World world : DimensionManager.getWorlds())
		{					
			HashMap<TileEntity, Integer> resultHash = new HashMap(world.loadedTileEntityList.size());
			long start = 0;
			long end = 0;
			for(int run = 0; run < cntRuns; run++)
			{
				for(Object objTileEntity : world.loadedTileEntityList)
				{
					if (objTileEntity == null) continue;
					if (!(objTileEntity instanceof TileEntity)) continue;
					TileEntity tileEntity = (TileEntity)objTileEntity;
					
					start = System.nanoTime();
					tileEntity.updateEntity();
					end = System.nanoTime();
					
					if (run == 0)
					{
						resultHash.put(tileEntity, (int) (end-start));
					}
					else
					{
						resultHash.get(tileEntity).compareTo((int)(end-start));
					}
				}
			}
			
			
			Iterator it = resultHash.entrySet().iterator();
		    while (it.hasNext()) 
		    {
		    	Map.Entry<TileEntity, Integer> entry = (Map.Entry<TileEntity, Integer>)it.next();
		    	
		        TileEntity tileEntity = entry.getKey();		
		        Object[] oResult = new Object[4];
		        oResult[0] = world.provider.dimensionId;
		        oResult[1] = tileEntity.getBlockType().getUnlocalizedName().substring(5);
		        oResult[2] = tileEntity.xCoord + " " + tileEntity.zCoord + " " + tileEntity.yCoord;
		        oResult[3] = entry.getValue();	
		        aResult.add(oResult);
		        it.remove();
		    }	
		    
		}	
		
		Object[][] tData = new Object[aResult.size()][4];
		int i = 0;
		for(Object[] oResult : aResult)
		{
			tData[i][0] = oResult[0];
			tData[i][1] = oResult[1];
			tData[i][2] = oResult[2];
			tData[i][3] = oResult[3];					
			i++;
		}
		
		new FrameDebug(new STable(tData, new Object[]{"Dimension", "Name", "Coords (X,Z,Y)", "TimeConsumpion (Nanoseconds)"})
		{
			@Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
		});

	}
	
	@Override
	public List getCommandAliases() 
	{
		return null;
	}
	
	@Override
	public int compareTo(Object arg0) 
	{
		return 0;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender iCommandSender)
	{
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender iCommandSender, String[] args) 
	{
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int unknown) 
	{
		return false;
	}
}