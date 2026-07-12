package moddedmite.xylose.extragui.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigTab;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.util.JsonUtils;
import moddedmite.xylose.extragui.gui.GuiEntityStats;
import moddedmite.xylose.extragui.gui.GuiMiniInfoHandle;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class ExtraGuiConfig extends SimpleConfigs {
    public static final ConfigBoolean DisplayDurability = new ConfigBoolean("extraGui.DisplayDurability", true);
    public static final ConfigBoolean DurabilityPercentageDisplay = new ConfigBoolean("extraGui.DurabilityPercentageDisplay", true);
    public static final ConfigBoolean DurabilityLine = new ConfigBoolean("extraGui.DurabilityLine", false);
    public static final ConfigInteger DurabilityX = new ConfigInteger("extraGui.DurabilityX", 90, 0, 100);
    public static final ConfigInteger DurabilityY = new ConfigInteger("extraGui.DurabilityY", 100, 0, 100);
    public static final ConfigDouble DurabilitySize = new ConfigDouble("extraGui.DurabilitySize", 1.0F, 0.1F, 2.0F);
    public static final ConfigColor DurabilityColor = new ConfigColor("extraGui.DurabilityColor", "#FFFFFFFF");
    public static final ConfigBoolean DurabilityTextRight = new ConfigBoolean("extraGui.DurabilityTextRight", true);
    
    public static final ConfigBoolean DisableDevInfo = new ConfigBoolean("extraGui.cancelDevInfo", true);
    public static final ConfigBoolean DisableErrorInfo = new ConfigBoolean("extraGui.cancelErrorInfo", true);
    public static final ConfigBoolean ShowInfo = new ConfigBoolean("extraGui.showInfo", true);
    public static final ConfigBoolean RightAlign = new ConfigBoolean("extraGui.RightAlign", false);
    public static final ConfigBoolean background = new ConfigBoolean("extraGui.background", true);
    public static final ConfigString timeZone = new ConfigString("extraGui.timeZone", getTimeZone(), "extraGui.timeZone");
    public static final ConfigInteger InfoXLevel = new ConfigInteger("extraGui.xLevel", 0, -100, 100);
    public static final ConfigInteger InfoYLevel = new ConfigInteger("extraGui.yLevel", 0, 0, 100);
    public static final ConfigDouble InfoSize = new ConfigDouble("extraGui.InfoSize", 0.5F, 0.1F, 2.0F);
    public static final ConfigColor infoColor = new ConfigColor("extraGui.infoColor", "#FFFFFFFF");

    public static final ConfigInfo FPS = new ConfigInfo("extraGui.fps", false, mc -> GuiMiniInfoHandle.getInstance().getFps());
    public static final ConfigInfo Mem = new ConfigInfo("extraGui.mem", false, mc -> GuiMiniInfoHandle.getInstance().getMem());
    public static final ConfigInfo RealTime = new ConfigInfo("extraGui.realTime", true, false, mc -> GuiMiniInfoHandle.getInstance().getRealTime());
    public static final ConfigInfo MCTime = new ConfigInfo("extraGui.time", true, mc -> GuiMiniInfoHandle.getInstance().getTime(mc.theWorld));
    public static final ConfigBoolean OverWorldTime = new ConfigBoolean("extraGui.overWorldTime", true, "extraGui.overWorldTime");
    public static final ConfigInfo Position = new ConfigInfo("extraGui.position", true, false, mc -> GuiMiniInfoHandle.getInstance().getPosition(mc.theWorld));
    public static final ConfigBoolean DimensionPosition = new ConfigBoolean("extraGui.dimensionPosition", false, "extraGui.dimensionPosition");
    public static final ConfigInfo ChunkPosition = new ConfigInfo("extraGui.chunkPosition", false, mc -> GuiMiniInfoHandle.getInstance().getChunkPosition());
    public static final ConfigInfo Direction = new ConfigInfo("extraGui.direction", true, mc -> GuiMiniInfoHandle.getInstance().getDirectionInfo(mc));
    public static final ConfigInfo YawPitchSpeed = new ConfigInfo("extraGui.yawPitchSpeed", false, false, mc -> GuiMiniInfoHandle.getInstance().getYawPitchSpeedInfo(mc));
    public static final ConfigInfo Weather = new ConfigInfo("extraGui.weather", true, mc -> GuiMiniInfoHandle.getInstance().weatherInfo(mc.theWorld));
    public static final ConfigInfo Light = new ConfigInfo("extraGui.light", false, mc -> GuiMiniInfoHandle.getInstance().getLightInfo(mc));
    public static final ConfigInfo Biome = new ConfigInfo("extraGui.biome", false, mc -> GuiMiniInfoHandle.getInstance().getBiomeFullInfo(mc.thePlayer.getBiome()));
    public static final ConfigInfo Dimension = new ConfigInfo("extraGui.dimension", false, mc -> GuiMiniInfoHandle.getInstance().getDimension(mc.theWorld));
    public static final ConfigInfo MoonPhases = new ConfigInfo("extraGui.moonPhases", true, mc -> GuiMiniInfoHandle.getInstance().getMoonPhases(mc.theWorld));
    public static final ConfigBoolean OnePercentLowFps = new ConfigBoolean("extraGui.onePercentLowFps", true);
    public static final ConfigString CustomString = new ConfigString("extraGui.customString", "");

    public static final ConfigBoolean DisplayItemRender = new ConfigBoolean("extraGui.DisplayItemRender", false);
    public static final ConfigInteger ItemRenderX = new ConfigInteger("extraGui.ItemRenderX", 5, 0, 100);
    public static final ConfigInteger ItemRenderY = new ConfigInteger("extraGui.ItemRenderY", 25, 0, 100);
    public static final ConfigDouble ItemRenderSize = new ConfigDouble("extraGui.ItemRenderSize", 7.0F, 1.0F, 8.0F);

//    public static final ConfigBoolean DisplayWorldTitle = new ConfigBoolean("extraGui.DisplayWorldTitle", true);
//    public static final ConfigInteger WorldTitleX = new ConfigInteger("extraGui.WorldTitleX", 50, 0, 100);
//    public static final ConfigInteger WorldTitleY = new ConfigInteger("extraGui.WorldTitleY", 20, 0, 100);
//    public static final ConfigDouble WorldTitleSize = new ConfigDouble("extraGui.WorldTitleSize", 4.0F, 1.0F, 8.0F);
//    public static final ConfigInteger WorldTitleAlpha = new ConfigInteger("extraGui.WorldTitleAlpha", 255, 0, 255);
//    public static final ConfigInteger WorldTitleDisplayTime = new ConfigInteger("extraGui.WorldTitleDisplayTime", 255, 0, 255);
//    public static final ConfigBoolean WorldTitleFadeOut = new ConfigBoolean("extraGui.WorldTitleFadeOut", true);
//    public static final ConfigString WorldTitleColor = new ConfigString("extraGui.WorldTitleColor", "ffffff");
//    public static final int WorldTitleColorInt =  Integer.parseInt(WorldTitleColor.getStringValue(), 16);

    public static final ConfigHotkey ToggleInfo = new ConfigHotkey("extraGui.toggleShowInfo", "", "");
    public static final ConfigHotkey Stats = new ConfigHotkey("extraGui.Stats", Keyboard.KEY_P);
    public static final ConfigHotkey MobStats = new ConfigHotkey("extraGui.MobStats", "LCONTROL,P", "");

    private static final ExtraGuiConfig Instance;
    public static final List<ConfigBase<?>> configValues;
    public static final List<ConfigBase<?>> durability;
    public static final List<ConfigBase<?>> presentInfo;
    public static final List<ConfigBase<?>> info;
    public static final List<ConfigBase<?>> itemRender;
//    public static final List<ConfigBase<?>> worldTitle;
    public static final List<ConfigHotkey> hotkeys;

    public static final List<ConfigTab> tabs = new ArrayList<>();

    public ExtraGuiConfig() {
        super("Extra Gui", hotkeys, durability);
    }

    static {
        durability = List.of(DisplayDurability, DurabilityPercentageDisplay, DurabilityLine, DurabilityX, DurabilityY, DurabilitySize, DurabilityColor, DurabilityTextRight);
        info = List.of(ShowInfo, DisableDevInfo, DisableErrorInfo, RightAlign, background, timeZone, InfoXLevel, InfoYLevel, InfoSize, infoColor);
        presentInfo = List.of(FPS, Mem, RealTime, MCTime, Position, DimensionPosition, ChunkPosition, Direction, YawPitchSpeed, Weather, Light, Biome, Dimension, MoonPhases, OnePercentLowFps, CustomString);
        itemRender = List.of(DisplayItemRender, ItemRenderX, ItemRenderY, ItemRenderSize);
//        worldTitle = List.of(DisplayWorldTitle, WorldTitleX, WorldTitleY, WorldTitleSize, WorldTitleAlpha, WorldTitleDisplayTime, WorldTitleFadeOut, WorldTitleColor);
        hotkeys = List.of(ToggleInfo, Stats, MobStats);

        configValues = new ArrayList<>();
//        configValues.addAll(durability);
//        configValues.addAll(info);
        configValues.addAll(presentInfo);
//        configValues.addAll(itemRender);
//        configValues.addAll(worldTitle);

        tabs.add(new ConfigTab("extraGui.durability", durability));
        tabs.add(new ConfigTab("extraGui.info", info));
        tabs.add(new ConfigTab("extraGui.presentInfo", presentInfo));
        tabs.add(new ConfigTab("extraGui.itemRender", itemRender));
//        tabs.add(new ConfigTab("extraGui.worldTitle", worldTitle));
        tabs.add(new ConfigTab("extraGui.hotkeys", hotkeys));

        Instance = new ExtraGuiConfig();

        ToggleInfo.setHotKeyPressCallBack(minecraft -> ShowInfo.toggleBooleanValue());
        Stats.setHotKeyPressCallBack(minecraft -> minecraft.displayGuiScreen(new GuiEntityStats()));
        MobStats.setHotKeyPressCallBack(minecraft -> minecraft.displayGuiScreen(new GuiEntityStats(minecraft.objectMouseOver != null ? minecraft.objectMouseOver.getEntityHit().getAsEntityLiving() : minecraft.thePlayer)));
    }

    @Override
    public List<ConfigTab> getConfigTabs() {
        return tabs;
    }

    public static ExtraGuiConfig getInstance() {
        return Instance;
    }

    @Override
    public void save() {
        JsonObject root = new JsonObject();
        ConfigUtils.writeConfigBase(root, "耐久", durability);
        ConfigUtils.writeConfigBase(root, "信息", info);
        ConfigUtils.writeConfigBase(root, "显示信息", presentInfo);
        ConfigUtils.writeConfigBase(root, "物品渲染", itemRender);
//        ConfigUtils.writeConfigBase(root, "世界标题", worldTitle);
        ConfigUtils.writeConfigBase(root, "快捷键", hotkeys);
        JsonUtils.writeJsonToFile(root, this.optionsFile);
    }

    @Override
    public void load() {
        if (!this.optionsFile.exists()) {
            this.save();
        } else {
            JsonElement jsonElement = JsonUtils.parseJsonFile(this.optionsFile);
            if (jsonElement != null && jsonElement.isJsonObject()) {
                JsonObject root = jsonElement.getAsJsonObject();
                ConfigUtils.readConfigBase(root, "耐久", durability);
                ConfigUtils.readConfigBase(root, "信息", info);
                ConfigUtils.readConfigBase(root, "显示信息", presentInfo);
                ConfigUtils.readConfigBase(root, "物品渲染", itemRender);
//                ConfigUtils.readConfigBase(root, "世界标题", worldTitle);
                ConfigUtils.readConfigBase(root, "快捷键", hotkeys);
            }
        }
    }

    public static String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getID();
    }
}
