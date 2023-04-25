package com.gedalias.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Configuration {
    private Server server;
    private String rootPath;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Server {
        private String host;
        private int port;
        private boolean ssl;
    }
}
