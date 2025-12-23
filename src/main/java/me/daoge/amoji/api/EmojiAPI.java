package me.daoge.amoji.api;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.particle.CustomParticle;
import org.joml.Vector3d;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * API for handling emoji display in the game.
 *
 * @author daoge_cmd
 */
public final class EmojiAPI {

    private static final Map<String, double[]> EMOJI_POS = new HashMap<>();
    private static final Map<String, List<String>> EMOJI_PHRASES = new HashMap<>();

    static {
        EMOJI_POS.put("smiley", new double[]{0, 0});
        EMOJI_POS.put("grimacing", new double[]{0.01, 0});
        EMOJI_POS.put("grin", new double[]{0.02, 0});
        EMOJI_POS.put("joy", new double[]{0.03, 0});
        EMOJI_POS.put("smile", new double[]{0.04, 0});
        EMOJI_POS.put("sweat_smile", new double[]{0.05, 0});
        EMOJI_POS.put("laughing", new double[]{0.06, 0});
        EMOJI_POS.put("innocent", new double[]{0.07, 0});
        EMOJI_POS.put("wink", new double[]{0, 0.01});
        EMOJI_POS.put("blush", new double[]{0.01, 0.01});
        EMOJI_POS.put("slight_smile", new double[]{0.02, 0.01});
        EMOJI_POS.put("upside_down", new double[]{0.03, 0.01});
        EMOJI_POS.put("relaxed", new double[]{0.04, 0.01});
        EMOJI_POS.put("yum", new double[]{0.05, 0.01});
        EMOJI_POS.put("relieved", new double[]{0.06, 0.01});
        EMOJI_POS.put("heart_eyes", new double[]{0.07, 0.01});
        EMOJI_POS.put("kissing_heart", new double[]{0, 0.02});
        EMOJI_POS.put("kissing", new double[]{0.01, 0.02});
        EMOJI_POS.put("kissing_smiling_eyes", new double[]{0.02, 0.02});
        EMOJI_POS.put("kissing_closed_eyes", new double[]{0.03, 0.02});
        EMOJI_POS.put("stuck_out_tongue_winking_eye", new double[]{0.04, 0.02});
        EMOJI_POS.put("stuck_out_tongue_closed_eyes", new double[]{0.05, 0.02});
        EMOJI_POS.put("stuck_out_tongue", new double[]{0.06, 0.02});
        EMOJI_POS.put("money_mouth", new double[]{0.07, 0.02});
        EMOJI_POS.put("sunglasses", new double[]{0, 0.03});
        EMOJI_POS.put("smirk", new double[]{0.01, 0.03});
        EMOJI_POS.put("no_mouth", new double[]{0.02, 0.03});
        EMOJI_POS.put("neutral_face", new double[]{0.03, 0.03});
        EMOJI_POS.put("expressionless", new double[]{0.04, 0.03});
        EMOJI_POS.put("unamused", new double[]{0.05, 0.03});
        EMOJI_POS.put("rolling_eyes", new double[]{0.06, 0.03});
        EMOJI_POS.put("flushed", new double[]{0.07, 0.03});
        EMOJI_POS.put("disappointed", new double[]{0, 0.04});
        EMOJI_POS.put("worried", new double[]{0.01, 0.04});
        EMOJI_POS.put("angry", new double[]{0.02, 0.04});
        EMOJI_POS.put("rage", new double[]{0.03, 0.04});
        EMOJI_POS.put("pensive", new double[]{0.04, 0.04});
        EMOJI_POS.put("confused", new double[]{0.05, 0.04});
        EMOJI_POS.put("slight_frown", new double[]{0.06, 0.04});
        EMOJI_POS.put("frowning2", new double[]{0.07, 0.04});
    }

    @Getter
    private static EmojiAPI API;
    @Getter
    private final Map<String, String> emojiList;
    @Getter
    @Setter
    private static boolean autoEmoji = true;

    private EmojiAPI(Map<String, String> emojiList) {
        API = this;
        for (var id : EMOJI_POS.keySet()) {
            var name = emojiList.getOrDefault(id, id);
            emojiList.put(id, name);
        }
        this.emojiList = emojiList;
    }

    /**
     * Initialize the API with the emoji list.
     *
     * @param emojiList       the emoji list (id -> name mapping)
     * @param emojiPhrasesMap the emoji phrases map (id -> list of phrases)
     */
    public static void initAPI(Map<String, String> emojiList, Map<String, List<String>> emojiPhrasesMap) {
        if (API == null) {
            API = new EmojiAPI(emojiList);
            EMOJI_PHRASES.clear();
            EMOJI_PHRASES.putAll(emojiPhrasesMap);
        }
    }

    /**
     * Send an emoji particle effect above the entity.
     *
     * @param entity  the entity to display emoji above
     * @param emojiId the emoji id
     */
    public void sendEmoji(Entity entity, String emojiId) {
        if (emojiId == null || !EMOJI_POS.containsKey(emojiId)) {
            return;
        }

        var viewers = entity.getViewers();
        if (viewers.isEmpty() && !(entity instanceof EntityPlayer)) {
            return;
        }

        double[] pos = EMOJI_POS.get(emojiId);
        var moLangVariables = "[{\"name\":\"variable.ix\",\"value\":{\"type\":\"float\",\"value\":" +
                pos[0] +
                "}},{\"name\":\"variable.iy\",\"value\":{\"type\":\"float\",\"value\":" +
                pos[1] +
                "}}]";

        var particle = new CustomParticle("emoji:emoji", moLangVariables);
        var entityLoc = entity.getLocation();
        var entityHeight = entity.getAABB().maxY() - entity.getAABB().minY();
        var particlePos = new Vector3d(entityLoc.x(), entityLoc.y() + entityHeight + 1, entityLoc.z());

        entity.getDimension().addParticle(particlePos, particle);
    }

    /**
     * Get the emoji id from the phrase.
     *
     * @param phrase the phrase to check
     * @return the emoji id, or null if no emoji phrase is found
     */
    public String getPhraseEmoji(String phrase) {
        phrase = phrase.toLowerCase(Locale.ROOT);
        for (Map.Entry<String, List<String>> entry : EMOJI_PHRASES.entrySet()) {
            for (String emojiPhrase : entry.getValue()) {
                if (phrase.contains(emojiPhrase)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}
