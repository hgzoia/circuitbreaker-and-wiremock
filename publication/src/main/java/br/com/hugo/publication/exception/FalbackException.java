package br.com.hugo.publication.exception;


public class FalbackException extends RuntimeException{

    public FalbackException(Throwable cause){
        super("Service unavailable.", cause);
    }

}
