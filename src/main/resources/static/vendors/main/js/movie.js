const inputMovie = document.getElementById('name');
const inputPrice = document.getElementById('price');
const inputCode = document.getElementById('code');
const inputStartDate = document.getElementById('startDate');
const inputEndDate = document.getElementById('endDate');
const inputCast = document.getElementById('cast');
const inputDirector = document.getElementById('director');
const inputLength = document.getElementById('length');
const inputLanguage = document.getElementById('language');
const inputDescription = document.getElementById('description');
const selectCountry = document.getElementById('country');
const image = document.getElementById('poster');
const movieGenre = document.getElementById('tags_1');
const notifySpan = document.getElementById('notify');

function errorSpanBuild(message) {
    return notifySpan.innerHTML = `<div class="alert alert-danger alert-dismissible fade in" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
        <strong>Hmmm 🤔!</strong> ${message}.
    </div>`;
}

function infoSpanBuild(message) {
    return notifySpan.innerHTML = `
    <div class="alert alert-info alert-dismissible fade in" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
    </button>
    <strong>Info 🐳!</strong> ${message}.
  </div>`;
};

inputPrice.addEventListener('focusout', e => {
    if (!parseInt(e.target.value)) {
        errorSpanBuild('Price must not empty and be a number');
    }else{
        notifySpan.innerHTML = 'Add movie';
    }
});

inputEndDate.addEventListener('focusout', e => {
    let startDate = new Date(inputStartDate.value);
    let endDate = new Date(e.target.value);
    let now = new Date();
    if (startDate.getTime() < now.getTime() || 
        endDate.getTime() < now.getTime() || 
        startDate.getTime() < endDate.getTime()) {
        errorSpanBuild('The date provided is invalid, Please check!');
    }
});

inputLength.addEventListener('focusin', e => {
    infoSpanBuild('The Length of movie just a number of minutes');
});

inputLength.addEventListener('focusout', e => {
    if (e.target.value === '') {
        errorSpanBuild('The Length of movie must not empty and be a number');
        return;
    }
    let characters = e.target.value.match(/[^\d]+/g);
    if (characters.length > 0) {
        errorSpanBuild('The Length of movie must not empty and be a number');
    }
});






