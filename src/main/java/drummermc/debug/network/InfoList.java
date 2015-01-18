package drummermc.debug.network;

import net.minecraft.nbt.NBTTagCompound;

public class InfoList {

	String tileEntityName;
	int worldID;
	int count;
	
	public InfoList(String _tileEntityName, int _worldID, int _count){
		tileEntityName = _tileEntityName;
		worldID = _worldID;
		count = _count;
	}
	
	public InfoList(NBTTagCompound data, int number){
		readFromNBT(data, number);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag, int i){
		tag.setString("tile"+i, tileEntityName);
		tag.setInteger("world"+i, worldID);
		tag.setInteger("count"+i, count);
		return tag;
	}
	
	public void readFromNBT(NBTTagCompound tag, int i){
		tileEntityName = tag.getString("tile"+i);
		worldID = tag.getInteger("world"+i);
		count = tag.getInteger("count"+i);
	}
	
}
