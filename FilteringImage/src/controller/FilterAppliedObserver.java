package controller;

import java.awt.image.BufferedImage;

public interface FilterAppliedObserver {
    void onFilterApplied(BufferedImage image);
}
