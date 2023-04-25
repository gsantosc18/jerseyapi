package com.gedalias;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.gedalias.config.BoostrapConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResourceTest {

    private HttpServer server;
    private WebTarget target;

    @BeforeEach
    public void setUp() {
        // start the server
        server = BoostrapConfig.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(BoostrapConfig.getHost());
    }

    @AfterEach
    public void tearDown(){
        server.shutdown();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("user").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
