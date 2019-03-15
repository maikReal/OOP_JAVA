import java.util.Arrays;
import java.util.List;

public class StringParcer {

    /**
     * This function is parse the expression and recursively compute every operation from the expression
     *
     * @param expression string, which consist of operation/operations between VeryLong numbers
     * @return result of expression
     */
    public String getResult(String expression) {

        if (expression.split(" ").length == 1) {
            return makeOperation(expression);
        }

        int indexProb = expression.split(" ")[1].length() == 1 ? expression.indexOf(" ") + 2 : expression.indexOf(" ") + 3;

        int indexEndSlice = expression.indexOf(" ", indexProb + 1);

        if (indexEndSlice == -1) {
            return makeOperation(expression);
        } else {
            String subExpr = (String) expression.subSequence(0, indexEndSlice);
            String endExpr = (String) expression.subSequence(indexEndSlice, expression.length());

            return getResult(makeOperation(subExpr) + endExpr);
        }

    }

    /**
     * Compute the operation between the two VeryLong numbers with certain sign
     *
     * @param operation string with one operation
     * @return result of certain expression
     */
    private String makeOperation(String operation) {

        List<String> expr = Arrays.asList(operation.split(" "));

        VeryLong num1;
        VeryLong num2;

        if (expr.size() == 1) {

            num1 = operation.contains("++") ? new VeryLong(operation.substring(0, operation.length() - 2)) :
                    new VeryLong(operation.substring(0, operation.length() - 2));

            if (operation.contains("+")) {
                num1.increment();
            } else {
                num1.dicrement();
            }

            return num1.toString();

        } else {

            char lastSymbol1 = expr.get(0).charAt(expr.get(0).length() - 1);
            char lastSymbol2 = expr.get(2).charAt(expr.get(2).length() - 1);

            if ((int) lastSymbol1 < 48 && (int) lastSymbol2 < 48) {
                return makeOperation(makeOperation(expr.get(0)) + " " + expr.get(1) + " " + makeOperation(expr.get(2)));
            } else if ((int) lastSymbol1 < 48) {

                return makeOperation(makeOperation(expr.get(0)) + " " + expr.get(1) + " " + expr.get(2));

            } else if ((int) lastSymbol2 < 48) {

                return makeOperation(expr.get(0) + " " + expr.get(1) + " " + makeOperation(expr.get(2)));

            }

            num1 = new VeryLong(expr.get(0));

            num2 = new VeryLong(expr.get(2));

            switch (expr.get(1)) {
                case "+":
                    return num1.add(num2).toString();
                case "-":
                    return num1.substraction(num2).toString();
                case "/":
                    return num1.division(num2).toString();
                case "*":
                    return num1.multiply(num2).toString();
                case "+=":
                    return num1.add(num2).toString();
                case "-=":
                    return num1.substraction(num2).toString();
                case "*=":
                    return num1.multiply(num2).toString();
//            case "/=":
                case "==":
                    return String.valueOf(num1.isEqual(num2));
                case "!=":
                    return String.valueOf(num1.isNotEqual(num2));
                case ">=":
                    return String.valueOf(num1.bigger(num2, true));
                case "<=":
                    return String.valueOf(num1.less(num2, true));
                case ">":
                    return String.valueOf(num1.bigger(num2, false));
                case "<":
                    return String.valueOf(num1.less(num2, false));
            }
        }
        throw new UnsupportedOperationException();

    }
}
