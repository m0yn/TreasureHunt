package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjCastle extends SuperObject {
    public ObjCastle() {
        name = "castle";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/castle.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
