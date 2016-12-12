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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tommy on 2016/12/02.
 */
public class LayoutAction implements IntentionAction {
    private List<String> checkList = Arrays.asList("manifest", "project", "component", "mobile");

    @Nls
    @NotNull
    @Override
    public String getText() {
        return "layout";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "layout";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        if (!(file instanceof XmlFile)) return false;
        XmlTag rootTag = ((XmlFile) file).getRootTag();
        if (rootTag == null) {
            return false;
        }

        String rootTagName = rootTag.getName();
        if (checkList.contains(rootTagName)) {
            return false;
        }

        return rootTagName != "layout" && rootTag.getAttribute("android") != null;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        if (!(file instanceof XmlFile)) return;
        XmlTag rootTag = ((XmlFile) file).getRootTag();
        if (rootTag == null) {
            return;
        }
        List attrs = new ArrayList();
        for (XmlAttribute attr : rootTag.getAttributes()) {
            if (attr.getName().startsWith("hoge")) {
                attrs.add(attr.getText());
            }
        }

//        String attrText;
//        for (String attr : attrs) {
//
//        }
//        if (specifiedAttrText != null) {
//            specifiedAttrText += " ";
//        }
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
