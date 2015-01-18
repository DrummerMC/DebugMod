package drummermc.debug.network;

import org.apache.commons.lang3.SerializationUtils;

import cpw.mods.fml.common.network.ByteBufUtils;
import drummermc.debug.jgui._components.STable;
import drummermc.debug.jgui.debug.FrameDebug;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import io.netty.buffer.ByteBuf;

public class PacketCounterTile extends AbstractPacket{
	
	public PacketCounterTile(){}
	
	private InfoList[] list;
	
	private byte[] b1;
	private byte[] b2;
	
	public PacketCounterTile(EntityPlayer _player, InfoList[] _list){
		super(_player);
		mode = 0;
		list = _list;
	}
	
	public PacketCounterTile(EntityPlayer _player, byte[] b1, byte[] b2){
		super(_player);
		mode = 1;
		this.b1 = b1;
		this.b2 = b2;
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
		case 1:
			NBTTagCompound tag = new NBTTagCompound();
			tag.setByteArray("b1", b1);
			tag.setByteArray("b2", b2);
			ByteBufUtils.writeTag(out, tag);
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
		case 1:
			NBTTagCompound tag = ByteBufUtils.readTag(in);
			b1 = tag.getByteArray("b1");
			b2 = tag.getByteArray("b2");
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
		case 1:
			new FrameDebug(new STable((Object[][]) SerializationUtils.deserialize(b1), (Object[])SerializationUtils.deserialize(b2))
			{
				@Override
	            public boolean isCellEditable(int row, int col)
	            {
	                return false;
	            }
			});
			break;
		}
		
	}

}
