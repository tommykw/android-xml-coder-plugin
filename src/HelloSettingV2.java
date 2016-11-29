import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by tommy on 2016/11/30.
 */
public class HelloSettingV2 extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Config config = Config.getInstance(e.getProject());

        if (config.isEmpty()) {
            config.init();
        }

        int count = config.getCount();
        Notifications.Bus.notify(
            new Notification("sample", "Hello Plugin!", String.format("This action has been performed %d times", count), NotificationType.INFORMATION)
        );

        config.increment();
    }
}
