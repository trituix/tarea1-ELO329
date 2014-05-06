.PHONY: run

default:
	javac -d . src/*.java

run:
	@read -p "Ingrese delta: " DELTA; \
	read -p "Ingrese tiempo a simular: " TOTAL; \
	read -p "Ingrese tiempo de muestreo: " SAMPLING; \
	java PhysicsLab $$DELTA $$TOTAL $$SAMPLING

clean:
	$(RM) *.class