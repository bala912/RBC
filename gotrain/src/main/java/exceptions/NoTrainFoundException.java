package exceptions;

/**
 *  Exception thrown when No trains exist for the Given line
 */
public class NoTrainFoundException extends RuntimeException{

    public NoTrainFoundException(String msg) {
        super(msg);
    }
}
