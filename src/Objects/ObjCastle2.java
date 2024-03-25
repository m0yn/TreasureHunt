package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjCastle2 extends SuperObject {
    public ObjCastle2() {
        name = "castle2";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/castle2.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
