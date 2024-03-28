package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjShield1 extends SuperObject{
    public ObjShield1() {
        name = "Shield1";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/shield1.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
