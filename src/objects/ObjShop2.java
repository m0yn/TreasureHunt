package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjShop2 extends SuperObject{
    public ObjShop2() {
        name = "Shop2";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/shop2.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
