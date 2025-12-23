package me.daoge.amoji.command;

import me.daoge.amoji.api.EmojiAPI;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.SenderType;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.form.Forms;
import org.allaymc.api.utils.TextFormat;

import java.util.List;

/**
 * Command for sending emoji.
 *
 * @author daoge_cmd
 */
public class EmojiCommand extends Command {

    public EmojiCommand() {
        super("emoji", "Send emoji!", "amoji.command.emoji");
        this.aliases.addAll(List.of("emj", "ej"));
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .exec((context, player) -> {
                    var form = Forms.simple()
                            .title(TextFormat.WHITE + "Emoji")
                            .content(TextFormat.AQUA + "Choose the emoji you want!");

                    var emojiList = EmojiAPI.getAPI().getEmojiList();
                    for (var entry : emojiList.entrySet()) {
                        var emojiId = entry.getKey();
                        var emojiName = entry.getValue();
                        var buttonText = TextFormat.WHITE + emojiName + TextFormat.DARK_GRAY + "\n" + emojiId;
                        form.button(buttonText).onClick(button -> {
                            EmojiAPI.getAPI().sendEmoji(player, emojiId);
                        });
                    }

                    form.sendTo(player.getController());
                    return context.success();
                }, SenderType.ACTUAL_PLAYER);
    }
}
