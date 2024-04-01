package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestC4 extends SuperObject{
    public ObjChestC4() {
        name = "ChestC4";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestC4.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
