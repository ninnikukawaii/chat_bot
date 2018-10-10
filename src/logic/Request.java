package logic;

public class Request {
    private String request;
    private Long userId;

    public Request(String request, Long userId){
        this.request = request;
        this.userId = userId;
    }

    public String getUsersRequest() {
        return request;
    }

    public Long getUserId() {
        return userId;
    }
}
