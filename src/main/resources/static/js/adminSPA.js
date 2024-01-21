
const operationSelect = document.getElementById('operationSelect');
const selectedSection = document.getElementById('selectedSection');

operationSelect.addEventListener('change', (event) => {
    const selectedOption = event.target.value;

    if (selectedOption === 'add') {
        mostrarSeccion('añadir');
    } else if (selectedOption === 'update') {
        mostrarSeccion('actualizar');
    } else if (selectedOption === 'delete') {
        mostrarSeccion('borrar');
    }
});

function mostrarSeccion(seccion) {
    var secciones = document.querySelectorAll('.section');
    secciones.forEach(function (element) {
        element.classList.remove('active-section');
    });
    document.getElementById(seccion).classList.add('active-section');
}

/*
const operationSelect = document.getElementById('operationSelect');
const selectedSection = document.getElementById('selectedSection');

operationSelect.addEventListener('change', (event) => {
    const selectedOption = event.target.value;

    if (selectedOption === 'add') {
        generateAddSection();
    } else if (selectedOption === 'update') {
        generateUpdateSection();
    } else if (selectedOption === 'delete') {
        generateDeleteSection();
    }
});

function generateAddSection() {
    selectedSection.innerHTML = `
        <div id="añadir" class="section active-section container mt-3">
            <h2>Elige un elemento para añadir</h2>
            <form onsubmit="return false">
                <select name="addSelect" id="addSelect">
                    <option value="country">Country</option>
                    <option value="language">Language</option>
                    <option value="language_role">Language Rol</option>
                    <option value="genre">Genre</option>
                    <option value="keyword">Keyword</option>
                    <option value="movie_company">Movie Company</option>
                    <option value="production_company">Production Company</option>
                    <option value="gender">Genre</option>
                    <option value="person">Person</option>
                    <option value="department">Department</option>
                </select>

                <div id="countryAddElements">
                    <label for="countryIsoCode">
                        <input type="text" name="countryIsoCode" id="countryIsoCode" placeholder="country_iso_code">
                    </label>
                    <label for="countryName">
                        <input type="text" name="countryName" id="countryName" placeholder="Country Name">
                    </label>
                </div>

                <div id="languageAddElements">
                    <label for="languageCode">
                        <input type="text" name="languageCode" id="languageCode" placeholder="Language Code">
                    </label>
                    <label for="languageName">
                        <input type="text" name="languageName" id="languageName" placeholder="Language Name">
                    </label>
                </div>

            </form>
        </div>
    `;
}
*/
const addSelect = document.getElementById('addSelect');
const addElements = document.getElementById('addElements');

addSelect.addEventListener('change', (event) => {
    const selectedOption = event.target.value;
    const placeholderText = getPlaceholderText(selectedOption);

    if (selectedOption === 'department' || selectedOption === 'genre' || selectedOption === 'keyword' || selectedOption === 'production_company' || selectedOption === 'person') {
        addElements.innerHTML = `
    <input type="text" name="${selectedOption}" id="${selectedOption}" placeholder="${placeholderText}">
    `;
    } else {
        addElements.innerHTML = `
    <label for="${selectedOption}IsoCode">
        <input type="text" name="${selectedOption}IsoCode" id="${selectedOption}IsoCode" placeholder="${placeholderText} Iso Code">
    </label>
    <label for="${selectedOption}Name">
        <input type="text" name="${selectedOption}Name" id="${selectedOption}Name" placeholder="${placeholderText} Name">
    </label>
    `;
    }
});

function getPlaceholderText(selectedOption) {
    if (selectedOption === 'department') {
        return 'Department Iso Code';
    } else if (selectedOption === 'genre') {
        return 'Genre Name';
    } else if (selectedOption === 'keyword') {
        return 'Keyword Name';
    } else if (selectedOption === 'production_company') {
        return 'Production Company Iso Code';
    } else if (selectedOption === 'person') {
        return 'Person Name';
    } else {
        return '';
    }
}



