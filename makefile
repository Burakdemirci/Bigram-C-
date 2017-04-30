all: begin

begin: _mainTester.java
	javac  _mainTester.java -Xlint

clean:
	rm -f *.class