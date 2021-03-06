package filter.affine;

import controller.FilterAppliedObserver;

public class Sharpening extends MatrixFilter {
    private static final Matrix MATRIX = Matrix.of(
             0, -1,  0,
            -1,  5, -1,
             0, -1,  0
    );

    public Sharpening(FilterAppliedObserver observer) {
        super(observer);
        setFilterMatrix(MATRIX);
    }
}
