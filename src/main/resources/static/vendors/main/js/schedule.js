const codeSelect = document.getElementById('code');
const dateSelect = document.getElementById('date');
const movieSelect = document.getElementById('movie');
const createdBySelect = document.getElementById('createdBy');
const statusSelect = document.getElementById('status');
const tableBody = document.querySelector('tbody');

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

let criteriaParams = {
  code: '',
  startTime: '',
  movie: '',
  createdBy: '',
  status: ''
}

const url = document.location.origin + '/api/v1/admin/schedule/search';

codeSelect.addEventListener('change', async e => {
  criteriaParams.code = codeSelect.value;
  await fetchData(url, criteriaParams).then(res => {
    render(res.data);
  });
});

dateSelect.addEventListener('change', async e => {
  criteriaParams.date = dateSelect.value;
    await fetchData(url, criteriaParams).then(res => {
    render(res.data);
  });
});

movieSelect.addEventListener('change', async e => {
  criteriaParams.movie = movieSelect.value;
    await fetchData(url, criteriaParams).then(res => {
    render(res.data);
  });
});

createdBySelect.addEventListener('change', async e => {
  criteriaParams.createdBy = createdBySelect.value;
    await fetchData(url, criteriaParams).then(res => {
    render(res.data);
  });
});

statusSelect.addEventListener('change', async e => {
  criteriaParams.status = statusSelect.value;
    await fetchData(url, criteriaParams).then(res => {
    render(res.data);
  });
});

function render(schedules) {
  tableBody.innerHTML = '';
  let bodies = '';
  for (const schedule of schedules) {
    bodies += `
    <tr class="odd pointer">
    <td class="a-center ">
      <input type="checkbox" class="flat" name="table_records">
    </td>
    <td class=" ">${schedule.code}</td>
    <td class=" ">${schedule.startTime + '-' + schedule.endTime}</td>
    <td class=" ">${schedule.movie.name} <i class="success fa fa-long-arrow-up"></i>
    </td>
    <td class=" ">${schedule.createdBy}</td>
    <td class=" ">${schedule.status}</td>
    <td class="a-right a-right ">${schedule.room.name}</td>
    <td class="a-right a-right ">${schedule.room.name}</td>
    <td class=" last"><a href="#">View</a>
    </td>
    </tr>
    `;
  }
  return tableBody.innerHTML = bodies;
}


function formatDates(date1, date2) {
  let d1 = new Date(date1);
  let d2 = new Date(date2);
  return `${d1.getDate()}-${d1.getMonth()}-${d1.getYear()} ${d1.getHours()}:${d1.getMinutes()} - 
  ${d2.getDate()}-${d2.getMonth()}-${d2.getYear()} ${d2.getHours()}:${d2.getMinutes()}`;
}
