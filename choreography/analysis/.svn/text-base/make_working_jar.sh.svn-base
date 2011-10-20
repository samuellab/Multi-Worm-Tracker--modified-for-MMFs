pushd . ; 
cd ../DataMapVisualizer/src ; 
cp ../../../java/Generic/kerrutil.jar ./Chore.jar ;
rm *.class ;
javac -cp Chore.jar:. *.java ;
jar uMf Chore.jar *.class *.png ; 
rm *.class ;
mv Chore.jar ../../analysis ; 
popd ; 
javac -cp Chore.jar:. *.java ;
jar umf Chore.manifest Chore.jar *.class *.png ;
rm *.class
