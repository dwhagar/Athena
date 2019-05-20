package com.blazingumbra.athena.main;

import com.blazingumbra.athena.discord.BotContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Trying to start discord bot.");
        try {
            BotContainer.initBot();
        } catch (IOException | LoginException e) {
            logger.error(e.getMessage());
        }
    }

}
