package org.lwjgl.sdl;

public final class SDLError {
    private static volatile String error = "";

    private SDLError() {
        throw new UnsupportedOperationException();
    }

    public static String SDL_GetError() {
        return error;
    }

    static void setError(String newError) {
        error = newError == null ? "" : newError;
    }

    static void clearError() {
        error = "";
    }
}
