package control;

import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypeController {

    private RequestController requestController;
    private AnswerController answerController;
    private ResultController resultController;
    private StartController startController;

    public TypeController() {
        startController = new StartController();
        requestController = new RequestController();
        answerController = new AnswerController();
        resultController = new ResultController();
    }

    public String handleType(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {
        String USER_ID = "-";
        switch(header.type) {

            case Header.TYPE_START:
                startController.handleStart(header, bodyReader, outputStream);
                break;


            case Header.TYPE_REQ:
                requestController.handleRequest(header, bodyReader , outputStream);
                break;

            case Header.TYPE_ANS:
                USER_ID=answerController.handleAnswer(header, bodyReader, outputStream );
                break;

            case Header.TYPE_RES:
                resultController.handleResult(header, bodyReader, outputStream);
                break;

            case Header.TYPE_QUIT:
                return "0";

            default:
                // BadRequest 알려주는 패킷 전송 ?? 이거 뭔지 모름
                break;
        }
        return USER_ID;
    }
}
