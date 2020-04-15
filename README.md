### Gradle direct
The following shows the correct output of the program:

```sh
$ ./gradlew -q run
class com.devinrsmith.IJManifestIssueMain
class com.devinrsmith.IJManifestIssueMain$Inner
class org.joor.CompileOptions
```

### No shortening

It correctly runs with no shortening applied as an IJ configuration:

![no shortening](no-shortening.png)

### Jar shortening

It fails to run with jar shortening applied as an IJ configuration:

![jar shortening](jar-shortening.png)

with the output

```
Exception in thread "main" org.joor.ReflectException: Compilation error: /ShowMainImpl.java:5: error: package com.devinrsmith does not exist
        System.out.println(com.devinrsmith.IJManifestIssueMain.class);
                                          ^
/ShowMainImpl.java:6: error: package com.devinrsmith.IJManifestIssueMain does not exist
        System.out.println(com.devinrsmith.IJManifestIssueMain.Inner.class);
                                                              ^
/ShowMainImpl.java:7: error: package org.joor does not exist
        System.out.println(org.joor.CompileOptions.class);
                                   ^
3 errors

	at org.joor.Compile.compile(Compile.java:100)
	at org.joor.Reflect.compile(Reflect.java:102)
	at com.devinrsmith.IJManifestIssueMain.main(IJManifestIssueMain.java:27)
```

### Issue?

It appears that IJ is creating an improper manifest file with `file:` prefixes on the classpath
parts. These `file:` prefixes seem to work "ok" in `java`, but fail in `javac`. Once these prefixes
are removed, the shortened command line program works. See [BAD.MF](BAD.MF) for an example of the
bad manifest.