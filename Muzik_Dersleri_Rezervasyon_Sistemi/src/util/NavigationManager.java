package util;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.Stack;

public class NavigationManager {

    private static final Stack<Node> history = new Stack<>();
    private static StackPane contentArea;

    public static void init(StackPane area) {
        contentArea = area;
        contentArea.setMouseTransparent(true);
    }

    public static void navigate(Node node) {
        if (contentArea == null) {
            System.out.println("NavigationManager: contentArea NULL (init çağrılmamış)");
            return;
        }

        if (contentArea.getChildren().isEmpty()) {
            history.push(null);
        } else {
            history.push(contentArea.getChildren().get(0));
        }

        contentArea.setMouseTransparent(false);
        contentArea.getChildren().setAll(node);
    }

    public static void goBack() {
        if (contentArea == null) return;

        if (history.isEmpty()) {
            contentArea.getChildren().clear();
            contentArea.setVisible(false);
            contentArea.setManaged(false);
            return;
        }

        Node prev = history.pop();

        if (prev == null) {
            contentArea.getChildren().clear();
            contentArea.setVisible(false);
            contentArea.setManaged(false);
        } else {
            contentArea.setVisible(true);
            contentArea.setManaged(true);
            contentArea.getChildren().setAll(prev);
        }
    }


    public static void clearHistory() {
        history.clear();
        if (contentArea != null) {
            contentArea.getChildren().clear();
            contentArea.setMouseTransparent(true);
        }
    }
}
