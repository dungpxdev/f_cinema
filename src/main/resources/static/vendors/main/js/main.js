const container = document.querySelector('.room');
const seats = document.querySelectorAll('.seat-row .seat:not(.occupied)');
const movieSelect = document.getElementById('room');
const addRowBtn = document.getElementById('addRow');
const addColBtn = document.getElementById('addCol');
const cinemaSelect = document.getElementById('cinema');
const roomSelect = document.getElementById('room');
const btnSaveSeatModal = document.getElementById('modal');
const seatModal = document.getElementById('seatModal');


populateUI();

// Save selected movie index and price
function setMovieData(movieIndex, moviePrice) {
    localStorage.setItem('selectedMovieIndex', movieIndex);
    localStorage.setItem('selectedMoviePrice', moviePrice);
}

// Get data from localstorage and populate UI
function populateUI() {
    const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));

    if (selectedSeats !== null && selectedSeats.length > 0) {
        seats.forEach((seat, index) => {
            if (selectedSeats.indexOf(index) > -1) {
                seat.classList.add('selected');
            }
        });
    }

    const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');

    if (selectedMovieIndex !== null) {
        movieSelect.selectedIndex = selectedMovieIndex;
    }
}

// Seat click event
// container.addEventListener('click', e => {
//     if (
//         e.target.classList.contains('seat') &&
//         !e.target.classList.contains('occupied')
//     ) {
//         e.target.classList.toggle('selected');
//     }
// });

function addMoreRowOfSeat() {
    let rowOfSeat = `<div class="seat-row">
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
        <div class="seat"></div>
    </div>`;
    container.innerHTML += rowOfSeat;
    document.querySelectorAll('.seat-row .seat:not(.occupied)').forEach(seat => {
        seat.addEventListener('click', (e) => onHandleClickSeat(e), false);
    });
}

function addMoreCol() {
    const rows = document.querySelectorAll('.seat-row');
    const seat = `<div class="seat"></div>`;
    rows.forEach(row => {
        row.innerHTML += seat;
    });
}

//Button add row seat click event
addRowBtn.addEventListener('click',() => addMoreRowOfSeat());

//Button add col seat click event
addColBtn.addEventListener('click', () => addMoreCol());

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

cinemaSelect.addEventListener('change', async () => {
    const url = document.location.origin + '/api/v1/admin/room';
    let cinemaParams = {
        cinemaCode: cinemaSelect.value
    }
    await fetchData(url, cinemaParams)
    .then(rooms => {
        let options = '';
        for (const room of rooms) {
            options += `<option value="${room.code}">${room.name}</option>`;
        }
        roomSelect.innerHTML = options;
    });
});

