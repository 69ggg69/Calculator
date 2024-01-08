import java.math.BigDecimal;
import java.util.*;

public class Model extends TokenRepository {
    public static final String ZNAKI = " +-/*()l|^";
    public List<Token> getTokens(String source){
        var tokenizer = new StringTokenizer(source, ZNAKI, true);
        List<Token> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()){
            var token = tokenizer.nextToken();
            if (token.isBlank()){
                continue;
            } else if (isNumber(token)) {
                tokens.add(new NumberToken(Double.parseDouble(token)));
                continue;
            }
            tokens.add(switch (token){
                case "+" -> new Operation(OperationType.PLUS);
                case "-" -> new Operation(OperationType.MINUS);
                case "/" -> new Operation(OperationType.DELENIE);
                case "*" -> new Operation(OperationType.UMNOZHENIE);
                case "(" -> new OtherToken(TokenType.OPEN_SKOBKA);
                case ")" -> new OtherToken(TokenType.CLOSE_SKOBKA);
                case "l" -> new Operation(OperationType.LOG);
                case "|" -> new Operation(OperationType.MODUL);
                case "^" -> new Operation(OperationType.STEPEN);
                default -> throw new IllegalStateException("Unexpected value: " + token);
            });
        }
        return tokens;
    }
    public static boolean isNumber(String token) {
        for(int i=0; i<token.length(); i++){
            return token.matches("-?\\d+(\\.\\d+)?");
    }
        return true;
    }

    public List<Token> convertToPostfix(List<Token> source){
        List<Token> postfixExpression = new ArrayList<>();
        Deque<Token> operationStack = new LinkedList<>();
        for (Token token:source){
            switch (token.type()){
                case NUMBER -> postfixExpression.add(token);
                case OPEN_SKOBKA -> operationStack.push(token);
                case CLOSE_SKOBKA -> {
                    while (!operationStack.isEmpty() && operationStack.peek().type() !=TokenType.OPEN_SKOBKA){
                        postfixExpression.add(operationStack.pop());
                    }
                    operationStack.pop();
                }
                case OPERATION -> {
                    while (!operationStack.isEmpty() && getPriority(operationStack.peek()) >= getPriority(token)
                    ) {
                        postfixExpression.add(operationStack.pop());
                    }
                    operationStack.push(token);
                }
            }

        }

        while (!operationStack.isEmpty()){
            postfixExpression.add(operationStack.pop());
        }
        return postfixExpression;
    } private int getPriority(Token token) {
        if (token instanceof Operation operation) {
            return switch (operation.operationType()){
                case PLUS, MINUS -> 1;
                case DELENIE, UMNOZHENIE, LOG, MODUL, STEPEN -> 2;
            };
        }
        return 0;
    }

}
    enum TokenType{
        OPERATION,
        NUMBER,
        OPEN_SKOBKA,
        CLOSE_SKOBKA
    }





