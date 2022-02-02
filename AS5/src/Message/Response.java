package Message;

public class Response extends Message{

    private String response;
    public Response(Request r) {
        super(r.getRequester(), r.getPriority());
        response = r.getString().toUpperCase();
    }

    public String getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return  "Response" + "["+getId()+ "][" + response + "]";
    }
}
