package com.paresh.practice.design.patterns.creational.builder;

class DatabaseConnectionConfig {
    // Required fields
    private final String host;
    private final int port;

    // Optional fields
    private final String username;
    private final String password;
    private final boolean useSSL;

    // Private constructor (used by the builder)
    private DatabaseConnectionConfig(DatabaseConnectionConfigBuilder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.username = builder.username;
        this.password = builder.password;
        this.useSSL = builder.useSSL;
    }

    // Getters
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    // DatabaseConnectionConfig Builder class
    public static class DatabaseConnectionConfigBuilder {
        private final String host;  // Required field
        private final int port;     // Required field

        // Optional fields
        private String username = "root";   // Default value
        private String password = "";       // Default value
        private boolean useSSL = false;     // Default value

        // Constructor with required fields
        public DatabaseConnectionConfigBuilder(String host, int port) {
            this.host = host;
            this.port = port;
        }

        // Optional fields
        public DatabaseConnectionConfigBuilder username(String username) {
            this.username = username;
            return this;
        }

        public DatabaseConnectionConfigBuilder password(String password) {
            this.password = password;
            return this;
        }

        public DatabaseConnectionConfigBuilder useSSL(boolean useSSL) {
            this.useSSL = useSSL;
            return this;
        }

        // Build method to create DatabaseConnectionConfig instance
        public DatabaseConnectionConfig build() {
            return new DatabaseConnectionConfig(this);
        }
    }

    @Override
    public String toString() {
        return "DatabaseConnectionConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", useSSL=" + useSSL +
                '}';
    }
}


public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Create a DatabaseConnectionConfig using the builder
        DatabaseConnectionConfig config = new DatabaseConnectionConfig.DatabaseConnectionConfigBuilder("localhost", 3306)
                .username("admin")
                .password("admin123")
                .useSSL(true)
                .build();

        System.out.println(config);

        // Create a DatabaseConnectionConfig with default settings for username, password, and useSSL
        DatabaseConnectionConfig defaultConfig = new DatabaseConnectionConfig.DatabaseConnectionConfigBuilder("localhost", 5432)
                .build();

        System.out.println(defaultConfig);
    }
}

