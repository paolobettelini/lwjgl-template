# LWJGL

This repository contains a template for setting up a window using LWJGL.

1. Compile with Gradle

   + On UNIX
    ```shell
        chmod +x gradlew
        ./gradlew build
    ```

    + On Windows
    ```shell
    gradlew.bat build
    ```

    + If you have Gradle installed
    ```shell
    gradle build
    ```

2. Run program
  ```shell
  java -jar weapons-game/build/libs/weapons-<version>.jar
  ```

  + On macOS
  ```shell
  java -XstartOnFirstThread -jar weapons-game/build/libs/weapons-<version>.jar
  ```
