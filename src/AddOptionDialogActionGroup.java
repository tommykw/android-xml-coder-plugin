import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by tommy on 2016/12/13.
 */
public class AddOptionDialogActionGroup extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Notifications.Bus.notify(
                new Notification("sample", "Hello Plugin!", "Hello! This is Sample Plugin.", NotificationType.INFORMATION)
        );

    }
}
