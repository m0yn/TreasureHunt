package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjTrap1 extends SuperObject{
    public ObjTrap1() {
        name = "Trap1";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/trap1.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
