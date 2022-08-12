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
            var table = document.getElementById("donelist");
            for( let i = 0 ; i < resp.data.length; i++){
                if (resp.data[i].isDone == "1") {
                    const row = table.insertRow(-1);
                    for(let j = 0; j < 4; j++)
                        row.insertCell(j)
                    
                    row.setAttribute("id",`row_${resp.data[i].orderID}`);
                    row.cells[0].innerHTML = resp.data[i].a_Time
                    row.cells[1].innerHTML = resp.data[i].name
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
    var table = document.getElementById("donelist");
    
    for (let i = 1; i < table.getElementsByTagName("tr").length; i++){
        if(table.getElementsByTagName("tr")[i].id == targerID){
            var id = "itcouldbesame" + targetRow
            var row = table.insertRow(i + 1);

            let cancelid = `cancel${id}`;

            row.setAttribute("class", "show");
            row.setAttribute("id","showDIV")

            row.innerHTML  = `<td colspan = '4' style = "display : none;"><div id="informationTab" class = ${targetRow} style = "width : 100%;display:flex;">
            <div style = "width : 30%">
                <table class="mytab" id ="symptom" style = "width: 100%; border-spacing: 0px;">
                    <tr>
                        <td colspan="2" style = "font-size:medium">Symptom</td>
                    </tr>
                    <tr>
                        <th style = "width: 50%"; height: 5%><p>Name</p></th>
                        <th style = "width: 50%"; height: 5%><p>Description</p></th>
                    </tr>
                </table>
            </div>
            <div style = "width : 5%"></div>

            <div style = "width : 30%">
                <table class="mytab" id ="prescription" style = "width: 100%; border-spacing: 0px;">
                    <tr>
                        <td colspan="2" style = "font-size:medium">Prescription</td>
                    </tr>
                    <tr>
                        <th style = "width: 50%; height: 5%"><p>Name</p></th>
                        <th style = "width: 50%"; height: 5%><p>Amount</p></th>
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
                    <textarea id = 'conclusion' style = "height: 80%;width: 100%;border: 1px solid black;text-align: left;"></textarea>
                </div>
                <button type = "button" id = "${cancelid}">Close</button>
                </div>
            </div></td>`

            // getdata
            GiveSymptom(targetRow)

            let cancel = document.getElementById(cancelid); 
            cancel.addEventListener('click', function(){cancelAdd()});
        }
    }
}

function GiveSymptom(orderID){
    let api = '/Doc/scheduler/detailget'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if(resp.code == "success"){
            var tableSymptom = document.getElementById("symptom");
            n = resp.data.length

            for(let i = 0; i < n; i++){
                var row = document.createElement("tr");
                cell1 = row.insertCell(0)
                cell2 = row.insertCell(1)

                cell1.setAttribute("style","width: 50%")
                cell2.setAttribute("style","width: 50%")
                
                a = document.createElement("textarea")
                b = document.createElement("textarea")
                
                a.readOnly = true
                b.readOnly = true
                
                a.value = resp.data[i].name
                b.value = resp.data[i].value

                cell1.appendChild(a)
                cell2.appendChild(b)

                tableSymptom.appendChild(row);
            }
        }
        getconclusion(orderID)
    };
    Http.send();
}

function getconclusion(orderID){
    let api2 = '/Doc/scheduler/result'
    let Http2 = new XMLHttpRequest();
    Http2.open("GET", url+api2+`?orderID=${orderID}`,true);
    Http2.onload = function(){
        resp2 = JSON.parse(Http2.responseText);
        if(resp2.code == 'success'){
            document.getElementById("conclusion").value = resp2.data[0].result
        }
        GivePP(orderID)
    };
    Http2.send();
}

function GivePP(orderID){
    let api = '/Doc/scheduler/getp'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if(resp.code == "success"){
            var tablePrescription = document.getElementById("prescription");
            n = resp.data.length
console.log(resp);
            for(let i = 0; i < n; i++){
                var row = document.createElement("tr");
                cell1 = row.insertCell(0)
                cell2 = row.insertCell(1)

                cell1.setAttribute("style","width: 50%")
                cell2.setAttribute("style","width: 50%")
                
                a = document.createElement("textarea")
                b = document.createElement("input")

                a.readOnly = true
                b.setAttribute("readonly","this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1').replace(/^0[^.]/, '0');")
                b.setAttribute("maxlength",'1')
                b.setAttribute("style","border: 1px solid black; width: 11%;margin:0.5px")
                
                a.value = resp.data[i].name
                for(let j = 0 ; j < 8 ; j++)
                    cell2.appendChild(b.cloneNode()).value = resp.data[i].amount[j]
                
                cell1.appendChild(a)
                tablePrescription.appendChild(row);
            }
        }
        document.getElementById("informationTab").parentNode.setAttribute("style","display:")
    };
    Http.send();
}

function cancelAdd(row){
    edit1row = true;
    document.getElementById("showDIV").remove();
}