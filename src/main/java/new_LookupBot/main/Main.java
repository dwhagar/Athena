package new_LookupBot.main;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8088/myapp/";

    public static JDA getTheJDA() {
        return theJDA;
    }
    private static void setTheJDA(JDA aJDA)
    {
        theJDA = aJDA;
    }

    public static JDA theJDA;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in new_LookupBot package
        final ResourceConfig rc = new ResourceConfig().packages("new_LookupBot");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        Runtime.getRuntime().addShutdownHook(new Thread(()->server.stop()));
        File config = new File("Token.txt");
        BufferedReader reader;
        String token = "";
        System.out.println(config.getAbsolutePath());
        try {
            reader = new BufferedReader(new FileReader(config));
            token = reader.readLine();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(token)
                    .addEventListener(new Listener())
                    .setGame(Game.watching("Molten Aether Freeform Roleplay Network"))
                    .build();
            setTheJDA(jda);
        } catch (LoginException e) {
            logger.error(e.toString());
        }
        logger.info("Bot brought up successfully.");
    }
}

