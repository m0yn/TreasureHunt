package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjTrap3 extends SuperObject{
    public ObjTrap3() {
        name = "Trap3";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/items/trap3.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
