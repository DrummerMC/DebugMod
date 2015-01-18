package drummermc.debug.network;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import java.util.EnumMap;

import drummermc.debug.Main;

public class ChannelHandler {

    private static EnumMap<Side, FMLEmbeddedChannel> channels;
    public static SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);

    public static void registerMessages() {
        
    	wrapper.registerMessage(HandlerCounterTile.class, PacketCounterTile.class, 0, Side.CLIENT);
        wrapper.registerMessage(HandlerCounterTile.class, PacketCounterTile.class, 0, Side.SERVER);

    }

    public static void sendPacketToServer(AbstractPacket packet) {
        wrapper.sendToServer(packet);
    }

    public static void sendPacketToPlayer(AbstractPacket packet, EntityPlayer player) {
        wrapper.sendTo(packet, (EntityPlayerMP) player);
    }

    public static void sendPacketToAllPlayers(AbstractPacket packet) {
        wrapper.sendToAll(packet);
    }
    
    public static void sendPacketToPlayersAround(AbstractPacket abstractPacket, NetworkRegistry.TargetPoint point){
    	wrapper.sendToAllAround(abstractPacket, point);
    }

    public static void sendPacketToAllPlayers(Packet packet, World world) {
        for (Object player : world.playerEntities) {
            if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(packet);
            }
        }
    }
    
}
