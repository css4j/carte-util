# How to produce a `carte-util` release

Please follow these steps to produce a new release of carte-util.

## Requirements

- The [Git version control system](https://git-scm.com/downloads) is required to
obtain the sources. Any recent version should suffice.

- Java 11 or later. You can install it from your favourite package manager or by
downloading from [Adoptium](https://adoptium.net/).

- The [`generate_directory_index_caddystyle.py`](https://gist.github.com/carlosame/bd5b68c4eb8e0817d9beb1dcfb4de43d)
script and a recent version of [Python](https://www.python.org/) (required to
run it). The script is necessary to create the index files in the bare-bones
Maven repository currently used by carte-util.

## Steps

1) In the `master` branch of your local copy of the carte-util Git repository,
bump the `version` in the [`build.gradle`](build.gradle) file or remove the
`-SNAPSHOT` suffix as necessary. Commit the change to the Git repository.

2) If there is an issue tracking the release, close it (could be done adding a
'closes...' to the message in the previously described commit).

3) If your local copy of the carte-util Git repository exactly matches the current
`master` HEAD, use that copy to execute the `gradlew` commands shown later,
otherwise create a new clone of the `git@github.com:css4j/carte-util.git`
repository with `git clone` and use it.

For reference, let your copy of the carte-util release code be at
`/path/to/carte-util`.

4) To check that everything is fine, build the code:

```shell
cd /path/to/carte-util
./gradlew build
```

5) Use `changes.sh <new-version>` to create a `CHANGES.txt` file with the
changes from the latest tag. For example if you are releasing `3.6.1`:

```shell
./changes.sh 3.6.1
```

Edit the resulting `CHANGES.txt` as convenient, to use it as the basis for the
detailed list of changes when you create the new release in Github.

6) Clone the `git@github.com:css4j/css4j.github.io.git` repository (which
contains a bare-bones Maven repository) and let `/path/to/css4j.github.io` be
its location.

7) From your copy of the carte-util release code, write the new artifacts into
the local copy of the bare-bones Maven repository with:

```shell
cd /path/to/carte-util
./gradlew publish -PmavenReleaseRepoUrl="file:///path/to/css4j.github.io/maven"
```

8) Produce the necessary directory indexes in the local copy of the bare-bones
Maven repository using [`generate_directory_index_caddystyle.py`](https://gist.github.com/carlosame/bd5b68c4eb8e0817d9beb1dcfb4de43d):

```shell
cd /path/to/css4j.github.io/maven/io/sf/carte
generate_directory_index_caddystyle.py -r carte-util
```

If the changes to the `css4j.github.io` repository look correct, commit them but
do not push yet.

9) For legacy URL compatibility, produce a non-modular Javadoc and put it into
`css4j.github.io/api/carte-util/3`. To generate the javadocs,
apply the following patch to the `build.gradle` file in your copy of the
release code:

```patch
@@ -112,11 +112,14 @@ tasks.withType(JavaCompile) {
 
 tasks.withType(Javadoc) {
    options.addStringOption('Xdoclint:none', '-quiet')
    options.addStringOption('encoding', 'UTF-8')
    options.addStringOption('charset', 'UTF-8')
-   options.links 'https://docs.oracle.com/en/java/javase/11/docs/api/'
+   options.links 'https://docs.oracle.com/javase/8/docs/api/'
+   options.source = '8'
+   excludes += '**/module-info.java'
+   modularity.inferModulePath = false
 }
 
 tasks.withType(AbstractArchiveTask).configureEach {
 	// Reproducible build
 	preserveFileTimestamps = false
```

Once the new javadocs are generated, move them to the website repo. For example:

```shell
rm -fr /path/to/css4j.github.io/api/3
mkdir /path/to/css4j.github.io/api/3
mv /path/to/css4j-3.9.1/build/docs/javadoc/* /path/to/css4j.github.io/api/3
```

If the changes to the `css4j.github.io` repo look correct, commit them with a
description like "Non-modular Javadocs for 3.9.1" but do not push yet.


10) Clone the [css4j-dist](https://github.com/css4j/css4j-dist) repository and
execute `./gradlew mergedJavadoc`. Move the javadocs from `build/docs/javadoc`
to `/path/to/css4j.github.io/api/latest`:

```shell
rm -fr /path/to/css4j.github.io/api/latest
mkdir /path/to/css4j.github.io/api/latest
mv /path/to/css4j-dist/build/docs/javadoc/* /path/to/css4j.github.io/api/latest
```

If the changes to the `css4j.github.io` repo look correct, commit them with a
description like "Latest modular Javadocs after carte-util 3.6.1" and push.

Check whether the ["Examples" CI](https://github.com/css4j/css4j.github.io/actions/workflows/examples.yml)
triggered by that commit to the `css4j.github.io` repository completed
successfully. A failure could mean that the jar file is not usable with Java 8,
for example.

11) Create a `v<version>` tag in the carte-util Git repository. For example:

```shell
cd /path/to/carte-util
git tag -s v3.6.1 -m "Release 3.6.1"
git push origin v3.6.1
```

or `git tag -a` instead of `-s` if you do not plan to sign the tag. But it is
generally a good idea to sign a release tag.

Alternatively, you could create the new tag when drafting the Github release
(next step).

12) Draft a new Github release at https://github.com/css4j/carte-util/releases

Summarize the most important changes in the release description, then create a
`## Detail of changes` section and paste the contents of the `CHANGES.txt` file
under it.

Add to the Github release the _jar_ files from this release.

13) Verify that the new [Github packages](https://github.com/orgs/css4j/packages?repo_name=carte-util)
were created successfully by the [Gradle Package](https://github.com/css4j/carte-util/actions/workflows/gradle-publish.yml)
task.

14) In your local copy of the [css4j-dist](https://github.com/css4j/css4j-dist)
repository, update the carte-util version number in the
[maven/install-css4j.sh](https://github.com/css4j/css4j-dist/blob/master/maven/install-css4j.sh)
script. Commit the change, push and look for the completion of that project's
CI.
