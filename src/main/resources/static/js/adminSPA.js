

/*function mostrarSeccion(seccion) {
    var secciones = document.querySelectorAll('.section');
    secciones.forEach(function (element) {
        element.classList.remove('active-section');
    });
    document.getElementById(seccion).classList.add('active-section');
}
*/
/*

function mostrarSeccion() {
    var select = document.getElementById("crud");
    var seccionSeleccionada = select.value;

    var secciones = document.getElementsByClassName("section");
    for (var i = 0; i < secciones.length; i++) {
        secciones[i].classList.remove("active-section");
    }

    var seccionActual = document.getElementById(seccionSeleccionada);
    seccionActual.classList.add("active-section");
}
*/


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
