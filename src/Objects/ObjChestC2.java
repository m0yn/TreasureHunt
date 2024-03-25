package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestC2 extends SuperObject{
    public ObjChestC2() {
        name = "ChestC2";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestC2.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
