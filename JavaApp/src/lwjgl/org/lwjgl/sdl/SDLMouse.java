package org.lwjgl.sdl;

import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public final class SDLMouse {
    public static final int SDL_BUTTON_LEFT = 1;
    public static final int SDL_BUTTON_MIDDLE = 2;
    public static final int SDL_BUTTON_RIGHT = 3;
    public static final int SDL_BUTTON_X1 = 4;
    public static final int SDL_BUTTON_X2 = 5;

    public static final int SDL_BUTTON_LMASK = 1 << (SDL_BUTTON_LEFT - 1);
    public static final int SDL_BUTTON_MMASK = 1 << (SDL_BUTTON_MIDDLE - 1);
    public static final int SDL_BUTTON_RMASK = 1 << (SDL_BUTTON_RIGHT - 1);
    public static final int SDL_BUTTON_X1MASK = 1 << (SDL_BUTTON_X1 - 1);
    public static final int SDL_BUTTON_X2MASK = 1 << (SDL_BUTTON_X2 - 1);

    private SDLMouse() {
        throw new UnsupportedOperationException();
    }

    public static int SDL_GetMouseState(IntBuffer x, IntBuffer y) {
        DoubleBuffer dx = x != null ? DoubleBuffer.allocate(1) : null;
        DoubleBuffer dy = y != null ? DoubleBuffer.allocate(1) : null;
        GLFW.glfwGetCursorPos(0L, dx, dy);
        if (x != null && dx != null) {
            x.put(0, (int) dx.get(0));
        }
        if (y != null && dy != null) {
            y.put(0, (int) dy.get(0));
        }

        int mask = 0;
        if (GLFW.glfwGetMouseButton(0L, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS) mask |= SDL_BUTTON_LMASK;
        if (GLFW.glfwGetMouseButton(0L, GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == GLFW.GLFW_PRESS) mask |= SDL_BUTTON_MMASK;
        if (GLFW.glfwGetMouseButton(0L, GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS) mask |= SDL_BUTTON_RMASK;
        if (GLFW.glfwGetMouseButton(0L, GLFW.GLFW_MOUSE_BUTTON_4) == GLFW.GLFW_PRESS) mask |= SDL_BUTTON_X1MASK;
        if (GLFW.glfwGetMouseButton(0L, GLFW.GLFW_MOUSE_BUTTON_5) == GLFW.GLFW_PRESS) mask |= SDL_BUTTON_X2MASK;
        return mask;
    }

    public static boolean SDL_WarpMouseInWindow(long window, float x, float y) {
        GLFW.glfwSetCursorPos(window, x, y);
        return true;
    }

    public static boolean SDL_SetWindowRelativeMouseMode(long window, boolean enabled) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, enabled ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
        return true;
    }

    public static boolean SDL_HideCursor() {
        return true;
    }

    public static boolean SDL_ShowCursor() {
        return true;
    }
}
