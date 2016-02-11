import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import org.json.*;

/**
 * ASCII Art Generator
 *
 * @author Huw Jones
 * @since 11/02/2016
 */
public class asciiGenerator {

    private HashMap<Integer, Character> charMap;

    public asciiGenerator (String picture) {
        if (!loadCharMap()) {
            System.err.println("Failed to load character map.\n Quitting...");
            return;
        }
    }

    private boolean loadCharMap () {
        File map = new File("map.json");
        if (!map.exists() || !map.canRead()) {
            return false;
        }
    }

    //
    private BufferedImage loadImage (String imageFile) {

    }

    // Hannah
    private BufferedImage resizeImage (BufferedImage image) {

    }

    // Jamie's Task
    private BufferedImage convertToGreyScale (BufferedImage image) {
        for (int x = 0; x < image.getWidth(); ++x)
            for (int y = 0; y < image.getHeight(); ++y) {
                int rgb = image.getRGB(x, y);
                int r = ( rgb >> 16 ) & 0xFF;
                int g = ( rgb >> 8 ) & 0xFF;
                int b = ( rgb & 0xFF );

                int grayLevel = ( r + g + b ) / 3;
                int gray = ( grayLevel << 16 ) + ( grayLevel << 8 ) + grayLevel;
                image.setRGB(x, y, gray);
            }
        return image;
    }

    // Huw's Job
    private String getASCII (BufferedImage image) {
        String picture = "";

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                float luminosity = ( image.getRGB(x, y) & 0xff ) / this.charMap.size();
                int charValue = Double.valueOf(Math.floor(luminosity)).intValue();

                picture += this.charMap.get(charValue);
            }
            picture += "\n";
        }

        return picture;
    }
}
