MAVEN="mvn"
.PHONY: no_targets__ list


clean:
	${MAVEN} clean

test:
	${MAVEN} test

buildFatPackage:
	${MAVEN} package
