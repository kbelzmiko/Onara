#!/bin/sh
set -euo pipefail

cd "$(dirname "$0")"

APP_NAME="Onara"
APP_VERSION="${1:-1.0.0}"
MODULE="com.zmiko.onara/com.zmiko.onara.Launcher"

OS="$(uname -s)"

echo "==> Compiling and packaging ($APP_NAME $APP_VERSION)"
./mvnw clean package

echo "==> Building runtime image -> target/$APP_NAME/"
./mvnw javafx:jlink

echo "==> Packaging app-image -> target/dist/"
jpackage --type app-image \
    --name "$APP_NAME" \
    --app-version "$APP_VERSION" \
    --runtime-image "target/$APP_NAME" \
    --module "$MODULE" \
    --dest target/dist

case "$OS" in
    Darwin)
        echo "==> Packaging .dmg -> target/dist/$APP_NAME-$APP_VERSION.dmg"
        jpackage --type dmg \
            --name "$APP_NAME" \
            --app-version "$APP_VERSION" \
            --runtime-image "target/$APP_NAME" \
            --module "$MODULE" \
            --dest target/dist
        ;;
    Linux)
        ;;
    CYGWIN*|MINGW*|MSYS*)
        echo "==> Packaging .msi -> target/dist/$APP_NAME-$APP_VERSION.msi"
        jpackage --type msi \
            --name "$APP_NAME" \
            --app-version "$APP_VERSION" \
            --runtime-image "target/$APP_NAME" \
            --module "$MODULE" \
            --dest target/dist \
            --win-menu \
            --win-shortcut
        ;;
    *)
        echo "Unsupported platform: $OS" >&2
        exit 1
        ;;
esac

echo
echo "Done:"

case "$OS" in
    Darwin)
        echo "  target/dist/$APP_NAME.app"
        echo "  target/dist/$APP_NAME-$APP_VERSION.dmg"
        ;;
    Linux)
        echo "  target/dist/$APP_NAME/"
        ;;
    CYGWIN*|MINGW*|MSYS*)
        echo "  target/dist/$APP_NAME/"
        echo "  target/dist/$APP_NAME-$APP_VERSION.msi"
        ;;
esac