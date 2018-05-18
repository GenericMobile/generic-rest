MCSFLAGS=-r:System.Net.dll -r:System.Net.Http.dll

.PHONY: example.cs

all: example.exe

example.exe: example.cs
	mcs $(MCSFLAGS) example.cs

clean:
	rm -f example.exe
