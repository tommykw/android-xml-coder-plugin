import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.vfs.CharsetToolkit
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

/**
 * Created by tommy on 2017/01/06.
 */
class MeterFileType : FileType {
    companion object {
        val INSTANCE = MeterFileType()
    }

    override fun getIcon(): Icon? {
        return IconLoader.getIcon("/icons/beaker.png");
    }

    override fun getName(): String {
        return "name"
    }

    override fun isBinary(): Boolean {
        return false
    }

    override fun isReadOnly(): Boolean {
        return false;
    }

    override fun getDefaultExtension(): String {
        return "jmx"
    }

    override fun getCharset(file: VirtualFile, content: ByteArray): String? {
        return CharsetToolkit.UTF8
    }

    override fun getDescription(): String {
        return "description"
    }
}