import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlElementFactory;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tommy on 2016/12/01.
 */
public class AddTagAction implements IntentionAction {
    ＠Nls
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
        if (!(psiFile instanceof XmlFile)) return false;
        XmlTag xmlTag = ((XmlFile) psiFile).getRootTag();
        if (xmlTag == null) return false;
        return xmlTag.getName() == "LinearLayout";
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        if (!(psiFile instanceof XmlFile)) return;
        XmlTag newTag = ((XmlFile) psiFile).getRootTag()
                .addSubTag(XmlElementFactory.getInstance(project).createTagFromText("<hoge></hoge>", XMLLanguage.INSTANCE), true);
        if (newTag.getValue().getTextRange().getStartOffset() != 0) {
            editor.getCaretModel().moveToOffset(newTag.getValue().getTextRange().getStartOffset());
        }
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
