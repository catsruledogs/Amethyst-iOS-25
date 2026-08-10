package org.lwjgl.sdl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SDLHints {
    public static final String SDL_HINT_VIDEO_DRIVER = "SDL_VIDEO_DRIVER";
    public static final String SDL_HINT_MOUSE_FOCUS_CLICKTHROUGH = "SDL_MOUSE_FOCUS_CLICKTHROUGH";
    public static final String SDL_HINT_IME_IMPLEMENTED_UI = "SDL_IME_IMPLEMENTED_UI";
    public static final String SDL_HINT_QUIT_ON_LAST_WINDOW_CLOSE = "SDL_QUIT_ON_LAST_WINDOW_CLOSE";
    public static final String SDL_HINT_VIDEO_WAYLAND_ALLOW_LIBDECOR = "SDL_VIDEO_WAYLAND_ALLOW_LIBDECOR";
    public static final String SDL_HINT_VIDEO_WAYLAND_PREFER_LIBDECOR = "SDL_VIDEO_WAYLAND_PREFER_LIBDECOR";
    public static final String SDL_HINT_VIDEO_MAC_FULLSCREEN_SPACES = "SDL_VIDEO_MAC_FULLSCREEN_SPACES";

    private static final Map<String, String> HINTS = new ConcurrentHashMap<>();

    private SDLHints() {
        throw new UnsupportedOperationException();
    }

    public static boolean SDL_SetHint(String name, String value) {
        if (name == null || value == null) {
            return false;
        }
        HINTS.put(name, value);
        return true;
    }

    public static String SDL_GetHint(String name) {
        return HINTS.get(name);
    }
}
