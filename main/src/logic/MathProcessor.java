package logic;

import logic.handlers.MathHandler;
import logic.interfaces.Processor;

import java.util.List;

public class MathProcessor implements Processor {
    private final MathHandler handler;
    private String request;

    public MathProcessor(String request, MathHandler handler) {
        this.request = request;
        this.handler = handler;
    }

    @Override
    public List<String> requestProcessing(User user) {
        return handler.getResponse(request);
    }
}
