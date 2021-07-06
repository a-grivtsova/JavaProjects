package filter.edge;

import filter.Filter;

public interface EdgeDetection extends Filter {
    int MIN_THRESHOLD = 0;
    int MAX_THRESHOLD = 100;
    int THRESHOLD_LENGTH = MAX_THRESHOLD - MIN_THRESHOLD;
    int DEFAULT_THRESHOLD = 20;

    void setThreshold(int threshold);

    void setInverted(boolean value);
}