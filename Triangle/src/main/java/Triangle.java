public class Triangle {

    public enum Result {
        INVALID_INPUT,
        NOT_A_TRIANGLE,
        EQUILATERAL,
        ISOSCELES,
        SCALENE
    }

    public Result classify(int a, int b, int c) {


        if (a < 1 || a > 100) return Result.INVALID_INPUT;
        if (b < 1 || b > 100) return Result.INVALID_INPUT;
        if (c < 1 || c > 100) return Result.INVALID_INPUT;

        if (a + b <= c || a + c <= b || b + c <= a) return Result.NOT_A_TRIANGLE;

        if (a == b && b == c) return Result.EQUILATERAL;
        if (a == b || b == c || a == c) return Result.ISOSCELES;
        return Result.SCALENE;
    }
}