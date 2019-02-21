package com.pooh.moonrocks.gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;

// Going to act similar to the ImageLoader class. Catch-clause is a since-Java-7 format for catching multiple Exceptions.
public class FontLoader {

    public static Font loadFont(String path, float size) {

        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null;

    }

} // **** end FontLoader class ****