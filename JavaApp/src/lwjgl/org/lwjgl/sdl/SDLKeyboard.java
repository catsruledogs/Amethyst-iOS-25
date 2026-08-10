package org.lwjgl.sdl;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public final class SDLKeyboard {
    private static final ByteBuffer KEYBOARD_STATE = ByteBuffer.allocateDirect(512);

    private SDLKeyboard() {
        throw new UnsupportedOperationException();
    }

    public static ByteBuffer SDL_GetKeyboardState() {
        return KEYBOARD_STATE;
    }

    public static int SDL_GetScancodeFromKey(int key, Object keymodstate) {
        return key;
    }

    public static int SDL_GetModState() {
        return 0;
    }

    public static long nSDL_GetKeyName(int keycode) {
        String name = GLFW.glfwGetKeyName(keycode, -1);
        return MemoryUtil.memAddress(MemoryUtil.memUTF8(name == null ? "" : name, true));
    }

    public static long nSDL_GetScancodeName(int scancode) {
        return MemoryUtil.memAddress(MemoryUtil.memUTF8(String.valueOf(scancode), true));
    }

    public static void SDL_StartTextInput(long window) {
    }

    public static void SDL_StopTextInput(long window) {
    }

    public static long SDL_GetKeyboardFocus() {
        return GLFW.glfwGetCurrentContext();
    }
}
