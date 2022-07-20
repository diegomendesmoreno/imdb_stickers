import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickerGenerator {

    public void create(InputStream inputStream, String imageFileName, String commentOnImage) throws IOException {
        // Fixed factor to which extend image height
        final double MULT_FACTOR = 0.2;

        // Read image
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Create a taller transparent image (in memory)
        int originalImageWidth = originalImage.getWidth();
        int originalImageHeight = originalImage.getHeight();
        int newImageWidth = originalImageWidth;
        int newImageHeight = (int) (originalImageHeight * MULT_FACTOR) + originalImageHeight;
        BufferedImage newImage = new BufferedImage(originalImageWidth, newImageHeight, BufferedImage.TRANSLUCENT);
    
        // Copy original image in the top of the new image (in memory)
        Graphics2D newImageGraphics = (Graphics2D) newImage.getGraphics();
        newImageGraphics.drawImage(originalImage, 0, 0, null);

        // Configure font
        int fontSize = (int) (originalImageHeight * MULT_FACTOR/2);
        var font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);
        newImageGraphics.setFont(font);
        newImageGraphics.setColor(Color.YELLOW);
        
        // Configure Text position
        int textPosX = (int) (newImageWidth/2) - (int) (newImageGraphics.getFontMetrics().stringWidth(commentOnImage)/2);
        int textPosY = newImageHeight - (int) (originalImageHeight * MULT_FACTOR/2) + (int) (fontSize/2 - fontSize*0.2);
        
        // Write a comment at the bottom
        newImageGraphics.drawString(commentOnImage, textPosX, textPosY);
    
        // Write image to file
        String outputPath = "output_images/";
        var newFile = new File(outputPath + imageFileName);
        if(newFile.exists() == false) {
            newFile.mkdirs();
        }
        else {
            newFile.createNewFile();
        }
        ImageIO.write(newImage, "png", newFile);
    }

    public static void main(String[] args) throws IOException {

        // Generate Sticker Images
        InputStream inputStream = 
            new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
                .openStream();
        String movieComment = "IRADO!";
        String imageFileName = "outputImage" + ".png";
        
        StickerGenerator generator = new StickerGenerator();
        generator.create(inputStream, imageFileName, movieComment);
    }
}
