package logic.interfaces;

import logic.Request;

import java.io.IOException;

public interface IInput {
    Request getRequest() throws IOException;
}
