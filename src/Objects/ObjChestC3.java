package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestC3 extends SuperObject{
    public ObjChestC3() {
        name = "ChestC3";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestC3.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
