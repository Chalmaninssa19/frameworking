set fw_root=C:\Users\Chalman\Documents\java\s4\frameworking
set project_lib=.\testFramework\build\web\WEB-INF\lib\

mkdir src_jar
mkdir class_jar

for /r %fw_root%\ %%f in (*.java) do copy %%f .\src_jar
javac -Xlint -cp gson-2.8.2.jar;servlet-api.jar -parameters -d .\class_jar .\src_jar\*.java
jar -cf fw.jar -C .\Frameworking\build\web\WEB-INF\classes\ etu1960
::copy fw.jar %project_lib%

rmdir /S /Q src_jar
rmdir /S /Q class_jar