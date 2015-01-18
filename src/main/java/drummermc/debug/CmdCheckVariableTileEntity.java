package drummermc.debug;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CmdCheckVariableTileEntity implements ICommand {

	private static final String CMD = "test";
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
		for(World world : DimensionManager.getWorlds()){
			for(Object obj : world.loadedTileEntityList){
				if(obj instanceof TileEntity){
					int counter = 0;
					TileEntity tile = (TileEntity) obj;
					Class clazz = tile.getClass();
					Field[] fields = clazz.getDeclaredFields();
					for(Field field : fields){
						boolean b1 = field.isAccessible();
						field.setAccessible(true);
						try{
							Object o = field.get(tile);
							System.out.println(field.getName());
							if(o != null){
								Class clazz2 = o.getClass();
								try{
									Method f = clazz2.getDeclaredMethod("iterator");
									f.setAccessible(true);
									Iterator itr = (Iterator) f.invoke(o);
									counter = counter + getAmountOfFields(itr);
								}catch(Throwable e){
									
									counter = counter + getAmount(o);
								}
							}
						}catch(Throwable e){
							
						}
						field.setAccessible(b1);
					}
					System.out.println("World: " + world.provider.dimensionId + " Tile X:" + tile.xCoord + " Tile Y:" + tile.yCoord + " Tile Z:" + tile.zCoord + " Class: " + tile.getClass().getName() + " Amount: " + counter);
				}
			}
		}
	}
	
	public int getAmountOfFields(Iterator itr){
		int i = 0;
		System.out.println();
		try{
			while(itr.hasNext()) {
	         Object element = itr.next();
	         i++;
		}
		}catch(Throwable e){
		}
		return i;
		
	}
	
	public int getAmount(Object obj){
		int i = 0;
		try{
			for(Object o : (Object[])obj){
			i++;
		}
		}catch(Throwable e){
		}
		return i == 0 ? 1 : i;
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
