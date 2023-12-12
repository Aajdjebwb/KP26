package book.kp26.data.concurency;

public class Fault {
    public final Throwable exception;

    public Fault(Throwable exception) {
        this.exception = exception;
    }

    public Fault(String errorMessage)
    {
        this.exception=new Throwable((errorMessage));
    }
}
