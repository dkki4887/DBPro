package persistence.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Alias("orderMenuList")

public class OrderMenuDTO implements MySerializableClass {
    private String orderMenu_id;
    private String menu_name;
    private String order_num;

    public OrderMenuDTO(String orderMenu_id, String order_num, String menu_name)
    {
        this.orderMenu_id = orderMenu_id;
        this.order_num = order_num;
        this.menu_name = menu_name;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getOrderMenu_id() {
        return orderMenu_id;
    }

    public void setOrderMenu_id(String orderMenu_id) {
        this.orderMenu_id = orderMenu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public static OrderMenuDTO read(DataInputStream bodyReader) throws IOException
    {
        String orderMenu_id = bodyReader.readUTF();
        String menu_name = bodyReader.readUTF();
        String order_num = bodyReader.readUTF();

        return new OrderMenuDTO(orderMenu_id, order_num, menu_name);
    }


    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(orderMenu_id);
        dos.writeUTF(menu_name);
        dos.writeUTF(order_num);
        return buf.toByteArray();
    }
}
