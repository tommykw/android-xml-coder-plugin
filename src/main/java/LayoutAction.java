import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlElementFactory;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tommy on 2016/12/02.
 */
public class LayoutAction implements IntentionAction {
    private List<String> specifiedList = Arrays.asList("manifest", "project", "component", "module");

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "layout action";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "layout action";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        if (!(psiFile instanceof XmlFile)) return false;
        XmlTag rootTag = ((XmlFile) psiFile).getRootTag();
        if (rootTag == null) return false;
        String rootTagName = ((XmlFile) psiFile).getRootTag().getName();
        if (specifiedList.contains(rootTagName)) return false;
        return rootTagName != "layout" && rootTag.getAttribute("xmlns:android") != null;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        if (!(psiFile instanceof XmlFile)) return;
        XmlTag rootTag = ((XmlFile) psiFile).getRootTag();
        if (rootTag == null) return;

        String specifiedAttrText = null;
        for (XmlAttribute attr : rootTag.getAttributes()) {
            if (attr.getName().startsWith("xmlns:")) {
                if (specifiedAttrText != null) {
                    specifiedAttrText += " ";
                }
                specifiedAttrText += attr.getName();
            }
        }

        XmlElementFactory factory = XmlElementFactory.getInstance(project);
        XmlTag tag = factory.createTagFromText("<android></android>", XMLLanguage.INSTANCE);
        rootTag.replace(tag);
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
