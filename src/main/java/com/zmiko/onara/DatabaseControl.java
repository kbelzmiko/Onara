package com.zmiko.onara;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DatabaseControl {
    private static final String APP_DIR_NAME = "com.zmiko.onara";
    private static final String  DB_FILE_NAME = "tasks.db";
    private Connection conn;

    public DatabaseControl() {
        Path dir = getAppDataDirectory();

        try {
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            System.err.println("Error, " + e.getMessage());
        }

        Path dbPath = getDatabasePath();

        String url = "jdbc:sqlite:" + dbPath.toAbsolutePath().toString().replace("\\", "/");

        try {
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Error, " + e.getMessage());
        }
    }

    private Path getAppDataDirectory() {
        String appData = System.getenv("APPDATA");

        Path basePath;

        if ((appData != null) && !appData.isEmpty()) {
            basePath = Paths.get(appData);
        } else {
            String userHome = System.getProperty("user.home");
            basePath = Paths.get(userHome, ".local/share");
        }

        return basePath.resolve(APP_DIR_NAME);
    }

    private Path getDatabasePath() {
        return getAppDataDirectory().resolve(DB_FILE_NAME);
    }


    public boolean exists() {
        Path dbPath = getDatabasePath();
        try {
            return Files.exists(dbPath) && Files.size(dbPath) > 0;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean isEmpty() {
        String sql = "SELECT name FROM sqlite_master";

        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return !rs.next();

        } catch (SQLException e) {
            System.err.println("Error, " + e.getMessage());
            return true;
        }
    }

}
