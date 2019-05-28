package com.blazingumbra.athena.discord;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BotContainer {
    private static Logger logger = LoggerFactory.getLogger(BotContainer.class);
    private static JDA jda;

    public static void initBot() throws IOException, LoginException {
        String token = getToken();
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListener(new Listener())
                .setGame(Game.watching("Molten Aether Freeform Roleplay Network"))
                .build();
        setJda(jda);
        logger.info("Bot brought up successfully.");
    }
    private static String getToken() throws IOException {
        File config = new File("Token.txt");
        BufferedReader reader;
        String token;
        logger.info("Looked for token-file in "+ config.getAbsolutePath());
        System.out.println(config.getAbsolutePath());
        reader = new BufferedReader(new FileReader(config));
        token = reader.readLine();
        return token;
    }

    public static JDA getJda() {
        return jda;
    }

    public static void setJda(JDA jdaa) {
        jda = jdaa;
    }
}
