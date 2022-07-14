const url='http://localhost:80';

// _____________________________________________________________________________

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function LoginDoc(id="",pass=""){
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

function GetScheduler_doc_pai(usID){
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

function GetPrescriptions(orderID=""){
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

function GivePrescriptions(orderID="",drugname="",amount=""){
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

function RemovePrescriptions(orderID="",drugname=""){
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

function AddSchedulerDetail(orderID="",_name="",_value=""){
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

function GetSchedulerDetail(orderID=""){
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

function RemoveSchedulerDetail(orderID="",_name=""){
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

export default function LoginHos(id,pass){
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

function CreateDoctorAccount(docid,name,citizenid,bhday,addr){
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

function GetHosService(){
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

function ServiceAdd(serviceID,serviceName){
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

function ServiceRemove(serviceID){
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

function DocServiceAdd(doc,service){
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

function DocServiceRemove(doc,service){
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