import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

class ResourceOptimizerIntention : IntentionAction {
    private val assetType = "string"
    override fun getFamilyName() = text
    override fun getText() = "Optimize resources"
    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
        return file is XmlFile
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        val xml = file as XmlFile
        val appDirIndex = project.baseDir.canonicalPath.toString().split("/").size
        val appDirName = xml.containingDirectory.toString().split("/")[appDirIndex]

        xml.rootTag?.findSubTags(assetType)?.map {
            it.attributes.map { attr ->
                val appResName = "R.${assetType}.${attr.displayValue}"
                val assetResName = "@${assetType}/${attr.displayValue}"
                val child = findChildByRelativePath(project.baseDir, "/${appDirName}/src/main")
                println("${child?.canonicalPath}")
                if (child == null) return

                val files = findFilesRecursive(child)

                var isDeletable = true
                files.forEach READ_WHOLE_FILES@ { targetFile ->
                    val reader = BufferedReader(InputStreamReader(targetFile.inputStream, "UTF-8"))
                    var line = reader.readLine()
                    while (line != null) {
                        println(line)
                        if (isMatched(line, appResName) || isMatched(line, assetResName)) {
                            if (isCommentOut(line).not()) {
                                isDeletable = false
                                break
                            }
                        }

                        line = reader.readLine()
                    }
                    reader.close()

                    if (isDeletable) return@READ_WHOLE_FILES
                }

                if (isDeletable) attr.parent.delete()
            }
        }
    }

    private tailrec fun findFilesRecursive(virtualFile: VirtualFile): List<VirtualFile> {
        val files = arrayListOf<VirtualFile>()
        virtualFile.children.forEach { currentChild ->
            if (currentChild.isDirectory) {
                findFilesRecursive(currentChild).forEach { files.add(it) }
            } else if (currentChild.extension == "kt" || currentChild.extension == "java" || currentChild.extension == "xml") {
                files.add(currentChild)
            } else {}
        }

        return files
    }

    private tailrec fun findChildByRelativePath(virtualFile: VirtualFile, path: String): VirtualFile? {
        if (path.isEmpty() && path.split("/").size <= 1) return null
        val child = virtualFile.findChild(path.split("/")[1])

        if (child == null) {
            return null
        } else if (path.split("/").size > 2 && child.isDirectory) {
            val len = path.split("/")[1].length
            val nextPath = path.substring(len + 1)
            return findChildByRelativePath(child, nextPath)
        } else {
            return child
        }
    }

    private fun isMatched(target: String, regex: String) = Pattern.compile(regex).matcher(target).find()

    private fun isCommentOut(str: String) =
        Pattern.compile("^//").matcher(str.trim()).find()
}