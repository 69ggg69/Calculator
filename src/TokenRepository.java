import java.math.BigDecimal;

public class TokenRepository {
    public enum OperationType {
        PLUS,
        MINUS,
        DELENIE,
        UMNOZHENIE,
        LOG,
        MODUL,
        STEPEN
    }
    public record Operation(OperationType operationType) implements Token{
        @Override
        public TokenType type() {
            return TokenType.OPERATION;
        }
    }
    public record NumberToken(Double value) implements Token{
        @Override
        public TokenType type() {
            return TokenType.NUMBER;
        }

    }
    public record OtherToken(TokenType type) implements Token{
    }

}
