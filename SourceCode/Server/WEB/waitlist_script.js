const url='http://localhost:803';

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

(() => {
    let api = '/Doc/scheduler'
    const Http = new XMLHttpRequest();

    Http.open("GET", url+api+`?auth=${getCookie('Auth')}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if(resp.code == "success"){
            var table = document.getElementById("waitlist");
            for( let i = 0 ; i < resp.data.length; i++){
                if (resp.data[i].isDone == "0") {
                    const row = table.insertRow(-1);
                    for(let j = 0; j < 4; j++)
                        row.insertCell(j)
                    
                    row.setAttribute("id",`row_${resp.data[i].orderID}`);
                    row.cells[0].innerHTML = resp.data[i].a_Time
                    row.cells[1].innerHTML = resp.data[i].orderID
                    row.cells[2].innerHTML = resp.data[i].serviceID
                    row.cells[3].innerHTML = `<div class="iconsedit" style="background-color: white;">
                    <i class="fas fa-pen" id='${resp.data[i].orderID}' style="text-align: center"></i>
                    </div>` 
            
                    var temp = document.getElementById(resp.data[i].orderID);
                    temp.addEventListener('click', more);
                }
            }
          }
        console.log(resp);
    };
    Http.send();
})();

var edit1row = true;

function more(event){
    if(edit1row == false){
      alert('Please finish editting the previous row')
      return;
    }

    edit1row = false;

    var targetRow = event.target.id
    var targerID = "row_" + targetRow
    var table = document.getElementById("waitlist");
    
    for (let i = 1; i < table.getElementsByTagName("tr").length; i++){
        if(table.getElementsByTagName("tr")[i].id == targerID){
            var id = "itcouldbesame" + targetRow
            var row = table.insertRow(i + 1);

            let saveid = `save${id}`;
            let cancelid = `cancel${id}`;

            row.setAttribute("class", "edit");
            row.innerHTML  = `<td colspan = '4'><div id="informationTab" style = "width : 100%;display:flex ">
            <div style = "width : 30%">
            <table class="mytab" id ="symptom" style = "width: 100%; border-spacing: 0px;">
                <tr>
                    <td colspan="2" style = "font-size:medium">Symptom</td></tr>
                <tr>
                    <th style = "width: 50%"><p>Name</p></th>
                    <th style = "width: 50%"><p>Description</p></th>
                </tr>
                <tr>
                    <td style = "width: 50%"><textarea onkeydown="addNewRowSymptom()"></textarea></td>
                    <td style = "width: 50%"><textarea></textarea></td>
                </tr>
            </table>

            </div>

            <div style = "width : 5%"></div>

            <div style = "width : 30%">
            <table class="mytab" id ="prescription" style = "width: 100%; border-spacing: 0px;">
                <tr>
                    <td colspan="2" style = "font-size:medium">Prescription</td></tr>
                <tr>
                    <th style = "width: 50%"><p>Name</p></th>
                    <th style = "width: 50%"><p>Amount</p></th>
                </tr>
                <tr>
                    <td style = "width: 50%"><textarea onkeydown="addNewRowPrescription()"></textarea></td>
                    <td style = "width: 50%"><textarea maxlength = "8"></textarea></td>
                </tr>
            </table>
            </div>

            <div style = "width : 5%"></div>
            
            <div style = "width :30%">
            <div style = "height : 40%">
                <p style = "text-align: left;font-family: 'Times New Roman', Times, serif;font-size:20;"><b>Patient's record</b></p>
                <textarea id = "UserRecord" style = "height: 90%;width: 100%;border: 1px solid black;text-align: left;" readonly></textarea>
            </div>

            <div style = "height : 5%"></div>
            <div style = "height : 40%">
                <p style = "text-align: left;font-family: 'Times New Roman', Times, serif;font-size:20;"><b>Doctor's conclusion</b></p>
                <textarea id = 'edit${i + 1}' style = "height: 80%;width: 100%;border: 1px solid black;text-align: left;"></textarea>
            </div>
                <button type = "button" id = "${saveid}">Done</button>
                <button type = "button" id = "${cancelid}">Cancel</button>
            </div>
            </div></td>`

            let addPre = document.getElementById(saveid);
            addPre.addEventListener('click', function(){done(i + 1, targetRow)});

            let remove = document.getElementById(cancelid); 
            remove.addEventListener('click', function(){cancelAdd(i + 1)});
        }
    }
}

function checkEmptyTextarea(row){
    for(let i = 2; i < row.length; i++){
        if(row[i].getElementsByTagName("td")[0].firstChild.value != "" 
            && row[i].getElementsByTagName("td")[1].firstChild.value != ""){
            return 0;
        }
    }
    return 1;
}

function addNewRowSymptom(){
    var tableSymptom = document.getElementById("symptom");
    let rowSymptom = tableSymptom.getElementsByTagName("tr");
    
    if(checkEmptyTextarea(rowSymptom) == 1){
        var row = document.createElement("tr");

        row.innerHTML = `<tr>
                            <td style = "width: 50%"><textarea onkeydown="addNewRowSymptom()"></textarea></td>
                            <td style = "width: 50%"><textarea></textarea></td>
                        </tr>`;

        tableSymptom.appendChild(row);
    }
}

function addNewRowPrescription(){
    let tablePrescription = document.getElementById("prescription");
    let rowPrescription = tablePrescription.getElementsByTagName("tr");
    
    if(checkEmptyTextarea(rowPrescription) == 1){
        var row = document.createElement("tr");

        row.innerHTML = `<tr>
                            <td style = "width: 50%"><textarea onkeydown="addNewRowPrescription()"></textarea></td>
                            <td style = "width: 50%"><textarea></textarea></td>
                        </tr>`;

        tablePrescription.appendChild(row);
    }
}

function done(row, targetRow) {
    let tableSymptom = document.getElementById("symptom");
    let rowSymptom = tableSymptom.getElementsByTagName("tr");

    let apiSymptom = '/Doc/scheduler/detailadd'

    for (let i = 2; i < rowSymptom.length; i++){        
        if(rowSymptom[i].getElementsByTagName("td")[0].firstChild.value != "" 
            && rowSymptom[i].getElementsByTagName("td")[1].firstChild.value != ""){
            const HttpSymptom = new XMLHttpRequest();
            HttpSymptom.open("GET", url+apiSymptom+`?auth=${getCookie('Auth')}&orderID=${targetRow}
                            &name=${rowSymptom[i].getElementsByTagName("td")[0].firstChild.value}
                            &value=${rowSymptom[i].getElementsByTagName("td")[1].firstChild.value}`,true);
            HttpSymptom.onload = function(){
                respSymptom = JSON.parse(HttpSymptom.responseText);
                // do sthg here
                        
                console.log(respSymptom)
            };
            HttpSymptom.send();
        }
    }

    let tablePrescription = document.getElementById("prescription");
    let rowPrescription = tablePrescription.getElementsByTagName("tr");
    let apiPrescription = '/Doc/scheduler/givep'

    for (let i = 2; i < rowPrescription.length; i++){
        if(rowPrescription[i].getElementsByTagName("td")[0].firstChild.value != "" 
            && rowPrescription[i].getElementsByTagName("td")[1].firstChild.value != ""){
        const HttpPrescription = new XMLHttpRequest();
            HttpPrescription.open("GET", url+apiPrescription+`?auth=${getCookie('Auth')}
                                &orderID=${targetRow}&name=${rowPrescription[i].getElementsByTagName("td")[0].firstChild.value}
                                &amount=${rowPrescription[i].getElementsByTagName("td")[1].firstChild.value}`,true);
            HttpPrescription.onload = function() {
                respPrescription = JSON.parse(HttpPrescription.responseText);
                if (respPrescription.code == "fail") {
                    alert('fail')
                }
                console.log(respPrescription);
            };
            HttpPrescription.send();
        }
    }
}

function cancelAdd(row){
    edit1row = true;
    document.getElementById("waitlist").deleteRow(row);
}

function GivePrescriptions(table, targetRow, num) {
    let id = table.id.slice(4);
    
    for (let i = num; i < 6; i++){
        if (document.getElementById( `edit${i}`).value == "") {
            alert('You must input something here');
            return;
        }
    }

    // gui API , neu thanh cong thi lam cai nay
    let api = '/Doc/scheduler/givep'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?auth=${getCookie('Auth')}&orderID=${targetRow}&name=${name}&amount=${number}`,true);
    Http.onload = function() {
        resp = JSON.parse(Http.responseText);
        if (resp.code == "fail") {
            alert('fail')
        }
    };
    Http.send();
}

var savebutton = document.getElementById('saveInfo');
//savebutton.addEventListener('click', GivePrescriptions);

/* function GetSchedulerDetail(orderID=""){ // lấy ra thông tin chi tiết của buổi khám
    let api= '/Doc/scheduler/detailget'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        // do sthg here
        
        console.log(resp)
    };
    Http.send();

} */