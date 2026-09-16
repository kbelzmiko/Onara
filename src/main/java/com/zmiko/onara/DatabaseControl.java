package com.zmiko.onara;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

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

        if (!exists()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE taskList (id INTEGER PRIMARY KEY, description TEXT)");
            } catch (SQLException e) {
                System.err.println("Error, " + e.getMessage());
            }
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
            System.err.println("Error ISEMPTY, " + e.getMessage());
            return true;
        }
    }

    public ArrayList<Task> getFromDatabase() {
        ArrayList<Task> result = new ArrayList<>();

        String sql = "SELECT id, description FROM taskList ORDER BY id ASC";

        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String desc = rs.getString("description");
                result.add(new Task(id,desc));
            }
        } catch (SQLException e) {
            System.err.println("Error GETFROM, " + e.getMessage());
        }
        return result;
    }

    public boolean insert(String task) {
        String sql = "INSERT INTO taskList (description) VALUES (?)";

        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, task);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error INSERT, " + e.getMessage());
            return false;
        }

    }

    public boolean pop(int id) {
        String sql = "DELETE FROM taskList WHERE id = ?";

        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error POP, " + e.getMessage());
        }

        return true;
    }

    public int getLastId() {
        String sql = "SELECT COALESCE(MAX(id), 0) AS max_id FROM taskList";

        try {
            PreparedStatement pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getInt("max_id");
            }

        } catch (SQLException e) {
            System.err.println("Error LASTID, " + e.getMessage());
        }
        return 0;
    }
}
