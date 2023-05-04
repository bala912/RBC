package exceptions;

/**
 *  Exception thrown when input time format for departure is not valid
 *   12 Hour: "hh:mma" (h -> Hours, m -> Minutes, a -> Am/PM)
 *   24 Hour: "H", "Hmm", "HHmm" (  H -> Hours, m -> minutes )
 *
 */
public class InvalidTimeFormatException extends RuntimeException{

    public InvalidTimeFormatException(String msg) {
        super(msg);
    }
}
