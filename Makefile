JFLAGS =
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	TCPServer.java \
	TCPClient.java \
	Logger.java \
	Calculator.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
