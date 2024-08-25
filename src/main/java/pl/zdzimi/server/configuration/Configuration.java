package pl.zdzimi.server.configuration;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

    static {
        CONFIGURATION = setConfiguration();
    }

    public static final Configuration CONFIGURATION;

    private final int port;
    private final String root;

    private Configuration(int port, String root) {
        this.port = port;
        this.root = root;
    }

    public int getPort() {
        return port;
    }

    public String getRoot() {
        return root;
    }

    private static Configuration setConfiguration() {
        Properties properties = new Properties();
        int port = 80;
        String root = "example";
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("configuration.properties"));
            port = Integer.parseInt(properties.getProperty("PORT"));
            root = properties.getProperty("ROOT");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Configuration(port, root);
    }

}
