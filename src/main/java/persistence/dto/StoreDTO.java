package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreDTO implements MySerializableClass {
    private int store_id;
    private String user_id; //점주 누군지 확인용
    private String store_name;
    private String store_phone;
    private String store_address;
    private boolean store_state;
    private int store_category;
    private int store_rate;
    private String store_time;
    private String store_info;
    private boolean store_ack;


    public StoreDTO() {
    }

    public StoreDTO(int store_id, String user_id, String store_name, String store_phone, String store_address, boolean store_state, int store_category, int store_rate, String store_time, String store_info, boolean store_ack) {
        this.store_id = store_id;
        this.user_id = user_id; //점주 누군지 확인용
        this.store_name = store_name;
        this.store_phone = store_phone;
        this.store_address = store_address;
        this.store_state = store_state;
        this.store_category = store_category;
        this.store_rate = store_rate;
        this.store_time = store_time;
        this.store_info = store_info;
        this.store_ack = store_ack;
    }

    public static StoreDTO read(DataInputStream bodyReader) throws IOException {
        int store_id = bodyReader.readInt();
        String user_id = bodyReader.readUTF();
        String store_name = bodyReader.readUTF();
        String store_phone = bodyReader.readUTF();
        String store_address = bodyReader.readUTF();
        boolean store_state = bodyReader.readBoolean();
        int store_category = bodyReader.readInt();
        int store_rate = bodyReader.readInt();
        String store_time = bodyReader.readUTF();
        String store_info = bodyReader.readUTF();
        boolean store_ack = bodyReader.readBoolean();
        StoreDTO storeDTO = new StoreDTO(store_id, user_id, store_name, store_phone, store_address, store_state, store_category, store_rate, store_time, store_info, store_ack);
        return storeDTO;
    }

    public static void print(StoreDTO storeDTO)
    {
        System.out.println("가게 아이디 : " + storeDTO.getStore_id() + "| 가게 이름 : " + storeDTO.getStore_name() + "| 가게 전화 : " + storeDTO.getStore_phone()
                + "| 가게 주소 : " + storeDTO.getStore_address() + "| 가게 상태 : " + storeDTO.getStore_state() + "| 가게 시간 : " + storeDTO.getStore_time()
                + "| 가게 소개 : " + storeDTO.getStore_info() + "| 가게 소유주 : " + storeDTO.getUser_id());
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public boolean getStore_state() {
        return store_state;
    }

    public void setStore_state(boolean store_state) {
        this.store_state = store_state;
    }

    public int getStore_category() {
        return store_category;
    }

    public void setStore_category(int store_category) {
        this.store_category = store_category;
    }

    public int getStore_rate() {
        return store_rate;
    }

    public void setStore_rate(int store_rate) {
        this.store_rate = store_rate;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }

    public String getStore_info() {
        return store_info;
    }

    public void setStore_info(String store_info) {
        this.store_info = store_info;
    }

    public boolean getStore_ack()
    {
        return store_ack;
    }

    public void setStore_ack(boolean store_ack)
    {
        this.store_ack = store_ack;
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(store_id);
        dos.writeUTF(user_id);
        dos.writeUTF(store_name);
        dos.writeUTF(store_phone);
        dos.writeUTF(store_address);
        dos.writeBoolean(store_state);
        dos.writeInt(store_category);
        dos.writeInt(store_rate);
        dos.writeUTF(store_time);
        dos.writeUTF(store_info);
        dos.writeBoolean(store_ack);
        return buf.toByteArray();
    }
}