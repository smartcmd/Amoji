package me.daoge.amoji.listener;

import me.daoge.amoji.api.EmojiAPI;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerChatEvent;
import org.allaymc.api.utils.TextFormat;

/**
 * Listener for player chat events.
 *
 * @author daoge_cmd
 */
public class ChatListener {

    @EventHandler(priority = Integer.MIN_VALUE)
    private void onChat(PlayerChatEvent event) {
        if (event.isCancelled() || !EmojiAPI.isAutoEmoji()) {
            return;
        }

        var player = event.getPlayer();
        var message = TextFormat.clean(event.getMessage());
        var emojiId = EmojiAPI.getAPI().getPhraseEmoji(message);

        EmojiAPI.getAPI().sendEmoji(player, emojiId);
    }
}
