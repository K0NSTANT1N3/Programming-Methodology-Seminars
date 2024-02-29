package exams.finaleExamProblem.version1.gallery;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ImageGallery extends GraphicsProgram {
    public static final int IMG_H = 200;
    public static final int IMG_DST = 20;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<GImage> pictures = new ArrayList<>();
    private boolean pictureZoomed = false;
    private GImage curImg = null;

    public void run() {
        addMouseListeners();

        createImageObjects();
        drawGreed();
    }

    private void zoomImage(GImage img){
        removeAll();

        img.setSize(getWidth() - IMG_DST * 2, getHeight() - IMG_DST * 2);
        add(img, IMG_DST, IMG_DST);
        curImg = img;
    }

    private void createImageObjects(){
        for(String str : images){
            GImage img = new GImage(str);
            pictures.add(img);
        }
    }

    private void drawGreed() {
        removeAll();
        int numWidth = getWidth() / (IMG_DST + IMG_H);
        int numHeight = (images.size() + numWidth - 1) / numWidth;

        for (int i = 0, indx = 0; i < numHeight || indx < pictures.size(); i++) {
            for(int j = 0; j < numWidth || indx < pictures.size(); j++, indx++){
                double x = IMG_DST + (IMG_H + IMG_DST) * j;
                double y = IMG_DST + (IMG_H + IMG_DST) * i;

                add(pictures.get(indx), x, y);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!pictureZoomed){
            if(getElementAt(e.getX(), e.getY()) instanceof GImage img){
                zoomImage(img);
            }
        }else{
            if(e.getX() < getWidth() / 3){
                int newImageIndex = pictures.indexOf(curImg) - 1;
                if(newImageIndex <= -1)newImageIndex = pictures.size() - 1;

                GImage newImage = pictures.get(newImageIndex);
                zoomImage(newImage);
            } else if (e.getX() > getWidth() * 2 / 3) {
                int newImageIndex = pictures.indexOf(curImg) + 1;
                if(newImageIndex >= pictures.size())newImageIndex = 0;

                GImage newImage = pictures.get(newImageIndex);
                zoomImage(newImage);
            } else if (e.getX() >= getWidth() / 3 && e.getX() <= getWidth() * 2 / 3) {
                drawGreed();
            }
        }
    }
}
