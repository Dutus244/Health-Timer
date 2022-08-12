const url='http://localhost:803';

function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("waitlist");
    tr = table.getElementsByTagName("tr");
    
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[1];
        if (td) {
          if (td.innerText.toUpperCase().indexOf(filter) > -1)
            tr[i].style.display = "";
          else
            tr[i].style.display = "none";
        }
    }
  }

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
    var table = document.getElementById("waitlist");
    
    for (let i = 1; i < table.getElementsByTagName("tr").length; i++){
        if(table.getElementsByTagName("tr")[i].id == targerID){
            var id = "itcouldbesame" + targetRow
            var row = table.insertRow(i + 1);

            let saveid = `save${id}`;
            let doneid = `done${id}`;
            let cancelid = `cancel${id}`;

            row.setAttribute("class", "edit");
            row.setAttribute("id","editDIV")

            row.innerHTML  = `<td colspan = '4' style = "display :none;"><div id="informationTab" class = ${targetRow} style = "width : 100%;display:flex;">
            <div style = "width : 30%">
                <table class="mytab" id ="symptom" style = "width: 100%; border-spacing: 0px;">
                    <tr>
                        <td colspan="2" style = "font-size:medium">Symptom</td>
                    </tr>
                    <tr>
                        <th style = "width: 50%"><p>Name</p></th>
                        <th style = "width: 50%"><p>Description</p></th>
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
                <button type = "button" id = "${doneid}">Done</button>
                <button type = "button" id = "${saveid}">Save</button>
                <button type = "button" id = "${cancelid}">Close</button>
                </div>
            </div></td>`


            // getdata

            GetSymptom(targetRow)

            //
            
            addNewRowSymptom();
            addNewRowPrescription();
            let addPre = document.getElementById(doneid);
            addPre.addEventListener('click', function(){done()});

            let cancel = document.getElementById(cancelid); 
            cancel.addEventListener('click', function(){cancelAdd()});

            let remove = document.getElementById(saveid); 
            remove.addEventListener('click', function(){save()});
        }
    }
}

function GetSymptom(orderID){
    let api = '/Doc/scheduler/detailget'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if(resp.code == "success"){
            var tableSymptom = document.getElementById("symptom");
            n = resp.data.length
            
            buton = document.createElement('div')
            buton.setAttribute("style","width:15%")

            col1 = document.createElement("textarea")
            col1.readOnly = true;
            col2 = col1.cloneNode()
            col1.setAttribute("style","width:85%")

            icon = document.createElement('i')
            icon.setAttribute("class","fa fa-times-circle")
            icon.setAttribute("onclick",'removeSymptom(this)')
            for(let i = 0 ; i < n ; i ++){
                row = tableSymptom.insertRow(2);
                a = col1.cloneNode()
                a.innerHTML = resp.data[i].name

                b = col2.cloneNode()
                b.innerHTML = resp.data[i].value
        
                c1 = row.insertCell(0)
                c2 = row.insertCell(1)

                k = c1.appendChild(buton.cloneNode())
                k.appendChild(icon.cloneNode())
                c1.appendChild(a)
                c2.appendChild(b)
            }
        }
        getconclusion(orderID)
    };
    Http.send();
}

function removeSymptom(e){
    let api = '/Doc/scheduler/detailremove'
    let Http = new XMLHttpRequest();
    let name = e.parentElement.parentElement.parentElement.childNodes[0].childNodes[1].value
    
    orderID = document.getElementById("informationTab").className
    
    Http.open("GET", url+api+`?orderID=${orderID}&name=${name}&auth=${getCookie('Auth')}`,true);
    Http.onload = function(){
        if(resp.code == 'success'){
            e.parentElement.parentElement.parentElement.remove();
        }
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
        GetPP(orderID)
    };
    Http2.send();
}

function GetPP(orderID){
    let api = '/Doc/scheduler/getp'
    const Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.onload = function(){
        resp = JSON.parse(Http.responseText);
        if(resp.code == "success"){
            var tableSymptom = document.getElementById("prescription");
            n = resp.data.length

            buton = document.createElement('div')
            buton.setAttribute("style","width:15%")

            col1 = document.createElement("textarea")
            col1.readOnly = true;
            col1.setAttribute("style","width:85%")

            icon = document.createElement('i')
            icon.setAttribute("class","fa fa-times-circle")
            icon.setAttribute("onclick",'removePP(this)')

            col2 = document.createElement("input")
            col2.setAttribute("maxlength",'1')
            col2.setAttribute("style","border: 1px solid black; width: 11%;margin:0.5px")
            col2.readOnly=true
            for(let i = 0 ; i < n ; i ++){
                row = tableSymptom.insertRow(2);
                a = col1.cloneNode()
                a.innerHTML = resp.data[i].name

                c1 = row.insertCell(0)
                c2 = row.insertCell(1)
                k = c1.appendChild(buton.cloneNode())
                k.appendChild(icon.cloneNode())
                c1.appendChild(a)
                for (let j = 0 ; j < 8 ; j ++){
                    b = col2.cloneNode()
                    b.value = resp.data[i].amount[j]
                    c2.appendChild(b)
                }
            }
        }
        document.getElementById("informationTab").parentNode.setAttribute("style","display:")
    };
    Http.send();
}

