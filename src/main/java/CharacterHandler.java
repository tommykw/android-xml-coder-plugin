import org.jetbrains.annotations.NotNull;
import sun.jvm.hotspot.ui.Editor;

/**
 * Created by tommy on 2016/12/06.
 */
public class CharacterHandler implements CodeInsightActionHandler {
    private final boolean expanded;

    public CharacterHandler(final boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public void invoke(@NotNull final Project project,
                       @NotNull final Editor editor,
                       @NotNull final PsiFile file) {

        PsiDocumentManager.getInstance(project).commitAllDocuments();
        CodeFoldingManager foldingManager = CodeFoldingManager.getInstance(project);
        foldingManager.updateFoldRegions(editor);
        final FoldRegion[] allFoldRegions = editor.getFoldingModel().getAllFoldRegions();
        Runnable processor = new Runnable() {
            @Override
            public void run() {
                for (FoldRegion region : allFoldRegions) {
                    PsiElement element = EditorFoldingInfo.get(editor).getPsiElement(region);
                    final ASTNode node = element != null ? element.getNode() : null;
                    if (node != null && (node.getElementType() == MathematicaElementTypes.IDENTIFIER )||
                    }
                }
            }
        }
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
