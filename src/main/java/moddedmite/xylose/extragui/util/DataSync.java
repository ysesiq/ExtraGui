package moddedmite.xylose.extragui.util;

import moddedmite.rustedironcore.api.util.LogUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.IntegratedServer;
import net.minecraft.Minecraft;
import net.minecraft.TileEntity;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class DataSync {
    private static final Logger LOGGER = LogUtil.getLogger();

    @Environment(EnvType.CLIENT)
    @Nullable
    public static TileEntity getServerSide(TileEntity tileEntity) {
        IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
        if (server == null) return null;
        try {// not sure if this try can be removed
            return server.worldServerForDimension(tileEntity.getWorldObj().getDimensionId())
                    .getBlockTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
        } catch (Exception e) {
            LOGGER.warn(e);
            return null;
        }
    }
}
