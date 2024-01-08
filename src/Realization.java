import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Realization {
    Model model = new Model();
    StackMachine stackMachine = new StackMachine();
    public Double calculate(String expression){
        History history = new History();
        history.ArrayListSaver("Primeri");
        List<Token> tokens=model.getTokens(expression);
        history.save(tokens);
        var postfixExpression = model.convertToPostfix(tokens);
        var result = stackMachine.evaluate(postfixExpression);
        System.out.println(result);
        return result;
    }

}
