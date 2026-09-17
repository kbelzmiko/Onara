## About

Onara (One at a time) is an open source secuencial tasks desktop application for simple productivity.

### Build

Requires JDK 21+ (jpackage needs a full JDK, not just a JRE). One command:

```sh
./build.sh                # -> target/dist/Onara.app + target/dist/Onara-1.0.0.dmg
./build.sh 1.1.0          # override the app version
```

It runs `./mvnw clean package`, `./mvnw javafx:jlink` (portable runtime image in `target/Onara/`), then `jpackage` for the `.app` and `.dmg`.

### Install
Drag `Onara.app` into Applications from `target/dist/Onara-1.0.0.dmg`, or run `target/dist/Onara.app` directly. Tasks are stored in `~/.local/share/com.zmiko.onara/tasks.db`.

### Images
WIP 

### Modes
Stack/Queue
