package com.dotseven.poc.service;

import org.springframework.stereotype.Service;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.dotseven.poc.api.model.Captcha;

@Service
public class CaptchaService {

    private static int TARGET_SIZE = 500;
    private static String SRC_IMGS_DIRECTORY = "./src_dataset/";
    private static String OUTPUT_DATASET_DIRECTORY = "./out_dataset/";
    private static int BUFFER_SIZE = 10;

    private ArrayList<Captcha> captchas = new ArrayList<>();

    private static void saveImage(String imageName, BufferedImage image) {
        try {
            File outputFile = new File(imageName + ".png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            System.out.println("Error while saving the image");
            e.printStackTrace();
        }
    }

    private static BufferedImage generateCombinedImage(Image backGround, Image foreGround) {

        BufferedImage bi = new BufferedImage(TARGET_SIZE, TARGET_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gi = bi.createGraphics();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

        gi.drawImage(backGround, 0, 0, TARGET_SIZE, TARGET_SIZE, null);
        gi.setComposite(ac);
        gi.drawImage(foreGround, 0, 0, TARGET_SIZE, TARGET_SIZE, null);

        gi.dispose();

        return bi;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initImagesBuffer() {
        int combinationsIndex = 0;
        int totalDigits = String.valueOf(BUFFER_SIZE).length();
        Scanner scanner = new Scanner(System.in);

        File dataset = new File(SRC_IMGS_DIRECTORY);
        int n_subjects = dataset.listFiles().length;

        Vector<Image> imageQueue = new Vector<Image>();

        for (int i = 0; i < BUFFER_SIZE; ++i) {
            Random randomGenerator = new Random(); // set seed
            int bgSubject = randomGenerator.nextInt(n_subjects);
            int fgSubject = randomGenerator.nextInt(n_subjects);

            while (fgSubject == bgSubject)
                fgSubject = randomGenerator.nextInt(n_subjects);

            int bgId = randomGenerator.nextInt(dataset.listFiles()[bgSubject].listFiles().length);
            int fgId = randomGenerator.nextInt(dataset.listFiles()[fgSubject].listFiles().length);

            BufferedImage bgImg = null;
            BufferedImage fgImg = null;
            try {
                bgImg = ImageIO.read(dataset.listFiles()[bgSubject].listFiles()[bgId]);
                fgImg = ImageIO.read(dataset.listFiles()[fgSubject].listFiles()[fgId]);
            } catch (IOException e) {
                System.err.println("Couldn't interpretate image from File");
                e.printStackTrace();
                System.exit(1);
            }

            BufferedImage img = generateCombinedImage(bgImg, fgImg);

            String imageName = "img";
            for (int missingZeros = 0; missingZeros < totalDigits
                    - String.valueOf(combinationsIndex).length(); ++missingZeros)
                imageName += "0";
            imageName += combinationsIndex;// + "_" + i + "-" + j;

            saveImage(OUTPUT_DATASET_DIRECTORY + imageName, img);
            // ADD CAPTCHA FROM BUFFER TO THE LIST
            captchas.add(new Captcha(img, dataset.listFiles()[bgSubject].getName(),
                    dataset.listFiles()[fgSubject].getName()));

            ++combinationsIndex;
            imageQueue.add(img);
            System.out.printf("%20s | %20s | %20s\n",
                    imageName,
                    dataset.listFiles()[bgSubject].getName(),
                    dataset.listFiles()[fgSubject].getName());

        }

    }

    public byte[] getImageFromBuffer() {
        File dataset = new File(OUTPUT_DATASET_DIRECTORY);
        try {
            BufferedImage image = ImageIO.read(dataset.listFiles()[0]);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();

        } catch (IOException e) {
            System.err.println("Couldn't interpretate image from File");
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public byte[] getImageFromBuffer(int i) {
        File dataset = new File(OUTPUT_DATASET_DIRECTORY);
        try {
            BufferedImage image = ImageIO.read(dataset.listFiles()[i]);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();

        } catch (IOException e) {
            System.err.println("Couldn't interpretate image from File");
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public ArrayList<byte[]> getAllImagesFromBuffer() {
        ArrayList<byte[]> imagesList = new ArrayList<>();

        for (int i = 0; i < BUFFER_SIZE; i++) {
            imagesList.add(getImageFromBuffer(i));
        }

        return imagesList;
    }

    public Captcha getCaptcha(int id) {
        return captchas.get(id);
    }
}
