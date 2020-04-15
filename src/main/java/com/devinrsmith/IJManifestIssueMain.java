package com.devinrsmith;

import org.joor.CompileOptions;
import org.joor.Reflect;

public class IJManifestIssueMain {
    private static final String IMPL = "public class ShowMainImpl implements Runnable {\n"
        + "\n"
        + "    @Override\n"
        + "    public void run() {\n"
        + "        System.out.println(com.devinrsmith.IJManifestIssueMain.class);\n"
        + "        System.out.println(com.devinrsmith.IJManifestIssueMain.Inner.class);\n"
        + "        System.out.println(org.joor.CompileOptions.class);\n"
        + "    }\n"
        + "}";

    public static class Inner {

    }

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(System.getProperty("java.class.path"));
        //System.out.println(IJManifestIssueMain.class);
        //System.out.println(IJManifestIssueMain.Inner.class);
        //System.out.println(org.joor.CompileOptions.class);
        Reflect
            .compile("ShowMainImpl", IMPL, new CompileOptions())
            .create()
            .as(Runnable.class)
            .run();
    }
}
