run-dist:
	make -C app run-dist

clean:
	make -C app clean

run:
	make -C app run

test:
	make -C app test

lint:
	make -C app lint

report:
	make -C app report

build:
	make -C app build

.PHONY:	build