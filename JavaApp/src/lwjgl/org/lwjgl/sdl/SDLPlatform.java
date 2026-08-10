package org.lwjgl.sdl;

public final class SDLPlatform {
    private SDLPlatform() {
        throw new UnsupportedOperationException();
    }

    public static String SDL_GetPlatform() {
        return "iOS";
    }
}
