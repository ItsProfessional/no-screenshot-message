package me.proferk.noscreenshotmessage.mixin;

import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public class ScreenshotRecorderMixin {

    @Redirect(method = "method_1661", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private static void accept(Consumer<Text> messageReceiver, Object object)
    {
        MutableText text = (MutableText) object;
        if(text.getString().startsWith("Saved screenshot as ")) return;

        messageReceiver.accept(text);
    }

}
