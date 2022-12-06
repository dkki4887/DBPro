package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString
public class StoreReviewDTO implements MySerializableClass {
    private int store_id;
    private String store_name;
    private String store_phone;
    private String store_address;
    private String store_time;
    private String store_info;
    private int review_rate;
    private String review_content;

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(store_id);
        dos.writeUTF(store_name);
        dos.writeUTF(store_phone);
        dos.writeUTF(store_address);
        dos.writeUTF(store_time);
        dos.writeUTF(store_info);
        dos.writeInt(review_rate);
        dos.writeUTF(review_content);
        return buf.toByteArray();
    }
}