roomSelect.addEventListener('change', async () => {
    const url = document.location.origin + '/api/v1/admin/seat';
    let cinemaParams = {
        cinemaCode: cinemaSelect.value,
        roomCode: roomSelect.value
    }
    container.innerHTML = '';
    await fetchData(url, cinemaParams)
        .then(data => {
            let alphabet = "abcdefghijklmnopqrstuvwxyz".split('');
            let rowIndex = 0;
            let result = '';

            console.log(data);
            for (let row = 0; row < data.length / 12; row += 12) {
                for (let col = 0; col < 12; col++) {
                    const seat = data[row][i];
                    console.log(seat);
                }
            }


        // for (let i = 0; i < data.length / 12; i+=12) {
        //     result += `<div id="${alphabet[rowIndex++].toUpperCase()}" class="seat-row">
        //     <div id="${data[i] ? data[i].code : ''}" value="${data[i] ? data[i].code : ''}" class="seat ${data[i] ? data[i].status === 1 ? 'selected': '' : ''}">${data[i] ? data[i].name : ''}</div>
        //     <div id="${data[i + 1] ? data[i + 1].code : ''}" value="${data[i + 1] ? data[i + 1].code : ''}" class="seat ${data[i + 1] ? data[i + 1].status === 1 ? 'selected': '' : ''}">${data[i + 1] ? data[i + 1].name : ''}</div>
        //     <div id="${data[i + 2] ? data[i + 2].code : ''}" value="${data[i + 2] ? data[i + 2].code : ''}" class="seat ${data[i + 2] ? data[i + 2].status === 1 ? 'selected': '' : ''}">${data[i + 2] ? data[i + 2].name : ''}</div>
        //     <div id="${data[i + 3] ? data[i + 3].code : ''}" value="${data[i + 3] ? data[i + 3].code : ''}" class="seat ${data[i + 3] ? data[i + 3].status === 1 ? 'selected': '' : ''}">${data[i + 3] ? data[i + 3].name : ''}</div>
        //     <div id="${data[i + 4] ? data[i + 4].code : ''}" value="${data[i + 4] ? data[i + 4].code : ''}" class="seat ${data[i + 4] ? data[i + 4].status === 1 ? 'selected': '' : ''}">${data[i + 4] ? data[i + 4].name : ''}</div>
        //     <div id="${data[i + 5] ? data[i + 5].code : ''}" value="${data[i + 5] ? data[i + 5].code : ''}" class="seat ${data[i + 5] ? data[i + 5].status === 1 ? 'selected': '' : ''}">${data[i + 5] ? data[i + 5].name : ''}</div>
        //     <div id="${data[i + 6] ? data[i + 6].code : ''}" value="${data[i + 6] ? data[i + 6].code : ''}" class="seat ${data[i + 6] ? data[i + 6].status === 1 ? 'selected': '' : ''}">${data[i + 6] ? data[i + 6].name : ''}</div>
        //     <div id="${data[i + 7] ? data[i + 7].code : ''}" value="${data[i + 7] ? data[i + 7].code : ''}" class="seat ${data[i + 7] ? data[i + 7].status === 1 ? 'selected': '' : ''}">${data[i + 7] ? data[i + 7].name : ''}</div>
        //     <div id="${data[i + 8] ? data[i + 8].code : ''}" value="${data[i + 8] ? data[i + 8].code : ''}" class="seat ${data[i + 8] ? data[i + 8].status === 1 ? 'selected': '' : ''}">${data[i + 8] ? data[i + 8].name : ''}</div>
        //     <div id="${data[i + 9] ? data[i + 9].code : ''}" value="${data[i + 9] ? data[i + 9].code : ''}" class="seat ${data[i + 9] ? data[i + 9].status === 1 ? 'selected': '' : ''}">${data[i + 9] ? data[i + 9].name : ''}</div>
        //     <div id="${data[i + 10] ? data[i + 10].code : ''}" value="${data[i + 10] ? data[i + 10].code : ''}" class="seat ${data[i + 10] ? data[i + 10].status === 1 ? 'selected': '' : ''}">${data[i + 10] ? data[i + 10].name : ''}</div>
        //     <div id="${data[i + 11] ? data[i + 11].code : ''}" value="${data[i + 11] ? data[i + 11].code : ''}" class="seat ${data[i + 11] ? data[i + 11].status === 1 ? 'selected': '' : ''}">${data[i + 11] ? data[i + 11].name : ''}</div>
        // </div>`;
        // }
        container.innerHTML += result;
        console.log('called');
        document.querySelectorAll('.seat-row .seat:not(.occupied)').forEach(seat => {
            seat.addEventListener('click', (e) => onHandleClickSeat(e), false);
        });

    });
});

let currentSelectedSeat = '';

function onHandleClickSeat(events) {
    let cinemaModalCode = document.getElementById('cinemaModalCode');
    let cinemaModalName = document.getElementById('cinemaModalName');
    let seatStatusModal = document.getElementById('seatStatusModal');

    cinemaModalCode.innerHTML = cinemaSelect.value;
    cinemaModalName.innerHTML = $('#cinema option:selected').text();
    seatStatusModal.setAttribute('checked', '');
    currentSelectedSeat = events.target;
    $('#seatModal').modal('show');
}

btnSaveSeatModal.addEventListener('click', async () => {
    let seatName = document.getElementById('name');
    let seatStatusModal = document.getElementById('seatStatusModal');
    if (!currentSelectedSeat.id === '') {
        seatName.value = currentSelectedSeat.id;
        //currentSelectedSeat.checked ? seatStatusModal.setAttribute('checked', '') : '';
        $('#seatModal').modal('hide');
    }else {
        let seatParams = {
            cinemaCode: cinemaSelect.value,
            roomCode: roomSelect.value,
            name: seatName.value,//check
            code: seatName.value,
            status: seatStatusModal.checked ? 1 : 0
        }
        const url = document.location.origin + '/api/v1/admin/seat/add';
        await fetchData(url, seatParams)
        .then(res => {
            console.log(res);
            alert(res.message);
        })
        console.log(seatParams);
        $('#seatModal').modal('hide');
    }
});
