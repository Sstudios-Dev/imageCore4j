package org.sstudios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Core {

    private static final String imageUrl = "https://raw.githubusercontent.com/Sstudios-Dev/image-core/main/src/img/";
    private static final Random random = new Random();

    public static String[] getImageUrlFull() {
        int randomNumber = random.nextInt(70) + 1; // Assuming there are 1000 images.
        String imageName = "img-core" + randomNumber + ".jpg";
        return new String[] { imageUrl + imageName + "?raw=true", imageName };
    }

    public static void downloadRandomImage() {
        String[] imageInfo = getImageUrlFull();
        String imageUrlFull = imageInfo[0];
        String imageName = imageInfo[1];
        String directory = "./images-Random";

        try {
            if (!Files.exists(Paths.get(directory))) {
                Files.createDirectories(Paths.get(directory));
            }

            URL url = new URL(imageUrlFull);
            try (FileOutputStream fos = new FileOutputStream(directory + "/" + imageName)) {
                fos.write(url.openStream().readAllBytes());
                System.out.println("Image random " + imageName + " downloaded");
            }
        } catch (IOException e) {
            System.err.println("Failed to download image " + imageName + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        downloadRandomImage();
    }
}
