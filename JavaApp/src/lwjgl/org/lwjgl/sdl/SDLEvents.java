package org.lwjgl.sdl;

import org.lwjgl.glfw.GLFW;

public final class SDLEvents {
    public static final int SDL_GETEVENT = 2;

    public static final int SDL_EVENT_FIRST = 0;
    public static final int SDL_EVENT_LAST = 0xFFFF;
    public static final int SDL_EVENT_QUIT = 0x100;
    public static final int SDL_EVENT_WINDOW_MOVED = 0x203;
    public static final int SDL_EVENT_WINDOW_RESIZED = 0x205;
    public static final int SDL_EVENT_WINDOW_PIXEL_SIZE_CHANGED = 0x206;
    public static final int SDL_EVENT_WINDOW_FOCUS_GAINED = 0x20C;
    public static final int SDL_EVENT_WINDOW_FOCUS_LOST = 0x20D;
    public static final int SDL_EVENT_WINDOW_CLOSE_REQUESTED = 0x20E;
    public static final int SDL_EVENT_KEY_DOWN = 0x300;
    public static final int SDL_EVENT_KEY_UP = 0x301;
    public static final int SDL_EVENT_TEXT_EDITING = 0x302;
    public static final int SDL_EVENT_TEXT_INPUT = 0x303;
    public static final int SDL_EVENT_MOUSE_MOTION = 0x400;
    public static final int SDL_EVENT_MOUSE_BUTTON_DOWN = 0x401;
    public static final int SDL_EVENT_MOUSE_BUTTON_UP = 0x402;
    public static final int SDL_EVENT_MOUSE_WHEEL = 0x403;
    public static final int SDL_EVENT_DROP_FILE = 0x1000;
    public static final int SDL_EVENT_DISPLAY_ADDED = 0x151;
    public static final int SDL_EVENT_DISPLAY_REMOVED = 0x152;

    private SDLEvents() {
        throw new UnsupportedOperationException();
    }

    public static void SDL_PumpEvents() {
        GLFW.glfwPollEvents();
    }

    public static int SDL_PeepEvents(Object events, int action, int minType, int maxType) {
        return 0;
    }
}
