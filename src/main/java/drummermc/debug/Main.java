package drummermc.debug;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import drummermc.debug.network.ChannelHandler;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "debugmod";
    public static final String VERSION = "1.0";
    
    ChannelHandler handler = new ChannelHandler();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		handler.registerMessages();
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
      event.registerServerCommand(new CmdCheckTileEntityUpdateTimeConsumption());
      event.registerServerCommand(new CmdCheckVariableTileEntity());
    }
}
