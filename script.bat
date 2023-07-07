set tomcat_root=C:\MyWebServer\apache-tomcat-8.5.75\apache-tomcat-8.5.75
set project_root=C:\Users\Chalman\Documents\java\s4\testFramework
set fw_root=C:\Users\Chalman\Documents\java\s4\frameworking
set lib_root=.\temp\WEB-INF\lib\framework.jar

:: Cr�ation du dossier temporaire temp
mkdir temp
cd temp


:: Cr�ation de la structure du fichier
mkdir WEB-INF
mkdir pages
mkdir css/bootstrap
mkdir src
cd WEB-INF
mkdir classes
mkdir lib
mkdir css
cd ../../


:: Compilation du modele
copy fw.jar %lib_root%
for /r %project_root%\ %%f in (*.java) do copy %%f .\temp\src
javac -cp framework.jar -parameters -d .\temp\WEB-INF\classes .\temp\src\*.java
@rem rmdir /S /Q .\temp\src


:: Copie des Autre fichiers
copy .\TestFramework\web\pages\*.jsp .\temp\pages\
copy .\TestFramework\web\WEB-INF\web.xml .\temp\WEB-INF\
xcopy .\TestFramework\web\css\* .\temp\css /E /I


:: D�ploiement vers tomcat
jar -cvf test_fw.war -C .\temp\ .
copy test_fw.war %tomcat_root%\webapps\


:: Supprimer le dossier temp
@rem:: rmdir /S /Q .\temp