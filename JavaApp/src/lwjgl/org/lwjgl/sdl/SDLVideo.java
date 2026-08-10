package org.lwjgl.sdl;

import org.lwjgl.glfw.GLFW;

import java.nio.IntBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SDLVideo {
    public static final long SDL_WINDOW_FULLSCREEN = 0x00000001L;
    public static final long SDL_WINDOW_OPENGL = 0x00000002L;
    public static final long SDL_WINDOW_HIDDEN = 0x00000008L;
    public static final long SDL_WINDOW_BORDERLESS = 0x00000010L;
    public static final long SDL_WINDOW_RESIZABLE = 0x00000020L;
    public static final long SDL_WINDOW_HIGH_PIXEL_DENSITY = 0x00002000L;

    public static final int SDL_WINDOWPOS_UNDEFINED_MASK = 0x1FFF0000;
    public static final int SDL_WINDOWPOS_CENTERED_MASK = 0x2FFF0000;

    public static final int SDL_GL_RED_SIZE = 0;
    public static final int SDL_GL_GREEN_SIZE = 1;
    public static final int SDL_GL_BLUE_SIZE = 2;
    public static final int SDL_GL_ALPHA_SIZE = 3;
    public static final int SDL_GL_DEPTH_SIZE = 6;
    public static final int SDL_GL_STENCIL_SIZE = 7;
    public static final int SDL_GL_DOUBLEBUFFER = 5;
    public static final int SDL_GL_CONTEXT_MAJOR_VERSION = 17;
    public static final int SDL_GL_CONTEXT_MINOR_VERSION = 18;
    public static final int SDL_GL_CONTEXT_PROFILE_MASK = 20;
    public static final int SDL_GL_CONTEXT_FLAGS = 19;

    public static final int SDL_GL_CONTEXT_PROFILE_CORE = 0x0001;
    public static final int SDL_GL_CONTEXT_PROFILE_ES = 0x0004;

    private static final Map<Integer, Long> WINDOW_BY_ID = new ConcurrentHashMap<>();

    private SDLVideo() {
        throw new UnsupportedOperationException();
    }

    public static boolean SDL_GL_LoadLibrary(String path) {
        return true;
    }

    public static void SDL_GL_ResetAttributes() {
    }

    public static boolean SDL_GL_SetAttribute(int attr, int value) {
        Integer glfwHint = mapGLHint(attr);
        if (glfwHint != null) {
            GLFW.glfwWindowHint(glfwHint, value);
        }
        return true;
    }

    public static boolean SDL_GL_GetAttribute(int attr, IntBuffer value) {
        if (value != null) {
            value.put(0, 0);
        }
        return true;
    }

    public static long SDL_CreateWindow(String title, int width, int height, long flags) {
        GLFW.glfwDefaultWindowHints();
        if ((flags & SDL_WINDOW_RESIZABLE) != 0L) {
            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        }
        if ((flags & SDL_WINDOW_BORDERLESS) != 0L) {
            GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
        }
        long window = GLFW.glfwCreateWindow(width, height, title, 0L, 0L);
        if (window != 0L) {
            WINDOW_BY_ID.put((int) window, window);
            if ((flags & SDL_WINDOW_HIDDEN) == 0L) {
                GLFW.glfwShowWindow(window);
            }
        }
        return window;
    }

    public static void SDL_DestroyWindow(long window) {
        WINDOW_BY_ID.remove((int) window);
        GLFW.glfwDestroyWindow(window);
    }

    public static long SDL_GetPrimaryDisplay() {
        return GLFW.glfwGetPrimaryMonitor();
    }

    public static String SDL_GetCurrentVideoDriver() {
        return "uikit";
    }

    public static int SDL_GetWindowID(long window) {
        WINDOW_BY_ID.put((int) window, window);
        return (int) window;
    }

    public static long SDL_GetWindowFromID(int id) {
        Long window = WINDOW_BY_ID.get(id);
        return window == null ? 0L : window.longValue();
    }

    public static boolean SDL_GetWindowPosition(long window, IntBuffer x, IntBuffer y) {
        int[] xpos = x != null ? new int[1] : null;
        int[] ypos = y != null ? new int[1] : null;
        GLFW.glfwGetWindowPos(window, xpos, ypos);
        if (x != null && xpos != null) x.put(0, xpos[0]);
        if (y != null && ypos != null) y.put(0, ypos[0]);
        return true;
    }

    public static boolean SDL_GetWindowSize(long window, IntBuffer width, IntBuffer height) {
        int[] w = width != null ? new int[1] : null;
        int[] h = height != null ? new int[1] : null;
        GLFW.glfwGetWindowSize(window, w, h);
        if (width != null && w != null) width.put(0, w[0]);
        if (height != null && h != null) height.put(0, h[0]);
        return true;
    }

    public static boolean SDL_GetWindowSizeInPixels(long window, IntBuffer width, IntBuffer height) {
        int[] w = width != null ? new int[1] : null;
        int[] h = height != null ? new int[1] : null;
        GLFW.glfwGetFramebufferSize(window, w, h);
        if (width != null && w != null) width.put(0, w[0]);
        if (height != null && h != null) height.put(0, h[0]);
        return true;
    }

    public static boolean SDL_SetWindowPosition(long window, int x, int y) {
        GLFW.glfwSetWindowPos(window, x, y);
        return true;
    }

    public static boolean SDL_SetWindowSize(long window, int width, int height) {
        GLFW.glfwSetWindowSize(window, width, height);
        return true;
    }

    public static void SDL_SetWindowTitle(long window, String title) {
        GLFW.glfwSetWindowTitle(window, title);
    }

    public static boolean SDL_ShowWindow(long window) {
        GLFW.glfwShowWindow(window);
        return true;
    }

    public static boolean SDL_HideWindow(long window) {
        return true;
    }

    public static boolean SDL_SetWindowFullscreen(long window, boolean fullscreen) {
        GLFW.glfwSetWindowMonitor(window, fullscreen ? GLFW.glfwGetPrimaryMonitor() : 0L, 0, 0, 0, 0, 0);
        return true;
    }

    public static long SDL_GetWindowFlags(long window) {
        return 0L;
    }

    public static boolean SDL_SetWindowResizable(long window, boolean resizable) {
        GLFW.glfwSetWindowAttrib(window, GLFW.GLFW_RESIZABLE, resizable ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        return true;
    }

    public static boolean SDL_SetWindowBordered(long window, boolean bordered) {
        GLFW.glfwSetWindowAttrib(window, GLFW.GLFW_DECORATED, bordered ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
        return true;
    }

    public static boolean SDL_SetWindowMinimumSize(long window, int width, int height) {
        GLFW.glfwSetWindowSizeLimits(window, width, height, GLFW.GLFW_DONT_CARE, GLFW.GLFW_DONT_CARE);
        return true;
    }

    public static boolean SDL_SetWindowMaximumSize(long window, int width, int height) {
        GLFW.glfwSetWindowSizeLimits(window, GLFW.GLFW_DONT_CARE, GLFW.GLFW_DONT_CARE, width, height);
        return true;
    }

    public static float SDL_GetWindowDisplayScale(long window) {
        return 1.0f;
    }

    public static float SDL_GetWindowPixelDensity(long window) {
        return 1.0f;
    }

    public static boolean SDL_RaiseWindow(long window) {
        return true;
    }

    public static boolean SDL_MaximizeWindow(long window) {
        return true;
    }

    public static boolean SDL_MinimizeWindow(long window) {
        return true;
    }

    public static boolean SDL_RestoreWindow(long window) {
        return true;
    }

    public static boolean SDL_SyncWindow(long window) {
        return true;
    }

    public static long SDL_GL_CreateContext(long window) {
        return window;
    }

    public static boolean SDL_GL_MakeCurrent(long window, long context) {
        GLFW.glfwMakeContextCurrent(window);
        return true;
    }

    public static boolean SDL_GL_SetSwapInterval(int interval) {
        GLFW.glfwSwapInterval(interval);
        return true;
    }

    public static int SDL_GL_GetSwapInterval() {
        return 0;
    }

    public static void SDL_GL_SwapWindow(long window) {
        GLFW.glfwSwapBuffers(window);
    }

    public static long SDL_GL_GetCurrentWindow() {
        return GLFW.glfwGetCurrentContext();
    }

    public static long SDL_GL_GetCurrentContext() {
        return GLFW.glfwGetCurrentContext();
    }

    public static void SDL_GL_DestroyContext(long context) {
    }

    public static long SDL_GL_GetProcAddress(String proc) {
        return 0L;
    }

    private static Integer mapGLHint(int attr) {
        switch (attr) {
            case SDL_GL_RED_SIZE:
                return GLFW.GLFW_RED_BITS;
            case SDL_GL_GREEN_SIZE:
                return GLFW.GLFW_GREEN_BITS;
            case SDL_GL_BLUE_SIZE:
                return GLFW.GLFW_BLUE_BITS;
            case SDL_GL_ALPHA_SIZE:
                return GLFW.GLFW_ALPHA_BITS;
            case SDL_GL_DEPTH_SIZE:
                return GLFW.GLFW_DEPTH_BITS;
            case SDL_GL_STENCIL_SIZE:
                return GLFW.GLFW_STENCIL_BITS;
            case SDL_GL_DOUBLEBUFFER:
                return GLFW.GLFW_DOUBLEBUFFER;
            case SDL_GL_CONTEXT_MAJOR_VERSION:
                return GLFW.GLFW_CONTEXT_VERSION_MAJOR;
            case SDL_GL_CONTEXT_MINOR_VERSION:
                return GLFW.GLFW_CONTEXT_VERSION_MINOR;
            case SDL_GL_CONTEXT_PROFILE_MASK:
                return GLFW.GLFW_OPENGL_PROFILE;
            case SDL_GL_CONTEXT_FLAGS:
                return GLFW.GLFW_OPENGL_FORWARD_COMPAT;
            default:
                return null;
        }
    }

    public static int SDL_WINDOWPOS_UNDEFINED_DISPLAY(int displayIndex) {
        return SDL_WINDOWPOS_UNDEFINED_MASK | displayIndex;
    }

    public static int SDL_WINDOWPOS_CENTERED_DISPLAY(int displayIndex) {
        return SDL_WINDOWPOS_CENTERED_MASK | displayIndex;
    }
}
