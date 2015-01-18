package drummermc.debug.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import drummermc.debug.jgui._components.STable;
import drummermc.debug.jgui.debug.FrameDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import io.netty.buffer.ByteBuf;

public class PacketCounterTile extends AbstractPacket{
	
	public PacketCounterTile(){}
	
	private InfoList[] list;
	
	public PacketCounterTile(EntityPlayer _player, InfoList[] _list){
		super(_player);
		mode = 0;
		list = _list;
	}

	@Override
	public void writeData(ByteBuf out) {
		switch(mode){
		case 0:
			NBTTagCompound data = new NBTTagCompound();
			for(int i = 0; i < list.length; i++){
				list[i].writeToNBT(data, i);
			}
			data.setInteger("i", list.length);
			ByteBufUtils.writeTag(out, data);
			break;
		}
		
	}

	@Override
	public void readData(ByteBuf in) {
		switch(mode){
		case 0:
			NBTTagCompound data = ByteBufUtils.readTag(in);
			if(data.hasKey("i")){
				int max = data.getInteger("i");
				list = new InfoList[max];
				for(int i = 0; i < max; i++){
					list[i] = new InfoList(data, i);
				}
			}
			break;
		}
		
	}

	@Override
	public void execute() {
		switch(mode){
		case 0:
			Object[][] obj = new Object[list.length][3];
			for(int i = 0; i < list.length; i++){
				InfoList l = list[i];
				Object[] o = {l.tileEntityName, ""+l.count+"", ""+l.worldID+""};
				obj[i] = o;
			}
			Object[] o = {"Tile Entity Name", "Fields", "World ID"};
			FrameDebug frame = new FrameDebug(new STable(obj, o));
			break;
		}
		
	}

}
