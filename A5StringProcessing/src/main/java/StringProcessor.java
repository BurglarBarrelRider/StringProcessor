public class StringProcessor {

    public boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false, hasLowerCase = false, hasDigit = false, hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            switch (Character.getType(c)) {
                case Character.UPPERCASE_LETTER:
                    hasUpperCase = true;
                    break;
                case Character.LOWERCASE_LETTER:
                    hasLowerCase = true;
                    break;
                case Character.DECIMAL_DIGIT_NUMBER:
                    hasDigit = true;
                    break;
                default:
                    if (!Character.isLetterOrDigit(c)) {
                        hasSpecialChar = true;
                    }
                    break;
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }

    public int calculateDigits(String sentence) {
        int count = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i); // по i находим символ
            if (c >= '0' && c <= '9') {
                count++;
            }
        }
        return count;
    }


    public int calculateWords(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }

        int totalWords = 0;
        boolean isInWord = false;

        for (int index = 0; index < input.length(); index++) {
            char currentChar = input.charAt(index);

            if (currentChar == ' ') {
                isInWord = false;
            } else {
                if (!isInWord) {
                    totalWords++;
                    isInWord = true;
                }
            }
        }

        return totalWords;
    }


    public double calculateExpression(String expr) {
        if (expr == null || expr.isEmpty()) return 0;

        double num = 0, total = 0;
        char prevOperator = '+';
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char currentChar = expr.charAt(i);

            if (Character.isDigit(currentChar)) {
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    num = num * 10 + (expr.charAt(i++) - '0');
                }
                i--;
            }

            if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/' || currentChar == '(' || currentChar == ')'
                    || i == expr.length() - 1) {
                switch (prevOperator) {
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': stack.push(stack.pop() * num); break;
                    case '/': stack.push(stack.pop() / num); break;
                }
                if (currentChar == '(') {
                    int j = i, brackets = 1;
                    while (brackets > 0) {
                        if (expr.charAt(++j) == '(') brackets++;
                        if (expr.charAt(j) == ')') brackets--;
                    }
                    num = calculateExpression(expr.substring(i + 1, j));
                    i = j;
                } else {
                    prevOperator = currentChar;
                    num = 0;
                }
            }
        }

        while (!stack.isEmpty()) total += stack.pop();
        return total;
    }

}
