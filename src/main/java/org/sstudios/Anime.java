package org.sstudios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Anime {

    private static final String animeImageUrl = "https://raw.githubusercontent.com/Sstudios-Dev/image-core/main/src/img-anime/";
    private static final Random random = new Random();

    public static String[] getRandomAnimeImageUrl() {
        int randomNumber = random.nextInt(200) + 1; // Assuming there are 1000 images
        String imageName = "img-anime" + randomNumber + ".jpg";
        return new String[] { animeImageUrl + imageName + "?raw=true", imageName };
    }

    public static void downloadRandomAnimeImage() {
        String[] imageInfo = getRandomAnimeImageUrl();
        String animeImageUrlFull = imageInfo[0];
        String imageName = imageInfo[1];
        String directory = "./anime-images";

        try {
            if (!Files.exists(Paths.get(directory))) {
                Files.createDirectories(Paths.get(directory));
            }

            URL url = new URL(animeImageUrlFull);
            try (FileOutputStream fos = new FileOutputStream(directory + "/" + imageName)) {
                fos.write(url.openStream().readAllBytes());
                System.out.println("org.sstudios.Anime image " + imageName + " downloaded");
            }
        } catch (IOException e) {
            System.err.println("Failed to download image " + imageName + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        downloadRandomAnimeImage();
    }
}
