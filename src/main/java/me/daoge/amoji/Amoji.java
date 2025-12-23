package me.daoge.amoji;

import me.daoge.amoji.api.EmojiAPI;
import me.daoge.amoji.command.EmojiCommand;
import me.daoge.amoji.listener.ChatListener;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main plugin class for Amoji.
 *
 * @author daoge_cmd
 */
public class Amoji extends Plugin {

    public static Amoji instance;

    {
        instance = this;
    }

    @Override
    public void onLoad() {
        saveDefaultResources();
    }

    @Override
    public void onEnable() {
        // Load config
        var dataFolder = getPluginContainer().dataFolder();
        var mainConfig = new Config(dataFolder.resolve("config.yml").toFile(), Config.YAML);
        var autoEmoji = mainConfig.getBoolean("auto-emoji", true);

        // Load emoji list
        var emojiConfig = new Config(dataFolder.resolve("emoji.yml").toFile(), Config.YAML);
        var emojiList = new HashMap<String, String>();
        for (var entry : emojiConfig.getAll().entrySet()) {
            emojiList.put(entry.getKey(), entry.getValue().toString());
        }

        // Load emoji phrases
        var emojiPhrasesConfig = new Config(dataFolder.resolve("emoji-phrases.yml").toFile(), Config.YAML);
        var emojiPhrases = new HashMap<String, List<String>>();
        for (var entry : emojiPhrasesConfig.getAll().entrySet()) {
            var phrases = emojiPhrasesConfig.getStringList(entry.getKey());
            emojiPhrases.put(entry.getKey(), phrases != null ? phrases : new ArrayList<>());
        }

        // Init API
        EmojiAPI.initAPI(emojiList, emojiPhrases);
        EmojiAPI.setAutoEmoji(autoEmoji);

        // Register command
        Registries.COMMANDS.register(new EmojiCommand());

        // Register event listener
        Server.getInstance().getEventBus().registerListener(new ChatListener());

        getPluginLogger().info("Amoji enabled!");
    }

    @Override
    public void onDisable() {
        getPluginLogger().info("Amoji disabled!");
    }

    private void saveDefaultResources() {
        saveResource("config.yml");
        saveResource("emoji.yml");
        saveResource("emoji-phrases.yml");
    }

    private void saveResource(String resourceName) {
        var dataFolder = getPluginContainer().dataFolder();
        var targetPath = dataFolder.resolve(resourceName);

        if (Files.exists(targetPath)) {
            return;
        }

        try {
            Files.createDirectories(dataFolder);
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName)) {
                if (is != null) {
                    Files.copy(is, targetPath);
                } else {
                    getPluginLogger().warn("Resource {} not found!", resourceName);
                }
            }
        } catch (IOException e) {
            getPluginLogger().error("Failed to save resource: {}", resourceName, e);
        }
    }
}