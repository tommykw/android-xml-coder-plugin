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
}