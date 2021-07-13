package es.home.example.feignexample.customize;

public class CustomException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2878833957128656805L;

    public CustomException(final String message) {
	super(message);
    }
}
