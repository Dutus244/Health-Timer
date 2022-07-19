const url='http://localhost:803';

function GetScheduler_doc_pai(usID){// lấy ra lịch sử khám bệnh của 1 bên nhân nào đó
    let api = '/Doc/scheduler/Paitent'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?usID=${usID}`,true);

    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}