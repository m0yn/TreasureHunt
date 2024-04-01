package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjCastle3 extends SuperObject {
    public ObjCastle3() {
        name = "Castle3";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/castle3.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }

}
