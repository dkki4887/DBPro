package control;

import protocol.BodyMaker;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypeController {

    RequestController requestController;
    AnswerController answerController;
    ResultController resultController;

    public TypeController() {
        requestController = new RequestController();
        answerController = new AnswerController();
        resultController = new ResultController();
    }

    public boolean handleType(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch(header.type) {

            case Header.TYPE_START:


            case Header.TYPE_REQ:
                RequestController.handleRequest(header, bodyReader , outputStream);
                break;

            case Header.TYPE_ANS:
                AnswerController.handleAnswer(header, bodyReader, outputStream);
                break;

            case Header.TYPE_RES:
                ResultController.handleResult(header, bodyReader, outputStream);
                break;

            case Header.TYPE_QUIT:
                return false;

            default:
                // BadRequest 알려주는 패킷 전송 ?? 이거 뭔지 모름
                break;
        }

        return true;
    }
}
