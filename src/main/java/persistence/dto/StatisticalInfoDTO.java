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

public class StatisticalInfoDTO implements MySerializableClass {
    private int store_id;
    private String store_name;
    private String menu_name;
    private long sum_order_price;
    private int count_order;

    public StatisticalInfoDTO(int store_id, String store_name, String menu_name, long sum_order_price, int count_order)
    {
        this.store_id = store_id;
        this.store_name = store_name;
        this.menu_name = menu_name;
        this.sum_order_price = sum_order_price;
        this.count_order = count_order;
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeInt(store_id);
        dos.writeUTF(store_name);
        dos.writeUTF(menu_name);
        dos.writeLong(sum_order_price);
        dos.writeInt(count_order);
        return buf.toByteArray();
    }
}
