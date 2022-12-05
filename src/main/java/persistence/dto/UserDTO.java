package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.session.ResultHandler;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString
public class UserDTO implements MySerializableClass{
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_address;
    private String user_phone;
    private int user_category;

    public UserDTO(){};

    public UserDTO(String user_id, String user_pw, String user_name, String user_address, String user_phone, int user_category)
    {
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.user_category = user_category;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUser_category() {
        return user_category;
    }

    public void setUser_category(int user_category) {
        this.user_category = user_category;
    }

    public UserDTO read(DataInputStream bodyReader) throws IOException {
        user_id = bodyReader.readUTF();
        user_pw = bodyReader.readUTF();
        user_name = bodyReader.readUTF();
        user_address = bodyReader.readUTF();
        user_phone = bodyReader.readUTF();
        user_category = bodyReader.readInt();

        return this;
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(user_id);
        dos.writeUTF(user_pw);
        dos.writeUTF(user_name);
        dos.writeUTF(user_address);
        dos.writeUTF(user_phone);
        dos.writeInt(user_category);
        return buf.toByteArray();
    }
}
