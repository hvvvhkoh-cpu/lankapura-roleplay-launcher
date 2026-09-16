# LANKAPURA ROLEPLAY Android Launcher

Blue-themed Android launcher for:
`51.79.254.10:7655`

## Build the APK in GitHub Actions

1. Create a new GitHub repository.
2. Upload all files from this folder to the repository.
3. Commit/push to the `main` branch.
4. Open the repository's **Actions** tab.
5. Select **Build LANKAPURA ROLEPLAY APK**.
6. The workflow runs automatically after a push, or you can choose **Run workflow**.
7. Open the completed workflow run.
8. In **Artifacts**, download `LankapuraRoleplay-debug-apk`.
9. Extract the downloaded ZIP and install `app-debug.apk` on Android.

This is a debug APK, suitable for testing. A production release should be signed with a private Android signing key.

## Important

The launcher stores the server address in the app and the PLAY NOW button currently copies
`51.79.254.10:7655` because Android SA-MP client implementations vary. It does not pretend to
launch a desktop SA-MP executable from Android.
