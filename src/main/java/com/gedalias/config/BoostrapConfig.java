package com.gedalias.config;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class BoostrapConfig {
    private static final String configPath = "src/main/resources/application.yaml";
    private static final Configuration configuration;
    private static HttpServer server;
    private static final Logger log;

    static {
        log = Logger.getLogger("BoostrapConfig");
        try {
            configuration = new Yaml().loadAs(new FileReader(configPath), Configuration.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private BoostrapConfig(){}

    public static HttpServer startServer() {
        if (server == null) {
            final String uri = getHost();
            final ResourceConfig rc = new ResourceConfig().packages(configuration.getRootPath());
            log.log(INFO, "Started server at {0}", uri);
            server = GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), rc);
        }
        return server;
    }
    public static void downServer() {
        if (server != null) {
            log.log(INFO, "Server down");
            server.shutdown();
        }
    }

    public static void run() {
        startServer();
        Runtime.getRuntime().addShutdownHook(new Thread(BoostrapConfig::downServer));
    }

    public static String getHost() {
        return String.format("%s://%s:%d",
                configuration.getServer().isSsl()? "https": "http",
                configuration.getServer().getHost(),
                configuration.getServer().getPort());
    }
}
