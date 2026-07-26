# Changelog

## v1.0

### Fixes

- **RAM Slider stuck at 200MB**: Root cause was all sliders sharing the same `UITableViewCell` reuse identifier (`@"slider"`) without resetting `minimumValue`/`maximumValue` on reuse. The allocated_memory slider would inherit incorrect range (e.g., 25-150%) from `video.resolution` or other sliders, causing the slider to clip the auto-calculated value (1189MB) to a wrong max. Fixed by always setting `sl.minimumValue`/`maximumValue` in `configureCell:` and using `sl.value` for the label. Also added migration in `PLPreferences.setDefaultsForPref:` to overwrite persisted `allocated_memory` values < 256MB on every app launch.

- **JVM SIGSEGV in `input_bridge_v3.m`**: Added null guards on `JNI_OnLoadGLFW` (`FindClass`/`GetStaticMethodID`/`GetStaticFieldID`) and `handleFramebufferSizeJava` to prevent null-pointer dereference.

- **Microsoft login callback**: Fixed `AccountViewController.m` success check — changed `[status isEqualToString:@"Done"]` to handle `status == nil` (actual success case).

- **LWJGL version resolution**: Fixed `VersionDirectoryManager.m` — added `?: @"3.3.3"` fallback when no LWJGL version is configured.

- **Log4j classpath**: Added glob-search and `addURL` in `Tools.java:launchMinecraft` to fix Log4j classpath loading.

- **JAR/Minecraft launch paths**: Merged renderer, gameDir, PojavClassLoader, and profile args into `JavaLauncher.m` so both JAR and Minecraft launches set the same environment.

- **Game directory setup**: Added game directory setup in `UIKit_launchJarFile` before `SurfaceViewController`.

- **Demo account**: Added demo purchase check + play blocking in `MainCoordinator.m:launchGame:` and `launchWithServer:`; added Demo badge in `AccountCell.configureWithAccount:`.

- **`getPhysicalMemoryMB()` removed**: Removed the wrapper function from `LauncherPreferences.h/.m` (including `#import <sys/sysctl.h>`). Replaced all usages with `(NSProcessInfo.processInfo.physicalMemory / 1048576)`.

- **`PojavLauncher.java` and `UIKit.callback`**: Fixed JAR import path — use `-Dpojav.runJar` instead of `args[0].equals("-jar")`.

- **`Tools.java:preProcessLibraries`**: Added bounds check for library names.

- **`PojavLauncher.java:91`**: Added null-safe guard for `version.id`.

- **Makefile line 278**: Changed to `-rm -f` for incremental builds.

- **`Natives/main.m:236`**: Fixed `POJAV_GAME_DIR` to use real path, not symlink.

- **LWJGL 3.3.6 removed**: Removed from both LWJGL version pickers and `recommendedLWJGLVersion`.

- **`UIView+LiquidGlass.m`**: Fixed `UIGlassEffect` compile error on Xcode 15.4 (CI) — use `NSClassFromString` runtime check instead of `@available(iOS 26, *)`.
