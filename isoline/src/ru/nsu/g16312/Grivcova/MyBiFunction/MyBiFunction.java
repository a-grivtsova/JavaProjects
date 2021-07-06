package ru.nsu.g16312.Grivcova.MyBiFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import ru.nsu.g16312.Grivcova.View.IsolinePlotter;

public class MyBiFunction {
    private FunctionDomain xDomain = new FunctionDomain(-6.28f, 6.28f), yDomain = new FunctionDomain(-6.28f, 6.28f);
    private int k = 17, m = 17;
    private float dx, dy;
    private GridNode[][] grid = new GridNode[m][k];

    private static Map<FunctionType, BiFunction<Float, Float, Float>> functionMapping = new HashMap<>();
    {
        functionMapping.put(FunctionType.HYPERBOLA, (x, y) -> (x*y));
        functionMapping.put(FunctionType.SPHERE, (x, y) -> (x*x + y*y - 1));
        functionMapping.put(FunctionType.SIN_AND_COS, (x, y) -> (float)(Math.sin(x) * Math.cos(y)));
    }

    private FunctionType type = FunctionType.HYPERBOLA;
    private BiFunction<Float, Float, Float> function = functionMapping.get(type) ;

    private void computeGrid() {
        dx = (xDomain.b - xDomain.a) / (k - 1);
        dy = (yDomain.b - yDomain.a) / (m - 1);

        float curX = xDomain.a, curY = yDomain.a;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                grid[i][j] = new GridNode(curX, curY, function.apply(curX, curY));
                curX += dx;
            }
            curX = xDomain.a;
            curY += dy;
        }
    }

    public MyBiFunction() 
    {
        computeGrid();
    }

    public MyBiFunction(FunctionType type, float xMin, float xMax, float yMin, float yMax, int k, int m) {
        xDomain = new FunctionDomain(xMin, xMax);
        yDomain = new FunctionDomain(yMin, yMax);
        this.k = k;
        this.m = m;
        grid = new GridNode[m][k];
        this.type = type;
        function = functionMapping.get(type);
        computeGrid();
    }

    public BiFunction<Float, Float, Float> getOriginalFunction() {
        return function;
    }

    public void resizeGrid(int newK, int newM) {
        m = newM;
        k = newK;
        grid = new GridNode[m][k];
        computeGrid();
    }

    public float myapply(float x, float y) {
        int j = (int) Math.floor(Math.abs((x - xDomain.a)) / dx);
        int i = (int) Math.floor(Math.abs((y - yDomain.a)) / dy);
        GridNode node11, node12, node21, node22;

        int i2 = i + 1;
        int j2 = j + 1;
        if (i + 1 > m) {
            i2 = i;
            i = i - 1;
        }
        if (j + 1 > k) {
            j2 = j;
            j = j - 1;
        }
        node11 = grid[i][j];
        node12 = grid[i2][j];
        node21 = grid[i][j2];
        node22 = grid[i2][j2];

        float result = 0;
        float commonDivisor = (node22.x - node11.x) * (node22.y - node11.y);

        float r1 = (node11.z * (node22.x - x) * (node22.y - y)) / commonDivisor;
        float r2 = (node21.z * (-node11.x + x) * (node22.y - y)) / commonDivisor;
        float r3 = (node12.z * (node22.x - x) * (-node11.y + y)) / commonDivisor;
        float r4 = (node22.z * (-node11.x + x) * (-node11.y + y)) / commonDivisor;

        result = r1 + r2 + r3 + r4;

        return result;
    }

    public FunctionType getFunctionType() {
        return type;
    }

    public FunctionDomain getxDomain() {
        return xDomain;
    }

    public FunctionDomain getyDomain() {
        return yDomain;
    }

    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public GridNode[][] getGrid() {
        return grid;
    }
}

