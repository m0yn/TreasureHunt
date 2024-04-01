package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestC extends SuperObject{
    public ObjChestC() {
        name = "chestC";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestC.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }

}
