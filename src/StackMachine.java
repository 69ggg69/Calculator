import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class StackMachine {
    public Double evaluate(List<Token> postfixExpression){
       Deque<Double> valueStack = new LinkedList<>();

        for (Token token:postfixExpression){
            if(token instanceof TokenRepository.NumberToken numberToken){
                //valueStack.push(numberToken.value());
                valueStack.push(numberToken.value());
            } else if (token instanceof TokenRepository.Operation operation) {
                var right = valueStack.pop();
                var left = valueStack.pop();
                var result = switch (operation.operationType()) {
                    case PLUS -> left + right;
                    case MINUS -> left - right;
                    case UMNOZHENIE -> left * right;
                    case LOG -> Math.log(left) / (right);
                    case MODUL -> Math.abs(left);
                    case STEPEN -> Math.pow(left, right);
                    case DELENIE -> {
                        if (right == 0) {
                            throw new RuntimeException("Uchi mateshu");
                        }
                    yield left / right;
                }
            };
                valueStack.push(result);

            }
        }
        return valueStack.pop();

    }
}
