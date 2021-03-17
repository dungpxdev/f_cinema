const btnCinemaDeactives = document.querySelectorAll('.cinema-deactive');
var btnSubmit = document.getElementById('btnSubmit');
const inputCinema = document.getElementById('name');
const inputCode = document.getElementById('code');
const inputAddress = document.getElementById('address');
const inputRoom = document.getElementById('numberOfRoom');


for (const btn of btnCinemaDeactives) {
    btn.addEventListener('click', e => {
        const url = window.location.origin + '/api/v1/admin/cinema/status';
        const cinema = {
            code: e.target.value
        };
        fetchData(url, cinema)
        .then(res => {
            if (res.message !== 'ACTIVE') {
                e.target.classList.replace('btn-danger', 'btn-success');
                e.target.innerText = 'Active';
            }else{
                e.target.classList.replace('btn-success', 'btn-danger');
                e.target.innerText = 'Deactive';
            }
        });
    })
}

/**
 * Fetch data from server (url) base on object parameters
 * 
 * @param {Object} params 
 * @param {String} url 
 */
 async function fetchData(url, params) {
    const response = await fetch(url,  {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(params)
    });

    return response.json();
}


btnSubmit.addEventListener('click', e =>{
    if(inputCinema.value===''){
        document.querySelectorAll('label[for~="name"]')[0].setAttribute('style', 'color: red');    
    }else{
        document.querySelectorAll('label[for~="name"]')[0].setAttribute('style', 'color: black');
    }

    if(inputCode.value===''){
        document.querySelectorAll('label[for~="code"]')[0].setAttribute('style', 'color: red');    
    }else{
        document.querySelectorAll('label[for~="code"]')[0].setAttribute('style', 'color: black');    

    }

    if(inputAddress.value===''){
        document.querySelectorAll('label[for~="address"]')[0].setAttribute('style', 'color: red');    
    }else{
        document.querySelectorAll('label[for~="address"]')[0].setAttribute('style', 'color: black')
    }

    if(inputRoom.value===''){
        document.querySelectorAll('label[for~="numberOfRoom"]')[0].setAttribute('style', 'color: red');    
    }else{
        document.querySelectorAll('label[for~="numberOfRoom"]')[0].setAttribute('style', 'color: black');    

    }
});

