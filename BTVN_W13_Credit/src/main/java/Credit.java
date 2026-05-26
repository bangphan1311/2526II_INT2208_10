public class Credit {
    public enum Result {
        INVALID_INPUT,
        REJECT,
        MANUAL_REVIEW,
        APPROVE
    }

    public Result classify(int age, double income, int creditScore, String employment) {

        if (age < 18 || age > 65) return Result.INVALID_INPUT;
        if (income < 5.0 || income > 500.0) return Result.INVALID_INPUT;
        if (creditScore < 300 || creditScore > 850) return Result.INVALID_INPUT;
        if (!"C".equals(employment) && !"F".equals(employment)) return Result.INVALID_INPUT;

        String risk;
        if (creditScore >= 300 && creditScore <= 500) risk = "HIGH";
        else if (creditScore >= 501 && creditScore <= 700) risk = "MEDIUM";
        else risk = "LOW";


        //Rule 1
        if ("HIGH".equals(risk)) return Result.REJECT;

        //Rule 2
        if ("MEDIUM".equals(risk) && income < 15.0) return Result.REJECT;

        //Rule 3
        if ("LOW".equals(risk) && income < 15.0 && "F".equals(employment)) return Result.REJECT;

        //Rule 4
        if ("LOW".equals(risk) && income < 15.0 && "C".equals(employment)) return Result.MANUAL_REVIEW;

        //Rule 5
        if ("MEDIUM".equals(risk) && income >= 15.0 && "C".equals(employment)) return Result.APPROVE;

        //Rule 6
        if ("LOW".equals(risk) && income >= 15.0 && "C".equals(employment)) return Result.APPROVE;

        //Rule 7
        if ("MEDIUM".equals(risk) && income >= 15.0 && "F".equals(employment)) return Result.MANUAL_REVIEW;

        //Rule 8
        return Result.MANUAL_REVIEW;
    }
}
