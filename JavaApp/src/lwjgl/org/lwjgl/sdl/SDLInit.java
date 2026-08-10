package org.lwjgl.sdl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SDLInit {
    public static final int SDL_INIT_VIDEO = 0x00000020;
    public static final int SDL_INIT_EVENTS = 0x00004000;

    public static final String SDL_PROP_APP_METADATA_NAME_STRING = "SDL.app.metadata.name";
    public static final String SDL_PROP_APP_METADATA_VERSION_STRING = "SDL.app.metadata.version";
    public static final String SDL_PROP_APP_METADATA_IDENTIFIER_STRING = "SDL.app.metadata.identifier";
    public static final String SDL_PROP_APP_METADATA_CREATOR_STRING = "SDL.app.metadata.creator";
    public static final String SDL_PROP_APP_METADATA_COPYRIGHT_STRING = "SDL.app.metadata.copyright";
    public static final String SDL_PROP_APP_METADATA_URL_STRING = "SDL.app.metadata.url";
    public static final String SDL_PROP_APP_METADATA_TYPE_STRING = "SDL.app.metadata.type";

    private static final Map<String, String> APP_METADATA = new ConcurrentHashMap<>();
    private static volatile int initializedFlags;

    private SDLInit() {
        throw new UnsupportedOperationException();
    }

    public static boolean SDL_Init(int flags) {
        initializedFlags |= flags;
        SDLError.clearError();
        return true;
    }

    public static boolean SDL_InitSubSystem(int flags) {
        initializedFlags |= flags;
        SDLError.clearError();
        return true;
    }

    public static int SDL_WasInit(int flags) {
        return initializedFlags & flags;
    }

    public static void SDL_QuitSubSystem(int flags) {
        initializedFlags &= ~flags;
    }

    public static void SDL_Quit() {
        initializedFlags = 0;
    }

    public static void SDL_SetAppMetadataProperty(String key, String value) {
        if (key != null && value != null) {
            APP_METADATA.put(key, value);
        }
    }
}
