package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjTrap2 extends SuperObject{
    public ObjTrap2() {
        name = "Trap2";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/trap2.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
