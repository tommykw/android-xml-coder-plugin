import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by tommy on 2016/12/01.
 */
public class AddDataTagIntention implements IntentionAction {

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Add <RelativeLayout> tag";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Add <RelativeLayout> tag";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        if (psiFile instanceof XmlFile) {
        } else {
            return false;
        }
        return false;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {

    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
