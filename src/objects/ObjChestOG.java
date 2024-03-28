package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestOG extends SuperObject{
    public ObjChestOG() {
        name = "ChestOG";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestOG.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
