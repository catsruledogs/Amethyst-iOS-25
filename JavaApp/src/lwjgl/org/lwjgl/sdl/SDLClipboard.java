package org.lwjgl.sdl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
public final class SDLClipboard {
    private SDLClipboard() {
        throw new UnsupportedOperationException();
    }

    public static String SDL_GetClipboardText() {
        String value = GLFW.glfwGetClipboardString(0L);
        return value == null ? "" : value;
    }

    public static boolean SDL_SetClipboardText(CharSequence text) {
        GLFW.glfwSetClipboardString(0L, text == null ? "" : text);
        return true;
    }

    public static boolean SDL_SetClipboardText(ByteBuffer text) {
        GLFW.glfwSetClipboardString(0L, text);
        return true;
    }

    public static boolean nSDL_SetClipboardText(long address) {
        if (address == 0L) {
            return false;
        }
        String value = MemoryUtil.memUTF8(address);
        GLFW.glfwSetClipboardString(0L, value == null ? "" : value);
        return true;
    }

    public static long nSDL_GetClipboardText() {
        String value = SDL_GetClipboardText();
        return MemoryUtil.memAddress(MemoryUtil.memUTF8(value == null ? "" : value, true));
    }
}
