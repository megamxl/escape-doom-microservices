package at.escapedoom.player.utils;

import java.util.regex.*;
import java.util.*;

public class UsernameSanitizer {

    // Pattern to match letters only (a–z, A–Z)
    private static final Pattern LETTER_PATTERN = Pattern.compile("[a-zA-Z]");

    // Emoji regex (covers most common emojis)
    private static final Pattern EMOJI_PATTERN = Pattern.compile("[\uD83C-\uDBFF\uDC00-\uDFFF]+");

    public static String sanitizeUsername(String input) {
        if (input == null)
            return "";

        StringBuilder result = new StringBuilder();
        Matcher letterMatcher = LETTER_PATTERN.matcher(input);
        Matcher emojiMatcher = EMOJI_PATTERN.matcher(input);

        // Add letters
        while (letterMatcher.find()) {
            result.append(letterMatcher.group());
        }

        // Collect emojis
        List<String> emojis = new ArrayList<>();
        while (emojiMatcher.find()) {
            emojis.add(emojiMatcher.group());
            if (emojis.size() == 2)
                break;
        }

        // Append up to 2 emojis
        for (String emoji : emojis) {
            result.append(emoji);
        }

        return result.toString();
    }
}
