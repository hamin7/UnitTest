public class Environment {
    private JavaSpecification javaSpecification;
    private OperationSystem operationSystem;

    public Environment(JavaSpecification javaSpecification, OperationSystem operationSystem) {
        this.javaSpecification = javaSpecification;
        this.operationSystem = operationSystem;
    }

    public boolean isWindows() {
        return operationSystem.getName().contains("Windows");
    }
    public boolean isMacOS() {
        return operationSystem.getName().contains("Mac");
    }

    public boolean isAmd64Architecture() {
//        return operationSystem.getArchitecture().equals("amd64");
        return operationSystem.getArchitecture().equals("x86_64");
    }
    public boolean isArm64Architecture() {
        return operationSystem.getArchitecture().equals("aarch64");
    }

    public String getJavaVersion() {
        return javaSpecification.getVersion();
    }
}
