package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestO extends SuperObject{
    public ObjChestO() {
        name = "ChestCO";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestO.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
