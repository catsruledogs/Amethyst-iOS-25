package org.lwjgl.sdl;

public final class SDLTimer {
    private SDLTimer() {
        throw new UnsupportedOperationException();
    }

    public static long SDL_GetTicksNS() {
        return System.nanoTime();
    }
}
