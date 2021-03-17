const inputNameRoom = document.getElementById('name');
const inputCodeRoom = document.getElementById('code');
const inputSeat = document.getElementById('numberOfSeat');
const selectCinema = document.getElementById('cinema');
const notifySpan = document.getElementById('notify');
var btnsubmit = document.getElementById('btnnhap');

function errorSpanBuild(message) {
    return notifySpan.innerHTML = `<div class="alert alert-danger alert-dismissible fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
        <strong>Hmmm 🤔!</strong> ${message}.
    </div>`;
}

inputSeat.addEventListener('focusout' , e =>{
    if(e.target.value%12!=0){ 
        errorSpanBuild("Please check number of seat is multiple of 12!!!!!");
        document.querySelectorAll('label[for~="numberOfSeat"]')[0].setAttribute('style', 'color: red');        
    }else{
        notifySpan.innerHTML = 'Add room';
        
    }
});

btnsubmit.addEventListener('click' , e =>{
    if(inputNameRoom===''){
        document.querySelectorAll('label[for~="name"]')[0].setAttribute('style', 'color: red');
    }
    if(inputCodeRoom===''){
        document.querySelectorAll('label[for~="code"]')[0].setAttribute('style', 'color: red');
    }
    if(inputSeat===''){
        document.querySelectorAll('label[for~="numberOfSeat"]')[0].setAttribute('style', 'color: red');
    }
    
});

