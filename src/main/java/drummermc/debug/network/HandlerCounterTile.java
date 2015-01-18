package drummermc.debug.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandlerCounterTile implements IMessageHandler<PacketCounterTile, IMessage> {

    @Override
    public IMessage onMessage(PacketCounterTile message, MessageContext ctx) {
        message.execute();
        return null;
    }
}
