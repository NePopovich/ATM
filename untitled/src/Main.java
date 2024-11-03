import controllers.StartController;
import model.Card;
import service.CardService;

public class Main {
    public static void main(String[] args) {
        StartController.showStartMenu();
        StartController.actions(CardService.getCard(new Card(159753, 2589)));


    }
}