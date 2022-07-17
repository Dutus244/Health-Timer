@ECHO OFF
ECHO creating exe file
g++ main.cpp TCPListener.cpp HttpHeader.cpp WebServerSide.cpp SQLServer-Side.cpp -o myserver.exe API.cpp CTokenGenerator.cpp -lws2_32 -lodbc32
ECHO debug success
PAUSE