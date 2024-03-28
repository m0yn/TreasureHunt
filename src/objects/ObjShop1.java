package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjShop1 extends SuperObject{
    public ObjShop1() {
        name = "Shop1";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/shop1.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
