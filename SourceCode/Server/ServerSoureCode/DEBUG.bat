@ECHO OFF
ECHO creating exe file
g++ main.cpp Network/TCPListener.cpp API_WEB/HttpHeader.cpp Network/WebServerSide.cpp Data/SQLServer-Side.cpp API_WEB/API.cpp API_WEB/CTokenGenerator.cpp -o ../myserver.exe  -lws2_32 -lodbc32
ECHO debug success
PAUSE