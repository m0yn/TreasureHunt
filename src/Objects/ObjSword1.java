package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjSword1 extends SuperObject{
    public ObjSword1() {
        name = "Sword1";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/sword1.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
