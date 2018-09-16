package logic;

public class Request {
    private String mRequest;

    public Request(String request){
        mRequest = request.toLowerCase();
    }

    public String getUsersRequest() {
        return mRequest;
    }
}
