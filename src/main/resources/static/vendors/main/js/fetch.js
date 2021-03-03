const cinemaSelect = document.getElementById('cinema');
const roomSelect = document.getElementById('room');
const movieSelect = document.getElementById('movie');
const timeInput = document.getElementById('reservation-time');
const submitBtn = document.getElementById('submitBtn');
const nameInput = document.getElementById('name');

let movieLength = 0;

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

movieSelect.addEventListener('change', async (e) => {
    const url = document.location.origin + '/api/v1/admin/movie';
    let movieParams = {
        code: movieSelect.value
    }
    await fetchData(url, movieParams)
    .then(movie => {
        if (movie.status === 'BAD_REQUEST') {
            alertify.error(movie.message);
        }
        movieLength = 0;
        movieLength = movie.length;
    });
})

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

function tConvert (time) {
    time = time.toString ().match (/^([01]\d|2[0-3])(:)([0-5]\d)(:[0-5]\d)?$/) || [time];
    if (time.length > 1) {
      time = time.slice (1);  // Remove full string match value
      time[5] = +time[0] < 12 ? 'AM' : 'PM'; // Set AM/PM
      time[0] = +time[0] % 12 || 12; // Adjust hours
    }
    return time.join ('');
}

submitBtn.addEventListener('click', (e) => {
    const url = document.location.origin + '/api/v1/admin/schedule';
    let startTime = timeInput.value.split('-')[0];
    let endTime = timeInput.value.split('-')[1];
    startTime = Date.parse(startTime);
    endTime = moment(startTime).add(movieLength, 'm').toDate();
    const params = {
        startTime,
        endTime,
        movie: movieSelect.value,
        room: roomSelect.value,
        cinema: cinemaSelect.value,
        name: nameInput.value
    }
    fetchData(url, params)
    .then(data => {
        console.log(data);
        alert(data.message);
        window.location.href = document.location.origin + '/admin/schedule';
    });
})
