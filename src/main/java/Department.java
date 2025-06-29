public sealed interface Department permits CSE, IT, CE, ME, ECE, EEE, AIDS {
    String name();
    String code();
}

record CSE() implements Department {
    @Override public String name() { return "Computer Science Engineering"; }
    @Override public String code() { return "CSE"; }
}

record IT() implements Department {
    @Override public String name() { return "Information Technology"; }
    @Override public String code() { return "IT"; }
}

record CE() implements Department {
    @Override public String name() { return "Civil Engineering"; }
    @Override public String code() { return "CE"; }
}

record ME() implements Department {
    @Override public String name() { return "Mechanical Engineering"; }
    @Override public String code() { return "ME"; }
}

record EEE() implements Department {
    @Override public String name() { return "Electrical and Electronics Engineering"; }
    @Override public String code() { return "EEE"; }
}

record ECE() implements Department {
    @Override public String name() { return "Electronics and Communication Engineering"; }
    @Override public String code() { return "ECE"; }
}

record AIDS() implements Department {
    @Override public String name() { return "Artificial Intellignence & Data Science"; }
    @Override public String code() { return "AIDS"; }
}