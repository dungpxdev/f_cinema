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

async function addMoreRowOfSeat() {
    const url = document.location.origin + '/api/v1/admin/seat/create';
    let params = {
        cinemaCode: cinemaSelect.value,
        roomCode: roomSelect.value
    }
    await fetchData(url, params)
    .then(async data => {
        alert(data.response.message);
        container.innerHTML = '';
        await fetchData(document.location.origin + '/api/v1/admin/seat', params)
            .then(res => {
                let alphabet = "abcdefghijklmnopqrstuvwxyz".split('');
                let result = '';
                let seats = res.seats;
                for (let row = 0; row < seats.length; row++) {
                    result += `<div id="${alphabet[row].toUpperCase()}" class="seat-row">`;
                    for (let col = 0; col < 12; col++) {
                        const seat = seats[row][col];
                        result += `<div id="${seat ? seat.code : ''}" value="${seat ? seat.code : ''}" class="seat ${seat ? seat.status === 1 ? 'selected': '' : ''}">${seat ? seat.name ? seat.name: '' : ''}</div>`;
                    }
                    result += `</div>`;
                }
    
            container.innerHTML += result;
        });
    });


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

/**
 * Cinema select event
 * find all room by cinema code
 */
cinemaSelect.addEventListener('change', async () => {
    const url = document.location.origin + '/api/v1/admin/room';
    let cinemaParams = {
        cinemaCode: cinemaSelect.value
    }
    await fetchData(url, cinemaParams)
    .then(rooms => {
        console.log(rooms);
        let options = '';
        for (const room of rooms) {
            options += `<option value="${room.code}">${room.name}</option>`;
        }
        roomSelect.innerHTML = options;
    });
});

/**
 * Room select event
 * find all seat by room code
 */
roomSelect.addEventListener('change', async () => {
    const url = document.location.origin + '/api/v1/admin/seat';
    let cinemaParams = {
        cinemaCode: cinemaSelect.value,
        roomCode: roomSelect.value
    }
    container.innerHTML = '';
    await fetchData(url, cinemaParams)
        .then(res => {
            let alphabet = "abcdefghijklmnopqrstuvwxyz".split('');
            let result = '';
            let seats = res.seats;
            for (let row = 0; row < seats.length; row++) {
                result += `<div id="${alphabet[row].toUpperCase()}" class="seat-row">`;
                for (let col = 0; col < 12; col++) {
                    const seat = seats[row][col];
                    result += `<div id="${seat ? seat.code : ''}" class="seat ${seat ? seat.status === 1 ? 'selected': '' : ''}">${seat ? seat.name ? seat.name: '' : ''}</div>`;
                }
                result += `</div>`;
            }

        container.innerHTML += result;
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
    let modalInput = document.getElementById('name');

    modalInput.value = events.target.innerText;
    cinemaModalCode.innerHTML = cinemaSelect.value;
    if (events.target.className.includes('selected')) {
        document.querySelectorAll('.icheckbox_flat-green')[0].classList.add('checked');
        seatStatusModal.value = true;
    }else{
        document.querySelectorAll('.icheckbox_flat-green')[0].classList.remove('checked');
        seatStatusModal.value = false;
    }
    cinemaModalName.innerHTML = $('#cinema option:selected').text();
    currentSelectedSeat = events.target;
    $('#seatModal').modal('show');
}

window.addEventListener('DOMContentLoaded', (event) => {
    let inputStatusModal = document.querySelectorAll('.iCheck-helper')[0];
    inputStatusModal.addEventListener('click', e => {
        if (e.target.previousSibling.checked) {
            console.log('remove scheduling ....');
        }else {
            let scheduleCalendar = `<div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-3">Disable planing date: </label>
            <div class="col-md-9 col-sm-9 col-xs-9">
              <input type="text" id="inputScheduleCalendar" class="form-control">
              <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>
            </div>
          </div>`;
          let notify = `Date format: <code>MM/dd/yyyy HH:mm - MM/dd/yyyy HH:mm</code>`;
          document.getElementById('scheduleCalendar').innerHTML = scheduleCalendar;
          cinemaModalName.innerHTML = notify;
            console.log('show scheduling ....');
            console.log('call api ....');
        }
        document.getElementById('inputScheduleCalendar').value = getCurrentDayRange();
    });
});

/**
 * Modal save button event
 * update value of current select seat
 */
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
            code: currentSelectedSeat.id,
            status: seatStatusModal.checked ? 1 : 0
        }
        const url = document.location.origin + '/api/v1/admin/seat/add';
        await fetchData(url, seatParams)
        .then(async res => {
            console.log(res);
            alertify.alert('Seat Management', res.message);
            container.innerHTML = '';
            await fetchData(document.location.origin + '/api/v1/admin/seat', {
                cinemaCode: cinemaSelect.value,
                roomCode: roomSelect.value
            })
            .then(res => {
                let alphabet = "abcdefghijklmnopqrstuvwxyz".split('');
                let result = '';
                let seats = res.seats;
                for (let row = 0; row < seats.length; row++) {
                    result += `<div id="${alphabet[row].toUpperCase()}" class="seat-row">`;
                    for (let col = 0; col < 12; col++) {
                        const seat = seats[row][col];
                        result += `<div id="${seat ? seat.code : ''}" class="seat ${seat ? seat.status === 1 ? 'selected': '' : ''}">${seat ? seat.name ? seat.name: '' : ''}</div>`;
                    }
                    result += `</div>`;
                }
                container.innerHTML += result;
                document.querySelectorAll('.seat-row .seat:not(.occupied)').forEach(seat => {
                    seat.addEventListener('click', (e) => onHandleClickSeat(e), false);
                });
                $('#seatModal').modal('hide');
            });
        });

        if (!seatStatusModal.checked) {
            let inputScheduleCalendar = document.getElementById('inputScheduleCalendar');
            if (inputScheduleCalendar.value) {
                    let startTime = new Date(inputScheduleCalendar.value.split("-")[0]);
                    let expireTime = new Date(inputScheduleCalendar.value.split("-")[1]);
                    if (!isNaN(startTime.getTime()) && !isNaN(expireTime.getTime())) {
                        let params = {
                            startTime,
                            expireTime
                        }
                        
                    }
            }
        }
    }
});

function getCurrentDayRange() {
    let current = new Date();
    let nextDay = new Date();
    nextDay.setDate(nextDay.getDate() + 1);

    let now = {
        month: current.getMonth() < 10 ? '0' + current.getMonth().toString() : current.getMonth(),
        day: current.getDay() < 10 ? '0' + current.getDay().toString() : current.getDay(),
        year: current.getFullYear(),
        hour: current.getHours() < 10 ? '0' + current.getHours().toString() : current.getHours(),
        minutes: current.getMinutes() < 10 ? '0' + current.getMinutes().toString() : current.getMinutes()
    }

    let tomorrow = {
        month: nextDay.getMonth() < 10 ? '0' + nextDay.getMonth().toString() : nextDay.getMonth(),
        day: nextDay.getDay() < 10 ? '0' + nextDay.getDay().toString() : nextDay.getDay(),
        year: nextDay.getFullYear(),
        hour: nextDay.getHours() < 10 ? '0' + nextDay.getHours().toString() : nextDay.getHours(),
        minutes: nextDay.getMinutes() < 10 ? '0' + nextDay.getMinutes().toString() : nextDay.getMinutes()
    }

    return `${now.month}/${now.day}/${now.year} ${now.hour}:${now.minutes} - 
    ${tomorrow.month}/${tomorrow.day}/${tomorrow.year} ${tomorrow.hour}:${tomorrow.minutes}`;
}
