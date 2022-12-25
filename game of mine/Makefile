JCT= javac -classpath test4poo.jar
EXEC_TEST=java -jar test4poo.jar
TESTS= tests/demineur/matrice/*.java \
	   #tests/demineur/exception/*.java \
	   #tests/demineur/gui/*.java

# Compile java files

compile: clean
	@echo "Compiling..."
	@cd src && javac demineur/exception/*.java -d ../classes
	@cd src && javac demineur/matrice/*.java -d ../classes
	@cd src && javac demineur/gui/*.java -d ../classes
	@cd src && javac demineur/Main.java -d ../classes
	@echo "Done"

# execute class files

play: compile
	@echo "Have fun!"
	@cd classes && java demineur/Main
	@echo "See you soon !"

# COMPILATION DES TESTS

tests: compile
	@echo "Compiling tests..."
	$(JCT) $(TESTS) -d classes
	@echo "Done"


#exemple : make demineur.matrice.MatriceBomb

# cleans class files
clean: 
	@echo "Cleaning..."
	@rm -rf classes/*
	@echo "Done"

# .PHONY compile execute

