package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjShop3 extends SuperObject {
    public ObjShop3() {
        name = "Shop3";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/shop3.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