function removePP(e){
    let api = '/Doc/scheduler/removep'
    let Http = new XMLHttpRequest();
    let name = e.parentElement.parentElement.parentElement.childNodes[0].childNodes[1].value
    
    orderID = document.getElementById("informationTab").className
    
    Http.open("GET", url+api+`?orderID=${orderID}&name=${name}&auth=${getCookie('Auth')}`,true);
    Http.onload = function(){
        console.log(resp);
        if(resp.code == 'success'){
            e.parentElement.parentElement.parentElement.remove();
        }
    };
    Http.send();


}

function checkFullTable(table){
    var n = table.childElementCount + 1
    for(let i = 2 ; i < n  ; i++){
        try{
        if (table.childNodes.item(i).firstChild.firstChild.value.length == 0)
            return 0;
        }
        catch(e){

        }
    }
    return 1;
}

function addNewRowSymptom(e){
    var tableSymptom = document.getElementById("symptom");

    if(checkFullTable(tableSymptom) == 1){
        var row = document.createElement("tr");
        cell1 = row.insertCell(0)
        cell2 = row.insertCell(1)

        cell1.setAttribute("style","width: 50%")
        cell2.setAttribute("style","width: 50%")
        
        a = document.createElement("textarea")
        cell2.appendChild(document.createElement("textarea"))
        a.setAttribute('onkeyup','addNewRowSymptom(this)')
        cell1.appendChild(a)
        tableSymptom.appendChild(row);
    }
    else{
        try {
            if(e.value.length==0 && tableSymptom.childElementCount!=2){
                e.parentElement.parentElement.remove()
            }   
        } catch (error) {
            
        }
    }
}

function addNewRowPrescription(e){
    let tablePrescription = document.getElementById("prescription");
    
    if(checkFullTable(tablePrescription) == 1){
        var row = document.createElement("tr");
        cell1 = row.insertCell(0)
        cell2 = row.insertCell(1)

        cell1.setAttribute("style","width: 50%")
        cell2.setAttribute("style","width: 50%")
        
        a = document.createElement("textarea")
        b = document.createElement("input")
        b.setAttribute("oninput","this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1').replace(/^0[^.]/, '0');")
        b.setAttribute("maxlength",'1')
        b.setAttribute("style","border: 1px solid black; width: 11%;margin:0.5px")
        for(let i = 0 ; i < 8 ; i ++)
            cell2.appendChild(b.cloneNode())
        a.setAttribute('onkeyup','addNewRowPrescription(this)')
        cell1.appendChild(a)
        tablePrescription.appendChild(row);
    }
    else{
        try {
            if(e.value.length==0 && tablePrescription.childElementCount!=2){
                e.parentElement.parentElement.remove()
            }   
        } catch (error) {
            
        }
    }
}

function save() {
    let tableSymptom = document.getElementById("symptom");
    let tablePrescription = document.getElementById("prescription");
    let orderID = document.getElementById("informationTab").className;
    
    n = tableSymptom.childElementCount

    for(let i = 2 ; i < n ; i ++){
        if (tableSymptom.childNodes[i].firstChild.childElementCount==1){
            let api = '/Doc/scheduler/detailadd'
            let Http = new XMLHttpRequest();
            let name = tableSymptom.childNodes[i].firstChild.firstChild.value
            let value = tableSymptom.childNodes[i].childNodes[1].firstChild.value
    
            Http.open("GET", url+api+`?orderID=${orderID}&name=${name}&auth=${getCookie('Auth')}&value=${value}`,true);
            Http.send();
        }
    }

    m = tablePrescription.childElementCount
    for(let i = 2 ; i < m ; i ++){
        if (tablePrescription.childNodes[i].firstChild.childElementCount==1){
            let api = '/Doc/scheduler/givep'
            let Http = new XMLHttpRequest();
            let name = tablePrescription.childNodes[i].firstChild.firstChild.value
            let value =  ""
            for (let j  = 0 ; j < 8 ; j ++)
                value += tablePrescription.childNodes[i].childNodes[1].childNodes[j].value
            console.log(name,value)
            Http.open("GET", url+api+`?orderID=${orderID}&name=${name}&auth=${getCookie('Auth')}&value=${value}`,true);
            Http.send();
        }
    }

    let conclusion = document.getElementById('conclusion').value
    
    if (1) {
        let api = '/Doc/scheduler/resultAdd'
            let Http = new XMLHttpRequest();
            Http.open("GET", url+api+`?orderID=${orderID}&auth=${getCookie('Auth')}&value=${conclusion}`,true);
            Http.send();
    }

    cancelAdd();
}

function done() {
    let orderID = document.getElementById("informationTab").className;
    save();
    let api = '/Doc/scheduler/done'
    let Http = new XMLHttpRequest();
    Http.open("GET", url+api+`?orderID=${orderID}`,true);
    Http.send();
    document.getElementById(`row_${orderID}`).remove()
}

function cancelAdd(row){
    edit1row = true;
    document.getElementById("editDIV").remove();
}