package moddedmite.xylose.extragui.mixin;

import fi.dy.masa.malilib.config.options.ConfigBase;
import moddedmite.xylose.extragui.config.ConfigInfo;
import moddedmite.xylose.extragui.config.ExtraGuiConfig;
import moddedmite.xylose.extragui.gui.GuiDebugInfo;
import moddedmite.xylose.extragui.gui.GuiDurability;
import moddedmite.xylose.extragui.gui.GuiEffectRenderer;
import moddedmite.xylose.extragui.gui.GuiMiniInfoHandle;
import net.minecraft.*;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

import static net.minecraft.Minecraft.inDevMode;

@Mixin(GuiIngame.class)
public class GuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;

    @Inject(method = {"renderGameOverlay(FZII)V"},
            at = {@At(value = "INVOKE",
                    target = "Lnet/minecraft/Minecraft;inDevMode()Z",
                    shift = At.Shift.BEFORE)})
    private void injectRenderExtraGuiIngame(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
        if (mc.gameSettings.gui_mode == 0) {
            GuiDurability guiDurability = new GuiDurability();
            GuiDebugInfo guiDebugInfo = new GuiDebugInfo();
            GuiEffectRenderer inventoryEffectRenderer = new GuiEffectRenderer();

            guiDurability.renderDurability();
            if (ExtraGuiConfig.MiniEffect.getBooleanValue())
                inventoryEffectRenderer.displayMiniDebuffEffects();
            else
                inventoryEffectRenderer.displayDebuffEffects();
            guiDebugInfo.renderDebugInfoOverlay(mc);
        }
    }

    @Inject(
            method = {"renderGameOverlay(FZII)V"},
            at = {@At(value = "INVOKE",
                    target = "Lnet/minecraft/Minecraft;inDevMode()Z",
                    shift = At.Shift.BEFORE)})
    private void injectRenderInfoString(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
        if (!ExtraGuiConfig.ShowInfo.getBooleanValue()) {
            return;
        }
        if (mc.gameSettings.showDebugInfo) {
            return;
        }
        if (!(mc.gameSettings.gui_mode == 0)) {
            return;
        }
        GuiMiniInfoHandle.getInstance().updatePosition(this.mc);
        FontRenderer fontRenderer = this.mc.fontRenderer;
        ArrayList<String> strings = new ArrayList<String>();
        for (ConfigBase<?> value : ExtraGuiConfig.configValues) {
            ConfigInfo configInfo;
            if (!(value instanceof ConfigInfo) || !(configInfo = (ConfigInfo)value).getBooleanValue()) continue;
            strings.add(value.getConfigGuiDisplayName() + ": " + configInfo.getString(this.mc));
        }
        GuiMiniInfoHandle.getInstance().drawStrings( this, fontRenderer, strings);
    }

    @Redirect(method={"renderGameOverlay"}, at=@At(value="INVOKE", target="Lnet/minecraft/Minecraft;inDevMode()Z"), require=0)
    private boolean disableDevInfoConfig() {
        return !ExtraGuiConfig.DisableDevInfo.getBooleanValue() && (!this.mc.gameSettings.showDebugInfo && inDevMode());
    }

    @Redirect(method = "renderGameOverlay", at = @At(value = "FIELD", target = "Lnet/minecraft/Debug;is_active:Z"), require = 0)
    private boolean disableIsActiveInfo() {
        return !this.mc.gameSettings.showDebugInfo && inDevMode() && Debug.is_active;
    }

    @Redirect(method = "renderGameOverlay", at = @At(value = "FIELD", target = "Lnet/minecraft/GameSettings;showDebugInfo:Z", opcode = Opcodes.GETFIELD), require = 0)
    private boolean doNotShowFPS(GameSettings instance) {
        return false;
    }

}
