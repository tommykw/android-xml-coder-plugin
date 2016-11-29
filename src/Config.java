/**
 * Created by tommy on 2016/11/30.
 */

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
    name = "Config",
    reloadable = true,
    storages = {
        @Storage(id = "default", file = "$PROJECT_FILE$"),
        @Storage(id = "dir", file = "$PROJECT_CONFIG_DIR$/setting-plugin.xml", scheme = StorageScheme.DIRECTORY_BASED)
    }
)

public class Config implements PersistentStateComponent<Config> {
    private Integer count;
    private String name;

    @Nullable
    @Override
    public Config getState() {
        return this;
    }

    @Override
    public void loadState(Config config) {
        XmlSerializerUtil.copyBean(config, this);
    }

    @Nullable
    public static Config getInstance(Project project) {
        return ServiceManager.getService(project, Config.class);
    }

    public boolean isEmpty() {
        return count == null;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() { count = 0; }

    public void increment() { count++; }
}