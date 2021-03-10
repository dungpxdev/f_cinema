const btnCinema = document.getElementById('btnCinema');
const btnMovie = document.getElementById('btnMovie');
const btnRoom = document.getElementById('btnRoom');
const btnSeat = document.getElementById('btnSeat');
const reservationTime = document.getElementById('reservation-time');
const btnSubmit = document.getElementById('btnSubmit');
const cinemaSelect = document.getElementById('cinemaSelect');


btnSubmit.addEventListener('click', (e) => {
    let cinemaParams = {
        cinemaCode: cinemaSelect.value
    }

    console.log(cinemaParams);
});
