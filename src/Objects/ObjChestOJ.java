package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChestOJ extends SuperObject{
    public ObjChestOJ() {
        name = "ChestOJ";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/chestOJ.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
