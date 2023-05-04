package exceptions;

public class NoTrainFoundException extends RuntimeException{

    public NoTrainFoundException(String msg) {
        super(msg);
    }
}
