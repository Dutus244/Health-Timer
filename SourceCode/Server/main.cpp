#include "WebServerSide.h"
#include "SQLServer-Side.h"
#pragma comment(lib, "lodbc32")
#pragma comment(lib, "lws2_32")

SQL_SERVER *dataServer = new SQL_SERVER(L"tcp:tntruong1009.database.windows.net,1433",
                                        L"HEALTH",
                                        L"tntruong1009",
                                        L"Truong1009");

int main()
{
  dataServer->Connect();
  SQLLEN a ;
  std::cout<<dataServer->SelectQuery(L"select* from GetPresciption('0000000001')",a).rdbuf();
  std::cout<<a;
  //WebResponse temp("", 80);
  //temp.init();
  //temp.run();
  //    std::string temp ="POST /ajax/bz?__a=1&__ccg=EXCELLENT&__comet_req=0&__csr=&__dyn=7xe6Fo4OQ1PyUbFuC1swgE98nwgU6C7UW3q327E2vwXx60kO4o3Bw5VCwjE3awbG782Cw8G1Qw5MKdwnU1oU884y0lW0SU2swdq0Ho2ewnE3fw5rwSyE1582ZwrU&__hs=19125.BP%3ADEFAULT.2.0.0.0.&__hsi=7097269269221765721-0&__req=s&__rev=1005512084&__s=yvzvg3%3Ap4qz6g%3Aw0g84m&__spin_b=trunk&__spin_r=1005512084&__spin_t=1652461772&__user=0&dpr=1&jazoest=2845&lsd=AVoZ528tw30 HTTP/3\r\nHost: www.facebook.com\r\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:100.0) Gecko/20100101 Firefox/100.0\r\nAccept: */*\r\nAccept-Language: vi-VN,vi;q=0.8,en-US;q=0.5,en;q=0.3\r\nAccept-Encoding: gzip, deflate, br\r\nReferer: https://www.facebook.com/login.php?next=https%3A%2F%2Fbusiness.facebook.com%2Flatest%2Fhome%3Fnav_ref%3Dbm_home_redirect\r\nContent-Type: multipart/form-data; boundary=---------------------------7751626747166705022826595893\r\nContent-Length: 1818\r\nOrigin: https://www.facebook.com\r\nAlt-Used: www.facebook.com\r\nConnection: keep-alive\r\nCookie: sb=zJB-Yr-HkwhUp3_-QPi9GKjs; fr=0Ty3zPJTFmlveeMeL..BifpDM.X9.AAA.0.0.BifpDM.AWXttFlbqc8; wd=1246x230; datr=zJB-YlihMsByPd3VOC_SH8_k\r\nSec-Fetch-Dest: empty\r\nSec-Fetch-Mode: cors\r\nSec-Fetch-Site: same-origin";
  //    HttpRequestHeader a(temp);
  //    a.print();
  return 0;
}
