import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class HelloSetting extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Notifications.Bus.notify(
                new Notification("sample", "Hello Plugin!", String.format("This action has been performed %d times", 0), NotificationType.INFORMATION)
        );
    }
}
