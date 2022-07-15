const url='http://localhost:80';

// _____________________________________________________________________________

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function LoginDoc(id="",pass=""){ // hàm này ko xài nũa, do đã chuyển API
    let api = '/Doc/account/log'
    let paragram  = `id=${id}&password=${pass}`
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api,true);
    Http.onload  = function(){
        let resp = JSON.parse(Http.responseText);
        if (resp.code == "success"){
            str  = "auth=" +resp.auth+ ";";
            document.cookie = str
        }
        console.log(resp)
    };
    Http.send(paragram);
};

function GetScheduler_doc_pai(usID){// lấy ra lịch sử khám bệnh của 1 bên nhân nào đó
    let api = '/Doc/scheduler/Paitent'
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?usID=${usID}`,true);

    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function GetPrescriptions(orderID=""){//lấy ra đơn thuốc
    let api = '/Doc/scheduler/getp'
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api +`?orderID=${orderID}`,true);

    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function GivePrescriptions(orderID="",drugname="",amount=""){// thêm 1 thuốc vào đơn thuốc (amount là chuỗi 8 kí tự, tương ứng số viên trc ăn sáng, sau ăn sáng, trc ăn trưa, sau ăn trưa ...)
    let api = '/Doc/scheduler/givep'
    let auth = getCookie('auth')

    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${auth}&orderID=${orderID}&name=${drugname}&amount=${amount}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function RemovePrescriptions(orderID="",drugname=""){// xóa bỏ 1 loại thuốc trong đơn
    let api ='/Doc/scheduler/removep'
    let auth = getCookie('auth')
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${auth}&orderID=${orderID}&name=${drugname}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function AddSchedulerDetail(orderID="",_name="",_value=""){// thêm một dữ liệu sức khỏe cho 1 lần khám đó (nhịp tim, nhóm máu, blabla)
    let api = '/Doc/scheduler/detailadd'
    let auth = getCookie('auth')
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${auth}&orderID=${orderID}&name=${_name}&value=${_value}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function GetSchedulerDetail(orderID=""){ // lấy ra thông tin chi tiết của buổi khám
    let api= '/Doc/scheduler/detailget'
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();

}

function RemoveSchedulerDetail(orderID="",_name=""){// xóa 1 dữ liệu trong chi tiết buổi khám
    let api = '/Doc/scheduler/detailremove'

    let auth = getCookie('auth')
 
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${auth}&orderID=${orderID}&name=${_name}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

export default function LoginHos(id,pass){ // hàm này ko xài nũa, do đã chuyển API
    let api = '/Hos/account/log'

    let paragram  = `?id=${id}&password=${pass}`
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+paragram,true);
    Http.onload  = function(){
        let resp = JSON.parse(Http.responseText);
        if (resp.code == "success"){
            str  = "HosAuth=" +resp.auth
            document.cookie = str
        }
        console.log(resp)
    };
    Http.send();
}

function CreateDoctorAccount(docid,name,citizenid,bhday,addr){// tạo ra 1 bác sĩ mới trong bệnh viện, có tk là DocID.TNT (TNT là do đó là tên viết tắt của bv hiện tại), mk là citizen ID
    let api = '/Hos/doc/add'
    let auth = getCookie('HosAuth')

    const Http = new XMLHttpRequest();
    paragram = `?docID=${docid}&citizenID=${citizenid}&name=${name}&bthday=${bhday}&addr=${addr}&auth=${auth}`
    Http.open("POST", url+api+paragram,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();

}

function GetHosService(){ //lấy ra các dịch vụ hiện tại của bệnh viện
    let api = '/Hos/service'

    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${getCookie('HosAuth')}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function ServiceAdd(serviceID,serviceName){//thêm một dịch vụ cho bênh viện
    let api = '/Hos/service/add'
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${getCookie('HosAuth')}&serviceID=${serviceID}&servicename=${serviceName}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function ServiceRemove(serviceID){// xóa 1 dịch vụ ra khỏi danh sách bv
    let api = '/Hos/service/remove'

    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${getCookie('HosAuth')}&serviceID=${serviceID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}

function DocServiceAdd(doc,service){ // thêm 1 bác sĩ vô 1 dịch vụ trong bv
    let api = '/Hos/doc/serviceadd'
    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${getCookie('HosAuth')}&serviceID=${service}$docID=${doc}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();

}

function DocServiceRemove(doc,service){// xóa đi 1 dịch vụ của bác sĩ
    let api = '/Hos/doc/serviceremove'

    const Http = new XMLHttpRequest();
    Http.open("POST", url+api+`?auth=${getCookie('HosAuth')}&serviceID=${service}$docID=${doc}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();
}