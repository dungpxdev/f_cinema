const container = document.querySelector('.room');
const seats = document.querySelectorAll('.seat-row .seat:not(.occupied)');
const movieSelect = document.getElementById('room');
const addRowBtn = document.getElementById('addRow');
const addColBtn = document.getElementById('addCol');

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
container.addEventListener('click', e => {
    if (
        e.target.classList.contains('seat') &&
        !e.target.classList.contains('occupied')
    ) {
        e.target.classList.toggle('selected');
    }
});

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
