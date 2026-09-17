#!/bin/sh
set -euo pipefail

cd "$(dirname "$0")"

APP_NAME="Onara"
APP_VERSION="${1:-1.0.0}"

echo "==> Compiling and packaging ($APP_NAME $APP_VERSION)"
./mvnw clean package

echo "==> Building runtime image -> target/$APP_NAME/"
./mvnw javafx:jlink

echo "==> Packaging .app -> target/dist/$APP_NAME.app"
jpackage --type app-image \
    --name "$APP_NAME" \
    --app-version "$APP_VERSION" \
    --runtime-image "target/$APP_NAME" \
    --module "com.zmiko.onara/com.zmiko.onara.Launcher" \
    --dest target/dist

echo "==> Packaging .dmg -> target/dist/$APP_NAME-$APP_VERSION.dmg"
jpackage --type dmg \
    --name "$APP_NAME" \
    --app-version "$APP_VERSION" \
    --runtime-image "target/$APP_NAME" \
    --module "com.zmiko.onara/com.zmiko.onara.Launcher" \
    --dest target/dist

echo
echo "Done:"
echo "  target/dist/$APP_NAME.app"
echo "  target/dist/$APP_NAME-$APP_VERSION.dmg"