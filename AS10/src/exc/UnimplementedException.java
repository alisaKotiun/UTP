package exc;


public final class UnimplementedException extends RuntimeException {

    private static final long serialVersionUID = -9136633471042484748L;

    public UnimplementedException(){super();}

    public UnimplementedException(Exception e) {
        super(String.valueOf(serialVersionUID), e);
    }

}
