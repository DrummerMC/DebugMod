package drummermc.debug;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;

public class CmdCheckTileEntityUpdateTimeConsumption implements ICommand
{
	private static final String CMD = "te timecs";
	private static final String S_ARGS = "<int:countRuns>";
	
	private ArrayList<TileEntity> tileEntitys;
	
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
	public void processCommand(ICommandSender iCommandSender, String[] args)
	{
		int cntRuns = 0;
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
		
		this.tileEntitys = (ArrayList<TileEntity>) iCommandSender.getEntityWorld().loadedTileEntityList;
		
		iCommandSender.addChatMessage(new ChatComponentTranslation("Loaded TileEnties: " + iCommandSender.getEntityWorld().loadedTileEntityList.size()));
		iCommandSender.addChatMessage(new ChatComponentTranslation("Disable all TileEnties..."));
		
		for(Object objTileEntityOriginal : iCommandSender.getEntityWorld().loadedTileEntityList)
		{
			TileEntity tileEntityOriginal = (TileEntity) objTileEntityOriginal;
			tileEntityOriginal.invalidate();
		}
		iCommandSender.getEntityWorld().loadedTileEntityList = null;
		
		for(int i = 5; i > 0; i--)
		{
			iCommandSender.addChatMessage(new ChatComponentTranslation("Wait... " + i));
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
		}
		
		if (iCommandSender.getEntityWorld().loadedTileEntityList == null)
		{
			iCommandSender.addChatMessage(new ChatComponentTranslation("Deleted all TileEntity updates from world! Start debugging... please wait..."));
		}
		else
		{
			iCommandSender.addChatMessage(new ChatComponentTranslation("Loaded TileEnties: " + iCommandSender.getEntityWorld().loadedTileEntityList.size() + " ... some mod reactivate tileenties... stop command execution"));
			return;
		}
		
		
		HashMap<String, Integer> unsortedTileEntiyTimes = new HashMap(this.tileEntitys.size());
		TreeMap<String, Integer> sortedTileEntityTimes = new TreeMap(new IntegerComparator(unsortedTileEntiyTimes)); 


		long start = 0;
		long end = 0;
		for(int run = 0; run < cntRuns; run++)
		{
			for(TileEntity tileEntity : this.tileEntitys)
			{
				if (tileEntity == null) continue;
				start = System.nanoTime();
				tileEntity.updateEntity();
				end = System.nanoTime();
				if (run == 0)
				{
					unsortedTileEntiyTimes.put(this.getTileEntityString(tileEntity), (int) (end-start));
				}
				else
				{
					unsortedTileEntiyTimes.get(this.getTileEntityString(tileEntity)).compareTo((int)(end-start));
				}
			}
		}
		
		
		iCommandSender.addChatMessage(new ChatComponentTranslation("Processing result... please wait..."));
		
		sortedTileEntityTimes.putAll(unsortedTileEntiyTimes);
		
		int cnt0 = 0;
		
		
		for(Map.Entry<String,Integer> entry : sortedTileEntityTimes.entrySet()) 
		{
			Integer value = entry.getValue();
			if (value == 0)
			{
				cnt0++;
				continue;
			}
			
			String key = entry.getKey();
			iCommandSender.addChatMessage(new ChatComponentTranslation(key + " = " + value + "ns"));
		}
		iCommandSender.addChatMessage(new ChatComponentTranslation("There are " + cnt0 + " TileEntitys with 0ns"));		
				
		iCommandSender.getEntityWorld().loadedTileEntityList = this.tileEntitys;
		for(Object objTileEntityOriginal : iCommandSender.getEntityWorld().loadedTileEntityList)
		{
			TileEntity tileEntityOriginal = (TileEntity) objTileEntityOriginal;
			tileEntityOriginal.validate();
		}
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
	
	private String getTileEntityString(TileEntity tileEntity)
	{
		return tileEntity.getClass().getName() + " at (X, Z, Y): " + tileEntity.xCoord + " " + tileEntity.zCoord + " " + tileEntity.yCoord;
	}
	
	private class IntegerComparator implements Comparator<String> 
	{
	    Map<String, Integer> map;
	    public IntegerComparator(Map<String, Integer> map) 
	    {
	        this.map = map;
	    }
    
	    public int compare(String a, String b) 
	    {
	    	return map.get(a) <= map.get(b) ? -1 : 1;
	    }
	}
	
}