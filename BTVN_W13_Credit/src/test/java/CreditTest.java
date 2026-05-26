import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    private final Credit credit = new Credit();

    ///  NHÓM 1

    @Test
    @DisplayName("TC_01 - BVA: age < 18 (age = 17)")
    void TC_01() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(17, 250.0, 575, "C"));
    }

    @Test
    @DisplayName("TC_02 - BVA: age > 65 (age = 66)")
    void TC_02() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(66, 250.0, 575, "C"));
    }

    @Test
    @DisplayName("TC_03 - BVA: income < 5.0 (income = 4.9)")
    void TC_03() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(40, 4.9, 575, "C"));
    }

    @Test
    @DisplayName("TC_04 - BVA: income > 500.0 (income = 500.1)")
    void TC_04() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(40, 500.1, 575, "C"));
    }

    @Test
    @DisplayName("TC_05 - BVA: credit_score < 300 (credit_score = 299)")
    void TC_05() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(40, 250.0, 299, "C"));
    }

    @Test
    @DisplayName("TC_06 - BVA: credit_score > 850 (credit_score = 851)")
    void TC_06() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(40, 250.0, 851, "C"));
    }

    @Test
    @DisplayName("TC_07 - EP: employment ngoài tập cho phép (employment = X)")
    void TC_07() {
        assertEquals(Credit.Result.INVALID_INPUT, credit.classify(40, 250.0, 575, "X"));
    }

    ///  NHÓM 2

    @Test
    @DisplayName("TC_08 - Rule 1: High Risk tại biên dưới (credit_score = 300)")
    void TC_08() {
        assertEquals(Credit.Result.REJECT, credit.classify(40, 250.0, 300, "C"));
    }

    @Test
    @DisplayName("TC_09 - Rule 1: High Risk tại biên trên (credit_score = 500)")
    void TC_09() {
        assertEquals(Credit.Result.REJECT, credit.classify(40, 250.0, 500, "F"));
    }

    @Test
    @DisplayName("TC_10 - Rule 2: Medium Risk + income < 15 tại biên dưới Medium")
    void TC_10() {
        assertEquals(Credit.Result.REJECT, credit.classify(40, 14.9, 501, "C"));
    }

    @Test
    @DisplayName("TC_11 - Rule 2: Medium Risk + income < 15 tại biên trên Medium")
    void TC_11() {
        assertEquals(Credit.Result.REJECT, credit.classify(40, 5.0, 700, "F"));
    }

    @Test
    @DisplayName("TC_12 - Rule 3: Low Risk + income < 15 + Freelance, biên dưới Low")
    void TC_12() {
        assertEquals(Credit.Result.REJECT, credit.classify(40, 14.9, 701, "F"));
    }

    @Test
    @DisplayName("TC_13 - Rule 4: Low Risk + income < 15 + Contract, biên trên Low")
    void TC_13() {
        assertEquals(Credit.Result.MANUAL_REVIEW, credit.classify(40, 5.0, 850, "C"));
    }

    @Test
    @DisplayName("TC_14 - Rule 5: Medium Risk + income tại biên 15.0 + Contract")
    void TC_14() {
        assertEquals(Credit.Result.APPROVE, credit.classify(40, 15.0, 575, "C"));
    }

    @Test
    @DisplayName("TC_15 - Rule 6: Low Risk + income tại biên trên + Contract")
    void TC_15() {
        assertEquals(Credit.Result.APPROVE, credit.classify(40, 500.0, 775, "C"));
    }

    @Test
    @DisplayName("TC_16 - Rule 7: Medium Risk + income >= 15 + Freelance")
    void TC_16() {
        assertEquals(Credit.Result.MANUAL_REVIEW, credit.classify(40, 250.0, 575, "F"));
    }

    @Test
    @DisplayName("TC_17 - Rule 8: Low Risk + income >= 15 + Freelance")
    void TC_17() {
        assertEquals(Credit.Result.MANUAL_REVIEW, credit.classify(40, 250.0, 775, "F"));
    }
}