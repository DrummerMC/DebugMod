package drummermc.debug;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import drummermc.debug.network.InfoList;
import drummermc.debug.network.PacketCounterTile;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

public class CmdCheckVariableTileEntity implements ICommand {

	private static final String CMD = "tevars";
	
	private ArrayList<TileEntity> tileEntitys;
	
	private static List<InfoList> lis;
	
	@Override
	public String getCommandName()
	{
		return CMD;
	}

	@Override
	public String getCommandUsage(ICommandSender iCommandSender) 
	{
		return CMD;
	}

	@Override
	public void processCommand(ICommandSender iCommandSender, String[] args)
	{
		if(!(iCommandSender instanceof EntityPlayer)){
			return;
		}
		EntityPlayer player = (EntityPlayer) iCommandSender;
		List<InfoList> list = new ArrayList<InfoList>();
		for(World world : DimensionManager.getWorlds()){
			for(Object obj : world.loadedTileEntityList){
				if(obj instanceof TileEntity){
					int counter = 0;
					try{
						TileEntity tile = (TileEntity) obj;
						Class clazz = tile.getClass();
						Field[] fields = clazz.getDeclaredFields();
						for(Field field : fields){
							boolean b1 = field.isAccessible();
							field.setAccessible(true);
							try{
								Object o = field.get(tile);
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
											}catch(Throwable e){
						
					}
					try{
						Class clazz = obj.getClass().getSuperclass();
						while(clazz != TileEntity.class){
							Field[] fields = clazz.getDeclaredFields();
							for(Field field : fields){
								boolean b1 = field.isAccessible();
								field.setAccessible(true);
								try{
									Object o = field.get(obj);
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
							clazz = clazz.getSuperclass();
						}
					}catch(Throwable e){}
					list.add(new InfoList(obj.getClass().getName(), world.provider.dimensionId, counter));
					
				}
			}
		}
		InfoList[] l = new InfoList[list.size()];
		
		lis = list;
		
		Thread t = new Thread(new Runnable(){
			
			List<InfoList> orginal = (List<InfoList>) ((ArrayList)lis).clone();
			
			@Override
			public void run() {
				try{
					File f = new File("tevars.txt");
					if(f.exists())
						f.delete();
						f.createNewFile();
						FileWriter w = new FileWriter(f);
						ArrayList<InfoList> output = new ArrayList<InfoList>();
						for(int i = 0; i < orginal.size(); i++){
							InfoList li = orginal.get(i);
							if(output.size() == 0)
								output.add(li);
							else{
								for(int i2 = 0; i2 < output.size(); i2++){
									if(output.get(i2).count > li.count){
										output.add(i2, li);
										li = null;
										break;
									}
								}
								if(li != null)
									output.add(output.size(), li);
							}
							
							
							
							
						}
						for(int i = 0; i < output.size(); i++){
							InfoList li = output.get(i);
							w.write("Class: " + li.tileEntityName + " Amount: " + li.count + " World id: " + li.worldID + "\n");
						}
						w.close();
					}catch(Throwable e){
						
					}
			}
			
		});
		t.start();
		
		for(int i = 0; i < list.size(); i++){
			l[i] = list.get(i);
		}
		new PacketCounterTile(player, l).sendPacketToPlayer(player);
	}
	
	public int getAmountOfFields(Iterator itr){
		int i = 0;
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
