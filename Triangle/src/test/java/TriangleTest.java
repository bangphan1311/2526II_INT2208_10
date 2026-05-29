import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
    private final Triangle triangle = new Triangle();



    @Test
    @DisplayName("TC_01 - BVA: Cạnh a < 1 (a = 0)")
    void TC_01() {
        assertEquals(Triangle.Result.INVALID_INPUT, triangle.classify(0, 50, 50));
    }

    @Test
    @DisplayName("TC_02 - BVA: Cạnh a > 100 (a = 101)")
    void TC_02() {
        assertEquals(Triangle.Result.INVALID_INPUT, triangle.classify(101, 50, 50));
    }

    @Test
    @DisplayName("TC_03 - BVA: Cạnh b < 1 (b = 0)")
    void TC_03() {
        assertEquals(Triangle.Result.INVALID_INPUT, triangle.classify(50, 0, 50));
    }

    @Test
    @DisplayName("TC_04 - BVA: Cạnh c > 100 (c = 101)")
    void TC_04() {
        assertEquals(Triangle.Result.INVALID_INPUT, triangle.classify(50, 50, 101));
    }


    @Test
    @DisplayName("TC_05 - Rule 1: Tổng 2 cạnh nhỏ hơn cạnh còn lại")
    void TC_05() {
        assertEquals(Triangle.Result.NOT_A_TRIANGLE, triangle.classify(10, 20, 50));
    }

    @Test
    @DisplayName("TC_06 - Rule 1: Tổng 2 cạnh bằng cạnh còn lại (1+2=3)")
    void TC_06() {
        assertEquals(Triangle.Result.NOT_A_TRIANGLE, triangle.classify(1, 2, 3));
    }

    @Test
    @DisplayName("TC_07 - Rule 2: Tam giác đều điểm giữa")
    void TC_07() {
        assertEquals(Triangle.Result.EQUILATERAL, triangle.classify(50, 50, 50));
    }

    @Test
    @DisplayName("TC_08 - Rule 2: Tam giác đều biên lớn nhất")
    void TC_08() {
        assertEquals(Triangle.Result.EQUILATERAL, triangle.classify(100, 100, 100));
    }

    @Test
    @DisplayName("TC_09 - Rule 3: Tam giác cân a = b")
    void TC_09() {
        assertEquals(Triangle.Result.ISOSCELES, triangle.classify(50, 50, 40));
    }

    @Test
    @DisplayName("TC_10 - Rule 4: Tam giác cân b = c")
    void TC_10() {
        assertEquals(Triangle.Result.ISOSCELES, triangle.classify(40, 50, 50));
    }

    @Test
    @DisplayName("TC_11 - Rule 5: Tam giác cân a = c")
    void TC_11() {
        assertEquals(Triangle.Result.ISOSCELES, triangle.classify(50, 40, 50));
    }

    @Test
    @DisplayName("TC_12 - Rule 6: Tam giác thường cơ bản")
    void TC_12() {
        assertEquals(Triangle.Result.SCALENE, triangle.classify(3, 4, 5));
    }

    @Test
    @DisplayName("TC_13 - Rule 6: Tam giác thường áp sát biên trên")
    void TC_13() {
        assertEquals(Triangle.Result.SCALENE, triangle.classify(98, 99, 100));
    }
}