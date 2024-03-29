import controller.TrazAquiController;
import exceptions.EmailJaExisteException;
import model.TrazAquiModel;

import java.io.IOException;

public class TrazAquiApp {

    public static void main(String[] args) throws EmailJaExisteException, IOException {
        TrazAquiModel model = new TrazAquiModel();
        TrazAquiController controller = new TrazAquiController(model);

        controller.run();
    }
}
