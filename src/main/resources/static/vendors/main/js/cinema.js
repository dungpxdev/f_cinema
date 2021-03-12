const btnCinemaDeactives = document.querySelectorAll('.cinema-deactive');

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

