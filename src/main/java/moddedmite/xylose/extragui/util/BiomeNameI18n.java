package moddedmite.xylose.extragui.util;

import moddedmite.rustedironcore.api.world.BiomeAPI;
import net.minecraft.BiomeGenBase;
import net.minecraft.I18n;

public class BiomeNameI18n {

    public static String getBiomeNameI18n(BiomeGenBase biome) {
        if (((BiomeAPI) biome).getBiomeUnlocalizedName() == null) return biome.biomeName;
        return I18n.getString(((BiomeAPI) biome).getBiomeUnlocalizedName());
    }

    public static String getBiomeTempRainInfo(BiomeGenBase biome) {
        return I18n.getStringParams(
                "extragui.biome.temp_rain",
                biome.temperature,
                biome.rainfall
        );
    }
}
