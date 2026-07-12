package moddedmite.xylose.extragui.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import moddedmite.xylose.extragui.util.DataSync;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiRepair.class)
public abstract class GuiRepairMixin extends GuiContainer {
    @Shadow
    private TileEntityAnvil tile_entity_anvil;
    @Shadow
    private ContainerRepair repairContainer;

    public GuiRepairMixin(Container par1Container) {
        super(par1Container);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer", at = @At("TAIL"))
    private void drawAnvilDurability(float par1, int par2, int par3, CallbackInfo ci, @Local(name = "var4") int var4, @Local(name = "var5") int var5) {
        TileEntity tileEntity = DataSync.getServerSide(this.tile_entity_anvil);
        if (tileEntity instanceof TileEntityAnvil tileEntityAnvil) {
            int maxDurability = ((BlockAnvil) this.repairContainer.block).getDurability();
            int durability = tileEntityAnvil.damage;
            if (durability == maxDurability) return;

            this.mc.fontRenderer.drawString(
                    I18n.getStringParams(
                            "extragui.gui.anvil.durability",
                            maxDurability - durability, maxDurability),
                    var4 + 25, var5 + 70, 4210752);
        }
    }
}
