#!/usr/bin/env bash
java --module-path libs \
org.junit.platform.console.ConsoleLauncher \
--classpath ./outDir/library-core \
--select-class cn.tuyucheng.taketoday.library.core.LibraryUnitTest