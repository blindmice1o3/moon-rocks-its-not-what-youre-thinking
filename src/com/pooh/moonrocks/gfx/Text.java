package com.pooh.moonrocks.gfx;

import java.awt.*;

public class Text {

    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos;

        // If we want it to be centered, we're going to have to change around the x and y position to draw it.
        // Normally the x and y will refer to the lower left corner... if we want centered, need x and y to refer to center.
        if (center) {
            FontMetrics fm = g.getFontMetrics(font);    // Provides us with data about the font (width/height of char, etc).

            // Modifying the x and y position that we will be drawing the text at to get a text centered around the point,
            // instead of being drawn at that point.

            // Our new x will be the position we passed in subtracted by the pixel-width of the String of text that we
            // want to draw, divided by 2.
            x = xPos - fm.stringWidth(text) / 2;

            // Ascent is a correction type value, it's actually the amount of pixels that the font should be drawn above
            // the baseline (or above the line you want to draw the text at). Height is the height of most of the char.
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }

        g.drawString(text, x, y);
    }

} // **** end Text class ****